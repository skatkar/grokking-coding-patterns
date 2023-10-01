package org.educative.unionfind;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Leetcode827 {
    int rows, columns;

    public static void main(String[] args) {
        Leetcode827 q = new Leetcode827();
        int answer = q.largestIsland(new int[][]{
                {1, 1, 0, 1, 1},
                {1, 1, 0, 1, 1},
                {1, 1, 0, 1, 1},
                {0,0,1,0,0},
                {0,0,1,1,1},
                {0,0,1,1,1}
        });

        System.out.println("answer = " + answer);
    }

    public int largestIsland(int[][] grid) {
        int maxIslandArea = Integer.MIN_VALUE;

        int[][] dirs = new int[][]{
                {-1,0},
                {1,0},
                {0,-1},
                {0,1}
        };

        rows = grid.length;
        columns = grid[0].length;
        DSU dsu = new DSU(grid);
        //Step 1- Connect the islands and maintain the size of each disjoint set
        for(int i=0; i < rows; i++){
            for(int j=0; j < columns; j++){
                if(grid[i][j] == 1){
                    int landPosition = i * columns + j;
                    for(int[] dir : dirs){
                        int neighborX = i + dir[0];
                        int neighborY = j + dir[1];
                        int neighborPosition = neighborX * columns + neighborY;
                        if(neighborX >= 0 && neighborX < rows && neighborY >= 0 && neighborY < columns &&
                                dsu.isLand(neighborPosition)){
                            dsu.union(landPosition, neighborPosition);
                        }
                    }
                }
            }
        }

        // Step 2 - For each 0 in the grid, find out the neighboring components' representative and the size for that representative
        for(int i=0; i < rows; i++){
            for(int j=0; j < columns; j++){
                if(grid[i][j] == 0){
                    Set<Integer> ulp = new HashSet<>();
                    int currentArea = 0;
                    for(int[] dir : dirs){
                        int neighborX = i + dir[0];
                        int neighborY = j + dir[1];
                        int neighborPosition = neighborX * columns + neighborY;
                        if(neighborX >= 0 && neighborX < rows && neighborY >= 0 && neighborY < columns && dsu.isLand(neighborPosition)) {
                            int adjacentComponent = dsu.find(neighborPosition);
                            ulp.add(adjacentComponent);
                        }
                    }
                    for (Integer integer : ulp) {
                        currentArea += dsu.size[integer];
                    }

                    maxIslandArea = Math.max(maxIslandArea, currentArea + 1);
                }
            }
        }


        return maxIslandArea == Integer.MIN_VALUE ? Arrays.stream(dsu.size).max().getAsInt() : maxIslandArea;
    }

    class DSU {
        int[] parents;
        int[] size;


        public DSU(int[][] grid){
            parents = new int[rows * columns];
            size = new int[rows * columns];


            Arrays.fill(parents, -1);

            for(int i=0; i < rows; i++){
                for(int j=0; j < columns; j++){
                    if(grid[i][j] == 1) {
                        addLand(i * columns + j);
                        size[i * columns + j]++;
                    }
                }
            }
        }

        public void addLand(int landPosition){
            // We might get the same x,y coordinates more than once
            // This will work as a "visited" array
            if(parents[landPosition] >= 0)
                return;
            parents[landPosition] = landPosition;

        }

        public boolean isLand(int landPosition){
            return parents[landPosition] != -1;
        }

        public int[] getParents(){
            return parents;
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

            if(size[parentB] < size[parentA]){
                parents[parentB] = parentA;
                size[parentA] += size[parentB];
            }else {
                parents[parentA] = parentB;
                size[parentB] += size[parentA];
            }
        }
    }
}
