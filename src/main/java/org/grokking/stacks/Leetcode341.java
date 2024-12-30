package org.grokking.stacks;

import java.util.Iterator;
import java.util.List;
import java.util.Stack;

interface NestedInteger {
    boolean isInteger();

    Integer getInteger();

    List<NestedInteger> getList();
}
class NestedIterator implements Iterator<Integer> {
    Stack<NestedInteger> stack;
    public NestedIterator(List<NestedInteger> nestedList) {
        stack = new Stack<>();
        prepareStack(nestedList);
    }

    // Put all the elements of the root level list
    private void prepareStack(List<NestedInteger> nestedList) {
        for(int i=nestedList.size() - 1; i >= 0; i--) {
            stack.push(nestedList.get(i));
        }
    }

    @Override
    public boolean hasNext() {
        while(!stack.isEmpty() && !stack.peek().isInteger()) {
            List<NestedInteger> list = stack.pop().getList();
            prepareStack(list);
        }
        return !stack.isEmpty();
    }

    @Override
    public Integer next() {
        if(!hasNext()){
            return null;
        }
        return stack.pop().getInteger();
    }
}
