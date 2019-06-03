package vector;

interface ObjectIterator {
	boolean hasNext();
	Object nextIterator();
}

class ListIterator implements ObjectIterator {
		List L; // the sequence over which we’re iterating
		DLNode node; // current position in L
		ListIterator(List _L) {
			L = _L;
			node = (DLNode) L.first();
		}
		boolean hasNext() {  return node != L.trailer; }
		Object nextObject() {
			Object o = node.element();
			node = node.getNext();
			return o; 	}
}