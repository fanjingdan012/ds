package graph;


public interface IGraph { //graph class ADT
    int VISITED = 1;
    int UNVISITED = 0;

    public int n();    //number of vertices

    public int e();    // number of edges

    public IEdge first(int v);    // get first edge for vertex

    public IEdge next(IEdge w);// get next edge for a vertex

    public boolean isEdge(IEdge w); // true if this is an edge

    public boolean isEdge(int i, int j); // true if this is an edge

    public int v1(IEdge w);

    public int v2(IEdge w);

    public void setEdge(int i, int j, int weight);// set edge weight

    public void setEdge(IEdge w, int weight);

    // set edge weight
    public void delEdge(IEdge w);    // delete edge w

    public void delEdge(int i, int j);    //delete edge (i, j)

    public int weight(int i, int j);    //return weight of edge

    public int weight(IEdge w);            //return weight of edge

    public void setMark(int v, int val);    // set mark for v

    public int getMark(int v);        // set mark for v
}// interface Graph

