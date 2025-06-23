package org.grokking.hashing;

import java.util.*;

public class Leetcode3508 {
    class Packet {
        int source;
        int destination;
        int timestamp;

        public Packet(int source, int destination, int timestamp) {
            this.source = source;
            this.destination = destination;
            this.timestamp = timestamp;
        }

        @Override
        public final boolean equals(Object obj) {
            if (!(obj instanceof Packet)) return false;

            if(this == obj) return true;

            Packet packet = (Packet) obj;
            return source == packet.source &&
                    destination == packet.destination &&
                    timestamp == packet.timestamp;
        }

        @Override
        public int hashCode() {
            return Objects.hash(source, destination, timestamp);
        }

        public int getSource() {
            return source;
        }

        public int getDestination() {
            return destination;
        }

        public int getTimestamp() {
            return timestamp;
        }
    }

    class Router {
        // Tracks seen packets to avoid duplicates
        Set<Packet> packetSet = new HashSet<>();

        // Stores packets in FIFO order
        Queue<Packet> packetQueue = new LinkedList<>();

        // Maps destination -> list of all timestamps (including ones of removed packets)
        Map<Integer, List<Integer>> destinationTimestamps = new HashMap<>();

        // Maps destination -> count of old (removed) packets to skip in timestamp list
        Map<Integer, Integer> expiredPacketIndex = new HashMap<>();

        int capacity;

        public Router(int memoryLimit) {
            this.capacity = memoryLimit;
        }

        public boolean addPacket(int source, int destination, int timestamp) {
            Packet newPacket = new Packet(source, destination, timestamp);

            if (packetSet.contains(newPacket))
                return false;

            if (packetQueue.size() == capacity) {
                // Remove the oldest packet to make space
                Packet evicted = packetQueue.poll();
                packetSet.remove(evicted);

                int dest = evicted.getDestination();
                // Mark one more expired packet for this destination
                expiredPacketIndex.put(dest, expiredPacketIndex.getOrDefault(dest, 0) + 1);
            }

            packetQueue.offer(newPacket);
            packetSet.add(newPacket);

            destinationTimestamps
                    .computeIfAbsent(destination, k -> new ArrayList<>())
                    .add(timestamp);

            return true;
        }

        public int[] forwardPacket() {
            if (packetQueue.isEmpty()) return new int[0];

            Packet packet = packetQueue.poll();
            packetSet.remove(packet);

            int destination = packet.getDestination();
            expiredPacketIndex.put(destination, expiredPacketIndex.getOrDefault(destination, 0) + 1);

            return new int[] { packet.getSource(), packet.getDestination(), packet.getTimestamp() };
        }

        public int getCount(int destination, int startTime, int endTime) {
            if (!destinationTimestamps.containsKey(destination))
                return 0;

            List<Integer> timestamps = destinationTimestamps.get(destination);
            int skipIndex = expiredPacketIndex.getOrDefault(destination, 0);

            int from = lowerBound(timestamps, startTime, skipIndex);
            int to = upperBound(timestamps, endTime, skipIndex);

            return to - from;
        }

        // Find first index >= target starting from 'start'
        private int lowerBound(List<Integer> timestamps, int target, int skipIndex) {
            int left = skipIndex, right = timestamps.size();
            while (left < right) {
                int mid = (left + right) / 2;
                if (timestamps.get(mid) < target)
                    left = mid + 1;
                else
                    right = mid;
            }
            return left;
        }

        // Find first index > target starting from 'start'
        private int upperBound(List<Integer> timestamps, int target, int skipIndex) {
            int left = skipIndex, right = timestamps.size();
            while (left < right) {
                int mid = (left + right) / 2;
                if (timestamps.get(mid) <= target)
                    left = mid + 1;
                else
                    right = mid;
            }
            return left;
        }
    }
}
