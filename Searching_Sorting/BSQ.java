public class BSQ {
//Painters Parttio's Prolem : https://www.interviewbit.com/problems/painters-partition-problem/
 private final int MOD = 10000003;

    public int paint(int A, int B, int[] C) {
        int n = C.length, max = -1, sum = 0;
        for(int el : C){
            sum += el;
            max = Math.max(max,el);
        }

        long si = max, ei = sum, mid = 0;
        while(si<ei){
            mid = si + (ei-si)/2;
            if(isValid(C,mid,A)) ei = mid;
            else si = mid+1;
        }
        long ans = (ei%MOD *B%MOD);
        return (int)(ans%MOD);
    }


    private boolean isValid(int[] arr, long workPerPerson, int painters){
        int c = 1, s = 0;

        for(int el : arr){
            s+=el;
            if(s>workPerPerson){
                s = el;
                c++;
            }
        }

        return c<=painters;
    }
}
