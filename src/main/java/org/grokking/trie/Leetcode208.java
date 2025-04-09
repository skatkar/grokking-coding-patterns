package org.grokking.trie;

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

    public void insert(String word) {
        TrieNode curr = root;
        for(char ch : word.toCharArray()) {
            if(curr.children[ch - 'a'] == null){
                curr.children[ch - 'a'] = new TrieNode();
            }

            curr = curr.children[ch - 'a'];
        }

        curr.isEnd = true;
    }

    /**
     * Returns true if the string word is in the trie and false otherwise.
     * @param word
     * @return
     */
    public boolean search(String word) {
        TrieNode curr = root;
        for(char ch : word.toCharArray()) {
            if(curr.children[ch = 'a'] == null){
                return false;
            }

            curr = curr.children[ch - 'a'];
        }

        return curr.isEnd;
    }

    /**
     * Returns true if there is a previously inserted string word that has the prefix, and false otherwise.
     * @param prefix
     * @return
     */
    public boolean startsWith(String prefix) {
        TrieNode curr = root;
        for(char ch : prefix.toCharArray()) {
            if(curr.children[ch = 'a'] == null){
                return false;
            }

            curr = curr.children[ch - 'a'];
        }

        return true;
    }
}
