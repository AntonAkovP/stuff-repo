import static org.junit.Assert.*;

import org.junit.Test;
import java.util.Scanner;


public class GridTest
{

   @Test
   public void test()
   {
      Grid board = new Grid();
      
    //constant input
      
      int [][] constGrid = {
      {0,6,0, 0,0,0, 0,4,0},
      {0,0,0, 7,0,2, 3,0,9},
      {5,0,0, 3,0,0, 1,0,0},
      
      {0,0,0, 5,0,0, 0,0,0},
      {9,0,4, 0,0,0, 6,0,7},
      {0,0,0, 0,0,7, 0,0,0},
      
      {0,0,2, 0,0,3, 0,0,4},
      {8,0,6, 2,0,9, 0,0,0},
      {0,1,0, 0,0,0, 0,9,0}};
      
      //uncomment this line to enable const
      //asd
      //board.setGrid(constGrid);
      
      //keyboard input if grid is empty(constant disabled)
      if(board.isEmpty()){
         Scanner keyB = new Scanner(System.in);
         int x=0, y=0, val = 1;
         System.out.println("x,y ∈ [0, 8] | v ∈ [0, 9] | enter v < 0 to stop input | enter sth other than a number to check input");
         while(val>=0)
         {
            try{
               System.out.print("x: ");
               x=Integer.valueOf(keyB.nextLine());
               
               System.out.print("y: ");
               y=Integer.valueOf(keyB.nextLine());
               
               System.out.print("v: ");
               val=Integer.valueOf(keyB.nextLine());
               
               if(val<0)break;
            }catch(Exception e)
            {
               board.printGrid();continue;}
            
            if(board.setVA(x, y, val))System.out.println("Value set");
            else System.out.println("Value NOT set");
         }
      }

      if(board.solve())
         board.printGrid();
      else System.out.println("Unsolvable");
   }

}
