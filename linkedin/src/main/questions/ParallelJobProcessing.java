package questions;

import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.atomic.AtomicInteger;

public class ParallelJobProcessing {

    ForkJoinPool forkJoinPool;

    static class MultiplyBy2Task extends RecursiveTask<Integer> {

        List<Integer> multiplicatives;
        ForkJoinPool forkJoinPool;

        public MultiplyBy2Task(List<Integer> multiplicatives, ForkJoinPool forkJoinPool) {
            this.multiplicatives = multiplicatives;
            this.forkJoinPool = forkJoinPool;
        }

        @Override
        protected Integer compute() {
            if(multiplicatives.size() > 2) {
                List<MultiplyBy2Task> tasks = _createSubTasks();
                return tasks.stream()
                        .mapToInt(forkJoinPool::invoke)
                        .sum();
            }
            else {
                return multiplicatives.stream()
                        .mapToInt(i -> i*2)
                        .sum();
            }
        }

        private List<MultiplyBy2Task> _createSubTasks() {
            int size = multiplicatives.size();
            int mid = size/2;

            return List.of(
                    new MultiplyBy2Task(multiplicatives.subList(0, mid), forkJoinPool),
                    new MultiplyBy2Task(multiplicatives.subList(mid, size), forkJoinPool)
            );
        }
    }

    static class PrintAction extends RecursiveAction {

        List<String> printables;
        AtomicInteger counter;

        PrintAction(List<String> printables, AtomicInteger counter) {
            this.printables = printables;
            this.counter = counter;
        }

        @Override
        protected void compute() {
            if(printables.size() > 2) {
                List<PrintAction> actions = _createSubTasks();
                ForkJoinTask.invokeAll(actions);
            }
            else {
                printables.forEach(printable -> {
                    System.out.println(printable);
                    counter.incrementAndGet();
                });
            }
        }

        private List<PrintAction> _createSubTasks() {
            int size = printables.size();
            int mid = size/2;

            return List.of(
                    new PrintAction(printables.subList(0, mid), counter),
                    new PrintAction(printables.subList(mid, size), counter)
            );
        }
    }

    public ParallelJobProcessing() {
        forkJoinPool = new ForkJoinPool(2);
    }

    public void execute(List<String> input) {
        if(input == null || input.isEmpty())
            return;

        AtomicInteger counter = new AtomicInteger(0);
        PrintAction action = new PrintAction(input, counter);
        ForkJoinTask.invokeAll(action);
        System.out.println(counter.get());
    }

    public Integer multiplyBy2(List<Integer> input) {
        if(input == null || input.isEmpty())
            return 0;

        MultiplyBy2Task multiplyBy2Task = new MultiplyBy2Task(input, forkJoinPool);
        return forkJoinPool.invoke(multiplyBy2Task);
    }
}
