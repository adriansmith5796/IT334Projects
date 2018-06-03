package implementations;

public class RedBlackTree<T> {
    // root of tree
    private Node root;

    // internal class that defines a node's structure
    // we will be accessing the internal members of this
    // class directly
    protected class Node {
	private T element;

	private Node left;
	private Node right;

	// constructor for Node
	public Node(T element) {
	    this.element = element;
	    this.left = null;
	    this.right = null;
	}

	public boolean isLeaf() {
	    if (this.left == null && this.right == null) {
		return true;
	    }

	    return false;
	}
    }

    public void rotateLeft(Node h) {
	// Pseudo code
	/*
	 * input: node h has a right-leaning red link
	 * output: node x at top of subtree originally rooted at h
	 * 	has balanaced R-B Trees
	 * 
	 * set node x = h's right child
	 * move x's left child to h's right child
	 * make h the left child of x
	 * set x's color to h's color
	 * set h's color to red
	 * return x
	 */
    }
    
    public void rotateRight(Node h) {
	// Pseudo code
	/*
	 * input: node h has a right-leaning red link
	 * output: node x at top of subtree originally rooted at h
	 * 	has balanaced R-B Trees
	 * 
	 * node x = h.left
	 * h.left = x.right
	 * x.right = h
	 * x.color = h.color
	 * h.color = RED
	 * return x
	 */
    }
}
