package AlgoExpert_Medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class GroupAnagrams {
	
	public static void main(String[] args) {
		Map<String, Integer> anagrams = new HashMap<>(); 
		
		anagrams.put("act", 2) ;
		anagrams.put("cat", 5) ; 
		
		System.out.println(anagrams.get("act"));
		System.out.println(anagrams.get("cat"));
		
		String[] array = { "yo", "act", "flop", "tac", "cat", "oy", "olfp" , "zzz" }; 
		
		System.out.println( groupAnagramsMethod1(Arrays.asList(array)) );
		System.out.println( groupAnagramsMethod2(Arrays.asList(array)) );

	}

	// O(WNlogN) time and O(WN) space, w: number of words and n is the length of the longest word. 
	public static List<List<String>> groupAnagramsMethod2(List<String> words) {
		
		Map<String, List<String>> anagrams = new HashMap<>(); 
		
		for(String word: words) {
			char[] charArray = word.toCharArray();
			Arrays.sort(charArray);
			String sortedWord = new String(charArray); 
			
			if(anagrams.containsKey(sortedWord)) {
				anagrams.get(sortedWord).add(word) ; 
			} else {
				anagrams.put(sortedWord, new ArrayList<String>(Arrays.asList(word)) );
			}
		}
		
		List<List<String>> output = new ArrayList<>(); 
		for(Map.Entry<String, List<String>> entry : anagrams.entrySet()) {
			output.add(entry.getValue()) ; 
		}
		
		return output; 
	}
	
	// O(WNlogN + NWlogW ) time, O(WN) space, W: number of strings, N: length of the longest string.
	public static List<List<String>> groupAnagramsMethod1(List<String> words) {
		
		if(words.isEmpty()) {
			return new ArrayList<>() ; 
		}
		
		List<String> sortedWords = new ArrayList<>(); 
		
		for(String word: words) {
			char[] charArray = word.toCharArray();
			Arrays.sort(charArray);
			String sortedWord = new String(charArray); 
			sortedWords.add(sortedWord); 
		}
		
		List<Integer> indices = IntStream.range(0, words.size()).boxed().collect(Collectors.toList());
		// create a list with values equal to indices : 0, 1, 2, 3, ...

		//System.out.println("indices is : " + indices);
		indices.sort((a, b) -> sortedWords.get(a).compareTo(sortedWords.get(b)));
		// sort the indices list based on sortedWord list.
		//System.out.println("indices is : " + indices);

		List<List<String>> result = new ArrayList<>(); 
		List<String> currentAnagramGroup = new ArrayList<>(); 
		
		String currentAnagram = sortedWords.get(indices.get(0));
		
		for(Integer index: indices) {
			String word = words.get(index); 
			String sortedWord = sortedWords.get(index); 
			
			if(sortedWord.equals(currentAnagram)) {
				currentAnagramGroup.add(word); 
				continue;
			}
			
			result.add(currentAnagramGroup); // done filling the previous anagram group. 
			// create a new anagram group with one word element as if we only create an empty list, then it will miss this and move to next index.
			currentAnagramGroup = new ArrayList<>(Arrays.asList(word)); // create a new group. Or could have created an empty list and then added word in it.
			currentAnagram = sortedWord;  			
		}

		// either the above loop will finish because of continue or if would evaluate to false.
		result.add(currentAnagramGroup); 
		
		return result;
	}

}