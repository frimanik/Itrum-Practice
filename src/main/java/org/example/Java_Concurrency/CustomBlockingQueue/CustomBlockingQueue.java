package org.example.Java_Concurrency.CustomBlockingQueue;

import java.util.LinkedList;
import java.util.Queue;

public class CustomBlockingQueue<T> {
    private final Queue<T> queue;
    private final int maxSize;

    public CustomBlockingQueue(int maxSize) {
        this.queue = new LinkedList<>();
        this.maxSize = maxSize;
    }

    public void enqueue(T element) throws InterruptedException {
        while (queue.size() == maxSize) {
            wait();
        }

        queue.add(element);
        notify();
    }

    public T dequeue() throws InterruptedException {
        while (queue.isEmpty()) {
            wait();
        }
        T element = queue.poll();
        notify();
        return element;
    }

    public int size() {
        return queue.size();
    }
}
