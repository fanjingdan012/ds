package graph;

import queue.Queue;

class Graphm implements IGraph {
    private int[][] matrix;
    private int numEdge;
    private int[] mark;

    public Graphm(int n) {
        mark = new int[n];
        matrix = new int[n][n];
        numEdge = 0;
    }

    public int n() {
        return mark.length;
    }

    public int e() {
        return numEdge;
    }

    public IEdge first(int v) {
        for (int i = 0; i < mark.length; i++)
            if (matrix[v][i] != 0)
                return new Edgem(v, i);
        return null;
    }

    public IEdge next(IEdge w) {
        if (w == null) return null;
        for (int i = w.v2() + 1; i < mark.length; i++)
            if (matrix[w.v1()][i] != 0)
                return new Edgem(w.v1(), i);
        return null;
    }

    public boolean isEdge(IEdge w) {
        if (w == null) return false;
        else return matrix[w.v1()][w.v2()] != 0;
    }

    public boolean isEdge(int i, int j) {
        return false;//TODO

    }


    public int v1(IEdge w) {
        return 0;//TODO
    }

    public int v2(IEdge w) {
        return 0;//TODO
    }

    public void setEdge(IEdge w, int weight) {
    }

    public void setEdge(int v1, int v2, int weight) {
    }

    public void delEdge(IEdge w) {
    }

    public void delEdge(int v1, int v2) {
    }

    public int weight(IEdge w) {
        return 0;
    }

    public int weight(int v1, int v2) {
        return 0;
    }

    public void setMark(int v, int val) {

    }

    public int getMark(int v) {
        return mark[v];
    }








}
