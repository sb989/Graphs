import java.util.*;
public class GridNode
{
  int x;
  int y;
  int value;
  int ind;
  ArrayList<GridNode> adj;
  public GridNode(int x,int y,int value,int count)
  {
    this.x = x;
    this.y = y;
    this.adj = new ArrayList<GridNode>();
    this.value = value;
    this.ind = count-1;
  }

  void setIndex(int ind)
  {
    this.ind = ind;
  }

  int getIndex()
  {
    return this.ind;
  }

  void addEdge(GridNode gn)
  {
    if(gn == this)
      return;
    for(int i =0;i<this.adj.size();i++)
    {
      if(this.adj.get(i) == gn)
        return;
    }
    this.adj.add(gn);
  }

  void removeEdge(GridNode gn)
  {
    int count = this.adj.size();
    for(int i =0 ; i<count ; i++)
    {
      if(this.adj.get(i) == gn)
      {
        this.adj.remove(i);
        return;
      }
    }
    
  }

}
