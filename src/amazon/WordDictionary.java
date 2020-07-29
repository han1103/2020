/*
 * https://leetcode.com/problems/add-and-search-word-data-structure-design/
*/
package amazon;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class WordDictionary {

	class TrieNode {
		boolean isEnd = false;
		TrieNode[] children = new TrieNode[26];

		public TrieNode() {
			// TODO Auto-generated constructor stub
		}

		public void add(String str) {
			TrieNode node = this;
			for (int i = 0; i < str.length(); i++) {
				int index = str.charAt(i) - 'a';
				if (node.children[index] == null)
					node.children[index] = new TrieNode();
				node = node.children[index];
				if(i==str.length()-1)
					node.isEnd = true;
			}
		}

		public boolean search(String str) {
			if(str.length()==0)
				return isEnd;
			char c = str.charAt(0);
			if (c == '.') {
				String nextStr = str.substring(1);
				for (TrieNode child : children) {
					if (child != null)
						if (child.search(nextStr))
							return true;
				}
				return false;
			} else {
				int index = str.charAt(0) - 'a';
				TrieNode child = children[index];
				if (child == null)
					return false;
				else {
					String nextStr = str.substring(1);
					return child.search(nextStr);
				}
			}

		}
	}
	
	private TrieNode root = new TrieNode();

	/** Initialize your data structure here. */
	public WordDictionary() {

	}

	/** Adds a word into the data structure. */
	public void addWord(String word) {
		root.add(word);
	}

	/**
	 * Returns if the word is in the data structure. A word could contain the dot
	 * character '.' to represent any one letter.
	 */
	public boolean search(String word) {
		if (word == null || word.length()==0)
			return false;
		return root.search(word);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		WordDictionary dict = new WordDictionary();
		dict.addWord("a");
		dict.addWord("aa");
		//dict.addWord("an");
		//dict.addWord("add");
		System.out.println(dict.search("a.."));
//		dict.addWord("bad");
//		dict.addWord("dad");
//		dict.addWord("mad");
//		dict.addWord("search");
//		dict.addWord("search");
//		dict.addWord("search");
//		dict.addWord("search");
//		dict.addWord("search");
//		dict.addWord("search");
//		System.out.println(dict.search("pad"));
//		System.out.println(dict.search("bad"));
//		System.out.println(dict.search(".ad"));
//		System.out.println(dict.search("b."));
//		System.out.println(dict.search("seac"));
	}

}
