package pa1;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.UnsupportedMimeTypeException;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import api.Graph;
import api.Util;

/**
 * Implementation of a basic web crawler that creates a graph of some portion of
 * the world wide web.
 *
 * @author Jeremy Galang and Ethan Mcgill
 * @param <E>
 */

public class Crawler {

	private String seedUrl;
	private int maxDepth;
	private int maxPages;

	/**
	 * Constructs a Crawler that will start with the given seed url, including only
	 * up to maxPages pages at distance up to maxDepth from the seed url.
	 * 
	 * @param seedUrl
	 * @param maxDepth
	 * @param maxPages
	 */
	public Crawler(String seedUrl, int maxDepth, int maxPages) {
		this.seedUrl = seedUrl;
		this.maxDepth = maxDepth;
		this.maxPages = maxPages;

	}

	/**
	 * 
	 * Helper method to determine if the a link start with http to ignore links that
	 * start with mailto
	 * 
	 * @param url
	 * @return
	 */
	private boolean isHTTP(String url) {
		return (url.charAt(0) == 'h' && url.charAt(1) == 't' && url.charAt(2) == 't' && url.charAt(3) == 'p');
	}

	/**
	 * 
	 * Helper that cleans up the implementation of BFS below
	 * 
	 * @param url
	 * @return
	 */
	private Document getDoc(String url) {

		Document toReturn = null;

		CallCounter counter = CallCounter.getInstance();

		if (counter.getCount() % 50 == 0 && counter.getCount() != 0) { // Check if we have made 50 requests
			try {
				System.out.println("Waiting...");	
				Thread.sleep(3000);

			} catch (InterruptedException ignore) {

			}
		}

		try {

			toReturn = Jsoup.connect(url).get(); // connect to the url

			counter.madeCall(); // tell the CallCounter we made a connection

		} catch (UnsupportedMimeTypeException e) {
			return null;

		} catch (HttpStatusException e) {
			return null;

		} catch (IOException e) {
			return null;

		}

		return toReturn;
	}

	/**
	 * Creates a web graph for the portion of the web obtained by a BFS of the web
	 * starting with the seed url for this object, subject to the restrictions
	 * implied by maxDepth and maxPages.
	 * 
	 * @return an instance of Graph representing this portion of the web
	 */
	public Graph<String> crawl() {

		HashMap<String, Integer> discovered = new HashMap<String, Integer>(); // String - link, Integer - index

		Queue<String> queue = new LinkedList<>(); // Queue holds the name of the next node to BFS

		WebGraph graph = new WebGraph();

		Document doc = null;
		int index = 0; // current index of the node
		int depth = 1; // depth of the nodes being discovered
		int c = 0; // number of jsoup requests made
		String endOfDepth = seedUrl; // Know when to increment the depth of nodes
		Document temp = null;

		// Dont go on if the root is invalid
		if (getDoc(seedUrl) == null) {
			return null;
		}

		queue.add(seedUrl); // add the first node of the graph to the queue to start BFS

		discovered.put(seedUrl, index); // Store the root node name with the index of the node into discovered

		WebNode node = new WebNode(seedUrl, index, 0, 1); // create a new node object for the root node

		graph.addNode(node); // Add the root node object to our webgraph

		while (!queue.isEmpty()) { // Start BFS
			String current = queue.remove(); // Set current to the first node in the queue

			HashMap<String, Boolean> seenByCurrent = new HashMap<>();

			doc = getDoc(current); // Store the current page

			// get the links from the document text
			if(doc != null) {
				Elements links = doc.select("a[href]"); // Get all of the out going edges from the current page
				
				for (Element link : links) {

					String childNodeName = link.attr("abs:href"); // get the link of the next outgoing node
					// make sure it's a non-bookmarked link with a valid MIME type and that it is
					// in fact an http link
					if (!Util.ignoreLink(current, childNodeName) && isHTTP(childNodeName)) {

						// Actual Logic
						// make sure we can continue adding nodes or go down another layer
						if (index < this.maxPages - 1 && depth <= this.maxDepth) {

							// if the node hasn't already been discovered
							if (discovered.get(childNodeName) == null && seenByCurrent.get(childNodeName) == null) {

								index++; // increment the index up by one (index = the number of pages we have found
											// after the root)
								int parentIndex = discovered.get(current); // get the index of the parent node
								WebNode newChild = new WebNode(childNodeName, index, depth,1);
								// create a new node object with link as name and specified index

								newChild.addInc(parentIndex); // and an incoming edge from the parent node to the
																// childnode

								graph.addNode(newChild); // add the new node to the graph at index

								WebNode parent = graph.getNode(parentIndex); // add an out going edge from parent to the
																				// child node

								parent.addOut(index); // add outgoing edge

								graph.updateNode(parentIndex, parent); // update the parent node

								discovered.put(childNodeName, index); // Add the new found node to the discovered HashMap

								seenByCurrent.put(childNodeName, true); // put the child node in the HashMap

								queue.add(childNodeName);

							} else if (discovered.get(childNodeName) != null && seenByCurrent.get(childNodeName) == null) {
								// if the child has been discovered and hasn't been seen by the current node

								int parentIndex = discovered.get(current); // get the index of the parent node
								int childNodeIndex = discovered.get(childNodeName);

								WebNode child = graph.getNode(childNodeIndex);

								child.addInc(parentIndex); // add the parent to the child's incoming edges
								child.incrementIndegree();

								graph.updateNode(childNodeIndex, child); // update the child node

								WebNode parent = graph.getNode(parentIndex); // add an outgoing edge from the current node
																				// to the child node

								parent.addOut(childNodeIndex); // add the child node as the parent's outgoing node

								graph.updateNode(parentIndex, parent); // update the parent node

								seenByCurrent.put(childNodeName, true); // put the child node in the HashMap

							}

						} else {

							if (discovered.get(childNodeName) != null && seenByCurrent.get(childNodeName) == null) {

								int parentIndex = discovered.get(current); // get the index of the parent node
								int childNodeIndex = discovered.get(childNodeName);

								WebNode child = graph.getNode(childNodeIndex);

								child.addInc(parentIndex);
								child.incrementIndegree();

								graph.updateNode(childNodeIndex, child);

								WebNode parent = graph.getNode(parentIndex); // add an outgoing edge from the
																				// current node to the child node

								parent.addOut(childNodeIndex);

								graph.updateNode(parentIndex, parent);

								seenByCurrent.put(childNodeName, true);

							}

						}
					}

				}
			}

			// if we've reached the end of the depth
			if (endOfDepth.equals(current)) {
				endOfDepth = graph.vertexData().get(index); // The end of the next depth is when we reach the final
															// child node of current depth
				depth++;
			}

		}

		return graph;

	}

}
