package sort;

import util.DSutil;

import java.util.ArrayList;
import java.util.List;

public class Sorting {
    public static final int MAXSTACKSIZE = 1000;
    public static final int THRESHOLD = 2;

    //insertion sort
    public static void insertsort(Comparable[] array) { // Insertion Sort
        for (int i = 1; i < array.length; i++) // insert i’th record
            for (int j = i; (j > 0) && array[j].compareTo(array[j - 1]) < 0; j--)
                DSutil.swap(array, j, j - 1);
    }

    //bubble sort
    public static void bubblesort(Comparable[] array) { // Bubble Sort
        for (int i = 0; i < array.length - 1; i++) // Bubble up i’th record
            for (int j = array.length - 1; j > i; j--)
                if (array[j].compareTo(array[j - 1]) < 0)
                    DSutil.swap(array, j, j - 1);
    }

    public static void selectsort(Comparable[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            int lowindex = i;
            for (int j = i + 1; j < array.length; j++)
                if (array[j].compareTo(array[lowindex]) < 0)
                    lowindex = j;
            DSutil.swap(array, i, lowindex);
        }
    }

    public static void shellsort(Comparable[] array) {
        for (int gap = array.length / 2; gap > 0; gap /= 2)
            for (int i = gap; i < array.length; i++) {
                for (int j = i; j >= gap &&
                        array[j].compareTo(array[j - gap]) < 0; j -= gap)
                    DSutil.swap(array, j, j - gap);
            }
    }

    /**
     * recursive
     * @param array
     * @param i
     * @param j
     */
    public static void quicksort(Elem[] array, int i, int j) {
        int pivotindex = findpivot(array, i, j);
        DSutil.swap(array, pivotindex, j);;
        int k = partition(array, i - 1, j, array[j]);
        DSutil.swap(array, k, j);
        if ((k - i) > 1) quicksort(array, i, k - 1);
        if ((j - k) > 1) quicksort(array, k + 1, j);
    }

    private static int findpivot(Comparable[] array, int i, int j) {
        return i;

    }

    private static int partition(Comparable[] array, int l, int r, Comparable pivot) {
        do {
            while (array[++l].compareTo(pivot) < 0) ;
            while ((r != 0) && (array[--r].compareTo(pivot) > 0)) ;
            DSutil.swap(array, l, r);
        } while (l < r);
        DSutil.swap(array, l, r);
        return l;
    }

    /**
     * non-recursive Quicksort
     */
    public static void qsort(Elem[] array, int oi, int oj) {
        int[] Stack = new int[MAXSTACKSIZE];
        int listsize = oj - oi + 1;
        int top = -1;
        Elem pivot;
        int pivotindex, l, r;
        Stack[++top] = oi;
        Stack[++top] = oj;
        while (top > 0) {
            int j = Stack[top--];
            int i = Stack[top--];
            pivotindex = (i + j) / 2;
            pivot = array[pivotindex];
            DSutil.swap(array, pivotindex, j);
            l = i - 1;
            r = j;
            do {
                while (array[++l].compareTo(pivot) < 0) ;
                while ((r != 0) && (array[--r].compareTo(pivot) > 0)) ;
                DSutil.swap(array, l, r);
            } while (l < r);
            DSutil.swap(array, l, r);
            DSutil.swap(array, l, j);
            if ((l - i) > THRESHOLD) {
                Stack[++top] = i;
                Stack[++top] = l - 1;
            }
            if ((j - l) > THRESHOLD) {
                Stack[++top] = l + 1;
                Stack[++top] = j;
            }
        }
        insertsort(array);
    }

    /**
     * recursive pseudocode
     * @param inlist
     * @return
     */
    private static List mergesort(List inlist) {
        if (inlist.size() <= 1) return inlist;
        List l1 = inlist.subList(0, 1);//half of the items from inlist;
        List l2 = inlist.subList(1, inlist.size());//other half of the items from inlist;
        return merge(mergesort(l1), mergesort(l2));
    }

    private static List merge(List l1,List l2){
        //TODO
        return new ArrayList();
    }

    /**
     * The recursive implementation
     * @param array
     * @param temp
     * @param l
     * @param r
     */
    public static void mergesort1(Elem[] array, Elem[] temp, int l, int r) {
        if (l == r) return;
        int mid = (l + r) / 2;
        mergesort1(array, temp, l, mid);
        mergesort1(array, temp, mid + 1, r);
        for (int i = l; i <= r; i++)
            temp[i] = array[i];
        //Do the merge operation back to array
        int i1 = l;
        int i2 = mid + 1;
        for (int curr = l; curr <= r; curr++) {
            if (i1 == mid + 1)
                array[curr] = temp[i2++];
            else if (i2 > r)
                array[curr] = temp[i1++];
            else if (temp[i1].key() <= temp[i2].key())
                array[curr] = temp[i1++];
            else
                array[curr] = temp[i2++];
        }
    }

    /**
     * The improved recursive implementation
     * @param array
     * @param temp
     * @param l
     * @param r
     */
    public static void mergesort(Elem[] array, Elem[] temp, int l, int r) {
        int i, j, k, mid = (l + r) / 2;
        if (l == r) return;
        if ((mid - l) >= THRESHOLD) mergesort(array, temp, l, mid);
        else inssort(array, l,  mid - l + 1);
        if ((r - mid) > THRESHOLD) mergesort(array, temp, mid + 1, r);
        else inssort(array, mid + 1, r - mid);
        for (i = l; i <= mid; i++) temp[i] = array[i];
        for (j = 1; j <= r - mid; j++) temp[r - j + 1] = array[j + mid];
        int a = temp[l].key();
        int b = temp[r].key();
        for (i = l, j = r, k = l; k <= r; k++)
            if (a < b) {
                array[k] = temp[i++];
                a = temp[i].key();
            } else {
                array[k] = temp[j--];
                b = temp[j].key();
            }
    }

    private static void inssort(Elem[] array, int l, int r){
        if(r<=l)
            return;
        Elem[] array2 = new Elem[r-l];
        for(int i = 0;i<array2.length;i++){
            array2[i]=array[l+i];
        }
        insertsort(array2);
    }


}
