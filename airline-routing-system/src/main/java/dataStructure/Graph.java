package dataStructure;

public class Graph {

	int adjMatrix[][];
	Vertex vertexList[];
	Edge sPath[];
	final int MAX_VERTEX = 20;
	final int INFINITY = 99999;
	int numVerts;
	int nEdge;
	int currentVertex;
	int startToCurrent;

	public Graph() {
		vertexList = new Vertex[MAX_VERTEX];
		adjMatrix = new int[MAX_VERTEX][MAX_VERTEX];
		sPath = new Edge[MAX_VERTEX];
		numVerts = 0;
		nEdge = 0;
		for (int i = 0; i < MAX_VERTEX; i++)
			for (int j = 0; j < MAX_VERTEX; j++)
				adjMatrix[i][j] = INFINITY;
	}

	public void addVertex(String node, int num) {
		vertexList[numVerts] = new Vertex(node, num);
		numVerts++;
	}

	public int getVnum() {
		return this.numVerts;
	}

	public void addEdge(Vertex start, Vertex end, int weight) {
		adjMatrix[start.num][end.num] = weight;
		Edge temp = new Edge(start, end, weight);
		this.sPath[nEdge] = temp;
		nEdge++;
	}

	public void addEdge(int start, int end, int weight) {
		adjMatrix[start][end] = weight;
		Edge temp = new Edge(this.vertexList[start], this.vertexList[end],
				weight);
		this.sPath[nEdge] = temp;
		nEdge++;
	}

	public void deleteVertex(int num) {
		// if(vertexList[num].isInNode){
		for (int i = 0; i < numVerts; i++) {
			adjMatrix[num][i] = 0;
			adjMatrix[i][num] = 0;
		}
		numVerts--;

		this.vertexList[num] = null;
		// }
		// else
		// System.out.println("?????");
	}

	public void deleteEdge(Vertex start, Vertex end, int weight) {
		adjMatrix[start.num][end.num] = 0;
		adjMatrix[end.num][start.num] = 0;
		boolean done = false;
		for (int i = 0; i < this.sPath.length; i++) {
			if (this.sPath[i] != null) {
				if (this.sPath[i].getNode().getNode().equals(start.getNode())) {
					if (this.sPath[i].getnextNode().getNode().equals(
							end.getNode())) {
						System.out.println("Edge deleted!");
						this.sPath[i] = null;
						done = true;
					}
				}
				if (!done)
					if (this.sPath[i].getnextNode().getNode().equals(
							start.getNode())) {
						if (this.sPath[i].getNode().getNode().equals(
								end.getNode())) {
							System.out.println("Edge deleted!");
							this.sPath[i] = null;
						}
					}

			}
		}
	}

	public void dijkstra() {
		// ????
		int startnum = 0;
		vertexList[startnum].isInNode = true;
		nEdge = 1;
		for (int i = startnum; i < numVerts; i++) {
			int tempDist = adjMatrix[startnum][i];
			Vertex start = vertexList[startnum];
			Vertex j = vertexList[i];
			sPath[i] = new Edge(start, j, tempDist);
		}
		while (nEdge < numVerts) {
			int indexMin = getMin();
			int minDist = sPath[indexMin].weight;
			if (minDist == INFINITY) {
				System.out.println("????");
			} else {
				currentVertex = indexMin;
				startToCurrent = sPath[indexMin].weight;
			}
			vertexList[currentVertex].isInNode = true;
			nEdge++;
			adjust_sPath();
		}

	}

	public String Dijkstra(Vertex start,Vertex end)
	{
		StringBuffer buf=new StringBuffer();
		int D[]=new int[MAX_VERTEX];
		int p[]=new int[MAX_VERTEX];
		boolean s[]=new boolean[MAX_VERTEX];
		int v = start.num;
		int w = end.num;
		int i,j,v1,pre;
		int min,max=Integer.MAX_VALUE;
		v1=v;
		int k=v1;
		int dist[][]=new int[MAX_VERTEX][MAX_VERTEX];
		for(i=0 ; i < dist.length;i++)
			for(j=0 ; j < dist.length;j++)
			{
				if(i==j) dist[i][j]=0;
				else
					dist[i][j]=max;
			}
		for( i = 0; i< dist.length; i++){
			for( j = 0; j<dist.length; j++){
				if(this.vertexList[i]!= null &&this.vertexList[j]!=null
						&& adjMatrix[i][j] != 0)
					dist[i][j] = adjMatrix[i][j];

			}
		}

		for(i=0;i<dist.length;i++)
		{
			D[i]=dist[v1][i];
			if(D[i]!=max) p[i]=v1+1;
			else p[i]=0;
			s[i]=false;
		}
		s[v1]=true;
		for(i=0;i<dist.length; i++)
		{
			min=Integer.MAX_VALUE;
			for(j=0;j<dist.length;j++)

				if((!s[j])&&(D[j]<min))
				{
					min=D[j];
					k=j;
				}
			s[k]=true;
			if(s[w]) break;
			for(j=0;j<dist.length;j++)
				if((!s[j])&&(dist[k][j]<max))
				{int newdist = D[k]+dist[k][j];
					if(newdist<D[j]){
						D[j]=newdist;
						p[j]=k+1;
					}
				}
		}

		buf.append("?????"+D[w]+"?  ?????"+ vertexList[w].getNode());
		pre=p[w];
		while((pre!=0)&&(pre!=v+1))
		{
			buf.append("," + vertexList[(pre-1)].getNode());
			pre=p[pre-1];
		}
		buf.append("," + vertexList[v].getNode());

		return buf.toString();

		//return null;

	}

	private void adjust_sPath() {
		int column = 1;
		while (column < numVerts) {
			if (vertexList[column].isInNode) {
				column++;
				continue;
			}
			int currentToFringe = adjMatrix[currentVertex][column];
			int startToFringe = startToCurrent + currentToFringe;
			int sPathDist = sPath[column].weight;
			if (startToFringe < sPathDist) {
				sPath[column].Node.num = currentVertex;
				sPath[column].weight = startToFringe;
			}
			column++;
		}
	}

	private int getMin() {
		int minDist = INFINITY;
		int indexMin = 0;
		for (int j = 0; j < numVerts; j++) {
			if (!vertexList[j].isInNode && sPath[j].weight < minDist) {
				minDist = sPath[j].weight;
				indexMin = j;
			}
		}
		return indexMin;
	}

	public Vertex[] getVertices() {
		Vertex[] re;
		int count = 0;
		for (int i = 0; i < vertexList.length; i++) {
			if (vertexList[i] != null) {
				count++;
			}
		}
		re = new Vertex[count];

		count = 0;
		for (int i = 0; i < vertexList.length; i++) {
			if (vertexList[i] != null) {
				re[count] = vertexList[i];
				count++;
			}
		}

		return re;
	}

	public Edge[] getEdges() {
		int count = 0;
		for (int i = 0; i < nEdge; i++) {
			if (sPath[i] != null)
				count++;
		}
		Edge[] Edges = new Edge[count];
		System.out.println("count:" + Edges.length);

		count = 0;
		for (int j = 0; j < sPath.length; j++) {
			if (sPath[j] != null) {
				System.out.println("c:" + count);
				Edges[count] = sPath[j];
				count++;
			}
		}
		return Edges;
	}

}
