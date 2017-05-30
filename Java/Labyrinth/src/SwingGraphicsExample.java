
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.util.concurrent.ThreadLocalRandom;

public class SwingGraphicsExample
{

   private static class DrawingPanel extends JPanel
   {
      private static final long serialVersionUID = 1L;
      
      @Override
      protected void paintComponent(Graphics g)
      {
         int xOffset = 50, yOffset=50;
         int x2 = this.getWidth() - 100, y2= this.getHeight() - 100;
         g.drawRect(xOffset, yOffset, x2, y2);
         //drawLineWithDoor(g,xOffset+50,yOffset,xOffset+50,this.getHeight()-50,50,true);
         drawLab(20, 15, xOffset, yOffset, x2+xOffset, y2+yOffset, g);
         
         //g.drawLine(xOffset+50, yOffset, xOffset+50, this.getHeight()-50);

      }
      
      /**
       * Draws a labyrinth in a specified rectangular area
       * <p>
       * Called originally, by paintComponent in this case, to start the recursion which calls on the same method with one extra parameter. Redundant but makes it look a lot better.
       * </p>
       * @param minDist minimal distance between walls
       * @param doorWidth width of the doors in the walls
       * @param xb1 x coordinate of the top-left corner of the area
       * @param yb1 y coordinate of the top-left corner of the area
       * @param xb2 x coordinate of the bottom-right corner of the area
       * @param yb2 y coordinate of the bottom-right corner of the area
       * @param g graphics object passed by caller that's used to draw stuff
       */
      
