package pers.hanchao.datastructure.heap;

import org.springframework.util.comparator.Comparators;

import java.util.Comparator;
import java.util.Objects;

/**
 * <p>用堆(优先级队列)</P>
 * 1.用数组实现：查找、删除最大/最小元素时间复杂度为O(1)，插入元素复杂度O(n)，因为要元素移动
 * 2.用堆实现：查找最大/最小元素时间复杂度为O(1)，删除最大/最小元素时间复杂度为O(log2N)，插入元素的时间复杂度O(log2N)
 * 3.堆：用数组实现的子节点大于等于或者小于等于父节点的完全二叉树。因为其弱序性，不支持查找和遍历，因为没有意义。
 * 4.堆顶元素index=0；节点索引为index，则其父节点为(index-1)/2，其左孩子为2*index+1，有孩子为2*index+2.
 * 5.peek(): return array[0] 复杂度 O(1)
 * 6.offer(e): 1.新节点成为最后节点array[size-1]，2.上浮(交换)此节点至合适位置，3.size+1，；复杂度 O(log2N)
 * 7.poll(): 1.删除根节点，2.最后节点array[size-1]移动至根，3.下沉(交换)此节点至合适位置，4.size-1；复杂度 O(log2N)
 *
 * @author hanchao
 */
public class MyHeap<E extends Number & Comparable> {

    /**
     * 数组实现
     */
    private Object[] elements;

    private int capacity;

    private int size = 0;

    /**
     * 决定是最大堆还是最小堆
     */
    private Comparator<Object> comparator;

    public MyHeap(int capacity, Comparator<Object> comparator) {
        this.capacity = capacity;
        this.comparator = comparator;
        elements = new Object[capacity];
    }

    public int size() {
        return size;
    }

    public int capacity() {
        return capacity;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == capacity;
    }


    /**
     * 获取不删除顶元素
     */
    public E peek() {
        if (isEmpty()) {
            return null;
        }

        return (E) elements[0];
    }

    /**
     * 添加元素
     * 6.offer(e): 1.新节点成为最后节点array[size]，2.上浮(交换)此节点至合适位置，3.size+1，
     */
    public boolean offer(E e) {
        if (isFull()) {
            return false;
        }
        //1.新节点成为最后节点array[size]
        elements[size] = e;

        int index = size;

        //2.上浮(交换)此节点至合适位置,如果当前节点为根节点，则上浮终止
        while (index > 0) {
            //与父节点比较
            int fIndex = (index - 1) / 2;
            //条件成立，则交换成立，并继续上浮
            if (comparator.compare(elements[index], elements[fIndex]) > 0) {
                elements[index] = elements[fIndex];
                elements[fIndex] = e;
                index = fIndex;
            }else {
                break;
            }
        }

        //3.size+1
        size++;

        return true;
    }

    /**
     * 获取并删除顶部元素
     * <p>
     * poll(): 1.删除根节点，2.最后节点array[size-1]移动至根，3.下沉(交换)此节点至合适位置，4.size-1
     */
    public E poll() {
        if (isEmpty()) {
            return null;
        }
        E e = (E) elements[0];
        //1.删除根节点，2.最后节点array[size-1]移动至根
        elements[0] = elements[size - 1];
        elements[size - 1] = null;
        //3.下沉(交换)此节点至合适位置，
        int index = 0;
        while(index * 2 + 1 < size - 1){
            int lIndex = index * 2 + 1;
            int rIndex;
            //有可能没有又孩子,此时为了统一逻辑，让右孩子等于左孩子
            if (index * 2 + 2 == size -1){
                rIndex = lIndex;
            }else {
                 rIndex = index * 2 + 2;
            }

            int cIndex = rIndex;
            //先找出最大的孩子
            if (comparator.compare(elements[lIndex],elements[rIndex]) > 0 ){
                cIndex = lIndex;
            }
            //下沉
            if (comparator.compare(elements[index],elements[cIndex]) < 0){
                Object temp = elements[index];
                elements[index] = elements[cIndex];
                elements[cIndex] = temp;
                index = cIndex;
            }else {
                break;
            }
        }

        //4.size-1
        size--;
        return e;
    }

    /**
     * 打印
     */
    public void printAll(){
        System.out.print("[");
        for (Object element : elements) {
            if (Objects.nonNull(element)) {
                System.out.print(element + " ");
            }
        }
        System.out.print("]");
        System.out.println();
    }

    public static void main(String[] args) {
        MyHeap<Integer> maxHeap = new MyHeap<>(10, Comparators.comparable());


        for (int i = 0; i < 10; i++) {
            maxHeap.offer(i);
            maxHeap.printAll();
            System.out.println("max=" + maxHeap.peek());
        }

        System.out.println("=======");

        for (int i = 0; i < 10; i++) {
            maxHeap.poll();
            maxHeap.printAll();
            System.out.println("max=" + maxHeap.peek());
        }

        maxHeap = new MyHeap<>(9, Comparators.comparable());

        //
        Integer[] array1 = new Integer[]{3, 5, 2, 4, 7, 9, 8, 6, 1};
        for (Integer e : array1) {
            maxHeap.offer(e);
            maxHeap.printAll();
            System.out.println("max=" + maxHeap.peek());
        }

        for (int i = 0; i < array1.length; i++) {
            maxHeap.poll();
            maxHeap.printAll();
            System.out.println("max=" + maxHeap.peek());
        }
    }
}
