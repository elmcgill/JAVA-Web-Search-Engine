package pa1;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;

import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.UnsupportedMimeTypeException;
import org.jsoup.nodes.Document;

import api.Util;

/**
 * Page class that represents a page object
 * 
 * @author Jeremy Galang and Ethan McGill
 *
 */
public class Page {

	private String urlLink;
	private HashMap<String, Integer> wordCount = new HashMap<String, Integer>(); // list(w)

	private int inDegree;
	CallCounter counter = CallCounter.getInstance();

	/**
	 * Constructor for the page class
	 * 
	 * @param url      - the link
	 * @param inDegree - the number of incoming edges to the link
	 */
	public Page(String url, int inDegree) {

		this.urlLink = url;
		this.inDegree = inDegree;
	}

	/**
	 * Creates a page by connecting to the URL, and grabbing the words on the page
	 * as an array of Strings.
	 * 
	 * @return a Page object
	 */
	public Page createPage() {

		try {
			//if (counter.getCount() % 50 == 0 && counter.getCount() != 0) { // check if we've made 50 calls
				//try {
					
					//Thread.sleep(3000);
				//} catch (InterruptedException ignore) {

				//}
			//}
			Document doc = Jsoup.connect(urlLink).get(); // connect to the URL
		
			counter.madeCall(); // signal that a call has been made
			
			if(doc.body() == null) {
				return this;
			} else {
				String text = doc.body().text(); // save all the words as a string

				String[] words = text.split(" "); // convert all the words into a string array

				for (int i = 0; i < words.length; i++) {

					String toCheck = words[i]; // grab a word
					toCheck = Util.stripPunctuation(words[i]); // strip the punctuation

					if (!Util.isStopWord(toCheck) && !toCheck.isEmpty() && toCheck != null) { // if its not a stop word

						if (!wordCount.containsKey(toCheck)) { // if the word isn't already in the HashMap
							wordCount.put(toCheck, 1);
						} else if (wordCount.containsKey(toCheck)) { // if the word is already in the HashMap
							int index = wordCount.get(toCheck);
							wordCount.replace(toCheck, index, index + 1); // increment the number of times the word appears
						}

					}

				}
			}
			

		} catch (UnsupportedMimeTypeException e) {
			
		} catch (HttpStatusException e) {
			
		} catch (IOException e) {

			
		}

		return this;
	}

	/**
	 * Returns how many times a certain word appears in the graph
	 * 
	 * @param word - the word we're checking
	 * @return the number of times the word appears
	 */
	public int getWordCount(String word) {

		return wordCount.get(word);
	}

	/**
	 * Return the number of incoming edges
	 * 
	 * @return number of incoming edges
	 */
	public int getIndegree() {
		return this.inDegree;
	}

	/**
	 * A HashMap containing all the words along with the number of times they appear
	 * in the page
	 * 
	 * @return - Hashmap with words and number of times they appear
	 */
	public HashMap<String, Integer> returnMap() {

		return wordCount;
	}

	/**
	 * @return The url link
	 */
	public String getName() {
		return this.urlLink;
	}

}
