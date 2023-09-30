package org.educative.unionfind;

import java.util.ArrayList;
import java.util.List;

public class Leetcode305 {
    public static void main(String[] args) {
        Leetcode305 q = new Leetcode305();
        List<Integer> integers = q.numIslands2(3, 3, new int[][]{
                {0, 0},
                {0, 1},
                {1, 2},
                {2, 1}
        });
        System.out.println("integers = " + integers);
    }

    public List<Integer> numIslands2(int m, int n, int[][] positions) {
        int[][] dirs = new int[][]{
                {-1,0},
                {1,0},
                {0,-1},
                {0,1}
        };
        List<Integer> result = new ArrayList<>();
        DSU dsu = new DSU(m * n);

        for(int[] position : positions){
            int landPosition = position[0] * n + position[1];
            dsu.addLand(landPosition);

            for(int[] dir : dirs){
                int neighborX = position[0] + dir[0];
                int neighborY = position[1] + dir[1];
                int neighborPosition = neighborX * n + neighborY;

                if(neighborX >=0 && neighborX < m && neighborY >= 0 && neighborY < n && dsu.isLand(neighborPosition)){
                    dsu.union(landPosition, neighborPosition);
                }
            }

            result.add(dsu.getNoOfIslands());
        }

        return result;
    }

    class DSU {
        int[] parents;
        int[] rank;
        int noOfIslands;

        public DSU(int length){
            parents = new int[length];
            rank = new int[length];
            noOfIslands = 0;

            for(int i=0; i < length; i++){
                parents[i] = -1;
            }
        }

        public int getNoOfIslands(){
            return noOfIslands;
        }

        public void addLand(int landPosition){
            // We might get the same x,y coordinates more than once
            // This will work as a "visited" array
            if(parents[landPosition] >= 0)
                return;
            parents[landPosition] = landPosition;
            noOfIslands++;
        }

        public boolean isLand(int landPosition){
            return parents[landPosition] != -1;
        }

        public int find(int landPosition){
            if(parents[landPosition] != landPosition)
                parents[landPosition] = find(parents[landPosition]);
            return parents[landPosition];
        }

        public void union(int nodeA, int nodeB){
            int parentA = find(nodeA);
            int parentB = find(nodeB);

            if(parentA == parentB) return;

            if(rank[parentB] < rank[parentA]){
                parents[parentB] = parentA;
            }else if(rank[parentA] < rank[parentB]){
                parents[parentA] = parentB;
            }else{
                parents[parentB] = parentA;
                rank[parentA]++;
            }

            noOfIslands--;
        }
    }
}
