package sort;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import sun.misc.Sort;
import util.DSutil;

/**
 * Created by I312177 on 11/13/2015.
 */

public class SortingTest {
    Integer [] array =null;
    Elem[] arrayE=null;
    @Before
    public void setUp(){
        array = new Integer[]{1, 4, 3, 2, 5};
        arrayE = new Elem[]{new Elem(1),new Elem(4),new Elem(3),new Elem(2),new Elem(5)};
    }

    @After
    public void tearDown(){
        array=null;
    }

    private void checkArrayResult(){
        assertEquals(new Integer(1),array[0]);
        assertEquals(new Integer(2),array[1]);
        assertEquals(new Integer(3), array[2]);
        assertEquals(new Integer(4), array[3]);
        assertEquals(new Integer(5), array[4]);
    }

    private void checkArrayEResult(){
        assertEquals(new Elem(1),arrayE[0]);
        assertEquals(new Elem(2),arrayE[1]);
        assertEquals(new Elem(3), arrayE[2]);
        assertEquals(new Elem(4), arrayE[3]);
        assertEquals(new Elem(5), arrayE[4]);
    }


    @Test
    public void testInsertsort(){

        Sorting.insertsort(array);
        DSutil.printArray(array);
        checkArrayResult();
    }


    @Test
    public void testBubblesort(){

        Sorting.bubblesort(array);
        DSutil.printArray(array);
        checkArrayResult();
    }

    @Test
    public void testSelectSort(){
        Sorting.selectsort(array);
        DSutil.printArray(array);
        checkArrayResult();
    }

    @Test
    public void testShellSort(){
        Sorting.shellsort(array);
        DSutil.printArray(array);
        checkArrayResult();
    }

    @Test
    public void testQuickSort(){
        Sorting.quicksort(arrayE, 0, arrayE.length - 1);
        DSutil.printArray(arrayE);
        checkArrayEResult();
    }

    @Test
    public void testQSort(){
        Sorting.qsort(arrayE, 0, arrayE.length - 1);
        DSutil.printArray(arrayE);
        checkArrayEResult();
    }

    @Test
    public void testMergeSort1(){
        Sorting.mergesort1(arrayE, new Elem[arrayE.length], 0, arrayE.length - 1);
        DSutil.printArray(arrayE);
        checkArrayEResult();
    }

    @Test
    public void testMergeSort(){
        Sorting.mergesort(arrayE, new Elem[arrayE.length], 0, arrayE.length - 1);
        DSutil.printArray(arrayE);
        checkArrayEResult();
    }
}
