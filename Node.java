import java.util.*;
public class Node
{
  int nodeVal;
  ArrayList<Node> adj;
  int ind;
  Node(int n)
  {
    this.nodeVal = n;
    this.adj = new ArrayList<>();
    this.ind = 0;
  }

  void setIndex(int ind)
  {
    this.ind = ind;
  }

  int getIndex()
  {
    return this.ind;
  }

  void removeEdge(Node n)
  {
    this.adj.remove(n);
  }

  void addEdge(Node n)
  {
    if(n== this)
      return;
    for(int i =0;i<this.adj.size();i++)
    {
      if(this.adj.get(i) == n)
        return;
    }
    this.adj.add(n);
  }

}
