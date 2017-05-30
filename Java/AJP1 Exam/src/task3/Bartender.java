package task3;

public class Bartender extends Thread
{
   private BarDesk serves;
   
   public Bartender(BarDesk barmanFor, String name)
   {
      super(name);
      serves = barmanFor;
   }
   @Override
   public void run()
   {
      while(true)
      {
         serves.takeEmptyGlass();
         
         try
         {
            Thread.sleep(800);
         }
         catch (InterruptedException e)
         {
            e.printStackTrace();
         }
         serves.placeFullGlass();
      }
   }
}
