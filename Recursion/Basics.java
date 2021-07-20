public class Basics {

    public static void printIncreasing(int a, int b) {
        if (a == b)
            return;
        System.out.println(a);
        printIncreasing(a + 1, b);
    }

    public static void printDecreasing(int a, int b) {
        if (a == b)
            return;

        printDecreasing(a + 1, b);
        System.out.println(a);

    }

    public static void printIncreasingDecreasing(int a, int b) {
        if (a == b)
            return;
        System.out.println(a);
        printIncreasingDecreasing(a + 1, b);
        System.out.println(a);

    }

    public static void oddEven(int a, int b) {
        if (a == b + 1) {
            return;
        }
        if (a % 2 == 1) {
            // odd -> first(all increasing)
            System.out.println(a);
        }
        oddEven(a + 1, b);
        if (a % 2 == 0) {
            System.out.println(a);
        }
    }

    public static void factorial(int n) {
        System.out.println(fact(n));
    }

    private static int fact(int n) {
        if (n == 0)
            return 1;
        return n * fact(n - 1);
    }

    public static int power(int a, int b) {
        if (b == 1)
            return a;
        return a * power(a, b - 1);
    }

    // O(logn)
    public static int powerBtr(int a, int b) {
        if (b == 0)
            return 1;
        if (b % 2 == 1)
            return a * powerBtr(a * a, (b - 1 / 2));
        return powerBtr(a * a, b / 2);
    }

    public static void printArray(int[] arr) {
        printArr(arr, 0);
    }

    static void printArr(int[] arr, int i) {
        if (i == arr.length)
            return;
        System.out.println(arr[i]);
        printArr(arr, i + 1);
    }

    public static void printArrayReverse(int[] arr) {
        printArrRev(arr, 0);
    }

    static void printArrRev(int[] arr, int i) {
        if (i == arr.length)
            return;

        printArrRev(arr, i + 1);

        System.out.println(arr[i]);
    }

    public static void maximum(int[] arr) {
        System.out.println(findMax(arr, 0));
    }

    public static void minimum(int[] arr) {
        System.out.println(findMin(arr, 0));
    }

    private static int findMax(int[] arr, int i) {
        if (i == arr.length)
            return -(int) 1e9;
        return Math.max(arr[i], findMax(arr, i + 1));
    }

    private static int findMin(int[] arr, int i) {
        if (i == arr.length)
            return (int) 1e9;
        return Math.min(arr[i], findMin(arr, i + 1));
    }

    public static void find(int[] arr, int data) {
        System.out.println(findData(arr, 0, data));
    }

    private static int findData(int[] arr, int i, int data) {
        if (i == arr.length)
            return -1;
        if (arr[i] == data)
            return i;
        return findData(arr, i + 1, data);
    }

    public static void firstIndex(int[] arr, int data) {
        System.out.println(findData(arr, 0, data));
    }

    public static void lastIndex(int[] arr, int data) {
        System.out.println(findDataRev(arr, arr.length - 1, data));
    }

    private static int findDataRev(int[] arr, int i, int data) {
        if (i == -1)
            return -1;

        if (arr[i] == data)
            return i;

        return findDataRev(arr, i - 1, data);
    }

    public static void main(String[] args) {
        int[] arr = new int[] { 1, 2, 3, 4, 5, 6, 7, 7, 7, 8, 9, 10 };
        System.out.println("Print Array: ");
        printArray(arr);
        System.out.println("Print Array Reverse: ");
        printArrayReverse(arr);
        System.out.println("Print Increasing: ");
        printIncreasing(1, 8);
        System.out.println("Print Decreasing: ");
        printDecreasing(1, 8);
        System.out.println("Find 5: ");
        find(arr, 5);
        System.out.println("Find 7'd first index: ");
        firstIndex(arr, 7);
        System.out.println("Find 7'd last index: ");
        lastIndex(arr, 7);
        System.out.println("Odd than even from 1 to 15");
        oddEven(1, 15);
        System.out.println("Find min in arr");
        minimum(arr);
        System.out.println("Find max in arr");
        maximum(arr);
    }

}