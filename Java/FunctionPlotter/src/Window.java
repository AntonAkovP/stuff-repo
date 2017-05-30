import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;


public class Window extends JFrame
{

   protected drawingPane pane;
   protected JPanel buttons;
   protected JButton Linear;
   protected JButton Quadratic;
   
   protected LinearEquation linear;
   protected QuadraticEquation quadratic;
   
   public Window()
   {
      super("Plotter");
      
      addComponentListener(new ComponentListener(){
         @Override
         public void componentHidden(ComponentEvent arg0)
         {
            // TODO Auto-generated method stub
            
         }

         @Override
         public void componentMoved(ComponentEvent arg0)
         {
            // TODO Auto-generated method stub
            
         }

         @Override
         public void componentResized(ComponentEvent arg0)
         {
            pane.repaint();
         }

         @Override
         public void componentShown(ComponentEvent arg0)
         {
            // TODO Auto-generated method stub
            
         }
      });
      
      
      linear = new LinearEquation();
      quadratic = new QuadraticEquation();
      
      pane= new drawingPane(new TestEquation());
      pane.setPreferredSize(new Dimension(300, 400));
      
      ButtonListener BL = new ButtonListener();
      
      Linear = new JButton("Linear");
      Linear.addActionListener(BL);
      
      Quadratic = new JButton("Quadratic");
      Quadratic.addActionListener(BL);
      
      
      
      buttons = new JPanel();
      buttons.setLayout(new FlowLayout());
      buttons.add(Linear);
      buttons.add(Quadratic);
      
      
      setLayout(new BorderLayout());
      add(pane, BorderLayout.CENTER);
      add(buttons, BorderLayout.SOUTH);
      
      setSize(600, 650);
      setMinimumSize(new Dimension(400, 450));
      setVisible(true);
      setDefaultCloseOperation(EXIT_ON_CLOSE);
      
   }
   private class ButtonListener implements ActionListener
   {

      @Override
      public void actionPerformed(ActionEvent arg0)
      {
         if(arg0.getSource()==Linear)pane.setFunction(linear);
         if(arg0.getSource()==Quadratic)pane.setFunction(quadratic);
         
      }
      
   }
   
   public static void main(String[] args)
   {
      new Window();
   }
   
}
