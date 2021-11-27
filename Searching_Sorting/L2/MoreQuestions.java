public class MoreQuestions {

    public static void main(String[] args) {
        int arr[] = new int[] { 3, 5, 7, 9, 10, 90, 100, 130, 140, 160, 170 };
        System.out.println(findInInfi(arr, 10));
    }

    public char nextGreatestLetter(char[] arr, char target) {
        int n = arr.length, si = 0, ei = n - 1;
        // wrap around: ex: target = z and no element > z so return a(0)

        // in case of ceil type return si
        // in case of floor types return ei

        while (si <= ei) {
            int mid = si + (ei - si) / 2;
            char ch = arr[mid];
            if (ch > target) {
                // look for more smaller ele
                ei = mid - 1;
            } else {
                si = mid + 1;
            }
        }
        return arr[si % n];
    }

    // https://
    // www.geeksforgeeks.org/find-position-element-sorted-array-infinite-numbers/#:~:text=If%20the%20array%20is%20infinite,bounds%20to%20apply%20binary%20search.&text=Let%20low%20be%20pointing%20to,and%20double%20the%20high%20index.
    // so basically in this question we are first going in reverse order of divide
    // and conquer(binary search) to find the range for searching the element
    // since it is an infinte array: we can't use length;
    public static int findInInfi(int[] nums, int tar) {
        // find the appropriate range to search the element -> log n

        int si = 0, ei = 1, len = ei - si + 1;
        while (tar > nums[ei]) {
            si = ei + 1;
            ei = ei + len * 2;
        }

        // /apply bs
        return bs(nums, si, ei, tar);
    }

    private static int bs(int[] nums, int si, int ei, int tar) {
        while (si <= ei) {
            int mid = si + (ei - si) / 2;
            if (nums[mid] == tar)
                return mid;
            else if (tar > nums[mid])
                si = mid + 1;
            else
                si = mid - 1;
        }
        return -1;
    }

    // binary search in order sorted array: order is not confirmed
    public static int binarySearchOrder(int[] arr, int tar) {
        int n = arr.length, si = 0, ei = n - 1;
        boolean isAsc = arr[0] <= arr[ei];
        while (si <= ei) {
            int mid = si + (ei - si) / 2;
            if (arr[mid] == tar)
                return mid;
            else if (tar > arr[mid]) {
                if (isAsc) {
                    si = mid + 1;
                } else {
                    ei = mid - 1;
                }
            } else {
                if (isAsc) {
                    ei = mid - 1;
                } else {
                    si = mid + 1;
                }
            }
        }
        return -1;
        // not found
    }

    // Leetcode 162,852 exactly same
    public int peakIndexInMountainArray(int[] arr) {
        int n = arr.length, si = 0, ei = n - 1;
        // we have to find the peak(greatest el in between)
        while (si < ei) {
            int mid = si + (ei - si) / 2;

            if (arr[mid] > arr[mid + 1]) { // chunck is in descending order hence greater elemetns would lie on left
                                           // part
                ei = mid; // mid is greater it can be possible ans
            } else {// chunck is in ascending order hence greater elemetns would lie on left part
                si = mid + 1; // bc mid+1 is greater and can be the possible ans
            }
            // when si == ei => both are pointing to the same element (hence peak)
        }

        return si; // return the index

    }

    // Pivot != peak
    // Pivot [5,6,7,8,1,2,3,4] => increasing ->8(pivot)-> increasing from another
    // start point
    // Peak [5,6,7,8,4,3,2] => increasing->8(peak)->decreasing

    // find no of rotations done in the array
    // https://practice.geeksforgeeks.org/problems/rotation4723/1#
    int findKRotation(int arr[], int n) {
        return findPivot(arr) + 1;
    }

    private int findPivot(int[] arr) {
        int n = arr.length, si = 0, ei = n - 1;
        while (si < ei) {
            int mid = si + (ei - si) / 2;
            if (mid + 1 < n && arr[mid] > arr[mid + 1])
                return mid;
            if (mid - 1 >= 0 && arr[mid - 1] > arr[mid])
                return mid - 1;
            else if (arr[mid] <= arr[si]) {
                // bigger ele is towars start
                ei = mid; // mid can be the answer
            } else {
                si = mid + 1;
            }
        }
        return -1; // no pivot found already in sorted no rotation
    }

    // leetcode 33 : search in rotated sorted array(distinct numbers)
    public int search(int[] nums, int target) {
        int n = nums.length;
        int pivotIdx = findPivot(nums);

        if (pivotIdx == -1)
            return bs(nums, 0, n - 1, target);

        if (nums[pivotIdx] == target)
            return pivotIdx;
        // now we are left with 2 sorted arrays in ascending order
        // 0-> pivot idx-1, pivotidx+1->n-1
        int leftpart = bs(nums, 0, pivotIdx - 1, target);
        if (leftpart != -1)
            return leftpart;
        int rightpart = bs(nums, pivotIdx + 1, n - 1, target);
        return rightpart;
    }

// Aggresive Cows : SPOJ
import java.util.*;
import java.lang.*;

class Main
{
	public static void main (String[] args) throws java.lang.Exception
	{
		final Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();
		while(t-->0){
			int n = sc.nextInt();
			int cows = sc.nextInt();
			int[] arr = new int[n];
			for(int i=0;i<n;i++){
				arr[i] = sc.nextInt();
			}
			System.out.println(getMaxMinDistance(arr,cows));
		}
		
	}
	
	private static int getMaxMinDistance(int[]arr, int cows){
		int n = arr.length;
		Arrays.sort(arr);
		int si = 1, ei = arr[n-1]-arr[0];
		int ans = -1;
		while(si<=ei){
			int mid = si + (ei-si)/2;
			
			if(isValidMinDistance(arr,mid,cows)){
				ans = mid;
				// try for others greater values of minDtance
				si = mid +1;
				
			}else{
				ei = mid - 1 ;// we have to chekc for lesser min distance try smaller values
				
			}
			
		}
		
		return ans;
		
	}
	
	
	
	// sonwhow we got the min distance and we hacve to chek wheather we are able to place all the given cows
	private static boolean isValidMinDistance(int[] arr, int minDist,int cows){
		int n = arr.length;
		int cowsPlaced = 1; // we have placed this cow at 0th postiton  
		int placedAt = arr[0];
		
		for(int i=1;i<n;i++){
			if(arr[i] - placedAt >= minDist){
				cowsPlaced++;
				placedAt = arr[i];
			}
			
		}
		
		if(cowsPlaced >= cows) return true;
		
		return false;
		
	}
}

}
