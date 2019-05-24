package dlinkedlist;


import graph.Edge;
import graph.Vertex;


public class DLinkedList {
	public int length = 0;
	public Node header = new Node(null, null, null);
	public Node trailer = new Node (null, null, null);
    
	public DLinkedList() {
		length = 0;// TODO Auto-generated constructor stub
		header.next = trailer;
		trailer.prev = header;
	}
	public void insertNode(Object element){		
		Node<Object> newNode = new Node<Object>(element,trailer.prev,trailer);
    	trailer.prev.next = newNode;
    	trailer.prev = newNode;    	
    	length++;   	
	}
    public void deleteNode(Vertex element){
    	
    	Node node = findNode(element);
    	if(node!=null){
    		node.next.prev = node.prev;
        	node.prev.next = node.next;
        	node = null;
        	length--;
    	}
    	
    	return;
    }
    public void deleteNode(Edge element){
    	
    	Node node = findNode(element);
    	if(node!=null){
    		node.next.prev = node.prev;
        	node.prev.next = node.next;
        	node = null;
        	length--;
    	}
    	
    	return;
    }

    public Node findNode(Vertex o){
    	Node thisNode = header.next;
		Object thisO = thisNode.element;
		
		
		while(thisNode.next != null){
			
			if(((Vertex)thisO).equal((Vertex)o)){
			
				return thisNode;
			}
			thisNode = thisNode.next;
			thisO = thisNode.element;
		}
		
		return null;
    	
    }
    public Node findNode(Edge o){
    	Node thisNode = header.next;
		Object thisO = thisNode.element;
		while(thisNode.next != null){			
			if(((Edge)thisO).equal(o)){
			
				return thisNode;
			}
			thisNode = thisNode.next;
			thisO = thisNode.element;
		}
		
		return null;
    	
    }
 
    public boolean isEmpty(){
    	return (length == 0);
    }
    public void clear(){
		length = 0;
		header.next = trailer;
		trailer.prev = header;
    }
  
}