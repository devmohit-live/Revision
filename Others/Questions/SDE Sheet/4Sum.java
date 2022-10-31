public class 4Sum {
    public List<List<Integer>> fourSum(int[] arr, int target) {
        final int N = arr.length;
        List<List<Integer>> ans = new ArrayList<>();
        if(N <4) return  ans;
        
        Arrays.sort(arr);
        
        for(int i=0;i<N;i++){
            for(int j=i+1;j<N;j++){
                long newTarget = target - ((long)arr[i] + arr[j]);
                int si = j+1, ei = N - 1;
                while(si<ei){
                    long sum = 0l + arr[si] + arr[ei];
                    if(sum > newTarget) ei--;
                    else if(sum < newTarget) si++;
                    else{
                        ans.add(Arrays.asList(arr[i], arr[j], arr[si], arr[ei]));
                        si++;
                        ei--;
                        int idx = ans.size()-1;
                        while(si<ei && ans.get(idx).get(2) == arr[si]) si++;
                        while(si<ei && ans.get(idx).get(3) == arr[ei]) ei--;
                    }
                }
                
                while(j+1<N && arr[j+1] == arr[j]) j++;
            }
            while(i+1<N && arr[i+1] == arr[i]) i++;

        }
        
        return ans;
    }
}
