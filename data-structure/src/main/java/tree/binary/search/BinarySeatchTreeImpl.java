package tree.binary.search;

public class BinarySeatchTreeImpl implements BinarySearchTree {
    private BinaryNode root;

    @Override
    public void makeEmpty() {

    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public Comparable find(Comparable x) {
        return findHelper(x, root).element;
    }

    private BinaryNode findHelper (Comparable x, BinaryNode t){
        if (null == t)
            return null;
        if (x.compareTo(t.element) < 0)
            return findHelper(x, t.left);
        else if (x.compareTo (t.element) > 0)
            return findHelper(x, t.right);
        else
            return t;
    }
    private BinaryNode findHelperNonRecursive (Comparable x, BinaryNode t){
        Comparable it;
        while  (null != t) {
            it = t.element;
            if (x.compareTo(it) < 0)
                t = t.left;
                //move down along left path
            else if (x.compareTo(it) == 0)    // found
                return t;
            else
                t = t.right;
            // move hile-loop
            return null;   // not found
            //down along right path
        }//end w	// iterative version of search in BST
        return null;
    }

    @Override
    public void insert(Comparable x) {
        insert(x,root);
    }
    private BinaryNode insert (Comparable x, BinaryNode t){
        if (null  == t) 	// if root is null
            // new a BinNode
            return new BinaryNode(x);
        else if (x.compareTo(t.element)<0)
            t.left = insert(x, t.left);
        else if (x.compareTo(t.element)>0)
            t.right = insert(x, t.right);
        else
            ;
        return t;
    }

    @Override
    public void remove(Comparable x) {
        root = remove(x, root);
    }

    private BinaryNode remove(Comparable x, BinaryNode t) {
        if (null == t) return null;
        if (x.compareTo(t.element) < 0)
            t.left = remove(x, t.left);
        else if (x.compareTo(t.element) > 0)
            t.right = remove(x, t.right);
        else {
            if (null == t.left) t = t.right;
            else if (null == t.right) t = t.left;
            else {
                t.element = findMin(t.right).element;
                t.right = remove(t.element, t.right);
            }
        }
        return t;
    }

    private BinaryNode findMin(BinaryNode t) {
        if (null == t) return null;
        if (t.left==null){
            return t;
        }
        return findMin(t.left);
    }


    @Override
    public void printTree() {

    }
}
