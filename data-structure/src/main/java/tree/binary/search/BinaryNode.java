package tree.binary.search;

public class BinaryNode {
    BinaryNode (Comparable theElement){
        this (theElement, null, null);
    }
    BinaryNode (Comparable theElement, BinaryNode lt, 			BinaryNode rt){
        element = theElement; left = lt; right = rt;
    }
    Comparable element;
    BinaryNode left;
    BinaryNode right;








}