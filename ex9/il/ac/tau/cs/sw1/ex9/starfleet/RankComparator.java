package il.ac.tau.cs.sw1.ex9.starfleet;

import java.util.Comparator;
import java.util.Map;

public class RankComparator implements Comparator<Map.Entry<OfficerRank, Integer>> {
	public RankComparator() {
	}

	public int compare(Map.Entry<OfficerRank, Integer> rank1, Map.Entry<OfficerRank, Integer> rank2) {
		if (rank1.getValue() > rank2.getValue())
			return -1;
		if (rank1.getValue() < rank2.getValue())
			return 1;
		return rank1.getKey().compareTo(rank2.getKey());
	}
}
