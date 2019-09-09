package pers.hanchao.algorithm.senior.heap;

/**
 * <p>空间复杂度O(1)解决TopN问题</P>
 * <p>
 * 1.最大N问题，通过小顶对解决，只处理大于堆顶的元素。
 * 2.步骤：构建N大小的堆；剩余元素，若大于堆顶，则与堆顶交换，然后调整堆。
 * 3.构建N堆：从最后非叶子节点n/2-1开始向上调整；将根左右中最小的值交换至根；调整被交换的值所在的子树
 * 4.剩余元素：若大于a[0]，则与a[0]交换；调整a[0]
 *
 * @author hanchao
 */
public class TopNByBasic {
    public static void main(String[] args) {
        int[] a = new int[]{1, 3, 22, 12, 18, 5, 7, 17, 2, 11, 4, 6, 16, 9, 8, 10};

        topN(a, 5);
        System.out.print("Top Max 5: ");
        for (int i = 0; i < 5; i++) {
            System.out.print(a[i] + " ");
        }
        System.out.println();

        a = new int[]{1, 3, 22, 12, 18, 5, 7, 17, 2, 11, 4, 6, 16, 9, 8, 10};
        topN(a, 1);
        System.out.print("Top Max 1: ");
        for (int i = 0; i < 1; i++) {
            System.out.print(a[i] + " ");
        }
        System.out.println();
    }

    /**
     * 通过小顶堆找到最大的n个值
     */
    private static void topN(int[] a, int n) {
        //临界判断
        if (a.length <= n) {
            return;
        }

        //数组的前N个元素构建小顶堆
        buildHeap(a, n);
        System.out.print("Heap init: ");
        for (int i = 0; i < n; i++) {
            System.out.print(a[i] + " ");
        }
        System.out.println();

        //剩余元素，若大于堆顶，则与堆顶交换，然后调整堆
        for (int i = n; i < a.length; i++) {
            if (a[i] > a[0]) {
                System.out.println("Heap swap: a[i]=" + a[i] + ", head =" + a[0]);
                swapHeadAndAdjust(a, i, n);
                System.out.print("Heap now: ");
                for (int j = 0; j < n; j++) {
                    System.out.print(a[j] + " ");
                }
                System.out.println();
            }else {
                System.out.println("Heap break: a[i]=" + a[i] + ", head =" + a[0]);
            }
        }
    }

    /**
     * 在数组a的前n个索引中构建小顶堆，数组长度必然大于n
     * 容量为n的堆的最后一个非叶子节点index=i/2 - 1
     */
    private static void buildHeap(int[] a, int n) {
        //从最后一个非叶子节点开始向上调整
        for (int i = n / 2 - 1; i >= 0; i--) {
            //从当前节点、其左孩子、右孩子中获取最小堆值，替换至当前节点
            adjustNode(a,i, n);
        }
    }

    /**
     * 从当前节点、其左孩子、右孩子中获取最小堆值，替换至当前节点
     *
     * 最大索引范围n
     */
    private static void adjustNode(int[] a, int i,int n) {
        int left = left(i);
        int right = right(i);

        //最小值所在index
        int minIndex = i;

        //如果左孩子存在，且左孩子小于当前节点，则最小值为左孩子
        if (left < n && a[left] < a[i]){
            minIndex = left;
        }

        //如果右孩子存在，且右孩子小于之前最小值，则右孩子为最小值
        if (right < n && a[right] < a[i]){
            minIndex = right;
        }

        //如果最小值index等于当前index，则说明当前子树无需调整
        if (i == minIndex){
            //do nothing...
        }else {
            //交换最小值与当前子树跟
            swap(a,i,minIndex);

            //因为minIndex所在的元素值发送了变化，所以要对这个节点进行调整
            adjustNode(a,minIndex,n);
        }
    }

    /**
     * 交换堆顶元素，并继续调整
     */
    private static void swapHeadAndAdjust(int[] a, int i, int n) {
        //交换a[i]与a[0]
        swap(a,0,i);
        //调整堆顶
        adjustNode(a,0,n);
    }

    /**
     * index=i的节点的左孩子index
     */
    private static int left(int i) {
        return i *2 + 1;
    }

    /**
     * index=i的节点的右孩子index
     */
    private static int right(int i) {
        return i *2 + 2;
    }

    /**
     * 交换数组a中的两个元素
     */
    public static void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}
