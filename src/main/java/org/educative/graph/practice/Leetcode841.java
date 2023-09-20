package org.educative.graph.practice;


import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Leetcode841 {
    Set<Integer> visited;
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
