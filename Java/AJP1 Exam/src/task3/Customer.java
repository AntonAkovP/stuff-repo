package task3;

import java.util.concurrent.ThreadLocalRandom;

public class Customer extends Thread
{
   private int drank;
   private BarDesk bar;
   
   public Customer(BarDesk drinksAt, String name)
   {
      super(name);
      drank = 0;
      bar = drinksAt;
   }
   
   @Override
   public void run()
   {
      while(drank<10)
      {
         bar.takeFullGlass();
         try
         {
            Thread.sleep(ThreadLocalRandom.current().nextInt(1, 10)*500);
         }
         catch (InterruptedException e)
         {
            // TODO Auto-generated catch block
            e.printStackTrace();
         }
         bar.placeEmptyGlass();
         drank++;
      }
      System.out.println(this.getName() + " is done for the night");
   }
}
