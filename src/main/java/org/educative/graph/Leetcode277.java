package org.educative.graph;

abstract class Relation {
    boolean knows(int a, int b){
        return true;
    }
}
public class Leetcode277 extends Relation {
    public int findCelebrity(int n) {
        int celeb = 0;
        for(int i=1; i < n; i++){
            if(knows(celeb, i))
                celeb = i;
        }
        // So far, we assumed someone to be a celebrity. But we did not check the reverse
        // i.e. for celeb as 2, we didn't check whether 3,4,5 know 2 or not.

        for(int i=0; i < n; i++){
            if(i < celeb){
                if(knows(celeb, i) || !knows(i, celeb))
                    return -1;
            } else {
                if(!knows(i, celeb))
                    return -1;
            }
        }
        return celeb;
    }
}
