package ramesh.aadhavan.revision;

import java.util.Map;
import java.util.TreeMap;

public class Strings {
    public String findReplaceString(String s, int[] indexes, String[] sources, String[] targets) {
        StringBuilder sb = new StringBuilder();
        TreeMap<Integer, Map.Entry<String, String>> indexMap = new TreeMap<>();
        for(int i=0; i<indexes.length; i++) {
            indexMap.put(indexes[i], Map.entry(sources[i], targets[i]));
        }

        int prev = 0;

        for(Map.Entry<Integer, Map.Entry<String, String>> entry : indexMap.entrySet()) {
            int startIdx = entry.getKey();
            int endIdx = startIdx + entry.getValue().getKey().length();

            if(prev != startIdx)
                sb.append(s, prev, startIdx);

            String toCompare = s.substring(startIdx, endIdx);
            String source = entry.getValue().getKey();
            if(toCompare.equals(source)) {
                String target = entry.getValue().getValue();
                sb.append(target, 0, target.length());
            }
            else {
                sb.append(toCompare);
            }

            prev = endIdx;
        }

        if(prev < s.length()) {
            sb.append(s.substring(prev));
        }

        return sb.toString();
    }
}
