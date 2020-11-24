package forFun;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BinarySearch {
	static List<Integer> list = new ArrayList<Integer>();
	static final int TO_SEARCH_FOR = 69420, LENGTH = 1000000;
	public static void main(String[] args) {	
		int searchingIndex = (int)(Math.random() * LENGTH);
		for (int i = 0; i < LENGTH; i++) {
			if (searchingIndex == i) {
				list.add(TO_SEARCH_FOR);
			}
			list.add((int)(Math.random() * LENGTH));
		}
		Collections.sort(list);
		linearSearch();
		long start = System.nanoTime();
		System.out.println("Binary Search found in: " + (start - binarySearch(0, LENGTH-1, TO_SEARCH_FOR)) + "ns");
	}
	
	public static void linearSearch() {
		long start = System.nanoTime(), end = 0;
		for (int i = 0; i < LENGTH; i++) {
			if (list.get(i) == TO_SEARCH_FOR)
			end = System.nanoTime() - start;
		}
		System.out.println("Found in " + end + "ns.");
	}
	
	public static long binarySearch(int l, int r, int x) 
    { 
        if (r >= l) { 
            int mid = l + (r - l) / 2; 
  
            // If the element is present at the 
            // middle itself 
            if (list.get(mid) == x) 
                return System.nanoTime();
  
            // If element is smaller than mid, then 
            // it can only be present in left subarray 
            if (list.get(mid) > x) 
                return binarySearch(l, mid - 1, x); 
  
            // Else the element can only be present 
            // in right subarray 
            return binarySearch(mid + 1, r, x); 
        } 
        return -1;
    } 
}
