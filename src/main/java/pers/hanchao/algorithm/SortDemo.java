package pers.hanchao.algorithm;

import pers.hanchao.algorithm.insert.DirectInsert;
import pers.hanchao.algorithm.insert.ShellSort;
import pers.hanchao.algorithm.merge.MergeSort;
import pers.hanchao.algorithm.select.HeapSort;
import pers.hanchao.algorithm.select.SimpleSelect;
import pers.hanchao.algorithm.swap.BubbleSorter;
import pers.hanchao.algorithm.swap.QuickSort;

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

        //12.swap - quick sort
        new QuickSort().sort(array1);
        new QuickSort().sort(array2);
        new QuickSort().sort(array3);

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

        //32.shell sort
        new ShellSort().sort(array1);
        new ShellSort().sort(array2);
        new ShellSort().sort(array3);

        //4.merge
        new MergeSort().sort(array1);
        new MergeSort().sort(array2);
        new MergeSort().sort(array3);
    }
}
