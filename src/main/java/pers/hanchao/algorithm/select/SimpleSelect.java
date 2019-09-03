package pers.hanchao.algorithm.select;

import pers.hanchao.algorithm.SortUtil;
import pers.hanchao.algorithm.Sortable;

/**
 * <p></P>
 *
 * @author hanchao
 */
public class SimpleSelect implements Sortable {

    /**
     * 简单选择排序
     * 1.n个元素的数组需要n轮简单选择
     * 2.每次简单选择就是从待排序数组中，通过依次比较，找出最小的元素，然后放入到已排序数组的最右侧
     * <p>
     * 复杂度
     * - 时间：O(n2) O(n2) O(n2)
     * - 空间：O(1)
     */
    @Override
    public <E extends Comparable> E[] sort(E[] array) {
        E[] a = SortUtil.copyArrayForTest(array);
        System.out.println();
        for (int i = 0; i < a.length; i++) {
            //从a[i]至a[size-1]中选择最小的元素放在a[i]
            int minIndex = i;
            E min = a[i];
            for (int j = i + 1; j < a.length; j++) {
                //a[j]小，则最小元素为a[j]
                if (min.compareTo(a[j]) > 0) {
                    minIndex = j;
                    min = a[j];
                }
            }
            //交换a[i]与min
            if (minIndex != i) {
                E temp = a[i];
                a[i] = a[minIndex];
                a[minIndex] = temp;
            }

            //以下代码为了测试
            showForTest(a, i);
        }
        return a;
    }

    private <E extends Comparable> void showForTest(E[] a, int i) {
        System.out.print("第" + (i + 1) + "次简单选择之后：");
        System.out.print("已排序元素 ");
        for (int k = 0; k < i + 1; k++) {
            System.out.print(a[k] + " ");
        }
        System.out.print(" 待排序元素 ");
        for (int k = i + 1; k < a.length; k++) {
            System.out.print(a[k] + " ");
        }
        System.out.println();
    }
}
