package 剑指offer.数组;

public class No15_获取0到n减1中缺失的数字 {
    public static void main(String[] args) {
        System.out.println("args = " + takeAttendance(new int[]{0, 1, 2, 3, 5}));
//        System.out.println("args = " + takeAttendance(new int[]{0, 1, 2, 3, 4, 5, 6, 8}));
    }

    public static int takeAttendance(int[] records) {
        return search(records, 0, records.length - 1);
    }

    public static int search(int[] records, int low, int high) {
        //终止条件
        if (low > high) {
            return high+1;
        }

        int mid = low + (high - low) / 2;
        if (mid < records[mid]) {
            return search(records, low, mid - 1);
        } else {
            return search(records, mid + 1, high);
        }
    }
}
