package questions;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReaderWriterLock {

    private final Semaphore readerLock;
    private final Semaphore writerLock = new Semaphore(1);
    private final ReentrantLock writerWaitingLock = new ReentrantLock();
    private final Condition writerWaitingCond = writerWaitingLock.newCondition();
    private final AtomicBoolean writerWaiting = new AtomicBoolean(false);
    private final ReadWriteLock lock = new ReentrantReadWriteLock();


    private final AtomicInteger currentReaders = new AtomicInteger(0);

    public ReaderWriterLock(final int NUM_READERS) {
        this.readerLock = new Semaphore(NUM_READERS);
    }

    public void getReadLock() throws InterruptedException {
        writerWaitingLock.lock();
        while(writerWaiting.get())
            writerWaitingCond.await();
        writerWaitingLock.unlock();

        readerLock.acquire();
        if(currentReaders.incrementAndGet() == 1)
            writerLock.acquire();
    }

    public void releaseReadLock() {
        if(currentReaders.decrementAndGet() == 0)
            writerLock.release();
        readerLock.release();
    }

    public void getWriteLock() throws InterruptedException {
        writerWaitingLock.lock();
        while(!writerWaiting.compareAndSet(false, true))
            writerWaitingCond.await();
        writerWaitingLock.unlock();

        writerLock.acquire();
    }

    public void releaseWriteLock() {
        writerWaitingLock.lock();
        writerWaiting.set(false);
        writerWaitingCond.signalAll();
        writerWaitingLock.unlock();

        writerLock.release();
    }
}
