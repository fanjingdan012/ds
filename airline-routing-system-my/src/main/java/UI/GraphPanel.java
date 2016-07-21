package UI;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;
import javax.swing.Timer;


import util.Node;
import util.Stack;


import core.Edge;
import core.Graph;
import core.Vertex;

public class GraphPanel extends JPanel{
	public int width = 0;
	public int height = 0;
	public GraphPanel(){
	
	}

    public void paintComponent(Graphics g){
       	setSize(getParent().getSize());
		this.setBackground(Color.YELLOW);
	    super.paintComponent(g);
	    width = getSize().width;
		height = getSize().height;
		this.setBounds(0, 0, width, height);
		g.drawImage(AirlineUI.iconMap.getImage(), 0, 0, width, height, null);
		Node thisNodeV = AirlineUI.map.vertices.header.next;
		Vertex thisV = (Vertex)thisNodeV.element;
		while(thisNodeV.next != null){
			
			Node thisNodeE = thisV.getEdgeList().header.next;
			Edge thisE = (Edge)thisNodeE.element;
			while(thisE != null){			
				
				if(Vertex.getV(thisE.startV.toString()).isInPath()&&Vertex.getV(thisE.endV.toString()).isInPath()){
					drawLinker(thisE.startV, thisE.endV,thisE.getWeight(),Color.red, g);
				}
				else{
					drawLinker(thisE.startV, thisE.endV,thisE.getWeight(),Color.black, g);
				}
				
				thisNodeE = thisNodeE.next;
				thisE = (Edge)thisNodeE.element;
			}
			if(thisV.isInPath()){
				drawNode(thisV.name, thisV.computePosition().x, thisV.computePosition().y,Color.red, g);
			}
			else{
				
				drawNode(thisV.name, thisV.computePosition().x, thisV.computePosition().y,Color.black, g);
			}
			thisNodeV = thisNodeV.next;
			thisV = (Vertex)thisNodeV.element;
			
		}
	}
	public void drawNode(String s,int x,int y,Color c,Graphics g){
		g.setColor(c);
		g.fillRoundRect(x-25, y-15, 50, 30, 20, 20);
		g.setColor(Color.WHITE);
		g.drawString(s, x - 10, y);	
	}
	public void drawLinker(Vertex v1,Vertex v2,int weight ,Color c,Graphics g){
		g.setColor(c);
		Position p1 = v1.computePosition();
		Position p2 = v2.computePosition();
		g.drawLine(p1.x,p1.y,p2.x,p2.y);
		g.drawString(weight+"",( p1.x+p2.x)/2, ( p1.y+p2.y)/2);
	}

	


}
