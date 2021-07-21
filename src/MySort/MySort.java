package MySort;

import java.util.Arrays;

/**
 * @Author whe
 * @Date 2021/7/21 10:27
 */
public class MySort {

    int[] bubbleSort(int[] sourceArray) {
        int[] arr = Arrays.copyOf(sourceArray, sourceArray.length);
        for (int i = arr.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                }
            }
        }
        return arr;
    }

    int[] selectSort(int[] sourceArray) {
        int[] arr = Arrays.copyOf(sourceArray, sourceArray.length);
        for (int i = 0; i < arr.length; i++) {
            int min = Integer.MAX_VALUE;
            int id = i;
            for (int j = i; j < arr.length; j++) {
                if (min > arr[j]) {
                    min = arr[j];
                    id = j;
                }
            }
            swap(arr, i, id);
        }
        return arr;
    }

    int[] insertSort(int[] sourceArray) {
        int[] arr = Arrays.copyOf(sourceArray, sourceArray.length);
        for (int i = 1; i < arr.length; i++) {
            int temp = arr[i];
            int j = i;
            while (j >= 1 && temp < arr[j - 1]) {
                arr[j] = arr[j - 1];
                j--;
            }
            arr[j] = temp;
        }
        return arr;
    }

    int[] shellSort(int[] sourceArray) {
        int[] arr = Arrays.copyOf(sourceArray, sourceArray.length);
        int len = arr.length;
        int step = len / 2;
        while (step > 0) {
            for (int i = step; i < arr.length; i++) {
                int temp = arr[i];
                int j = i;
                while (j - step >= 0 && temp < arr[j - step]) {
                    arr[j] = arr[j - step];
                    j -= step;
                }
                arr[j] = temp;
            }
            step /= 2;
        }
        return arr;
    }

    int[] mergeSort(int[] sourceArray) {
        int[] arr = Arrays.copyOf(sourceArray, sourceArray.length);
        if (arr.length < 2) {
            return arr;
        }
        int[] left = Arrays.copyOfRange(arr, 0, arr.length / 2);
        int[] right = Arrays.copyOfRange(arr, arr.length / 2, arr.length);
        return merge(mergeSort(left), mergeSort(right));
    }

    private int[] merge(int[] left, int[] right) {
        int[] res = new int[left.length + right.length];
        int i = 0, j = 0, k = 0;
        while (i < left.length && j < right.length) {
            if (left[i] <= right[j]) {
                res[k] = left[i];
                i++;
            } else {
                res[k] = right[j];
                j++;
            }
            k++;
        }
        while (i < left.length) {
            res[k] = left[i];
            i++;
            k++;
        }
        while (j < right.length) {
            res[k] = right[j];
            j++;
            k++;
        }
        return res;
    }

    int[] quickSort(int[] sourceArray) {
        int[] arr = Arrays.copyOf(sourceArray, sourceArray.length);
        quickSort(arr, 0, arr.length - 1);
        return arr;
    }

    private void quickSort(int[] arr, int low, int high){
        if (low < high) {
            int id = findIndex(arr, low, high);
            quickSort(arr, low, id - 1);
            quickSort(arr, id + 1, high);
        }
    }

    private int findIndex(int[] arr, int low, int high) {
        int pivot = arr[low];
        while (low < high) {
            while (low < high && arr[high] >= pivot) {
                high--;
            }
            arr[low] = arr[high];
            while (low < high && arr[low] <= pivot) {
                low++;
            }
            arr[high] = arr[low];
        }
        arr[low] = pivot;
        return low;
    }

    int[] heapSort(int[] sourceArray) {
        int[] arr = Arrays.copyOf(sourceArray, sourceArray.length);
        for (int i = arr.length / 2; i >= 0; i--) {
            adjustHeap(arr, i, arr.length);
        }
        for (int len = arr.length; len > 0; len--) {
            swap(arr, 0, len - 1);
            adjustHeap(arr, 0, len - 1);
        }
        return arr;
    }

    private void adjustHeap(int[] arr, int i, int len) {
        int temp = arr[i];
        int child = 2 * i + 1;//左孩子
        while (child < len) {
            if (arr[child] < arr[child + 1]) {
                child++;//找到最大的孩子
            }
            if (temp >= arr[child]) { //比最大的孩子大，必然大于两个孩子
                break;
            }
            arr[i] = arr[child]; //给父节点赋最大值，交换父节点与最大的孩子，实际操作无需交换，因为如果发生交换，必然是孩子节点变为temp，只需最后赋值即可。
            i = child; //以最大的孩子为父节点，继续向下
            child = 2 * i + 1;
        }
        arr[i] = temp;
    }

    private void swap(int[] arr, int i, int j) {
        if (i == j) return;
        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }
}
