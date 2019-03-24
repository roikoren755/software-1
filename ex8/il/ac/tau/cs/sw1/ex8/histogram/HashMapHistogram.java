package il.ac.tau.cs.sw1.ex8.histogram;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

/**************************************
 *  Add your code to this class !!!   *
 **************************************/
public class HashMapHistogram<T extends Comparable<T>> implements IHistogram<T> {

	private HashMap<T, Integer> hashMap;

	public HashMapHistogram() {
		this.hashMap = new HashMap<T, Integer>();
	}

	@Override
	public void addItem(T item) {
		if (this.hashMap.containsKey(item))
			this.hashMap.replace(item, this.hashMap.get(item) + 1);
		else
			this.hashMap.put(item, 1);
	}

	@Override
	public void addItemKTimes(T item, int k) throws IllegalKValue {
		if (k < 0)
			throw new IllegalKValue(k);
		if (this.hashMap.containsKey(item))
			this.hashMap.replace(item, this.hashMap.get(item) + k);
		else
			this.hashMap.put(item, k);
	}

	@Override
	public int getCountForItem(T item) {
		return this.hashMap.getOrDefault(item, 0);
	}

	@Override
	public void addAll(Collection<T> items) {
		for (T item : items)
			this.addItem(item);
	}

	@Override
	public void clear() {
		this.hashMap.clear();
	}

	@Override
	public Set<T> getItemsSet() {
		return this.hashMap.keySet();
	}

	@Override
	public Iterator<T> iterator() {
		return new HashMapHistogramIterator<T>(this.hashMap);
	}

}
