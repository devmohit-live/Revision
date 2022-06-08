
    class RemovePlaindromicSubseq_1332 {
    //The string only contains a,b
    // at at most 2 steps we can make plaindromic subseq(as it is subseq):
    // ex: aaaabbbbb => first all a then all b
    // aaaabbbbbaaaabbbbaa => first all a(as palindromic subseq not substring) then all a
    // if string is already palindromic : "aaaa","bbbb","abab" "aabb" we can delete this palindromic in just 1 step
    
    public int removePalindromeSub(String s) {
        if(s.equals(new StringBuilder(s).reverse().toString())) return 1;
        return 2;
    }

    //lps is failing in this case
    // Case: "ababb"
    //op : 3
    //expected : 2
    
}