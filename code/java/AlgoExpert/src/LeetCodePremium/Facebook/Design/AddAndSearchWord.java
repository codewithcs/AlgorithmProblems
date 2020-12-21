package LeetCodePremium.Facebook.Design;

/*
Design a data structure that supports adding new words and finding if a string matches any previously added string.

Implement the WordDictionary class:
WordDictionary() Initializes the object.
void addWord(word) Adds word to the data structure, it can be matched later.
bool search(word) Returns true if there is any string in the data structure that matches word or false otherwise.
word may contain dots '.' where dots can be matched with any letter.

Constraints:

1 <= word.length <= 500
word in addWord consists lower-case English letters.
word in search consist of  '.' or lower-case English letters.
At most 50000 calls will be made to addWord and search.
 */

import java.util.HashMap;
import java.util.Map;

class TrieNode{
    public TrieNode(){}
    Map<Character, TrieNode> children = new HashMap<>();
    boolean end;
}

public class AddAndSearchWord {
    TrieNode trie;

    public AddAndSearchWord() {
        trie = new TrieNode();
    }

    public void addWord(String word) {
        TrieNode node = trie;
        for (int i = 0; i < word.length(); i++) {
            char current = word.charAt(i);
            if (!node.children.containsKey(current)) {
                TrieNode newNode = new TrieNode();
                node.children.put(current, newNode);
            }
            node = node.children.get(current);
        }
        node.end = true;
    }

    // . will not be present in the Trie.
    TrieNode currentNode = trie;
    // Failed strategy: This function is involving changing the global state of a variable.
    // Pass currentNode in the function signature itself.
    public boolean search(String word) {
        TrieNode node = currentNode;

        for (int i = 0; i < word.length(); i++) {
            char current = word.charAt(i);
            if (node.children.containsKey(current)) {
                node = node.children.get(current);
            } else {
                if (current == '.' && node.children.size() > 0) {
                    boolean b = false;
                    for (char key : node.children.keySet()) {
                        currentNode = node.children.get(key);
                        b = search(word.substring(i+1));
                        if (b) return true;
                    }
                    return b;
                } else {
                    return false;
                }
            }
        }
        currentNode = trie;
        return node.end;
    }

    public boolean search(String word, TrieNode node) {
        for(int i=0; i<word.length(); i++){
            char current = word.charAt(i);
            if(node.children.containsKey(current)){
                node = node.children.get(current);
            } else {
                if(current == '.' && node.children.size() > 0){
                    boolean b = false;
                    for(char key: node.children.keySet()){
                        b = search(word.substring(i+1), node.children.get(key));
                        if(b) return true;
                    }
                    return b;
                } else {
                    return false;
                }
            }
        }
        return node.end;
    }

    public boolean searchRefactored(String word, TrieNode node) {
        for(int i=0; i<word.length(); i++){
            char current = word.charAt(i);
            if(node.children.containsKey(current)){
                node = node.children.get(current);
            } else {
                if(current == '.' && node.children.size() > 0){
                    for(char key: node.children.keySet()){ // iterate over potential matches. Any other character can come after a .
                        if(searchRefactored(word.substring(i+1), node.children.get(key))){
                            return true;
                        }
                    }
                }
                return false;
            }
        }
        return node.end;
    }
}