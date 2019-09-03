package pers.hanchao.algorithm;

import pers.hanchao.algorithm.insert.DirectInsert;
import pers.hanchao.algorithm.select.SimpleSelect;
import pers.hanchao.algorithm.swap.BubbleSorter;

/**
 * <p></P>
 *
 * @author hanchao
 */
public class SortDemo {
    public static void main(String[] args) {
        Integer[] array1 = new Integer[]{3, 5, 2, 4, 7, 9, 8, 6, 1};
        Integer[] array2 = new Integer[]{1, 2, 3, 6, 4, 7, 9, 8, 5};

        //1.bubble sort
        new BubbleSorter().sort(array1);
        new BubbleSorter().sort(array2);

        //2.simple select
        new SimpleSelect().sort(array1);
        new SimpleSelect().sort(array2);

        //3.direct insert
        new DirectInsert().sort(array1);
        new DirectInsert().sort(array2);

    }
}
