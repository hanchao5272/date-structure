package pers.hanchao.algorithm;

/**
 * <p>排序</P>
 *
 * @author hanchao
 */
public interface Sortable {
    <E extends Comparable> E[] sort(E[] a);
}
