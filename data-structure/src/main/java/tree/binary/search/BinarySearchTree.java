package tree.binary.search;

interface BinarySearchTree {
    void makeEmpty();    // Throw the nodes away

    boolean isEmpty();    // judge the BST is empty

    Comparable find(Comparable x);    // R

    void insert(Comparable x);    // C

    void remove(Comparable x);    // D

    void printTree();        // print out the BST
}
