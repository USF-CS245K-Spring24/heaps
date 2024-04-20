package exercise04_04;

public class MinHeap {
    private int[] heap; // the array to store the heap
    private int maxsize; // the size of the array
    private int size; // the current number of elements in the array

    /**
     * Constructor for the min heap
     * @param max the maximum size of the heap
     */
    public MinHeap(int max) {
        maxsize = max;
        heap = new int[maxsize];
        size = 0;
        heap[0] = Integer.MIN_VALUE;
        // Note: no actual data is stored at heap[0].
        // Assigned MIN_VALUE so that it's easier to bubble up
    }

    /** Return the index of the left child of the element at index pos
     *
     * @param pos the index of the element in the heap array
     * @return the index of the left child
     */
    private int leftChild(int pos) {
        return 2 * pos;
    }

    /** Return the index of the right child
     *
     * @param pos the index of the element in the heap array
     * @return the index of the right child
     */
    private int rightChild(int pos) {
        return 2 * pos + 1;
    }

    /** Return the index of the parent
     *
     * @param pos the index of the element in the heap array
     * @return the index of the parent
     */
    private int parent(int pos) {
        return pos / 2;
    }

    /** Returns true if the node in a given position is a leaf
     *
     * @param pos the index of the element in the heap array
     * @return true if the node is a leaf, false otherwise
     */
    private boolean isLeaf(int pos) {
        return ((pos > size / 2) && (pos <= size));
    }

    /** Swap given elements: one at index pos1, another at index pos2
     *
     * @param pos1 the index of the first element in the heap
     * @param pos2 the index of the second element in the heap
     */
    private void swap(int pos1, int pos2) {
        int tmp;
        tmp = heap[pos1];
        heap[pos1] = heap[pos2];
        heap[pos2] = tmp;
    }

    /** Insert an element into the heap
     *
     * @param elem the element to insert
     */
    public void insert(int elem) {
        size++;
        heap[size] = elem;

        int current = size;
        // FILL IN CODE:
        // Keep swapping current with its parent (and updating current),
        // if the heap[current] is smaller than the parent
        while (heap[current] < heap[parent(current)]) {
            swap(current, parent(current));
            current = parent(current);

        }

    }

    /** Print the array that stores the heap */
    public void print() {
        int i;
        for (i = 1; i <= size; i++)
            System.out.print(heap[i] + " ");
        System.out.println();
    }

    /** Remove minimum element (it is at the top of the minHeap)
     *
     * @return the smallest element in the heap
     */
    public int removeMin() {
        swap(1, size); // swap the end of the heap into the root
        size--;  	   // removed the end of the heap
        // fix the heap property - push down as needed
        if (size != 0)
            pushdown(1);
        return heap[size + 1];
    }

    /** Push the value down the heap if it does not satisfy the heap property
     *
     * @param position the index of the element in the heap
     */
    private void pushdown(int position) {
        int smallestChild;

        while(!isLeaf(position)) {
            smallestChild = leftChild(position);
            if (smallestChild + 1 <= size) { // I have the right child
                    if (heap[smallestChild + 1] < heap[smallestChild])
                        smallestChild = smallestChild + 1;
            }
            // Compare the smallest child with the parent value
            if (heap[position] < heap[smallestChild])
                return;
            swap(position, smallestChild);
            position = smallestChild;
        }

    }

    public void buildFromTheBottomUp(int[] elements) {
        heap = new int[elements.length + 1];
        for (int i = 1; i < elements.length + 1; i++)
            heap[i] = elements[i-1];
        size = elements.length;
        heap[0] = Integer.MIN_VALUE;
        print();
        for (int k = size/2; k > 0; k--) {
          pushdown(k);
         }
    }


    public static void main(String[] args) {
        MinHeap minheap = new MinHeap(11);
        /*minheap.insert(14);
        minheap.insert(16);
        minheap.insert(5);
        minheap.insert(4);
        minheap.insert(0);
        minheap.print();

        System.out.println();
        System.out.println(minheap.removeMin());

         */
        int[] elements = {6, 9, 1, 15, 4, 7, 12, 16, 10, 0};
        System.out.println("Before: ");
        // System.out.println(Arrays.toString(elements));
        minheap.buildFromTheBottomUp(elements);
        System.out.println("After: ");
        minheap.print();

    }

}

