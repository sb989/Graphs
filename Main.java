import java.util.*;
//import java.util.Random;
public class Main
{

  public static void main(String []args)
  {
    MainFunctions m = new MainFunctions();
    Graph g = m.createLinkedList(10000);
    //ArrayList<Node> r = m.BFTRecLinkedList(g);
    ArrayList<Node> r = m.BFTIterLinkedList(g);
    //r.forEach(n->System.out.println(n.nodeVal));
    DirectedGraph dg = m.createRandomDAGIter(100);

    System.out.println(GraphSearch.isCyclic(dg.adjList));
    System.out.println("finished creating dag");

    ArrayList<Node> k = TopSort.Kahns(dg);

    if(k == null)
      System.out.println("k is null. dg is not a dag");
    else
      System.out.println("k is a dag");//k.forEach(n->System.out.println(n.nodeVal));
    ArrayList<Node> md = TopSort.mDFS(dg);
    if(md == null)
      System.out.println("md is null. dg is not a dag");
    else
      System.out.println("md is a dag");

    WeightedGraph wg = m.createRandomCompleteWeightedGraph(100);
    System.out.println("finished creating weighted graph");
    HashMap <Node,Integer> d = m.dijkstras(wg.getNode(0));
    for(Node n:d.keySet())
    {
      //System.out.println("the distance to "+n.nodeVal+" is "+d.get(n));
    }

    GridGraph gg = m.createRandomGridGraph(100);
    ArrayList<GridNode> astar = m.astar(gg.getGridNode(0),gg.getGridNode(9999));
    if(astar != null)
    {
      for(int i =0;i<astar.size();i++)
      {
        GridNode a = astar.get(i);
        System.out.println("("+a.x+","+a.y+")");
      }
    }

    else
      System.out.println("there is not path to the destination");
  }
}
