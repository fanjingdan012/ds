package vector;

public class DLNode implements Position {
    // first, stuff related to Position
    private Object element;

    public DLNode(Object o, DLNode prev, DLNode next) {
        this.element=o;
        this.prev=prev;
        this.next=next;

    }

    public Object element() { return element; }
    // next, stuff related to linked list node
    private DLNode next;
    private DLNode prev;

    public DLNode getNext() {
        return next;
    }

    public void setNext(DLNode next) {
        this.next = next;
    }

    public DLNode getPrev() {
        return prev;
    }

    public void setPrev(DLNode prev) {
        this.prev = prev;
    }
}