package ramesh.aadhavan.executor;

import java.util.concurrent.*;

public class ProducerConsumer {
    static class Producer implements Runnable {
        BlockingQueue<String> blockingQueue;
        int id;

        public Producer(BlockingQueue<String> blockingQueue, int id) {
            this.blockingQueue = blockingQueue;
            this.id = id;
        }

        @Override
        public void run() {
            try {
                String message = "Producer-"+id+"-%d";
                for (int i = 0; i < 100; i++) {
                    blockingQueue.put(String.format(message, i));
                }
            }
            catch (InterruptedException ignore) { }
        }
    }

    static class Consumer implements Runnable {
        BlockingQueue<String> blockingQueue;
        int id;

        public Consumer(BlockingQueue<String> blockingQueue, int id) {
            this.blockingQueue = blockingQueue;
            this.id = id;
        }

        @Override
        public void run() {
            try {
                String format = "Consumer-%d: %s";
                while(true) {
                    String message = blockingQueue.poll(10, TimeUnit.MILLISECONDS);
                    System.out.println(String.format(format, id, message));
                    if(blockingQueue.isEmpty())
                        break;
                }
            }
            catch (InterruptedException e) { }
        }
    }

    public static void main(String[] args) {
        BlockingQueue<String> queue = new LinkedBlockingQueue<>(100);
        Producer producer = new Producer(queue, 1);
        Producer producer2 = new Producer(queue, 2);
        Consumer consumer = new Consumer(queue, 100);
        Consumer consumer2 = new Consumer(queue, 200);

        ExecutorService executorService = Executors.newFixedThreadPool(4);
        executorService.submit(consumer);
        executorService.submit(consumer2);
        executorService.submit(producer);
        executorService.submit(producer2);
    }
}
