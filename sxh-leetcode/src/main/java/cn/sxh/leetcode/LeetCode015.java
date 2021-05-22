package cn.sxh.leetcode;

import java.util.HashMap;
import java.util.Vector;

public class LeetCode015 {

    public static void main(String[] args) {

    }


    public static Vector<Integer> twoNumber(Vector<Integer> nums, int target) {
        HashMap<Integer,Integer> mapping = new HashMap<>();
        Vector<Integer> result = new Vector<>();
        for (int i = 0; i < nums.size(); i++) {
            mapping.put(nums.get(i),i);
        }
        return result;
    }


}
