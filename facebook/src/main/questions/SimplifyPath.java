package questions;

import java.util.ArrayDeque;
import java.util.Deque;

public class SimplifyPath {
    public String simplifyPath(String path) {
        return _simplifyPath(path);
    }

    private String _simplifyPath(String path) {
        String[] tokens = path.split("/");
        Deque<String> pathStack = new ArrayDeque<>();
        int idx = 0;

        while(idx < tokens.length) {
            if(tokens[idx].isBlank() || tokens[idx].equals(".")) {
                idx++;
                continue;
            }
            else {
                if(tokens[idx].equals("..")) {
                    if(!pathStack.isEmpty())
                        pathStack.pop();
                }
                else {
                    pathStack.push(tokens[idx]);
                }
            }

            idx++;
        }

        StringBuilder resultBuilder = new StringBuilder();
        if(pathStack.isEmpty()) {
            resultBuilder.append("/");
        }
        else {
            while (!pathStack.isEmpty()) {
                resultBuilder.append("/");
                resultBuilder.append(pathStack.pollLast());
            }
        }

        return resultBuilder.toString();
    }
}
