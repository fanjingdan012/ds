package vector;

public interface Vector {
    public int size();

    public boolean isEmpty();

    public Object elemAtRank(int r) throws InvalidRankException;

    public Object replaceAtRank(int r, Object o) throws InvalidRankException;

    public void insertAtRank(int r, Object o) throws InvalidRankException;

    public Object removeAtRank(int r) throws InvalidRankException;
}

class InvalidRankException extends Exception {


}