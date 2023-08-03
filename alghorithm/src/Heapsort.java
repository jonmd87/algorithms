public class Heapsort {
    public void sort(int[] arr) {
        int size =  arr.length;
        for (int root = size / 2 - 1; root >= 0; root--) {
            heapify(arr, root, size);
        }
        for (size -= 1; size >= 0; size--) {
            swap(arr, 0, size);
            heapify(arr, 0, size);
        }
    }

    private void heapify(int[] arr, int root, int size) {
        int left = root * 2 + 1;     // create the index of left child
        int right = root * 2 + 2;        // create the index of right child
        int top = root;              // top of pyramid
        if (left < size && arr[left] > arr[top]) { //compare two numbers, bigger must be on the top
            top = left;
        }
        if (right < size && arr[right] > arr[top]) { //compare two numbers, bigger must be on the top
            top = right;
        }
        if (root != top) { //if value on the top was changed we must check out children
            swap(arr, top, root);
            heapify(arr, top, size);
        }
    }

    private void swap(int[] arr,int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }
}
