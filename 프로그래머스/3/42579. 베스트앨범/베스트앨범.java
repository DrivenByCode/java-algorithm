import java.util.*;
class Solution {
    private static class SongInfo implements Comparable<SongInfo> {
        private final int index;
        private final int playsCount;
        private SongInfo(final int index, final int playsCount) {
            this.index = index;
            this.playsCount = playsCount;
        }

        @Override
        public int compareTo(SongInfo other) {
            if (this.playsCount == other.playsCount) {
                return Integer.compare(this.index, other.index);
            }
            return Integer.compare(other.playsCount, this.playsCount);
        }
    }
    
    private static class GenreInfo implements Comparable<GenreInfo> {
        private final String genre;
        private final int playsCount;
        private GenreInfo(final String genre, final int playsCount) {
            this.genre = genre;
            this.playsCount = playsCount;
        }
        
        @Override
        public int compareTo(GenreInfo other) {
            return Integer.compare(other.playsCount, this.playsCount);
        }
    }
    
    public int[] solution(String[] genres, int[] plays) {        
        // 장르별 플레이 횟수 높은 노래 카운트
        Map<String, PriorityQueue<SongInfo>> songs = new HashMap<>();
        // 장르별 플레이 갯수 카운트
        Map<String, Integer> genreCounts = new HashMap<>();
        
        int len = genres.length;
                
       for(int i = 0; i < len; i++) {
            songs.putIfAbsent(genres[i], new PriorityQueue<>());
            songs.get(genres[i]).offer(new SongInfo(i, plays[i]));
            genreCounts.put(genres[i], genreCounts.getOrDefault(genres[i], 0) + plays[i]);
        }
        
        List<Integer> list = new ArrayList<>();
        
        genreCounts.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .forEach(entry -> {
                    PriorityQueue<SongInfo> pq = songs.get(entry.getKey());
                    int count = 0;
                    while (!pq.isEmpty() && count < 2) {
                        list.add(pq.poll().index);
                        count++;
                    }
                });
                
        return list.stream().mapToInt(i->i).toArray();
    }
}