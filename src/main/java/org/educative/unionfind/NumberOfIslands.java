package org.educative.unionfind;

import java.util.Arrays;

class Leetcode200 {
    int[][] dirs;
    int rows, columns;
    public int numIslands(char[][] matrix) {
        rows = matrix.length;
        columns = matrix[0].length;
        dirs = new int[][]{
                {-1,0},
                {1,0},
                {0,-1},
                {0,1}
        };

        DSU dsu = new DSU(matrix);

        for(int i=0; i < rows; i++){
            for(int j=0; j < columns; j++){
                if(matrix[i][j] == '1'){
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

        return dsu.noOfIslands;
    }

    class DSU {
        int[] parents;
        int[] size;
        int noOfIslands;

        public DSU(char[][] matrix){
            parents = new int[rows * columns];
            size = new int[rows * columns];
            noOfIslands = 0;

            Arrays.fill(parents, -1);

            for(int i=0; i < rows;i++){
                for (int j=0; j < columns;j++){
                    if(matrix[i][j] == '1'){
                        int landPosition = i * columns + j;
                        parents[landPosition] = landPosition;
                        size[landPosition] = 1;
                        noOfIslands++;
                    }
                }
            }
        }

        public boolean isLand(int landPosition){
            return parents[landPosition] != -1;
        }

        public int find(int landPosition){
            if(landPosition == parents[landPosition])
                return landPosition;
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
            }else{
                parents[parentA] = parentB;
                size[parentB] += size[parentA];
            }
            noOfIslands--;
        }
    }

}
