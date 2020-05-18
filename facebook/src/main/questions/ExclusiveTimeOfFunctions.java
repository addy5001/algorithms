package questions;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;

public class ExclusiveTimeOfFunctions {

    class ProcessInfo {
        int processId;
        boolean isStart;
        int time;

        public ProcessInfo(int processId, boolean isStart, int time) {
            this.processId = processId;
            this.isStart = isStart;
            this.time = time;
        }

        public int getProcessId() {
            return processId;
        }

        public boolean isStart() {
            return isStart;
        }

        public int getTime() {
            return time;
        }
    }

    public int[] exclusiveTime(int n, List<String> logs) {
        return _exclusiveTime(n, logs);
    }

    private Function<String[], ProcessInfo> procFunc = logLine ->
            new ProcessInfo(Integer.parseInt(logLine[0]), logLine[1].equals("start"), Integer.parseInt(logLine[2]));

    private int[] _exclusiveTime(int n, List<String> logs) {
        Deque<ProcessInfo> stack = new LinkedList<>();
        int[] timeFunctions = new int[n];
        String[] firstProcLog = logs.get(0).split(":");
        stack.push(procFunc.apply(firstProcLog));
        int i=1;
        int prevTime = Integer.parseInt(firstProcLog[2]);

        while(i < logs.size()) {
            String[] current = logs.get(i).split(":");
            if(current[1].equals("start")) {
                if(!stack.isEmpty())
                    timeFunctions[stack.peek().processId] += Integer.parseInt(current[2]) - prevTime;

                stack.push(procFunc.apply(current));
                prevTime = Integer.parseInt(current[2]);
            }
            else {
                timeFunctions[stack.peek().processId] += Integer.parseInt(current[2]) - prevTime + 1;
                stack.pop();
                prevTime = Integer.parseInt(current[2]) + 1;
            }

            i++;
        }

        return timeFunctions;
    }
}
