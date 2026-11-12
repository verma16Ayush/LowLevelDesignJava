package com.avr.problems.ProducerConsumer;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import lombok.AllArgsConstructor;

public class ProducerConsumerFixedBuffer {
    private static class SharedResource {
        private final int capacity;
        private final Deque<Integer> deque;
        private final Lock lock = new ReentrantLock();
        private final Condition notEmpty = lock.newCondition();
        private final Condition notFull = lock.newCondition();
        
        SharedResource(int capacity) {
            this.capacity = capacity;
            this.deque = new ArrayDeque<>(this.capacity);
        }

        private void publish(int data) throws InterruptedException {
            lock.lock();
            try {
                while(this.deque.size() >= capacity) {
                    notFull.await();
                }
                deque.addLast(data);
                System.out.println("data: " + data + " pushed by thread: " + Thread.currentThread().getName());
                notEmpty.signal();
            } finally {
                lock.unlock();
            }
        }

        private void read() throws InterruptedException {
            lock.lock();
            try {
                while (this.deque.isEmpty()) {
                    notEmpty.await();
                }
                int dataRead = deque.pollFirst();
                System.out.println("data: " + dataRead + " readed by thread: " + Thread.currentThread().getName());
                notFull.signal();
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
        SharedResource sharedResource = new ProducerConsumerFixedBuffer.SharedResource(5);
        Thread producer1 = new Thread(new ProducerConsumerFixedBuffer.Producer(sharedResource), "prod-1");
        Thread consumer1 = new Thread(new ProducerConsumerFixedBuffer.Consumer(sharedResource), "cons-1");

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
