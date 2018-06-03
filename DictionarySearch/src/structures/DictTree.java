/*
 * Adrian Smith
 * 3/6/18
 * Dictionary Search API
 * IT333
 * 
 * 
 */

package structures;

import java.io.*;
import java.util.*;

/**
 * This class is a binary search tree that reads in a file of word-definition
 * pairs from save.txt and puts the values into an array that gets sorted. Then,
 * it picks the middle element of the array and inserts it into the tree while
 * picking the middle element of the left and right sub-arrays and inserting
 * those. This ensures that the tree stays balanced no matter how unsorted the
 * new dictionary is. The save file preserves the order of the tree so that when
 * it's loaded it's the exact same tree.
 * 
 * @author adriansmith
 *
 */
public class DictTree {
    private Node root;
    private int size;

    /**
     * This class is a custom Node class that has a key (word) and a value
     * (definition). It implements Comparable so it can be sorted by the Arrays
     * static class.
     * 
     * @author adriansmith
     *
     */
    private class Node implements Comparable<Object> {
	String key;
	String value;
	Node left;
	Node right;

	/**
	 * Takes a word-definition pair and parses out the word and definition and puts
	 * them in the appropriate spots.
	 * 
	 * @param line
	 *            Word-definition pair
	 */
	public Node(String line) {
	    String[] pair = line.split(":", 2);

	    this.key = pair[0].trim();
	    this.value = pair[1].trim();
	    this.left = null;
	    this.right = null;
	}

	/**
	 * Takes a word for key and the word's definition for value.
	 * 
	 * @param key
	 *            Word
	 * @param value
	 *            Definition
	 */
	public Node(String key, String value) {
	    this.key = key.trim();
	    this.value = value.trim();
	    this.left = null;
	    this.right = null;
	}

	/**
	 * Word and definition stored in the fields
	 */
	public String toString() {
	    return this.key + ": " + this.value;
	}

	@Override
	public int compareTo(Object node) {
	    return this.key.compareTo(node.toString());
	}

    }

    /**
     * Constructs a tree if a key (word) and value (definition) are provided.
     * 
     * @param key
     *            Word
     * @param value
     *            The word's definition
     */
    public DictTree(String key, String value) {
	Node node = new Node(key, value);
	this.root = node;
    }

    /**
     * Simply constructs an empty tree.
     */
    public DictTree() {
	this.root = null;
    }

    /**
     * This reads in the possibly unsorted dictionary file and adds each
     * word-definition pair to an array. Then the current tree gets added to the
     * array as well so that it can be resorted to maintain balance.
     * 
     * @param fileName
     *            The name of the file being read
     */
    public void read(String fileName) {
	Node[] nodes = new Node[100];
	File file = new File(fileName);
	int index = 0;
	FileReader instream = null;

	// Reads in the file
	try {
	    instream = new FileReader(file);
	    BufferedReader input = new BufferedReader(instream);
	    String line = null;

	    while ((line = input.readLine()) != null) {
		if (index == nodes.length) { // Array is full
		    Node[] newArray = new Node[nodes.length * 2];
		    for (int i = 0; i < nodes.length; i++) {
			newArray[i] = nodes[i];
		    }
		    nodes = newArray;
		}
		nodes[index++] = new Node(line);
	    }

	    Node[] newLines = new Node[index + this.size];

	    // Adds the new pairs to the array
	    for (int i = 0; i < index; i++) {
		newLines[i] = nodes[i];
	    }

	    Node[] treeNodes = new Node[this.size];
	    treeNodes = this.getNodes();

	    // Adds the current tree to the array
	    for (int i = 0; i < this.size; i++) {
		newLines[i + index] = treeNodes[i];
	    }

	    nodes = newLines;
	    // Sorts and reinserts into tree
	    Arrays.sort(nodes);
	    addArray(nodes, 0, nodes.length - 1);

	    input.close();
	} catch (Exception e) {} // Do nothing
    }

    /**
     * Helper method that iteratively goes through the tree and adds each Node to an
     * array.
     * 
     * @return Array of the tree's current nodes
     */
    private Node[] getNodes() {
	Node current = root;
	Node[] nodes = new Node[20];
	Queue<Node> queue = new LinkedList<>();
	int index = 0;
	queue.add(current);

	while (!queue.isEmpty()) {
	    current = queue.remove();
	    nodes[index++] = current;
	    if (current.left != null)
		queue.add(current.left);
	    if (current.right != null)
		queue.add(current.right);
	}

	return nodes;
    }

