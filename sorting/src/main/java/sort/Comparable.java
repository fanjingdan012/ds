package sort;

class Comparable{
    int key = 0;
    public Comparable(int key){
    	this.key = key;
    }

	public int compareTo(Comparable r) {
		if(this.key>r.key){
			return 1;
		}
		else if(this.key<r.key){
			return -1;
		}
		else{
			return 0;
		}
	}
	public int compareTo(int r) {
		if(this.key>r){
			return 1;
		}
		else if(this.key<r){
			return -1;
		}
		else{
			return 0;
		}
	}

	public String toString(){
		return key+"";
		
	}

	public boolean equals(Object o){
    	if(o instanceof Comparable){
			if(((Comparable) o).key==this.key){
				return true;
			}
		}
    	return false;

	}
	
	
}