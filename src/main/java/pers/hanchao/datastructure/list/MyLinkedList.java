package pers.hanchao.datastructure.list;

import java.util.*;

/**
 * <p>有链表实现List</P>
 * 1.size是实际元素个数，链表没有capacity。
 * 2.新增元素时，无需考虑数组越界问题，但是需要考虑当前节点是否为first或者last节点。
 * 3.删除元素时，需要考虑删除之后，需要将原节点的prev、next和data置为null，利于GC。
 * 4.first节点的prev为null，last的节点的next为null，单节点链表的prev和next都未null。
 * 5.getFirst()、getLast():获取头尾节点元素，不存在则抛出NoSuchElementException；复杂度O(1)
 * 6.removeFirst()、removeLast():移除头尾节点元素，不存在则抛出NoSuchElementException；复杂度O(1)
 * 7.unlinkFirst()、unlinkLast()、unlink()、link(e)：私有方法；移除头、尾和中间节点，增加节点；前提：节点存在；复杂度O(1)
 * 8.addFirst(e)、addLast(e):在头部、尾部新增节点元素；复杂度O(1)
 * 9.add(e): =addLast()；复杂度O(1)
 * 10.add(index,e):确保index <= size；遍历寻找index节点；index元素后移；指定index添加元素； size++；复杂度O(n)
 * 11.set(index,e)：确保index <= size；遍历寻找index节点；指定index覆盖元素；复杂度O(n)
 * 12.remove(e)：遍历寻找节点；removeFirst();复杂度O(n)
 * 13.remove(index):确保index <= size；遍历寻找index节点；删除节点；复杂度O(n)
 * 14.get(index): 确保index <= size；遍历寻找index节点；return elementData[index]; 复杂度O(n)
 * 15.clear(): 遍历情况节点; size=0；复杂度O(n)
 * 16.peek()=peekFirst(),peekLast():适用于队列；peek=偷看,所以不删元素；获取首、尾元素，不删除，不存在返回null；复杂度O(1)
 * 17.offer()=offerFirst(),offerLast():适用于队列；offer=献上；在首尾添加元素；复杂度O(1)
 * 18.poll()=pollFirst(),pollLast:使用与队列；poll=获得，所以删元素；获取首尾元素，删除，不存在返回null；复杂度O(1)
 * 19.push(e),pop(e):适用于栈；在头节点入栈出栈元素，相当于addFirst(e)和removeFirst(e)，不存在则抛出NoSuchElementException；复杂度O(1)；
 * 20.element()：获取头节点元素，不删除，不存在则抛出NoSuchElementException；复杂度O(1)；
 * 21.remove(): 删除头节点，不存在则抛出NoSuchElementException；复杂度O(1)；
 *
 * @author hanchao
 */
public class MyLinkedList<E> {

    /**
     * 列表节点
     */
    private class Node<E> {
        E data;
        Node<E> prev;
        Node<E> next;

        Node(E data, Node<E> prev, Node<E> next) {
            this.data = data;
            this.prev = prev;
            this.next = next;
        }
    }

    /**
     * 头结点
     */
    private Node<E> first;
    /**
     * 尾结点
     */
    private Node<E> last;
    /**
     * 节点个数
     */
    private int size = 0;

    public MyLinkedList() {
    }


    /**
     * Constructs a list containing the elements of the specified
     * collection, in the order they are returned by the collection's
     * iterator.
     *
     * @param c the collection whose elements are to be placed into this list
     * @throws NullPointerException if the specified collection is null
     */
    public MyLinkedList(Collection<? extends E> c) {
        addAll(c);
    }

    /**
     * Returns the first element in this list.
     *
     * @return the first element in this list
     * @throws NoSuchElementException if this list is empty
     */
    public E getFirst() {
        if (Objects.isNull(first)) {
            throw new NoSuchElementException();
        }
        return first.data;
    }

