import java.util.*;
public class GraphSearch
{

  static ArrayList<Node> visited = new ArrayList<Node>();

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
    Stack<Node> s = new Stack<Node>();
    ArrayList<Node> ret = new ArrayList<Node>();
    visited.add(start);
    while(true)
    {
      if(curr == end)
      {
        visited.add(end);
        ret.add(end);
        break;
      }
      for(int i =0;i < curr.adj.size();i++)
      {
        if(!visited.contains(curr.getNeighbor(i)))
        {
          s.push(curr);
          curr = curr.getNeighbor(i);
          visited.add(curr);
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
