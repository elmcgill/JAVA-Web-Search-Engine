package pa1;

import java.util.ArrayList;
import java.util.List;

import api.TaggedVertex;

/**
 * Implementation of an inverted index for a web graph.
 * 
 * @author Jeremy Galang and Ethan McGill
 */
public class Index {
	private List<TaggedVertex<String>> urlList; // list of urls as taggedVertexes
	private List<Page> index = new ArrayList<Page>(); // arraylist of page objects

	/**
	 * Constructs an index from the given list of urls. The tag value for each url
	 * is the indegree of the corresponding node in the graph to be indexed.
	 * 
	 * @param urls information about graph to be indexed
	 */
	public Index(List<TaggedVertex<String>> urls) {
		if (urls == null) {
			throw new NullPointerException();
		}

		this.urlList = urls;
	}

	/**
	 * Creates the index.
	 */
	public void makeIndex() {

		for (int i = 0; i < urlList.size(); i++) {
			
			if(i == 0) {
				Page second = new Page(urlList.get(i).getVertexData(), urlList.get(i).getTagValue());

				second.createPage();
				index.add(second);
			} else {
				
				Page second = new Page(urlList.get(i).getVertexData(), urlList.get(i).getTagValue());

				second.createPage();
				index.add(second);
				
			}

		}
	}

	/**
	 * Searches the index for pages containing keyword w. Returns a list of urls
	 * ordered by ranking (largest to smallest). The tag value associated with each
	 * url is its ranking. The ranking for a given page is the number of occurrences
	 * of the keyword multiplied by the indegree of its url in the associated graph.
	 * No pages with rank zero are included.
	 * 
	 * @param w keyword to search for
	 * @return ranked list of urls
	 */
	public List<TaggedVertex<String>> search(String w) {

		ArrayList<TaggedVertex<String>> rankedList = new ArrayList<>();

		for (int i = 0; i <= index.size() - 1; i++) {
			if (index.get(i).returnMap().get(w) != null) { // if the index doesn't exist in the HashMap
				int count = index.get(i).returnMap().get(w); // get the number of times the word w appears
				int rank = index.get(i).getIndegree() * count; // calculate the rank
				if (rank != 0) { // add it to the rank list
					rankedList.add(new TaggedVertex<String>(index.get(i).getName(), rank));
					
				}

			}

		}

		MergeSort sorter = new MergeSort(rankedList);

		sorter.sortArray(); // sort the ranking list

		return rankedList;
	}

	/**
	 * Searches the index for pages containing both of the keywords w1 and w2.
	 * Returns a list of qualifying urls ordered by ranking (largest to smallest).
	 * The tag value associated with each url is its ranking. The ranking for a
	 * given page is the number of occurrences of w1 plus number of occurrences of
	 * w2, all multiplied by the indegree of its url in the associated graph. No
	 * pages with rank zero are included.
	 * 
	 * @param w1 first keyword to search for
	 * @param w2 second keyword to search for
	 * @return ranked list of urls
	 */
	public List<TaggedVertex<String>> searchWithAnd(String w1, String w2) {

		ArrayList<TaggedVertex<String>> rankedList = new ArrayList<>();

		for (int i = 0; i <= index.size() - 1; i++) {
			if (index.get(i).returnMap().get(w1) != null && index.get(i).returnMap().get(w2) != null) { // check if both
																										// words exist
				int wordCount1 = index.get(i).returnMap().get(w1); // get the number of times both words appear in the
																	// HashMap
				int wordCount2 = index.get(i).returnMap().get(w2);
				int rank = index.get(i).getIndegree() * (wordCount1 + wordCount2); // calculate the ranks

				if (rank != 0) {
					rankedList.add(new TaggedVertex<String>(index.get(i).getName(), rank)); // add it to the ranked list
					
				}
			}
		}

		MergeSort sorter = new MergeSort(rankedList);

		sorter.sortArray(); // do merge sort on the ranked list

		return rankedList;
	}

	/**
	 * Searches the index for pages containing at least one of the keywords w1 and
	 * w2. Returns a list of qualifying urls ordered by ranking (largest to
	 * smallest). The tag value associated with each url is its ranking. The ranking
	 * for a given page is the number of occurrences of w1 plus number of
	 * occurrences of w2, all multiplied by the indegree of its url in the
	 * associated graph. No pages with rank zero are included.
	 * 
	 * @param w1 first keyword to search for
	 * @param w2 second keyword to search for
	 * @return ranked list of urls
	 */
	public List<TaggedVertex<String>> searchWithOr(String w1, String w2) {

		ArrayList<TaggedVertex<String>> rankedList = new ArrayList<>();

		int wordCount1, wordCount2, rank = 0;

		for (int i = 0; i <= index.size() - 1; i++) {
			// if either of the words exists
			if (index.get(i).returnMap().get(w1) != null || index.get(i).returnMap().get(w2) != null) {

				// if only w1 exists
				if (index.get(i).returnMap().get(w1) != null && index.get(i).returnMap().get(w2) == null) {
					wordCount1 = index.get(i).returnMap().get(w1); // get w1
					rank = index.get(i).getIndegree() * (wordCount1);// calculate rank

				}
				// if only w2 exists
				else if (index.get(i).returnMap().get(w1) == null && index.get(i).returnMap().get(w2) != null) {
					wordCount2 = index.get(i).returnMap().get(w2); // get w2
					rank = index.get(i).getIndegree() * (wordCount2);// calculate rank

				} else { // assume both words exist
					wordCount1 = index.get(i).returnMap().get(w1); // get both words
					wordCount2 = index.get(i).returnMap().get(w2);
					rank = index.get(i).getIndegree() * (wordCount1 + wordCount2); // calculate rank

				}

				if (rank != 0) {
					rankedList.add(new TaggedVertex<String>(index.get(i).getName(), rank)); // add it to the unranked
					//System.out.println("Page: " + index.get(i).getName() + " Rank: " + rank);																		// list
				}

			}
		}

		MergeSort sorter = new MergeSort(rankedList);

		sorter.sortArray(); // sort the unranked list using merge sort

		return rankedList;
	}

	/**
	 * Searches the index for pages containing keyword w1 but NOT w2. Returns a list
	 * of qualifying urls ordered by ranking (largest to smallest). The tag value
	 * associated with each url is its ranking. The ranking for a given page is the
	 * number of occurrences of w1, multiplied by the indegree of its url in the
	 * associated graph. No pages with rank zero are included.
	 * 
	 * @param w1 first keyword to search for
	 * @param w2 second keyword to search for
	 * @return ranked list of urls
	 */
	public List<TaggedVertex<String>> searchAndNot(String w1, String w2) {
		ArrayList<TaggedVertex<String>> rankedList = new ArrayList<>();

		for (int i = 0; i <= index.size() - 1; i++) {
			if (index.get(i).returnMap().get(w1) != null && index.get(i).returnMap().get(w2) == null) { // checks if w1
																										// exists and w2
																										// doesn't
				int count = index.get(i).returnMap().get(w1); // get w1
				int rank = index.get(i).getIndegree() * count; // calculate the rank
				if (rank != 0) {
					rankedList.add(new TaggedVertex<String>(index.get(i).getName(), rank)); // add it to the unranked
																							// list
				}
			}
		}

		MergeSort sorter = new MergeSort(rankedList);

		sorter.sortArray(); // sort the arraylist using merge sort

		return rankedList;
	}
}
