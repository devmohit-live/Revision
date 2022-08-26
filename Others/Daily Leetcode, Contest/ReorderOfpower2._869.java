public class ReorderOfpower2._869
{

    // limit 10^9 <= 2^30 (in range of int )
    
    
    //     static Set<Integer> set = new HashSet<>();
    //     void precomute(){
    //         for(int i=0;i<31;i++){
    //             set.add((int)(1<<i));
    //         }
    //     }

    public boolean reorderedPowerOf2(int n) {
        // precomute();
        // char[] arr = String.valueOf(n).toCharArray();
        // Arrays.sort(arr);
        // String s = new String(arr);
        // return set.contains(Integer.parseInt(s)); // fails in case of leading zeros : 10 -> 01(now considrerd as 1 and returned true, we have to avoid leading zeros)
        
        char[] a1 = String.valueOf(n).toCharArray();
        Arrays.sort(a1);
        String s1 = new String(a1);
        // System.out.println(s1); 
        //here we are gettinh 01 not 1 and 01 != 1(power of 2 ) hence returned false;
        
        for (int i = 0; i < 31; i++) { // if >10^9 use long(<64)
            char[] a2 = String.valueOf((1 << i)).toCharArray();
            Arrays.sort(a2);
            String s2 = new String(a2);
            if (s1.equals(s2)) return true;
        }
        
        return false;
        
}
