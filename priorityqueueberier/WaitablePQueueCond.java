package priorityqueueberier;

import sun.awt.Mutex;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.*;


public class WaitablePQueueCond<E> {

    private final Lock lock = new ReentrantLock();
    private final Condition conditionVar  = lock.newCondition();
    private int capacity;

    private PriorityQueue<E> priorityQueue;

    public WaitablePQueueCond(int capacity) {
        priorityQueue =new PriorityQueue<>(capacity);
        this.capacity=capacity;

    }
    public WaitablePQueueCond(int capacity,Comparator<E> comperator) {
        priorityQueue = new PriorityQueue<>(capacity, comperator);
        this.capacity = capacity;
    }


    public void enqueue(E e) {
        lock.lock();
        while(priorityQueue.size() >= capacity){
            try {
                conditionVar.await();
            } catch (InterruptedException exeption) {
                System.out.println(exeption);
            }
        }
        priorityQueue.add(e);
        lock.unlock();
    }

    public E dequeue() {

        lock.lock();
        while (priorityQueue.isEmpty()) {
            try {
                conditionVar.await();
            } catch (InterruptedException e) {
                System.out.println(e);
            }
        }
        E data =  priorityQueue.poll();
        conditionVar.signal();
        lock.unlock();

        return data; //local var
    }

    public E dequeue(long timeout, TimeUnit unit) {
        return dequeue();
    }

    public boolean remove(E element) {
        lock.lock();
             boolean flag = priorityQueue.remove(element);
        lock.unlock();

            return flag;
    }

    public boolean remove(E element, long timeout, TimeUnit unit) {
        return false;
    }

    public E peek(){
            lock.lock();
                E data =  priorityQueue.peek();
            lock.unlock();
            return data;
    }

    public int size(){
        lock.lock();
          int size = priorityQueue.size();
        lock.unlock();
        return size;
    }

    public boolean isEmpty(){
        lock.lock();
            boolean flag =  priorityQueue.isEmpty();
        lock.unlock();
        return flag;
    }

}