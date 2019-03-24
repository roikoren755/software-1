package il.ac.tau.cs.sw1.ex8.wordsRank;

import java.util.Comparator;

import il.ac.tau.cs.sw1.ex8.wordsRank.RankedWord.rankType;


/**************************************
 *  Add your code to this class !!!   *
 **************************************/

class RankedWordComparator implements Comparator<RankedWord>{
	private rankType cType;
	
	public RankedWordComparator(rankType cType) {
		this.cType = cType;
	}
	
	@Override
	public int compare(RankedWord o1, RankedWord o2) {
		int rank1 = o1.getRankByType(this.cType);
		int rank2 = o2.getRankByType(this.cType);
		if (rank1 < rank2)
			return -1;
		if (rank2 < rank1)
			return 1;
		return o1.getWord().compareTo(o2.getWord());
	}	
}
