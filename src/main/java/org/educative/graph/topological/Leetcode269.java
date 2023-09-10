package org.educative.graph.topological;

import java.util.*;

public class Leetcode269 {
    Map<Character, List<Character>> map;
    int[] indegrees;
    public String alienOrder(String[] words) {
        map = new HashMap<>();
        indegrees = new int[26];
        StringBuilder result = new StringBuilder();
        Queue<Character> characterQueue = new LinkedList<>();
        
        buildGraph(words);

        for(char c : map.keySet()) {
            if(indegrees[c - 'a'] == 0){
                characterQueue.add(c);
                result.append(c);
            }
        }

        while(!characterQueue.isEmpty()) {
            char currChar = characterQueue.poll();
            List<Character> neighbors = map.get(currChar);
            if(neighbors != null){
                for(char neighbor : neighbors){
                    indegrees[neighbor - 'a']--;
                    if(indegrees[neighbor - 'a'] == 0){
                        characterQueue.add(neighbor);
                        result.append(neighbor);
                    }
                }
            }
        }
        
        return result.length() == map.size() ? result.toString() : ""; 
    }

    private void buildGraph(String[] words) {
        for(String word : words) {
            for(int i=0; i < word.length(); i++) {
                if(!map.containsKey(word.charAt(i))){
                    map.put(word.charAt(i), new ArrayList<>());
                }
            }
        }

        for(int i=0; i < words.length - 1; i++){
            String firstWord = words[i];
            String secondWord = words[i + 1];

            int fLength = firstWord.length();
            int sLength = secondWord.length();

            // if the second word is the substring of the first word
            if(firstWord.startsWith(secondWord) && fLength > sLength){
                map.clear();
                return;
            }
            for(int j=0; j < fLength && j < sLength; j++){
                if(firstWord.charAt(j) != secondWord.charAt(j)){
                    char out = firstWord.charAt(j);
                    char in = secondWord.charAt(j);

                    map.get(out).add(in);
                    indegrees[in - 'a']++;
                    break;
                }
            }
        }
    }


}
