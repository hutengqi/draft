package cn.sincerity.algorithm;

import java.util.Arrays;

/**
 * 排序算法
 * Created by Ht7_Sincerity on 2021/10/17.
 */
public class Sort {
    /**
     * 待排序数组
     */
    public static int[] array = {8, 7, 2, 0, 4, 6, 9, 1, 3, 5};

    public static int[] aux;

    public static void main(String[] args) {
        /*
         * bubbleSort(array);
         * quickSort(array);
         * selectionSort(array);
         * insertionSort(array);
         * aux = new int[array.length];
         * mergeSort(array, 0, array.length - 1);
         */
        shellSort(array);
        System.out.println(Arrays.toString(array));
    }

    /**
     * <h2>快速排序</h2>
     * 快速排序是一种分治的排序算法 <br>
     * 当数组的两个子数组有序的时候，那也整个数组就是有序的。<br>
     * 选择任意一个位置（一般策略选择为每次切分后的低位）作为切分元素，即此元素为将会被排定的元素。<br>
     * 从左到右扫描直到找到大于等于切分元素的元素，从右到左扫描找到小于等于切分元素的元素。交换两个元素的位置。<br>
     * 重复上一步操作，直至左指针 i 左侧元素都不大与切分元素，右指针 j 右侧元素都都不小于切分元素。<br>
     * 当两个指针相遇时，此时 array[j] (即作子数组的最右侧)是切分元素排定的位置，将 array[j] 与 切分元素交换位置即可。<br>
     *
     * @param array 待排序的数组
     */
    public static void quickSort(int[] array) {
        quickSort(array, 0, array.length - 1);
    }

    /**
     * 快速排序
     *
     * @param array 待排序数组
     * @param lo    低位索引值
     * @param hi    高位索引值
     */
    private static void quickSort(int[] array, int lo, int hi) {
        if (hi <= lo) {
            return;
        }
        int j = partition(array, lo, hi);
        quickSort(array, lo, j - 1);
        quickSort(array, j + 1, hi);
    }

    /**
     * 快速排序切分
     *
     * @param array 待排序数组
     * @param lo    低位索引值
     * @param hi    高位索引值
     * @return 切分元素索引值
     */
    private static int partition(int[] array, int lo, int hi) {
        int i = lo, j = hi + 1;
        int v = array[lo];
        while (true) {
            while (less(array[++i], v)) {
                if (i == hi) {
                    break;
                }
            }
            while (less(v, array[--j])) {
                if (j == lo) {
                    break;
                }
            }
            if (i >= j) {
                break;
            }
            swap(array, i, j);
        }
        swap(array, lo, j);
        return j;
    }

    /**
     * <h2>归并排序</h2>
     * 算法思想：归并排序同样是分治思想的体现。<br/>
     * 通过递归将数组分割为单个元素的子数组，而后通过归并子数组得到有序的父数组，最后使整个数组有序<br/>
     * 与快速排序不同点：<br/>
     * 1.归并排序利用辅助数组排序，快速排序利用交换排序。<br/>
     * 2.归并排序从子数组有序开始，快速排序从整个数组相对有序开始。<br/>
     *
     * @param array 待排序数组
     * @param lo    高位索引
     * @param hi    低位索引
     */
    public static void mergeSort(int[] array, int lo, int hi) {
        if (hi <= lo) {
            return;
        }
        int mid = lo + (hi - lo) / 2;
        mergeSort(array, lo, mid);
        mergeSort(array, mid + 1, hi);
        merge(array, lo, mid, hi);
    }

    /**
     * 对有序子数组进行归并
     *
     * @param array 待排序数组
     * @param lo    本次归并低位索引
     * @param mid   本次归并分隔索引
     * @param hi    本次归并高位索引
     */
    private static void merge(int[] array, int lo, int mid, int hi) {
        int i = lo, j = mid + 1;
        if (hi + 1 - lo >= 0) {
            System.arraycopy(array, lo, aux, lo, hi + 1 - lo);
        }
        // 需要先对 i,j 进行检查，如果先使用 i,j 比较会出现数组越界或不符合分治思想的情况。
        for (int k = lo; k <= hi; k++) {
            if (i > mid) {
                array[k] = aux[j++];
            } else if (j > hi) {
                array[k] = aux[i++];
            } else if (less(aux[j], aux[i])) {
                array[k] = aux[j++];
            } else {
                array[k] = aux[i++];
            }
        }
    }

    /**
     * <h2>希尔排序</h2>
     * 针对插入排序的优化
     * 将数组分为每个元素间隔为 h 的子数组，针对每个子数组进行插入排序。
     * 重复以上操作，然后将 h 逐步减小为 1 进行最终的全数组插入排序。
     * 局部进行插入排序是优化插入排序中某些元素需要长距离逐个交换的情况。
     *
     * @param array 待排序的数组
     */
    public static void shellSort(int[] array) {
        int n = array.length;
        int h = 1;
        while (h < n / 3) {
            h = h * 3 + 1;
        }
        while (h >= 1) {
            for (int i = h; i < n; i++) {
                for (int j = i; j >= h && less(array[j], array[j - h]); j -= h) {
                    swap(array, j, j - h);
                }
            }
            h = h / 3;
        }
    }

    /**
     * <h2>插入排序</h2>
     * 算法思想：将一个元素插入到有序数组中合适的位置后，数组依旧是有序的。
     * 每轮将指定位置元素与左侧元素对比，满足条件则交换，重复此操作直至元素插入到合适的位置。
     *
     * @param array 待排序数组
     */
    public static void insertionSort(int[] array) {
        for (int i = 1; i < array.length; i++) {
            for (int j = i; j > 0 && array[j] < array[j - 1]; j--) {
                swap(array, j, j - 1);
            }
        }
    }

    /**
     * <h2>选择排序</h2>
     * 每轮排序找到最小的元素，将元素与剩余数组中的最左边元素交换。
     *
     * @param array 待排序的数据
     */
    public static void selectionSort(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            int min = i;
            for (int j = i + 1; j < array.length; j++) {
                if (less(array[j], array[min])) {
                    min = j;
                }
            }
            swap(array, i, min);
        }
    }

    /**
     * <h2>冒泡排序</h2>
     * 外层循环控制排序所需要的轮数，内层循环控制每轮排序的所需要检查的数组长度。<br/>
     * 每轮排序将该轮最小的数据通过交换放入该轮的最大索引处。
     *
     * @param array 待排序的数组
     */
    public static void bubbleSort(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - 1 - i; j++) {
                if (less(array[j + 1], array[j])) {
                    swap(array, j, j + 1);
                }
            }
        }
    }

    /**
     * 判断是否小于指定值
     *
     * @param v1 待比较值
     * @param v2 指定值
     * @return boolean
     */
    private static boolean less(int v1, int v2) {
        return v1 < v2;
    }

    /**
     * 将指定数组中两个索引处的值进行交换
     *
     * @param array  待排序数组
     * @param index1 第一个索引位置
     * @param index2 第二个索引位置
     */
    private static void swap(int[] array, int index1, int index2) {
        int temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
    }
}
