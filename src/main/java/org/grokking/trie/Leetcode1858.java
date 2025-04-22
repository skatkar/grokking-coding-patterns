package org.grokking.trie;

public class Leetcode1858 {
    public String longestWord(String[] words) {
        Trie trie = new Trie();
        // 1. Insert the string one by one
        for(String word : words) {
            trie.insert(word);
        }
        String longest = "";
        // 2. Check if the prefix exists or not.
        for(String word : words) {
            if(trie.checkIfPrefixExists(word)){
                if(word.length() > longest.length()){
                    longest = word;
                } // lexicographically smaller string
                else if(word.length() == longest.length() && word.compareTo(longest) < 0){
                    longest = word;
                }
            }
        }
        return longest;
    }

    class TrieNode {
        TrieNode[] children;
        boolean isEnd;

        public TrieNode() {
            children = new TrieNode[26];
        }
    }

    class Trie {
        TrieNode root;

        public Trie() {
            root = new TrieNode();
        }

        private void insert(String word) {
            TrieNode curr = root;
            for(char ch : word.toCharArray()) {
                if(curr.children[ch - 'a'] == null){
                    curr.children[ch - 'a'] = new TrieNode();
                }
                curr = curr.children[ch - 'a'];
            }
            curr.isEnd = true;
        }

        private boolean checkIfPrefixExists(String word) {
            TrieNode curr = root;
            for(char ch : word.toCharArray()) {
                if(curr.children[ch - 'a'] != null){
                    curr = curr.children[ch - 'a'];

                    // If the current character is not the end, then we can ignore this prefix
                    if(!curr.isEnd) return false;
                }
            }

            return true;
        }
    }
}
