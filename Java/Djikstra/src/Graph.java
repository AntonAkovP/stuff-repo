import java.util.List;

public class Graph
{
   private List<Node> nodes;
   private List<Edge> edges;

   public Graph(List<Node> nS, List<Edge> eS)
   {
      nodes = nS;
      edges = eS;
   }

   public List<Node> getNodes()
   {
      return nodes;
   }

   public List<Edge> getEdges()
   {
      return edges;
   }
   
}
