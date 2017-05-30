package task1;

public class MathStuff
{
   public static double secant(Function fct, double x0, double x1, double epsilon)
   {
      double temp;
      while(Math.abs(x1-x0)>=epsilon){
         temp = x1;
         x1 = ((x0*fct.f(x1))-x1*fct.f(x0))/(fct.f(x1)-fct.f(x0));
         x0 = temp;
      }; 
      return x1;
   }
   
   public static double secRec(Function fct, double x0, double x1, double epsilon)
   {
      if(Math.abs(x1-x0)<epsilon)return x1;
      return secRec(fct, x1, (x0*fct.f(x1)-x1*fct.f(x0))/(fct.f(x1)-fct.f(x0)), epsilon);
   }
   
   public static void main(String[] args)
   {
      Function testF = new TheFunction();
      //i have no idea what arguments would be correct but it returns identical results
      System.out.print("iterative: ");
      System.out.println(MathStuff.secant(testF, -1, 2.5, Math.E));
      System.out.print("recursive: ");
      System.out.println(MathStuff.secRec(testF, -1, 2.5, Math.E));
   }
   
}
