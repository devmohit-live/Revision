import java.util.ArrayList;
import java.util.List;

// LC : 1324
class PrintWordVertically {
    public List<String> printVertically(String s) {
        String[] words = s.split(" ");
        int n = words.length, k = 0;
        // find word of max length: that decides the answer's length;
        for(String word: words) k = Math.max(k,word.length());
        int[] pointers = new int[k]; // to dave the upcoming character's index
        StringBuilder[] sbs = new StringBuilder[k];
        for(int i=0;i<k;i++) sbs[i] = new StringBuilder();
        List<String> ans = new ArrayList<>();
    
       for(int i=0;i<n;i++){
           String word = words[i];
           int len = word.length();
           
           // for all chars of this word: add the characters to it's correct builder
           for(int j = 0;j<k;j++){
               if(j<len) sbs[j].append(word.charAt(j));
               else sbs[j].append(" "); // for the words which don't have max length
               pointers[j]++;
           }
        }
        
        for(int i=0;i<k;i++){
            for(int j=sbs[i].length()-1; j>=0;j--){
                // remove trailing spaces
                if(sbs[i].charAt(j) != ' ') break;
                else sbs[i].deleteCharAt(j);
            }
            if(sbs[i].length() == 0) sbs[i].append(" "); // words with all spaces 
            ans.add(sbs[i].toString());
        }
        
        return ans;
    }
}
