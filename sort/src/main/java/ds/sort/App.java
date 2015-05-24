package ds.sort;

import util.DSutil;

/**
 * Hello world!
 *
 */
public class App 
{
	
    public static void main( String[] args )
    {
    	Integer [] array = {4,5,3,7,9,10,2,1,6,8};
    	Sorting.mergesort(array, new Integer[array.length], 0, array.length-1);
        DSutil.printArray( array );
    }
}
