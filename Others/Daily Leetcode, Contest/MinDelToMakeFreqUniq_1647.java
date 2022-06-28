class MinDelToMakeFreqUniq_1647{
     public int minDeletions(String s) {
        int count = 0;
        int[] freq = new int[26];
        for(char ch : s.toCharArray()) freq[ch-'a']++;
        Set<Integer> set  = new HashSet<>();
        for(int i=0;i<26;i++){
            while(freq[i] > 0){
                if(!set.contains(freq[i])){
                    set.add(freq[i]);
                    break;
                }
                freq[i]--;
                count++;
            }
        }
        
        return count;
    }
}