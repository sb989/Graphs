import java.util.*;
public class GraphSearch
{

  static ArrayList<Node> visited = new ArrayList<Node>();


  public static Boolean isCyclic(ArrayList<Node>adjList)
  {

    Node n;
    for(int i = 0;i<adjList.size();i++)
    {
      n = adjList.get(i);
      if(isCyclicUtil(adjList,i))
        return true;
    }
    return false;

  }

  public static Boolean isCyclicUtil(ArrayList<Node> adjList, int start)
  {
    int i = start;
    Stack <Integer> neighbor = new Stack<Integer>();
    Stack <Integer> parent = new Stack<Integer>();
    Boolean[] rec = new Boolean[adjList.size()];
    Boolean[] v = new Boolean[adjList.size()];
    Node curr = adjList.get(i);
    int count = 0;
    Arrays.fill(rec,Boolean.FALSE);
    Arrays.fill(v,Boolean.FALSE);
    while(true)
    {
      //System.out.println(i);
      if(rec[i])
      {
        return true;
      }
      if(v[i])
      {
        if(neighbor.empty())
        {
          break;
        }
        i = neighbor.pop();
        if(i == -1)
        {
          i = parent.pop();
          rec[i] =false;
        }
        curr = adjList.get(i);
        continue;
        //return false;
      }
      v[i] = true;
      rec[i] = true;
      count = curr.adj.size();
      if(count>0)
      {
        neighbor.push(-1);
        parent.push(curr.ind);
        for(int c = 0;c<count;c++)
        {
          neighbor.push(curr.getNeighbor(c).ind);
        }

        i = neighbor.pop();
        curr = adjList.get(i);
      }
      else
      {
        rec[i] = false;
      }
    }
    return false;
  }


  public static ArrayList<Node> DFSRec(final Node start, final Node end)
  {
    ArrayList<Node> ret = new ArrayList<Node>();
    if(start == end)
    {
      ret.add(end);
      visited.add(end);
      return ret;
    }
    ret.add(start);
    visited.add(start);
    for(int i = 0;i<start.adj.size();i++)
    {
      Node s = start.getNeighbor(i);
      if(!visited.contains(s))
      {
        ret.addAll(DFSRec(s,end));
        if(visited.get(visited.size()-1)==end)
          break;
      }
    }
    if(visited.get(0) == start && visited.get(visited.size()-1)!=end)
    {
      visited.clear();
      return null;
    }
    else if(visited.get(0) == start && visited.get(visited.size()-1)==end)
    {
      visited.clear();
      return ret;
    }
    else
      return ret;
  }

  public static ArrayList<Node> DFSIter(final Node start, final Node end)
  {
    Node curr = start;
    Boolean []v = new Boolean[start.getGraphSize()];
    Arrays.fill(v,Boolean.FALSE);
    Stack<Node> s = new Stack<Node>();
    ArrayList<Node> ret = new ArrayList<Node>();
    v[start.ind]= true;
    while(true)
    {
      if(curr == end && curr != start)
      {
        v[end.ind] = true;
        ret.add(end);
        break;
      }
      for(int i =0;i < curr.adj.size();i++)
      {
        if(!v[curr.getNeighbor(i).ind])
        {
          s.push(curr);
          curr = curr.getNeighbor(i);
          v[curr.ind] = true;
          ret.add(curr);
          continue;
        }
      }
      if(s.empty())
      {
        break;
      }
      curr = s.pop();
    }
    if(ret.contains(end))
    {
      visited.clear();
      return ret;
    }
    else
    {
      visited.clear();
      return null;
    }
  }

  static ArrayList<Node> BFTRecUtil(Node n)
  {
    ArrayList<Node>ret = new ArrayList<Node>();
    ArrayList<Node>neighbor = new ArrayList<Node>();
    for(int i=0;i<n.adj.size();i++)
    {
      if(!visited.contains(n.getNeighbor(i)))
      {
        visited.add(n.getNeighbor(i));
        neighbor.add(n.getNeighbor(i));
        ret.add(n.getNeighbor(i));
      }
    }
    for(int i = 0;i<neighbor.size();i++)
    {
      ret.addAll(BFTRecUtil(neighbor.get(i)));
    }
    return ret;
  }

  public static ArrayList<Node> BFTRec(final Graph graph)
  {
    ArrayList<Node> ret = new ArrayList<Node>();
    Node n = graph.getNode(0);
    ret.add(n);
    visited.add(n);
    ret.addAll(BFTRecUtil(n));
    visited.clear();
    return ret;
  }



  public static ArrayList<Node> BFTIter(final Graph graph)
  {
    Node n = graph.getNode(0);
    ArrayList<Node> ret = new ArrayList<Node>();
    ArrayList<Node> neighbor = new ArrayList<Node>();
    ret.add(n);
    visited.add(n);
    Node curr = n;
    while(true)
    {
      for(int i=0;i<curr.adj.size();i++)
      {
        if(!visited.contains(curr.getNeighbor(i)))
        {
          visited.add(curr.getNeighbor(i));
          neighbor.add(curr.getNeighbor(i));
          ret.add(curr.getNeighbor(i));
        }
      }
      if(neighbor.isEmpty())
      {
        break;
      }
      curr = neighbor.remove(0);
    }
    visited.clear();
    return ret;
  }

}
