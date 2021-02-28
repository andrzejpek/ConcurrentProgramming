package implementation;


import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadLocalRandom;

public class Philosopher extends Thread implements Runnable{
    private int number;
    private Semaphore leftFork;
    private Semaphore rightFork;

    public Philosopher(int number, Semaphore leftFork, Semaphore rightFork){
        this.number = number;
        this.leftFork = leftFork;
        this.rightFork = rightFork;
    }

    public void run(){

        while (true){
            think();
            pickUpLeftFork();
            pickUpRightFork();
            eat();
            putDownFork();
        }
    }
    private void eat(){
        try {
            int sleep = ThreadLocalRandom.current().nextInt(0,1000);
            System.out.printf("Philosopher %d is eating %n",number);
            Thread.sleep(sleep);
        }catch (Exception e){
            System.out.printf("Exception %s",e);
        }
    }
    private void think(){
        try{
            int sleep = ThreadLocalRandom.current().nextInt(0,1000);
            System.out.printf("Philosopher %d is thinking.%n",number);
            Thread.sleep(sleep);
        }catch (Exception e){
            System.out.printf("Exception %s",e);
        }
    }
    private void pickUpLeftFork(){
        try{
            if (leftFork.availablePermits() == 0){
                System.out.printf("Philosopher %d is waiting for left fork %n",number);
            }
            leftFork.acquire();
            System.out.printf("Philosopher %d is holding left fork %n",number);
        }catch (Exception e){
            System.out.printf("Exception %s",e);
        }
    }
    private void pickUpRightFork(){
        try{
            if (rightFork.availablePermits() == 0){
                System.out.printf("Philosopher %d is waiting for right fork %n",number);
            }
            rightFork.acquire();
            System.out.printf("Philosopher %d is holding right fork %n",number);
        }catch (Exception e){
            System.out.printf("Exception %s",e);
        }
    }
    private void putDownFork(){
        leftFork.release();
        rightFork.release();
    }


}
