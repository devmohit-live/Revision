class RemoveAllAdjacentDuplicates_1047{
        public String removeDuplicates(String s) {
        
    }
    
    //using Stack and StringBuilder
    private String approach1(String s){
        StringBuilder sb = new StringBuilder();
        Stack<Character> st = new Stack<>();
        for(char ch: s.toCharArray()){
            boolean isLoopExecuted = false;
            while(st.size()>0 && st.peek() == ch){
                st.pop();
                isLoopExecuted = true;
            }
            if(!isLoopExecuted)
                 st.push(ch);
            
        }
        
        while(!st.isEmpty()){
            sb.append(st.pop());
        }
        
        return sb.reverse().toString();
    }
    
}