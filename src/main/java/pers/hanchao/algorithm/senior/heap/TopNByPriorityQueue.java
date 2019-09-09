package pers.hanchao.algorithm.senior.heap;

import java.util.PriorityQueue;

/**
 * <p>通过优先级队里解决TopN问题</P>
 * <p>
 * 1.PriorityQueue:优先级队列，默认就是一个小顶堆，可解决最大N问题。
 * 2.最大N问题，通过小顶对解决，只处理大于堆顶的元素。
 * 3.步骤：构建N大小的堆；剩余元素，若大于堆顶，则先poll()，后offer()。
 *
 * @author hanchao
 */
public class TopNByPriorityQueue {
    public static void main(String[] args) {
        int[] a = new int[]{1, 3, 12, 18, 5, 7, 17, 2, 11, 4, 6, 16, 9, 8, 10};

        int[] b = topN(a, 5);
        System.out.print("Top Max 5: ");
        for (int i : b) {
            System.out.print(i + " ");
        }
        System.out.println();


        int[] c = topN(a, 1);
        System.out.print("Top Max 1: ");
        for (int i : c) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    /**
     * 求解topN问题
     */
    private static int[] topN(int[] a, int n) {
        if (n > a.length) {
            return a;
        }
        //PriorityQueue默认为自然数排序，即：1，2，3。即：小顶堆
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(n);
        //构建N大小的小顶堆求解top max
        for (int i = 0; i < n; i++) {
            minHeap.offer(a[i]);
        }

        System.out.print("Heap init: ");
        for (Integer e : minHeap) {
            System.out.print(e + " ");
        }
        System.out.println();
        System.out.println();

        //剩余元素入堆
        for (int i = n; i < a.length; i++) {
            //如果元素小于堆顶，则无需处理
            if (a[i] <= minHeap.peek()) {
                System.out.println("Heap break: a[i]=" + a[i] + ", head =" + minHeap.peek());
                continue;
            } else {
                System.out.println("Heap swap: a[i]=" + a[i] + ", head =" + minHeap.peek());
                //删除堆顶
                minHeap.poll();
                //入堆
                minHeap.offer(a[i]);
            }
        }
        int[] result = new int[n];
        for (int i = 0; i < result.length; i++) {
            result[i] = minHeap.poll();
        }
        return result;
    }

}
