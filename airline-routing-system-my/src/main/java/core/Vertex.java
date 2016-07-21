package core;

import javax.swing.JOptionPane;

import util.DLinkedList;
import util.Node;
import UI.AirlineUI;
import UI.Position;

public class Vertex{
	public String name;
	private boolean visited = false;
    private Vertex previousV = null;//the previous vertex to this v
	private Position p = new Position();//the (x,y)of the v
    private DLinkedList edgeList = new DLinkedList();//edges lined to this v
    private int cost;//the cost spent to this v
    private boolean isInPath = false;
    public int degree = 0;
	public Vertex(String name){
		this.name = name;
		this.edgeList=new DLinkedList();
		this.visited = false;
		this.cost = Graph.INFINITE;
		this.isInPath = false;
		this.degree = 0;
	}
	public String toString(){
		return name;
		
	}
	public Position computePosition(){
		p.x = name.hashCode()%AirlineUI.p.getWidth();
		p.y = (name.hashCode()*3)%(AirlineUI.p.getHeight());
		return p;
		
	}
	public static Vertex getV(String name){
		if(AirlineUI.map.vertices.findNode(new Vertex(name))!=null){
			return (Vertex)AirlineUI.map.vertices.findNode(new Vertex(name)).element;
		}
		else {
			JOptionPane.showMessageDialog(null, "No such Vertex");
			return null;
		}
		
		
	}
	public boolean equal(Vertex anotherV){
		boolean result = false;
		if(anotherV == null){
			result = false;
		}
		else{
			if(anotherV.name.equals(name)){
				result = true;
			}
		}
		return result;
	}
	public boolean hasNeighbour(){
		return !edgeList.isEmpty();
	}
	public void init(){		
	    this.previousV = null;
		this.visited = false;
		this.cost = Graph.INFINITE;
		this.isInPath = false;
	}
    
	/*public Vertex getUnvisitedNeighbour(){
		Vertex result = null;
		while()
	}*/ 
	/*public int computeOrder(){
		int order = 0;
		Node thisNodeV = AirlineUI.map.vertices.header.next;
		Vertex thisV = (Vertex)thisNodeV.element;
		while(thisNodeV.next != null){
			if(thisV.name.equals(name)){
				return order;
			}
			order++;
			//drawNode(thisV.name, thisV.computePosition().x, thisV.computePosition().y, g);
			thisNodeV = thisNodeV.next;
			thisV = (Vertex)thisNodeV.element;
		}
		
		return -1;
		
	}*/
	public void setVisited(boolean visited) {
		this.visited = visited;
	}
	public boolean isVisited() {
		return visited;
	}
	public void setPreviousV(Vertex previousV) {
		this.previousV = previousV;
	}
	public Vertex getPreviousV() {
		return previousV;
	}
	public void setCost(int cost) {
		this.cost = cost;
	}
	public int getCost() {
		return cost;
	}
	public void setEdgeList(DLinkedList edgeList) {
		this.edgeList = edgeList;
	}
	public DLinkedList getEdgeList() {
		return edgeList;
	}
	public void connect(Vertex anotherEnd,int weight){
    	Edge newEdge = new Edge(this,anotherEnd,weight);    	
    	edgeList.insertNode(newEdge);
    	anotherEnd.edgeList.insertNode(newEdge);  	
    }    
    public void deleteE(Edge e){  
    	if(getV(e.startV.toString())==null||getV(e.endV.toString())==null){
    		JOptionPane.showMessageDialog(null,"No such Edge,Because no such end Vertex!");
    		return;
    	}
    	getV(e.startV.toString()).getEdgeList().deleteNode(e);
    	getV(e.endV.toString()).getEdgeList().deleteNode(e);

    }
    public boolean clearE(){
    	boolean result = true;
		Node thisNodeE = getEdgeList().header.next;
		Edge thisE = (Edge)thisNodeE.element;
		while(thisE != null){
			deleteE(thisE);
			thisNodeE = thisNodeE.next;
			thisE = (Edge)thisNodeE.element;
		}
    	return result;
    }
    public DLinkedList getNeighbour(){
    	DLinkedList neighbourV = new DLinkedList();
    	Node thisNodeE = getEdgeList().header.next;
		Edge thisE = (Edge)thisNodeE.element;
		while(thisE != null){			
			
			neighbourV.insertNode(thisE.endV);
			thisNodeE = thisNodeE.next;
			thisE = (Edge)thisNodeE.element;
		}
		return neighbourV;
    	
    }
	public void setInPath(boolean isInPath) {
		this.isInPath = isInPath;
	}
	public boolean isInPath() {
		return isInPath;
	}

	
	
}
