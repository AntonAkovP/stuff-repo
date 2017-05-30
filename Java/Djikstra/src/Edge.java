
public class Edge
{
   private Node source;
   private Node dest;
   private int weight;
   
   public Edge(Node s, Node d, int w)
   {
      source = s;
      dest = d;
      weight = w;
   }

   public Node getSource()
   {
      return source;
   }

   public Node getDest()
   {
      return dest;
   }

   public int getWeight()
   {
      return weight;
   }
   
   
   
}
