package pa1;

import java.util.ArrayList;
import java.util.Collections;

import api.TaggedVertex;

/**
 * MergeSort implementation except with an ArrayList and utilizes an
 * IndexComparator class
 *
 * @author Jeremy Galang and Ethan McGill
 */

public class MergeSort {

	private ArrayList<TaggedVertex<String>> toSort;

	IndexComparator comp = new IndexComparator();

	public MergeSort(ArrayList<TaggedVertex<String>> arr) {
		if (arr == null) { // error checking
			return;
		}

		this.toSort = arr;
	}

	public void sortArray() {
		mergeSortRec(0, this.toSort.size() - 1); // pass in the array
	}

	public void mergeSortRec(int start, int end) {

		if (start < end && (end - start) >= 1) {
			int mid = (end + start) / 2; // get midpoint

			mergeSortRec(start, mid); // first half of the array
			mergeSortRec(mid + 1, end); // second half of the array

			merge(start, mid, end); // merge the two together
		}
	}

	public void merge(int start, int mid, int end) {

		ArrayList<TaggedVertex<String>> sortedArr = new ArrayList<>();

		int left = start; // get left subarray
		int right = mid + 1; // get right subarray

		while (left <= mid && right <= end) {
			if (comp.compare(toSort.get(left), toSort.get(right)) > 0) { // compare the two elements
				sortedArr.add(toSort.get(left));
				left++;
			} else {
				sortedArr.add(toSort.get(right));
				right++;
			}
		}

		while (left <= mid) { // left subarray
			sortedArr.add(toSort.get(left));
			left++;
		}

		while (right <= end) { // right subarray
			sortedArr.add(toSort.get(right));
			right++;
		}

		int i = 0;
		int j = start;

		while (i < sortedArr.size()) {
			toSort.set(j, sortedArr.get(i++));
			j++;
		}
	}
}
