package application;




import implementation.Philosopher;
import implementation.ReadWriteLock;
import org.riversun.promise.Func;
import org.riversun.promise.Promise;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class ConcurrentProgramming {

    public static void main(String[] args) {
//      zad.1
        ReadWriteLock readWriteLock = new ReadWriteLock();
//        Thread t1 = new Thread(() -> {
//            try {
//                readWriteLock.readLock();
//                Thread.sleep(2000);
//                readWriteLock.readUnLock();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        });
//        Thread t2 = new Thread(() -> {
//            try {
//                readWriteLock.readLock();
//                Thread.sleep(2000);
//                readWriteLock.readUnLock();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        });
//        Thread t3 = new Thread(() -> {
//            try {
//                readWriteLock.readLock();
//                Thread.sleep(2000);
//                readWriteLock.readUnLock();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        });
//        Thread t4 = new Thread(() -> {
//            try {
//                readWriteLock.writeLock();
//                Thread.sleep(2000);
//                readWriteLock.writeUnLock();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        });
//        Thread t5 = new Thread(() -> {
//            try {
//                readWriteLock.writeLock();
//                Thread.sleep(2000);
//                readWriteLock.writeUnLock();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        });
//        t4.start();
//        t5.start();
//        t1.start();
//        t2.start();
//        t3.start();

//        zad.2
//        int numberPhilosophers = 5;
//        Philosopher philosophers[] = new Philosopher[numberPhilosophers];
//        Semaphore forks[] = new Semaphore[numberPhilosophers];
//        ExecutorService executorService = Executors.newFixedThreadPool(5);
//        for (int i = 0; i < numberPhilosophers; i++){
//            forks[i] = new Semaphore(1);
//        }
//
//        for (int i = 0; i < numberPhilosophers; i++){
//            philosophers[i] = new Philosopher(i,forks[i],forks[(i + 1)%numberPhilosophers]);
//            executorService.execute(philosophers[i]);
//        }
//        executorService.shutdown();
//      zad.3
        Promise.all(promiseWriter(readWriteLock),promiseReader(readWriteLock),promiseWriter(readWriteLock),promiseReader(readWriteLock),promiseReader(readWriteLock),promiseWriter(readWriteLock)).start();








    }
        public static Func promiseReader(ReadWriteLock readWriteLock){
            Func func1 = (action, data) -> {
                readWriteLock.readLock();
                Promise.sleep(2000);
                readWriteLock.readUnLock();
                action.resolve();
            };
            return func1;
        }
        public static Func promiseWriter(ReadWriteLock readWriteLock){
        Func func2 = (action, data) -> {
            readWriteLock.writeLock();
            Promise.sleep(1000);
            readWriteLock.writeUnLock();
            action.resolve();
        };
        return func2;
        }
}
