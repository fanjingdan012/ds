package util;

import UI.EmptyStackException;


//Implement the stack ADT with a doubly linked list
public class Stack {
	protected Node1<Object> top;
	protected int size;
	public static final int N = 100;
	public Stack(){
		top = null;
		size = 0;
	}
	public int size(){		
		return size;
	}
	public boolean isEmpty(){
		if (top == null){
			return true;
		}
		return false;
	}
	public Object top()throws EmptyStackException{
		if(isEmpty()){
			throw new EmptyStackException("Exception:Stack is empty");
		}
		return top.getElement();
		
	}
    public void push(Object element){
		Node1<Object> v = new Node1<Object>(element,null,top);
		if(top == null){
			top = v;
			size ++;
		}
		else{
			
			size ++;
			top.setPrev(v);
			top = v;
		}
				
	}
    public Object pop()throws EmptyStackException{
    	if(isEmpty()){
			throw new EmptyStackException("Exception:Stack is empty");
		}
    	Object temp = top.getElement();
    	if(!(top.getNext()==null)){
    		top = top.getNext();
    	}
    	top.setPrev(null);
    	size--;
    	return temp;
    	
    }

}
class Node1<Object>{
	private Object element;
	private Node1<Object> prev;
	private Node1<Object> next;
	public Node1(Object e ,Node1<Object> p ,Node1<Object> n){
		setElement(e);
		setPrev(p);
		setNext(n);
	}
	public void setElement(Object element) {
		this.element = element;
	}
	public Object getElement() {
		return element;
	}
	public void setPrev(Node1<Object> prev) {
		this.prev = prev;
	}
	public Node1<Object> getPrev() {
		return prev;
	}
	public void setNext(Node1<Object> next) {
		this.next = next;
	}
	public Node1<Object> getNext() {
		return next;
	}

	

}
