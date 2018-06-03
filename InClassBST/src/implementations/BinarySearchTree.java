package implementations;

// Binary Search Tree that has an invariant that no duplicate data is allowed
public class BinarySearchTree<T extends Comparable<? super T>> {
	
	// root of tree
	private Node root;
	private int size;
	
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
		
		public String toString() {
		    String data = (this.element == null) ? "null" : this.element.toString();
		    String leftChild = (this.left == null) ? "null" : this.left.toString();
		    String rightChild = (this.right == null) ? "null" : this.right.toString();
		    
		    return leftChild + " <-- " + data + " --> " + rightChild;
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
		size++;
	}
	
	// add method - iterative version
	public boolean add(T element) {
		// edge case -- is the tree empty?
		if (root == null) {
			root = new Node(element);
			size++;
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
			} else if (comp > 0) {  // if comp > 0, go right
				prev = current;
				current = current.right;
			} else { // duplicate
				return false;
			}
		}
		
		// at a leaf's null child
		if (comp < 0) {
			prev.left = new Node(element);
			size++;
		} else {
		    prev.right = new Node(element);
		    size++;
		}
		
		return true;
	}
	
	public int size() {
	    return this.size;
	}
	
	public boolean isEmpty() {
	    return this.size == 0;
	}
	
	public void clear() {
	    this.root = null;
	    size = 0;
	}
	
	public boolean contains(T element) {
	    return contains(element, root);
	}
	
	private boolean contains(T element, Node current) {
	    if(current == null) {
		return false;
	    }
	    
	    int comp = element.compareTo(current.element);
	    
	    if(comp < 0) {
		return contains(element, current.left);
	    }else if(comp > 0) {
		return contains(element, current.right);
	    }else {
		return true;
	    }
	}
	
	public void delete(T element) {
	    
	}
	
	public Node delete(T element, Node current) {
	    if(current == null) {
		return current;
	    }
	    
	    int comp = element.compareTo(current.element);
	    
	    if(comp < 0) {
		current.left = delete(element, current.left);
	    }else if(comp > 0) {
		current.right = delete(element, current.right);
	    }else {
		if(current.right == null) {
		    current = current.left;
		    size--;
		}else if(current.left == null) {
		    current = current.right;
		    size--;
		}else {
		    Node minRight = findMin(current.right);
		    current.element = minRight.element;
		    
		}
	    }
	    
	    return current;
	    
	}
	
	private Node findMin(Node node) {
	    if(node == null) {
		return null;
	    }else if(node.left == null) {
		return node;
	    }
	    
	    return findMin(node.left);
	}

	/*********TRAVERSALS*****************************/
	
	// recursive version of inorder traversal
	public void recursiveInOrder() {
		recursiveInOrder(root);
	}
	
	// overloaded method
	public void recursiveInOrder(Node n) {
		if (n == null) return;
		recursiveInOrder(n.left);
		System.out.println(n.element);
		recursiveInOrder(n.right);
	}

}