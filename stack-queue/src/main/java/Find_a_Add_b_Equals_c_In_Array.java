/**
 * C3.16 Write a recursive algorithm that will check if an array A of integers contains an integer A[i] that is the sum of two integers that appear earlier in A, That is, such that A[i] = A[j]+A[k] for j, k < i && j != k
 */
import javax.swing.JOptionPane;
public class Find_a_Add_b_Equals_c_In_Array {
	static int [] array = new int[100];
	static boolean bool = false;
	public static void main(String[] args) {
		
		//input an array

		System.out.println("Please input an array of integer,end it with a '*'");
		int counter = 0;
		String input = "";
		do{
            input = JOptionPane.showInputDialog("Please input an array of integer:");
            if(!input.equals("*")){
			    array[counter] = Integer.parseInt(input);
			}
			counter ++;
		}while (!input.equals("*"));
		counter --;
        if (counter < 3){
			System.out.println("error:you should input 3 or more numbers!");
		}
		else{
			//recursive method
        
		    int n = counter - 1;
		    //rec (n);
		    boolean boolres = rec(n);
		    System.out.println(boolres);
		}

	}
	public static boolean rec(int n){
        if (n == 2){
            if (array[n] != array[n-1] + array[n-2]){
		        bool = false;
			    return bool;
            }
            else {
            	bool = true;
            	return bool;
            }
        }
		else{
			boolean boolloop = false;
			//TODO:loop
			for(int i = 0;i < n;i++){
				for(int j = 0;j < i;j++){
					if(array[n] == array[i] + array[j]){
					    boolloop = true;
					}
				}
			}
			
	 	    bool = rec(n - 1) || boolloop;
			return bool;
		}
	
	}
}
