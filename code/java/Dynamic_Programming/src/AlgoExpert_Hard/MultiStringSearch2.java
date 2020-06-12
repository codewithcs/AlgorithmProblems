package AlgoExpert_Hard;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MultiStringSearch2 {
	public static void main(String[] args) {
		
	}
	
	// Method 2: O(b^2 + ns) time | O(b^2 + n) space 
	public static List<Boolean> multiStringSearch2(String bigString, String[] smallStrings) {
	    
		
		
		return null;
	}
		
	static class TrieNode {
		Map<Character, TrieNode> children = new HashMap<>(); 
	}
	
	static class ModifiedSuffixTrie{
		TrieNode root = new TrieNode(); 
		
		public ModifiedSuffixTrie(String str) {
			populateModifiedSuffixTrieFrom(str); 
		}
		
		public void populateModifiedSuffixTrieFrom(String str) {
			for(int i=0 ; i<str.length(); i++) {
				insertSubstringStartingAt(i, str); 
			}
		}
		
		public void insertSubstringStartingAt(int i, String str) {
			TrieNode node = root; 
			
			for(int j=i ; j<str.length(); j++) {
				char letter = str.charAt(j);
				
				if(!node.children.containsKey(letter)) {
					TrieNode newNode = new TrieNode(); 
					node.children.put(letter, newNode); 
				}
				node = node.children.get(letter); 
			}	
		}
		
		public boolean contains(String str) {
			TrieNode node = root; 
			for(int i=0; i<str.length(); i++) {
				char letter = str.charAt(i); 
				if(!node.children.containsKey(letter)) {
					return false; 
				}
				node = node.children.get(letter); 
			}
			return true; 
		}
		
	}
	
}