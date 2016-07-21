package core;

import java.awt.Color;
import java.awt.Graphics;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

import UI.AirlineUI;
import UI.EmptyStackException;
import UI.TimerFlashListener;

import util.DLinkedList;
import util.Node;
import util.Queue;
import util.Stack;

public class Graph {
	public int numOfV;
	public int numOfE;
	public DLinkedList vertices = new DLinkedList();
    public static final int INFINITE = 100000000;
    public int [][] adjacentMatrix;
    public Stack path = new Stack();

	public Graph() {	
		numOfV = 0;
		numOfE = 0;		
	}
	public Graph(DLinkedList vertices,DLinkedList edges){
		numOfV = vertices.length;
		numOfE = edges.length;
		
		
	}
	public void clear(){
		vertices.clear();		
	}
	public void resetVertices(){
		Node thisNode = AirlineUI.map.vertices.header.next;
		Vertex thisV = (Vertex)thisNode.element;
		while(thisV != null){			
			thisV.init();
			thisNode = thisNode.next;
			thisV = (Vertex)thisNode.element;
		}
	}
	public Vertex [] changeForm(){
		int count = 0;
		Vertex [] arrV = new Vertex[vertices.length];
		Node thisNodeV = AirlineUI.map.vertices.header.next;
		Vertex thisV = (Vertex)thisNodeV.element;
		while(thisNodeV.next != null){
			arrV[count] = thisV;
			count++;
			thisNodeV = thisNodeV.next;
			thisV = (Vertex)thisNodeV.element;
		}
		return arrV;
		
	}

	public int dijkstra(Vertex start,Vertex end){
		resetVertices();
		boolean done = false;
    	Queue vertexQueue = new Queue(vertices.length);
    	start.setCost(0);
    	start.setVisited(true);
    	vertexQueue.enqueue(start);
    	while(!done && !vertexQueue.isEmpty()){
    		Vertex frontVertex = Vertex.getV(((Vertex) vertexQueue.dequeue()).toString());
    		frontVertex.setVisited(true);    		
    		DLinkedList edges = frontVertex.getEdgeList();    	
    		Node currentEdgeNode = edges.header.next;
    		Edge currentEdge =  (Edge)currentEdgeNode.element;
    		int minWeight = Graph.INFINITE;
    		Edge minEdge = null;
            //one round :find min weighted edge linked to front Vertex
    		while(!done&&(currentEdge!=null)){    			
    			Vertex currentNeighbour = Vertex.getV(currentEdge.endV.toString());
    			if(!currentNeighbour.isVisited()){
    				if(currentEdge.getWeight()<minWeight){
    					minEdge = currentEdge;
    					minWeight = minEdge.getWeight();
    				}    				
    			}
    			
    			if((currentEdge.getWeight()+frontVertex.getCost())<currentNeighbour.getCost()){
					
					currentNeighbour.setPreviousV(frontVertex);
					
					currentNeighbour.setCost(currentEdge.getWeight()+frontVertex.getCost());
				}
    			
    			currentEdgeNode = currentEdgeNode.next;
    			currentEdge =  (Edge)currentEdgeNode.element;
    			
    		}
    		//check if there is Vertex not visited
    		Node check = AirlineUI.map.vertices.header.next;
    		Vertex checkV = (Vertex)check.element;
    		done = true;
    		while(check.next != null){
    			if(checkV.isVisited()==false){
    				done = false;
    			}
    			
    			check = check.next;
    			checkV = (Vertex)check.element;
    		}
    		if(minEdge!=null)
    			vertexQueue.enqueue(minEdge.endV);
    		else{
    			if(frontVertex.getPreviousV().equal(start)){
    				return INFINITE;
    			}
    			vertexQueue.enqueue(frontVertex.getPreviousV());
    			
    		}
    	}
    	int pathLength = (int)end.getCost();    	
    	Vertex vInPath = end;
    	vInPath.setInPath(true);  
    	while(vInPath !=null){
    		Vertex.getV(vInPath.toString()).setInPath(true);
    		vInPath = vInPath.getPreviousV();
    	}
    	return pathLength;		
	}
 
