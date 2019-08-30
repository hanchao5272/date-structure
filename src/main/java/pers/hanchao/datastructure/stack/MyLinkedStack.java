package pers.hanchao.datastructure.stack;

import pers.hanchao.datastructure.list.MyLinkedList;

import java.util.EmptyStackException;
import java.util.Stack;

/**
 * <p>用链表实现栈</P>
 * 1.栈 相当于 一个正立的LinkedList，栈顶=List头节点，栈底=List尾结点。
 * 2.push(e):栈顶插入元素，相当于在LinkedList头结点加入元素，= MyLinkedList.push(e)。
 * 3.pop():栈顶弹出元素，相当于在List头结点删除元素，= MyLinkedList.pop()。
 * 4.peek():栈顶查询元素，相对与在List头结点获取元素，= MyLinkedList.peekFirst();
 * 5.search(e):获取在栈顶(index=1)开始出现的第一个e元素，相当于从LinkedList头结点开始获取元素，= 1 - MyLinkedList.indexOf(e);
 *
 * @author hanchao
 */
public class MyLinkedStack<E> {

    /**
     * 用链表实现栈
     */
    private MyLinkedList<E> elementList;

    /**
     * Creates an empty Stack.
     */
    public MyLinkedStack() {
        elementList = new MyLinkedList<>();
    }

    /**
     * Pushes an item onto the top of this stack. This has exactly
     * the same effect as:
     * <blockquote><pre>
     * addElement(item)</pre></blockquote>
     *
     * @param item the item to be pushed onto this stack.
     * @return the <code>item</code> argument.
     */
    public Object push(E item) {
        elementList.push(item);
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
        if (elementList.size() == 0) {
            throw new EmptyStackException();
        }
        return elementList.pop();
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
        //获取栈顶元素：链表头结点取值
        if (elementList.size() == 0) {
            throw new EmptyStackException();
        }
        return elementList.peekFirst();
    }

    /**
     * Tests if this stack is empty.
     *
     * @return <code>true</code> if and only if this stack contains
     * no items; <code>false</code> otherwise.
     */
    public boolean empty() {
        return elementList.size() == 0;
    }

    /**
     * Returns the number of components in this vector.
     *
     * @return the number of components in this vector
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
    public synchronized int search(E o) {
        //从栈顶（index=1）开始搜索，返回第一个元素所在位置
        int indexOf = elementList.indexOf(o);
        if (-1 == indexOf) {
            return -1;
        }
        return 1 + indexOf;
    }

    public static void main(String[] args) {
        Stack stack = new Stack();
        stack.push("A");
        stack.push("B");
        stack.push("C");
        MyLinkedStack myLinkedStack = new MyLinkedStack();
        myLinkedStack.push("A");
        myLinkedStack.push("B");
        myLinkedStack.push("C");

        System.out.println(stack.peek());
        System.out.println(myLinkedStack.peek());

        System.out.println(stack.search("C"));
        System.out.println(myLinkedStack.search("C"));

        System.out.println(stack.size());
        System.out.println(myLinkedStack.size());

        System.out.println(stack.pop());
        System.out.println(myLinkedStack.pop());
    }
}
