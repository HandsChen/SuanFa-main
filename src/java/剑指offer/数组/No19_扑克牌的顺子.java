package 剑指offer.数组;

import java.util.*;

public class No19_扑克牌的顺子 {

    public static void main(String[] args) {
        System.out.println("args = " + checkDynasty(new int[]{6, 3, 4, 2, 10}));
    }

    public static boolean checkDynasty(int[] places) {
        int len = places.length;
        int cursor = 0;
        Set<Integer> mySet = new HashSet<>();
        int maxValue = Integer.MIN_VALUE;
        int minValue = Integer.MAX_VALUE;
        for (int place : places) {
            maxValue = Math.max(maxValue, place);
            if (place == 0) {
                cursor++;
            } else {
                minValue = Math.min(minValue, place);
                if (!mySet.add(place)) {
                    return false;
                }
            }
        }
        int plusFactor = len - cursor - 1;
        return Math.abs(minValue + plusFactor - maxValue) <= cursor;
    }
    // 2. max - min < 5
    public boolean checkDynasty2(int[] places) {
        int unknown = 0;
        Arrays.sort(places); // 数组排序
        for(int i = 0; i < 4; i++) {
            if(places[i] == 0) unknown++; // 统计未知朝代数量
            else if(places[i] == places[i + 1]) return false; // 若有重复，提前返回 false
        }
        return places[4] - places[unknown] < 5; // 最大编号朝代 - 最小编号朝代 < 5 则连续
    }
}
