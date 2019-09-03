package pers.hanchao.algorithm.swap;

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
     *
     * 优化：
     * 1.每次冒泡，无需比较已排序元素
     * 2.可设置标志位记录每次冒泡过程中是否进行过交换，如果没有，则表示数组已经排序完成，无需继续冒泡。
     *
     * 复杂度：
     * - 时间：O(n) - O(n2) - O(n2)
     * - 空间：O(1)
     */
    @Override
    public <E extends Comparable> E[] sort(E[] array) {
        System.out.println();
        if (array != null && array.length > 0) {
            E temp;
            //每轮冒泡将待排序数组中最大的元素交换至已排序数组
            for (int i = 0; i < array.length; i++) {
                //记录本次冒泡是否有过交换
                boolean swap = false;
                //边界条件 j + 1 < array.length - i
                //array[array.length - i - 1]至array[array.length - 1]为已排序元素
                //array[j + 1]为每次比较的右侧元素
                for (int j = 0; j + 1 < array.length - i; j++) {
                    //大于则交换
                    if (array[j].compareTo(array[j + 1]) > 0) {
                        temp = array[j];
                        array[j] = array[j + 1];
                        array[j + 1] = temp;
                        swap = true;
                    }
                }
                //如果没有交换过，则表示已经排序完成
                if (!swap){
                    break;
                }
                //下面的代码是为了显示冒泡过程
                System.out.print("第" + i + "次冒泡之后：");
                System.out.print("待排序元素- ");
                for (int k = 0; k < array.length - i - 1; k++) {
                    System.out.print(array[k] + " ");
                }
                System.out.print(" 已排序元素- ");
                for (int k = array.length - i - 1; k < array.length; k++) {
                    System.out.print(array[k] + " ");
                }
                System.out.println();
            }
        }
        return array;
    }
}
