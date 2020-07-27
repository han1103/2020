/*
 * https://leetcode.com/problems/add-and-search-word-data-structure-design/
*/
package amazon;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class WordDictionary {

	private Set<String> data = new HashSet<>();

	/** Initialize your data structure here. */
	public WordDictionary() {

	}

	/** Adds a word into the data structure. */
	public void addWord(String word) {
		if (word != null && !data.contains(word))
			data.add(word);
	}

	private boolean match(String word, String item) {
		if (word.length() != item.length())
			return false;
		for (int i = 0; i < word.length(); i++) {
			char c = word.charAt(i);
			if (c != '.' && c != item.charAt(i))
				return false;
		}
		return true;
	}

	/**
	 * Returns if the word is in the data structure. A word could contain the dot
	 * character '.' to represent any one letter.
	 */
	public boolean search(String word) {
		if (word != null)
			for (String item : data) {
				if (match(word, item))
					return true;
			}
		return false;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		WordDictionary dict = new WordDictionary();
		dict.addWord("bad");
		dict.addWord("dad");
		dict.addWord("mad");
		dict.addWord("search");
		dict.addWord("search");
		dict.addWord("search");
		dict.addWord("search");
		dict.addWord("search");
		dict.addWord("search");
		System.out.println(dict.search("pad"));
		System.out.println(dict.search("bad"));
		System.out.println(dict.search(".ad"));
		System.out.println(dict.search("b."));
	}

}
