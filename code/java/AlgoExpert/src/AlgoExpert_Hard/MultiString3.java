package AlgoExpert_Hard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MultiString3 { // Also see similar problem of Boggle Board. 
	public static void main(String[] args) {
		
	}
	
	// Method 3: O(ns + bs) time | O(ns) space 
	public static List<Boolean> multiStringSearch3(String bigString, String[] smallStrings) {
	    Trie trie = new Trie(); 
	    for(String smallString: smallStrings) {
	    	trie.insert(smallString);
	    }
	    
	    Set<String> containedStrings = new HashSet<>(); 
	    
	    for(int i=0 ; i<bigString.length(); i++ ) {
	    	findSmallStringsIn(bigString, i, trie, containedStrings); 
	    }
		
	    List<Boolean> solution = new ArrayList<>();
	    
	    for(String str: smallStrings) { // Have to fill the boolean list in same order as the order of the small strings. 
	    	solution.add(containedStrings.contains(str));
	    }
		
		return solution;
	}
	
	public static void findSmallStringsIn(String str, int startIdx, Trie trie, Set<String> containedStrings) {
		
		TrieNode currentNode = trie.root; 
		
		for(int i= startIdx; i<str.length(); i++) {
			
			char currentChar = str.charAt(i); 
			
			if(!currentNode.children.containsKey(currentChar)) {
				break; 
			}
			
			currentNode = currentNode.children.get(currentChar);  
			
			if(currentNode.children.containsKey(trie.endSymbol)) {
				containedStrings.add(currentNode.word); 
			}
			
		}
		
	}
	
	static class TrieNode {
		Map<Character, TrieNode> children = new HashMap<>(); 
		String word; 
	}
	
	static class Trie {
		TrieNode root = new TrieNode(); 
		char endSymbol = '*' ; 
		
		public void insert(String str) {
			TrieNode node = root; 
			for(int i=0 ; i<str.length(); i++) {
				char letter = str.charAt(i); 
				if(!node.children.containsKey(letter)) {
					TrieNode newNode = new TrieNode(); 
					node.children.put(letter, newNode); 
				}
				node = node.children.get(letter);			
			}
			node.children.put(endSymbol, null); 
			node.word = str; 
		}
	}
}
