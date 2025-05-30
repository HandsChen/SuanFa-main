package 剑指offer.数组;

public class No2_二维数组中的查找 {
    public static void main(String[] args) {
        /*int[][] plants = {{2,3,6,8},{4,5,8,9},{5,9,10,12}};
        int target = 8;*/
        int[][] plants = {{1, 3, 5}, {2, 5, 7}};
        int target = 4;
        System.out.println("最后的结果为 = " + findTargetIn2DPlants(plants, target));
    }

    //关键是寻找左下角为标志位
    public static boolean findTargetIn2DPlants(int[][] plants, int target) {
        int rows = plants.length;
        if (rows > 0) {
            int cols = plants[0].length;
            if (cols > 0) {
                //从最后一行和第一行开始遍历
                for (int i = rows - 1; i >= 0; i--) { //从最后一行开始
                    for (int j = 0; j < cols; j++) { //从第一列开始
                        int curFlagNum = plants[i][j]; //当前标志数
                        if (target == curFlagNum) {
                            return true;
                        } else if (target < curFlagNum) { //如果要找的数小于当前标志位处的数，那么应该向上寻找
                            break;
                        }
                    }
                }
            }
        }
        return false;
    }
}
