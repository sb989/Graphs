import java.util.*;
public class DirectedGraph
{

  ArrayList <Node> adjList;
  int count;

  DirectedGraph()
  {
      this.adjList = new ArrayList<Node>();
      this.count = 0;
  }

  Node getNode(int ind)
  {
    return this.adjList.get(ind);
  }

  void addNode(final int nodeVal)
  {
    Node n = new Node(nodeVal);
    n.setIndex(this.count);
    this.count++;
    this.adjList.add(n);
  }

  void removeNode(final Node first)
  {
    int ind = first.getIndex();
    Node n;
    for(int i =-0;i<ind;i++)
    {
      (this.adjList.get(i)).removeEdge(first);
    }
    for(int i = ind+1;i<count;i++)
    {
      int temp = (this.adjList.get(i)).getIndex();
      (this.adjList.get(i)).setIndex(temp-1);
      (this.adjList.get(i)).removeEdge(first);
    }
    this.adjList.remove(ind);
    this.count --;
  }

  void addDirectedEdge(final Node first,final Node second)//first points to second
  {
    int f = (first.getIndex());
    (this.adjList.get(f)).addUnweightedEdge(second);
  }

  void removeDirectedEdge(final Node first,final Node second)
  {
    int f = (first.getIndex());
    (this.adjList.get(f)).removeEdge(second);
  }

  HashSet<Node> getAllNodes()
  {
    HashSet<Node>ret = new HashSet<Node>();
    this.adjList.forEach((n)-> ret.add(n));
    return ret;
  }

  Boolean hasLeaf()
  {
    for(int i = 0;i<adjList.size();i++)
    {
      if((adjList.get(i)).isLeaf())
      {
        return true;
      }
    }
    return false;
  }


}
