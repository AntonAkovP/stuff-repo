
public class Grid
{
   private int[][] grid = new int[9][9];
   private int[][] backup = new int[9][9];
   public Grid()
   {
      int i=0,j=0;
      while(i<9)
      {
         while(j<9)
         {
            grid[i][j]=0;
            j++;
         }
         j=0;
         i++;
      }
   }
   public void setGrid(int [][] newGrid)
   {
      grid = newGrid;
   }
   
   public boolean setVA(int x, int y, int v)
   {
      if(v<0||v>9)return false;
      if(x<0||x>8||y<0||y>8)return false;
      grid[x][y]=v;
      return true;
   }
   public boolean setV(int x, int y, int v)
   {
      if(grid[x][y]!=0)return false;
      if(v<1||v>9)return false;
      if(x<0||x>8||y<0||y>8)return false;
      
      //ninth check
      
      int rowL=0, rowH=0, colL=0, colH=0;
      
      if(x<=2){rowL = 0;rowH = 2;}
      if(x>2&&x<=5){rowL = 3;rowH = 5;}
      if(x>5&&x<=8){rowL = 6;rowH = 8;}
      
      if(y<=2){colL = 0;colH = 2;}
      if(y>2&&y<=5){colL = 3;colH = 5;}
      if(y>5&&y<=8){colL = 6;colH = 8;}
      
      int i=rowL,j=colL;
      while(i<=rowH)
      {
         while(j<=colH)
         {
            if(grid[i][j]==v)return false;
            j++;
         }
         j=colL;
         i++;
      }
      
      
      //row/column check
      i=0;
      j=0;
      
      while(i<9)
      {
         if(grid[x][i]==v)return false;
         i++;
      }
      while(j<9)
      {
         if(grid[j][y]==v)return false;
         j++;
      }
      
      //passed
      grid[x][y]=v;
      
      //printGrid();
      return true;
   }
   public boolean isEmpty()
   {
      for(int[] subArr : grid)
         for(int val : subArr)
            if(val!=0)return false;
      return true;
   }
   public boolean isFull()
   {
      for(int[] subArr : grid)
         for(int val : subArr)
            if(val==0)return false;
      return true;
   }
   public boolean solve()
   {
      return attemptS(0,0);
   }
   
   private boolean attemptS(int x, int y)
   {
      if(y==9){x++;y=0;}
      if(x==9)return true;
      if(grid[x][y]!=0)return attemptS(x, y+1);
      
      int v=1;
      
      while(v<=9)
      {
         if(setV(x,y,v))
            if(attemptS(x, y+1))return true;
            else setVA(x,y,0);
         v++;
      }
      setVA(x, y , 0);
      return false;
   }
   
   
   
   public void printGrid()
   {
      int i=0,j=0;
      
      while(i<9)
      {
         while(j<9)
         {
            System.out.print(""+grid[i][j]+" ");
            if((j+1)%3==0)System.out.print("| ");
            j++;
         }
         System.out.println();
         j=0;
         if((i+1)%3==0)
         {
            while(j<9)
            {
               System.out.print("--");
               if((j+1)%3==0)System.out.print("+-");
               j++;
            }
            j=0;
            System.out.println();
         }
         i++;
      }
      System.out.println();
   }
   
   
}
