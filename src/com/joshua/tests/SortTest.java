package com.joshua.tests;

import com.joshua.webpage.java.Test;
import net.sf.json.JSONObject;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by bmk on 18-3-20.
 */
public class SortTest {
     public static void main(String[] args) {
//        int[] a = {1,2,3,7,8};
//        int[] b = {4,5,6,9,10};
        int[] arr = {5,8,3,4,9,7};
//        Sort.mergeSort(arr);
//        Sort.basicSort(arr);
//         Sort.bubbleSort(arr);
//         Sort.quickSort(arr);
         Sort.bucketSort(arr);
        Arrays.stream(arr).forEach(System.out::println);
    }

    static void mergeTwoArray(int[] a, int n, int[] b, int m, int[] c) {
        int i,j,k;
        i = j = k = 0;

        while (i<n && j<m){
            if (a[i]<b[j]){
                c[k++] = a[i++];
            } else {
                c[k++] = b[j++];
            }
        }

        while (i<n){
            c[k++] = a[i++];
        }

        while (j<m){
            c[k++] = b[j++];
        }

    }

}
