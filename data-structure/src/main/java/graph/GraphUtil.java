package graph;

import queue.Queue;


import static graph.IGraph.UNVISITED;
import static graph.IGraph.VISITED;

public class GraphUtil {

    static void Floyd(Graph G, int[][] D) {
        for (int i=0; i<G.n(); i++)
            for (int j=0; j<G.n(); j++)
                D[i][j] = G.weight(i, j);
        for (int k=0; k<G.n(); k++){
            for (int i=0; i<G.n(); i++)
                for (int j=0; j<G.n(); j++)
                    if ((D[i][k]!=Integer.MAX_VALUE) &&
                            (D[k][j] != Integer.MAX_VALUE) &&
                            (D[i][j] > (D[i][k] + D[k][j])))
                        D[i][j] = D[i][k]+D[k][j];
        }
    }

    static void graphTraverse(Graph g) {
        for (int v = 0; v < g.n(); v++)
            g.setMark(v, UNVISITED); // Initialize mark bits
        for (int v = 0; v < g.n(); v++)
            if (g.getMark(v) == UNVISITED)
                doTraverse(g, v);
    }

    private static void doTraverse(Graph g, int v) {
    }

    static void DFS(Graph G, int v) {
        preVisit(G, v);
        G.setMark(v, VISITED);
        for (IEdge w = G.first(v); G.isEdge(w); w = G.next(w))
            if (G.getMark(G.v2(w)) == UNVISITED)
                DFS(G, G.v2(w));
        postVisit(G, v);
    }

    private static void postVisit(Graph G, int v) {
    }

    private static void preVisit(Graph G, int v) {
        
    }

    static void BFS(Graph G, int start) {
        Queue Q = new Queue(G.n());
        Q.enqueue(new Integer(start));
        G.setMark(start, VISITED);
        while (!Q.isEmpty()) {
            int v = ((Integer) Q.dequeue()).intValue();
            preVisist(G, v);
            for (IEdge w = G.first(v); G.isEdge(w); w = G.next(w))
                if (G.getMark(G.v2(w)) == UNVISITED) {
                    G.setMark(G.v2(w), VISITED);
                    Q.enqueue(new Integer(G.v2(w)));
                }
            postVisit(G, v);
        }
    }

    static void topsort(Graph G) {
        for (int i = 0; i < G.n(); i++)
            G.setMark(i, UNVISITED);
        for (int i = 0; i < G.n(); i++)
            if (G.getMark(i) == UNVISITED)
                tophelp(G, i);
    }

    static void tophelp(Graph G, int v) {
        G.setMark(v, VISITED);
        for (IEdge w = G.first(v); G.isEdge(w); w = G.next(w))
            if (G.getMark(G.v2(w)) == UNVISITED)
                tophelp(G, G.v2(w));
        printout(v);
    }

    void topsortDetectCycle() throws CycleFound {
        Vertex v, w;
        for (int counter = 0; counter < NUM_VERTICES; counter++) {
            v = findNewVertexOfDegreeZero();
            v.topNum = counter; // assign next number
            for each w adjacent to v
            w.indegree--;
        }
    }

    static void topsortNonRecursive(Graph G) {
        Queue Q = new Queue(G.n());
        int[] Count = new int[G.n()];
        int v;
        for (v = 0; v < G.n(); v++) Count[v] = 0;
        for (v = 0; v < G.n(); v++)
            for (IEdge w = G.first(v); G.isEdge(w); w = G.next(w))
                Count[G.v2(w)]++;
        for (v = 0; v < G.n(); v++)
            if (Count[v] == 0)
                Q.enqueue(new Integer(v));
        while (!Q.isEmpty()) {
            v = ((Integer) Q.dequeue()).intValue();
            printout(v);
            for (IEdge w = G.first(v); G.isEdge(w); w = G.next(w)) {
                Count[G.v2(w)]--;
                if (Count[G.v2(w)] == 0)
                    Q.enqueue(new Integer(G.v2(w)));
            }
        }
    }

    void topsortDetectCycle() throws CycleFound {
        Vertex v, w;
        for (int count = 0; counter < NUM_VERTICES; counter++) {
            v = findNewVertexOfDegreeZero();
            if (null == v)
                throw new CycleFound();
            v.topNum = counter;
            for each w adjacent to v
            w.indegree--;
        }
    }

    static void topsort(Graph G) {
        Queue Q = new Queue(G.n());
        int[] Count = new int[G.n()];
        int v;
        int dealedVertex;
        dealedVertex = 0;
        for (v = 0; v < G.n(); v++) Count[v] = 0;
        for (v = 0; v < G.n(); v++)
            for (IEdge w = G.first(v); G.isEdge(w); w = G.next(w))
                Count[G.v2(w)]++;
        for (v = 0; v < G.n(); v++)
            if (Count[v] == 0)
                Q.enqueue(new Integer(v));
        while (!Q.isEmpty()) {
            v = ((Integer) Q.dequeue()).intValue();
            printout(v);
            dealedVertex++;
            for (IEdge w = G.first(v); G.isEdge(w); w = G.next(w)) {
                Count[G.v2(w)]--;
                if (Count[G.v2(w)] == 0)
                    Q.enqueue(new Integer(G.v2(w)));
            }
        }
        if (dealedVertex != G.n())
            throw new RuntimeException("The graph is not a DAG");
    }

    void unweight(Vertex s) {
        Vertex v, w;
        s.dist = 0;
        for (int currDist = 0; currDist < NUM_VERTICES; currDist++)
            for each vertex v
        if (!v.known && v.dist == currDist) {
            v.known = true;
            for each w adjacent to v
            if (w.dist == INFINITY) {
                w.dist = currDis + 1;
                w.path = v;
            }
        }
    }

    void unweighted(Vertex s) {
        Queue q;
        Vertex v, w;
        q = new Queue();
        q.enqueue(s);
        s.dist = 0;
        while (!q.isEmpty()) {
            v = q.dequeue();
            v.know = true;
            for each w adjacent to v
            if (w.dist == INFINITY) {
                w.dist = v.dist + 1;
                w.path = v;
                q.enqueue(w);
            }
        }
    }

    static void Dijkstra (Graph G, int s, int[] D){
        for (int i=0; i<G.n(); i++)
            D[i] = Integer.MAX_VALUE;
        D[s] = 0;
        for (int i=0; i<G.n(); i++){
            int v = minVertex(G,D);
            G.setMark(v, VISITED);
            if (Integer.MAX_VALUE == D[v]) return;
            for (IEdge w = G.first(v); G.isEdge(w); w = G.next(w))
                if (D[G.v2(w)] > (D[v]+G.weight(w)))
                    D[G.v2(w)] = D[v] + G.weight(w);
        }
    }
    static int minVertex(Graph G, int[] D){
        int v=0;
        for (int i=0; i<G.n(); i++)
            if (G.getMark(i) == UNVISITED){
                v = i; break;
            }
        for (int i=0; i<G.n(); i++)
            if ((G.getMark(i) == UNVISITED) && (D[i]<D[v]))
                v = i;
        return v;
    }

    void weightedNegative (Vertex s){
        Queue q;
        Vertex v, w;
        q = new Queue();
        q.enqueue(s);
        while( !q.isEmpty()){
            v = q.dequeue();
            for (each w adjacent to v){
                if (v.dist + cvw < w.dist) {
                    w.dist = v.dist + cvw;
                    w.path = v;
                    if (w is not already in q)
                    q.enqueue(w);
                }
            }
        }
    }
}
