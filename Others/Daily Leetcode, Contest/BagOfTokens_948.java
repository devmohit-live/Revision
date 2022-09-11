class BagOfToken_948 {
     public int bagOfTokensScore(int[] tokens, int power) {
        // return usingDequeue(tokens,power);
        return twoPointers(tokens,power);
    }
    
    //using 2 Pointer
    private int twoPointers(int[] tokens, int power){
        //2 pointers
        Arrays.sort(tokens);
        int score = 0, max = 0, n = tokens.length, si = 0, ei = n - 1;
        while(si<=ei){
            //faceup
            if(power >= tokens[si]){
                //enough point to get a score
                power -= tokens[si];
                score++;
                // max = Math.max(score, max);
                si++;
            }else if(power < tokens[si] && score >=1 && si!=ei){
                //not enough power : so get the highest power possible so that we can gain max score
                // in case of last element we will not try to increase power by sacrificing score as out task in not to maximize power but to maximize scre
                score--;
                power+=tokens[ei];
                ei--;
            }else break;
        }
        
        return score;
    } 
    
    //Using deque
    private int usingDequeue(int[] arr, int P){
        Arrays.sort(arr);
        Deque<Integer> dq = new ArrayDeque<>();
        for(int el : arr) dq.addLast(el);
        
        // System.out.println(dq);
        
        int score = 0;
        while(dq.size()>0){
            //facUP
            if(dq.peekFirst() <= P){
                P-=dq.removeFirst();
                score++;
            }else if(score>=1 && dq.size()>1){
                //size should be > 1 : as in case of last element we will not try to increase power by sacrificing score as out task in not to maximize power but to maximize scre
                //faceDown
                P+=dq.removeLast();
                score--;
            }else break;
        }
        return score;
    }
}