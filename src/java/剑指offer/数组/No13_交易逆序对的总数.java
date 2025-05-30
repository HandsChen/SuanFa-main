package 剑指offer.数组;


public class No13_交易逆序对的总数 {
    static int count = 0;
    public static void main(String[] args) {
        System.out.println("args = " + reversePairs(new int[]{9, 7, 5, 4, 6}));
    }

    //超时 暴力法，自己写的dp没有任何卵用
    static public int reversePairs(int[] record) {
        mergeSort(record, 0, record.length - 1);
        return count;
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
                //如果左半部分当前元素比右半部分的元素大，那么左半部分剩下的所有元素（因为已经排序好了）都会比当前右半部分的元素大
                count += (leftRes.length - leftIndex);
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
