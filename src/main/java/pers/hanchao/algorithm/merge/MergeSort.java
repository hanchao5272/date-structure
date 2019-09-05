package pers.hanchao.algorithm.merge;

import pers.hanchao.algorithm.SortUtil;
import pers.hanchao.algorithm.Sortable;

/**
 * <p></P>
 *
 * @author hanchao
 */
public class MergeSort implements Sortable {
    /**
     * 归并排序
     * 1.归并排序是一种递归、分治、排序算法。
     * 2.n个元素的数组需要log2n次合并。
     * 3.每次合并时将两个已排序的子数组合并。
     * <p>
     * 复杂度
     * - 时间：O(nlog2n) O(nlog2n) O(nlog2n)
     * - 空间：O(n)
     */
    @Override
    public <E extends Number & Comparable> E[] sort(E[] array) {
        E[] a = SortUtil.copyArrayForTest(array);
        System.out.println();

        mergeSort(a, 0, a.length - 1);

        return a;
    }

    /**
     * mid的选取：mid = start + (end - start) / 2 = (start + end) / 2
     */
    public <E extends Number & Comparable> void mergeSort(E[] a, int start, int end) {
        //终止递归
        if (end == start) {
            return;
        }
        //二分数组
        int mid = (start + end) / 2;
        //把左侧数组归并排序，左侧数组变得有序
        mergeSort(a, start, mid);
        //把右侧数组归并排序，右侧数组变得有序
        mergeSort(a, mid + 1, end);
        //合并左右数组
        merge(a, start, mid, end);

        //打印信息for测试
        printForTest(a, start, end, mid);
    }
    private <E extends Number & Comparable> void printForTest(E[] a, int start, int end, int mid) {
        System.out.print("某次归并： [ ");
        for (int i = start; i <= mid; i++) {
            System.out.print(a[i] + " ");
        }
        System.out.print(" | ");
        for (int i = mid + 1; i <= end; i++) {
            System.out.print(a[i] + " ");
        }
        System.out.print("]\n");
    }

    /**
     * a[start]~a[mid]
     * a[mid+1]~a[end]
     */
    private <E extends Number & Comparable> void merge(E[] a, int start, int mid, int end) {
        //临时数组用于存放合并的元素
        E[] copy = (E[]) new Number[end - start + 1];

        int left=start,right=mid+1;
        int c = 0;
        //通过比较，合并两个子数组的交叉元素
        while(left <=mid && right <=end ){
            if (a[left].compareTo(a[right]) < 0){
                copy[c] = a[left];
                left ++;
            }else {
                copy[c] = a[right];
                right++;
            }
            c++;
        }

        //如果左数组已经合并万，则直接合并右数组
        if (left == mid + 1){
            System.arraycopy(a,right,copy,c,end + 1 - right);
        }else {
            System.arraycopy(a,left,copy,c,mid + 1 - left);
        }

        //拷贝回原数组
        System.arraycopy(copy,0,a,start,copy.length);
    }
}
