package org.grokking.trie;

public class Leetcode1698 {
    public int countDistinct(String s) {
        Trie trie = new Trie();
        int distinctStrings = 0;
        for(int i=0; i < s.length(); i++) {
            distinctStrings += trie.insert(s.substring(i));
        }

        return distinctStrings;
    }

    class TrieNode {
        TrieNode[] children;

        public TrieNode(){
            children = new TrieNode[26];
        }
    }

    class Trie {
        TrieNode root;

        public Trie() {
            root = new TrieNode();
        }

        public int insert(String word) {
            TrieNode curr = root;
            int count = 0;
            for(char ch : word.toCharArray()) {
                if(curr.children[ch - 'a'] == null){
                    curr.children[ch - 'a'] = new TrieNode();
                    count++;
                }
                curr = curr.children[ch - 'a'];
            }
            return count;
        }
    }
}
