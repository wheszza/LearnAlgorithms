package MySort;

import java.util.Arrays;

/**
 * @Author whe
 * @Date 2021/7/21 10:27
 */
public class Test {
    public static void main(String[] args) {
        MySort test = new MySort();
        int[] arr = {1, 3, 2, 4};

        System.out.println(Arrays.toString(test.bubbleSort(arr)));

        System.out.println(Arrays.toString(test.selectSort(arr)));

        System.out.println(Arrays.toString(test.insertSort(arr)));

        System.out.println(Arrays.toString(test.shellSort(arr)));

        System.out.println(Arrays.toString(test.mergeSort(arr)));

        System.out.println(Arrays.toString(test.quickSort(arr)));

        System.out.println(Arrays.toString(test.heapSort(arr)));

        System.out.println(Arrays.toString(test.countSort(arr)));

        System.out.println(Arrays.toString(test.radixSort(arr)));

        System.out.println(Arrays.toString(arr));
    }
}
