package graph;

import org.junit.Assert;
import vector.DLinkedListVector;

class Link {
    int weight;
}
class GraphList extends DLinkedListVector {
    private Link curr;
    public Link currLink() {
        return curr;
    }

    public void setCurr(Link who) {
        curr = who;
    }

    public void setValue(int[] currEdge) {
    }

    public Object currValue() {
        return null;
    }

    public void setFirst() {
    }

    public boolean isInList() {
        return false;
    }

    public void insert(int[] currEdge) {
    }
    public void remove(int[] currEdge) {
    }
}

class Edgel implements IEdge {
    private int vert1, vert2;
    private Link itself;

    public Edgel(int vt1, int vt2, Link it) {
        this.vert1=vt1;
        this.vert2=vt2;
        this.itself=it;
    }

    public int v1() {
        return vert1;
    }

    public int v2() {
        return vert2;
    }

    public Link theLink() {
        return itself;
    }
}

class Graphl   implements IGraph {
    private GraphList[] vertex;
    private int numEdge;
    public int[] Mark;

    public Graphl(int n) {
        Mark = new int[n];
        vertex = new GraphList[n];
        for (int i = 0; i < n; i++)
            vertex[i] = new GraphList();
        numEdge = 0;
    }

    public int n() {
        return Mark.length;
    }

    public int e() {
        return numEdge;
    }

    public IEdge first(int v) {
        vertex[v].setFirst();
        if (vertex[v].currValue() == null) return null;
        return new Edgel(v, ((int[]) vertex[v].currValue())[0],
                vertex[v].currLink());
    }

    @Override
    public IEdge next(IEdge w) {
        return null;
    }

    public boolean isEdge(IEdge e) {
        if (e == null) return false;
        vertex[e.v1()].setCurr(((Edgel) e).theLink();
        if (!vertex[e.v1()].isInList()) return false;
        return ((int[]) vertex[e.v1()].currValue())[0] == e.v2();
    }

    @Override
    public boolean isEdge(int i, int j) {
        return false;
    }

    @Override
    public int v1(IEdge w) {
        return 0;
    }

    @Override
    public int v2(IEdge w) {
        return 0;
    }

    public void setEdge(int i, int j, int weight) {
        // set edge weight
        Assert.assertTrue( "cannot set weight to 0",weight != 0);
        int[] currEdge = {j, weight};
        if (isEdge(i, j))
            vertex[i].setValue(currEdge);
        else {
            vertex[i].insert(currEdge);
            numEdge++;
        }
    }

    @Override
    public void setEdge(IEdge w, int weight) {

    }

    @Override
    public void delEdge(IEdge w) {

    }

    public void delEdge(int i, int j) {
        if (isEdge(i, j)) {
            vertex[i].remove();
            numEdge--;
        }
    }

    public int weight(int i, int j) {
        if (isEdge(i, j))
            return ((int[]) vertex[i].currValue())[j];
        else
            return Integer.MAX_VALUE;
    }

    @Override
    public int weight(IEdge w) {
        return 0;
    }

    public void setMark(int v, int val) {
        Mark[v] = val;
    }

    public int getMark(int v) {
        return Mark[v];
    }
}
