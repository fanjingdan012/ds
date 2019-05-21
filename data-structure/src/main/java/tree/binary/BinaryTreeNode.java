package tree.binary;

import queue.EmptyQueueException;
import queue.Queue;
import stack.Stack;


public class BinaryTreeNode implements BTNode{
    Object element;
    BTNode left_child, right_child;
    BTNode parent;

    @Override
    public Object element() {
        return element;
    }

    @Override
    public void setElement(Object v) {
        element = element;
    }

    @Override
    public BTNode left() {
        return left_child;
    }

    @Override
    public void setLeft(BTNode p) {
        left_child = p;
    }

    @Override
    public BTNode right() {
        return right_child;
    }

    @Override
    public void setRight(BTNode p) {
        right_child=p;
    }

    @Override
    public boolean isLeaf() {
        return left_child==null&&right_child==null;
    }

    void visit(BTNode st) {
        System.out.println(st.element());
    }

    void preorder(BTNode st) {
        if (st == null) return;
        visit(st);
        preorder(st.left());
        preorder(st.right());
    }

    void inorder(BTNode st) {
        // in order tree traversal
        if (st == null) return;
        inorder(st.left());
        visit(st);
        inorder(st.right());
    }

    void postorder(BTNode st) {
        if (st == null) return;
        postorder(st.left());
        postorder(st.right());
        visit(st);
    }

    void levelorder(BTNode st) {   // level order tree traversal
        Queue q = new Queue(100);
        if (st == null) return;    // empty tree
        q.enqueue(st);        // push the root onto queue
        for (; ; ) {
            try {
                if (!q.isEmpty()) {
                    st = (BTNode)q.dequeue();    //dequeue one element from queue
                    visit(st);
                    if (st.left() != null)    //if left child is not NULL
                        q.enqueue(st.left());    // then push it onto queue
                    if (st.right() != null)    // if right child is not NULL
                        q.enqueue(st.right());    // then push it onto queue
                } else
                    break;         // if queue is empty, then finish
            } catch (EmptyQueueException e) {
                break;        // end if throw a QueueEmptyException
            }
        }  // end for-loop
    }

    //
    void preorderNonRecursive(BTNode st) {
        Stack stack = new Stack();
        if (st == null) return;
        stack.push(st);
        while (!stack.isEmpty()) {
            BTNode cur = (BTNode) stack.pop();
            while (cur != null) {
                visit(cur);            // visit parent of children
                stack.push(cur.right()); //push right child for coming visit
                cur = cur.left();        // go to its left child
            }
        }
    }

    void inorderNonRecursive (BTNode rt){
        Stack stack = new Stack();
        for (;;){
            for (; rt!=null; rt = rt.left());
            stack.push (rt);
            rt = (BTNode)stack.pop();
            if (rt == null)
                break;
            visit (rt);
            rt = rt.right();
        }
    }

    void postorderNonRecursive (BTNode rt){
        boolean tagTurn = true; // tag turn left
        Stack stack = new Stack();
        do {
            while (rt != null && tagTurn){
                stack.push(rt);
                rt = rt.left();
            }
            rt = (BTNode)stack.top();
            if (rt == null) {
                stack.pop();
                rt = (BTNode)stack.pop();
                visit(rt);
                tagTurn = false;
            }
            else {
                stack.push(null); // null means for back
                rt=rt.right();
                tagTurn = true;
            }
        } while (!stack.isEmpty());
    }



}