    public void addV(Vertex v){
    	if(vertices.findNode(v)==null){
    		vertices.insertNode(v);	
    	}
    	else{
    		JOptionPane.showMessageDialog(null, "already have this Vertex!");
    	}
    	
    }
    public void addV(String nameOfV){
    	Vertex v = new Vertex(nameOfV);
    	if(vertices.findNode(v)==null){
    		vertices.insertNode(v);	
    	}
    	else{
    		JOptionPane.showMessageDialog(null, "already have this Vertex!");
    	}
    }
    

    public void deleteV(Vertex v){
    	if(vertices.findNode(v)==null){
    		JOptionPane.showMessageDialog(null,"No such Vertex!");
    		return;
    	}
    	v.clearE();
        vertices.deleteNode(v);
    	
    }
    public void deleteV(String nameOfV){
    	if(vertices.findNode(new Vertex (nameOfV))==null){
    		JOptionPane.showMessageDialog(null,"No such Vertex!");
    		return;
    	}
    	String information = "";
    	Vertex v = (Vertex)Vertex.getV(nameOfV);
		Node thisNodeE = v.getEdgeList().header.next;
		Edge thisE = (Edge)thisNodeE.element;
		while(thisE != null){			
			information+=thisE.toString()+", ";
			thisNodeE = thisNodeE.next;
			thisE = (Edge)thisNodeE.element;
		}
		if(information.equals("")){
			Vertex.getV(nameOfV).clearE();
		     vertices.deleteNode(new Vertex (nameOfV));
			return;
    	}
    	int returnVal = JOptionPane.showConfirmDialog(null, "Airline "+information+" will be deleted,if you delete this airport.");
		if(returnVal == JOptionPane.OK_OPTION) {
			Vertex.getV(nameOfV).clearE();
		     vertices.deleteNode(new Vertex (nameOfV));
		}
       
    	
    }
    public void deleteE(String eToString){
    	String[]tmps = eToString.split(" "); 
    	if(tmps.length!=3){
    		JOptionPane.showMessageDialog(null, "wrong input");
    		return;
    	}
    	if(vertices.findNode(new Vertex (tmps[0]))==null||vertices.findNode(new Vertex (tmps[1]))==null){
    		JOptionPane.showMessageDialog(null,"No such Edge,Because no such end Vertex!");
    		return;
    	}
    	String information = "";
    	if(Vertex.getV(tmps[0]).getEdgeList().length==1){
    		information+=tmps[0]+",";   		
    	}
    	if(Vertex.getV(tmps[1]).getEdgeList().length==1){
    		information+=tmps[1];  		
    	}
    	if(information.equals("")){
    		Vertex.getV(tmps[0]).getEdgeList().deleteNode(new Edge(tmps[0],tmps[1],Integer.parseInt(tmps[2])));//deleteE(new Edge(tmps[0],tmps[1],Integer.parseInt(tmps[2])));//
	    	Vertex.getV(tmps[1]).getEdgeList().deleteNode(new Edge(tmps[1],tmps[0],Integer.parseInt(tmps[2])));//deleteE(new Edge(tmps[0],tmps[1],Integer.parseInt(tmps[2])));//
	    	return;
    	}
    	int returnVal = JOptionPane.showConfirmDialog(null,"Airport "+information+" will have no flight,if you delete this airline");
		if(returnVal == JOptionPane.OK_OPTION) {
			Vertex.getV(tmps[0]).getEdgeList().deleteNode(new Edge(tmps[0],tmps[1],Integer.parseInt(tmps[2])));//deleteE(new Edge(tmps[0],tmps[1],Integer.parseInt(tmps[2])));//
	    	Vertex.getV(tmps[1]).getEdgeList().deleteNode(new Edge(tmps[1],tmps[0],Integer.parseInt(tmps[2])));//deleteE(new Edge(tmps[0],tmps[1],Integer.parseInt(tmps[2])));//
		}
  
    }

