import java.util.*;

public class MainFunctions
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
        g.addDirectedEdge(nd,g.getNode(nodeNum));// if a path cant be found an edge is added between nd and the random node
        if(GraphSearch.isCyclicUtil(g.adjList,i))
        {
          g.removeDirectedEdge(nd,g.getNode(nodeNum));
        }
      }
      if(i%(n/10) == 0)
        System.out.print(".");
      //System.out.println("Finished adding node "+i+"'s edges");
    }
    System.out.println(" Done.");
    return g;
  }

  WeightedGraph createRandomCompleteWeightedGraph(final int n)
  {
    WeightedGraph wg = new WeightedGraph();
    Random r = new Random();
    int weight;
    for(int i = 0; i < n; i++)
    {
      wg.addNode(i);
    }

    for(int i = 0;i<n;i++)
    {
      for(int k=0;k<n;k++)
      {
        weight = r.nextInt(100);
        wg.addWeightedEdge(wg.getNode(i),wg.getNode(k),weight);
      }
    }
    return wg;
  }

  WeightedGraph createWeightedLinkedList(final int n)
  {
    WeightedGraph wg = new WeightedGraph();
    Random r = new Random();
    int weight = 1;
    for(int i = 0; i < n; i++)
    {
      wg.addNode(i);
    }
    for(int i =0;i<n-1;i++)
    {
      wg.addWeightedEdge(wg.getNode(i),wg.getNode(i+1),weight);
    }
    return wg;
  }

  HashMap<Node,Integer> dijkstras(final Node start)
  {
    HashMap<Node,Integer> hm = new HashMap<Node,Integer>();
    HashMap<Node,Node> parent = new HashMap<Node,Node>();
    hm.put(start,0);
    Node curr = start;
    ArrayList <Node> visited = new ArrayList<Node>();
    while(true)
    {
      if(visited.contains(curr))
        break;
      visited.add(curr);
      for(int i =0;i<curr.adj.size();i++)
      {
        Node n = curr.adj.get(i).getKey();
        int weight = curr.adj.get(i).getValue();
        if(!visited.contains(curr.adj.get(i).getKey()))
        {
          if(!hm.containsKey(n))
          {
            hm.put(n,weight);
            parent.put(n,curr);
          }
          else if(hm.get(curr)+weight <hm.get(n))
          {
            hm.replace(n,hm.get(curr)+weight);
            parent.replace(n,curr);
          }
        }
      }
      curr = findSmallestNode(hm,visited);
    }
    return hm;
  }

  public static Node findSmallestNode(HashMap<Node,Integer> hm, ArrayList<Node> visited)
  {
    Object []nodes = ((hm.keySet()).toArray());
    Node smallest = (Node)nodes[0];
    for(int n = 0;n<nodes.length;n++)
    {
      if(hm.get(nodes[n])<hm.get(smallest) && !visited.contains(nodes[n]))
      {
        smallest = (Node)nodes[n];
      }
    }
    return smallest;
  }

}
