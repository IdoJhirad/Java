package LinkedListGen.src;

import java.util.ConcurrentModificationException;
import java.util.Iterator;

public class LinkedListGen<T> implements Iterable<T> {

    private Node<T> head = null;
    private Node<T> tail = null;
    volatile int modCount = 0;


    // public functions
    public void pushFront(T t) {
        ++modCount;
        head = new Node<>(t, head);

        if(tail == null){
            tail = head;
        }
    }
    public T popFront() {
        try{
            T data = this.head.data;
            head = head.next;
            ++modCount;
            if(head == null){
                tail = null;
            }
            return data;
        }
        catch (NullPointerException e){
            throw new LinkedListGen.src.InvalidOperation();
        }

    }
    public int size() {
        int counter = 0;
        for(T data : this){
            ++counter;
        }
        return counter;
    }
    public boolean isEmpty() {
        return this.head == null;
    }

    public Iterator<T> find(T data) {
        Iterator<T> prev = iterator();
        for (T dataToFind : this){
            if(data.equals(dataToFind)){
                return prev;
            }
            prev.next();
        }
        return null;
    }
    public static <T> LinkedListGen<T> newReverse(LinkedListGen<T> list) {

        LinkedListGen<T> newList = new LinkedListGen<>();
        for (T data : list){
            newList.pushFront(data);
        }
        list.head = null;
        list.tail = null;

        return newList;
    }

    //removes all nodes from both lists and returns a new one
    public static <T> LinkedListGen<T> mergeLists(LinkedListGen<T> list1, LinkedListGen<T> list2) {
        list1.tail.next = list2.head;

        LinkedListGen<T> new_list = new LinkedListGen<>();
        new_list.head = list1.head;
        new_list.tail = list2.tail;

        list1.head = null;
        list2.head = null;
        list1.tail = null;
        list2.tail = null;

        return new_list;
    }

    public static <T> void printList(LinkedListGen<T> list) {
        for(T data : list){
            System.out.print(data+" ");
        }
        System.out.println();
    }
    @Override
    public Iterator<T> iterator() {
        return (Iterator<T>) new ListIterator<>(head);
    }

    // nested Node<T> class:
    private static class Node<E> {
        E data;
        Node<E> next;

        //constructor
        public Node(E t, Node<E> next) {
            this.data = t;
            this.next = next;
        }
    }
    // nested ListIterator<T> class:
    private class ListIterator<T> implements Iterator<T> {

        private Node<T> node;
        private final int lastModCount;

        //constructor:
        public ListIterator(Node<T> node) {
            this.node = node;
            this.lastModCount = modCount;
        }

        @Override
        public T next() {
            if(modCount!= lastModCount){
                throw new ConcurrentModificationException("list has changed");
            }
            T data = node.data;
            node = node.next;
            return data;
        }
        @Override
        public boolean hasNext() {
            if(modCount!= lastModCount){
                throw new ConcurrentModificationException("list has changed");
            }
            return this.node != null;
        }
    }
}
