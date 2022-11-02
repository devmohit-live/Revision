public class MnumumGeneticMutation_433 {
    //Same as word ladder
     public int minMutation(String start, String end, String[] bank) {
        char[] possible = {'A','C','G','T'};
        Set<String> set = new HashSet<>();
        for(String s: bank) set.add(s);
        if(start.length() != end.length()) return -1;
        if(!set.contains(end)) return -1;
        //set should contain all the non visited string only
       
        //bfs : without cycle detection
        LinkedList<String> q = new LinkedList<>();
        if(set.contains(start)) set.remove(start); //visited mark
        q.addLast(start);
        int lv = 0;
        while(!q.isEmpty()){
            int size = q.size();
            while(size-->0){
                String rm  = q.removeFirst();
                if(rm.equals(end)) return lv;
                                        
                //try changing all the chars in strign with all the possible chars
                char[] arr = rm.toCharArray(); //for easy modification
                for(int i=0;i<arr.length;i++){
                    for(char ch : possible){
                        char backcup = arr[i];
                        arr[i] = ch;
                        String nbr = new String(arr);
                        if(set.contains(nbr)){
                            q.addLast(nbr);
                            set.remove(nbr);
                        }
                        arr[i] = backcup; //revert curr changes for next changes
                    }
                }
            }
            lv++;
        }
        
        
        return -1;
    }
}
