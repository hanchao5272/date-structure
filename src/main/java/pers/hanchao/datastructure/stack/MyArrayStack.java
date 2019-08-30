package pers.hanchao.datastructure.stack;

import pers.hanchao.datastructure.list.MyArrayList;

import java.util.EmptyStackException;
import java.util.Stack;
import java.util.Vector;

/**
 * <p>用数组实现栈</P>
 * 1.栈 相当于 一个倒立的List，栈顶=List尾部，栈底=List头部。
 * 1.push(e):栈顶插入元素，相当于在List尾部加入元素，= MyArrayList.add(e)。
 * 2.pop():栈顶弹出元素，相当于在List尾部删除元素，= MyArrayList.remove(size - 1)。
 * 3.peek():栈顶查询元素，相对与在List尾部获取元素，= MyArrayList.get(0);
 * 4.search(e):获取在栈顶(index=1)开始出现的第一个e元素，相当于从List尾部开始获取元素，= size - MyArrayList.lastIndexOf(e);
 *
 * @author hanchao
 */
public class MyArrayStack<E> {
    /**
     * 通过数组实现栈
     */
    private MyArrayList<E> elementList;

    /**
     * Creates an empty Stack.
     */
    public MyArrayStack() {
        elementList = new MyArrayList<>();
    }

    /**
     * Pushes an item onto the top of this stack. This has exactly
     * the same effect as:
     * <blockquote><pre>
     * addElement(item)</pre></blockquote>
     *
     * @param item the item to be pushed onto this stack.
     * @return the <code>item</code> argument.
     * @see Vector#addElement
     */
    public Object push(Object item) {
        //入栈：在list尾部插入元素
        elementList.add((E) item);
        return item;
    }

    /**
     * Removes the object at the top of this stack and returns that
     * object as the value of this function.
     *
     * @return The object at the top of this stack (the last item
     * of the <tt>Vector</tt> object).
     * @throws EmptyStackException if this stack is empty.
     */
    public synchronized Object pop() {
        //出栈：去除list尾部元素
        if (elementList.isEmpty()) {
            throw new EmptyStackException();
        }
        return elementList.remove(elementList.size() - 1);
    }

    /**
     * Looks at the object at the top of this stack without removing it
     * from the stack.
     *
     * @return the object at the top of this stack (the last item
     * of the <tt>Vector</tt> object).
     * @throws EmptyStackException if this stack is empty.
     */
    public synchronized Object peek() {
        //偷看：获取list尾部元素
        if (elementList.isEmpty()) {
            throw new EmptyStackException();
        }
        return elementList.get(elementList.size() - 1);
    }

    /**
     * Tests if this stack is empty.
     *
     * @return <code>true</code> if and only if this stack contains
     * no items; <code>false</code> otherwise.
     */
    public boolean empty() {
        return elementList.isEmpty();
    }

    /**
     * Returns the number of components in this vector.
     *
     * @return  the number of components in this vector
     */
    public synchronized int size() {
        return elementList.size();
    }

    /**
     * Returns the 1-based position where an object is on this stack.
     * If the object <tt>o</tt> occurs as an item in this stack, this
     * method returns the distance from the top of the stack of the
     * occurrence nearest the top of the stack; the topmost item on the
     * stack is considered to be at distance <tt>1</tt>. The <tt>equals</tt>
     * method is used to compare <tt>o</tt> to the
     * items in this stack.
     *
     * @param o the desired object.
     * @return the 1-based position from the top of the stack where
     * the object is located; the return value <code>-1</code>
     * indicates that the object is not on the stack.
     */
    public synchronized int search(Object o) {
        //从栈顶（index=1）开始搜索，返回第一个元素所在位置
        int lastIndexOf = elementList.lastIndexOf(o);
        if (lastIndexOf == -1){
            return -1;
        }
        return elementList.size() - lastIndexOf;
    }

    public static void main(String[] args) {
        Stack stack = new Stack();
        stack.push("A");
        stack.push("B");
        stack.push("C");
        MyArrayStack myArrayStack = new MyArrayStack();
        myArrayStack.push("A");
        myArrayStack.push("B");
        myArrayStack.push("C");

        System.out.println(stack.peek());
        System.out.println(myArrayStack.peek());

        System.out.println(stack.search("C"));
        System.out.println(myArrayStack.search("C"));

        System.out.println(stack.size());
        System.out.println(myArrayStack.size());

        System.out.println(stack.pop());
        System.out.println(myArrayStack.pop());
    }
}
