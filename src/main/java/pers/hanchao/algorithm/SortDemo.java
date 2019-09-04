package pers.hanchao.algorithm;

import pers.hanchao.algorithm.insert.DirectInsert;
import pers.hanchao.algorithm.select.HeapSort;
import pers.hanchao.algorithm.select.SimpleSelect;
import pers.hanchao.algorithm.swap.BubbleSorter;

/**
 * <p></P>
 *
 * @author hanchao
 */
public class SortDemo {
    public static void main(String[] args) {
        Integer[] array1 = new Integer[]{3, 5, 2, 4, 7, 6, 1};
        Integer[] array2 = new Integer[]{49, 38, 65, 97, 76, 13, 27, 49};
        Integer[] array3 = new Integer[]{1, 2, 3, 6, 4, 7, 9, 8, 5};

        //11.swap - bubble sort
        new BubbleSorter().sort(array1);
        new BubbleSorter().sort(array2);
        new BubbleSorter().sort(array3);

        //21.select - simple select
        new SimpleSelect().sort(array1);
        new SimpleSelect().sort(array2);
        new SimpleSelect().sort(array3);

        //22.select - heap
        new HeapSort().sort(array1);
        new HeapSort().sort(array2);
        new HeapSort().sort(array3);

        //31.direct insert
        new DirectInsert().sort(array1);
        new DirectInsert().sort(array2);
        new DirectInsert().sort(array3);

        //4.
    }
}