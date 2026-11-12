package com.avr.problems.ProducerConsumer;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import lombok.AllArgsConstructor;

public class ProducerConsumer {
    private static class SharedResource {
        private final Lock lock = new ReentrantLock();
        private final Condition isEmpty = lock.newCondition();
        private final Condition isFull = lock.newCondition();
        private int data;
        private boolean isAvailable;

        SharedResource() {
            this.data = 0;
            this.isAvailable = false;
        }

        private void publish(int val) throws InterruptedException {
            lock.lock();
            try{
                while (isAvailable) {
                    isEmpty.await();
                }
                this.data = val;
                this.isAvailable = true;
                System.out.println("data: " + this.data + " addd by thread: " + Thread.currentThread().getName());
                isFull.signal();
            } finally {
                lock.unlock();
            }
        }

        private void read() throws InterruptedException {
            lock.lock();
            try {
                    while (!isAvailable) {
                    isFull.await();
                }
                System.out.println("data: " + this.data + " read by thread: " + Thread.currentThread().getName());
                this.data = 0;
                this.isAvailable = false;
                isEmpty.signal();
            } finally {
                lock.unlock();
            }
        }
    }
    
    @AllArgsConstructor 
    private static class Producer implements Runnable {
        SharedResource sharedResource;
        
        @Override
        public void run() {
            try {
                for(int i = 0; i < 100; i++) {
                    sharedResource.publish(ThreadLocalRandom.current().nextInt(10, 100));

                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    @AllArgsConstructor 
    private static class Consumer implements Runnable {
        SharedResource sharedResource;
        @Override
        public void run() {
            try {
                for(int i = 0; i < 100; i++) {
                    sharedResource.read();
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
    public static void main(String[] args) {
        SharedResource sharedResource = new ProducerConsumer.SharedResource();
        Thread producer1 = new Thread(new ProducerConsumer.Producer(sharedResource), "prod-1");
        Thread consumer1 = new Thread(new ProducerConsumer.Consumer(sharedResource), "cons-1");

        producer1.start();
        consumer1.start();

        try {
            producer1.join();
            consumer1.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
