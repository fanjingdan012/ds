package sort;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


/**
 * Created on 11/13/2015.
 */

public class SortingTest {
    Comparable[] array = null;

    @Before
    public void setUp() {
        array = new Comparable[8];
        array[0] = new Comparable(42);
        array[1] = new Comparable(20);
        array[2] = new Comparable(17);
        array[3] = new Comparable(14);
        array[4] = new Comparable(28);
        array[5] = new Comparable(13);
        array[6] = new Comparable(23);
        array[7] = new Comparable(15);

    }

    @After
    public void tearDown() {
        array = null;
    }

    private void checkArrayResult() {
        assertEquals(new Comparable(13), array[0]);
        assertEquals(new Comparable(14), array[1]);
        assertEquals(new Comparable(15), array[2]);
        assertEquals(new Comparable(17), array[3]);
        assertEquals(new Comparable(20), array[4]);
        assertEquals(new Comparable(23), array[5]);
        assertEquals(new Comparable(28), array[6]);
        assertEquals(new Comparable(42), array[7]);
    }


    @Test
    public void testInsertsort() {

        Sorting1.insertsort(array);
        DSutil.printArray(array);
        checkArrayResult();
    }


    @Test
    public void testBubblesort() {

        Sorting1.bubblesort(array);
        DSutil.printArray(array);
        checkArrayResult();
    }

    @Test
    public void testSelectSort() {
        Sorting1.selectsort(array);
        DSutil.printArray(array);
        checkArrayResult();
    }

    @Test
    public void testShellSort() {
        Sorting1.shellsort(array);
        DSutil.printArray(array);
        checkArrayResult();
    }

    @Test
    public void testQuickSort() {
        Sorting1.quicksort(array, 0, array.length - 1);
        DSutil.printArray(array);
        checkArrayResult();
    }

    @Test
    public void testQSort() {
        Sorting1.qsort(array, 0, array.length - 1);
        DSutil.printArray(array);
        checkArrayResult();
    }

    @Test
    public void testMergeSort1() {
        Sorting1.mergesort1(array, new Comparable[array.length], 0, array.length - 1);
        DSutil.printArray(array);
        checkArrayResult();
    }

//    @Test
//    public void testMergeSort() {
//        Sorting1.mergesort(array, new Comparable[array.length], 0, array.length - 1);
//        DSutil.printArray(array);
//        checkArrayResult();
//    }

    @Test
    public void findFiveLargestNumInArray() {

        findFiveLargestNumInArray(array);
		/*for(int i  = 0;i<array.length;i++){
			System.out.println(array[i].key);
		}	*/
    }

    public static void findFiveLargestNumInArray(Comparable[] array) {//modified from selectionsort
        for (int i = 0; i < 5; i++) {
            int lowindex = i;
            for (int j = i + 1; j < array.length; j++)
                if (array[j].compareTo(array[lowindex]) > 0)
                    lowindex = j;
            System.out.println("  " + array[lowindex]);
            DSutil.swap(array, i, lowindex);

            //System.out.print("lowindex="+lowindex);

        }
    }
}
