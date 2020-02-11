package pa1;

import java.util.ArrayList;
import java.util.List;

import api.Graph;
import api.TaggedVertex;

/**
 * WebGraph class representing our graph
 * @author Jeremy Galang and Ethan McGill
 *
 */
public class WebGraph implements Graph<String> {

	private ArrayList<WebNode<String>> gList = new ArrayList<WebNode<String>>(); // arraylist of WebNode objects

	public WebGraph() {

	}

	/**
	 * Prints the entire graph. For debugging purposes
	 */
	public String toString() {

		String ret = "----CURRENT GRAPH----\n";

		for (int i = 0; i <= gList.size() - 1; i++) {
			ret += "Node " + i + ":\n";
			WebNode cur = gList.get(i);
			String add = cur.toString();
			ret += add + "\n";

		}

		return ret;
	}

	/**
	 * Update a node based on a specified index.
	 * 
	 * @param index - the node's index
	 * @param node  - the actual WebNode object
	 */
	public void updateNode(int index, WebNode<String> node) {
		gList.set(index, node);
	}

	/**
	 * Get a particular node at a specific index
	 * 
	 * @param index - the node's index
	 * @return
	 */
	public WebNode getNode(int index) {
		return gList.get(index);

	}

	/**
	 * Add a node to the WebGraph
	 * 
	 * @param x - the node object we want to add
	 */
	public void addNode(WebNode<String> x) {

		gList.add(x);
	}

	/**
	 * Returns an ArrayList of the actual objects constituting the vertices of this
	 * graph.
	 * 
	 * @return ArrayList of objects in the graph
	 */
	@Override
	public ArrayList<String> vertexData() {

		ArrayList<String> ret = new ArrayList<String>();

		for (int i = 0; i < gList.size(); i++) {
			//return all the names
			ret.add(gList.get(i).getName());
		}

		return ret;
	}

	/**
	 * Returns an ArrayList that is identical to that returned by vertexData(),
	 * except that each vertex is associated with its incoming edge count.
	 * 
	 * @return ArrayList of objects in the graph, each associated with its incoming
	 *         edge count
	 */
	@Override
	public ArrayList<TaggedVertex<String>> vertexDataWithIncomingCounts() {
		ArrayList<TaggedVertex<String>> ret = new ArrayList<>();

		for (int i = 0; i < gList.size(); i++) {
			//add a new TaggedVertex Object
			ret.add(new TaggedVertex<String>(gList.get(i).getName(), gList.get(i).getIndegree()));

		}

		return ret;

	}

	/**
	 * Returns a list of outgoing edges, that is, a list of indices for neighbors of
	 * the vertex with given index. This method may throw
	 * ArrayIndexOutOfBoundsException if the index is invalid.
	 * 
	 * @param index index of the given vertex according to vertexData()
	 * @return list of outgoing edges
	 */
	@Override
	public List<Integer> getNeighbors(int index) {
		if (gList.get(index) == null) {
			throw new ArrayIndexOutOfBoundsException();
		}

		return gList.get(index).getOutgoing();
	}

	/**
	 * Returns a list of incoming edges, that is, a list of indices for vertices
	 * having the given vertex as a neighbor. This method may throw
	 * ArrayIndexOutOfBoundsException if the index is invalid.
	 * 
	 * @param index index of the given vertex according to vertexData()
	 * @return list of incoming edges
	 */
	@Override
	public List<Integer> getIncoming(int index) {
		if (gList.get(index) == null) {
			throw new ArrayIndexOutOfBoundsException();
		}

		return gList.get(index).getIncoming();

	}
	
	public ArrayList<TaggedVertex<String>> vertexDataWithIndegree(){
		ArrayList<TaggedVertex<String>> ret = new ArrayList<>();

		for (int i = 0; i < gList.size(); i++) {
			//add a new TaggedVertex Object
			ret.add(new TaggedVertex<String>(gList.get(i).getName(), gList.get(i).getIndegree()));

		}

		return ret;
	}

}
