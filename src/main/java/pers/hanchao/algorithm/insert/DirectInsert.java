package pers.hanchao.algorithm.insert;

import pers.hanchao.algorithm.SortUtil;
import pers.hanchao.algorithm.Sortable;

/**
 * <p></P>
 *
 * @author hanchao
 */
public class DirectInsert implements Sortable {

    /**
     * 直接插入排序
     * 1.n个元素的数组需要进行n次直接插入
     * 2.每次直接插入就是：从待排序元素中拿出index最小的元素，然后根据大小关系插入到已排序数组的合适位置
     * <p>
     * 复杂度
     * - 时间 O(n) O(n2) O(n2)
     * - 空间 O(1)
     * <p>
     * 稳定排序
     */
    @Override
    public <E extends Number & Comparable> E[] sort(E[] array) {
        System.out.println();
        E[] a = SortUtil.copyArrayForTest(array);
        //获取a[i]，将其插入到已排序数组a[0]至a[i]中的合适位置。
        for (int i = 1; i < a.length; i++) {
            //待插入元素
            E current = a[i];

            //插入位置
            int insertIndex = i;
            //a[0]至a[i]
            for (int j = 0; j < i; j++) {
                if (current.compareTo(a[j]) > 0) {
                    insertIndex = j;
                    break;
                }
            }

            //如果是自己，无需挪动元素
            if (insertIndex != i) {
                //从insertIndex+1至 i-1 开始后移
                for (int j = i; j - 1 >= insertIndex; j--) {
                    a[j] = a[j - 1];
                }

                //插入insertIndex
                a[insertIndex] = current;
            }

            //以下代码为了测试
            showForTest(a, i);
        }
        return a;
    }

    private <E extends Number & Comparable> void showForTest(E[] a, int i) {
        System.out.print("第" + i + "次直接插入之后：");
        System.out.print("已排序元素 ");
        for (int k = 0; k <= i; k++) {
            System.out.print(a[k] + " ");
        }
        System.out.print(" 待排序元素 ");
        for (int k = i + 1; k < a.length; k++) {
            System.out.print(a[k] + " ");
        }
        System.out.println();
    }


}
