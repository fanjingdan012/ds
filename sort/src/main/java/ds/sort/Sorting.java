package ds.sort;

import java.util.List;

import util.DSutil;

public class Sorting {
	private static final int THRESHOLD = 2;
	private static final int MAXSTACKSIZE = 0;

	// insertion sort
	static void insertsort(Comparable[] array) { // Insertion Sort
		for (int i = 1; i < array.length; i++)
			// insert i’th record
			for (int j = i; (j > 0) && array[j].compareTo(array[j - 1]) < 0; j--)
				DSutil.swap(array, j, j - 1);
	}

	// bubble sort
	static void bubblesort(Comparable[] array) { // Bubble Sort
		for (int i = 0; i < array.length - 1; i++)
			// Bubble up i’th record
			for (int j = array.length - 1; j > i; j--)
				if (array[j].compareTo(array[j - 1]) < 0)
					DSutil.swap(array, j, j - 1);
	}

	static void selectsort(Comparable[] array) {
		for (int i = 0; i < array.length - 1; i++) {
			int lowindex = i;
			for (int j = i + 1; j < array.length; j++)
				if (array[j].compareTo(array[lowindex]) < 0)
					lowindex = j;
			DSutil.swap(array, i, lowindex);
		}
	}

	static void shellsort(Comparable[] array) {
		for (int gap = array.length / 2; gap > 0; gap /= 2)
			for (int i = gap; i < array.length; i++) {
				for (int j = i; j >= gap
						&& array[j].compareTo(array[j - gap]) < 0; j -= gap)
					DSutil.swap(array, j, j - gap);
			}
	}

	static void quicksort(Integer[] array, int i, int j) {
		int pivotindex = findpivot(array, i, j);
		DSutil.swap(array, pivotindex, j);
		int k = partition(array, i - 1, j, array[j]);
		DSutil.swap(array, k, j);
		if ((k - i) > 1)
			quicksort(array, i, k - 1);
		if ((j - k) > 1)
			quicksort(array, k + 1, j);
	}

	static int findpivot(Comparable[] array, int i, int j) {
		return 0;
	}

	static int partition(Comparable[] array, int l, int r, int pivot) {
		do {
			while (array[++l].compareTo(pivot) < 0)
				;
			while ((r != 0) && (array[--r].compareTo(pivot) > 0))
				;
			DSutil.swap(array, l, r);
		} while (l < r);
		DSutil.swap(array, l, r);
		return l;
	}

	static void quicksort_non_recursive(Integer[] array, int i, int j) {
		int pivotindex = findpivot(array, i, j);
		DSutil.swap(array, pivotindex, j);
		int k = partition(array, i - 1, j, array[j]);
		DSutil.swap(array, k, j);
		if ((k - i) > 1)
			qsort(array, i, i - 1);
		if ((j - k) > 1)
			qsort(array, k + 1, j);
	}

	// non-recursive Quicksort
	static void qsort(Integer[] array, int oi, int oj) {
		int[] Stack = new int[MAXSTACKSIZE];
		int listsize = oj - oi + 1;
		int top = -1;
		int pivot;
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
				while (array[++l].compareTo(pivot) < 0)
					;
				while ((r != 0) && (array[--r].compareTo(pivot) > 0))
					;
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

//	List mergesort(List inlist) {
//		if (inlist.size() <= 1)
//			return inlist;
//		List l1 = inlist.subList(0, inlist.size() / 2);
//		List l2 = inlist.subList(inlist.size() / 2 + 1, inlist.size() - 1);
//		return merge(mergesort(l1), mergesort(l2));
//	}

	static void mergesort(Integer[] array, Integer[] temp, int l, int r) {
		if (l == r)
			return;
		int mid = (l + r) / 2;
		mergesort(array, temp, l, mid);
		mergesort(array, temp, mid + 1, r);
		for (int i = l; i <= r; i++)
			temp[i] = array[i];
		// Do the merge operation back to array
		int i1 = l;
		int i2 = mid + 1;
		for (int curr = l; curr <= r; curr++) {
			if (i1 == mid + 1)
				array[curr] = temp[i2++];
			else if (i2 > r)
				array[curr] = temp[i1++];
			else if (temp[i1] <= temp[i2])
				array[curr] = temp[i1++];
			else
				array[curr] = temp[i2++];
		}
	}
	//has threshold
	static void mergesort2(Integer[] array, Integer[] temp, int l, int r) {
		int i, j, k, mid = (l + r) / 2;
		if (l == r)
			return;
		if ((mid - l) >= THRESHOLD)
			mergesort2(array, temp, l, mid);
		else
			insertsort(array, l,  mid - l + 1);
		if ((r - mid) > THRESHOLD)
			mergesort2(array, temp, mid + 1, r);
		else
			insertsort(array, mid + 1, r - mid);
		for (i = l; i <= mid; i++)
			temp[i] = array[i];
		for (j = 1; j <= r - mid; j++)
			temp[r - j + 1] = array[j + mid];
		int a = temp[l];
		int b = temp[r];
		for (i = l, j = r, k = l; k <= r; k++)
			if (a < b) {
				array[k] = temp[i++];
				a = temp[i];
			} else {
				array[k] = temp[j--];
				b = temp[j];
			}
	}
	// insertion sort
	static void insertsort(Comparable[] array,int l,int r) { // Insertion Sort
		Comparable[] array2;
		for (int i = l; i < r; i++)
			// insert i’th record
			for (int j = i; (j > 0) && array[j].compareTo(array[j - 1]) < 0; j--)
				DSutil.swap(array, j, j - 1);
	}

}
