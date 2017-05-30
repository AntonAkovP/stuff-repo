package task3;

public class BarDesk implements IBarDesk
{
   private int full;
   private int empty;
   
   private Object custLock;
   private Object barLock;
   
   public BarDesk()
   {
      full = 50;
      empty = 0;
      barLock = new Object();
      custLock = new Object();
   }
   @Override
   public void takeFullGlass()
   {
      synchronized(custLock)
      {
         while(full == 0)
            try
            {
               custLock.wait();
            }
            catch (InterruptedException e)
            {
               e.printStackTrace();
            }
         full--;
         System.out.println("F:" + full + "E:" + empty+ " // " + Thread.currentThread().getName() + " took a full glass");
      }
   }
   @Override
   public void placeEmptyGlass()
   {
      synchronized(barLock)
      {  
         empty++;
         barLock.notify();
         System.out.println("F:" + full + "E:" + empty + " // " + Thread.currentThread().getName() + " drank a beer and returned a glass");
      }
   }
   @Override
   public void takeEmptyGlass()
   {
      synchronized(barLock)
      {
         while(empty == 0)
            try
            {
               barLock.wait();
            }
            catch (InterruptedException e)
            {
               e.printStackTrace();
            }
         empty--;
         System.out.println("F:" + full + "E:" + empty+ " // " + Thread.currentThread().getName() + " took an empty glass");
      }
   }
   @Override
   public void placeFullGlass()
   {
      synchronized(custLock)
      {
         full++;
         custLock.notify();
         System.out.println("F:" + full + "E:" + empty+ " // " + Thread.currentThread().getName() + " filled a glass");
      }
   }
}
