package pers.hanchao.datastructure.queue;

import pers.hanchao.datastructure.list.MyArrayList;

import java.util.NoSuchElementException;

/**
 * <p>有数组自定义队列LIFO，先进后出</P>
 * 1.队列相当于一个倒立的数组，队列头相当于ArrayList的尾部，队列尾相当于ArrayList的头部。
 * 2.add(e):带异常的入队，相当于ArrayList在尾部添加元素，= MyArrayList.add(e)
 * 3.remove():带异常的出队，相当于ArrayList在尾部删除元素，= MyArrayList.remove(size-1)
 * 4.element():带异常的获取队头元素，相当于查询ArrayList的尾部元素，= MyArrayList.get(size-1)
 * 5.offer(e):不带异常的入队，相当于ArrayList在尾部添加元素，= MyArrayList.add(e)
 * 6.poll():不带异常的出队，相当于ArrayList在尾部删除元素，= MyArrayList.remove(size-1)
 * 7.peek():不带异常的获取队头元素，相当于查询ArrayList的尾部元素，= MyArrayList.get(size-1)
 *
 * @author hanchao
 */
public class MyArrayQueue<E> {
    /**
     * 数组
     */
    private MyArrayList<E> myArrayList;

    /**
     * 容量有限制
     */
    private int capacity;

    public MyArrayQueue(int capacity) {
        this.capacity = capacity;
        myArrayList = new MyArrayList<>(capacity);
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
        //入队=ArrayList尾部插入
        if (myArrayList.size() == capacity) {
            throw new IllegalStateException("队列已满");
        }
        myArrayList.add(o);
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
        //入队=ArrayList尾部插入
        if (myArrayList.size() == capacity) {
            return false;
        }
        return myArrayList.add(o);
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
        //出队=ArrayList头部删除
        if (myArrayList.isEmpty()) {
            throw new NoSuchElementException();
        }
        E e = myArrayList.get(0);
        myArrayList.remove(0);
        return e;
    }

    /**
     * Retrieves and removes the head of this queue,
     * or returns {@code null} if this queue is empty.
     *
     * @return the head of this queue, or {@code null} if this queue is empty
     */
    public Object poll() {
        //出队=ArrayList头部删除
        if (myArrayList.isEmpty()) {
            return null;
        }
        E e = myArrayList.get(0);
        myArrayList.remove(0);
        return e;
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
        //查询队头=ArrayList尾部元素
        if (myArrayList.isEmpty()) {
            throw new NoSuchElementException();
        }
        return myArrayList.get(myArrayList.size() - 1);
    }

    /**
     * Retrieves, but does not remove, the head of this queue,
     * or returns {@code null} if this queue is empty.
     *
     * @return the head of this queue, or {@code null} if this queue is empty
     */
    public Object peek() {
        //查询队头=ArrayList尾部元素
        if (myArrayList.isEmpty()) {
            return null;
        }
        return myArrayList.get(myArrayList.size() - 1);
    }

    public static void main(String[] args) {
        MyArrayQueue myArrayQueue = new MyArrayQueue(5);
        myArrayQueue.offer(1);
        System.out.println(myArrayQueue.peek());
        myArrayQueue.offer(2);
        System.out.println(myArrayQueue.peek());
        myArrayQueue.offer(3);
        System.out.println(myArrayQueue.peek());

        System.out.println(myArrayQueue.poll());
        System.out.println(myArrayQueue.poll());
        System.out.println(myArrayQueue.poll());
    }
}
