package pers.hanchao.datastructure.queue;

import pers.hanchao.datastructure.list.MyLinkedList;

import java.util.NoSuchElementException;

/**
 * <p>由链表自定义队列LIFO，先进后出</P>
 * 1.队列相当于一个正立的链表，队列头相当于LinkedList的头节点，队列尾相当于LinkedList的尾节点。
 * 2.add(e):带异常的入队，相当于LinkedList在头节点添加元素，= MyLinkedList.addFirst(e)
 * 3.remove():带异常的出队，相当于LinkedList在尾节点删除元素，= MyLinkedList.removeLast(size-1)
 * 4.element():带异常的获取队头元素，相当于查询LinkedList的头节点元素，= MyLinkedList.getFirst(size-1)
 * 5.offer(e):不带异常的入队，相当于LinkedList在头节点添加元素，= MyLinkedList.offerFirst(e)
 * 6.poll():不带异常的出队，相当于LinkedList在尾节点删除元素，= MyLinkedList.pollLast(size-1)
 * 7.peek():不带异常的获取队头元素，相当于查询LinkedList的头节点元素，= MyLinkedList.peekFirst(size-1)
 *
 * @author hanchao
 */
public class MyLinkedQueue<E> {
    /**
     * 列表实现Queue
     */
    private MyLinkedList<E> myLinkedList;

    /**
     * 容量有限制
     */
    private int capacity;

    public MyLinkedQueue(int capacity) {
        this.capacity = capacity;
        myLinkedList = new MyLinkedList<>();
    }

    /**
     * Inserts the specified element into this queue if it is possible to do so
     * immediately without violating capacity restrictions, returning
     * {@code true} upon success and throwing an {@code IllegalStateException}
     * if no space is currently available.
     *
     * @param o the element to add
     * @throws IllegalStateException    if the element cannot be added at this
     *                                  time due to capacity restrictions
     * @throws ClassCastException       if the class of the specified element
     *                                  prevents it from being added to this queue
     * @throws NullPointerException     if the specified element is null and
     *                                  this queue does not permit null elements
     * @throws IllegalArgumentException if some property of this element
     *                                  prevents it from being added to this queue
     */
    public boolean add(E o) {
        //入队=LinkedList头节点插入
        if (myLinkedList.size() == capacity) {
            throw new IllegalStateException("队列已满");
        }
        myLinkedList.addFirst(o);
        return true;
    }

    /**
     * Inserts the specified element into this queue if it is possible to do
     * so immediately without violating capacity restrictions.
     * When using a capacity-restricted queue, this method is generally
     * preferable to {@link #add}, which can fail to insert an element only
     * by throwing an exception.
     *
     * @param o the element to add
     * @return {@code true} if the element was added to this queue, else
     * {@code false}
     * @throws ClassCastException       if the class of the specified element
     *                                  prevents it from being added to this queue
     * @throws NullPointerException     if the specified element is null and
     *                                  this queue does not permit null elements
     * @throws IllegalArgumentException if some property of this element
     *                                  prevents it from being added to this queue
     */
    public boolean offer(E o) {
        //入队=LinkedList头节点插入
        if (myLinkedList.size() == capacity) {
            return false;
        }
        myLinkedList.offerFirst(o);
        return true;
    }

    /**
     * Retrieves and removes the head of this queue.  This method differs
     * from {@link #poll poll} only in that it throws an exception if this
     * queue is empty.
     *
     * @return the head of this queue
     * @throws NoSuchElementException if this queue is empty
     */
    public Object remove() {
        //出队=LinkedList尾结点删除
        if (myLinkedList.size() == 0) {
            throw new NoSuchElementException();
        }
        return myLinkedList.removeLast();
    }

    /**
     * Retrieves and removes the head of this queue,
     * or returns {@code null} if this queue is empty.
     *
     * @return the head of this queue, or {@code null} if this queue is empty
     */
    public Object poll() {
        //出队=LinkedList尾结点删除
        if (myLinkedList.size() == 0) {
            return null;
        }
        return myLinkedList.pollLast();
    }

    /**
     * Retrieves, but does not remove, the head of this queue.  This method
     * differs from {@link #peek peek} only in that it throws an exception
     * if this queue is empty.
     *
     * @return the head of this queue
     * @throws NoSuchElementException if this queue is empty
     */
    public Object element() {
        //查询队头=LinkedList头节点元素
        if (myLinkedList.size() == 0) {
            throw new NoSuchElementException();
        }
        return myLinkedList.element();
    }

    /**
     * Retrieves, but does not remove, the head of this queue,
     * or returns {@code null} if this queue is empty.
     *
     * @return the head of this queue, or {@code null} if this queue is empty
     */
    public Object peek() {
        //查询队头=LinkedList头节点元素
        if (myLinkedList.size() == 0) {
            return null;
        }
        return myLinkedList.peek();
    }

    public static void main(String[] args) {
        MyLinkedQueue myLinkedQueue = new MyLinkedQueue(5);
        myLinkedQueue.offer(1);
        System.out.println(myLinkedQueue.peek());
        myLinkedQueue.offer(2);
        System.out.println(myLinkedQueue.peek());
        myLinkedQueue.offer(3);
        System.out.println(myLinkedQueue.peek());

        System.out.println(myLinkedQueue.poll());
        System.out.println(myLinkedQueue.poll());
        System.out.println(myLinkedQueue.poll());
    }
}
