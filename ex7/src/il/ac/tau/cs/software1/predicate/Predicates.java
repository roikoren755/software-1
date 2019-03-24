package src.il.ac.tau.cs.software1.predicate;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Predicates implements PredicatesSet<Person> {

	private List<Person> lst;

	public Predicates(List<Person> lst) {
		this.lst = lst;
	}

	@Override
	public void remove(Predictor pred) {
		Iterator<Person> iter = lst.iterator();
		while (iter.hasNext()) {
			Person p = iter.next();
			if (pred.test(p))
				iter.remove();
		}

	}

	@Override
	public void retain(Predictor pred) {
		Iterator<Person> iter = lst.iterator();
		while (iter.hasNext()) {
			Person p = iter.next();
			if (!pred.test(p))
				iter.remove();
		}
	}

	@Override
	public List<Person> collect(Predictor pred) {
		List<Person> res = new ArrayList<Person>();
		for (int i = 0; i < res.size(); i++) {
			Person p = lst.get(i);
			if (pred.test(p))
				res.add(p);
		}
		return res;
	}

	@Override
	public int find(Predictor pred) {
		for (int i = 0; i < lst.size(); i++) {
			if (pred.test(lst.get(i)))
				return i;
		}
		return -1;
	}

	public List<Person> getLst() {
		return this.lst;
	}

	public void setLst(List<Person> lst) {
		this.lst = lst;
	}

}
