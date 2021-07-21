import java.util.Arrays;
import java.util.ArrayList;

public class Basics {

    public static void printIncreasing(int a, int b) {
        if (a > b)
            return;
        System.out.println(a);
        printIncreasing(a + 1, b);
    }

    public static void printDecreasing(int a, int b) {
        if (a > b)
            return;

        printDecreasing(a + 1, b);
        System.out.println(a);

    }

    public static void printIncreasingDecreasing(int a, int b) {
        if (a > b)
            return;
        System.out.println(a);
        printIncreasingDecreasing(a + 1, b);
        System.out.println(a);

    }

    public static void oddEven(int a, int b) {
        if (a > b) {
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

    // classes approach
    public static int lastIndex2(int[] arr, int data, int idx) {
        if (idx >= arr.length)
            return -1;

        int ans = lastIndex2(arr, data, idx + 1);
        // means we have found the element somewhere
        // also if we do if(ans==-1) return -1, at last when idx==length will return -1
        // and then further exectution will be stopped

        if (ans != -1)
            return ans;

        return arr[idx] == data ? idx : -1;
    }

    private static int findDataRev(int[] arr, int i, int data) {
        if (i == -1)
            return -1;

        if (arr[i] == data)
            return i;

        return findDataRev(arr, i - 1, data);
    }

    public static int[] allIndex(int[] arr, int data, int idx, int count) {
        if (idx == arr.length) {
            int base[] = new int[count];
            return base;
        }

        // to get the size of result array while going up
        if (arr[idx] == data) {
            count++;
        }

        // get the array
        int[] ans = allIndex(arr, data, idx + 1, count);

        // filling array while coming back no need to do count-- as recursion call when
        // destroyed will contains count with lesser value : Stack

        if (arr[idx] == count)
            ans[count - 1] = idx;

    }

    // Return type: ex : ArrayList,String
    // Faith child: return your ans and I will add,or not add myself

    public static ArrayList<String> subsequnce(String str, int idx) {
        if (idx == str.length()) {
            ArrayList<String> base = new ArrayList<String>();
            base.add("");
            return base;
        }

        ArrayList<String> small = subsequnce(str, idx + 1);
        ArrayList<String> myans = new ArrayList<String>(small);
        char ch = str.charAt(idx);
        for (String string : small) {
            myans.add(ch + string);
        }

        return myans;
    }

    // void type or bottom up => ans upr
    // ques is constant= > same, we are using idx to get a specific character
    // instead of substring to short rest of quest
    public static int subsequenceBU(String ques, int idx, String ans) {
        if (idx == ques.length()) {
            System.out.println(ans);
            return 1;
        }

        int count = 0;

        count += subsequenceBU(ques, idx + 1, ans);
        count += subsequenceBU(ques, idx + 1, ques.charAt(idx) + ans);

        return count;

    }

    public static boolean firstAndLastIdx(int[] arr, int data, int idx, int[] ans) {

        if (idx >= arr.length)
            return false;

        if (arr[idx] == data && ans[0] == -1) {
            ans[0] = idx;
        }

        boolean res = firstAndLastIdx(arr, data, idx + 1, ans);

        if (res)
            return true;

        if (arr[idx] == data) {
            ans[1] = idx;
            return true;
        }

        return false;
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
        System.out.println("Find 7's first index: ");
        firstIndex(arr, 7);
        System.out.println("Find 7's last index: ");
        lastIndex(arr, 7);
        System.out.println("Odd than even from 1 to 15");
        oddEven(1, 15);
        System.out.println("Find min in arr");
        minimum(arr);
        System.out.println("Find max in arr");
        maximum(arr);
        int a[] = { 1, 3, 3, 3, 3, 4, 5 };
        System.out.println("AllIndex" + Arrays.toString(allIndex(a, 3, 0, 0)));

        int[] ans = { -1, -1 };
        firstAndLastIdx(arr, 7, 0, ans);
        System.out.println(ans[0] + "\n" + ans[1]);

    }

}