package com.joshua.tests;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by bmk on 18-3-26.
 */
public class Sort {
    private static void swap(int[] arr,int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    private static void mergeArray(int[] arr, int start, int mid , int end, int[] c) {
        int i = start;
        int j = mid+1;
        int k = 0;

        while (i<=mid && j<=end){
            if (arr[i]<arr[j]){
                c[k++] = arr[i++];
            } else {
                c[k++] = arr[j++];
            }
        }

        while (i<=mid){
            c[k++] = arr[i++];
        }

        while (j<=end){
            c[k++] = arr[j++];
        }

        for (i = 0; i < k; i++)
            arr[start + i] = c[i];
    }

    private static void mergeSortRecurse(int[] a, int start, int end, int[] temp) {
        if (start < end){
            int mid = (start + end)/2;
            mergeSortRecurse(a,start,mid,temp);
            mergeSortRecurse(a,mid+1,end,temp);
            mergeArray(a,start,mid,end,temp);
        }
    }

    /**
     * 归并排序
     * @param arr
     */
    static void mergeSort(int[] arr){
        int[] temp = new int[arr.length];
        mergeSortRecurse(arr,0,arr.length-1,temp);
    }


    /**
     * 冒泡排序
     * @param arr
     */
    static void bubbleSort(int[] arr){
        int length = arr.length;
        for (int i = 0; i< length - 1; i++){
            for (int j = length-1;j>i;j--){
                if (arr[i]>arr[j]){
                    swap(arr,i,j);
                }
            }
        }
    }

    /**
     * 改进的冒泡排序
     * @param arr
     */
    static void bubbleSort2(int[] arr) {
        int low = 0;
        int high = arr.length - 1;
        int j;
        while (low < high) {
            for (j=low;j<high;j++){
                if (arr[j]>arr[j+1]){
                    swap(arr,j,j++);
                }
            }
            low++;
            for (j=high;j>low;j--){
                if (arr[j]<arr[j-1]){
                    swap(arr,j,j-1);
                }
            }
            high--;
        }
    }

    static void basicSort(int[] arr){
        int length = arr.length;
        for (int i = 0; i<length;i++){
            int m = i;
            for (int j =i+1; j< length; j++){
                if (arr[m]>arr[j]){
                    m = j;
                    break;
                }
            }
            if (m!=i){
                swap(arr, m, i);
            }
        }
    }

    private static int getMiddle(int[] arr, int low, int high){
        int temp = arr[low];
        while (low<high){
            while (low<high && arr[high]>temp){
                high--;
            }
            arr[low] = arr[high];
            while (low<high && arr[low]<temp){
                low++;
            }
            arr[high] = arr[low];
        }
        arr[low] = temp;
        return low;
    }

    private static void _quickSort(int[] arr, int low, int high){
        if (low<high){
            int middle = getMiddle(arr, low,high);
            _quickSort(arr, low, middle-1);
            _quickSort(arr,middle+1, high);
        }
    }

    static void quickSort(int[] arr){
        if (arr.length>0){
            _quickSort(arr,0,arr.length-1);
        }
    }

    // 桶排序
    public static void bucketSort(int arr[]){

        int n = arr.length;

        ArrayList<Integer> arrList[] = new ArrayList [n];
        //把arr中的数均匀的的分布到[0,1)上，每个桶是一个list，存放落在此桶上的元素
        for(int i =0;i<n;i++){
            int temp = arr[i]/n + 1;
            if(null==arrList[temp])
                arrList[temp] = new ArrayList<>();
            // 将元素插入桶中并排序
            insertAndSort(arrList[temp], arr[i]);
        }

        //把各个桶的排序结果合并
        int count = 0;

        for(int i = 0;i<n;i++){
            if(null!=arrList[i]){
                Iterator iter = arrList[i].iterator();
                while(iter.hasNext()){
                    int d = (int)iter.next();
                    arr[count] = d;
                    count++;
                }
            }
        }
    }

    /**
     * 用插入排序对每个桶进行排序
     * @param list
     */
    private static void insertAndSort(ArrayList<Integer> list, int num){
        int i = list.size();
        if(list.size()>0){
            while (i > 0 && num < list.get(i-1)){
                i--;
            }
        }
        list.add(i,num);
    }
}
