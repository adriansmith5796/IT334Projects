/*
 * Adrian Smith
 * 2/16/18
 * 
 * Binary Search Tree Pair Program
 * 
 * The methods implemented are at the bottom.
 * Iterative contains, recursive preorder and post order
 * traversal, and recursive countLeaves methods were implemented.
 */

package implementation;

import java.util.Stack;

// Binary Search Tree that has an invariant that no duplicate data is allowed
public class BinarySearchTree<T extends Comparable<? super T>> {

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

    // constructor for the tree itself
    public BinarySearchTree() {
	root = null;
    }

    // constructor for the tree that takes a starting
    // root node
    public BinarySearchTree(T element) {
	root = new Node(element);
    }

    // add method - iterative version
    public boolean add(T element) {
	// edge case -- is the tree empty?
	if (root == null) {
	    root = new Node(element);
	    return true;
	}

	// walk down the tree to the insertion point
	Node current = root;
	Node prev = root;
	int comp = 0;
	while (current != null) {
	    // compare the keys
	    comp = element.compareTo(current.element);

	    // if comp < 0, go left
	    if (comp < 0) {
		prev = current;
		current = current.left;
	    } else if (comp > 0) { // if comp > 0, go right
		prev = current;
		current = current.right;
	    } else { // duplicate
		return false;
	    }
	}

	// at a leaf's null child
	if (comp < 0) {
	    prev.left = new Node(element);
	} else
	    prev.right = new Node(element);

	return true;
    }

    // recursive version of inorder traversal
    public void recursiveInOrder() {
	recursiveInOrder(root);
    }

    // overloaded method
    public void recursiveInOrder(Node n) {
	if (n == null)
	    return;
	recursiveInOrder(n.left);
	System.out.println(n.element);
	recursiveInOrder(n.right);
    }

    // Iterative in-order traversal
    public void inOrder() {
	if(root == null) 
	    return;
	
	Stack<Node> stack = new Stack<Node>();
	Node current = root;
	
	// push starting at root
	stack.push(current);
	
	// go down left branch, adding nodes as we go
	while(current.left != null) {
	    stack.push(current.left);
	    current = current.left;
	}
	
	// start popping nodes off stack one at a time
	// if node has right child, go down right subtree's 
	// left branch
	while(stack.size() > 0) {
	    current = stack.pop();
	    System.out.println(current.element);
	    
	    if(current.right != null) {
		stack.push(current.right);
		current = current.right;
		
		// does right child (current) have left subtree
		while(current.left != null) {
		    stack.push(current.left);
		    current = current.left;
		}
	    }
	}
    }

    /********* METHODS ADDED FOR PAIR PROGRAM ************/

    // Iterative contains method
    public boolean contains(T element) {
	Node current = root;

	while (current != null) {
	    int comp = element.compareTo(current.element);

	    if (comp < 0) {
		current = current.left;
	    } else if (comp > 0) {
		current = current.right;
	    } else {
		return true;
	    }
	}

	return false;
    }

    // Recursive Pre Order traversal
    public void recursivePreOrder() {
	recursivePreOrder(root);
    }

    private void recursivePreOrder(Node node) {
	if (node == null) {
	    return;
	}

	System.out.println(node.element);
	recursivePreOrder(node.left);
	recursivePreOrder(node.right);
    }

    // Recursive post order traversal
    public void recursivePostOrder() {
	recursivePostOrder(root);
    }

    private void recursivePostOrder(Node node) {
	if (node == null) {
	    return;
	}

	recursivePostOrder(node.left);
	recursivePostOrder(node.right);
	System.out.println(node.element);
    }

    // Recursive method that counts all childless nodes
    public int countLeaves() {
	return countLeaves(root);
    }

    private int countLeaves(Node node) {
	// Base case
	if (node == null) {
	    return 0;
	}

	if (node.left != null || node.right != null) { // leaf found
	    return countLeaves(node.left) + countLeaves(node.right);
	} else { // keep traversing
	    return 1;
	}
    }
}