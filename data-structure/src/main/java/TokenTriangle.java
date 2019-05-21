/**
 * 问题描述：  
如下图是由14个“+”和14个“-”组成的符号三角形, 2个同号下面都是“+”，2个异号下面都是“-”。  
- + + - + + +   
 - + - - + +   
  - - + - +   
   + - - -   
    - + +   
     - +   
      -  
在一般情况下，符号三角形的第一行有n个符号, 符号三角形问题要求对于给定的n，  
计算有多少个不同的符号三角形，使其所含的“+”和“-”的个数相同。 
 */
import javax.swing.JOptionPane;
public class TokenTriangle{
	static int counter = 0;//it counts the number of "+" 
    static int result = 0;
	static int [][] triangle = new int [100][100];
	public static void main(String[] args){		
        String num = JOptionPane.showInputDialog("Please input the number of notations in the first line.");
        int n = Integer.parseInt(num);
		int total = n *(n + 1) / 2;
		int half = total/2;		
		if (half * 2 != total){
			System.out.println(0);
		}
		else {
			rec(1,n,half);
			System.out.println(result);
		}
        
	}
	public static void rec(int a,int n,int half){		

		if (a > n){
			result ++;
		}
		else {
			//int i = 0;
			int notation = 0;
			for(notation = 0;notation < 2;notation ++){
				triangle[1][a] = notation;//notation =0 or 1;0 represents +;1 represents *
			
                counter += notation;
                
                for(int i = 2;i <= a;i ++){
					triangle[i][a - i + 1] = triangle[i - 1][a - i + 1] ^ triangle[i - 1][a - i + 2];
                    counter += triangle[i][a - i + 1];
				}
				if((counter <= half) && (a * (a + 1) / 2 - counter <= half)){
			        rec(a + 1,n,half);
				}
				//回朔
                for(int i = 2;i <= a;i++){
                    counter -= triangle[i][a - i + 1];
				}
			    counter -= notation;
			}				
		}
	}
}
