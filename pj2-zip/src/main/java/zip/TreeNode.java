package zip;


public class TreeNode{
    public TreeNode parent = null;
	public TreeNode leftChild = null;
	public TreeNode rightChild = null;
	public TreeNode prev = null;
	public TreeNode next = null;
	public int f = 0;
	public String code = "0";
	public char element='#';
	public int depth = 0;
	public boolean isLeaf = true;
	//constructor
	
	public TreeNode(char element,int f){
		this.f = f;
		this.element = element;		
	}

	public TreeNode(int f) {
		isLeaf = false;
		this.f = f;
	
	}
	
	
}