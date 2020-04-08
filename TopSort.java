import java.util.*;
public class TopSort
{
  static int []inD;
  static ArrayList  <Node> visited = new ArrayList <Node>();
  static ArrayList  <Node> zeroD = new ArrayList <Node>();

  public static void printNodeNieghbor(Node n)
  {
    System.out.print("Node "+n.nodeVal+" has neighbors:");
    for(int i =0;i<n.adj.size();i++)
    {
      System.out.print(n.adj.get(i).getKey().nodeVal);
      System.out.print(" ");
    }
    System.out.println("");
  }

  public static ArrayList<Node> Kahns(final DirectedGraph graph)
  {
    ArrayList  <Node> ret= new ArrayList<Node>();

    Node n;
    inD = new int[graph.adjList.size()];
    Arrays.fill(inD,0);
    for(int i =0;i<graph.adjList.size();i++)
    {
      n = graph.adjList.get(i);
      n.adj.forEach(x->inD[x.getKey().ind]++);
    }
    for(int i = 0;i<graph.adjList.size();i++)
    {
      if(inD[i] == 0)
        zeroD.add(graph.adjList.get(i));
    } // places all Nodes with in degree of 0 in zeroD

    AbstractMap.SimpleEntry<Node,Integer> p;
    while(zeroD.size() != 0) // repeats while zeroD is not empty
    {
      for(int i = 0;i<zeroD.size();i++)
      {
        n = zeroD.get(i);
        KahnsUtil(n);
      }
    }
    if(visited.size() != graph.adjList.size())
    {
      System.out.println("visiteds size is "+visited.size()+" inDs size is "+inD.length);
      visited.clear();
      zeroD.clear();
      inD = null;
      System.gc();
      return null;
    }
    else
    {
      ret.addAll(visited);
      visited.clear();
      zeroD.clear();
      inD = null;
      System.gc();
      return ret;
    }

  }

  static void KahnsUtil(Node n)
  {
    int count;
    zeroD.remove(n);
    visited.add(n);
    Node neighbor;
    changeDegreeValue(n,-1);

    count = n.adj.size();
    for(int i = 0;i<count ; i++)
    {
      neighbor = n.getNeighbor(i);
      if(!zeroD.contains(neighbor))
      {
        changeDegreeValue(neighbor,-1);
        if(inD[neighbor.ind] == 0)
        {
          zeroD.add(neighbor);
        }
      }
    }
  }

  static void changeDegreeValue(Node n,int change)
  {
    inD[n.ind]+= change;
  }

  static ArrayList <Node> mDFS(final DirectedGraph graph)
  {
    Stack<Integer>neigh = new Stack<Integer>();
    Stack<Integer>ret = new Stack<Integer>();
    ArrayList<Node>r = new ArrayList<Node>();
    Stack<Integer>parent = new Stack<Integer>();
    Boolean []v = new Boolean[graph.adjList.size()];
    Arrays.fill(v,Boolean.FALSE);
    int i;
    Node curr;
    Node neighbor;
    for(int k = 0;k<graph.adjList.size();k++)
    {
      neigh.push(k);
      while(!neigh.empty())
      {
          i = neigh.pop();
          curr = graph.adjList.get(i);
          if(!v[i])
          {
            v[i] = true;
            r.add(curr);
          }
          for(int nn = 0;nn<curr.adj.size();nn++)
          {
            int ind = curr.adj.get(nn).getKey().ind;
            if(!v[ind])
            {
              neigh.push(ind);
            }
          }
      }
    }

    if(r.size() != graph.adjList.size())
    {
      System.out.println("adjList size is "+graph.adjList.size()+" rets size is "+ret.size());
      return null;
    }
    /*while(!ret.empty())
    {
      r.add(graph.adjList.get(ret.pop()));
    }*/
    return r;


  }


}
