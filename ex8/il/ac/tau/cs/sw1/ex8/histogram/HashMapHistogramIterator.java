package il.ac.tau.cs.sw1.ex8.histogram;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;


/**************************************
 *  Add your code to this class !!!   *
 **************************************/
public class HashMapHistogramIterator<T extends Comparable<T>> implements Iterator<T> {

	private HashMap<T, Integer> hashMap;
	private List<T> keyList;
	private int curr = -1;

	public HashMapHistogramIterator(HashMap<T, Integer> hashMap) {
		this.hashMap = hashMap;
		this.keyList = new ArrayList<T>(this.hashMap.keySet());
		this.keyList.sort(new HashMapHistogramComparator<T>());
	}


	@Override
	public boolean hasNext() {
		return this.curr < keyList.size() - 1;
	}

	@Override
	public T next() {
		this.curr++;
		return this.keyList.get(curr);
	}

	@Override
	public void remove() {
		throw new UnsupportedOperationException();
	}

	private class HashMapHistogramComparator<T extends Comparable<T>> implements Comparator<T> {

		@Override
		public int compare(T arg0, T arg1) {
			int count0 = HashMapHistogramIterator.this.hashMap.getOrDefault(arg0, 0);
			int count1 = HashMapHistogramIterator.this.hashMap.getOrDefault(arg1, 0);
			if (count0 > count1)
				return -1;
			if (count1 > count0)
				return 1;
			else
				return arg0.compareTo(arg1);
		}

	}
}
