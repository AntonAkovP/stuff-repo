
public class TestEquation implements Equation
{
   public static final double PEAK = 1;
   @Override
   public double f(double x)
   {
      return Math.abs(x);
   }

   @Override
   public double globalMin(double from, double to)
   {
      return 10;
   }

   @Override
   public double globalMax(double from, double to)
   {
      return 10;
   }

}
