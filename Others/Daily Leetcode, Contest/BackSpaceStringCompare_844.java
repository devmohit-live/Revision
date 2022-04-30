class BackSpaceStringCompare_844{
     
    public boolean backspaceCompare(String s, String t) {
        
        return OnTimeANdConstantSpace(s,t);
    }
    //Using two pointers and maintaing back variable ehich tells how much chars to delte
    // starting pointers from back to be assured that current(this) char will be included
    
    private boolean OnTimeANdConstantSpace(String s, String t){
        int i = s.length()-1, j = t.length()-1, back;
        while(true){
            back = 0; // intialize for s string
            //either it is a # or this char should be deleted by previous occuring hashes
            while(i>=0 && (back>0 || s.charAt(i) == '#')){
                back +=  (s.charAt(i) == '#') ? 1 : -1;
                i--;
            }
            
            back = 0; // intialize for t string
            while(j>=0 && (back>0 || t.charAt(j) == '#')){
                back +=  (t.charAt(j) == '#' )? 1 : -1;
                j--;
            }
            
            if(i>=0 && j>=0 && s.charAt(i) == t.charAt(j)){
                i--;
                j--;
            }else{
                // bcz if it can't be # and we have already deleted the chars , so if it is not matching now 
                // then the strings are not same
                break;
            }
        }
        
        return (i==-1 && j==-1);
    }
    
    //Using Stack: o(n) TIME and O(n) space
    private boolean OnTimeAndSpace(String s, String t){
        Stack<Character> st1 = new Stack<>();
        Stack<Character> st2 = new Stack<>();
        processString(st1,s);
        processString(st2,t);
        StringBuilder sb1 = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        s = createString(sb1,st1);
        t = createString(sb2,st2);
        return s.equals(t);
    }
    
    private void processString(Stack<Character> st, String s){
        for(char c: s.toCharArray()){
            if(st.size()>0 && c=='#') st.pop();
            else if(c!='#') st.push(c);
        }
    }
    
    private String createString(StringBuilder sb, Stack<Character>st){
        while(st.size()>0){
            sb.append(st.pop());
        }
        return sb.reverse().toString();
    }
    
}
}