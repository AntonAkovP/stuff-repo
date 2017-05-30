package task3;

public class Main
{
   public static void main(String[] args)
   {
      BarDesk bar = new BarDesk();
      
      Bartender b1 = new Bartender(bar, "Smith");
      Bartender b2 = new Bartender(bar, "John");
      Bartender b3 = new Bartender(bar, "Richard");
      Bartender b4 = new Bartender(bar, "Ron");
      Bartender b5 = new Bartender(bar, "Donald");
      
      Customer c1 = new Customer(bar, "Jane");
      Customer c2 = new Customer(bar, "Kate");
      Customer c3 = new Customer(bar, "Chad");
      Customer c4 = new Customer(bar, "Anderson");
      Customer c5 = new Customer(bar, "Anderson's dog");
      Customer c6 = new Customer(bar, "Willson");
      Customer c7 = new Customer(bar, "Bob");
      Customer c8 = new Customer(bar, "Anrei");
      Customer c9 = new Customer(bar, "a drunk driver");
      Customer c10 = new Customer(bar, "a cop");
      Customer c11 = new Customer(bar, "Jenny");
      Customer c12 = new Customer(bar, "Milo");
      Customer c13 = new Customer(bar, "Eric");
      Customer c14 = new Customer(bar, "A corrupt politician");
      Customer c15 = new Customer(bar, "Robert");
      Customer c16 = new Customer(bar, "Stacy");
      Customer c17 = new Customer(bar, "Jason Statham");
      Customer c18 = new Customer(bar, "Annie");
      Customer c19 = new Customer(bar, "the buddy's friend");
      Customer c20 = new Customer(bar, "the friend's buddy");
      
      c1.start();
      c2.start();
      c3.start();
      c4.start();
      c5.start();
      c6.start();
      c7.start();
      c8.start();
      c9.start();
      c10.start();
      c11.start();
      c12.start();
      c13.start();
      c14.start();
      c15.start();
      c16.start();
      c17.start();
      c18.start();
      c19.start();
      c20.start();

      b1.setDaemon(true);
      b2.setDaemon(true);
      b3.setDaemon(true);
      b4.setDaemon(true);
      b5.setDaemon(true);
      b1.start();
      b2.start();
      b3.start();
      b4.start();
      b5.start();
      
      //Exits when all customers are done drinking, doesn't wait for bartenders to finish cleanining
   }
}
