package util;

public class DSutil {

	public static void swap(Comparable[] array, int j, int i) {
		// TODO Auto-generated method stub
		Comparable temp = array[j];
		array[j]=array[i];
		array[i]=temp;
		
	}
	public static void printArray(Integer [] array){
		for(int i = 0;i<array.length;i++){
			System.out.print(" "+array[i]);
		}
		System.out.println();
		
	}

	

}
