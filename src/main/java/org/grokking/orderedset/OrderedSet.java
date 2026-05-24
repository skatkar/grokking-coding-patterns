package org.grokking.orderedset;

import java.util.Comparator;
import java.util.TreeSet;

public class OrderedSet {
    private TreeSet<Integer> set;

    public OrderedSet(Comparator<Integer> comparator) {
        this.set = new TreeSet<>(comparator);
    }

    public static void main(String[] args) {
        OrderedSet orderedSet = new OrderedSet(Comparator.reverseOrder());

        orderedSet.add(5);
        orderedSet.add(1);
        orderedSet.add(2);

        System.out.println("orderedSet.findByOrder(2) = " + orderedSet.findByOrder(1));
        System.out.println("orderedSet.orderOfKey(3) = " + orderedSet.orderOfKey(3));
    }

    public void add(Integer element){
        set.add(element);
    }

    public Integer findByOrder(int k) {
        if(k < 0 || k >= set.size()) return null;
        return (Integer) set.toArray()[k];
    }

    public int orderOfKey(int element) {
        return set.headSet(element, true).size();
    }
}
