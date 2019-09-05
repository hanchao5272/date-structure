package pers.hanchao.algorithm;

/**
 * <p>排序</P>
 *
 * @author hanchao
 */
public interface Sortable {
    <E extends Number & Comparable> E[] sort(E[] array);
}
