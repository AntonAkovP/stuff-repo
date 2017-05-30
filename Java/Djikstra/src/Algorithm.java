
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.HashMap;
import java.util.HashSet;//could've used SortedSet but fuck it

public class Algorithm
{
   private Graph graph;
   
   private Set<Node> passedNodes;
   private Set<Node> unpassedNodes;
   
   private Map<Node, Node> nodeMap;
   private Map<Node, Integer> distanceMap;
   
   public Algorithm(Graph g)
   {
      graph =g;
   }
   
   public void run(Node start)
   {
      passedNodes = new HashSet<Node>();
      unpassedNodes = new HashSet<Node>();
      nodeMap = new HashMap<Node, Node>();
      distanceMap = new HashMap<Node, Integer>();
      
      unpassedNodes.add(start);
      distanceMap.put(start, 0);
      
      while (!unpassedNodes.isEmpty()) {
              Node temp = getMinimum(unpassedNodes);
              
              unpassedNodes.remove(temp);
              passedNodes.add(temp);
              
              findMinimalDistances(temp);
      }
   }
   
   //this one could've been optimised with a smarter model
   private int getDistance(Node node, Node target) 
   {
      for (Edge edge : graph.getEdges())
      {
        if (edge.getSource().equals(node) && edge.getDest().equals(target))
        {
          return edge.getWeight();
        }
      }
      return -1;//should never end up here, maybe should be an exception
      
   }
   
   //gets neighbors of nodes that haven't been passed yet
   private List<Node> getNeighbors(Node node)
   {
      List<Node> neighbors = new ArrayList<Node>();
      for (Edge edge : graph.getEdges()) {
        if (edge.getSource().equals(node) && !passedNodes.contains(edge.getDest()))
        {
          neighbors.add(edge.getDest());
        }
      }
      return neighbors;
   }
   //gets all minimal distances for the buddies of a node, starting from start
   private void findMinimalDistances(Node node)
   {
      List<Node> buddies = getNeighbors(node);
      
      for (Node temp : buddies)
      {
        if (getShortestDistance(temp) > getShortestDistance(node) + getDistance(node, temp))
        {
           distanceMap.put(temp, getShortestDistance(node) + getDistance(node, temp));
           nodeMap.put(temp, node);
           unpassedNodes.add(temp);
        }
      }  

   }
   //constructs the final path
   public List<Node> getPath(Node to) {
      List<Node> path = new ArrayList<Node>();
      Node step = to;
      // check if a path exists
      if (nodeMap.get(step) == null) {
              return null;
      }
      path.add(step);
      while (nodeMap.get(step) != null) {
              step = nodeMap.get(step);
              path.add(step);
      }
      
      Collections.reverse(path);
      return path;
}
   //closest node
   private Node getMinimum(Set<Node> nodes) {
      Node minimum = null;
      for (Node temp : nodes) {
              if (minimum == null) {
                      minimum = temp;
              } else {
                      if (getShortestDistance(temp) < getShortestDistance(minimum)) {
                              minimum = temp;
                      }
              }
      }
      return minimum;
}
   private int getShortestDistance(Node dest)
   {
      Integer d = distanceMap.get(dest);
      if (d == null)
         return Integer.MAX_VALUE;//infinity
      else return d;
   }
 
}
