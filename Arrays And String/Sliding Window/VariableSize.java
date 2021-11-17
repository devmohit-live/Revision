public class VariableSize {

    // Lengfth of longest subarray with sun = k;

    // Pattern

    public int maxLength(int[] arr, int sum){
        int  n=arr.length, si =0, ei =0,max =-1, ssf =0;

        while(ei<n){
            //operation
            sum += arr[ei];
            // less than the given condition
            if(ssf< sum){
                // keep on increasing the window
                ei++;
            }
            // equals
            else if(ssf == sum){
                // update the ans 
                max = Math.max(max, ei -si +1);
            }
            // greater
            else if(ssf > sum){
                while(si< n && ssf> sum){
                    // remove the opeartion 
                    sum -= arr[si];
                    si++;
                }
                ei++; // remember to do so;
            }

        }
        return max;
    }

    // this approach works only with +ve numbers if array contains negative numbers
    // we may have to use kdane

    // Longest K unique character substring
    public int longestkSubstr(String s, int k) {
        int n = s.length(), si = 0, ei = 0, max = -1;
        int[] freq = new int[26];
        while (ei < n) {
            // operations
            char a = s.charAt(ei);
            freq[a - 'a']++;
            int count = getUniqueCount(freq);

            if (count < k) {
                ei++;
            } else if (count == k) {
                max = Math.max(max, ei - si + 1);
                ei++;
            } else if (count > k) {
                while (si < n && count > k) {
                    char b = s.charAt(si);
                    freq[b - 'a']--;
                    if (freq[b - 'a'] == 0)
                        count--;
                    si++;
                }
                ei++;
            }

        }

        return max;

    }

    // constant time => 26
    private int getUniqueCount(int[] freq) {
        int count = 0;
        for (int el : freq)
            if (el > 0)
                count++;

        return count;

    }

}
