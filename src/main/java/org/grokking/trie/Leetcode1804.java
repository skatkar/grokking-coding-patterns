package org.grokking.trie;

public class Leetcode1804 {
    class TrieNode {
        TrieNode[] children;
        int cntEndWith = 0;
        int cntPrefix = 0;

        public TrieNode() {
            children = new TrieNode[26];
        }
    }

    class Trie {
       TrieNode root;

        public Trie() {
            root = new TrieNode();
        }

        public void insert(String word) {
            TrieNode curr = root;
            for(char ch : word.toCharArray()){
                if(curr.children[ch - 'a'] == null){
                    curr.children[ch - 'a'] = new TrieNode();
                }

                curr = curr.children[ch - 'a'];
                curr.cntPrefix++;
            }
            curr.cntEndWith++;
        }

        public int countWordsEqualTo(String word) {
            TrieNode curr = root;

            for(char ch : word.toCharArray()) {
                if(curr.children[ch - 'a'] != null){
                    curr = curr.children[ch - 'a'];
                }else{
                    return 0;
                }
            }

            return curr.cntEndWith;
        }

        public int countWordsStartingWith(String prefix) {
            TrieNode curr = root;

            for(char ch : prefix.toCharArray()) {
                if(curr.children[ch - 'a'] != null){
                    curr = curr.children[ch - 'a'];
                }else{
                    return 0;
                }
            }

            return curr.cntPrefix;
        }

        public void erase(String word) {
            TrieNode curr = root;
            for(char ch : word.toCharArray()){
                if(curr.children[ch - 'a'] == null){
                    curr.children[ch - 'a'] = new TrieNode();
                }

                curr = curr.children[ch - 'a'];
                curr.cntPrefix--;
            }
            curr.cntEndWith--;
        }
    }

}