    /**
     * Instantiates a Node object with the key and value.
     * 
     * @param key
     *            Word
     * @param value
     *            Definition
     */
    public void add(String key, String value) {
	add(new Node(key, value));
    }

    /**
     * This method is used by class methods clients won't have access to the Node
     * class.
     * 
     * @param node
     *            Node object being added.
     * 
     */
    private void add(Node node) {
	Node prev = root;
	Node current = root;
	int comp = 0;

	while (current != null) {
	    comp = node.key.compareTo(current.key);

	    if (comp < 0) {
		prev = current;
		current = current.left;
	    } else if (comp > 0) {
		prev = current;
		current = current.right;
	    } else {
		current.value = node.value;
		return;
	    }
	}

	if (comp > 0) {
	    prev.right = node;
	    this.size++;
	} else if (comp < 0) {
	    prev.left = node;
	    this.size++;
	} else if (root == null) {
	    root = node;
	    this.size++;
	}
    }

    /**
     * Adds an array recursively to the tree by choosing the middle element and then
     * the middle elements of the left and right sub-arrays and so on and so forth.
     * This insures that the tree is balanced but it also assumes that the array
     * being added is sorted (that's why it's private).
     * 
     * @param nodes
     *            Array being added
     * @param low
     *            The bottom index being considered
     * @param high
     *            The highest index being considered
     */
    private void addArray(Node[] nodes, int low, int high) {
	if (high < low)
	    return;

	// Middle element of array
	int mid = ((high - low) / 2) + low;

	// Add middle node between low and high
	add(nodes[mid]);
	// Add left sub array
	addArray(nodes, low, mid - 1);
	// Add right sub array
	addArray(nodes, mid + 1, high);
    }

    /**
     * Gets the definition of the word sent in.
     * 
     * @param key
     *            Word being searched for
     * @return The definition if found, "Word not Found!" if not
     */
    public String get(String key) {
	Node current = root;

	while (current != null) {
	    int comp = key.compareTo(current.key);

	    if (comp > 0)
		current = current.right;
	    else if (comp < 0)
		current = current.left;
	    else
		return current.value;
	}

	return "No Definition Found";
    }

    /**
     * Returns the size of the tree.
     * 
     * @return Size of tree
     */
    public int size() {
	return this.size;
    }

    /**
     * Clears/Deletes the current tree.
     */
    public void clear() {
	this.root = null;
	this.size = 0;
    }

    /**
     * Saves the tree to a file called save.txt while preserving it's order.
     */
    public void save() {
	if (root == null) {
	    return;
	}

	try (PrintWriter out = new PrintWriter("save.txt")) {

	    Queue<Node> nodes = new LinkedList<Node>();
	    Node current = root;
	    nodes.add(current);

	    while (!nodes.isEmpty()) {
		current = nodes.remove();
		out.println(current.toString());
		if (current.left != null)
		    nodes.add(current.left);
		if (current.right != null)
		    nodes.add(current.right);
	    }

	} catch (FileNotFoundException e) {}
    }

    /**
     * Loads the save.txt file and replaces whatever tree currently exists.
     */
    public void loadSaved() {
	this.clear();
	Node[] nodes = new Node[100];
	File file = new File("save.txt");
	FileReader instream = null;

	// Reads in the file
	try {
	    instream = new FileReader(file);
	    BufferedReader input = new BufferedReader(instream);
	    String line = null;

	    while ((line = input.readLine()) != null) {
		add(new Node(line));
	    }

	    input.close();
	} catch (Exception e) {}
    }

    /**
     * Returns the save.txt file representation of the tree's current state.
     */
    public String toString() {
	Queue<Node> nodes = new LinkedList<Node>();
	Node current = root;
	nodes.add(current);
	String dictionary = "";

	while (!nodes.isEmpty()) {
	    current = nodes.remove();
	    dictionary += current.toString() + "\n";
	    if (current.left != null)
		nodes.add(current.left);
	    if (current.right != null)
		nodes.add(current.right);
	}

	return dictionary;
    }
}
