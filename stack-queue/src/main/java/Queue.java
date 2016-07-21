

//Implement the queue ADT using an array
public class Queue {
	Object[] queue;
    int f = 0;
    int r = 0;
    int cap = 0;
    public Queue (int capacity) {
		queue = new Object[capacity + 1];
		cap = capacity;
		f = r = 0;
	}
	public boolean enqueue(Object elem) {
		if(size() == cap){
			return false;
		}
		else{
			queue[r]=elem;
			if(r == cap){
				r = 0;
			}
			else{
				r++;
			}
			return true;
		}
			
	}



	//@Override
	public Object peekLast() {
		int r1 = 0;
		if(r == 0){
			r1 = cap ;
		}
		else{
			r1 = r - 1;
		}
		return queue[r1];
	}

	//@Override
	public Object dequeue() {
		if(size()==0){
			System.out.println("error:Dequeue is empty");
			return null;
		}
		else{
			Object temp = queue[f];
			queue[f] = null;
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
    public Object front()throws EmptyQueueException{
    	return queue [f];
    	
    }
    
}
class EmptyQueueException extends Exception {

	public EmptyQueueException(String string) {
		System.out.println(string);
		// TODO Auto-generated constructor stub
	}

}