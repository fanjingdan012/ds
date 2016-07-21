package util;


public class Queue {


	/**
	 * Add your own fields/methods/classes below
	 * And finish the following methods
	 */
	Object[] deque;
    int f = 0;
    int r = 0;
    int cap = 0;
	public Queue (int capacity) {
		deque = new Object[capacity + 1];
		cap = capacity;
		f = r = 0;
	}



	public boolean enqueue(Object elem) {
		if(size() == cap){
			return false;
		}
		else{
			deque[r]=elem;
			if(r == cap){
				r = 0;
			}
			else{
				r++;
			}
			return true;
		}
	}


	public Object peekFirst() {
		return deque [f];
	}

	
	public Object peekLast() {
		int r1 = 0;
		if(r == 0){
			r1 = cap ;
		}
		else{
			r1 = r - 1;
		}
		return deque[r1];
	}


	public Object dequeue() {
		if(size()==0){
			System.out.println("error:queue is empty");
			return null;
		}
		else{
			Object temp = deque[f];
			deque[f] = null;
			if(f == cap){
				f = 0;
			}
			else{
				f++;
			}
			return temp;
		}
	}


	
	public int size() {
		if(f == 0 && r == cap){
			return cap ;
		}
		else if(f - 1 == r){
			return cap;
		}
		else{
			return ((cap + 1) - f + r) % (cap + 1);
		}
	}
	public boolean isEmpty(){
		return size()==0;
	}

}
