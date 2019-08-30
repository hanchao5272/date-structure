package pers.hanchao.datastructure;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hanchao
 */
public class MyDataStructureDemo<E> {

    public static void main(String[] args) {
        List list = new ArrayList(10);
        System.out.println(list.size());
        list.add(1);
        System.out.println(list.size());
        list.add(2, 20);
        System.out.println(list.size());

    }
}
