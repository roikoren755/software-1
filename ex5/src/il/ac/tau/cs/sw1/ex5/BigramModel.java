package src.il.ac.tau.cs.sw1.ex5;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class BigramModel {
	public static final int MAX_VOCABULARY_SIZE = 14000;
	public static final String VOC_FILE_SUFFIX = ".voc";
	public static final String COUNTS_FILE_SUFFIX = ".counts";
	public static final String SOME_NUM = "some_num";
	public static final int ELEMENT_NOT_FOUND = -1;
	
	String[] mVocabulary;
	int[][] mBigramCounts;
	
	// DO NOT CHANGE THIS !!! 
	public void initModel(String fileName) throws IOException{
		mVocabulary = buildVocabularyIndex(fileName);
		mBigramCounts = buildCountsArray(fileName, mVocabulary);
	}
	
	/*
	 * @pre: word = word.toLowerCase()
	 * @post: mVocabulary = prev(mVocabulary)
	 * @post: mBigramCounts = prev(mBigramCounts)
	 */
	public String isValidWord(String word){
		boolean isNumber = false;
		for (int i = 0; i < word.length(); i++){
			char c = word.charAt(i);
			if (c >= 'a' && c <= 'z')
				return word;
			else{
				if (Character.isDigit(c))
					isNumber = true;
				else
					isNumber = false;
			}
		}
		if (isNumber)
			return SOME_NUM;
		else
			return "";
	}
	
	/*
	 * @pre: isValidWord(word)
	 * @post: mVocabulary = prev(mVocabulary)
	 * @post: mBigramCounts = prev(mBigramCounts)
	 */
	public boolean toAdd(String word, String[] vocabulary){
		for (int i = 0; i < vocabulary.length; i++){
			if (vocabulary[i] == null)
				return true;
			if (word.equals(vocabulary[i]))
				return false;
		}
		return true;
	}
	
	/*
	 * @post: mVocabulary = prev(mVocabulary)
	 * @post: mBigramCounts = prev(mBigramCounts)
	 */
	public String[] buildVocabularyIndex(String fileName) throws IOException{ // Q 1
		File file = new File(fileName);
		BufferedReader reader = new BufferedReader(new FileReader(file));
		String[] vocabulary = new String[MAX_VOCABULARY_SIZE];
		int count = 0;
		String line = "";
		while (count < vocabulary.length && (line = reader.readLine()) != null){
			String[] words = line.split(" ");
			for (int i = 0; i < words.length; i++){
				String word = isValidWord(words[i].toLowerCase());
				if (word != "" && toAdd(word, vocabulary)){
					vocabulary[count] = word;
					count++;
				}
			}
		}
		if (count < MAX_VOCABULARY_SIZE){
			vocabulary = Arrays.copyOf(vocabulary, count);
		}
		reader.close();
		return vocabulary;
	}
	
	/*
	 * @pre: initModel not necessarily called
	 * @post: mVocabulary = prev(mVocabulary)
	 * @post: mBigramCounts = prev(mBigramCounts)
	 */
	public int getNonInitIndex(String word, String[] vocabulary){
		for (int i = 0; i < vocabulary.length; i++)
			if (word.equals(vocabulary[i]))
				return i;
		return -1;
	}
	
	
	/*
	 * @post: mVocabulary = prev(mVocabulary)
	 * @post: mBigramCounts = prev(mBigramCounts)
	 */
	public int[][] buildCountsArray(String fileName, String[] vocabulary) throws IOException{ // Q - 2
		int[][] bigramCounts = new int[vocabulary.length][vocabulary.length];
		BufferedReader reader = new BufferedReader(new FileReader(new File(fileName)));
		String line = reader.readLine();
		while (line != null){
			String[] words = line.split(" ");
			int i = 0;
			while (i < words.length - 1){
				String word1 = words[i].toLowerCase();
				int index1 = getNonInitIndex(word1, vocabulary);
				if (index1 != -1){
					String word2 = words[i + 1].toLowerCase();
					int index2 = getNonInitIndex(word2, vocabulary);
					if (index2 != -1)
						bigramCounts[index1][index2] += 1;
				}
				i++;
			}
			line = reader.readLine();
		}
		reader.close();
		return bigramCounts;
	}
	
	
	/*
	 * @pre: the method initModel was called (the language model is initialized)
	 * @pre: fileName is a legal file path
	 */
	public void saveModel(String fileName) throws IOException{ // Q-3
		String lineSeparator = System.lineSeparator();
		BufferedWriter voc = new BufferedWriter(new FileWriter(fileName + VOC_FILE_SUFFIX));
		voc.write(mVocabulary.length + " words" + lineSeparator);
		for (int i = 0; i < mVocabulary.length; i++)
			voc.write(i + "," + mVocabulary[i] + lineSeparator);
		voc.close();
		BufferedWriter counts = new BufferedWriter(new FileWriter(fileName + COUNTS_FILE_SUFFIX));
		for (int i = 0; i < mVocabulary.length; i++){
			for (int j = 0; j < mVocabulary.length; j++){
				if (mBigramCounts[i][j] != 0)
					counts.write(i + "," + j + ":" + mBigramCounts[i][j] + lineSeparator);
			}
		}
		counts.close();
	}
	
	
	
	/*
	 * @pre: fileName is a legal file path
	 */
	public void loadModel(String fileName) throws IOException{ // Q - 4
		BufferedReader voc = new BufferedReader(new FileReader(new File(fileName + VOC_FILE_SUFFIX)));
		String[] words = voc.readLine().split(" ");
		int len = Integer.parseInt(words[0]);
		String[] vocabulary = new String[len];
		for (int i = 0; i < len; i++){
			words = voc.readLine().split(",");
			vocabulary[i] = words[1];
		}
		mVocabulary = vocabulary;
		voc.close();
		BufferedReader counts = new BufferedReader(new FileReader(new File(fileName + COUNTS_FILE_SUFFIX)));
		int[][] bigramCounts = new int[len][len];
		String line;
		while ((line = counts.readLine()) != null){
			words = line.split(":");
			String[] indexes = words[0].split(",");
			bigramCounts[Integer.parseInt(indexes[0])][Integer.parseInt(indexes[1])] = Integer.parseInt(words[1]);
		}
		mBigramCounts = bigramCounts;
		counts.close();
	}

	
	
	/*
	 * @pre: word is in lowercase
	 * @pre: the method initModel was called (the language model is initialized)
	 * @post: $ret = -1 if word is not in vocabulary, otherwise $ret = the index of word in vocabulary
	 */
	public int getWordIndex(String word){  // Q - 5
		for (int i = 0; i < mVocabulary.length; i++){
			if (word.equals(mVocabulary[i]))
				return i;
		}
		return -1;
	}
	
	
	
	/*
	 * @pre: word1, word2 are in lowercase
	 * @pre: the method initModel was called (the language model is initialized)
	 * @post: $ret = the count for the bigram <word1, word2>. if one of the words does not
	 * exist in the vocabulary, $ret = 0
	 */
	public int getBigramCount(String word1, String word2){ //  Q - 6
		int index1 = getWordIndex(word1);
		int index2 = getWordIndex(word2);
		if (index1 == -1 || index2 == -1)
			return 0;
		return mBigramCounts[index1][index2];
	}
	
	
	/*
	 * @pre word in lowercase, and is in mVocabulary
	 * @pre: the method initModel was called (the language model is initialized)
	 * @post $ret = the word with the lowest vocabulary index that appears most fequently after word (if a bigram starting with
	 * word was never seen, $ret will be null
	 */
	public String getMostFrequentProceeding(String word){ //  Q - 7
		int index = getWordIndex(word);
		int maxCount = 0;
		String maxWord = null;
		for (int i = 0; i < mVocabulary.length; i++){
			if (mBigramCounts[index][i] > maxCount){
				maxCount = mBigramCounts[index][i];
				maxWord = mVocabulary[i];
			}
		}
		return maxWord;
	}
	
	
	/* @pre: sentence is in lowercase
	 * @pre: the method initModel was called (the language model is initialized)
	 * @pre: each two words in the sentence are separated with a single space
	 * @post: if sentence is probable, according to the model, $ret = true, else, $ret = false
	 */
	public boolean isLegalSentence(String sentence){  //  Q - 8
		String[] words = sentence.split(" ");
		int[] indexes = new int[words.length];
		for (int i = 0; i < words.length; i++){
			indexes[i] = getWordIndex(words[i]);
			if (indexes[i] == -1)
				return false;
		}
		for (int i = 0; i < words.length - 1; i++)
			if (mBigramCounts[indexes[i]][indexes[i+1]] == 0)
				return false;
		return true;
	}
	
	
	
	/*
	 * @pre: arr1.length = arr2.legnth
	 * post if arr1 or arr2 are only filled with zeros, $ret = 0, otherwise
	 */
	public static double calcCosineSim(int[] arr1, int[] arr2){ //  Q - 9
		double sum = 0.0;
		double squareSum1 = 0.0;
		double squareSum2 = 0.0;
		for (int i = 0; i < arr1.length; i++){
			sum += arr1[i]*arr2[i];
			squareSum1 += (arr1[i] * arr1[i]);
			squareSum2 += (arr2[i] * arr2[i]);
		}
		if (squareSum1 == 0.0 || squareSum2 == 0.0)
			return 0.0;
		return  sum / (Math.sqrt(squareSum1) * Math.sqrt(squareSum2));
	}

	
	/*
	 * @pre: word is in vocabulary
	 * @pre: the method initModel was called (the language model is initialized), 
	 * @post: $ret = wordVector of word
	 */
	public int[] getWordVector(int index){
		int[] wordVector = new int[mVocabulary.length];
		for (int i = 0; i < mVocabulary.length; i++)
			wordVector[i] = mBigramCounts[index][i];
		return wordVector;
	}
	
	
	/*
	 * @pre: word is in vocabulary
	 * @pre: the method initModel was called (the language model is initialized), 
	 * @post: $ret = wordVector of word
	 */
	public int[] getWordVector(String word){
		int index = getWordIndex(word);
		return getWordVector(index);
	}
	
	/*
	 * @pre: word is in vocabulary
	 * @pre: the method initModel was called (the language model is initialized), 
	 * @post: $ret = w implies that w is the word with the largest cosineSimilarity(vector for word, vector for w) among all the
	 * other words in vocabulary
	 */
	public String getClosestWord(String word){ //  Q - 10
		int[] wordVector = getWordVector(word);
		int wordIndex = getWordIndex(word);
		double bestCosineSim = 0.0;
		String bestWord = "";
		for (int i = 0; i < mVocabulary.length; i++){
			if (wordIndex != i){
				int[] wVector = getWordVector(i);
				double cosineSim = calcCosineSim(wordVector, wVector);
				if (cosineSim > bestCosineSim){
					bestCosineSim = cosineSim;
					bestWord = mVocabulary[i];
				}
			}
		}
		return bestWord;
	}
	
}
