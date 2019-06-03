package vector;

class ExtendableArrayVector extends ArrayVector {
	public ExtendableArrayVector(int capacity) {
		super(capacity);
	}

	// all we need to do is overload one method!
	@Override
	public void insertAtRank(int r, Object o) throws InvalidRankException {
		if (n == N) { // over capacity!
			N *= 2;
			Object s2[] = new Object[N];
			for (int i=0; i<n; i++)
				s2[i]=s[i];
			s = s2;
		}
		// now the original implementation does the job!
		super.insertAtRank(r,o); // call ArrayVector’s method
	}
}