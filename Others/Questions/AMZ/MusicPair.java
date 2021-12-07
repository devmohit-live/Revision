public class MusicPair {
    public int numPairsDivisibleBy60(int[] time) {
        // complemetnt , count of complement
        HashMap<Integer, Integer> map = new HashMap<>();
        int ans = 0, n = time.length;
        if (n <= 1)
            return 0;
        // whatever bigger numbber it is compliment i < 60 as we are doing mod
        // my current element after comsideration can pair with someone else as their
        // are no restrictions so it can also act as someone's else compliment

        // % prop: (a+b)%m => (a%m + b%m)%m

        // kind of remainder : somehow eliminate the remainder from sum of a+b: ny finding the compliemtn
        for (int i = 0; i < n; i++) {
            int mytime = time[i] % 60;
            int complement = (60 - mytime) % 60; 
            ans += map.getOrDefault(complement, 0);

            map.put(mytime, map.getOrDefault(mytime, 0) + 1);
        }

        return ans;
    }


    /*
    Basically we need pair of ele such that trie sum(a+b) is divisible by 60 , that is the remainder should be 0
    The problem boils down to the condition of making a pair for two element a,b such that a%60 + b%60 should be zeros: ditributive property of modulos
    so we basically needed two elemets such that their sum of remainder is completely divisible by zeros hence we have done modulos of  both a md b 
    we can say that the sum of remainder of both the numbers are <=60 
    so we need (a%60 + b%60 )%60 == 0,
    for that we need to store the occurance of the remainders we have encounter , I have used hashamp for that :
    so while iterating the array we also calculate the compliment b%60 that is required and it's frequncey (k) will cuase the k pairse to form 
    ex: if a = 40, b can 20,80,140 as b%60 is zeros if these numbers are present in array they all can for a pair with 40
    
    */
}
