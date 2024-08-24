package org.educative.graph.concepts;

/**
 * Reference - <a href="https://www.geeksforgeeks.org/problems/implementing-floyd-warshall2042/1?utm_source=youtube&utm_medium=collab_striver_ytdescription&utm_campaign=implementing-floyd-warshall">...</a>
 * This algorithm talks about finding the shortest path from multi-sources unlike Dijkstra's which needs only one starting node.
 */
public class FloydWarshall {
    public void shortest_distance(int[][] matrix) {
        int rows = matrix.length;
        int columns = matrix[0].length;
        // Step 1 - Distance to a self-node is zero.
        // If the distance in the given matrix is -1, replace it with 1e9 temporarily
        for(int i=0; i < rows; i++) {
            for(int j=0; j < columns; j++) {
                if(matrix[i][j] == -1){
                    matrix[i][j] = (int)1e9;
                }

                if(i==j) matrix[i][j] = 0;
            }
        }

        // Step 2 - Move through the intermediate nodes to find the shortest distance
        for(int k=0; k < rows; k++) { // no. of nodes. k is for a via node
            for(int i=0; i < rows; i++) {
                for(int j=0; j < columns; j++) {
                    matrix[i][j] = Math.min(matrix[i][j],
                            matrix[i][k] + matrix[k][j]);
                }
            }
        }

        // Step 3 - Reverting to the initial values as we modified it manually.
        for(int i=0; i < rows; i++) {
            for(int j=0; j < columns; j++) {
                if(matrix[i][j] == (int)1e9){
                    matrix[i][j] = -1;
                }
            }
        }
    }
}
