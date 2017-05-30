package task2;



public class SearchingSortedArray
{
   public static int linearSearch(int[] arr, int key, int left, int right)
   {
      if(arr.length==0||left<0||left>right||right>=arr.length) return -1;
      int i=left;  
      while(i<=right)
      {
         if(arr[i]==key)return i;
         i++;
      }
      return -1;
   }
   public static int binarySearch(int[] arr, int key, int left, int right)
   {
      if(arr==null||arr.length==0||left<0||left>right||right>=arr.length) return -1;
      
      if(right-left<10) return linearSearch(arr, key, left, right);

      int p=left+(right-left)/2;
      if(arr[p]==key)return p;
      else{
         if(arr[p]<key)return binarySearch(arr, key, left+1, right);
         else return binarySearch(arr, key, left, p);
      }
   }
   
   
   
   
   
}
