package vector;

public class ArrayVector implements Vector {

    protected int N; // max capacity
    protected int n; // no. elements stored	protected Object s[];
    Object[] s;

    public ArrayVector(int capacity) {
        N = capacity;
        n = 0;
        s = new Object[N];
    }

    public ArrayVector() {
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public Object elemAtRank(int r) throws InvalidRankException {
        return s[r];
    }

    @Override
    public Object replaceAtRank(int r, Object e) throws InvalidRankException {
        Object x = elemAtRank(r);
        s[r] = e;
        return x;

    }

    @Override
    public void insertAtRank(int r, Object e) throws InvalidRankException {
        for (int i = n - 1; i >= r; i--) {
            s[i + 1] = s[i];
        }
        s[r] = e;
        n = n + 1;
    }

    @Override
    public Object removeAtRank(int r) throws InvalidRankException {
        Object e = s[r];
        for (int i = r;i<n-1;i++)
            s[i] = s[i + 1];
        n = n - 1;
        return e;

    }
}
