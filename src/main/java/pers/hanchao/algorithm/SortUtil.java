package pers.hanchao.algorithm;

/**
 * <p></P>
 *
 * @author hanchao
 */
public class SortUtil {
    /**
     * 为了测试，拷贝一份
     */
    public static <E extends Comparable> E[] copyArrayForTest(E[] array) {
        E[] a = (E[]) new Comparable[array.length];
        System.arraycopy(array,0,a,0,array.length);
        return a;
    }
}
