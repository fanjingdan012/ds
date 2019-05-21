package stack;

//Implement the stack ADT with a doubly linked list
public class Stack {
	protected Node<Object> top;
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
			throw new EmptyStackException("Exception:stack.Stack is empty");
		}
		return top.getElement();
		
	}
    public void push(Object element){
		Node<Object> v = new Node<Object>(element,null,top);
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
			throw new EmptyStackException("Exception:stack.Stack is empty");
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
class Node<Object>{
	private Object element;
	private Node<Object> prev;
	private Node<Object> next;
	public Node(Object e ,Node<Object> p ,Node<Object> n){
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
	public void setPrev(Node<Object> prev) {
		this.prev = prev;
	}
	public Node<Object> getPrev() {
		return prev;
	}
	public void setNext(Node<Object> next) {
		this.next = next;
	}
	public Node<Object> getNext() {
		return next;
	}

	

}
class EmptyStackException extends RuntimeException {

	public EmptyStackException(String string) {
		System.out.println(string);
		// TODO Auto-generated constructor stub
	}

}
