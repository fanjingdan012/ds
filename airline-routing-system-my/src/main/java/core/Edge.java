package core;

public class Edge{
	public Vertex startV;
	public Vertex endV;
	private int weight;
	private boolean written = false;
	public Edge(Vertex start,Vertex end,int weight){
		this.startV = start;
		this.endV = end;
		this.weight = weight;
	}
	public Edge(String startNameOfV, String endNameOfV,int weight) {
		this.startV = new Vertex(startNameOfV);
		this.endV = new Vertex (endNameOfV);
		this.weight = weight;
	}
	public Edge() {
	}
	public String toString(){
		return startV.name+" "+endV.name+" "+weight;
		
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	public int getWeight() {
		return weight;
	}
	public boolean equal(Edge anotherE){
		boolean result = false;
		if(anotherE == null){
			result = false;
			
		}
		else{
			System.out.println(anotherE+" vs "+this);
			if(((anotherE.startV.equal(this.startV))&&(anotherE.endV.equal(this.endV)))||((anotherE.startV.equal(this.endV))&&(anotherE.endV.equal(this.startV)))){
				result = true;
				System.out.println(anotherE+"equals"+this);
			}
		}
		return result;
	}
	public void setWritten(boolean written) {
		this.written = written;
		Edge otherE = (Edge)Vertex.getV(endV.toString()).getEdgeList().findNode(new Edge(endV,startV,weight)).element;
		if(otherE.isWritten()!=written){
			otherE.setWritten(written);
		}
		
	}
	public boolean isWritten() {
		return written;
	}


}