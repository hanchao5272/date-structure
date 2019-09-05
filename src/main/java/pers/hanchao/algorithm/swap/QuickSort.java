package pers.hanchao.algorithm.swap;

import pers.hanchao.algorithm.SortUtil;
import pers.hanchao.algorithm.Sortable;

/**
 * <p></P>
 *
 * @author hanchao
 */
public class QuickSort implements Sortable {
    /**
     * 快速排序
     * 1.快速排序是一种采用了分治策略的交换算法。
     * 2.n个元素的数组需要经过log2n次的快速分治。
     * 3.每次快速分治都会将待排序数组以基准元素为中线划分为两部分，左侧部分小于基准元素，右侧部分大于基准元素。
     * 4.每次快速分治设立左游标和右游标
     * 4.1.左游标向右探索，右游标向左探索，右游标先行。
     * 4.2.右游标遇到小于基准元素的值停止，左游标遇到大于基准元素的值停止。
     * 4.3.当左右游标都停止时，交换游标所在元素，继续探索。
     * 4.4.当左右游标相遇时，一次快速分治结束。
     * <p>
     * 复杂度
     * - 时间：O(nlog2n) O(nlog2n) O(nlog2n)
     * - 空间：O(1)
     * <p>
     * 不稳定排序
     */
    @Override
    public <E extends Number & Comparable> E[] sort(E[] array) {
        E[] a = SortUtil.copyArrayForTest(array);
        System.out.println();

        quickSort(a, 0, a.length - 1);

        return a;
    }

    /**
     * @param start 开始元素index，包括
     * @param end   结束元素index，包括
     */
    public <E extends Number & Comparable> void quickSort(E[] a, int start, int end) {
        //终止条件：start=end
        if (start >= end) {
            return;
        }
        //获取基准元素index
        int mid = division(a, start, end);
        //对基准左侧元素继续快排
        quickSort(a, start, mid - 1);
        //对基准右侧元素继续快排
        quickSort(a, mid + 1, end);

        //打印信息for测试
        printForTest(a, start, end, mid);
    }

    private <E extends Number & Comparable> void printForTest(E[] a, int start, int end, int mid) {
        System.out.print("某次快速分治： [ ");
        for (int i = start; i < mid; i++) {
            System.out.print(a[i] + " ");
        }
        System.out.print("< " + a[mid]);
        System.out.print(" > ");
        for (int i = mid + 1; i <= end; i++) {
            System.out.print(a[i] + " ");
        }
        System.out.print("]\n");
    }

    /**
     * 对a[start]至a[end]元素进行一次快速分治
     */
    public <E extends Number & Comparable> int division(E[] a, int start, int end) {
        int left = start;
        int right = end;
        //如果左右游标未相遇，则一直循环
        while (right != left) {
            //右游标先行向左探测，直到遇到小于它的元素
            for (right = end; right > left; right--) {
                if (a[right].compareTo(a[start]) < 0) {
                    break;
                }
            }
            //左游标后行向右探测，直到遇到大于他的元素
            for (left = start; left < right; left++) {
                if (a[left].compareTo(a[start]) > 0) {
                    break;
                }
            }
            //如果左右游标未相遇，则交换元素
            if (left != right) {
                E temp = a[left];
                a[left] = a[right];
                a[right] = temp;
            } else {
                break;
            }
        }
        //左右游标已经相遇，将此处元素与start元素交换
        if (start != right) {
            E temp = a[start];
            a[start] = a[right];
            a[right] = temp;
        }

        return right;
    }
}
