
public class LinearEquation implements Equation
{

   @Override
   public double f(double x)
   {
      return 2*x+1;
   }
   public double globalMin(double from, double to)
   {
      if(from>to)return Double.NaN;
      if(from==to)return f(from);
      return f(from);
      
   }
   public double globalMax(double from, double to)
   {
      if(from>to)return Double.NaN;
      if(from==to)return f(from);
      return f(to);
      
   }

}
