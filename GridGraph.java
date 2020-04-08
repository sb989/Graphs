import java.util.*;
public class GridGraph
{

  ArrayList<GridNode> adjList;
  int count;
  public GridGraph()
  {
    adjList = new ArrayList<GridNode>();
    count = 0;
  }

  void addGridNode(final int x, final int y, final int nodeVal)
  {
    //adjList.forEach(x-> x.n.setGraphSize(adjList.size()+1));
    GridNode gn = new GridNode(x,y,nodeVal,this.count+1);
    this.count++;
    this.adjList.add(gn);
  }

  GridNode getGridNode(int ind)
  {
    return adjList.get(ind);
  }

  void addUndirectedEdge(final GridNode first, final GridNode second)
  {
    int f = (first.getIndex());
    int s = (second.getIndex());
    (this.adjList.get(f)).addEdge(second);
    (this.adjList.get(s)).addEdge(first);
  }

  void removeUndirectedEdge(final GridNode first, final GridNode second)
  {
    int f = (first.getIndex());
    int s = (second.getIndex());
    (this.adjList.get(f)).removeEdge(second);
    (this.adjList.get(s)).removeEdge(first);
  }

  HashSet<GridNode> getAllNodes()
  {
    HashSet<GridNode>ret = new HashSet<GridNode>();
    this.adjList.forEach((n)-> ret.add(n));
    return ret;
  }
}
