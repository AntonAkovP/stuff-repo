import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class DjikstraTest
{

   @Test
   public void test()
   {
      
      
      List<Node> nodes = new ArrayList<Node>();
      List<Edge> lines = new ArrayList<Edge>();
      
      Node S = new Node("S");
      Node P = new Node("P");
      Node U = new Node("U");
      Node X = new Node("X");
      Node Q = new Node("Q");
      Node Y = new Node("Y");
      Node V = new Node("V");
      Node R = new Node("R");
      Node W = new Node("W");
      Node T = new Node("T");
      
      nodes.add(S);
      nodes.add(P);
      nodes.add(U);
      nodes.add(X);
      nodes.add(Q);
      nodes.add(Y);
      nodes.add(V);
      nodes.add(R);
      nodes.add(W);
      nodes.add(T);
      
      lines.add(new Edge(S, P, 2));
      lines.add(new Edge(S, U, 3));
      lines.add(new Edge(P, X, 4));
      lines.add(new Edge(U, X, 1));
      lines.add(new Edge(P, Q, 5));
      lines.add(new Edge(U, V, 3));
      lines.add(new Edge(X, Q, 7));
      lines.add(new Edge(X, Y, 6));
      lines.add(new Edge(X, V, 8));
      lines.add(new Edge(Q, R, 2));
      lines.add(new Edge(Y, R, 1));
      lines.add(new Edge(Y, W, 3));
      lines.add(new Edge(V, W, 4));
      lines.add(new Edge(R, T, 6));
      lines.add(new Edge(W, T, 5));
      
      Graph graph = new Graph(nodes, lines);
      
      Algorithm instance = new Algorithm(graph);
      
      //calculates with a certain node as a starting point
      instance.run(S);
      
      //gets path to a node
      System.out.println(instance.getPath(X));
      
      
   }

}
