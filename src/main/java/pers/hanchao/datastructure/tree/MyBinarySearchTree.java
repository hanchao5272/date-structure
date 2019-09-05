package pers.hanchao.datastructure.tree;

/**
 * <p>自定义二叉查找树(左子树均小于根节点，右子树均大于根节点)</P>
 * 1.contains(e)、find(e)、findMin(e)、findMax(e)：while实现二分查找；复杂度log2N
 * 2.前序 root-left-right, 中序 left-root-right ，后续 left-right-root：递归实现；时间复杂度O(n)
 * 3.常常定义父节点用于回溯
 * 4.删除采用惰性删除，标志位delete.
 * @author hanchao
 */
public class MyBinarySearchTree<E extends Number & Comparable> {
    /**
     * 树节点
     */
    private class Node<E> {
        E data;
        Node<E> left;
        Node<E> right;
        boolean delete;

        public Node(E data) {
            this.data = data;
            this.delete = false;
        }

        public Node(E data, Node<E> left, Node<E> right) {
            this.data = data;
            this.left = left;
            this.right = right;
            this.delete = false;
        }

        public void printNode(){
            if (!delete){
                System.out.print(" --> " + data);
            }
        }
    }

    /**
     * 根节点
     */
    private Node<E> root;

    public MyBinarySearchTree() {

    }

    /**
     * 是否为空
     */
    public boolean isEmpty() {
        return null == root;
    }

    /**
     * 是否包含某节点(二分查找)
     */
    public boolean contains(E e) {
        //从根节点开始，如果e大于根节点，则查找右子树；如果e小于根节点，则查找左子树
        //如果节点为空，则停止查找
        Node<E> node = root;
        while (node != null) {
            if (e.compareTo(node.data) > 0) {
                node = node.right;
            } else if (e.compareTo(node.data) < 0) {
                node = node.left;
            } else {
                return true;
            }
        }
        return false;
    }

    /**
     * 查找某节点(二分查找)
     */
    public Node<E> find(E e) {
        //从根节点开始，如果e大于根节点，则查找右子树；如果e小于根节点，则查找左子树
        //如果节点为空，则停止查找
        Node<E> node = root;
        while (node != null) {
            if (e.compareTo(node.data) > 0) {
                node = node.right;
            } else if (e.compareTo(node.data) < 0) {
                node = node.left;
            } else {
                return node;
            }
        }
        return null;
    }

    /**
     * 获取最小值(一路向左)
     */
    public E findMin() {
        Node<E> node = root;
        Node<E> minNode = node;
        while (node != null) {
            minNode = node;
            node = node.left;
        }
        return minNode.data;
    }

    /**
     * 获取最大值(一路向右)
     */
    public E findMax() {
        Node<E> node = root;
        Node<E> maxNode = node;
        while (node != null) {
            maxNode = node;
            node = node.right;
        }
        return maxNode.data;
    }

    /**
     * 插入一个节点(二分查找，重复节点抛异常)
     */
    public void insert(E e) {
        //根节点为空，则插入根节点
        if (root == null){
            root = new Node<>(e);
        }else {
            //根节点不为空，则二分查找位置
            Node<E> node = root;
            //记录当前节点的上个节点
            Node<E> parent = node;
            while (node != null){
                if (e.compareTo(node.data) > 0){
                    parent = node;
                    //大于当前节点，继续找右子树
                    node = node.right;
                    //如果右子树为空，则将右子树置为新节点
                    if (node == null){
                        parent.right = new Node<>(e);
                    }
                }else if (e.compareTo(node.data) < 0){
                    parent = node;
                    //小于当前节点，继续找左子树
                    node = node.left;
                    //如果做子树为空，则将左子树置为新节点
                    if (node == null){
                        parent.left = new Node<>(e);
                    }
                }else {
                    throw new IllegalArgumentException("重复节点");
                }
            }
        }
    }

    /**
     * 前序遍历打印树(root -> left -> right)(递归)
     */
    public void printTreeByPreOrder() {
        printTreeByPreOrder(root);
        System.out.println();
    }

    /**
     * 前序遍历打印树(root -> left -> right)(递归)
     */
    public void printTreeByPreOrder(Node<E> node) {
        if (node !=null){
            node.printNode();
            printTreeByPreOrder(node.left);
            printTreeByPreOrder(node.right);
        }
    }

    /**
     * 中序遍历打印树(left -> root -> right)
     */
    public void printTreeByInOrder() {
        printTreeByInOrder(root);
        System.out.println();
    }

    private void printTreeByInOrder(Node<E> node) {
        if (node != null){
            printTreeByInOrder(node.left);
            node.printNode();
            printTreeByInOrder(node.right);
        }
    }

    /**
     * 后序遍历打印树(left -> right -> root)
     */
    public void printTreeByPostOrder() {
        printTreeByPostOrder(root);
        System.out.println();
    }

    private void printTreeByPostOrder(Node<E> node) {
        if (node != null){
            printTreeByPostOrder(node.left);
            printTreeByPostOrder(node.right);
            node.printNode();
        }
    }

    /**
     * 删除一个节点(懒惰删除)
     */
    public void remove(E e) {
        Node<E> node = find(e);
        if (node != null){
            node.delete = true;
        }
    }

    public static void main(String[] args) {
        MyBinarySearchTree<Integer> tree = new MyBinarySearchTree<>();
        tree.insert(5);
        tree.insert(3);
        tree.insert(7);
        tree.insert(2);
        tree.insert(4);
        tree.insert(6);
        tree.insert(8);

        System.out.println(tree.contains(4));
        System.out.println(tree.contains(9));

        System.out.println(tree.findMin());
        System.out.println(tree.findMax());

        tree.printTreeByPreOrder();
        tree.printTreeByInOrder();
        tree.printTreeByPostOrder();

        tree.remove(4);
        tree.remove(6);

        tree.printTreeByPreOrder();
        tree.printTreeByInOrder();
        tree.printTreeByPostOrder();

    }
}
