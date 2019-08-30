package pers.hanchao.datastructure.list;

import java.util.*;

/**
 * <p>用数组实现列表</P>
 * 1.size是实际元素个数，capacity是elementDate的定义长度，有可能size lt capacity，但是不可能size > capacity
 * 2.新增元素时，务必通过ensureCapacity保证elementDate的长度足够，避免数组越界
 * 3.删除元素时，无需考虑elementDate的长度问题
 * 4.add(e)：末尾添加元素，size+1; 复杂度O(1)
 * 5.add(index,e)：确保index <= size；index及之后的元素后移；指定index添加元素； size++；复杂度O(n)
 * 6.set(index,e)：确保index <= size；指定index覆盖元素；；复杂度O(1)
 * 7.remove(index): 确保index <= size；index+1及之后的元素前移；末尾元素置为null；size--；复杂度O(n)
 * 8.remove(e)：确保index <= size；index = indexOf(e); index+1及之后的元素前移；末尾元素置为null；size--；复杂度O(n)
 * 9.get(index): 确保index <= size；return elementData[index]; 复杂度O(1)
 * 10.clear(): for{elementData[i] = null}; size=0；复杂度O(n)
 * 11.Object[] Arrays.copyOf(srcArray, copyLength); 返回一个拷贝自srcArray数组的长度为copyLength的数组
 * 12.System.arraycopy(srcArray, srcArrayStartIndex, toArray, toArrayStartIndex, srcCopyLength); 从srcArray的第srcArrayStartIndex开始，拷贝至toArray数组的toArrayStartIndex，共计拷贝srcCopyLength个元素
 *
 * @author hanchao
 */
public class MyArrayList<E> implements List<E> {
    /**
     * 用数组存储元素
     */
    private Object[] elementDate;

    /**
     * 实际元素个数
     */
    private int size;

    /**
     * 默认构造函数
     */
    public MyArrayList() {
        this.elementDate = new Object[]{};
        this.size = 0;
    }

