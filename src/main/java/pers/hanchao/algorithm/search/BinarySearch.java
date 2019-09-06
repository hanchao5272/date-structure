package pers.hanchao.algorithm.search;

/**
 * <p>二分查找:只针对有序数组</P>
 *
 * @author hanchao
 */
public class BinarySearch {
    /**
     * 边界判断、index处理
     */
    public static int binarySearch(int[] a, int value) {
        if (a.length == 0) {
            return -1;
        } else {
            return search(a, value, 0, a.length - 1);
        }
    }

    /**
     * 递归实现二分查找
     * 1.终止条件-成功：a[mid] == value
     * 2.终止条件-失败：end <= start
     * <p>
     * 非递归用while
     */
    private static int search(int[] a, int value, int start, int end) {
        int mid = start + (end - start) / 2;
        if (a[mid] == value) {
            return mid;
        } else if (end <= start) {
            return -1;
        } else {
            if (a[mid] > value) {
                return search(a, value, start, mid);
            } else {
                return search(a, value, mid + 1, end);
            }
        }
    }

    public static void main(String[] args) {
        int[] a = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        for (int i = 0; i < 12; i++) {
            System.out.println(BinarySearch.binarySearch(a, i));
        }
    }
}
