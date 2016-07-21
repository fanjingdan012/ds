package util;

public class DSutil {

	public static void swap(Comparable[] array, int j, int i) {
		Comparable temp = array[i];
		array[i]=array[j];
		array[j]=temp;
		
	}

	public static void printArray(Comparable[] array){
		System.out.print("[");
		for(int i=0;i<array.length;i++){
			System.out.print(array[i]+" , ");
		}
		System.out.println("]");
	}

	

}
