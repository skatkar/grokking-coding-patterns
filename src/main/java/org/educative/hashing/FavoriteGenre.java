package org.educative.hashing;

import java.util.*;

public class FavoriteGenre {
    public static Map<String, List<String>> favoritegenre(Map<String, List<String>> userMap, Map<String, List<String>> genreMap) {
        // user to genre mapping
        Map<String, List<String>> result = new HashMap<>();

        // 1. Make a mapping of song to genre
        Map<String, String> songToGenre = new HashMap<>();
        for(Map.Entry<String, List<String>> entry : genreMap.entrySet()) {
            List<String> songs = entry.getValue();
            songs.forEach(song -> songToGenre.put(song, entry.getKey()));
        }
        
        // 2. User to genre mapping
        for(Map.Entry<String, List<String>> userEntry : userMap.entrySet()){
            Map<String, Integer> countmap = new HashMap<>();

            List<String> songsThatUserListen = userEntry.getValue();
            int max = 0;
            for(String song : songsThatUserListen) {
                String genre = songToGenre.get(song);
                countmap.put(genre, countmap.getOrDefault(genre, 0) + 1);
                max = Math.max(max, countmap.get(genre));
            }

            generateListForUser(userEntry.getKey(), max, result, countmap);
        }
        return result;
    }

    private static void generateListForUser(String user, int max, Map<String, List<String>> result, Map<String, Integer> countmap) {
        for(Map.Entry<String, Integer> entry : countmap.entrySet()) {
            if(entry.getValue() == max) {
                List<String> genres = result.getOrDefault(user, new ArrayList<>());
                genres.add(entry.getKey());
                result.put(user, genres);
            }
        }
    }

    public static void main(String[] args) {
        HashMap<String, List<String>> userSongs = new HashMap<>();

        userSongs.put("David", Arrays.asList(new String[]{"song1", "song2", "song3", "song4", "song8"}));

        userSongs.put("Emma", Arrays.asList(new String[]{"song5", "song6", "song7"}));

        HashMap<String, List<String>> songGenres = new HashMap<>();

        songGenres.put("Rock", Arrays.asList(new String[]{"song1", "song3"}));

        songGenres.put("Dubstep", Arrays.asList(new String[]{"song7"}));

        songGenres.put("Techno", Arrays.asList(new String[]{"song2", "song4"}));

        songGenres.put("Pop", Arrays.asList(new String[]{"song5", "song6"}));

        songGenres.put("Jazz", Arrays.asList(new String[]{"song8", "song9"}));

        Map<String, List<String>> res = favoritegenre(userSongs, songGenres);

        System.out.println(res);
    }
}
