public class RemoveAllAdjacentDuplicatesII_1209 {

    public String removeDuplicates(String s, int k) {
        // return usingPairClasAndStack(s, k);
        return UsingStringBuilderOnly(s, k);
    }

    private usingPairClasAndStack(String s, int k){
          class Pair{
             char ch;
             int freq;
             Pair(char ch, int freq){
                this.ch = ch;
                this.freq = freq;
             }
         }
          for(char ch : s.toCharArray()){
            boolean isLastCharMatched = false;
            if(st.size()>0 && st.peek().ch == ch){
                Pair p = st.pop();
                p.freq++;
                if(p.freq >=k) continue;
                else
                 st.push(new Pair(p.ch,p.freq));
                isLastCharMatched = true;
            }
                                
           if(!isLastCharMatched) st.push(new Pair(ch,1));
            
        }
        
        while(!st.isEmpty()){
            Pair p = st.pop();
            char ch = p.ch;
            int freq = p.freq;
           while(freq-->0) sb.append(ch);
        }
        
        return sb.reverse().toString();
    }

    private UsingStringBuilderOnly(String s, int k){
        StringBuilder sb = new StringBuilder();
        int[] count = new int[s.length()]; //to have freq of last word
        
        for(char ch : s.toCharArray()){
            sb.append(ch);
            int lastIdx = sb.length()-1;
            count[lastIdx] = 1 + ((lastIdx>0 && sb.charAt(lastIdx) == sb.charAt(lastIdx-1) ) ? count[lastIdx-1] : 0);
            if(count[lastIdx] >= k) 
                sb.delete(sb.length()-k,sb.length());
        }
        
        return sb.toString();
    }

}
