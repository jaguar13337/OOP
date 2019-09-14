package ru.nsu.fit.g18214.yakovlev.Heapsort;




public class Heapsort {
    /**
     * Sort given array of ints and returns it.
     * @param ary array, that you want to sort
     * @return sorted array
     */
    public static int[] heapSort(int[] ary) {
        ary = heapify(ary);
        int end = ary.length - 1;

        while (end > 0) {
            arySwap(ary, 0, end);
            end--;
            siftDown(ary, 0, end);
        }

        return ary;
    }

    private static int[] heapify(int[] ary) {
        int start = parentIndex(ary.length);
        while (start >= 0) {
            siftDown(ary, start, ary.length-1);
            start--;
        }
        return ary;
    }

    private static void siftDown(int[] ary, int start, int end) {
        int root = start;

        while(iLeftChild(root) <= end) {

            int child = iLeftChild(root);
            int swap = root;

            if (ary[swap] < ary[child])
                swap = child;

            if(child + 1 <= end && ary[swap] < ary[child + 1]) {
                swap = child + 1;
            }

            if (swap != root) {
                arySwap(ary, root, swap);
                root = swap;
            }
            else
                return;

        }
    }

    private static int parentIndex(int index) {
        return (index - 1)/2;
    }

    private static int iLeftChild(int index) {
        return 2 * index + 1;
    }

    private static void arySwap(int[] ary, int a, int b) {
        int tmp = ary[a];
        ary[a] = ary[b];
        ary[b] = tmp;
    }
}
