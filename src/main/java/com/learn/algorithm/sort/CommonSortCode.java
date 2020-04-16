package com.learn.algorithm.sort;

import lombok.extern.slf4j.Slf4j;

/**
 * 常见排序算法
 *
 * @Author hanchen
 * @Date: 2020/4/12
 */
@Slf4j
public class CommonSortCode {

    /**
     * 冒泡排序
     * 从头到尾遍历
     *
     * @param items
     * @param size
     */
    public static void bubbleSort(int[] items, int size) {
        if (size == 1) {
            return;
        }

        for (int i = 0; i < size; i++) {
            boolean change = false;
            for (int j = 0; j < size - i - 1; j++) {
                if (items[j] > items[j + 1]) {
                    int temp = items[j + 1];
                    items[j + 1] = items[j];
                    items[j] = temp;
                    change = true;
                }
            }
            if (!change) {
                break;
            }
        }
    }

    /**
     * 插入排序
     * 从尾到头遍历
     *
     * @param items
     * @param size
     */
    public static void insertionSort(int[] items, int size) {
        if (size == 1) {
            return;
        }

        for (int i = 1; i < size; i++) {
            int temp = items[i];
            int j = i - 1;
            for (; j >= 0; j--) {
                if (items[j] > temp) {
                    items[j + 1] = items[j];
                } else {
                    break;
                }
            }
            items[j + 1] = temp;
        }
    }

    /**
     * 选择排序
     *
     * @param items
     * @param size
     */
    public static void selectionSort(int[] items, int size) {
        if (size == 1) {
            return;
        }

        for (int i = 0; i < size; i++) {
            int min = i;
            boolean change = false;
            for (int j = i + 1; j < size; j++) {
                if (items[j] < items[min]) {
                    min = j;
                    change = true;
                }
            }

            if (change) {
                int temp = items[min];
                items[min] = items[i];
                items[i] = temp;
            }
        }
    }

    /**
     * 归并排序
     *
     * @param items
     * @param size
     */
    public static void mergeSort(int[] items, int size) {
        mergeSortArray(items, 0, size - 1);
    }

    private static void mergeSortArray(int[] items, int p, int r) {
        if (p >= r) {
            return;
        }
        int q = p + (r - p) / 2;
        mergeSortArray(items, p, q);
        mergeSortArray(items, q + 1, r);
        merge(items, p, q, r);
    }

    private static void merge(int[] items, int p, int q, int r) {
        int[] leftArr = new int[q - p + 2];
        int[] rightArr = new int[r - q + 1];

        for (int i = 0; i <= q - p; i++) {
            leftArr[i] = items[p + i];
        }
        leftArr[q - p + 1] = Integer.MAX_VALUE;

        for (int i = 0; i < r - q; i++) {
            rightArr[i] = items[q + 1 + i];
        }
        rightArr[r - q] = Integer.MAX_VALUE;

        int i = 0, j = 0, k = p;

        while (k <= r) {
            if (leftArr[i] <= rightArr[j]) {
                items[k++] = leftArr[i++];
            } else {
                items[k++] = rightArr[j++];
            }
        }
    }

    /**
     * 快速排序
     *
     * @param items
     * @param size
     */
    public static void quickSort(int[] items, int size) {
        quickSortArray(items, 0, size - 1);
    }

    public static void quickSortArray(int[] items, int p, int r) {
        if (p >= r) {
            return;
        }

        int q = partition(items, p, r);
        quickSortArray(items, p, q - 1);
        quickSortArray(items, q + 1, r);
    }

    public static int partition(int[] items, int p, int r) {
        int pivot = items[r];
        int i = p;
        for (int j = p; j < r; j++) {
            if (items[j] < pivot) {
                if (i == j) {
                    i++;
                } else {
                    int temp = items[i];
                    items[i++] = items[j];
                    items[j] = temp;
                }
            }
        }

        int temp = items[r];
        items[r] = items[i];
        items[i] = temp;
        return i;
    }

