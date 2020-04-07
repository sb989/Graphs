import java.util.*;
public class TopSort
{
  static ArrayList  <AbstractMap.SimpleEntry<Node,Integer>> inD = new ArrayList <AbstractMap.SimpleEntry<Node,Integer>>();
  static ArrayList  <Node> visited = new ArrayList <Node>();
  static ArrayList  <Node> zeroD = new ArrayList <Node>();

  public static ArrayList<Node> Kahns(final DirectedGraph graph)
  {
    ArrayList  <Node> ret= new ArrayList<Node>();

    graph.adjList.forEach(n->inD.add(new AbstractMap.SimpleEntry<Node,Integer>(n,n.inDegree))); //creates pairs of all Nodes with their in degree value and puts them in inD
    inD.forEach(n->addZeroDegreeNode(n)); // places all Nodes with in degree of 0 in zeroD
    Node n;
    AbstractMap.SimpleEntry<Node,Integer> p;
    inD.forEach(x->System.out.println("the degrees are "+x.getValue()));
    while(zeroD.size() != 0) // repeats while zeroD is not empty
    {
      for(int i = 0;i<zeroD.size();i++)
      {
        n = zeroD.get(i);
        KahnsUtil(n);
      }

    }
    if(visited.size() != inD.size())
    {
      System.out.println("visiteds size is "+visited.size()+" inDs size is "+inD.size());
      inD.clear();
      visited.clear();
      zeroD.clear();
      return null;
    }
    else
    {
      ret.addAll(visited);
      inD.clear();
      visited.clear();
      zeroD.clear();
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
        if(getNodeDegree(neighbor) == 0)
        {
          zeroD.add(neighbor);
        }
      }

    }

  }

  static void addZeroDegreeNode(AbstractMap.SimpleEntry<Node,Integer> n)
  {
    if((n.getValue() == 0))
      zeroD.add(n.getKey());
  }

  static int getNodeDegree(Node n)
  {
    int count = inD.size();
    for(int i = 0;i<count;i++)
    {
      if((inD.get(i)).getKey() == n)
      {
        return (inD.get(i)).getValue();
      }
    }
    return -1;
  }

  static void changeDegreeValue(Node n,int change)
  {
    int degree,count;
    AbstractMap.SimpleEntry <Node,Integer> p;
    count = inD.size();
    degree = -change;
    for(int i = 0;i<count;i++)
    {
      p = inD.get(i);
      if(p.getKey() == n)
      {
        degree = p.getValue();
        inD.remove(i);
        break;
      }
    }
    inD.add(new AbstractMap.SimpleEntry<Node,Integer>(n,degree+change));
  }
}
