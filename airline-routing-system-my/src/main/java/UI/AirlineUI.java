package UI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.*;
import javax.swing.border.TitledBorder;


import util.Stack;

import core.Graph;
import core.Vertex;




public class AirlineUI extends JFrame{	
	public static Graph map = new Graph();
	static AirlineUI frame;
	public static File file;
	static JPanel jpCenter = new JPanel();     
    static JPanel jpEast = new JPanel();                           
    static JPanel [] jpEasts = new JPanel[3];         
    static ImageIcon iconMap;
    static ImageIcon iconPlane;
    static ImageIcon iconAirport;
    static ImageIcon iconDelAirport;
    static ImageIcon iconAirline;
    static ImageIcon iconDelAirline;
    public static Image imagePlane;
    static JLabel jlbPlane;
    //buttons in the east
    static JButton jbAddV;
    static JButton jbDelV;
    static JButton jbAddE;
    static JButton jbDelE;
    static JButton jbImportMap = new JButton("Import map");
    static JButton jbShortestPath = new JButton("Compute Shortest Path");
	static ClassLoader classLoader;
    public static JPanel p = new GraphPanel();
    public AirlineUI(){
		classLoader= getClass().getClassLoader();
    	setLayout(new BorderLayout());
    	add(jpCenter, "Center");
    	add(jpEast, "East");
		file=new File(classLoader.getResource("Airlines/Airline.txt").getFile());
		System.out.print(file.getAbsolutePath());
		iconMap = new ImageIcon(classLoader.getResource("image/map.png").getFile());
		iconPlane = new ImageIcon(classLoader.getResource("image/plane.png").getFile());
		iconAirport = new ImageIcon(classLoader.getResource("image/airport.png").getFile());
		iconDelAirport = new ImageIcon(classLoader.getResource("image/DELairport.png").getFile());
		iconAirline = new ImageIcon(classLoader.getResource("image/airline.png").getFile());
		iconDelAirline = new ImageIcon(classLoader.getResource("image/DELairline.png").getFile());
		imagePlane = iconPlane.getImage();
		jlbPlane = new JLabel(iconPlane);
		jbAddV = new JButton(iconAirport);
		jbDelV = new JButton(iconDelAirport);
		jbAddE = new JButton(iconAirline);
		jbDelE = new JButton(iconDelAirline);
		//east panel
	    jpEast.setLayout(new GridLayout(3,1));
	        for (int k = 0;k <= 2;k++){
	    	    jpEasts[k] = new JPanel(); 
	    	    jpEasts[k].setLayout(new GridLayout(2,1));
	    	    jpEasts[k].setBackground(new Color(35 * k,255,35 * k));
	    	    jpEast.add(jpEasts[k]);
	        }
	        jpEasts[0].setBorder(new TitledBorder("airports"));
	            jpEasts[0].add(jbAddV);
	            jpEasts[0].add(jbDelV);
	        jpEasts[1].setBorder(new TitledBorder("airlines"));
                jpEasts[1].add(jbAddE);
                jpEasts[1].add(jbDelE); 	
	        jpEasts[2].setBorder(new TitledBorder("main"));
                jpEasts[2].add(jbImportMap);  
                jpEasts[2].add(jbShortestPath);
           jpCenter.add(p);           
           jbAddV.addActionListener(new ActionListener() {
   			   public void actionPerformed(ActionEvent arg0) {			
   				   String newVName = JOptionPane.showInputDialog("Please Input the Name of new Airport");
   				   if(newVName!=null){
   					   map.addV(newVName);
   					   Graph.writeG();
   				   }
   				   
   				   p.repaint();
   			   }
   		   });
   		   jbDelV.addActionListener(new ActionListener() {
   			    public void actionPerformed(ActionEvent arg0) {	
    			    String newVName = JOptionPane.showInputDialog("Please Input the Name of the Airport");
       				if(newVName!=null){
       					map.deleteV(newVName);
       					Graph.writeG();
       				}
       				
   					p.repaint();		
   			    }
   		   });
           jbAddE.addActionListener(new ActionListener() {
   			   public void actionPerformed(ActionEvent arg0) {			
  			    	String eToString = JOptionPane.showInputDialog("Please Input the Name of the Airline(in the form of 'name1 name2 weight' )");
  			        if(eToString!=null){
  			        	map.addE(eToString);
  			        	Graph.writeG();
     				}
 			    	
  			    	
   			    	p.repaint();
   			   }
   		   });
   		   jbDelE.addActionListener(new ActionListener() {			
   			    public void actionPerformed(ActionEvent arg0) {
   			    	String eToString = JOptionPane.showInputDialog("Please Input the Name of the Airline(in the form of 'name1 name2 weight' )");
                    if(eToString!=null){
       					
                    	map.deleteE(eToString);
                    	Graph.writeG();
       				}   			    
   			    	p.repaint();
   							
   			    }
   		   });

	       jbImportMap.addActionListener(new ActionListener() {			
	   			public void actionPerformed(ActionEvent arg0) {
	   				JFileChooser openChooser = new JFileChooser(new File("Airlines"));
	   				openChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
	   				int returnVal = openChooser.showOpenDialog(null);
	   				if(returnVal == JFileChooser.APPROVE_OPTION) {
	   					File openFile = openChooser.getSelectedFile();	
	   				    map.readGraph(openFile);
	   				    p.repaint();
	   				}			
	   			}
	   		});
	       jbShortestPath.addActionListener(new ActionListener() {			
	   			public void actionPerformed(ActionEvent arg0) {
	   				String str = JOptionPane.showInputDialog("Please Input the Name of the Airports(in the form of name1 name2");
	   				if(str!=null){
	   					String[]tmps = str.split(" ");
		   				Vertex start = (Vertex) map.vertices.findNode(new Vertex(tmps[0])).element;
		   				Vertex end = (Vertex) map.vertices.findNode(new Vertex(tmps[1])).element;
		   				p.repaint();
		   				int answer = map.dijkstra(start,end);
		   				if(answer==Graph.INFINITE){
		   				    JOptionPane.showMessageDialog(null, "graph isn't connected");	
		   				}
		   				else{
		   					System.out.print("ANSWER:"+answer);
		   				}
		   					
		   			    
	   				}
	   				
	   			}
	   		});
	      
    }
	
    
	
	public void flyBetween(Vertex start,Vertex end){
		int xBias = (end.computePosition().x-start.computePosition().x)/10;
    	int yBias =  (end.computePosition().y-start.computePosition().y)/10;
		
    	jlbPlane.setBounds(start.computePosition().x, start.computePosition().y, 30, 30);
    	
    	int count = 0;
    	while(jlbPlane.getX()-end.computePosition().x>30||jlbPlane.getY()-end.computePosition().y>30){	
    		count++;
    		p.add(jlbPlane);
    		jlbPlane.setBounds(start.computePosition().x+count*xBias,start.computePosition().y+count*yBias,30,30);
    		p.repaint();
    		try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}
    
    	}
    	return;	
	   
	}
    public static void main(String[]args){    	
    	
    	frame = new AirlineUI();
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	frame.setBounds(100,0,900,700);
    	//frame.setResizable(false);
    	frame.setVisible(true);
    	frame.setTitle("Airline System");
    }
    
}
