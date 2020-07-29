/*
 * https://leetcode.com/problems/search-suggestions-system/
*/
package amazon;

import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import amazon.WordDictionary.TrieNode;

public class SearchSuggestionsSystem {
	static int counter = 0;
	
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
				if (i == str.length() - 1)
					node.isEnd = true;
			}
		}

		public boolean search(String str) {
			if (str.length() == 0)
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
	
		public List<String> prefixSearch3(String prefix) {
			return prefixSearchRecursive(prefix, prefix);
		}

		public void prefixSearch2(String prefix, List<String> result) {			
			if(isEnd) {
				result.add(prefix);
				counter++;
				if(counter==3)
					return;
			}			
			for(int i=0; i<26; i++) {
				if (children[i] != null) {
					String newPrefix = prefix + Character.toString((char)('a'+i));
					children[i].prefixSearch2(newPrefix, result);
					if(counter==3)
						return;
				}
			}
			
		}
		
		public List<String> prefixSearchRecursive(String prefix, String str) {
			List<String> result = new ArrayList<String>();

			if (str.length() == 0) {
				counter = 0;
				prefixSearch2(prefix, result);
				return result;
			}

			int index = str.charAt(0) - 'a';
			TrieNode child = children[index];
			if (child == null)
				return result;
			else {
				String nextStr = str.substring(1);
				return child.prefixSearchRecursive(prefix, nextStr);
			}
		}
	}

	private TrieNode root = new TrieNode();

	public List<List<String>> suggestedProducts(String[] products, String searchWord) {
		List<List<String>> result = new ArrayList<List<String>>();

		for (String product : products)
			root.add(product);
//		List<String> subList = root.prefixSearch3("mo");
//		result.add(subList);
		
		for (int i = 0; i < searchWord.length(); i++) {
			String preFix = searchWord.substring(0, i + 1);
			List<String> subList = root.prefixSearch3(preFix);
			result.add(subList);
		}

		return result;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		String[] products = {"mobile","mouse","moneypot","monitor","mousepad"};
//		List<List<String>> result = new SearchSuggestionsSystem().suggestedProducts(products, "mouse");
//		String[] products = {"havana"};
//		List<List<String>> result = new SearchSuggestionsSystem().suggestedProducts(products, "havana");
//		String[] products = {"bags","baggage","banner","box","cloths"};
//		List<List<String>> result = new SearchSuggestionsSystem().suggestedProducts(products, "bags");
		String[] products = {"havana"};
		List<List<String>> result = new SearchSuggestionsSystem().suggestedProducts(products, "tatiana");
		System.out.println("here");
	}

}
