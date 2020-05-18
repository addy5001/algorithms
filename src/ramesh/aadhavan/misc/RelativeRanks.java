package ramesh.aadhavan.misc;

import java.util.*;

public class RelativeRanks {
    public String[] findRelativeRanks(int[] nums) {
        Map<Integer, String> rankMap = new LinkedHashMap<>();
        Queue<Integer> rankQ = new PriorityQueue<>((n1, n2) -> n2 - n1);

        for(int i=0; i<nums.length; i++) {
            rankMap.put(nums[i], "");
            rankQ.offer(nums[i]);
        }

        int order = 0;
        int inc = 4;

        while(!rankQ.isEmpty()) {
            int i = rankQ.poll();

            if(order == 0) {
                rankMap.put(i, "Gold Medal");
            }
            else if(order == 1) {
                rankMap.put(i, "Silver Medal");
            }
            else if(order == 2) {
                rankMap.put(i, "Bronze Medal");
            }
            else {
                rankMap.put(i, Integer.toString(inc));
                inc++;
            }

            order++;
        }

        Iterator<Map.Entry<Integer, String>> iterator = rankMap.entrySet().iterator();
        String[] results = new String[nums.length];
        int i = 0;
        while(iterator.hasNext()) {
            Map.Entry<Integer, String> entry = iterator.next();
            results[i] = entry.getValue();
            i++;
        }

        return results;
    }
}
