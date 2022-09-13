public class LargestPalidromicNumber_2384 {

    //Clean Code: 
     public String largestPalindromic(String s) {
        int n = s.length();
        char[] arr = s.toCharArray();
        int[] freq = new int[10];
        for(char c: arr) freq[c-'0']++;
        LinkedHashSet<Integer> set = new LinkedHashSet<>();
        StringBuilder sb = new StringBuilder();
        int mid = -1;
        
        //trying with largest
        for(int i=9;i>=0;i--){
            while(freq[i]>=2){
                sb.append(i);
                freq[i]-=2; //left and right end of palindrome
            }
            
            if(freq[i] == 1){
                // can be placed in middle 
                mid = Math.max(mid, i);
            }
        }
        
        if(sb.toString().startsWith("0")) sb = new StringBuilder(); // only mid part will be there
        
        String ans = sb.toString() + (mid==-1 ? "" : mid) + sb.reverse().toString();
        
        // check if all are 0
        if( ans.chars().allMatch(x->x=='0') ) return "0";
        
        return ans;
        
    }

    public String largestPalindromic_(String s) {
        int n = s.length();
        int[] arr = s.toCharArray();
        int n = num.length();
        int[] freq = new int[10];
        char[] arr = num.toCharArray();
        // Arrays.sort(arr);

        Map<Boolean, List<Integer>> map = new HashMap<>();
        map.put(true, new ArrayList<Integer>()); // freq > 2 numbers : can be used as extreme ends
        map.put(false, new ArrayList<Integer>()); // 1 freq numbers

        StringBuilder firstHalf = new StringBuilder(), secondHalf = new StringBuilder();
        for (int i = 0; i < n; i++) {
            char ch = arr[i];
            int d = ch - '0';
            freq[d]++;
        }

        for (int i = 0; i < 10; i++) {
            int f = freq[i];
            if (f >= 2) {
                while (f >= 2) {
                    map.get(true).add(i);
                    map.get(true).add(i);
                    f -= 2;
                }
            }

            if (f > 0)
                map.get(false).add(i);
        }

        // System.out.println(Arrays.toString(freq));
        // System.out.println(map.get(true));
        // System.out.println(map.get(false));

        List<Integer> list = map.get(true);
        String maxOddFreqNum = map.get(false).isEmpty() ? "" : "" + map.get(false).get(map.get(false).size() - 1);
        if (list.stream().allMatch(x -> x == 0))
            return maxOddFreqNum == "" ? "0" : maxOddFreqNum;

        while (list.size() > 0) {
            int c = list.remove(list.size() - 1);
            if (list.size() == 0)
                break;
            while (freq[c] >= 2) {
                firstHalf.append(c);
                secondHalf.append(c);
                freq[c] -= 2;
            }
        }
        String ans = firstHalf.toString() + maxOddFreqNum + secondHalf.reverse().toString();
        if (ans.chars().allMatch(x -> x == '0'))
            return "0";
        return ans;
    }
}