    /**
     * 容量构造函数
     */
    public MyArrayList(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("The capacity must bigger than 0 !");
        }
        this.elementDate = new Object[capacity];
        this.size = 0;
    }

    /**
     * Returns the number of elements in this list.  If this list contains
     * more than <tt>Integer.MAX_VALUE</tt> elements, returns
     * <tt>Integer.MAX_VALUE</tt>.
     *
     * @return the number of elements in this list
     */
    @Override
    public int size() {
        return this.size;
    }

    /**
     * Returns <tt>true</tt> if this list contains no elements.
     *
     * @return <tt>true</tt> if this list contains no elements
     */
    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    /**
     * Returns <tt>true</tt> if this list contains the specified element.
     * More formally, returns <tt>true</tt> if and only if this list contains
     * at least one element <tt>e</tt> such that
     * <tt>(o==null&nbsp;?&nbsp;e==null&nbsp;:&nbsp;o.equals(e))</tt>.
     *
     * @param o element whose presence in this list is to be tested
     * @return <tt>true</tt> if this list contains the specified element
     * @throws ClassCastException   if the type of the specified element
     *                              is incompatible with this list
     *                              (<a href="Collection.html#optional-restrictions">optional</a>)
     * @throws NullPointerException if the specified element is null and this
     *                              list does not permit null elements
     *                              (<a href="Collection.html#optional-restrictions">optional</a>)
     */
    @Override
    public boolean contains(Object o) {
        //获取index
        return Objects.nonNull(get(indexOf(o)));
    }

    /**
     * Returns an iterator over the elements in this list in proper sequence.
     *
     * @return an iterator over the elements in this list in proper sequence
     */
    @Override
    public Iterator<E> iterator() {
        return null;
    }

    /**
     * Returns an array containing all of the elements in this list in proper
     * sequence (from first to last element).
     *
     * <p>The returned array will be "safe" in that no references to it are
     * maintained by this list.  (In other words, this method must
     * allocate a new array even if this list is backed by an array).
     * The caller is thus free to modify the returned array.
     *
     * <p>This method acts as bridge between array-based and collection-based
     * APIs.
     *
     * @return an array containing all of the elements in this list in proper
     * sequence
     * @see Arrays#asList(Object[])
     */
    @Override
    public Object[] toArray() {
        return Arrays.copyOf(elementDate, size);
    }

    /**
     * Returns an array containing all of the elements in this list in
     * proper sequence (from first to last element); the runtime type of
     * the returned array is that of the specified array.  If the list fits
     * in the specified array, it is returned therein.  Otherwise, a new
     * array is allocated with the runtime type of the specified array and
     * the size of this list.
     *
     * <p>If the list fits in the specified array with room to spare (i.e.,
     * the array has more elements than the list), the element in the array
     * immediately following the end of the list is set to <tt>null</tt>.
     * (This is useful in determining the length of the list <i>only</i> if
     * the caller knows that the list does not contain any null elements.)
     *
     * <p>Like the {@link #toArray()} method, this method acts as bridge between
     * array-based and collection-based APIs.  Further, this method allows
     * precise control over the runtime type of the output array, and may,
     * under certain circumstances, be used to save allocation costs.
     *
     * <p>Suppose <tt>x</tt> is a list known to contain only strings.
     * The following code can be used to dump the list into a newly
     * allocated array of <tt>String</tt>:
     *
     * <pre>{@code
     *     String[] y = x.toArray(new String[0]);
     * }</pre>
     * <p>
     * Note that <tt>toArray(new Object[0])</tt> is identical in function to
     * <tt>toArray()</tt>.
     *
     * @param a the array into which the elements of this list are to
     *          be stored, if it is big enough; otherwise, a new array of the
     *          same runtime type is allocated for this purpose.
     * @return an array containing the elements of this list
     * @throws ArrayStoreException  if the runtime type of the specified array
     *                              is not a supertype of the runtime type of every element in
     *                              this list
     * @throws NullPointerException if the specified array is null
     */
    @Override
    public <T> T[] toArray(T[] a) {
        return (T[]) Arrays.copyOf(elementDate, size, a.getClass());
    }

    /**
     * Appends the specified element to the end of this list (optional
     * operation).
     *
     * <p>Lists that support this operation may place limitations on what
     * elements may be added to this list.  In particular, some
     * lists will refuse to add null elements, and others will impose
     * restrictions on the type of elements that may be added.  List
     * classes should clearly specify in their documentation any restrictions
     * on what elements may be added.
     *
     * @param e element to be appended to this list
     * @return <tt>true</tt> (as specified by {@link Collection#add})
     * @throws UnsupportedOperationException if the <tt>add</tt> operation
     *                                       is not supported by this list
     * @throws ClassCastException            if the class of the specified element
     *                                       prevents it from being added to this list
     * @throws NullPointerException          if the specified element is null and this
     *                                       list does not permit null elements
     * @throws IllegalArgumentException      if some property of this element
     *                                       prevents it from being added to this list
     */
    @Override
    public boolean add(E e) {
        //扩容+1
        ensureCapacity(this.size + 1);

        //添加元素
        elementDate[size] = e;

        //size+1
        size++;

        return true;
    }


    /**
     * 确保数组容量(确保新增元素时，数组不越界；移除元素无需担心)
     */
    private void ensureCapacity(int newCapacity) {
        //1.校验新capacity大于size
        if (newCapacity <= size) {
            throw new IllegalArgumentException("New capacity must bigger than size.");
        }
        //2.拷贝新数组
        elementDate = Arrays.copyOf(elementDate, newCapacity);
    }

    /**
     * Removes the first occurrence of the specified element from this list,
     * if it is present (optional operation).  If this list does not contain
     * the element, it is unchanged.  More formally, removes the element with
     * the lowest index <tt>i</tt> such that
     * <tt>(o==null&nbsp;?&nbsp;get(i)==null&nbsp;:&nbsp;o.equals(get(i)))</tt>
     * (if such an element exists).  Returns <tt>true</tt> if this list
     * contained the specified element (or equivalently, if this list changed
     * as a result of the call).
     *
     * @param o element to be removed from this list, if present
     * @return <tt>true</tt> if this list contained the specified element
     * @throws ClassCastException            if the type of the specified element
     *                                       is incompatible with this list
     *                                       (<a href="Collection.html#optional-restrictions">optional</a>)
     * @throws NullPointerException          if the specified element is null and this
     *                                       list does not permit null elements
     *                                       (<a href="Collection.html#optional-restrictions">optional</a>)
     * @throws UnsupportedOperationException if the <tt>remove</tt> operation
     *                                       is not supported by this list
     */
    @Override
    public boolean remove(Object o) {
        //1.获取元素位置
        int index = indexOf(o);
        //2.将index 至 size之间的元素左移一位
        System.arraycopy(elementDate, index + 1, elementDate, index, size - index - 1);
        //3.删除最后一个元素
        elementDate[size] = null;
        return true;
    }

    /**
     * Returns <tt>true</tt> if this list contains all of the elements of the
     * specified collection.
     *
     * @param c collection to be checked for containment in this list
     * @return <tt>true</tt> if this list contains all of the elements of the
     * specified collection
     * @throws ClassCastException   if the types of one or more elements
     *                              in the specified collection are incompatible with this
     *                              list
     *                              (<a href="Collection.html#optional-restrictions">optional</a>)
     * @throws NullPointerException if the specified collection contains one
     *                              or more null elements and this list does not permit null
     *                              elements
     *                              (<a href="Collection.html#optional-restrictions">optional</a>),
     *                              or if the specified collection is null
     * @see #contains(Object)
     */
    @Override
    public boolean containsAll(Collection<?> c) {
        return c.stream().allMatch(this::contains);
    }

    /**
     * Appends all of the elements in the specified collection to the end of
     * this list, in the order that they are returned by the specified
     * collection's iterator (optional operation).  The behavior of this
     * operation is undefined if the specified collection is modified while
     * the operation is in progress.  (Note that this will occur if the
     * specified collection is this list, and it's nonempty.)
     *
     * @param c collection containing elements to be added to this list
     * @return <tt>true</tt> if this list changed as a result of the call
     * @throws UnsupportedOperationException if the <tt>addAll</tt> operation
     *                                       is not supported by this list
     * @throws ClassCastException            if the class of an element of the specified
     *                                       collection prevents it from being added to this list
     * @throws NullPointerException          if the specified collection contains one
     *                                       or more null elements and this list does not permit null
     *                                       elements, or if the specified collection is null
     * @throws IllegalArgumentException      if some property of an element of the
     *                                       specified collection prevents it from being added to this list
     * @see #add(Object)
     */
    @Override
    public boolean addAll(Collection<? extends E> c) {
        //1.目标集合转为数组
        Object[] cArray = c.toArray();
        //2.确保容量
        ensureCapacity(size + cArray.length);
        //3.数组拷贝
        System.arraycopy(cArray, 0, elementDate, size, cArray.length);
        //4.size+n
        size += cArray.length;
        return false;
    }

    /**
     * Inserts all of the elements in the specified collection into this
     * list at the specified position (optional operation).  Shifts the
     * element currently at that position (if any) and any subsequent
     * elements to the right (increases their indices).  The new elements
     * will appear in this list in the order that they are returned by the
     * specified collection's iterator.  The behavior of this operation is
     * undefined if the specified collection is modified while the
     * operation is in progress.  (Note that this will occur if the specified
     * collection is this list, and it's nonempty.)
     *
     * @param index index at which to insert the first element from the
     *              specified collection
     * @param c     collection containing elements to be added to this list
     * @return <tt>true</tt> if this list changed as a result of the call
     * @throws UnsupportedOperationException if the <tt>addAll</tt> operation
     *                                       is not supported by this list
     * @throws ClassCastException            if the class of an element of the specified
     *                                       collection prevents it from being added to this list
     * @throws NullPointerException          if the specified collection contains one
     *                                       or more null elements and this list does not permit null
     *                                       elements, or if the specified collection is null
     * @throws IllegalArgumentException      if some property of an element of the
     *                                       specified collection prevents it from being added to this list
     * @throws IndexOutOfBoundsException     if the index is out of range
     *                                       (<tt>index &lt; 0 || index &gt; size()</tt>)
     */
    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        //1.数组转换
        Object[] cArray = c.toArray();
        //2.越界判断
        if (index + cArray.length >= size) {
            throw new ArrayIndexOutOfBoundsException("index must small than " + (size - cArray.length));
        }
        //3.确保数组容量
        ensureCapacity(size + cArray.length);
        //4.元素右移
        System.arraycopy(elementDate, index, elementDate, index + cArray.length, size - index);
        //5.填充新元素
        System.arraycopy(cArray, 0, elementDate, index, cArray.length);
        //6.size+n
        size += cArray.length;
        return true;
    }

    /**
     * Removes from this list all of its elements that are contained in the
     * specified collection (optional operation).
     *
     * @param c collection containing elements to be removed from this list
     * @return <tt>true</tt> if this list changed as a result of the call
     * @throws UnsupportedOperationException if the <tt>removeAll</tt> operation
     *                                       is not supported by this list
     * @throws ClassCastException            if the class of an element of this list
     *                                       is incompatible with the specified collection
     *                                       (<a href="Collection.html#optional-restrictions">optional</a>)
     * @throws NullPointerException          if this list contains a null element and the
     *                                       specified collection does not permit null elements
     *                                       (<a href="Collection.html#optional-restrictions">optional</a>),
     *                                       or if the specified collection is null
     * @see #remove(Object)
     * @see #contains(Object)
     */
    @Override
    public boolean removeAll(Collection<?> c) {
        for (Object o : c) {
            remove(o);
        }
        return false;
    }

    /**
     * Retains only the elements in this list that are contained in the
     * specified collection (optional operationremoveAll(C).  In other words, removes
     * from this list all of its elements that are not contained in the
     * specified collection.
     *
     * @param c collection containing elements to be retained in this list
     * @return <tt>true</tt> if this list changed as a result of the call
     * @throws UnsupportedOperationException if the <tt>retainAll</tt> operation
     *                                       is not supported by this list
     * @throws ClassCastException            if the class of an element of this list
     *                                       is incompatible with the specified collection
     *                                       (<a href="Collection.html#optional-restrictions">optional</a>)
     * @throws NullPointerException          if this list contains a null element and the
     *                                       specified collection does not permit null elements
     *                                       (<a href="Collection.html#optional-restrictions">optional</a>),
     *                                       or if the specified collection is null
     * @see #remove(Object)
     * @see #contains(Object)
     */
    @Override
    public boolean retainAll(Collection<?> c) {
        for (Object e : elementDate) {
            if (!c.contains(e)) {
                remove(e);
            }
        }
        return false;
    }

    /**
     * Removes all of the elements from this list (optional operation).
     * The list will be empty after this call returns.
     *
     * @throws UnsupportedOperationException if the <tt>clear</tt> operation
     *                                       is not supported by this list
     */
    @Override
    public void clear() {
        //1.每个元素置为kong
        for (int i = 0; i < elementDate.length; i++) {
            elementDate[i] = null;
        }
        //2.size=0
        size = 0;
    }

    /**
     * Returns the element at the specified position in this list.
     *
     * @param index index of the element to return
     * @return the element at the specified position in this list
     * @throws IndexOutOfBoundsException if the index is out of range
     *                                   (<tt>index &lt; 0 || index &gt;= size()</tt>)
     */
    @Override
    public E get(int index) {
        //1.越界判断
        if (index >= size) {
            throw new ArrayIndexOutOfBoundsException("index must small than " + size);
        }
        //2.获取元素
        return (E) elementDate[index];
    }

    /**
     * Replaces the element at the specified position in this list with the
     * specified element (optional operation).
     *
     * @param index   index of the element to replace
     * @param element element to be stored at the specified position
     * @return the element previously at the specified position
     * @throws UnsupportedOperationException if the <tt>set</tt> operation
     *                                       is not supported by this list
     * @throws ClassCastException            if the class of the specified element
     *                                       prevents it from being added to this list
     * @throws NullPointerException          if the specified element is null and
     *                                       this list does not permit null elements
     * @throws IllegalArgumentException      if some property of the specified
     *                                       element prevents it from being added to this list
     * @throws IndexOutOfBoundsException     if the index is out of range
     *                                       (<tt>index &lt; 0 || index &gt;= size()</tt>)
     */
    @Override
    public E set(int index, E element) {
        //1.越界判断
        if (index >= size) {
            throw new ArrayIndexOutOfBoundsException("index must small than size " + size);
        }
        //2.获取当前位置的元素
        E e = get(index);
        //3.替换
        elementDate[index] = element;
        return e;
    }

    /**
     * Inserts the specified element at the specified position in this list
     * (optional operation).  Shifts the element currently at that position
     * (if any) and any subsequent elements to the right (adds one to their
     * indices).
     *
     * @param index   index at which the specified element is to be inserted
     * @param element element to be inserted
     * @throws UnsupportedOperationException if the <tt>add</tt> operation
     *                                       is not supported by this list
     * @throws ClassCastException            if the class of the specified element
     *                                       prevents it from being added to this list
     * @throws NullPointerException          if the specified element is null and
     *                                       this list does not permit null elements
     * @throws IllegalArgumentException      if some property of the specified
     *                                       element prevents it from being added to this list
     * @throws IndexOutOfBoundsException     if the index is out of range
     *                                       (<tt>index &lt; 0 || index &gt; size()</tt>)
     */
    @Override
    public void add(int index, E element) {
        //1.index 不能超过size
        if (index >= size) {
            throw new IllegalArgumentException("Index cannot bigger than size.");
        }
        //2.扩容+1
        ensureCapacity(size + 1);
        //3.从index至末尾的元素后移
        System.arraycopy(elementDate, index, elementDate, index + 1, size - index);
        //4.替换index处的元素
        elementDate[index] = element;
        //5.size+1
        size++;
    }

    /**
     * Removes the element at the specified position in this list (optional
     * operation).  Shifts any subsequent elements to the left (subtracts one
     * from their indices).  Returns the element that was removed from the
     * list.
     *
     * @param index the index of the element to be removed
     * @return the element previously at the specified position
     * @throws UnsupportedOperationException if the <tt>remove</tt> operation
     *                                       is not supported by this list
     * @throws IndexOutOfBoundsException     if the index is out of range
     *                                       (<tt>index &lt; 0 || index &gt;= size()</tt>)
     */
    @Override
    public E remove(int index) {
        //1.获取当前元素
        E e = get(index);
        //2.数组拷贝
        System.arraycopy(elementDate, index + 1, elementDate, index, size - index - 1);
        //3.末尾元素置空
        elementDate[size] = null;
        //4.size--
        size--;
        //返回当前元素
        return e;
    }

    /**
     * Returns the index of the first occurrence of the specified element
     * in this list, or -1 if this list does not contain the element.
     * More formally, returns the lowest index <tt>i</tt> such that
     * <tt>(o==null&nbsp;?&nbsp;get(i)==null&nbsp;:&nbsp;o.equals(get(i)))</tt>,
     * or -1 if there is no such index.
     *
     * @param o element to search for
     * @return the index of the first occurrence of the specified element in
     * this list, or -1 if this list does not contain the element
     * @throws ClassCastException   if the type of the specified element
     *                              is incompatible with this list
     *                              (<a href="Collection.html#optional-restrictions">optional</a>)
     * @throws NullPointerException if the specified element is null and this
     *                              list does not permit null elements
     *                              (<a href="Collection.html#optional-restrictions">optional</a>)
     */
    @Override
    public int indexOf(Object o) {
        //1.定义index
        int index = -1;
        //2.遍历
        if (o == null) {
            for (int i = 0; i < elementDate.length; i++) {
                if (null == elementDate[i]) {
                    index = i;
                    break;
                }
            }
        } else {
            for (int i = 0; i < elementDate.length; i++) {
                if (o.equals(elementDate[i])) {
                    index = i;
                    break;
                }
            }
        }

        return index;
    }

    /**
     * Returns the index of the last occurrence of the specified element
     * in this list, or -1 if this list does not contain the element.
     * More formally, returns the highest index <tt>i</tt> such that
     * <tt>(o==null&nbsp;?&nbsp;get(i)==null&nbsp;:&nbsp;o.equals(get(i)))</tt>,
     * or -1 if there is no such index.
     *
     * @param o element to search for
     * @return the index of the last occurrence of the specified element in
     * this list, or -1 if this list does not contain the element
     * @throws ClassCastException   if the type of the specified element
     *                              is incompatible with this list
     *                              (<a href="Collection.html#optional-restrictions">optional</a>)
     * @throws NullPointerException if the specified element is null and this
     *                              list does not permit null elements
     *                              (<a href="Collection.html#optional-restrictions">optional</a>)
     */
    @Override
    public int lastIndexOf(Object o) {
        //1.定义index
        int index = -1;
        //2.遍历
        if (o == null) {
            for (int i = elementDate.length - 1; i >= 0; i--) {
                if (null == elementDate[i]) {
                    index = i;
                    break;
                }
            }
        } else {
            for (int i = elementDate.length - 1; i >= 0; i--) {
                if (o.equals(elementDate[i])) {
                    index = i;
                    break;
                }
            }
        }

        return index;
    }

    /**
     * Returns a list iterator over the elements in this list (in proper
     * sequence).
     *
     * @return a list iterator over the elements in this list (in proper
     * sequence)
     */
    @Override
    public ListIterator<E> listIterator() {
        return null;
    }

    /**
     * Returns a list iterator over the elements in this list (in proper
     * sequence), starting at the specified position in the list.
     * The specified index indicates the first element that would be
     * returned by an initial call to {@link ListIterator#next next}.
     * An initial call to {@link ListIterator#previous previous} would
     * return the element with the specified index minus one.
     *
     * @param index index of the first element to be returned from the
     *              list iterator (by a call to {@link ListIterator#next next})
     * @return a list iterator over the elements in this list (in proper
     * sequence), starting at the specified position in the list
     * @throws IndexOutOfBoundsException if the index is out of range
     *                                   ({@code index < 0 || index > size()})
     */
    @Override
    public ListIterator<E> listIterator(int index) {
        return null;
    }

    /**
     * Returns a view of the portion of this list between the specified
     * <tt>fromIndex</tt>, inclusive, and <tt>toIndex</tt>, exclusive.  (If
     * <tt>fromIndex</tt> and <tt>toIndex</tt> are equal, the returned list is
     * empty.)  The returned list is backed by this list, so non-structural
     * changes in the returned list are reflected in this list, and vice-versa.
     * The returned list supports all of the optional list operations supported
     * by this list.<p>
     * <p>
     * This method eliminates the need for explicit range operations (of
     * the sort that commonly exist for arrays).  Any operation that expects
     * a list can be used as a range operation by passing a subList view
     * instead of a whole list.  For example, the following idiom
     * removes a range of elements from a list:
     * <pre>{@code
     *      list.subList(from, to).clear();
     * }</pre>
     * Similar idioms may be constructed for <tt>indexOf</tt> and
     * <tt>lastIndexOf</tt>, and all of the algorithms in the
     * <tt>Collections</tt> class can be applied to a subList.<p>
     * <p>
     * The semantics of the list returned by this method become undefined if
     * the backing list (i.e., this list) is <i>structurally modified</i> in
     * any way other than via the returned list.  (Structural modifications are
     * those that change the size of this list, or otherwise perturb it in such
     * a fashion that iterations in progress may yield incorrect results.)
     *
     * @param fromIndex low endpoint (inclusive) of the subList
     * @param toIndex   high endpoint (exclusive) of the subList
     * @return a view of the specified range within this list
     * @throws IndexOutOfBoundsException for an illegal endpoint index value
     *                                   (<tt>fromIndex &lt; 0 || toIndex &gt; size ||
     *                                   fromIndex &gt; toIndex</tt>)
     */
    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        return null;
    }
}
