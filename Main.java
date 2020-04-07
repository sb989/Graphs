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
      g.addUndirectedEdge(g.getNode(i),g.getNode(i+1));
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

  DirectedGraph createRandomDAGIter(final int n)
  {
    DirectedGraph g = new DirectedGraph();
    Random r = new Random();
    for(int i =0;i<n;i++)
    {
      g.addNode(i);
    }
    for(int i =0;i<n;i++)
    {
      Node nd = g.getNode(i); //node the edge is being added to
      int range = r.nextInt(n); // random number of edges to add to Node nd
      for(int j = 0;j<range;j++)
      {
        int nodeNum = r.nextInt(n); //index of a random node to add to nd
        if(GraphSearch.DFSIter(g.getNode(nodeNum),nd) ==null) // DFSIter returns null when a path cant be found from the random node to nd
        {
          g.addDirectedEdge(nd,g.getNode(nodeNum)); // if a path cant be found an edge is added between nd and the random node
        }
      }
      //System.out.println("Finished adding node "+i+"'s edges");
    }
    return g;
  }

  public static void main(String []args)
  {
    Main m = new Main();
    Graph g = m.createLinkedList(10000);
    //ArrayList<Node> r = m.BFTRecLinkedList(g);
    ArrayList<Node> r = m.BFTIterLinkedList(g);
    r.forEach(n->System.out.println(n.nodeVal));
    DirectedGraph dg = m.createRandomDAGIter(100);
    System.out.println("finished creating dag");
    ArrayList<Node> k = TopSort.Kahns(dg);
    if(k == null)
      System.out.println("k is null");
    else
      k.forEach(n->System.out.println(n.nodeVal));
  }
}
