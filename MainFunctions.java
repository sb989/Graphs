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

  ArrayList<GridNode> astar(final GridNode sourceNode,final GridNode destNode)
  {
    HashMap<GridNode,Integer> a = new HashMap <GridNode,Integer>();
    HashMap<GridNode,GridNode> parent = new HashMap<GridNode,GridNode>();
    a.put(sourceNode,0+manhattanDistance(sourceNode,destNode));
    GridNode curr = sourceNode;
    ArrayList<GridNode> visited = new ArrayList<GridNode>();
    while(true)
    {
      if(visited.contains(destNode))
        break;
      if(visited.contains(curr))
      {
        System.out.println(visited.size()+" "+a.size());
        break;
      }
      if(!visited.contains(curr))
        visited.add(curr);
      for(int i =0;i<curr.adj.size();i++)
      {
        GridNode gn = curr.adj.get(i);
        int dist = a.get(curr)+1;//manhattanDistance(gn,destNode);
        if(!visited.contains(curr.adj.get(i)))
        {
          if(!a.containsKey(gn))
          {
            a.put(gn,dist);
            parent.put(gn,curr);
          }
          else if(dist+manhattanDistance(gn,destNode) <a.get(gn)+manhattanDistance(gn,destNode))
          {
            a.replace(gn,dist);
            parent.replace(gn,curr);
          }
        }
      }
      curr = findSmallestGridNode(a,visited,destNode);
      if(curr == null)
        break;
    }
    if(visited.contains(destNode))
    {
      System.out.println("creating return array");
      ArrayList<GridNode> ret = new ArrayList<GridNode>();
      ret.add(parent.get(destNode));
      for(int gn = 0;gn<parent.size()-1;gn++)
      {
        GridNode temp = parent.get(ret.get(0));
        ret.add(0,temp);
        if(temp == sourceNode)
          break;
      }
      ret.add(destNode);
      //System.out.println("size of ret is "+ret.size());
      return ret;
    }
    else
      return null;
  }

  static int manhattanDistance(final GridNode sourceNode, final GridNode destNode)
  {
    int x = Math.abs(sourceNode.x-destNode.x);
    int y = Math.abs(sourceNode.y-destNode.y);
    return x+y;
  }

  public static GridNode findSmallestGridNode(HashMap<GridNode,Integer> a, ArrayList<GridNode> visited,GridNode destNode)
  {
    Object []nodes = ((a.keySet()).toArray());
    GridNode smallest = (GridNode)nodes[0];
    for(int i = 0;i<nodes.length;i++)
    {
      if(!visited.contains((GridNode)nodes[i]))
        {
          smallest = (GridNode)nodes[i];
          break;
        }
      else if(visited.contains((GridNode)nodes[i]) && i==nodes.length-1)
      {
        return null;
      }
    }
    for(int n = 0;n<nodes.length;n++)
    {
      if(a.get(nodes[n])+manhattanDistance((GridNode)nodes[n],destNode)<=a.get(smallest)+manhattanDistance(smallest,destNode) && !visited.contains(nodes[n]))
      {
        smallest = (GridNode)nodes[n];
      }
    }
    return smallest;
  }

  GridGraph createRandomGridGraph(int n)
  {
    GridGraph gp = new GridGraph();
    Random r = new Random();
    for(int x = 0;x<n;x++)
    {
      for(int y = 0;y<n;y++)
      {
        gp.addGridNode(x,y,r.nextInt(n));
      }
    }
    for(int x = 0;x<n;x++)
    {
      for(int y = 0;y<n;y++)
      {
        if(x!=0 && r.nextGaussian()>0)
        {
          gp.addUndirectedEdge(gp.getGridNode(x*n+y),gp.getGridNode((x-1)*n+y));//adds left grid node edge
        }
        if(x!=n-1 && r.nextGaussian()>0)
        {
          gp.addUndirectedEdge(gp.getGridNode(x*n+y),gp.getGridNode((x+1)*n+y));//adds right grid node edge
        }
        if(y!=0 && r.nextGaussian()>0)
        {
          gp.addUndirectedEdge(gp.getGridNode(x*n+y),gp.getGridNode(x*n+(y-1)));//adds up grid node edge
        }
        if(y!=n-1 && r.nextGaussian()>0)
        {
          gp.addUndirectedEdge(gp.getGridNode(x*n+y),gp.getGridNode(x*n+(y+1)));//adds down grid node edge
        }
      }
    }
    return gp;
  }

}
