package pers.hanchao.algorithm.senior.fastandslow;

/**
 * <p>判断链表是否有环：快慢指针法</P>
 * <p>
 * 其他应用：找到链表的中间元素（快指针走到末尾时，慢指针所在位置）
 *
 * @author hanchao
 */
public class LinkedListHasRing {
    private static class Node {
        int data;
        Node next;

        public Node(int data, Node next) {
            this.data = data;
            this.next = next;
        }

        public Node(int data) {
            this.data = data;
        }

        public Node addNext(Node node) {
            this.next = node;
            return this;
        }
    }

    /**
     * 快慢指针法判断是否有环
     * 1.每次循环，快指针走两步，慢指针走一步
     * 2.若快慢指针相遇，则必有环
     * 3.若快指针的下一步或者下下步为null，则必无欢
     */
    public static boolean hasRing(Node head) {
        //如果最多有1个节点则不可能有环
        if (head == null || head.next == null) {
            return false;
        }
        Node fast = head;
        Node slow = head;
        while (fast != null) {
            //快指针走一步
            fast = fast.next;
            if (fast == null) {
                return false;
            }
            //快指针走两步
            fast = fast.next;
            if (fast == null) {
                return false;
            }
            //慢指针走一步
            slow = slow.next;
            //如果快慢指针相遇，则有环
            if (fast == slow) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Node node2 = new Node(2);
        Node node6 = new Node(6);
        Node head = new Node(1, node2.addNext(new Node(3, new Node(4, new Node(5, node6)))));
        System.out.println(hasRing(head));

        node6.next = node2;
        System.out.println(hasRing(head));
    }
}
