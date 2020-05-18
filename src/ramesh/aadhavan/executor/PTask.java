package ramesh.aadhavan.executor;

import java.util.concurrent.*;

public class PTask {
    static class Task1 implements Callable<Integer> {
        int v;

        public Task1(int v) {
            this.v = v;
        }

        @Override
        public Integer call() throws Exception {
            return v;
        }
    }

    private static String getStringTask(String v) {
        return v;
    }

    public static void someMethod() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        Future<Integer> vInteger = executorService.submit(new Task1(123));
        Future<String> vString = executorService.submit(() -> getStringTask("hello"));

        try {
            System.out.println(vInteger.get());
            System.out.println(vString.get());
            executorService.shutdown();
        }
        catch (InterruptedException ie) {
            throw ie;
        }
        catch (ExecutionException e) {
            System.out.println("Some problem with the execution: "+e);
            executorService.shutdown();
        }
        finally {
            if(!executorService.awaitTermination(5, TimeUnit.MILLISECONDS)) {
                executorService.shutdownNow();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        someMethod();
    }
}
