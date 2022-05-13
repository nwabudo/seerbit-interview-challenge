package com.seerbit.challenge.algorithm;

import java.util.HashSet;

public class AlgorithmSolutions {

//    public static void main(String[] args) {
//        int[] arr = {1, 2, 2, 3, 3, 3, 3, 4, 5};
//        int value = 3;
//        System.out.println(findArrayPairsToSum(arr, value));
//
//        System.out.println(Arrays.toString(findLowAndHighIndex(arr, value)));
//    }

    //    Given an array of integers and a value, determine if there are any two integers in the
    //    array whose sum is equal to the given value.
    public static boolean findArrayPairsToSum(int[] arr, int value){
        HashSet<Integer> s = new HashSet<>();
        for (int i = 0; i < arr.length; ++i) {
            int temp = value - arr[i];

            // checking for condition
            if (s.contains(temp))
                return true;

            s.add(arr[i]);
        }
        return false;
    }

    //   Given a sorted array of integers, return the low and high index of the given key. Return
    //   -1 if not found. The array length can be in the millions with many duplicates.
    public static int[] findLowAndHighIndex(int[] arr, int key){
        int lowIndex = binarySearch(arr, key, true);
        int highIndex = binarySearch(arr, key, false);

        return new int[]{lowIndex, highIndex};
    }

    private static int binarySearch(int[] arr, int key, boolean isAsc){
        int start = 0;
        int end = arr.length - 1;
        int res = -1;

        while(start<=end) {

            int mid = start + (end - start) / 2;

            if(isAsc) {
                if(key < arr[mid]) {
                    end = mid - 1;
                }else if(key > arr[mid]){
                    start = mid + 1;
                }else {
                    res = mid;
                    end = mid - 1;
                }
            }else {
                if(key > arr[mid]) {
                    end = mid - 1;
                }else if(key < arr[mid]) {
                    start = mid + 1;
                }else{
                    res = mid;
                    start = mid + 1;
                }
            }
        }
        return res;
    }
}
