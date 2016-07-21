package util;

public class Node<Object> {
	public static int orderCount = 0;
	public Object element;
	public Node<Object> prev;
	public Node<Object> next;
	public int order;
	public Node(Object e ,Node<Object> p ,Node<Object> n){
		this.element = e;
		this.prev = p;
		this.next = n;
		orderCount++;
		this.order = orderCount;
	}
	
}
