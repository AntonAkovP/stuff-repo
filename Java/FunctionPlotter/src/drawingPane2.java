import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;

import javax.swing.JPanel;

public class drawingPane2 extends JPanel
{
   private static final long serialVersionUID = 1L;
   
   protected double maxY;
   protected Polygon p;
   protected Equation e;
   protected final int xOffset = 20;
   protected final int yOffset = 25;
   protected double Xstep;
   protected double Ystep;
   
   public drawingPane2(Equation e)
   {
      super();
      p = new Polygon();
      this.e = e;
      maxY = (e.globalMax(-10,10)>e.globalMin(-10, 10))?e.globalMax(-10,10):e.globalMin(-10,10);
   }
   public void setFunction(Equation e)
   {
      this.e = e;
      //for debugging and clarity
      double eMin = Math.abs(e.globalMin(-10, 10));
      double eMax = Math.abs(e.globalMax(-10, 10));
      maxY = eMax>eMin?eMax:eMin;
      repaint();
   }
   
   @Override
   protected void paintComponent(Graphics g)
   {
      super.paintComponent(g);
      p.reset();
      Xstep = (double)20/(getWidth()-2*xOffset);
      Ystep = (maxY/(getHeight()-2*yOffset))*2;
      
      g.drawRect(xOffset, yOffset, getWidth()-xOffset*2, getHeight()-yOffset*2);
      g.drawLine(getWidth()/2, yOffset, getWidth()/2, getHeight()-yOffset);
      g.drawLine(xOffset, getHeight()/2 , getWidth()-xOffset, getHeight()/2);
      g.drawString("-10", 1 , getHeight()/2+5);
      g.drawString("10", getWidth()-xOffset+2 , getHeight()/2+5);
      g.drawString("-"+String.valueOf(maxY), getWidth()/2-6, getHeight()-yOffset+15);
      g.drawString(String.valueOf(maxY), getWidth()/2-6, yOffset-3);
      
      //g.drawLine(xOffset+(getWidth()-xOffset*2)/2, getHeight()/2-4 , xOffset+(getWidth()-xOffset*2)/2, getHeight()/2+4);
      /*
      int i = xOffset;
      double j;
      double asd;
      double asd2;
      double debug1;
      int debug2;
      while(i<getWidth()-xOffset)
      {
         asd = i-(getWidth()-2*xOffset)/2;
         j=asd*Xstep;
         debug1 = e.f(j);
         asd2 = debug1/Ystep;
         debug2 = (int)(getHeight()/2-asd2);//+(getHeight()-2*yOffset)/2);
         //debug2 = (int)(debug1/Ystep);
         p.addPoint(i, yOffset/2+debug2 );
         i++;
      }
      */
      double drawW = getWidth()-2*xOffset;
      double drawH = getHeight()-2*yOffset;
      p.addPoint((int)(xOffset+drawW/4), (int)(yOffset+drawH/4) );
      p.addPoint((int)(xOffset+drawW*3/4), (int)(yOffset+drawH/4));
      p.addPoint((int)(xOffset+drawW*3/4), (int)(yOffset+drawH*3/4));
      p.addPoint((int)(xOffset+drawW/4), (int)(yOffset+drawH/4*3));
      p.addPoint((int)(xOffset+drawW), (int)(yOffset));
      p.addPoint((int)(xOffset+drawW*3/4), (int)(yOffset+drawH/4));
      g.setColor(Color.RED);
      g.drawPolyline(p.xpoints, p.ypoints, p.npoints);
      
      g.setColor(Color.black);
      
      
      Polygon testP = new Polygon();
      int i = 0;
      Xstep = 20/drawW;
      Ystep = maxY/drawH;
      
      double asd;
      double asd2;
      double debug1;
      double debug2;
      while(i<drawW)
      {
         asd = (i-drawW/2)*Xstep;
         asd2 = e.f(asd);
         debug1 = asd2/Ystep/2;
         debug2 = drawH/2-debug1;
         testP.addPoint(xOffset+i, (int) (yOffset+debug2));
         i++;
      }
      g.drawPolyline(testP.xpoints, testP.ypoints, testP.npoints);
   }
}
