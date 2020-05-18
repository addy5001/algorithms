package questions;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class NestedArraySum {

    public interface NestedInteger {
        // @return true if this NestedInteger holds a single integer, rather than a nested list.
        public boolean isInteger();

        // @return the single integer that this NestedInteger holds, if it holds a single integer
        // Return null if this NestedInteger holds a nested list
        public Integer getInteger();

        // Set this NestedInteger to hold a single integer.
        public void setInteger(int value);

        // Set this NestedInteger to hold a nested list and adds a nested integer to it.
        public void add(NestedInteger ni);

        // @return the nested list that this NestedInteger holds, if it holds a nested list
        // Return null if this NestedInteger holds a single integer
        public List<NestedInteger> getList();
  }

    public int depthSum(List<NestedInteger> nestedList) {
        return _depthSum(nestedList, 1);
    }

    private int _depthSum(List<NestedInteger> nestedIntegers, int depth) {
        if(nestedIntegers == null || nestedIntegers.size() == 0)
            return 0;

        int sum = 0;
        for(NestedInteger nestedInteger : nestedIntegers) {
            if(nestedInteger.isInteger())
                sum += nestedInteger.getInteger() * depth;
            else
                sum += _depthSum(nestedInteger.getList(), depth+1);
        }

        return sum;
    }

    public int _depthSumIterative(List<NestedInteger> nestedList) {
        int sum=0;

        LinkedList<NestedInteger> queue = new LinkedList<NestedInteger>();
        LinkedList<Integer> depth = new LinkedList<Integer>();

        for(NestedInteger ni: nestedList){
            queue.offer(ni);
            depth.offer(1);
        }

        while(!queue.isEmpty()){
            NestedInteger top = queue.poll();
            int dep = depth.poll();

            if(top.isInteger()){
                sum += dep*top.getInteger();
            }else{
                for(NestedInteger ni: top.getList()){
                    queue.offer(ni);
                    depth.offer(dep+1);
                }
            }
        }

        return sum;
    }

    public int depthSumInverse(List<NestedInteger> nestedList) {
        int unweighted = 0;
        int weighted = 0;

        while(!nestedList.isEmpty()) {
            List<NestedInteger> nextLevel = new ArrayList<>();
            for(NestedInteger n : nestedList) {
                if(n.isInteger())
                    unweighted += n.getInteger();
                else
                    nextLevel.addAll(n.getList());
            }

            weighted += unweighted;
            nestedList = nextLevel;
        }

        return weighted;
    }
}
