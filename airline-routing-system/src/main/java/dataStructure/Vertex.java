package dataStructure;

public class Vertex {
    String node;
    boolean isInNode;
    int num;
    
    public Vertex(String node){
    	this.node= node;
    	this.isInNode =false;
    }
    
    public Vertex(String node, int num){
    	this.node = node;
    	this.num = num;
    	isInNode = false;
    }
    public String getNode(){
    	return node;
    }
    public int getNum(){
    	return num;
    }
    public void setVertex(String node, int num){
    	this.node =  node;
    	this.num = num;
    }
   
}
