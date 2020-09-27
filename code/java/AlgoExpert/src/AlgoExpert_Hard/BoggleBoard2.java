package AlgoExpert_Hard;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BoggleBoard2 {
	public static void main(String[] args) {
		
		char[][] board = {  { 't', 'h', 'i', 's', 'i', 's', 'a' },
				{ 's', 'i', 'm', 'p', 'l', 'e', 'x' },
				{ 'b', 'x', 'x', 'x', 'x', 'e', 'b' },
				{ 'x', 'o', 'g', 'g', 'l', 'x', 'o' },
				{ 'x', 'x', 'x', 'D', 'T', 'r', 'a' }, 
				{ 'R', 'E', 'P', 'E', 'A', 'd', 'x' },
				{ 'x', 'x', 'x', 'x', 'x', 'x', 'x' },
				{ 'N', 'O', 'T', 'R', 'E', '-', 'P' },
				{ 'x', 'x', 'D', 'E', 'T', 'A', 'E' }
		}; 

		String[] words = { "this", "is", "not", "a", "simple", "boggle", "board", "test", "REPEATED", "NOTRE-PEATED" } ;

		System.out.println(boggleBoard(board, words));
		
	}

	public static List<String> boggleBoard(char[][] board, String[] words) {
		
		Trie trie = new Trie() ; 
		trie.add("this");
		
		return null; 
	}
	
	static class TrieNode{
		Map<Character, TrieNode> children = new HashMap<>(); 
		String word = "";
	}
	
	static class Trie{
		TrieNode root; 
		char endSymbol; 
		
		public Trie() {
			this.root = new TrieNode() ; 
			this.endSymbol = '*' ; 
		}
		
		public void add(String str) {
			TrieNode node = this.root; 
			
			for(int i=0; i<str.length(); i++) {
				char letter = str.charAt(i) ; 
				if(!node.children.containsKey(letter)) {
					TrieNode newNode = new TrieNode(); 
					node.children.put(letter, newNode); 
				}
				node = node.children.get(letter); 
			}
			
			node.children.put(this.endSymbol, null); 
			node.word = str; 
		}
	}

}
