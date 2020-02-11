package pa1;

import java.util.Comparator;

import api.TaggedVertex;

/**
 * Index comparator class to compare TaggedVertex<String> objects in MergeSort
 * @author Jeremy Galang and Ethan McGill
 *
 */
public class IndexComparator implements Comparator<TaggedVertex<String>> {
	
	public IndexComparator() {
	}
	
	
	/**
	 * Compares o1 and o2 based on their tag values
	 */
	@Override
	public int compare(TaggedVertex<String> o1, TaggedVertex<String> o2) {
		
		if(o1.getTagValue() > o2.getTagValue()) {
			return 1; 
		}
		
		else if(o1.getTagValue() < o2.getTagValue()) {
			return -1; 
		}
		
		
		return 0;
	}
	

}
