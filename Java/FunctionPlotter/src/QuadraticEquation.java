
public class QuadraticEquation implements Equation
{
   public static final double PEAK = 1;
   @Override
   public double f(double x)
   {
      return x*x*x-5;//(-2)*(x*x) + 4*x - 2;
   }

   @Override
   public double globalMin(double from, double to)
   {
      if(from>to)return Double.NaN;
      if(from==to)return f(from);
      if(from<PEAK&&to>PEAK)
      {
         if(Math.abs(from-PEAK)>=Math.abs(to-PEAK))
         {
            return f(from);
         }
         else return f(to);
      }
         //return f(Math.max(Math.abs(from-PEAK), Math.abs(to-PEAK)));
      
      if(to<PEAK)
         return f(from);
         
      return f(to);
   }

   @Override
   public double globalMax(double from, double to)
   {
      if(from>to)return Double.NaN;
      if(from==to)return f(from);
      
      if(from<PEAK&&to>PEAK)
         return f(PEAK);
      
      if(to<PEAK)
         return f(to);
      
      return f(from);
   }

}