    /**
     * Returns the last element in this list.
     *
     * @return the last element in this list
     * @throws NoSuchElementException if this list is empty
     */
    public E getLast() {
        if (Objects.isNull(last)) {
            throw new NoSuchElementException();
        }
        return last.data;
    }

    /**
     * Removes and returns the first element from this list.
     *
     * @return the first element from this list
     * @throws NoSuchElementException if this list is empty
     */
    public E removeFirst() {
        if (Objects.isNull(first)) {
            throw new NoSuchElementException();
        } else {
            return unlinkFirst();
        }
    }

    /**
     * 移除头结点(头结点存在)
     */
    private E unlinkFirst() {
        //1.获取头元素的值
        E firstData = first.data;
        //2.下个节点
        Node<E> nextNode = first.next;
        //3.清除原头节点
        first.next = null;
        first.data = null;
        //4.头结点后移
        first = nextNode;
        //5.新的头结点的上个节点置为空
        if (Objects.nonNull(first)) {
            first.prev = null;
        } else {
            //头结点为null，则头尾相交
            last = null;
        }
        //6.size--
        size--;
        return firstData;
    }

    /**
     * Removes and returns the last element from this list.
     *
     * @return the last element from this list
     * @throws NoSuchElementException if this list is empty
     */
    public E removeLast() {
        if (null == last) {
            throw new NoSuchElementException();
        }
        return unlinkLast();

    }

    /**
     * 移除为节点(尾结点存在)
     */
    private E unlinkLast() {
        //1.获取原尾结点数据
        E data = last.data;
        //2.获取尾节点上个元素
        Node<E> prevNode = last.prev;
        //3.清除原尾结点
        last.prev = null;
        last.data = null;
        //4.尾结点更新
        last = prevNode;
        //5.新尾结点处理
        if (null == last) {
            //头尾相交
            first = null;
        } else {
            last.next = null;
        }
        //6.size--
        size--;
        return data;
    }

    /**
     * Inserts the specified element at the beginning of this list.
     *
     * @param e the element to add
     */
    public void addFirst(E e) {
        if (first == null) {
            last = first = new Node<>(e, null, null);
        }
        //1.原头结点
        Node<E> oldFirst = first;
        //2.新头结点
        first = new Node<>(e, null, first);
        //3.旧头结点处理
        oldFirst.prev = first;
        //4.size ++
        size++;
    }

    /**
     * Appends the specified element to the end of this list.
     *
     * <p>This method is equivalent to {@link #add}.
     *
     * @param e the element to add
     */
    public void addLast(E e) {
        if (last == null) {
            last = first = new Node<>(e, null, null);
        }
        //1.原尾结点
        Node<E> oldLast = last;
        //2.新尾结点
        last = new Node<>(e, oldLast, null);
        //3.旧尾结点处理
        oldLast.next = last;
        //4.size++
        size++;
    }

    /**
     * Returns {@code true} if this list contains the specified element.
     * More formally, returns {@code true} if and only if this list contains
     * at least one element {@code e} such that
     * <tt>(o==null&nbsp;?&nbsp;e==null&nbsp;:&nbsp;o.equals(e))</tt>.
     *
     * @param o element whose presence in this list is to be tested
     * @return {@code true} if this list contains the specified element
     */
    public boolean contains(Object o) {
        return indexOf(o) != -1;
    }

    /**
     * Returns the number of elements in this list.
     *
     * @return the number of elements in this list
     */
    public int size() {
        return size;
    }

    /**
     * Appends the specified element to the end of this list.
     *
     * <p>This method is equivalent to {@link #addLast}.
     *
     * @param e element to be appended to this list
     * @return {@code true} (as specified by {@link Collection#add})
     */
    public boolean add(E e) {
        addLast(e);
        return true;
    }