    public void addE(String eToString){
    	String[]tmps = eToString.split(" ");    	
    	
    	if(tmps.length!=3){
    		JOptionPane.showMessageDialog(null, "wrong input");
    		return;
    	}
    	
    	if(Vertex.getV(tmps[0])!=null&&Vertex.getV(tmps[1])!=null){
    		Vertex.getV(tmps[0]).getEdgeList().insertNode( new Edge(tmps[0],tmps[1],Integer.parseInt(tmps[2])));
    		Vertex.getV(tmps[1]).getEdgeList().insertNode( new Edge(tmps[1],tmps[0],Integer.parseInt(tmps[2])));
    	}
    	else{
    		JOptionPane.showMessageDialog(null, "Can not add "+eToString);
    	}
    
    }
    public Graph readGraph(File file){
        try{    
        	clear();
			FileInputStream fileInput = new FileInputStream(file);
    		Scanner input = new Scanner(file);
		    String tmp = "";
			while(input.hasNext()){
				tmp = input.nextLine();
				String [] tmps = tmp.split(" ");
				if(tmps[0].equals("VERTEX")){
					addV(new Vertex(tmps[1]));
				}
				else if(tmps[0].equals("EDGE")){
					addE(tmps[1]+" "+tmps[2]+" "+tmps[3]);
					
				}
			}
			
			input.close();						
			fileInput.close();
			AirlineUI.file = file;
			
			   	    
    	}
    	catch(IOException ex) {	
			System.out.println("wrong");
		}   
    	
		return null;
    	
    	
    }

    public boolean isEmpty(){
    	return vertices.isEmpty();
    }
    public static void writeG(){
    	PrintWriter generalSaver;
		try {
			generalSaver = new PrintWriter(AirlineUI.file);
			String buffer = "";
			Node thisNodeV = AirlineUI.map.vertices.header.next;
			Vertex thisV = (Vertex)thisNodeV.element;
			while(thisNodeV.next != null){			
				generalSaver.println("VERTEX "+thisV);
				Node thisNodeE = thisV.getEdgeList().header.next;
				Edge thisE = (Edge)thisNodeE.element;
				while(thisE != null){			
					
					thisE.setWritten(false);
					thisNodeE = thisNodeE.next;
					thisE = (Edge)thisNodeE.element;
				}
				thisNodeV = thisNodeV.next;
				thisV = (Vertex)thisNodeV.element;
				
			}
			Node thisNodeV1 = AirlineUI.map.vertices.header.next;
			Vertex thisV1 = (Vertex)thisNodeV1.element;
			while(thisNodeV1.next != null){
				
				Node thisNodeE = thisV1.getEdgeList().header.next;
				Edge thisE = (Edge)thisNodeE.element;
				while(thisE != null){			
					
					if(!thisE.isWritten()){
						generalSaver.println("EDGE "+thisE);
					
						thisE.setWritten(true);
					}
					
					thisNodeE = thisNodeE.next;
					thisE = (Edge)thisNodeE.element;
				}
				
				
				thisNodeV1 = thisNodeV1.next;
				thisV1 = (Vertex)thisNodeV1.element;
				
			}
			
			generalSaver.close();
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}
    	
    }
    public void printG(){
    	Node thisNode = vertices.header.next;
		Vertex thisV = (Vertex)thisNode.element;
		
		while(thisV != null){			
			System.out.print(thisV);
			
			Node thisNodeE = thisV.getEdgeList().header.next;
			Edge thisE = (Edge)thisNode.element;
			while(thisE != null){			
				System.out.println(thisE);
			
				thisNodeE = thisNode.next;
				thisE = (Edge)thisNode.element;
			}
			System.out.println();
			thisNode = thisNode.next;
			thisV = (Vertex)thisNode.element;
		}
    }


 


}



