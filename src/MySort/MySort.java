package MySort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

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
            if (child + 1 < len && arr[child] < arr[child + 1]) {
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

    int[] countSort(int[] sourceArray) {
        int[] arr = Arrays.copyOf(sourceArray, sourceArray.length);
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            if (max < arr[i]) max = arr[i];
            if (min > arr[i]) min = arr[i];
        }
        int[] count = new int[max - min + 1];
        for (int i = 0; i < arr.length; i++) {
            count[arr[i] - min]++;
        }
        int j = 0;
        for (int i = 0; i < count.length; i++) {
            while (count[i] > 0) {
                arr[j] = min + i;
                count[i]--;
                j++;
            }
        }
        return arr;
    }

    int[] bucketSort(int[] sourceArray) {
        int[] arr = Arrays.copyOf(sourceArray, sourceArray.length);
        // 计算最大值与最小值
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for(int i = 0; i < arr.length; i++){
            max = Math.max(max, arr[i]);
            min = Math.min(min, arr[i]);
        }
        // 计算桶的数量
        int bucketNum = (max - min) / arr.length + 1;
        ArrayList<ArrayList<Integer>> bucketArr = new ArrayList<>(bucketNum);
        for(int i = 0; i < bucketNum; i++){
            bucketArr.add(new ArrayList<Integer>());
        }

        // 将每个元素放入桶
        for(int i = 0; i < arr.length; i++){
            int num = (arr[i] - min) / (arr.length);
            bucketArr.get(num).add(arr[i]);
        }

        // 对每个桶进行排序
        for(int i = 0; i < bucketArr.size(); i++){
            Collections.sort(bucketArr.get(i));
        }

        // 将桶中的元素赋值到原序列
        int index = 0;
        for(int i = 0; i < bucketArr.size(); i++){
            for(int j = 0; j < bucketArr.get(i).size(); j++){
                arr[index++] = bucketArr.get(i).get(j);
            }
        }
        return arr;
    }

    int[] radixSort(int[] sourceArray) {
        int[] arr = Arrays.copyOf(sourceArray, sourceArray.length);
        int maxValue = arr[0];
        for (int i = 0; i < arr.length; i++) {
            if (maxValue < arr[i]) {
                maxValue = arr[i];
            }
        }
        int length = String.valueOf(maxValue).length();

        for (int digit = 0; digit < length; digit++) {
            final int radix = 10; // 基数
            int[] count = new int[radix];
            int[] bucket = new int[arr.length];

            // 统计将数组中的数字分配到桶中后，各个桶中的数字个数
            for (int i = 0; i < arr.length; i++) {
                count[getDigit(arr[i], digit)]++;
            }

            // 将各个桶中的数字个数，转化成各个桶中最后一个数字的下标索引
            for (int i = 1; i < radix; i++) {
                count[i] = count[i] + count[i - 1];
            }

            // 将原数组中的数字分配给辅助数组 bucket
            for (int i = 0; i < arr.length; i++) {
                int number = arr[i];
                int d = getDigit(number, digit);
                bucket[count[d] - 1] = number;
                count[d]--;
            }
            arr = Arrays.copyOf(bucket, bucket.length);
        }
        return arr;
    }

    private int getDigit(int x, int d) {
        int a[] = { 1, 10, 100, 1000, 10000, 100000, 1000000, 10000000, 100000000, 1000000000 };
        return ((x / a[d]) % 10);
    }

    private void swap(int[] arr, int i, int j) {
        if (i == j) return;
        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }
}
