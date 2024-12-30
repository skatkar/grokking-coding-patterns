package org.grokking.graph;

public class Leetcode130 {

    private final int[][] dirs = new int[][]{
            {0, 1},
            {-1, 0},
            {0, -1},
            {1, 0}
    };

    public static void main(String[] args) {
        Leetcode130 q = new Leetcode130();
        char[][] board = new char[][]{
                {'X','O','X','O','X','O'},
                {'O','X','O','X','O','X'},
                {'X','O','X','O','X','O'},
                {'O','X','O','X','O','X'}
        };

        q.solve(board);
    }

    private void dfs(int row, int column, boolean[][] visited, char[][] board){
        if(row < 0 || row >= board.length || column < 0 || column >= board[0].length || visited[row][column]){
            return;
        }

        if(board[row][column] == 'O') {
            visited[row][column] = true;
            for(int[] dir : dirs) {
                int nRow = row + dir[0];
                int nCol = column + dir[1];
                dfs(nRow, nCol, visited, board);
            }
        }
    }

    public void solve(char[][] board) {
        int rows = board.length;
        int columns = board[0].length;
        boolean[][] visited = new boolean[rows][columns];

        // Step 1 - Visit all the 0's on the boundary and mark the regions connected to them as visited
        // First and last row
        for(int col=0; col < columns; col++) {

            // First row
            if(!visited[0][col] && board[0][col] == 'O') {
                dfs(0, col, visited, board);
            }

            // Last row
            if(!visited[rows - 1][col] && board[rows - 1][col] == 'O') {
                dfs(rows - 1, col, visited, board);
            }
        }
        // First and last column
        for(int row=0; row < rows; row++) {

            //First column
            if(!visited[row][0] && board[row][0] == 'O') {
                dfs(row, 0, visited, board);
            }

            // Last column
            if(!visited[row][columns - 1] && board[row][columns - 1] == 'O') {
                dfs(row, columns - 1, visited, board);
            }
        }

        // Step 2 - Visit all other unvisited 0's and convert them to X
        for(int row = 0; row < rows; row++) {
            for(int col = 0; col < columns; col++) {
                if(!visited[row][col] && board[row][col] == 'O'){
                    board[row][col] = 'X';
                }
            }
        }
    }
}
