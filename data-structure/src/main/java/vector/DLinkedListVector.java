package vector;

public class DLinkedListVector implements Vector {
    private DLNode header;        // header of Doubly Linked List
    private DLNode trailer;        // tailer of Doubly Linked List
    private int size;

    public DLinkedListVector() {
        header = new DLNode(null, null, null);
        trailer = new DLNode(null, header, null);
        header.setNext(trailer);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public Object elemAtRank(int r) throws InvalidRankException {
        return nodeAtRank(r).element();
    }

    @Override
    public Object replaceAtRank(int r, Object o) throws InvalidRankException {
        return null;
    }

    public void insertAtRank(int r, Object o) throws InvalidRankException {
        if (r < 0 || r > size()) throw new InvalidRankException();
        DLNode next = nodeAtRank(r); // the new node will be right before ‘next’
        DLNode prev = next.getPrev(); // the new node willl be right after ‘prev’
        DLNode node = new DLNode(o, prev, next);
        // new node knows about its next & prev. Now
        // we tell next & prev about the new node.
        next.setPrev(node);
        prev.setNext(node);
        size++;
    }


    public Object removeAtRank(int r) throws InvalidRankException {
        if (r < 0 || r > size() - 1)        //r is over bound
            throw new InvalidRankException();
        DLNode node = nodeAtRank(r); // node to be removed
        DLNode next = node.getNext();    // node before node to be removed
        DLNode prev = node.getPrev();    // node after node to be removed
        prev.setNext(next);
        next.setPrev(prev);
        size--;
        return node.element(); // returns the element of the deleted node
    }

    //simple version
//	DLNode nodeAtRank(int rank){
//		DLNode node = header;
//		for (int i=0; i<=rank; i++)
//			node = node.getNext();
//		return node;
//	}
// improved version
    private DLNode nodeAtRank(int r) {
        DLNode node; // start at the node closest to the desired rank
        if (r <= size() / 2) { //scan forward from header
            node = header.getNext();
            for (int i = 0; i < r; i++)
                node = node.getNext();
        } else { // scan backward from trailer
            node = trailer.getPrev();
            for (int i = 0; i < size() - r - 1; i++)
                node = node.getPrev();
        }
        return node;
    }



}