      protected static void drawLab(int minDist, int doorWidth, int xb1 ,int yb1 ,int xb2,int yb2, Graphics g)
      {
         boolean dir = ThreadLocalRandom.current().nextBoolean();
         int lastPos;
         if(dir)
         {
            if(minDist*2>xb2-xb1)
            {
               dir=false;
               if(minDist*2>yb2-yb1)return;
            }
         }
         else
         {
            if(minDist*2>yb2-yb1)
            {
               dir=true;
               if(minDist*2>xb2-xb1)return;
            }
         }
                     
            
         if(dir)
         {
            //lastPos = drawLineWithDoor(g, xb1+xb1/2+Math.abs(xb1-xb2)/2, yb1, xb1+xb1/2+Math.abs(xb1-xb2)/2, yb2, doorWidth, dir);
            
            lastPos = drawLineWithDoor(g, xb1+Math.abs(xb1-xb2)/2, yb1, xb1+Math.abs(xb1-xb2)/2, yb2, doorWidth, dir);
            if(lastPos==-1)return;
            drawLab(minDist, doorWidth, dir, xb1, yb1, xb1+Math.abs(xb1-xb2)/2, lastPos, g);
            drawLab(minDist, doorWidth, dir, xb1+Math.abs(xb1-xb2)/2, yb1, xb2, lastPos, g);
            
            drawLab(minDist, doorWidth, dir, xb1, lastPos+doorWidth, xb1+Math.abs(xb1-xb2)/2, yb2, g);
            drawLab(minDist, doorWidth, dir, xb1+Math.abs(xb1-xb2)/2, lastPos+doorWidth, xb2, yb2, g);
         }
         else
         {
            //lastPos = drawLineWithDoor(g, xb1, yb1+yb1/2+Math.abs(yb1-yb2)/2, xb2, yb1+yb1/2+Math.abs(yb1-yb2)/2, doorWidth, dir);
            lastPos = drawLineWithDoor(g, xb1, yb1+Math.abs(yb1-yb2)/2, xb2, yb1+Math.abs(yb1-yb2)/2, doorWidth, dir);
            if(lastPos==-1)return;
            drawLab(minDist, doorWidth, dir, xb1, yb1, lastPos, yb1+Math.abs(yb1-yb2)/2, g);
            drawLab(minDist, doorWidth, dir, xb1, yb1+Math.abs(yb1-yb2)/2, lastPos, yb2, g);
            
            drawLab(minDist, doorWidth, dir, lastPos+doorWidth, yb1+Math.abs(yb1-yb2)/2, xb2, yb2 , g);
            drawLab(minDist, doorWidth, dir, lastPos+doorWidth, yb1, xb2, yb1+Math.abs(yb1-yb2)/2 , g);
            
         }
         
         
      }
      /**
       * Draws a labyrinth in a specified rectangular area and puts walls on the borders
       * <p>
       * Used to use the lastDir parameter and draw a line when the lineWithDoor decided to draw parallel to its parent like this ¦| . Instead, it makes it look a bit like this _¦¯|, or more like a rectangle with a hole in one wall. Not long after, I changed it so it just drew the lines anyway as it made it look even better because often times you'd end up with "loose ends" if drawLineWidthDoor drew perpendicular to the previous line anyway.
       * lastDir doesn't do anything now.
       * Other than the useless extra argument, the only differance is that it draws some extra lines
       * </p>
       * @param minDist minimal distance between walls
       * @param doorWidth width of the doors in the walls
       * @param lastDir if true - parent drew vertically, if false - parent drew horizontally
       * @param xb1 x coordinate of the top-left corner of the area
       * @param yb1 y coordinate of the top-left corner of the area
       * @param xb2 x coordinate of the bottom-right corner of the area
       * @param yb2 y coordinate of the bottom-right corner of the area
       * @param g graphics object passed by caller that's used to draw stuff
       */
      protected static void drawLab(int minDist, int doorWidth, boolean lastDir, int xb1 ,int yb1 ,int xb2,int yb2, Graphics g)
      {
         boolean dir = ThreadLocalRandom.current().nextBoolean();
         int lastPos;
         if(dir)
         {
            if(minDist*2>xb2-xb1)
            {
               dir=false;
               if(minDist*2>yb2-yb1)return;
            }
         }
         else
         {
            if(minDist*2>yb2-yb1)
            {
               dir=true;
               if(minDist*2>xb2-xb1)return;
            }
         }
                     
            
         if(dir)
         {
            //lastPos = drawLineWithDoor(g, xb1+xb1/2+Math.abs(xb1-xb2)/2, yb1, xb1+xb1/2+Math.abs(xb1-xb2)/2, yb2, doorWidth, dir);
            lastPos = drawLineWithDoor(g, xb1+Math.abs(xb1-xb2)/2, yb1, xb1+Math.abs(xb1-xb2)/2, yb2, doorWidth, dir);
            if(lastPos==-1)return;
            
            //if(dir==lastDir)
            //{
               if(ThreadLocalRandom.current().nextBoolean())
               {
                  g.drawLine(xb1, yb1, xb1+Math.abs(xb1-xb2)/2, yb1);
                  g.drawLine(xb1, yb2, xb1+Math.abs(xb1-xb2)/2, yb2);
               }
               else
               {
                  g.drawLine(xb2, yb1, xb1+Math.abs(xb1-xb2)/2, yb1);
                  g.drawLine(xb2, yb2, xb1+Math.abs(xb1-xb2)/2, yb2);
               }
            //}
            
            drawLab(minDist, doorWidth, dir, xb1, yb1, xb1+Math.abs(xb1-xb2)/2, lastPos, g);
            drawLab(minDist, doorWidth, dir, xb1+Math.abs(xb1-xb2)/2, yb1, xb2, lastPos, g);
            
            drawLab(minDist, doorWidth, dir, xb1, lastPos+doorWidth, xb1+Math.abs(xb1-xb2)/2, yb2, g);
            drawLab(minDist, doorWidth, dir, xb1+Math.abs(xb1-xb2)/2, lastPos+doorWidth, xb2, yb2, g);
         }
         else
         {
            //lastPos = drawLineWithDoor(g, xb1, yb1+yb1/2+Math.abs(yb1-yb2)/2, xb2, yb1+yb1/2+Math.abs(yb1-yb2)/2, doorWidth, dir);
            lastPos = drawLineWithDoor(g, xb1, yb1+Math.abs(yb1-yb2)/2, xb2, yb1+Math.abs(yb1-yb2)/2, doorWidth, dir);
            

            if(lastPos==-1)return;
            
            //if(dir==lastDir)
            //{
               if(ThreadLocalRandom.current().nextBoolean())
               {
                  g.drawLine(xb1, yb1, xb1, yb1+Math.abs(yb1-yb2)/2);
                  g.drawLine(xb2, yb2, xb2, yb1+Math.abs(yb1-yb2)/2);
               }
               else
               {
                  g.drawLine(xb1, yb2, xb1, yb1+Math.abs(yb1-yb2)/2);
                  g.drawLine(xb2, yb1, xb2, yb1+Math.abs(yb1-yb2)/2);
               }
            //}
            drawLab(minDist, doorWidth, dir, xb1, yb1, lastPos, yb1+Math.abs(yb1-yb2)/2, g);
            drawLab(minDist, doorWidth, dir, xb1, yb1+Math.abs(yb1-yb2)/2, lastPos, yb2, g);
            
            drawLab(minDist, doorWidth, dir, lastPos+doorWidth, yb1+Math.abs(yb1-yb2)/2, xb2, yb2 , g);
            drawLab(minDist, doorWidth, dir, lastPos+doorWidth, yb1, xb2, yb1+Math.abs(yb1-yb2)/2 , g);
            
         }
         
         
      }
      
      
      /**
       * Draws a line with a hole in it
       * <p>
       * Padding ensures the hole is somewhere in the middle
       * <p>
       * 
       * @param g graphics object used to draw
       * @param x1 x coordinate of the start of the line
       * @param y1 y coordinate of the end of the line
       * @param x2 x coordinate of the start of the line
       * @param y2 y coordinate of the end of the line
       * @param doorWidth width of the gap in the wall
       * @param vertical true for a vertical line, false for a horizontal one
       * @return returns the starting position of the door, or -1 if there wasn't enough space to make it pretty
       */
      protected static int drawLineWithDoor(Graphics g, int x1, int y1, int x2 , int y2, int doorWidth, boolean vertical)
      {
         int doorPos;
         int padding;
         try{
            
            if(vertical){
               
               padding = (y2-y1)/5;
               if(doorWidth*2>y2-y1)return -1;
               doorPos = ThreadLocalRandom.current().nextInt(y1+padding, y2-doorWidth-padding);
               g.drawLine(x1, y1, x2, doorPos);
               g.drawLine(x1, doorPos+doorWidth, x2, y2);
               /* no padding version
               if(doorWidth*2>y2-y1)return -1;
               doorPos = ThreadLocalRandom.current().nextInt(y1, y2-doorWidth);
               g.drawLine(x1, y1, x2, doorPos);
               g.drawLine(x1, doorPos+doorWidth, x2, y2);*/
            }
            else
            {
               padding = (x2-x1)/5;
               if(doorWidth*2>x2-x1)return -1;
               doorPos = ThreadLocalRandom.current().nextInt(x1+padding, x2-doorWidth-padding);
               g.drawLine(x1, y1, doorPos, y2);
               g.drawLine(doorPos+doorWidth, y1 , x2, y2);
               /* no padding version
               if(doorWidth*2>x2-x1)return -1;
               doorPos = ThreadLocalRandom.current().nextInt(x1, x2-doorWidth);
               g.drawLine(x1, y1, doorPos, y2);
               g.drawLine(doorPos+doorWidth, y1 , x2, y2);*/
            }
         }
         catch(Exception e)
         {
            return -1;
         }
         return doorPos;
         
         
      }
   }

   private static class MyFrame extends JFrame
   {
      private static final long serialVersionUID = 1L;

      MyFrame()
      {
         super("Drawing panel");
         DrawingPanel d = new DrawingPanel();
         d.setPreferredSize(new Dimension(900, 550));
         getContentPane().add(d);
         
         
         setSize(1000, 650);
         setVisible(true);
         setDefaultCloseOperation(EXIT_ON_CLOSE);
         
         
      }
   }

   public static void main(String[] args)
   {
      MyFrame f = new MyFrame();
   }
}
