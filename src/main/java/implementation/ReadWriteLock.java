package implementation;

import java.util.concurrent.Semaphore;

public class ReadWriteLock {
    private Semaphore readSemaphore = new Semaphore(1);
    private Semaphore writeSemaphore = new Semaphore(1);
    private int count = 0;

    public void readLock() throws InterruptedException {
        readSemaphore.acquire();
                if (count == 0){
                    writeSemaphore.acquire();
                    System.out.printf("Reader %d is waiting %n",Thread.currentThread().getId());
                }
                count++;
        System.out.printf("Reader %d is reading %n",Thread.currentThread().getId());
        readSemaphore.release();

    }
    public void readUnLock() throws InterruptedException {
        readSemaphore.acquire();
        count--;
        if (count == 0){
            writeSemaphore.release();
        }
        System.out.printf("Reader %d is finished reading %n",Thread.currentThread().getId());
        readSemaphore.release();
    }

    public void writeLock() throws InterruptedException {
        writeSemaphore.acquire();
        System.out.printf("Writer %d is writing %n",Thread.currentThread().getId());
    }

    public void writeUnLock(){
        writeSemaphore.release();
        System.out.printf("Writer %d is finished writing %n",Thread.currentThread().getId());
    }
}
