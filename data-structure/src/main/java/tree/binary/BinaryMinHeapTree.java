package tree.binary;

public class BinaryMinHeapTree implements BinaryMinHeap {
    private Comparable[] array;
    private int currentSize;

    public BinaryMinHeapTree(int maxSize){
        array = new Comparable[maxSize];
    }

    @Override
    public Comparable findMin() {
        return array[0];
    }

    @Override
    public boolean isEmpty() {
        return currentSize==0;
    }

    @Override
    public boolean isFull() {
        return currentSize>=array.length;
    }

    @Override
    public void makeEmpty() {
        array = new Comparable[array.length];
    }

    public void insert(Comparable x) throws OverflowException {
        if (isFull())
            throw new OverflowException();
        //1. put the new node at last position
        int hole = ++currentSize;
        //2. compare with its parent, and swap if needed
        for (; hole > 1 && x.compareTo(array[hole / 2]) < 0; hole /= 2)
            array[hole] = array[hole / 2];
        array[hole] = x;
    }

    public Comparable deleteMin() {
        if (isEmpty())
            return null;
        Comparable minItem = findMin();
        array[1] = array[currentSize--];
        percolateDown(1);
        return minItem;
    }

    private void percolateDown(int hole) {
        int child;
        Comparable tmp = array[hole];
        for (; hole * 2 <= currentSize; hole = child) {
            child = hole * 2;
            if (child != currentSize &&
                    array[child + 1].compareTo(array[child]) < 0)
                child++;
            if (array[child].compareTo(tmp) < 0)
                array[hole] = array[child];
            else
                break;
        }
        array[hole] = tmp;
    }


}

class OverflowException extends RuntimeException {

}
