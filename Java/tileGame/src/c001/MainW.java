package c001;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.ThreadLocalRandom;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class MainW extends JFrame
{

   private JPanel timeMoves;
   private JPanel game;
   private JPanel buttons;

   private JLabel movesL;
   private JLabel timeL;

   private ArrayList<JButton> gameButtons;

   private JButton resetB;
   private JButton exitB;

   private Timer gameT;
   private timerListener TL;

   private gameListener GL;
   private buttonListener BL;

   private MainW thisWindow;

   public MainW()
   {
      super("15 piece");
      thisWindow = this;

      timeL = new JLabel("00:00");
      movesL = new JLabel("Moves: 0");

      TL = new timerListener(timeL);
      gameT = new Timer(1000, TL);

      timeMoves = new JPanel(new FlowLayout());
      timeMoves.setPreferredSize(new Dimension(20, 400));
      timeMoves.add(timeL);
      timeMoves.add(movesL);

      BL = new buttonListener();

      resetB = new JButton("Reset");
      resetB.addActionListener(BL);
      exitB = new JButton("Exit");
      exitB.addActionListener(BL);

      buttons = new JPanel(new FlowLayout());
      buttons.setPreferredSize(new Dimension(20, 350));
      buttons.add(resetB);
      buttons.add(exitB);

      gameButtons = new ArrayList<JButton>();

      int i = 0;

      game = new JPanel(new GridLayout(4, 4));
      game.setMinimumSize(new Dimension(300, 300));
      game.setMaximumSize(new Dimension(300, 300));
      game.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

      while (i < 15)
      {
         gameButtons.add(new JButton("" + (i + 1)));
         i++;
      }
      GL = new gameListener();
      for (JButton temp : gameButtons)
         temp.addActionListener(GL);

      setLayout(new BorderLayout());

      Box box = new Box(BoxLayout.Y_AXIS);
      box.setAlignmentX(JComponent.CENTER_ALIGNMENT);
      box.add(timeMoves);
      box.add(Box.createVerticalGlue());
      box.add(game);
      box.add(Box.createVerticalGlue());
      box.add(buttons);
      add(box);

      setSize(400, 410);
      setVisible(true);
      setResizable(false);
      setDefaultCloseOperation(EXIT_ON_CLOSE);
      repaint();

   }

   private class gameListener implements ActionListener
   {
      private ArrayList<gridNode> grid;
      private int moveCnt;
      private ArrayList<JButton> randomButtons;

      private gridNode hole;

      gameListener()
      {
         grid = new ArrayList<gridNode>();

         int i = 0;
         int j = 0;
         while (i < 4)
         {
            while (j < 4)
            {
               grid.add(new gridNode(j, i));
               j++;
            }
            j = 0;
            i++;
         }

         randomButtons = (ArrayList<JButton>) gameButtons.clone();
         Collections.shuffle(randomButtons);

         int holeN = ThreadLocalRandom.current().nextInt(0, 16);
         grid.get(holeN).setBut(null);
         hole = grid.get(holeN);
         i = 0;
         j = 0;
         while (i < 16)
         {
            if (!grid.get(i).isSet())
            {
               grid.get(i).setBut(randomButtons.get(j));
               i++;
               j++;
            }
            else
               i++;
         }

         for (gridNode tempNode : grid)
         {
            if (tempNode.getButt() != null)
               game.add(tempNode.getButt());
            else
               game.add(new JPanel());
         }

         moveCnt = 0;

      }

      public boolean isInLnWtEmpty(gridNode node)
      {
         return node.getX() == hole.getX() || node.getY() == hole.getY();
      }

      private gridNode getNodeBy(int x, int y)
      {
         for (gridNode tempNode : grid)
            if (tempNode.x == x && tempNode.y == y)
               return tempNode;
         return null;
      }

      @Override
      public void actionPerformed(ActionEvent e)
      {
         gridNode clickedNode = null;

         JButton clickedButton = (JButton) e.getSource();

         for (gridNode tempNode : grid)
         {
            if (clickedButton == tempNode.getButt())
            {
               clickedNode = tempNode;
               break;
            }
         }

         if (isInLnWtEmpty(clickedNode))
         {
            if (!gameT.isRunning())
            {
               gameT.restart();
            } // gameT.start();thisWindow.dispose();}

            int helpX, helpY;
            gridNode helpNode;

            if (clickedNode.y == hole.y)
            {

               helpY = hole.y;
               if (clickedNode.x < hole.x)
               {
                  helpX = hole.x - 1;
                  while (helpX >= clickedNode.x)
                  {
                     helpNode = getNodeBy(helpX, helpY);
                     hole.setBut(helpNode.getButt());
                     helpNode.setBut(null);
                     hole = helpNode;
                     helpX--;
                  }

               }
               else
               {
                  helpX = hole.x + 1;
                  while (helpX <= clickedNode.x)
                  {
                     helpNode = getNodeBy(helpX, helpY);
                     hole.setBut(helpNode.getButt());
                     helpNode.setBut(null);
                     hole = helpNode;
                     helpX++;
                  }

               }
            }
            else
            {
               helpX = hole.x;
               if (clickedNode.y < hole.y)
               {
                  helpY = hole.y - 1;
                  while (helpY >= clickedNode.y)
                  {
                     helpNode = getNodeBy(helpX, helpY);
                     hole.setBut(helpNode.getButt());
                     helpNode.setBut(null);
                     hole = helpNode;
                     helpY--;
                  }
               }
               else
               {

                  helpY = hole.y + 1;
                  while (helpY <= clickedNode.y)
                  {
                     helpNode = getNodeBy(helpX, helpY);
                     hole.setBut(helpNode.getButt());
                     helpNode.setBut(null);
                     hole = helpNode;
                     helpY++;
                  }
               }

            }

            game.removeAll();
            for (gridNode tempNode : grid)
            {
               if (tempNode.getButt() != null)
                  game.add(tempNode.getButt());
               else
                  game.add(new JPanel());
            }
            game.validate();

            

               moveCnt++;
            movesL.setText("Moves: " + moveCnt);
            
            
            if (isSolved())JOptionPane.showMessageDialog(thisWindow, "Solving the puzzle took you" + timeL.getText() + " and "+moveCnt + " moves. Click reset to play again.", "Puzzle solved", JOptionPane.INFORMATION_MESSAGE);
         }

      }

      public void reset()
      {
         for (gridNode tempNode : grid)
            tempNode.reset();
         game.removeAll();

         Collections.shuffle(randomButtons);

         int holeN = ThreadLocalRandom.current().nextInt(0, 16);
         grid.get(holeN).setBut(null);
         hole = grid.get(holeN);
         int i = 0;
         int j = 0;
         while (i < 16)
         {
            if (!grid.get(i).isSet())
            {
               grid.get(i).setBut(randomButtons.get(j));
               i++;
               j++;
            }
            else
               i++;
         }

         for (gridNode tempNode : grid)
         {
            if (tempNode.getButt() != null)
               game.add(tempNode.getButt());
            else
               game.add(new JPanel());
         }

         if (isSolved())
            this.reset();

         moveCnt = 0;
         movesL.setText("Moves: 0");
         game.validate();
         TL.reset();
      }

      private boolean isSolved()
      {
         int i = 0;
         while (i < 15)
         {
            try
            {
               if (!grid.get(i).getButt().equals(gameButtons.get(i)))
                  return false;
            }
            catch (NullPointerException e)
            {
               return false;
            }
            i++;
         }
         return true;

      }

   }

   private class gridNode
   {
      private JButton but;
      private boolean set;

      private int x;
      private int y;

      public gridNode(int xV, int yV)
      {
         x = xV;
         y = yV;
         but = null;
         set = false;
      }

      public void setBut(JButton button)
      {
         but = button;
         set = true;
      }

      public boolean isSet()
      {
         return set;
      }

      public void reset()
      {
         but = null;
         set = false;
      }

      public JButton getButt()
      {
         return but;
      }

      public int getX()
      {
         return x;
      }

      public int getY()
      {
         return y;
      }

   }

   private class buttonListener implements ActionListener
   {

      @Override
      public void actionPerformed(ActionEvent arg0)
      {
         if (arg0.getSource() == resetB)
         {
            GL.reset();
            TL.reset();
            gameT.stop();
         }
         if (arg0.getSource() == exitB)
         {
            thisWindow.dispose();
         }
      }

   }

   private class timerListener implements ActionListener
   {
      private JLabel timerL;
      private int seconds;

      public timerListener(JLabel label)
      {
         timerL = label;
         timerL.setText("00:00");
         seconds = 0;
      }

      public void reset()
      {
         seconds = 0;
         timerL.setText("00:00");
      }

      @Override
      public void actionPerformed(ActionEvent arg0)
      {
         seconds++;
         int minutes = seconds / 60;
         int secondS = seconds % 60;
         String sS;
         String mS;
         if (minutes < 10)
         {
            mS = "0" + minutes;
         }
         else
         {
            mS = "" + minutes;
         }

         if (secondS < 10)
         {
            sS = "0" + secondS;
         }
         else
         {
            sS = "" + secondS;
         }

         timerL.setText(mS + ":" + sS);
      }

   }

   public static void main(String[] args)
   {
      new MainW();
   }
}
