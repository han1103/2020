/*
 * https://leetcode.com/problems/implement-trie-prefix-tree/
*/
package amazon;

// Used BST for implementation
public class Trie {
	String data;
	Trie left;
	Trie right;
	
	/** Initialize your data structure here. */
    public Trie() {
        data = null;
        left = null;
        right = null;
    }
    
    /** Inserts a word into the trie. */
    public void insert(String word) {
    	if(word==null || word.isEmpty())
    		return;
        if(data==null)
        	data = word;
        else if (word.compareTo(data) < 0) {
        	if(left==null) {
        		left = new Trie();
        		left.data = word;
        	}
        	else
        		left.insert(word);
        }
        else if (word.compareTo(data) > 0) {
        	if(right==null) {
        		right = new Trie();
        		right.data = word;
        	}
        	else
        		right.insert(word);
        }	
    }
    
    /** Returns if the word is in the trie. */
    public boolean search(String word) {
    	if(word==null || word.isEmpty() || data==null)
    		return false;
    	if(data.equals(word))
    		return true;
    	if(data.compareTo(word) < 0 && right!=null)
    		return right.search(word);
    	if(data.compareTo(word) > 0 && left!=null)
    		return left.search(word);
        return false;
    }
    
    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
    	if(prefix==null || prefix.isEmpty() || data==null)
    		return false;
    	if(data.startsWith(prefix))
    		return true;
    	if(data.compareTo(prefix) < 0 && right!=null)
    		return right.startsWith(prefix);
    	if(data.compareTo(prefix) > 0 && left!=null) {
    		return left.startsWith(prefix);
    	}
        return false;        
    }
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Trie trie = new Trie();

		trie.insert("app");
		trie.insert("apple");
		trie.insert("beer");
		trie.insert("add");
		trie.insert("jam");
		trie.insert("rental");
		System.out.println(trie.startsWith("ad"));	}

}
