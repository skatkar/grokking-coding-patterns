package org.grokking.graph.unionfind;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Leetcode990 {
    public static void main(String[] args) {
        Leetcode990 q = new Leetcode990();
        boolean equationsPossible = q.equationsPossible(new String[]{
                "a==b",
                "b!=c",
                "c==a"
        });
        System.out.println("equationsPossible = " + equationsPossible);
    }

    public boolean equationsPossible(String[] equations) {
        // Separate out the strings with ==
        List<String> equality = Arrays.stream(equations)
                .filter(equation -> equation.contains("=="))
                .collect(Collectors.toList());

        // Separate out the strings with !=
        List<String> inEquality = Arrays.stream(equations)
                .filter(equation -> equation.contains("!="))
                .collect(Collectors.toList());

        UnionFind uf = new UnionFind();
        // Union the variables from the equality equations
        equality.forEach(equation -> uf.unionByEquality(equation.charAt(0) - 'a', equation.charAt(3) - 'a'));

        // Among the inequality equations, if the equality is already established above, then the current equation is invalid
        for(String equation : inEquality) {
            if(uf.find(equation.charAt(0) - 'a') == uf.find(equation.charAt(3) - 'a'))
                return false;
        }
        return true;
    }

    class UnionFind {
        int[] parent;
        int[] rank;

        public UnionFind(){
            parent = new int[26];
            for(int i=0; i < 26; i++) {
                parent[i] = i;
            }
            rank = new int[26];
        }

        private int find(int variable) {
            if(parent[variable] == variable)
                return variable;
            parent[variable] = find(parent[variable]);
            return parent[variable];
        }

        private void unionByEquality(int varA, int varB) {
            int parentA = find(varA);
            int parentB = find(varB);

            if(rank[parentB] < rank[parentA]){
                parent[parentB] = parentA;
            }else if (rank[parentA] < rank[parentB]){
                parent[parentA] = parentB;
            }else{
                parent[parentB] = parentA;
                rank[parentA]++;
            }
        }
    }
}
