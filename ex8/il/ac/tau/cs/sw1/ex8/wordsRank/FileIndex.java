package il.ac.tau.cs.sw1.ex8.wordsRank;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import il.ac.tau.cs.sw1.ex8.histogram.HashMapHistogram;
import il.ac.tau.cs.sw1.ex8.histogram.IHistogram;
import il.ac.tau.cs.sw1.ex8.wordsRank.RankedWord.rankType;

/**************************************
 *  Add your code to this class !!!   *
 **************************************/

public class FileIndex {
	
	public static final int UNRANKED_CONST = 20;
	public List<RankedWord> wordRanks;
	public Set<String> rankedWords;
	public HashMap<String, HashMapHistogram<String>> hashMap;
	
	
	/*
	 * @pre: the directory is no empty, and contains only readable text files
	 */
  	public void indexDirectory(String folderPath) {
		//This code iterates over all the files in the folder. add your code wherever is needed

		this.hashMap = new HashMap<String, HashMapHistogram<String>>();
		this.wordRanks = new ArrayList<RankedWord>();
		this.rankedWords = new HashSet<String>();
		File folder = new File(folderPath);
		File[] listFiles = folder.listFiles();
		for (File file : listFiles) {
			// for every file in the folder
			if (file.isFile()) {
				this.hashMap.put(file.getName(), new HashMapHistogram<String>());
				HashMapHistogram<String> hist = this.hashMap.get(file.getName());
				try {
					List<String> tokens = FileUtils.readAllTokens(file);
					hist.addAll(tokens);
				}
				catch (IOException e) {
				}
			}
		}for (File file : listFiles) {
			if (file.isFile()) {
				try {
					for (String token: FileUtils.readAllTokens(file))
						getRankedWord(token);
				}
				catch (IOException e) {
				}
			}
		}
	}
	
  	private void checkFile(String filename) throws FileIndexException{
  		if (!this.hashMap.containsKey(filename))
  			throw new FileIndexException("Oops! Could not find a file named " + filename);
  	}
  	
  	/*
	 * @pre: the index is initialized
	 * @pre filename is a name of a valid file
	 * @pre word is not null
	 */
	public int getCountInFile(String filename, String word) throws FileIndexException{
		checkFile(filename);
		return this.hashMap.get(filename).getCountForItem(word.toLowerCase());
	}
	
	/*
	 * @pre: the index is initialized
	 * @pre filename is a name of a valid file
	 * @pre word is not null
	 */
	public int getRankForWordInFile(String filename, String word) throws FileIndexException{
		word = word.toLowerCase();
		checkFile(filename);
		int wordCount = this.hashMap.get(filename).getItemsSet().size();
		if (getCountInFile(filename, word) == 0)
			return wordCount + UNRANKED_CONST;
		int rank = 0;
		for (String token: this.hashMap.get(filename)) {
			rank++;
			if (token.equals(word))
				break;
		}
		return rank;
	}
	
	public RankedWord getRankedWord(String word){
		HashMap<String, Integer> ranks = new HashMap<String, Integer>();
		try {
			for (String filename: this.hashMap.keySet()){
				ranks.put(filename, getRankForWordInFile(filename, word));
			}
		}
		catch (FileIndexException e) { // shouldn't get here ever...
		}
		word = word.toLowerCase();
		RankedWord rankedWord = new RankedWord(word, ranks);
		if (!this.rankedWords.contains(word)) {
			this.rankedWords.add(word);
			this.wordRanks.add(rankedWord);
		}
		return rankedWord;
	}
	
	/*
	 * @pre: the index is initialized
	 * @pre word is not null
	 */
	public int getAverageRankForWord(String word){
		RankedWord rankedWord = getRankedWord(word);
		return rankedWord.getRankByType(rankType.average);
	}
	
	public List<String> getWordsWithAverageRankLowerThenK(int k){
		this.wordRanks.sort(new RankedWordComparator(rankType.average));
		List<String> words = new ArrayList<String>();
		for (RankedWord word: this.wordRanks){
			if (word.getRankByType(rankType.average) <= k)
				words.add(word.getWord());
		}
		return words;
	}
	
	public List<String> getWordsBelowMinRank(int k){
		this.wordRanks.sort(new RankedWordComparator(rankType.min));
		List<String> words = new ArrayList<String>();
		for (RankedWord word: this.wordRanks){
			if (word.getRankByType(rankType.min) <= k)
				words.add(word.getWord());
		}
		return words;
	}
	
	public List<String> getWordsAboveMaxRank(int k){
		this.wordRanks.sort(new RankedWordComparator(rankType.max));
		List<String> words = new ArrayList<String>();
		for (RankedWord word: this.wordRanks){
			if (word.getRankByType(rankType.max) <= k)
				words.add(word.getWord());
		}
		return words;
	}

}
