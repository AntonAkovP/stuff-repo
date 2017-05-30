import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;

public class Window2 extends JFrame
{
   private static final long serialVersionUID = 1L;
   
   protected drawingPane2 pane;
   protected JPanel buttons;
   protected JButton LinearB;
   protected JButton QuadraticB;
   protected JButton RedrawB;
   
   protected JTextField rangeXFrom;
   protected JTextField rangeXTo;
   protected JTextField rangeYFrom;
   protected JTextField rangeYTo;
   
   
   
   protected LinearEquation linear;
   protected QuadraticEquation quadratic;
   
   public Window2()
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
      
     
      
      ButtonListener BL = new ButtonListener();
      
      LinearB = new JButton("Linear");
      LinearB.addActionListener(BL);
      
      QuadraticB = new JButton("Quadratic");
      QuadraticB.addActionListener(BL);
      
      RedrawB = new JButton("Redraw");
      RedrawB.addActionListener(BL);
      
      rangeXFrom = new JTextField(4);
      rangeXFrom.setAutoscrolls(true);
      rangeXTo = new JTextField(4);
      rangeXTo.setAutoscrolls(true);
      rangeYFrom = new JTextField(4);
      rangeYFrom.setAutoscrolls(true);
      rangeYTo = new JTextField(4);
      rangeYTo.setAutoscrolls(true);
      
      
      buttons = new JPanel();
      buttons.setLayout(new FlowLayout());
      buttons.add(LinearB);
      buttons.add(QuadraticB);
      buttons.add(RedrawB);
      buttons.add(new JLabel("X:"));
      buttons.add(rangeXFrom);
      buttons.add(new JLabel(":"));
      buttons.add(rangeXTo);
      buttons.add(new JLabel("Y:"));
      buttons.add(rangeYFrom);
      buttons.add(new JLabel(":"));
      buttons.add(rangeYTo);
      
      
      pane= new drawingPane2(new TestEquation(), rangeXFrom, rangeXTo, rangeYFrom, rangeYTo);
      pane.setPreferredSize(new Dimension(300, 400));
      
      setLayout(new BorderLayout());
      add(pane, BorderLayout.CENTER);
      add(buttons, BorderLayout.SOUTH);
      
