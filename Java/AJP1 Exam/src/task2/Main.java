package task2;

import java.util.Arrays;

public class Main
{
   public static void main(String[]args)
   {
      int[] testArr1 = {0};
      int[] testArr9 = {1,2,3,4,5,6,7,8,9};
      int[] testArr25 = new int[25];
      int i=0;
      while(i<testArr25.length)
      {
         testArr25[i] = i;
         i++;
      }
      System.out.println("testArr1: " + Arrays.toString(testArr1) +" len: "+ testArr1.length);
      System.out.print("(0)[0, 0] exp: 0 result: ");
      System.out.println(SearchingSortedArray.binarySearch(testArr1, 0, 0, 0));
      System.out.print("(0)[0, 1] exp: -1 result: ");
      System.out.println(SearchingSortedArray.binarySearch(testArr1, 0, 0, 1));
      System.out.print("(5)[0, 0] exp: -1 result: ");
      System.out.println(SearchingSortedArray.binarySearch(testArr1, 5, 0, 0));
      
      System.out.println("\ntestArr9: " + Arrays.toString(testArr9) +" len: "+ testArr9.length);
      System.out.print("(0)[0, 8] exp: -1 result: ");
      System.out.println(SearchingSortedArray.binarySearch(testArr9, 0, 0, testArr9.length-1));
      System.out.print("(1)[0, 8] exp: 0 result: ");
      System.out.println(SearchingSortedArray.binarySearch(testArr9, 1, 0, testArr9.length-1));
      System.out.print("(9)[0, 8] exp: 8 result: ");
      System.out.println(SearchingSortedArray.binarySearch(testArr9, 9, 0, testArr9.length-1));
      System.out.print("(7)[0, 5] exp: -1 result: ");
      System.out.println(SearchingSortedArray.binarySearch(testArr9, 7, 0, 5));
      
      System.out.println("\ntestArr25:" + Arrays.toString(testArr25) +"len: "+ testArr25.length);
      System.out.print("(0)[0, 24] exp: 0 result: ");
      System.out.println(SearchingSortedArray.binarySearch(testArr25, 0, 0, testArr25.length-1));
      System.out.print("(24)[0, 24] exp: 24 result: ");
      System.out.println(SearchingSortedArray.binarySearch(testArr25, 24, 0, testArr25.length-1));
      System.out.print("(25)[0, 24] exp: -1 result: ");
      System.out.println(SearchingSortedArray.binarySearch(testArr25, 25, 0, testArr25.length-1));
      System.out.print("(20)[0, 24] exp: 20 result: ");
      System.out.println(SearchingSortedArray.binarySearch(testArr25, 20, 0, testArr25.length-1));
      System.out.print("(20)[0, 19] exp: -1 result: ");
      System.out.println(SearchingSortedArray.binarySearch(testArr25, 20, 10, 19));
      System.out.print("(20)[0, 20] exp: 20 result: ");
      System.out.println(SearchingSortedArray.binarySearch(testArr25, 20, 10, 20));
   }
}
