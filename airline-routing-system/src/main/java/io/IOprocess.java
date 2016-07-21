package io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.LinkedList;

import javax.swing.JOptionPane;

import dataStructure.*;

public class IOprocess {

	public static void main(String[] args) {
		IOprocess x = new IOprocess();
		File xx = new File("airline-routing-system/123.sgff");
		System.out.println(xx.getAbsolutePath());

		x.readMyFile(xx);

		System.out.println("number of vertices:" + x.vlist.size());

		for (int i = 0; i < x.vlist.size(); i++) {
			System.out.println(i + ":" + x.vlist.get(i).getNode());
		}

		for (int i = 0; i < x.elist.size(); i++) {
			System.out.println(i + ":" + x.elist.get(i).node1);
		}

		// x.dajiangyou("123.sgff");
	}

	public class EdgeContent {
		public String node1 = "";
		public String node2 = "";

		public int weight = 0;

	}

	public LinkedList<Vertex> vlist = new LinkedList<Vertex>();
	public LinkedList<EdgeContent> elist = new LinkedList<EdgeContent>();

	/** 读取： */
	public void readMyFile(File file) {

		String in = file.getName();

		if (!in.endsWith(".sgff")) {
			JOptionPane.showMessageDialog(null, "Input format error!");
		}

		String[] temp;

		String record = null;
		// int recCount = 0;
		try {
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			record = new String();
			while ((record = br.readLine()) != null) {
				temp = record.split(" ");

				for (int a = 0; a < temp.length; a++) {
					temp[a] = temp[a].trim();
				}

				if (temp[0].equals("VERTEX")) {
					// System.out.println(temp[1]);
					Vertex t = new Vertex(temp[1]);
					// t.setPos(Integer.parseInt(temp[2]),
					// Integer.parseInt(temp[3]));
					vlist.add(t);
				} else if (temp[0].equals("EDGE")) {
					EdgeContent e = new EdgeContent();
					e.node1 = temp[1];
					e.node2 = temp[2];
					e.weight = Integer.parseInt(temp[3]);
					elist.add(e);
				}

			}
			br.close();
			fr.close();
		} catch (IOException e) {
			System.out.println("IO异常！！！");
			e.printStackTrace();
		}
		// SudokuV1.printArray(re);
	}

	/** 写入文件 */
	public void writeFile(Vertex[] v, Edge[] e, String name) {
		try{
		
		FileOutputStream out; // declare a file output object   
	    PrintStream p; // declare a print stream object   
		
	    if(name.endsWith(".sgff")){} else name+=".sgff";
	    
	    out = new FileOutputStream(name);     
	    p = new PrintStream( out );  
	    
		for(int i=0; i<v.length;i++){
             p.print("VERTEX");
             p.print(" ");
             p.print(v[i].getNode());
             p.println();
        }  
        
		for(int i=0; i<e.length;i++){
			p.print("EDGE");
			p.print(" ");
			p.print(e[i].getNode().getNode());
			p.print(" ");
			p.print(e[i].getnextNode().getNode());
			p.print(" ");
			p.print(e[i].getWeight());
			p.println();
		}
		
		
		} catch(IOException aaa){
			aaa.printStackTrace();
		}	
	}

	/** 打酱油的测试方法 */
	public void dajiangyou(String in) {
		String[] v = new String[5];
		v[0] = "PVG";
		v[1] = "AMS";
		v[2] = "DUB";
		v[3] = "CFN";
		v[4] = "MAA";

		FileOutputStream out; // declare a file output object
		PrintStream p; // declare a print stream object

		if (in.endsWith(".sgff")) {
		} else
			in += ".sgff";

		try {
			out = new FileOutputStream(in);
			p = new PrintStream(out);

			p.print("VERTEX  PVG");

			p.close();
		} catch (Exception e) {
			System.err.println("写入文件错误！！！");
		}

	}

}
