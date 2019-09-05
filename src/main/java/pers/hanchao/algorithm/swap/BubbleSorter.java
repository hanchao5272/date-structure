package pers.hanchao.algorithm.swap;

import pers.hanchao.algorithm.SortUtil;
import pers.hanchao.algorithm.Sortable;

/**
 * <p>bubble sort</P>
 *
 * @author hanchao
 */
public class BubbleSorter implements Sortable {

    /**
     * 冒泡排序：
     * 1.n个元素的数组需要经过n次冒泡
     * 2.每次冒泡都是从index=0开始，依次比较相邻元素，将较大元素交换至右侧
     * <p>
     * 优化：
     * 1.每次冒泡，无需比较已排序元素
     * 2.可设置标志位记录每次冒泡过程中是否进行过交换，如果没有，则表示数组已经排序完成，无需继续冒泡。
     * <p>
     * 复杂度：
     * - 时间：O(n) - O(n2) - O(n2)
     * - 空间：O(1)
     */
    @Override
    public <E extends Number & Comparable> E[] sort(E[] array) {
        E[] a = SortUtil.copyArrayForTest(array);
        System.out.println();
        if (a != null && a.length > 0) {
            E temp;
            //每轮冒泡将待排序数组中最大的元素交换至已排序数组
            for (int i = 0; i < a.length; i++) {
                //记录本次冒泡是否有过交换
                boolean swap = false;
                //边界条件 j + 1 < array.length - i
                //array[array.length - i - 1]至array[array.length - 1]为已排序元素
                //array[j + 1]为每次比较的右侧元素
                for (int j = 0; j + 1 < a.length - i; j++) {
                    //大于则交换
                    if (a[j].compareTo(a[j + 1]) > 0) {
                        temp = a[j];
                        a[j] = a[j + 1];
                        a[j + 1] = temp;
                        swap = true;
                    }
                }
                //如果没有交换过，则表示已经排序完成
                if (!swap) {
                    break;
                }

                //下面的代码是为了显示冒泡过程
                showForTest(a, i);
            }
        }
        return a;
    }

    private <E extends Number & Comparable> void showForTest(E[] a, int i) {
        System.out.print("第" + (i + 1) + "次冒泡之后：");
        System.out.print("待排序元素 ");
        for (int k = 0; k < a.length - i - 1; k++) {
            System.out.print(a[k] + " ");
        }
        System.out.print(" 已排序元素 ");
        for (int k = a.length - i - 1; k < a.length; k++) {
            System.out.print(a[k] + " ");
        }
        System.out.println();
    }
}
