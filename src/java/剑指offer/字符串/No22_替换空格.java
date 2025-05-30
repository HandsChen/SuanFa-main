package 剑指offer.字符串;

public class No22_替换空格 {
    public static void main(String[] args) {
        System.out.println("args = " + pathEncryption("a.aef.qerf.bb"));
    }

    public static String pathEncryption(String path) {
        if (null == path){
            return null;
        }
        return path.replace('.', ' ');
    }
}
