import java.util.*;
public class Graph
{

  ArrayList<Node> adjList;
  int count;

  Graph()
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
    adjList.forEach(n-> n.setGraphSize(adjList.size()+1));
    Node n = new Node(nodeVal,adjList.size()+1);
    n.setIndex(this.count);
    this.count++;
    this.adjList.add(n);
  }

  void removeNode(final Node first)
  {
    int ind = first.getIndex();
    Node n;
    for(int i =0;i<ind;i++)
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

  void addUndirectedEdge(final Node first,final Node second)
  {
    int f = (first.getIndex());
    int s = (second.getIndex());
    (this.adjList.get(f)).addUnweightedEdge(second);
    (this.adjList.get(s)).addUnweightedEdge(first);
  }

  void removeUndirectedEdge(final Node first,final Node second)
  {
    int f = (first.getIndex());
    int s = (second.getIndex());
    (this.adjList.get(f)).removeEdge(second);
    (this.adjList.get(s)).removeEdge(first);
  }

  HashSet<Node> getAllNodes()
  {
    HashSet<Node>ret = new HashSet<Node>();
    this.adjList.forEach((n)-> ret.add(n));
    return ret;
  }

}