      setSize(600, 650);
      setMinimumSize(new Dimension(530, 450));
      setVisible(true);
      setDefaultCloseOperation(EXIT_ON_CLOSE);
      
   }
   private class ButtonListener implements ActionListener
   {

      @Override
      public void actionPerformed(ActionEvent arg0)
      {
         if(arg0.getSource()==LinearB)pane.setFunction(linear, rangeXFrom, rangeXTo, rangeYFrom, rangeYTo);
         if(arg0.getSource()==QuadraticB)pane.setFunction(quadratic, rangeXFrom, rangeXTo, rangeYFrom, rangeYTo);
         if(arg0.getSource()==RedrawB)pane.setFunction(pane.e, rangeXFrom, rangeXTo, rangeYFrom, rangeYTo);
      }
      
   }

   private class drawingPane2 extends JPanel
   {
      private static final long serialVersionUID = 1L;
      
      protected double maxY;
      protected Polygon p;
      protected Equation e;
      protected final int xOffset = 20;
      protected final int yOffset = 25;
      protected double Xstep;
      protected double Ystep;
      
      protected double rangeXFrom;
      protected double rangeXTo;
      protected double rangeYFrom;
      protected double rangeYTo;
      
      
      public drawingPane2(Equation e, JTextField rXF, JTextField rXT, JTextField rYF, JTextField rYT)
      {
         super();
         p = new Polygon();
         this.e = e;
         
         try{rangeXFrom = Double.parseDouble(rXF.getText());}
         catch(NumberFormatException exc){rangeXFrom = -10;rXF.setText("-10");}
         try{rangeXTo = Double.parseDouble(rXT.getText());}
         catch(NumberFormatException exc){rangeXTo = 10;rXT.setText("10");}
         
         
         double eMin = Math.abs(e.globalMin(rangeXFrom, rangeXTo));
         double eMax = Math.abs(e.globalMax(rangeXFrom, rangeXTo));
         maxY = eMax>eMin?eMax:eMin;
         
         rangeYFrom = -1*maxY;
         rangeYTo = maxY;
         
         try{
            rangeYFrom = Double.parseDouble(rYF.getText());
         }catch(NumberFormatException exc){rYF.setText("-"+maxY);}
         
         try{
            rangeYTo = Double.parseDouble(rYT.getText());
         }catch(NumberFormatException exc){rYT.setText(""+maxY);}
         
      }
      /**
       * I've worked a lot on the input recognition in this one. That's like all it does,
       * so that process isn't done every time the panel is repainted, but just the painting part.
       * <p>Possible inputs:
       *    <p>
       *       nothing -> X defaults to -10:10 , Y defaults to the highest value the function reaches in the X scope<br>
       *       (for each X or Y)+- number in the left(from) field, nothing in right(to) -> -# in left field and # in right<br>
       *       (for each X or Y)same number in both fields -> -# in left field and # in right
       *    </p>
       * </p>
       * By the way, if I put the time into it, I'm sure a drag functionality could easily be added to this implementation, as paintComponent has heavy focus on responsiveness and working with whatever valid values you throw at it
       * @param e
       * @param rXF
       * @param rXT
       * @param rYF
       * @param rYT
       */
      public void setFunction(Equation e, JTextField rXF, JTextField rXT, JTextField rYF, JTextField rYT)
      {
         this.e = e;
         
         if(rXF.getText().equals(rXT.getText())&&rXF.getText().length()>0)
         {
            try{
               rangeXFrom = -1*Math.abs(Double.parseDouble(rXF.getText()));
               rangeXTo = Math.abs(Double.parseDouble(rXF.getText()));
               
               rXF.setText(String.valueOf(rangeXFrom));
               rXT.setText(String.valueOf(rangeXTo));
            }catch(NumberFormatException exc){}
         }
         else if(rXF.getText().length()>0&&rXT.getText().length()==0)
         {
            try{
               rangeXFrom = -1*Math.abs(Double.parseDouble(rXF.getText()));
               rangeXTo = Math.abs(Double.parseDouble(rXF.getText()));
               
               rXF.setText(String.valueOf(rangeXFrom));
               rXT.setText(String.valueOf(rangeXTo));
            }catch(NumberFormatException exc){}
         }
         else {
            try{rangeXFrom = Double.parseDouble(rXF.getText());}
            catch(NumberFormatException exc){rangeXFrom = -10;rXF.setText("-10");}
            try{rangeXTo = Double.parseDouble(rXT.getText());}
            catch(NumberFormatException exc){rangeXTo = 10;rXT.setText("10");}
         }
         
         
         if(rangeXFrom>rangeXTo)return;
         double eMin = Math.abs(e.globalMin(rangeXFrom, rangeXTo));
         double eMax = Math.abs(e.globalMax(rangeXFrom, rangeXTo));
         maxY = eMax>eMin?eMax:eMin;
         
         rangeYFrom = -1*maxY;
         rangeYTo = maxY;
         
         
         if(rYF.getText().equals(rYT.getText())&&rYF.getText().length()>0)
         {
            try{
               rangeYFrom = -1*Math.abs(Double.parseDouble(rYF.getText()));
               rangeYTo = Math.abs(Double.parseDouble(rYF.getText()));
               
               rYF.setText(String.valueOf(rangeYFrom));
               rYT.setText(String.valueOf(rangeYTo));
            }catch(NumberFormatException exc){}
            
         }
         else if(rYF.getText().length()>0&&rYT.getText().length()==0)
         {
            try{
               rangeYFrom = -1*Math.abs(Double.parseDouble(rYF.getText()));
               rangeYTo = Math.abs(Double.parseDouble(rYF.getText()));
               
               rYF.setText(String.valueOf(rangeYFrom));
               rYT.setText(String.valueOf(rangeYTo));
            }catch(NumberFormatException exc){}
         }
         else{
            try{
               rangeYFrom = Double.parseDouble(rYF.getText());
            }catch(NumberFormatException exc){rYF.setText("-"+maxY);}
            
            try{
               rangeYTo = Double.parseDouble(rYT.getText());
            }catch(NumberFormatException exc){rYT.setText(""+maxY);}
         }
         if(rangeYFrom>rangeYTo)return;
         repaint();
      }
      @Override
      protected void paintComponent(Graphics g)
      {
         super.paintComponent(g);
         
         p.reset();
         
         double drawW = getWidth()-2*xOffset;
         double drawH = getHeight()-2*yOffset;
         
         double totalX = Math.abs(rangeXFrom-rangeXTo);
         double totalY = Math.abs(rangeYFrom-rangeYTo);
         
         Xstep = totalX/drawW;
         Ystep = totalY/drawH;
         
         g.drawRect(xOffset, yOffset, (int)drawW, (int)drawH);
         
         double Xratio = 0.5;
         double Yratio = 0.5;
         
         if(rangeXFrom<0&&rangeXTo>0)
         {
            Xratio = Math.abs(rangeXFrom)/totalX;
            g.drawLine(xOffset+(int)(drawW*Xratio), yOffset, xOffset+(int)(drawW*Xratio), yOffset+(int)drawH);
         }
         if(rangeYFrom<0&&rangeYTo>0)
         {
            Yratio = Math.abs(rangeYTo)/totalY;
            g.drawLine(xOffset, yOffset+(int)(drawH*Yratio), xOffset+(int)drawW, yOffset+(int)(drawH*Yratio));
            
         }
         /*
         p.addPoint((int)(xOffset+drawW/4), (int)(yOffset+drawH/4) );
         p.addPoint((int)(xOffset+drawW*3/4), (int)(yOffset+drawH/4));
         p.addPoint((int)(xOffset+drawW*3/4), (int)(yOffset+drawH*3/4));
         p.addPoint((int)(xOffset+drawW/4), (int)(yOffset+drawH/4*3));
         p.addPoint((int)(xOffset+drawW), (int)(yOffset));
         p.addPoint((int)(xOffset+drawW*3/4), (int)(yOffset+drawH/4));
         g.setColor(Color.RED);
         g.drawPolyline(p.xpoints, p.ypoints, p.npoints);
         
         g.setColor(Color.black);
         */

         int i = 0;
         //Xstep = 20/drawW;
         //Ystep = maxY*2/drawH;
         
         double asd;
         double asd2;
         double debug1;
         double debug2;
         if(rangeXFrom<0&&rangeXTo>0&&rangeYFrom<0&&rangeYTo>0)
         {
            
            while(i<drawW)
            {
               asd = (i-drawW*Xratio)*Xstep;
               asd2 = e.f(asd);
               debug1 = asd2/Ystep;
               debug2 = drawH*Yratio-debug1;
               int Y = (int) (yOffset+debug2);
               if(Y>=yOffset&&Y<=yOffset+drawH)p.addPoint(xOffset+i, Y);
               if(Y<yOffset)p.addPoint(xOffset+i, yOffset);
               if(Y>yOffset+drawH)p.addPoint(xOffset+i, (int)(yOffset+drawH));
               i++;
            }
         }
         else
         {
            while(i<drawW)
            {
               asd = rangeXFrom + i*Xstep;
               asd2 = e.f(asd);
               
               debug1 = (asd2-rangeYFrom)/Ystep;
               debug2 = drawH-debug1;
               int Y = (int) (yOffset+debug2);
               if(Y>=yOffset&&Y<=yOffset+drawH)p.addPoint(xOffset+i, Y);
               if(Y<yOffset)p.addPoint(xOffset+i, yOffset);
               if(Y>yOffset+drawH)p.addPoint(xOffset+i, (int)(yOffset+drawH));
               i++;

            }
         }
         g.drawPolyline(p.xpoints, p.ypoints, p.npoints);
      }
   }

   public static void main(String[] args)
   {
      new Window2();
   }
   
   
}
