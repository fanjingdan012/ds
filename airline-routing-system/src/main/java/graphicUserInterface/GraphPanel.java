package graphicUserInterface;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

import javax.imageio.ImageIO;
import javax.swing.*;

import dataStructure.*;

public class GraphPanel extends JPanel {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	/** Data needed to draw a graph */
	private Graph gr;
	private Vertex[] points;
	private Edge[] edges;
	// all the corresponding positions of vertices:
	private Point[] positions;
	// the list of vertices a flight passes
	private LinkedList<Integer> waylist = new LinkedList<Integer>();

	// ????????????????
	private int posx = 0, posy = 0;

	// ??????????
	private LinkedList<String> toBeShown = new LinkedList<String>();

	public GraphPanel() {
		this.setBackground(Color.white);
	}

	public GraphPanel(Graph g) {
		this.setBackground(Color.white);
		this.gr = g;
		// this.positions = pos;
	}

	public Graph getGraph() {
		return this.gr;
	}

	public Point[] getPos() {
		return this.positions;
	}

	public void setGraph(Graph g) {
		this.gr = g;
	}

	public void setPos(Point[] in) {
		this.positions = in;
	}

	public void addWarning(String in) {
		this.toBeShown.add(in);
	}

	public void setPlanePlace(int x, int y) {
		this.posx = x;
		this.posy = y;
	}

	// ???????????
	public void addToWay(Integer in) {
		this.waylist.add(in);
	}

	// ???????????????
	public void clearWay() {
		this.waylist.clear();
	}

	public void initAll() {
		this.points = gr.getVertices();
		this.edges = gr.getEdges();
		System.out.println("edge number:" + edges.length);
		this.positions = new Point[points.length];
		displayPoints();
	}

	// ????????????????????????????
	// ???????????
	private void displayPoints() {
		int width = this.getWidth();
		int height = this.getHeight();

		Point center = new Point(width / 2 - 35, height / 2 - 30); // ????
		int num = this.points.length;
		System.out.println("x:" + center.x);
		System.out.println("y:" + center.y);
		double part = (Math.toRadians(360)) / num;
		// System.out.println("part:"+part);
		double angle = 0;
		int r = Math.min(width / 2, height / 2) - 30;
		// ?????N????
		for (int i = 0; i < num; i++) {
			int x = (int) (center.x + r * Math.cos(angle));
			int y = (int) (center.y + r * Math.sin(angle));
			this.positions[i] = new Point(x, y);
			// System.out.println("x:"+x);
			// System.out.println("y:"+y);
			angle += part;
			// System.out.println("angle:"+angle);
		}

	}

	public void paintComponent(Graphics g) {
		if (this.gr != null) {
			initAll();

			// if(this.waylist.size()==0)
			super.paintComponent(g); // Important

			// draw the vertices:
			g.setColor(Color.ORANGE);// ????
			for (int i = 0; i < this.positions.length; i++) {
				g.fillOval(positions[i].x, positions[i].y, 50, 40);
				g.setColor(Color.magenta);// ????
				g.drawString(this.points[i].getNode(), positions[i].x + 20,
						positions[i].y + 20);
				g.setColor(Color.orange);
			}

			// draw the edges:
			g.setColor(Color.BLUE);// ????
			/** draw the edges: */
			System.out.println("edges:" + this.edges.length);

			for (int i = 0; i < this.edges.length; i++) {
				// g.drawLine(arg0, arg1, arg2, arg3)
				Vertex p1 = this.edges[i].getNode();
				Vertex p2 = this.edges[i].getnextNode();
				int weight = this.edges[i].getWeight();
				Point pos1 = null;
				Point pos2 = null;
				for (int j = 0; j < this.points.length; j++) {
					if (this.points[j] != null && p1 != null && p2 != null) {
						if (this.points[j].getNode().equals(p1.getNode())) {
							pos1 = positions[j];
						}
						if (this.points[j].getNode().equals(p2.getNode())) {
							pos2 = positions[j];
						}
					}
				}

				g.drawLine(pos1.x + 20, pos1.y + 20, pos2.x + 20, pos2.y + 20);

				Point mid = new Point((pos1.x + 20 + pos2.x + 20) / 2,
						(pos1.y + 40 + pos2.y) / 2);

				// ???????
				g.drawString("" + weight, mid.x, mid.y);

			}

			// ???????
			g.setColor(Color.RED);
			int count = 0;
			for (int i = 0; i < this.toBeShown.size(); i++) {
				g.drawString(this.toBeShown.get(i), 50, 50 + count);
				count += 20;
			}
			this.toBeShown.clear();

			if (this.waylist.size() != 0) {

				for (int i = 0; i < this.waylist.size(); i++) {
					int pos = this.waylist.get(i);
					g.setColor(Color.RED);
					// System.out.println("pos:"+positions[pos].x);
					// System.out.println("pos:"+positions[pos].y);
					g.fillOval(positions[pos].x, positions[pos].y, 50, 40);
					g.setColor(Color.GREEN);
					g.drawString(this.points[pos].getNode(),
							positions[pos].x + 20, positions[pos].y + 20);
				}

				/** the AIRPLANE: */
				try {
					BufferedImage bi = ImageIO.read(GraphPanel.class.getResource("1.png"));

					g.drawImage(bi, this.posx, this.posy, this);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		}

		else
			super.paintComponent(g);
	}

}
