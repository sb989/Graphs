import java.util.*;
public class Node
{
  int nodeVal;
  ArrayList<AbstractMap.SimpleEntry<Node,Integer>> adj;
  int ind;
  int inDegree;


  Node(int n)
  {
    this.nodeVal = n;
    this.adj = new ArrayList<>();
    this.ind = 0;
    this.inDegree = 0;
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
    int count = adj.size();
    for(int i =0 ; i<count ; i++)
    {
      if((adj.get(i)).getKey() == n)
      {
        this.adj.remove(i);
        return;
      }

    }
  }

  void addEdge(Node n,int weight)
  {
    if(n== this)
      return;
    for(int i = 0;i<this.adj.size();i++)
    {
      if(this.adj.get(i).getKey() == n)
        return;
    }
    this.adj.add(new AbstractMap.SimpleEntry<Node,Integer>(n,weight));
    n.inDegree +=1;
  }

  void addUnweightedEdge(Node n)
  {
    addEdge(n,0);
  }

  void addWeightedEdge(Node n,int weight)
  {
    addEdge(n,weight);
  }

  Node getNeighbor(int i)
  {
    if(i>this.adj.size())
      return null;
    return (this.adj.get(i)).getKey();
  }

  Boolean isLeaf()
  {
    return this.adj.size() == 0;
  }

}
