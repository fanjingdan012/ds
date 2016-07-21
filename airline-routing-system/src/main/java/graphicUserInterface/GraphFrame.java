package graphicUserInterface;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List; //import java.util.Map;

import javax.swing.*;

import dataStructure.Edge;
import dataStructure.Graph;
import dataStructure.Vertex;

import io.*;

//?????  ???Graphics   ????

public class GraphFrame extends JFrame implements ActionListener {

	/**
	 * listed below are private components
	 */
	private static final long serialVersionUID = 1L;

	private GraphPanel base;
	private JPanel side;
	private final JSplitPane split = new JSplitPane();

	// ???????????
	private String currentFilePath = "";

	// The points map:
	// private Map<Vertex, Coordinate> points;

	// the function buttons:
	private JButton load;
	private JButton save;
	private JButton mode;
	private JButton about;
	private JButton add;
	private JButton delete;

	// provide a button list to manage all the buttons at the same time:
	private JButton[] buttonList;

	@SuppressWarnings( { "unchecked" })
	public GraphFrame() {
		super("Airline Routing System");
		this.setLayout(new BorderLayout());

		String lookAndFeel = UIManager.getSystemLookAndFeelClassName();
		try {
			UIManager.setLookAndFeel(lookAndFeel);
		} catch (Exception e) {
			e.printStackTrace();
		}

		try
		{
			String[] info = new String[]
					{ "?????????...","????????...","???????..."};
			SplashScreen splash = SplashScreen.getSplashScreen();
			Graphics g = splash.createGraphics();
			if (splash != null)
			{   g.setColor(Color.WHITE);
				g.drawString("VERSION: 5.0", 70,100 );
				g.drawString("Author: ???",70,120);
				splash.update();
				for (int i = 0; i < 3; i++)
				{
					g.setColor(Color.WHITE);
					g.drawString(info[i], 0, 220 + i * 15);

					splash.update();
					try {
						Thread.sleep((i + 1)*1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}

			}
		}
		catch (Exception e)
		{System.out.println("????????");}

		base = new GraphPanel();
		// base.setBackground(Color.white);
		side = new JPanel();
		side.setOpaque(true);
		side.setBackground(new Color(116, 149, 226));
		side.setLayout(new FlowLayout());// 3 rows , 1 column, 5 for gap

		// initialize buttons:
		buttonList = new JButton[6];

		load = new JButton("Load");
		save = new JButton("Save");
		mode = new JButton("Flight");
		about = new JButton("About..");
		add = new JButton("Add ");
		delete = new JButton("Delete");

		buttonList[0] = load;
		buttonList[1] = save;
		buttonList[2] = mode;
		buttonList[3] = about;
		buttonList[4] = add;
		buttonList[5] = delete;
		for (int i = 0; i < buttonList.length; i++) {
			buttonList[i].addActionListener(this);
			buttonList[i].setForeground(Color.red);
			// buttonList[i].setFont(new Font(""));
		}

		List panelList = makeList();
		for (Iterator it = panelList.iterator(); it.hasNext();) {
			AccordionPanel ap = (AccordionPanel) it.next();
			side.add(ap);
		}

		base.setPreferredSize(new Dimension(400, 400));
		side.setPreferredSize(new Dimension(200, 260));
		side.setMinimumSize(new Dimension(120, 160));

		split.setLeftComponent(side);
		split.setRightComponent(base);

		split.setDividerSize(3); // the size of the gap
		split.setBackground(Color.black);

		add(split, BorderLayout.CENTER);

		// Try to set the window located in the center of screen:
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension frameSize = this.getPreferredSize();
		this.setLocation((screenSize.width - frameSize.width) / 2,
				(screenSize.height - frameSize.height) / 2);

		this.setSize(600, 400);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == load) { // load a graph file:
			JFileChooser chooser = new JFileChooser("./");
			chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
			int r = chooser.showOpenDialog(null);
			// ??????
			if (r == JFileChooser.APPROVE_OPTION) {
				File file = chooser.getSelectedFile();
				this.currentFilePath = file.getPath();
				Graph my = new Graph();
				IOprocess x = new IOprocess();
				x.readMyFile(file);

				String[] vnum = new String[x.vlist.size()];
				// Point [] pos = new Point[x.vlist.size()];
				// ?????
				for (int i = 0; i < x.vlist.size(); i++) {
					my.addVertex(x.vlist.get(i).getNode(), i);
					vnum[i] = x.vlist.get(i).getNode();
					// pos[i] = x.vlist.get(i).getPos();
				}

				// ????????????
				for (int i = 0; i < x.elist.size(); i++) {
					String node1 = x.elist.get(i).node1;
					String node2 = x.elist.get(i).node2;
					int place1 = 0, place2 = 0;

					for (int j = 0; j < vnum.length; j++) {
						if (node1.equals(vnum[j])) {
							place1 = j;
							System.out.println("p1:" + place1);
						}
						if (node2.equals(vnum[j])) {
							place2 = j;
							System.out.println("p2:" + place2);
						}
					}

					int weight = x.elist.get(i).weight;
					my.addEdge(place1, place2, weight);

				}

				/** Then, draw the graph: */
				base.setGraph(my);
				// base.setPos(pos);
				base.repaint();

			}

		}

		/** Save the graph to a file */
		else if (e.getSource() == save) {
			if (this.base.getGraph() == null) {
				JOptionPane.showMessageDialog(null, "?????????");
			} else {
				Vertex[] vs = this.base.getGraph().getVertices();
				Edge[] es = this.base.getGraph().getEdges();
				IOprocess io = new IOprocess();
				String name = JOptionPane.showInputDialog("???????");
				io.writeFile(vs, es, name);
				JOptionPane.showMessageDialog(null, "???????" + name);
			}
		}

		else if (e.getSource() == add) {
			/** Add an airport or flight: */
			String cmd = JOptionPane.showInputDialog(null, "?????????\n"
					+ "VERTEX NAME\n?EDGE P1 P2 WEIGHT");
			if (cmd != null) {
				String[] words = cmd.split(" ");
				if (words.length != 2 && words.length != 4) {
					JOptionPane.showMessageDialog(null, "?????????");
				} else {
					if (words[0].equals("VERTEX")) {
						if (words[1].equals("")) {
							JOptionPane.showMessageDialog(null, "???????");
						} else {
							if (base.getGraph() == null) {
								JOptionPane.showMessageDialog(null, "????????");
							} else {
								Graph temp = base.getGraph();
								temp.addVertex(words[1], temp.getVnum() + 1);
								base.setGraph(temp);
								// ?????:
								Vertex[] vs = this.base.getGraph()
										.getVertices();
								Edge[] es = this.base.getGraph().getEdges();
								IOprocess io = new IOprocess();
								String name = this.currentFilePath;
								System.out.println("path:" + name);
								io.writeFile(vs, es, name);
								base.repaint();
							}
						}
					}

					else if (words[0].equals("EDGE")) {

						String p1 = words[1];
						String p2 = words[2];
						int weight = Integer.parseInt(words[3]);
						Graph temp = base.getGraph();
						int num = temp.getVnum();
						int i1 = 0, i2 = 0;
						boolean f1 = false, f2 = false;

						for (int i = 0; i < num; i++) {
							if (temp.getVertices()[i].getNode().equals(p1)) {
								i1 = i;
								f1 = true;
							}
							if (temp.getVertices()[i].getNode().equals(p2)) {
								i2 = i;
								f2 = true;
							}
						}

						if (!(f1 && f2)) {
							JOptionPane.showMessageDialog(null, "??????");
						} else {
							temp.addEdge(i1, i2, weight);
							base.setGraph(temp);
							Vertex[] vs = this.base.getGraph().getVertices();
							Edge[] es = this.base.getGraph().getEdges();
							IOprocess io = new IOprocess();
							String name = this.currentFilePath;
							System.out.println("path:" + name);
							io.writeFile(vs, es, name);
							base.repaint();

						}

					} else
						JOptionPane.showMessageDialog(null, "???????");
				}
			}
		}

		/** ??????? */
		else if (e.getSource() == delete) {
			String cmd = JOptionPane.showInputDialog(null, "?????" + "?????\n"
					+ "???????????\nNAME1 NAME2");
			if (cmd != null) {
				String[] words = cmd.split(" ");
				System.out.print(words.length);
				if (words.length == 1) {// ?
					if (base.getGraph() == null) {
						JOptionPane.showMessageDialog(null, "????????");
					} else {
						Vertex[] v = base.getGraph().getVertices();
						boolean matchFound = false;
						Edge[] edges = base.getGraph().getEdges();
						for (int i = 0; i < v.length; i++) {
							if (cmd.equals(v[i].getNode())) {
								matchFound = true;
								String s = v[i].getNode();
								Graph temp = base.getGraph();
								String toBeShown = "";
								// ????????????????????
								for (int x = 0; x < edges.length; x++) {
									if ((edges[x].getNode().getNode().equals(s))
											|| (edges[x].getnextNode()
											.getNode().equals(s))) {
										toBeShown = "?"
												+ edges[x].getNode().getNode()
												+ "->"
												+ edges[x].getnextNode()
												.getNode() + "????" + s
												+ "????";
										base.addWarning(toBeShown);
										temp.deleteEdge(edges[x].getNode(),
												edges[x].getnextNode(), 1);
									}
								}

								boolean flag = false;
								// ???????????????
								for (int a = 0; a < v.length; a++) {
									for (int b = 0; b < edges.length; b++) {
										if (edges[b].getNode().getNode()
												.equals(v[a].getNode()))
											flag = true;
										if (edges[b].getnextNode().getNode()
												.equals(v[a].getNode()))
											flag = true;
									}
									if (flag != true) {
										toBeShown = "Airport " + v[a].getNode()
												+ " is unreachable!";
										base.addWarning(toBeShown);
									}
									flag = false;
								}

								temp.deleteVertex(i);
								base.setGraph(temp);
								Vertex[] vs = this.base.getGraph()
										.getVertices();
								Edge[] es = this.base.getGraph().getEdges();
								IOprocess io = new IOprocess();
								String name = this.currentFilePath;
								System.out.println("path:" + name);
								io.writeFile(vs, es, name);

								base.repaint();
								break;
							}
						}
						if (matchFound == false) {
							JOptionPane.showMessageDialog(null, "??????");
						}

					}
				} else if (words.length == 2) {
					// ???
					String p1 = words[0];
					String p2 = words[1];
					Graph temp = base.getGraph();
					int pos1 = 0, pos2 = 0;
					Vertex[] v = temp.getVertices();
					boolean b1 = false, b2 = false;
					for (int i = 0; i < v.length; i++) {
						if (v[i].getNode().equals(p1)) {
							pos1 = i;
							b1 = true;
						}
						if (v[i].getNode().equals(p2)) {
							pos2 = i;
							b2 = true;
						}
					}
					if (b1 && b2) {
						temp.deleteEdge(v[pos1], v[pos2], 1);
						base.setGraph(temp);
						Vertex[] vs = this.base.getGraph().getVertices();
						Edge[] es = this.base.getGraph().getEdges();
						IOprocess io = new IOprocess();
						String name = this.currentFilePath;
						System.out.println("path:" + name);
						io.writeFile(vs, es, name);

						base.repaint();
					} else {
						JOptionPane.showMessageDialog(null, "??????");
					}

				} else
					JOptionPane.showMessageDialog(null, "?????");
			}
		}//

		/** ?????????? */
		else if (e.getSource() == mode) {
			String v1 = JOptionPane.showInputDialog("???????");
			String v2 = JOptionPane.showInputDialog("???????");
			int[] way = null;
			// ???????:
			Vertex[] v = base.getGraph().getVertices();
			Vertex start = null, end = null;
			boolean found = false;
			for (int i = 0; i < v.length; i++) {
				if (v[i].getNode().equalsIgnoreCase(v1.trim())) {
					base.addToWay(new Integer(i));
					start = v[i];
					found = true;
					break;
				}
			}
			if (found != true) {
				JOptionPane.showMessageDialog(null, "?????");
			}
			found = false;
			for (int i = 0; i < v.length; i++) {
				if (v[i].getNode().equalsIgnoreCase(v2.trim())) {
					base.addToWay(new Integer(i));
					end = v[i];
					found = true;
					break;
				}
			}
			if (found != true) {
				JOptionPane.showMessageDialog(null, "?????");
			} else {
				String re = base.getGraph().Dijkstra(start, end);
				System.out.println("start:" + start.getNum());
				System.out.println("end:" + end.getNum());
				System.out.println("re:" + re);
				// TODO ?????split re????way
				way = new int[] { start.getNum(), end.getNum() };
			}

			base.repaint();
			// base.clearWay(); //TODO

			if (way != null) {
				Point[] pos = this.base.getPos();
				/** ????mode??thread?????????repaint */

				for (int x = 0; x < way.length - 1; x++) {
					final int x1 = pos[way[x]].x;
					final int y1 = pos[way[x]].y;
					final int x2 = pos[way[x + 1]].x;
					final int y2 = pos[way[x + 1]].y;

					Thread th = new Thread(){
						public void run(){
							invoke(x1+20, y1+20,x2+20, y2+20);
						}
					};
					th.start();

				}

			}
		}

		else if (e.getSource() == about) { // show the program information:
			JOptionPane.showMessageDialog(null, "??????\n"
					+ "09302010086 ???  \n" + "??? Simon Wong");
		}

	}

	public void invoke(int x1, int y1, int x2, int y2  ){
		int xinc = (x2-x1)/100;
		int yinc = (y2-y1)/100;

		for(int i=0; i<130;i++){   //TODO ???????
			base.setPlanePlace(x1+xinc*i, y1+yinc*i);
			base.repaint();
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}



	@SuppressWarnings( { "unchecked", })
	private List makeList() {
		List panelList = new ArrayList();

		// The file list:
		panelList.add(new AccordionPanel("File       ") {
			private static final long serialVersionUID = 1L;

			public JPanel makePanel() {
				JPanel pnl = new JPanel(new GridLayout(0, 1));
				JButton c1 = load;
				JButton c2 = save;
				c1.setOpaque(false);
				c2.setOpaque(false);
				pnl.add(c1);
				pnl.add(c2);
				pnl.setSize(new Dimension(0, 60));
				pnl.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));
				return pnl;
			}
		});

		// The Function list:
		panelList.add(new AccordionPanel("Function   ") {

			private static final long serialVersionUID = 1L;

			public JPanel makePanel() {
				JPanel pnl = new JPanel(new GridLayout(0, 1));
				pnl.add(mode);
				pnl.add(add);
				pnl.add(delete);

				pnl.setSize(new Dimension(0, 100));
				pnl.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));
				return pnl;
			}
		});

		// The Help list:
		panelList.add(new AccordionPanel("Help       ") {
			private static final long serialVersionUID = 1L;

			public JPanel makePanel() {
				JPanel pnl = new JPanel(new GridLayout(0, 1));
				JButton b1 = about;

				b1.setOpaque(false);

				pnl.add(b1);

				ButtonGroup bg = new ButtonGroup();
				bg.add(b1);

				b1.setSelected(true);
				pnl.setSize(new Dimension(0, 80));
				pnl.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));
				return pnl;
			}
		});
		return panelList;
	}

	public static void main(String[] args) {
		new GraphFrame();
	}

}
