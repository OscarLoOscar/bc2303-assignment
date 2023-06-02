package com.codewave.project.projectcryptocoingecko.infra.algo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SubSequence {

  public static void main(String[] args) {
    // Test findSubsequence - int array
    int[] nums = new int[] { 2, 1, 9, 10,4};
    Arrays.sort(nums);
    List<List<Integer>> integers = new ArrayList<>();
    findSubSeq(nums, new ArrayList<>(), integers);
    System.out.println(integers);

    // Test findSubsequence - String
    String test = "abc";
    List<String> strings = new ArrayList<>();
    findSubSeq(test, "", strings);
    System.out.println(strings);
  }

  public static void findSubSeq(String str, String ans, List<String> results) {
    if (str.length() == 0) {
      if (ans.length() != 0) // skip empty subsequence
        results.add(ans);
      return;
    }
    findSubSeq(str.substring(1), ans + str.charAt(0), results);
    findSubSeq(str.substring(1), ans, results);
  }

  public static void findSubSeq(int[] arr, List<Integer> ans, List<List<Integer>> results) {
    if (arr.length == 0) {
      if (ans.size() != 0) // skip empty subsequence
        results.add(ans);
      return;
    }
    int[] copiedArray = Arrays.copyOfRange(arr, 1, arr.length);

    List<Integer> temp = new ArrayList<>(ans);
    temp.add(arr[0]);
    findSubSeq(copiedArray, temp, results);

    List<Integer> temp2 = new ArrayList<>(ans);
    findSubSeq(copiedArray, temp2, results);
  }

}
