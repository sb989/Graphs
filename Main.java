import java.util.*;
//import java.util.Random;
public class Main
{

  Graph createRandomUnweightedGraphIter(int n)
  {
    Graph g = new Graph();
    Random r = new Random();
    for(int i =0;i<n;i++)
    {
      g.addNode(i);
    }
    for(int i =0;i<n;i++)
    {
      Node nd = g.getNode(i);
      int range = r.nextInt(n); // random number of edges
      for(int j = 0;j<range;j++)
      {
        g.addUndirectedEdge(nd,g.getNode(r.nextInt(n)));
      }
    }
    return g;
  }

  Graph createLinkedList(int n)
  {
    Graph g = new Graph();
    for(int i =0;i<n;i++)
    {
      g.addNode(i);
    }
    for(int i =0;i<n-1;i++)
    {
      g.addDirectedEdge(g.getNode(i),g.getNode(i+1));
    }
    return g;
  }

  ArrayList<Node> BFTRecLinkedList(final Graph graph)
  {
    ArrayList<Node> r=  GraphSearch.BFTRec(graph);
    System.out.println("done traversing graph recursiveley");
    return r;
  }

  ArrayList<Node> BFTIterLinkedList(final Graph graph)
  {
    ArrayList<Node> r=  GraphSearch.BFTIter(graph);
    System.out.println("done traversing graph iteratively");
    return r;
  }
  public static void main(String []args)
  {
    Main m = new Main();
    Graph g = m.createLinkedList(1000000);
    //ArrayList<Node> r = m.BFTRecLinkedList(g);
    ArrayList<Node> r = m.BFTIterLinkedList(g);
    r.forEach(n->System.out.println(n.nodeVal));
  }
}