    /**
     * Removes the first occurrence of the specified element from this list,
     * if it is present.  If this list does not contain the element, it is
     * unchanged.  More formally, removes the element with the lowest index
     * {@code i} such that
     * <tt>(o==null&nbsp;?&nbsp;get(i)==null&nbsp;:&nbsp;o.equals(get(i)))</tt>
     * (if such an element exists).  Returns {@code true} if this list
     * contained the specified element (or equivalently, if this list
     * changed as a result of the call).
     *
     * @param o element to be removed from this list, if present
     * @return {@code true} if this list contained the specified element
     */
    public boolean remove(Object o) {
        if (null == o) {
            for (Node<E> node = first; node != null; node = node.next) {
                if (null == node.data) {
                    unlink(node);
                    return true;
                }
            }
        } else {
            for (Node<E> node = first; node != null; node = node.next) {
                if (o.equals(node.data)) {
                    unlink(node);
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 移除某个节点(此节点存在)
     */
    private E unlink(Node<E> node) {
        E element = node.data;
        node.data = null;

        Node<E> prevNode = node.prev;
        Node<E> nextNode = node.next;
        if (prevNode != null) {
            //前置节点不为空，则设置其后置节点
            prevNode.next = nextNode;
            //help gc
            node.prev = null;
        } else {
            //前置节点为空，则当前节点为头节点
            removeFirst();
        }

        if (nextNode != null) {
            //后置节点不为空，则设置其前直接节点
            nextNode.prev = prevNode;
            //help gc
            node.next = null;
        } else {
            //后置节点为空，则当前节点为last节点
            removeLast();
        }

        size--;
        return element;
    }

    /**
     * Appends all of the elements in the specified collection to the end of
     * this list, in the order that they are returned by the specified
     * collection's iterator.  The behavior of this operation is undefined if
     * the specified collection is modified while the operation is in
     * progress.  (Note that this will occur if the specified collection is
     * this list, and it's nonempty.)
     *
     * @param c collection containing elements to be added to this list
     * @return {@code true} if this list changed as a result of the call
     * @throws NullPointerException if the specified collection is null
     */
    public boolean addAll(Collection<? extends E> c) {
        for (E e : c) {
            addFirst(e);
        }
        return true;
    }

    /**
     * Inserts all of the elements in the specified collection into this
     * list, starting at the specified position.  Shifts the element
     * currently at that position (if any) and any subsequent elements to
     * the right (increases their indices).  The new elements will appear
     * in the list in the order that they are returned by the
     * specified collection's iterator.
     *
     * @param index index at which to insert the first element
     *              from the specified collection
     * @param c     collection containing elements to be added to this list
     * @return {@code true} if this list changed as a result of the call
     * @throws IndexOutOfBoundsException {@inheritDoc}
     * @throws NullPointerException      if the specified collection is null
     */
    public boolean addAll(int index, Collection<? extends E> c) {
        for (E e : c) {
            add(index, e);
        }
        return true;
    }

    /**
     * Removes all of the elements from this list.
     * The list will be empty after this call returns.
     */
    public void clear() {
        for (Node<E> node = first; node != null; ) {
            Node<E> next = node.next;
            node.data = null;
            node.prev = null;
            node.next = null;
            node = next;
        }
        first = last = null;
        size = 0;
    }

    /**
     * Returns the element at the specified position in this list.
     *
     * @param index index of the element to return
     * @return the element at the specified position in this list
     * @throws IndexOutOfBoundsException {@inheritDoc}
     */
    public E get(int index) {
        if (index >= size) {
            throw new IndexOutOfBoundsException();
        }
        Node<E> node = first;
        //移动index个节点
        while (index > 0) {
            node = node.next;
            index--;
        }
        //获取数据
        return node.data;
    }

    /**
     * Replaces the element at the specified position in this list with the
     * specified element.
     *
     * @param index   index of the element to replace
     * @param element element to be stored at the specified position
     * @return the element previously at the specified position
     * @throws IndexOutOfBoundsException {@inheritDoc}
     */
    public E set(int index, E element) {
        if (index >= size) {
            throw new IndexOutOfBoundsException();
        }
        Node<E> node = first;
        //移动index个节点
        while (index > 0) {
            node = node.next;
            index--;
        }
        //原始数据
        E data = node.data;
        //替换数据
        node.data = element;
        return data;
    }

    /**
     * Inserts the specified element at the specified position in this list.
     * Shifts the element currently at that position (if any) and any
     * subsequent elements to the right (adds one to their indices).
     *
     * @param index   index at which the specified element is to be inserted
     * @param element element to be inserted
     * @throws IndexOutOfBoundsException {@inheritDoc}
     */
    public void add(int index, E element) {
        if (index >= size) {
            throw new IndexOutOfBoundsException();
        }
        Node<E> node = first;
        //移动index个节点
        while (index > 0) {
            node = node.next;
            index--;
        }
        //当前节点的前后节点
        Node<E> prevNode = node.prev;
        Node<E> nextNode = node.next;
        //新增节点-前节点处理
        if (prevNode == null) {
            //如果前节点为空，则表示当前节点为头节点
            addFirst(element);
        } else {
            //如果前节点不为空，则设置指针
            node.prev = prevNode;
            prevNode.next = node;
        }
        //新增节点-后节点处理
        if (nextNode == null) {
            ////如果后节点为空，则表示当前节点为尾节点
            addLast(element);
        } else {
            //如果后节点不为空，则设置指针
            node.next = nextNode;
            nextNode.prev = node;
        }

        size++;
    }

    /**
     * Removes the element at the specified position in this list.  Shifts any
     * subsequent elements to the left (subtracts one from their indices).
     * Returns the element that was removed from the list.
     *
     * @param index the index of the element to be removed
     * @return the element previously at the specified position
     * @throws IndexOutOfBoundsException {@inheritDoc}
     */
    public E remove(int index) {
        if (index >= size) {
            throw new IndexOutOfBoundsException();
        }
        Node<E> node = first;
        //移动index个节点
        while (index > 0) {
            node = node.next;
            index--;
        }
        return unlink(node);
    }

    /**
     * Returns the index of the first occurrence of the specified element
     * in this list, or -1 if this list does not contain the element.
     * More formally, returns the lowest index {@code i} such that
     * <tt>(o==null&nbsp;?&nbsp;get(i)==null&nbsp;:&nbsp;o.equals(get(i)))</tt>,
     * or -1 if there is no such index.
     *
     * @param o element to search for
     * @return the index of the first occurrence of the specified element in
     * this list, or -1 if this list does not contain the element
     */
    public int indexOf(Object o) {
        int index = -1;
        if (o == null) {
            for (Node<E> node = first; node != null; node = node.next) {
                if (null == node.data) {
                    break;
                }
                index++;
            }
        } else {
            for (Node<E> node = first; node != null; node = node.next) {
                if (o.equals(node.data)) {
                    break;
                }
                index++;
            }
        }

        return index;
    }

    /**
     * Returns the index of the last occurrence of the specified element
     * in this list, or -1 if this list does not contain the element.
     * More formally, returns the highest index {@code i} such that
     * <tt>(o==null&nbsp;?&nbsp;get(i)==null&nbsp;:&nbsp;o.equals(get(i)))</tt>,
     * or -1 if there is no such index.
     *
     * @param o element to search for
     * @return the index of the last occurrence of the specified element in
     * this list, or -1 if this list does not contain the element
     */
    public int lastIndexOf(Object o) {
        int index = -1;
        if (null == o) {
            for (Node<E> node = last; node != null; node = last.prev) {
                if (node.data == null) {
                    break;
                }
                index++;
            }
        } else {
            for (Node<E> node = last; node != null; node = last.prev) {
                if (o.equals(node.data)) {
                    break;
                }
                index++;
            }
        }
        return index;
    }

    /**
     * Retrieves, but does not remove, the head (first element) of this list.
     *
     * @return the head of this list, or {@code null} if this list is empty
     * @since 1.5
     */
    public E peek() {
        return Objects.nonNull(first) ? first.data : null;
    }

    /**
     * Retrieves, but does not remove, the head (first element) of this list.
     *
     * @return the head of this list
     * @throws NoSuchElementException if this list is empty
     * @since 1.5
     */
    public E element() {
        return getFirst();
    }

    /**
     * Retrieves and removes the head (first element) of this list.
     *
     * @return the head of this list, or {@code null} if this list is empty
     * @since 1.5
     */
    public E poll() {
        E first = peek();
        unlinkFirst();
        return first;
    }

    /**
     * Retrieves and removes the head (first element) of this list.
     *
     * @return the head of this list
     * @throws NoSuchElementException if this list is empty
     * @since 1.5
     */
    public E remove() {
        E first = peek();
        removeFirst();
        return first;
    }

    /**
     * Adds the specified element as the tail (last element) of this list.
     *
     * @param e the element to add
     * @return {@code true} (as specified by {@link Queue#offer})
     * @since 1.5
     */
    public boolean offer(E e) {
        addLast(e);
        return true;
    }

    /**
     * Inserts the specified element at the front of this list.
     *
     * @param e the element to insert
     * @return {@code true} (as specified by {@link Deque#offerFirst})
     * @since 1.6
     */
    public boolean offerFirst(E e) {
        addFirst(e);
        return true;
    }

    /**
     * Inserts the specified element at the end of this list.
     *
     * @param e the element to insert
     * @return {@code true} (as specified by {@link Deque#offerLast})
     * @since 1.6
     */
    public boolean offerLast(E e) {
        addLast(e);
        return true;
    }

    /**
     * Retrieves, but does not remove, the first element of this list,
     * or returns {@code null} if this list is empty.
     *
     * @return the first element of this list, or {@code null}
     * if this list is empty
     * @since 1.6
     */
    public E peekFirst() {
        return peek();
    }

    /**
     * Retrieves, but does not remove, the last element of this list,
     * or returns {@code null} if this list is empty.
     *
     * @return the last element of this list, or {@code null}
     * if this list is empty
     * @since 1.6
     */
    public E peekLast() {
        return Objects.nonNull(last) ? last.data : null;
    }

    /**
     * Retrieves and removes the first element of this list,
     * or returns {@code null} if this list is empty.
     *
     * @return the first element of this list, or {@code null} if
     * this list is empty
     * @since 1.6
     */
    public E pollFirst() {
        return poll();
    }

    /**
     * Retrieves and removes the last element of this list,
     * or returns {@code null} if this list is empty.
     *
     * @return the last element of this list, or {@code null} if
     * this list is empty
     * @since 1.6
     */
    public E pollLast() {
        E last = peekLast();
        unlinkLast();
        return last;
    }

    /**
     * Pushes an element onto the stack represented by this list.  In other
     * words, inserts the element at the front of this list.
     *
     * <p>This method is equivalent to {@link #addFirst}.
     *
     * @param e the element to push
     * @since 1.6
     */
    public void push(E e) {
        addFirst(e);
    }

    /**
     * Pops an element from the stack represented by this list.  In other
     * words, removes and returns the first element of this list.
     *
     * <p>This method is equivalent to {@link #removeFirst()}.
     *
     * @return the element at the front of this list (which is the top
     * of the stack represented by this list)
     * @throws NoSuchElementException if this list is empty
     * @since 1.6
     */
    public E pop() {
        return removeFirst();
    }

    /**
     * Returns an array containing all of the elements in this list
     * in proper sequence (from first to last element).
     *
     * <p>The returned array will be "safe" in that no references to it are
     * maintained by this list.  (In other words, this method must allocate
     * a new array).  The caller is thus free to modify the returned array.
     *
     * <p>This method acts as bridge between array-based and collection-based
     * APIs.
     *
     * @return an array containing all of the elements in this list
     * in proper sequence
     */
    public Object[] toArray() {
        Object[] array = new Object[size];
        Node<E> node = first;
        for (int i = 0; i < size; i++) {
            array[i] = node;
            node = node.next;
        }
        return array;
    }
}
