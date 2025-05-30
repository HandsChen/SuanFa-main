package 排序;

import java.util.Arrays;

public class 归并排序 {

    public static void main(String[] args) {
        System.out.println("args = " + Arrays.toString(mergeSort(new int[]{9, 7, 5, 4, 6}, 0, 4)));
    }

    public static int[] mergeSort(int[] data, int start, int end) {
        if (data == null || data.length == 0) {
            return data; //原封不动返回
        }
        //终止条件 (递归一定要有终止条件！！！)
        if (start == end) {
            return new int[]{data[start]};
        }
        int mid = start + (end - start) / 2;
        int[] leftRes = mergeSort(data, start, mid); //首先排好左边的数组
        int[] rightRes = mergeSort(data, mid + 1, end); //然后排好右边的数组
        return merge(leftRes, rightRes);
    }

    public static int[] merge(int[] leftRes, int[] rightRes) {
        int[] fullRes = new int[leftRes.length + rightRes.length];
        int leftIndex = 0, rightIndex = 0, fullIndex = 0;
        while (leftIndex < leftRes.length && rightIndex < rightRes.length) {
            if (leftRes[leftIndex] <= rightRes[rightIndex]) {
                fullRes[fullIndex++] = leftRes[leftIndex++];
            } else {
                fullRes[fullIndex++] = rightRes[rightIndex++];
            }
        }

        while (leftIndex < leftRes.length) {
            fullRes[fullIndex++] = leftRes[leftIndex++];
        }

        while (rightIndex < rightRes.length) {
            fullRes[fullIndex++] = rightRes[rightIndex++];
        }

        return fullRes;
    }
}
