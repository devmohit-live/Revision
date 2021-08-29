import java.util.Arrays;

public class Base {

    static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    static void display(int[] arr) {
        System.out.println(Arrays.toString(arr));
    }

    static void sort01(int[] arr) {
        int zero = -1, one = 0, n = arr.length;
        ;

        while (one < n) {
            if (arr[one] == 0) {
                zero++; // go to first element of 1's area
                swap(arr, zero, one);
            }
            one++;
        }
        display(arr);
    }

    static void sort012(int[] arr) {
        int zero = -1, one = 0, n = arr.length, two = n - 1;

        while (one <= two) {
            if (arr[one] == 0) {
                zero++;
                swap(arr, zero, one);
                // ones area is shifted too
                one++;
            } else if (arr[one] == 2) {
                // can't increment one/itr as we dont know with what element we have swapped 2
                // with
                swap(arr, one, two);
                two--;
            } else {
                // one++;
                one++;
            }
        }
        display(arr);
    }

    static int[] mergeTwoSortedArrays(int[] a, int[] b) {
        int[] c = new int[a.length + b.length];
        int i = 0, j = 0, itr = 0;
        ;
        while (i < a.length && j < b.length) {
            if (a[i] <= b[j])
                c[itr] = a[i++];
            else
                c[itr] = b[j++];
            itr++;
        }

        while (i < a.length) {
            c[itr++] = a[i++];
        }
        while (j < b.length) {
            c[itr++] = b[j++];
        }
        display(c);
        return c;
    }

    // partiton over a given data that may or may not be present in array
    // here th eproblem is if the data is present in array then it will not be at
    // it's coreect postion as it should be in sorted array
    static void partitionOverData(int[] arr, int pivot) {
        int p1 = -1, undef = 0, n = arr.length;
        while (undef < n) {
            if (arr[undef] <= pivot) {
                p1++;
                swap(arr, undef, p1);
                undef++;
            } else {
                undef++;
            }
        }
        System.out.println(Arrays.toString(arr));
    }

    // using two pointer to return thr pivot index => the place where the partion is
    // done
    static int partitionOverData2(int[] arr, int pivot) {
        int n = arr.length, p = n - 1, itr = 0;
        while (itr <= p) {
            if (arr[itr] <= pivot)
                itr++;
            else
                swap(arr, p--, itr);
        }
        System.out.println(Arrays.toString(arr));
        return p;
    }

    static void partitionOverPivot(int[] arr, int pivotIdx) {

        // we have also to fix the pivot to its correct place -> just put that pivot
        // elemtn at last postion
        // just to make the regions distinguihable => fix the pivot
        int pivot = arr[pivotIdx], n = arr.length;
        int p = -1, undef = 0, li = n - 1;

        // fixing the pivot so that we can put it at it's correct pos
        swap(arr, pivotIdx, n - 1);

        // < li bcz pivot is at li and is fixed
        while (undef < li) {
            if (arr[undef] <= pivot) {
                p++;
                swap(arr, undef, p);
            }
            undef++;
        }

        // putting pivot at it's correct pos

        swap(arr, p + 1, li); // we can also do while(undef<=li) then we don't need to swap it explicitly

        System.out.println(Arrays.toString(arr));
    }

    public static void main(String[] args) {
        // int[] arr = { 0, 0, 0, 1, 0, 1, 0, 1, 0, 1, 1, 1, 1, 0, 0, 1 };
        // int[] arr2 = { 0, 0, 2, 2, 2, 0, 1, 2, 0, 1, 2, 2, 2, 0, 1, 0, 1, 0, 1, 1, 1,
        // 1, 0, 0, 1, 2, 0 };
        // sort01(arr);
        // sort012(arr2);
        // mergeTwoSortedArrays(arr, arr2);

        int[] ar = { 56, -8, -12, -52, -89, -1, 98, 26, 21, 59, -100, -55, 0, 0, 0, 26, 2, 3, 4 };
        int[] arSorted = { -3, -2, -1, 0, 1, 2, 3, 4, 5 };

        // Bubble bub = new Bubble();
        // bub.sort(ar.clone());
        // bub.sort_opti(arSorted.clone());

        // Selection sel = new Selection();
        // sel.sort(ar.clone());

        // Insertion ins = new Insertion();
        // ins.sort(ar.clone());

        // partitionOverData(ar.clone(), 7);
        // partitionOverData(ar.clone(), 4);
        // System.out.println(
        // "Partiioning over data " + 7 + " partition is done at index " +
        // partitionOverData2(ar.clone(), 7));
        // System.out.println(
        // "Partiioning over data " + 4 + " partition is done at index " +
        // partitionOverData2(ar.clone(), 4));

        // System.out.println("Patitioning over " + ar[3] + " ");
        // partitionOverPivot(ar.clone(), 3);
        // System.out.println("Patitioning over " + ar[ar.length - 1] + " ");
        // partitionOverPivot(ar.clone(), ar.length - 1);
        // System.out.println("Patitioning over " + ar[5] + " ");
        // partitionOverPivot(ar.clone(), 5);
        // System.out.println("Patitioning over " + arSorted[5] + " ");
        // partitionOverPivot(arSorted.clone(), 5);

        // QuickSort quick = new QuickSort();
        // quick.sort(ar.clone());
        // quick.sort(arSorted.clone());

        Questions ques = new Questions();
        int kthLargest[] = { -12, 2, 7, 4, 34, 23, 0, 1, -1, -50, 16, 23, 7, 4, 2, 3 };
        int k = 4;
        System.out.printf("%d largest element in an array is %d", k, ques.quickSelect(kthLargest, k));

    }
}
