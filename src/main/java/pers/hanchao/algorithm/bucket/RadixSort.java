package pers.hanchao.algorithm.bucket;

import pers.hanchao.algorithm.SortUtil;
import pers.hanchao.algorithm.Sortable;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * <p></P>
 *
 * @author hanchao
 */
public class RadixSort implements Sortable {
    /**
     * 基数排序ø
     * 1.最大位数为m的数组需要经过m轮基数排序
     * 2.每轮技术排序是按照当前位(个位、十位)的大小放入对应的桶（10个桶）
     * <p>
     * 复杂度
     * - 时间：O(k*n) O(k*n) O(k*n) k=位数 n=元素个数
     * - 空间：O(n)
     */
    @Override
    public <E extends Number & Comparable> E[] sort(E[] array) {
        E[] a = SortUtil.copyArrayForTest(array);
        System.out.println();

        //遍历数组找出最大值，计算出最大位数
        int digits = findMaxDigits(a);

        //定义10个桶
        ArrayList<E>[] buckets = new ArrayList[10];
        //初始化桶
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = new ArrayList<>(a.length);
        }

        //每个位数进行一次入桶和出桶
        for (int i = 1; i <= digits; i++) {
            //入桶
            for (int j = 0; j < a.length; j++) {
                E current = a[j];
                //计算当前元素、当前位数对应的值
                int k = (int) ((current.intValue() / Math.pow(10, i - 1)) % 10);
                if (buckets[k].size() == 0) {
                    //插入表头
                    buckets[k].add(current);
                } else {
                    //与链表中元素比较，插入到合适位置
                    Iterator<E> iterator = buckets[k].iterator();
                    E next = iterator.next();
                    while (iterator.hasNext()) {
                        if (next.compareTo(current) > 0) {
                            break;
                        }
                        next = iterator.next();
                    }
                    buckets[k].add(buckets[k].indexOf(next), current);
                }

            }
            //出桶
            int m = 0;
            for (int j = 0; j < 10; j++) {
                while (buckets[j].size() > 0) {
                    a[m++] = buckets[j].remove(0);
                }
            }

            //为了测试打印信息
            System.out.print("第" + i + "次基数排序之后：");
            for (int k = 0; k < a.length; k++) {
                System.out.print(a[k] + " ");
            }
            System.out.println();
        }


        return null;
    }

    /**
     * 找到最大位数
     */
    private <E extends Number & Comparable> int findMaxDigits(E[] a) {
        E max = a[0];
        for (E e : a) {
            if (e.compareTo(max) > 0) {
                max = e;
            }
        }
        int digits = 1;
        int num = max.intValue();
        while (num > 10) {
            num = num / 10;
            digits++;
        }
        return digits;
    }
}
