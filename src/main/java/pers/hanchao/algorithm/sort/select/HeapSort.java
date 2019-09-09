package pers.hanchao.algorithm.sort.select;

import pers.hanchao.algorithm.sort.SortUtil;
import pers.hanchao.algorithm.sort.Sortable;

/**
 * <p></P>
 *
 * @author hanchao
 */
public class HeapSort implements Sortable {

    /**
     * 堆排序
     * 1.n个元素的数组需要经过n轮堆选择，每轮对选择会选出当前堆中最大/小的元素。
     * 2.堆化：自最后一个非叶子节点开始，选择每颗子树最大节点交换至子树根节点，至此，当前堆中最大/小的元素被交换至index=0。
     * 2.出堆：将堆顶元素交换至n-1，然后待排序数组长度n--。
     * <p>
     * 关键：
     * - 对index=i的元素，其根节点index=(i-1)/2，其孩子节点index=2*i+1和index=2*i+1。
     * - 大小为n的数组，其最后一个非叶子节点的index=n/2 - 1。
     * - 如何来的呢？假定最后一个非叶子节点index=x，则
     * - 如果最后一个非叶子节点有1个孩子，则n为偶数，则：2x+1=n-1 ==> x=(n-2)/2=n/2-1，n为偶数，可以除尽，最终：x=n/2-1
     * - 如果最后一个非叶子节点有2个孩子，则n为奇数，则：2x+2=n-1 ==> x=(n-3)/2=(n-1-2)/=(n-1)/2-1，n为奇数，java除法向下取整，(n-1)/2 = n/2，最终：x=n/2-1
     * <p>
     * 复杂度
     * - 时间: O(nlog2n) O(nlog2n) O(nlog2n)
     * - 空间：O(1)
     * <p>
     * 不稳定排序
     */
    @Override
    public <E extends Number & Comparable> E[] sort(E[] array) {
        E[] a = SortUtil.copyArrayForTest(array);
        System.out.println();

        //每次循环，相当于选择出待排序元素中最大的元素；剩余元素个数减一
        for (int n = a.length; n > 0; n--) {
            E temp;
            //从最后一个非叶子节点向上调整
            for (int i = n / 2 - 1; i >= 0; i--) {

                //获取较大的叶子节点（右孩子可能不存在）
                int bigChildIndex;
                if (2 * i + 2 <= n - 1) {
                    //当右孩子存在，则从两个孩子中获取较大的孩子
                    bigChildIndex = a[2 * i + 1].compareTo(a[2 * i + 2]) > 0 ? 2 * i + 1 : 2 * i + 2;
                } else {
                    //当右孩子不存在，则直接获取左孩子
                    bigChildIndex = 2 * i + 1;
                }
                //如果叶子大于根，则交换
                if (a[bigChildIndex].compareTo(a[i]) > 0) {
                    temp = a[bigChildIndex];
                    a[bigChildIndex] = a[i];
                    a[i] = temp;
                }
            }
            //将堆顶元素与最后元素交换
            temp = a[0];
            a[0] = a[n - 1];
            a[n - 1] = temp;

            //以下代码为了测试
            showForTest(a.length, a, n);
        }

        return a;
    }

    private <E> void showForTest(int length, E[] a, int n) {
        System.out.print("第" + (length - n + 1) + "次堆排序之后：");
        System.out.print(" 待排序元素 ");
        for (int k = 0; k < n - 1; k++) {
            System.out.print(a[k] + " ");
        }
        System.out.print("已排序元素 ");
        for (int k = n - 1; k < length; k++) {
            System.out.print(a[k] + " ");
        }

        System.out.println();
    }
}
