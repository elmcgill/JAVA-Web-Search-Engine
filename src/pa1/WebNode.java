package pa1;

import java.util.ArrayList;

/**
 * WebNode class that represents a Node object
 * @author Jeremy Galang and Ethan McGill
 *
 * @param <E>
 */
public class WebNode<E> {

	private ArrayList<Integer> inc = new ArrayList<Integer>(); // incoming edges
	private ArrayList<Integer> out = new ArrayList<Integer>(); // outgoing edges

	private String name; // name of the node
	private int index; // node's index
	private int depth; // nodes depth on the graph
	private int indegree;

	/**
	 * Constructor for the WebNode class
	 * 
	 * @param nodeName - the URL link
	 * @param index    - the index to store the node at
	 * @param depth    - depth of the node
	 */
	public WebNode(String nodeName, int index, int depth, int indegree) {
		this.name = nodeName;
		this.index = index;
		this.depth = depth;
		this.indegree = indegree;
	}

	/**
	 * 
	 * @param node
	 * @return Name of the node
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the index of a particular node
	 */
	public int getIndex() {
		return index;

	}

	/**
	 * @return The current depth level the node is at
	 */
	public int getDepth() {
		return depth;
	}
	
	public int getIndegree() {
		return indegree;
	}
	
	public void incrementIndegree() {
		indegree++;
	}

	/**
	 * Add specified incoming node
	 * 
	 * @param node
	 */
	public void addInc(int node) {

		inc.add(node);

	}

	/**
	 * Add specified outgoing node
	 * 
	 * @param node
	 */
	public void addOut(int node) {

		out.add(node);

	}

	/**
	 * @return All of the node's incoming edges
	 */
	public ArrayList<Integer> getIncoming() {
		return inc;

	}

	/**
	 * @return All of the node's outgoing edges
	 */
	public ArrayList<Integer> getOutgoing() {
		return out;
	}

	/**
	 * For debugging purposes, simply prints the node's depth, name, incoming edges and outgoing edges
	 */
	public String toString() {
		String ret = "Depth: " + depth + "\nName: " + name + "\nIncoming edges: ";
		for (int i = 0; i <= inc.size() - 1; i++) {
			ret += inc.get(i) + " ";
		}
		ret += "\nOutgoing edges: ";

		for (int i = 0; i <= out.size() - 1; i++) {
			ret += out.get(i) + " ";
		}

		return ret;

	}

}
