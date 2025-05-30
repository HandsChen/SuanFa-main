package 剑指offer.数组;

public class No1_数组中重复的数字 {
    public static void main(String[] args) {
        int[] documents = {2, 5, 3, 0, 5, 0};
        System.out.println("document's id= " + findRepeatDocument(documents));
    }

    public static int findRepeatDocument(int[] documents) {
        int[] tmp = new int[documents.length];
        for (int document : documents) {
            if(tmp[document] > 0) {
                 return document;
            }else {
                tmp[document] ++;
            }
        }
        return -1;
    }
}
