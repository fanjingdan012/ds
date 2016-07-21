package zip;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

import javax.swing.JOptionPane;


public class Zip {
	
	/**Find the types of all chars,and the frequency
	 * @param c
	 * @return TreeNode[]
	 */
	
	public TreeNode [] getTxt(char c[]){
	
		TreeNode [] result1 = new TreeNode[c.length+3];
		boolean bol = false;
		int cnt = 0;
    	for(int i = 0;i < c.length ;i++){
    		bol = false;    		
    		for(int j = 0;j < cnt ;j++){
        		if(result1[j].element == c[i]){
        			result1[j].f++;
        			bol = true;        			
        		}        		
        	}
    		if(bol==false){    			    			
    			result1[cnt]=new TreeNode(c[i],1);
    			cnt++;
    		}    		
    	}
    	TreeNode [] result = new TreeNode[cnt];
    	for(int i = 0;i < cnt ;i++){
    		result[i] = result1[i];   		
    	}
    	
    	
    	return result; 		
	}
    public void zip(File file,String basePath)  {		
    	try{
    		
    	    if(file.isFile()){    	
    	    	FileOutputStream fos = new FileOutputStream(basePath+ file.getName()+".zipfile");
    			DataOutputStream saver = new DataOutputStream(fos);
				FileInputStream fileInput = new FileInputStream(file);
	    		Scanner input = new Scanner(file);
				//get file context
			    StringBuilder tmp = new StringBuilder();
				while(input.hasNext()){
					tmp.append(input.nextLine()+"\n");
				}
				char [] buffer = tmp.toString().toCharArray();	
				//write zipfile		
				
				writeZip(fos,saver,buildHuffman(getTxt(buffer)),buffer);
				input.close();						
				fileInput.close();
				saver.close();
	    		fos.close();
			
		    }
		    else if(file.isDirectory()){
		    	File zipDir = new File(basePath+file.getName()+".zipfile");
		    	zipDir.mkdir();		 
				
			    File[] files = file.listFiles(); 
	           
	            basePath+=zipDir.getName()+"/";
	            for(int k = 0;k<files.length;k++){					
					zip(files[k],basePath);		        	
	            }	            
	        		
	            
		    }
    	    
    	}
    	catch(IOException ex) {	
			System.out.println("wrong");
		}   
    	
		return;
	
    	
    }
	/**
	 * finish zip function
	 * @param typeQunt
	 */
	public void writeZip(FileOutputStream fos,DataOutputStream saver,TreeNode []tree,char[]buffer){
		try {
			//write code
			saver.write(tree.length);
			for(int i = 0;i < tree.length;i++ ){
	    		saver.writeChar(tree[i].element );
				saver.writeUTF(tree[i].code);
	    	}
			StringBuilder s = new StringBuilder();
			for(int i = 0;i < buffer.length;i++ ){
				for(int j = 0;j < tree.length;j++ ){
					if(buffer[i]==tree[j].element ){
						s.append(tree[j].code);
					}
				}			
			}
			//add zero to fit byte
			byte b = 0;
			int addedZero=0;
			while(s.length()%8!=0){
				s.append("0");
				addedZero++;
			}
			saver.write(addedZero);
			//write contents
			String s1 = s.toString();
			int cnt = 0;
			for(int i = 0;i < s.length() ;i+=8 ){
				b = (byte)Integer.parseInt(s1.substring(i, i+8),2);
				saver.writeByte(b);
				cnt++;
	    	}	
			
		
	    	
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
    	
    	return; 		
	}
	public void writeFile(FileInputStream fis,DataInputStream reader,String path){
		try {
			HashMap<String, Character> map = new HashMap<String, Character>();
			int length = reader.read();
			String key = "";
			char val;
			
			for(int i = 0;i<length;i++){
				val = reader.readChar();
				key = reader.readUTF();
				map.put(key, val);				
			}		
			StringBuilder s = new StringBuilder();
			int addedZero = reader.read();
			while(reader.available()!=0){				
				int b = (int)(reader.readByte());
				b|=256;
			    String str =Integer.toBinaryString(b); 
			    int len=str.length(); 
                s.append(str.substring(len-8,len)); 
                 
             
			}
		
			
		
			
			StringBuilder buffer = new StringBuilder();
			
			boolean bool = false;
			int f = 1;
			int b = 0;
			String currKey;
			while(bool == false){	
				currKey = s.substring(b, f);
				if(map.containsKey(currKey)){
					buffer.append(map.get(currKey));
					bool = true;
					b = f;
				}
				f++;				
				if(f>s.length()-addedZero)break;
				bool = false;				
			}		
	    	reader.close();
	    	PrintWriter generalSaver=new PrintWriter(path);
			generalSaver.print(buffer.toString());
			generalSaver.close();
		}
		catch (FileNotFoundException e) {	
			e.printStackTrace();
		} 
		catch (IOException e) {			
			e.printStackTrace();
		}
		
    	return; 		
	}
	
	/**
	 * finish unzip function
	 * @param c
	 */
	
	public void unZip(File file,String basePath){ 		
		try{
			//if file then unzip
    	    if(file.isFile()){   
    	    	HashMap<String, Character> map = new HashMap<String, Character>();
				FileInputStream fis = new FileInputStream(file);
				DataInputStream reader = new DataInputStream(fis);
				
				int length = reader.read();
				String key = "";
				char val;
				for(int i = 0;i<length;i++){
					val = reader.readChar();
					key = reader.readUTF();
					map.put(key, val);				
				}		
				StringBuilder s = new StringBuilder();
				int addedZero = reader.read();
				while(reader.available()!=0){
					int b = (int)(reader.readByte());
					b|=256;
				    String str =Integer.toBinaryString(b); 
				    int len=str.length(); 
	                s.append(str.substring(len-8,len)); 
	               
				}
			
				
				StringBuilder buffer = new StringBuilder();
				
				boolean bool = false;
				int f = 1;
				int b = 0;
				String currKey;
				while(bool == false){	
					currKey = s.substring(b, f);
					if(map.containsKey(currKey)){
						buffer.append(map.get(currKey));
						bool = true;
						b = f;
					}
					f++;				
					if(f>s.length()-addedZero)break;
					bool = false;				
				}		
		    	reader.close();
		    	String fileName = basePath+ file.getName();
		    	PrintWriter generalSaver=new PrintWriter(fileName.substring(0,fileName.length()-8));
				generalSaver.print(buffer.toString());
				generalSaver.close();
			
		    }
    	    //if dir then creat dir and unzip
		    else if(file.isDirectory()){
		    	String fileName = basePath+ file.getName();
		    	File unzipDir = new File(fileName.substring(0,fileName.length()-8));
		    	unzipDir.mkdir();		
				
			    File[] files = file.listFiles(); 
	          
	            basePath+=unzipDir.getName()+"/";
	            for(int k = 0;k<files.length;k++){	 	
					
					unZip(files[k],basePath);
		        	
	            }
	           
	        
	        	
	            
		    }
    	    
    	}
    	catch(IOException ex) {	
			System.out.println("wrong");
		} 	
    			
		
    	
		return;
	}




	
	
	/**
	 * core method of building Huffman tree
	 * @param tree
	 * @return root of Haffman tree
	 */
	
    public TreeNode[] buildHuffman(TreeNode[] tree){
    	sort(tree);    	
    	//change to linked list
    	TreeNode header = tree[0];
    	TreeNode trailer = tree[tree.length - 1];    	
    	for(int i = 0;i < tree.length - 1;i++ ){
    		tree[i].next =tree[i+1];
    		tree[i+1].prev = tree[i];
    		
    	}
    
    
    	while(header != trailer.prev&&header!=null){
    		TreeNode a = header;
    		while(a != null){
    			
    			a=a.next;
    		}
    		
        	//build new TreeNode        	
        	TreeNode tmp = new TreeNode(header.f + header.next.f);
        	header.code = "0";
        	header.next.code = "1";
        	tmp.leftChild = header;
        	tmp.rightChild = header.next;
        	header.parent = tmp;
        	header.next.parent = tmp;
        	header = header.next.next;
        	//insert new TreeNode
        	TreeNode finder = header;
        	while((finder!=null)&&(tmp.f>finder.f)){        		
        		finder = finder.next;
        	}
        	
        	if(finder == null){
        		trailer.next = tmp;
        		tmp.prev = trailer;
        		trailer = tmp;
        		
        	}
        	else if(finder == header){
        		finder.prev = tmp;
        		tmp.next = finder;
        		header = tmp;
        		
        	}
        	else{
        		tmp.prev = finder.prev;
        		tmp.next = finder;
        		finder.prev.next = tmp;
        		finder.prev = tmp;
        		
        	}
        	
    	}
    	
    	TreeNode root = new TreeNode(header.f + trailer.f);
    	header.code = "0";
    	trailer.code = "1";
    	root.leftChild = header;
    	root.rightChild = trailer;
    	header.parent = root;
    	header.next.parent = root;
    	//get code
    	for(int i = 0;i < tree.length;i++ ){
    		TreeNode finder2 = tree[i].parent;
    		while(finder2 != root&&finder2!=null){
    			tree[i].code = finder2.code + tree[i].code;
    			finder2 = finder2.parent;
    		}
    		
    	}    	
		return tree;
    }
    
    public void sort(TreeNode array[]){
    	 TreeNode tmp;
    	 for(int i=0 ; i < array.length ; ++i){
    		 for(int j=0; j <array.length - i - 1; ++j){
    			 if(array[j].f > array[j + 1].f){
    				 tmp = array[j];
    				 array[j] = array[j + 1];
    				 array[j + 1] = tmp;
    			 }
    		 }
    	}
    	
    }

      
}
