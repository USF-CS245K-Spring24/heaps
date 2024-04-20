package exercise04_09;

import java.util.Arrays;

public class HeapSort {

    private static class MaxHeap {
        private int[] heap; // the array to store the heap
        private int maxsize; // the size of the array
        private int size; // the current number of elements in the array

        /**
         * Constructor for the max heap - builds a max heap from the bottom up
         *
         * @param array of keys
         */
        public MaxHeap(int[] array) {
            maxsize = array.length;
            size = array.length;
            heap = array;
            buildFromBottomUp();

        }

        public void buildFromBottomUp() {
            // fix the heap from the bottom up
            // Go over internal nodes (non-leaves) from right to left in the heap array
            for (int root = (size - 2) / 2; root >= 0; root--) {
                pushdown(root);
            }
        }

        /**
         * Return the index of the left child of the element at index pos
         *
         * @param pos the index of the element in the heap array
         * @return the index of the left child
         */
        private int leftChild(int pos) {
            return 2 * pos + 1;
        }

        /**
         * Return the index of the right child
         *
         * @param pos the index of the element in the heap array
         * @return the index of the right child
         */
        private int rightChild(int pos) {
            return 2 * pos + 2;
        }

        /**
         * Return the index of the parent
         *
         * @param pos the index of the element in the heap array
         * @return the index of the parent
         */
        private int parent(int pos) {
            return (pos-1) / 2;
        }

        /**
         * Returns true if the node in a given position is a leaf
         *
         * @param pos the index of the element in the heap array
         * @return true if the node is a leaf, false otherwise
         */
        private boolean isLeaf(int pos) {
            return ((pos > (size - 2) / 2) && (pos < size));
        }

        /**
         * Swap given elements: one at index pos1, another at index pos2
         *
         * @param pos1 the index of the first element in the heap
         * @param pos2 the index of the second element in the heap
         */
        private void swap(int pos1, int pos2) {
            int tmp = heap[pos1];
            heap[pos1] = heap[pos2];
            heap[pos2] = tmp;
        }


        /**
         * Print the array that stores the heap
         */
        public void print() {
            for (int i = 0; i < size; i++)
                System.out.print(heap[i] + " ");
            System.out.println();
        }

        /**
         * Remove the largest element (it is at the top of the maxHeap)
         *
         * @return the largest element in the max heap
         */
        public int removeMax() {
            swap(0, size - 1); // swap the end of the heap into the root
            size--;       // removed the end of the heap
            // fix the heap property - push down as needed
            if (size != 0)
                pushdown(0);
            return heap[size];
        }

        /**
         * Push the value down the heap if it does not satisfy the heap property
         *
         * @param position the index of the element in the heap
         */
        private void pushdown(int position) {
            int largestChild;
            while (!isLeaf(position)) {
                largestChild = leftChild(position);
                if (largestChild >= size)
                    return;
                if (largestChild + 1 <  size) {
                    if (heap[largestChild + 1] > heap[largestChild]) {
                        largestChild = largestChild + 1; // the largest child is the right one
                    }
                }
                if (heap[position] > heap[largestChild])
                    return;
                swap(position, largestChild);
                position = largestChild;
            }
        }


    }
    /** Sort the given array using heap sort
     *
     * @param arr array of integers
     */
    public static void sort(int[] arr) {
        // Create a max heap
        // Repeatedly call removeMax
        // Copy elements from the heap array back to arr
        MaxHeap maxHeap = new MaxHeap(arr);
        for (int k = 0; k< arr.length; k++) {
            maxHeap.removeMax();
        }
    }

    public static void main(String[] args) {
        int[] arr = {7, 4, 1, 12, 15, 8, 3, 9, 5, 17, 25, 2, 0, 10, 6};
        HeapSort.sort(arr);
        System.out.println(Arrays.toString(arr)); // Expected: [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 12, 15, 17, 25]
    }
}
