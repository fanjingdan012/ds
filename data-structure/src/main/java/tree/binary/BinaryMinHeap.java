package tree.binary;

interface BinaryMinHeap{
	public void insert (Comparable x) throws OverflowException;
	public Comparable findMin();
	public Comparable deleteMin();
	public boolean isEmpty();
	public boolean isFull();
	public void makeEmpty();
}
