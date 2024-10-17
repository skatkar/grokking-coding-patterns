package org.educative.graph.traversal.dfs;


import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

public class Leetcode841 {
    Set<Integer> visited;
    public boolean canVisitAllRooms2(List<List<Integer>> rooms){
        visited = new HashSet<>();
        visited.add(0);

        dfsRecursive(0, rooms);

        return visited.size() == rooms.size();
    }

    private void dfsRecursive(int room, List<List<Integer>> rooms){
        Stack<Integer> stack = new Stack<>();
        stack.add(room);

        while(!stack.empty()) {
            int currRoom = stack.pop();
            List<Integer> connectedRooms = rooms.get(currRoom);
            if(!connectedRooms.isEmpty()){
                for(int neighbor : connectedRooms) {
                    if(!visited.contains(neighbor)){
                        stack.push(neighbor);
                        visited.add(neighbor);
                    }
                }
            }
        }
    }

    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        visited = new HashSet<>();

        visited.add(0);

        dfs(0, rooms);

        return visited.size() == rooms.size();
    }

    private void dfs(int room, List<List<Integer>> rooms) {
        List<Integer> connectedRooms = rooms.get(room);
        if(!connectedRooms.isEmpty()){
            for(int connectedRoom : connectedRooms){
                if(visited.contains(connectedRoom))
                    continue;
                visited.add(connectedRoom);
                dfs(connectedRoom, rooms);
            }
        }
    }
}
