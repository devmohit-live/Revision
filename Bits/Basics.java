public class Basics {

    // set the kth bit
    static void offToOn(int n, int k) {
        int num = (1 << k);
        n = (n | num);
        System.out.println(n);
    }

    // unset the kth bit
    static void onToOff(int n, int k) {
        int num = (1 << k);
        num = (~num);
        int s = (n & num);
        System.out.println(s);
    }

    static int countSetBits(int n) {
        int cp = n;
        int count = 0;
        while (cp > 0) {
            count += (cp & 1);
            cp = (cp >> 1);
        }

        return count;
    }

    public static void main(String[] args) {
        int n = 13;
        onToOff(n, 2);
        offToOn(n, 1);
        System.out.println(countSetBits(n));
    }
}