    /**
     * 桶排序
     *
     * @param items
     * @param bucketSize
     */
    public static void bucketSort(int[] items, int bucketSize) {
        if (items.length < 2) {
            return;
        }
        int max = items[0];
        int min = items[0];

        for (int i = 1; i < items.length; i++) {
            if (items[i] < min) {
                min = items[i];
            }
            if (items[i] > max) {
                max = items[i];
            }
        }

        int bucketCount = (max - min) / bucketSize + 1;
        // 桶编号及存储的数据
        int[][] bucket = new int[bucketCount][bucketSize];
        // 每个桶存储的数据个数
        int[] bucketIndex = new int[bucketCount];

        for (int i = 0; i < items.length; i++) {
            int index = (items[i] - min) / bucketCount;

            // 扩容
            if (bucketIndex[index] == bucket[index].length) {
                // 扩容容量为原来的两倍
                int[] ensureItems = new int[bucket[index].length * 2];
                for (int k = 0; k < bucket[index].length; k++) {
                    ensureItems[k] = bucket[index][k];
                }
                bucket[index] = ensureItems;
            }
            bucket[index][bucketIndex[index]++] = items[i];
        }

        int k = 0;
        for (int i = 0; i < bucketCount; i++) {
            if (bucketIndex[i] == 0) {
                continue;
            }
            // 每个桶数据快排
            quickSort(bucket[i], bucketIndex[i]);
            for (int j = 0; j < bucketIndex[i]; j++) {
                items[k++] = bucket[i][j];
            }
        }
    }

    /**
     * 计数排序
     *
     * @param items
     * @param size
     */
    public static void countingSort(int[] items, int size) {
        if (size < 2) {
            return;
        }

        int max = items[0];
        for (int i = 1; i < size; i++) {
            if (items[i] > max) {
                max = items[i];
            }
        }

        int[] countArr = new int[max + 1];
        for (int i = 0; i < size; i++) {
            countArr[items[i]]++;
        }

        for (int i = 1; i < countArr.length; i++) {
            countArr[i] = countArr[i] + countArr[i - 1];
        }

        // 临时数组
        int[] temp = new int[size];
        for (int i = size - 1; i >= 0; i--) {
            temp[countArr[items[i]] - 1] = items[i];
            countArr[items[i]]--;
        }

        for (int i = 0; i < size; i++) {
            items[i] = temp[i];
        }
    }

    /**
     * 基数排序
     * @param arr
     */
    public static void radixSort(int[] arr) {
        int max = arr[0];
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }

        // 从个位开始，对数组arr按"指数"进行排序
        for (int exp = 1; max / exp > 0; exp *= 10) {
            radixCountingSort(arr, exp);
        }
    }

    public static void radixCountingSort(int[] arr, int exp) {
        if (arr.length <= 1) {
            return;
        }

        // 计算每个元素的个数
        int[] c = new int[10];
        for (int i = 0; i < arr.length; i++) {
            c[(arr[i] / exp) % 10]++;
        }

        // 计算排序后的位置
        for (int i = 1; i < c.length; i++) {
            c[i] += c[i - 1];
        }

        // 临时数组r，存储排序之后的结果
        int[] r = new int[arr.length];
        for (int i = arr.length - 1; i >= 0; i--) {
            r[c[(arr[i] / exp) % 10] - 1] = arr[i];
            c[(arr[i] / exp) % 10]--;
        }

        for (int i = 0; i < arr.length; i++) {
            arr[i] = r[i];
        }
    }

    public static void main(String[] args) {
        int[] items = {6, 2, 3, 7, 8, 4, 5};

//        int[] items = new int[100000];
//        for (int i = 0; i < 100000; i++) {
//            Random random = new Random();
//            items[i] = random.nextInt();
//        }
        long start = System.currentTimeMillis();
        radixSort(items);
        long end = System.currentTimeMillis();
        log.info("result = {}, 耗时 = {}ms", items, end - start);
    }
}
