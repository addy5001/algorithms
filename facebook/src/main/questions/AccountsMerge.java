package questions;

import java.util.*;

public class AccountsMerge {

    /**
     * Form edges between first email and all other emails in each account
     * Traverse DFS for each email in the graph, find all connected components
     * Sort the components
     * Add name, components to final list.
     * @param accounts
     * @return
     */
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        Map<String, List<String>> graph = new HashMap<>();
        Map<String, String> emailToName = new HashMap<>();

        for(List<String> account : accounts) {
            String name = account.get(0);

            for(String email : account.subList(1, account.size())) {
                graph.computeIfAbsent(email, key -> new ArrayList<>()).add(account.get(1));
                graph.computeIfAbsent(account.get(1), key -> new ArrayList<>()).add(email);
                emailToName.put(email, name);
            }
        }

        Set<String> visited = new HashSet<>();
        List<List<String>> results = new ArrayList<>();

        for(String email : graph.keySet()) {
            if(!visited.contains(email)) {
                visited.add(email);
                Deque<String> visiting = new ArrayDeque<>();
                visiting.push(email);
                List<String> component = new ArrayList<>();

                while(!visiting.isEmpty()) {
                    String node = visiting.pop();
                    component.add(node);
                    for(String neighbor : graph.get(node)) {
                        if(!visited.contains(neighbor)) {
                            visited.add(neighbor);
                            visiting.push(neighbor);
                        }
                    }
                }

                Collections.sort(component);
                List<String> mergedList = new ArrayList<>();
                mergedList.add(emailToName.get(email));
                mergedList.addAll(component);
                results.add(mergedList);
            }
        }

        return results;
    }
}
