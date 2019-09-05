package pers.hanchao.algorithm.insert;

import pers.hanchao.algorithm.SortUtil;
import pers.hanchao.algorithm.Sortable;

/**
 * <p></P>
 *
 * @author hanchao
 */
public class ShellSort implements Sortable {

    /**
     * 希尔排序
     * 1.希尔排序是一种插入排序，也叫增量递减插入排序。
     * 2.增量选取最优算法为knuth算法，inc = 3*h + 1，最大增量 3*h < size，最小增量为1。
     * 3.从最大增量开始逐渐递减，执行多次插入排序，每次排序对象为间隔为增量的元素。
     * <p>
     * 复杂度
     * - 时间：
     * - 空间：
     */
    @Override
    public <E extends Comparable> E[] sort(E[] array) {
        E[] a = SortUtil.copyArrayForTest(array);
        System.out.println();
        //以下代码为了测试
        showForTest(a, 0, 0);

        //寻找最大增量
        int h = 1;
        while (3 * h < a.length) {
            h = h * 3 + 1;
        }

        //增量递减插入排序
        for (; h >= 1; h = (h - 1) / 3) {
            //增量为h的插入排序
            //从index=0,h,2h一直排序到index=h-1,2h-1,3h-1
            for (int i = 0; i < h; i++) {
                directInsert(a, i, h, a.length);

                //以下代码为了测试
                showForTest(a, h, i);
            }
        }

        return a;
    }

    private <E extends Comparable> void showForTest(E[] a, int h, int i) {
        System.out.print("经过增量=" + h + ",index=" + i + "的希尔排序之后：");
        for (E e : a) {
            System.out.print(e + " ");
        }
        System.out.println();
    }

    /**
     * 对数组a[i],a[i+h],a[i+2h]进行插入排序
     * 从待排序数组中，选择最小的元素放入已排序数组
     */
    private <E extends Comparable> void directInsert(E[] a, int start, int h, int size) {
        //start start+h start+2h
        for (int i = start; i < size; i = i + h) {
            //定义最小元素
            int minIndex = i;
            E min = a[i];

            //获取最小元素
            for (int j = i; j < size; j = j + h) {
                if (min.compareTo(a[j]) < 0) {
                    min = a[j];
                    minIndex = j;
                }
            }

            //将最小元素替换至已排序数组
            if (minIndex != i) {
                E temp = a[minIndex];
                a[minIndex] = a[i];
                a[i] = temp;
            }
        }
    }
}
