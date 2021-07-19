import java.io.*;
import java.util.*;

//Leetcode : 34
public class FirstAndLastIndex {

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++)
            arr[i] = sc.nextInt();
        int data = sc.nextInt();
        firstAndLastIndex(arr, data);
    }

    public static void firstAndLastIndex(int[] arr, int data) {
        int fi = firstIndex(arr, data);
        int li = lastIndex(arr, data);

        // if(fi==-1){
        // System.out.println("Data is not present in array!");
        // return;
        // }
        // else{
        // System.out.println(fi);
        // System.out.println(li);
        // }
        System.out.println(fi);
        System.out.println(li);
    }

    private static int firstIndex(int[] arr, int data) {
        int si = 0, ei = arr.length - 1;

        while (si <= ei) {
            int mid = si + (ei - si) / 2;

            if (data < arr[mid])
                ei = mid - 1;
            else if (data > arr[mid])
                si = mid + 1;
            else {
                // equal

                // more elements are present to left
                if (mid - 1 >= 0 && arr[mid - 1] == data)
                    ei = mid - 1;

                else
                    return mid;
            }
        }

        return -1;
    }

    private static int lastIndex(int[] arr, int data) {
        int si = 0, ei = arr.length - 1;

        while (si <= ei) {
            int mid = si + (ei - si) / 2;

            if (data < arr[mid])
                ei = mid - 1;
            else if (data > arr[mid])
                si = mid + 1;
            else {
                // equal

                // more elements are present to right
                if (mid   +   1 < arr.length && arr [ mi d+ 1]==d

                    
                else
                    return mid;
            }
        }

        return -1;
    }

}