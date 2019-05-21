package tree.binary;

public interface BTNode {
    public Object element();

    public void setElement(Object v);

    public BTNode left();

    public void setLeft(BTNode p);

    public BTNode right();

    public void setRight(BTNode p);

    public boolean isLeaf();
}
