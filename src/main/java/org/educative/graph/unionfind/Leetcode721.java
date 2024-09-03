package org.educative.graph.unionfind;

import java.util.*;

public class Leetcode721 {
    public static void main(String[] args) {
        Leetcode721 q = new Leetcode721();
        List<List<String>> lists = q.accountsMerge(Arrays.asList(
                Arrays.asList("David","David0@m.co","David1@m.co"),
                Arrays.asList("David","David3@m.co","David4@m.co"),
                Arrays.asList("David","David4@m.co","David5@m.co"),
                Arrays.asList("David","David2@m.co","David3@m.co"),
                Arrays.asList("David","David1@m.co","David2@m.co")
        ));
        System.out.println("lists = " + lists);
    }

    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        Map<String, Integer> emailMap = new HashMap<>();
        UnionFind uf = new UnionFind(accounts);

        // Step 1 - Do Union find.
        // Accounts having email addresses in common will be grouped here.
        for(int accountIndex=0; accountIndex < accounts.size();accountIndex++){
            List<String> account = accounts.get(accountIndex);
            for(int emailAddressIndex=1; emailAddressIndex < account.size();emailAddressIndex++) {
                if(emailMap.containsKey(account.get(emailAddressIndex))){
                    uf.union(emailMap.get(account.get(emailAddressIndex)), accountIndex);
                }
                emailMap.put(account.get(emailAddressIndex), accountIndex);
            }
        }

        System.out.println("emailMap = " + emailMap);

        // Store emails corresponding to the component's representative
        // Ultimate parent number to email Address mapping
        Map<Integer, List<String>> components = new HashMap<>();
        for (String email : emailMap.keySet()) {
            int group = emailMap.get(email);
            int groupRep = uf.find(group);

            components.putIfAbsent(groupRep, new ArrayList<>());
            components.get(groupRep).add(email);
        }

        // Sort the components and add the account name
        List<List<String>> mergedAccounts = new ArrayList<>();
        for (int group : components.keySet()) {
            List<String> component = components.get(group);
            Collections.sort(component);
            component.add(0, accounts.get(group).get(0));
            mergedAccounts.add(component);
        }

        return mergedAccounts;
    }

    class UnionFind{
        int[] parents;
        int[] size;

        List<List<String>> accounts;

        public UnionFind(List<List<String>> accounts){
            this.parents = new int[accounts.size()];
            this.size = new int[accounts.size()];
            this.accounts = accounts;

            for(int i=0; i < accounts.size();i++) {
                parents[i] = i;
                size[i] = 1;
            }
        }

        public int find(int node){
            if(node == parents[node])
                return node;
            parents[node] = find(parents[node]);
            return parents[node];
        }

        public void union(int nodeA, int nodeB) {
            int parentA = find(nodeA);
            int parentB = find(nodeB);

            if(parentA == parentB) return;

            if(size[parentB] < size[parentA]){
                parents[parentB] = parentA;
                size[parentA] = size[parentA] + size[parentB];
            }else{
                parents[parentA] = parentB;
                size[parentB] = size[parentB] + size[parentA];
            }
        }



    }
}
