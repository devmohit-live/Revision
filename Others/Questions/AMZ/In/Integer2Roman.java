public class Integer2Roman {

// LC 12

        ///try saving mapping of the roman thta cuase problem (basicaly ) pow(-10,x) from base romans
    // ex: x=0 => 4(5-1), 9(10-1), 
    // x=1 => 90(100-10), 40(50-10),
    // x=2 => 400(500-100), 900(1000-100)
//      M is highest denomination so no need to go further
    
    int[] value = new int[] {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5,4,1}; 
    String [] romanStore = new String[] {"M", "CM", "D", "CD", "C", "XC", "L", "XL",
                                         "X", "IX", "V","IV","I"}; 
    StringBuilder sb = new StringBuilder();
    
    public String intToRoman(int num) {
        if(num==0){
            return "";
        }
        
        int i=0;//pointer to numerals / value
        while(num>0){
            if((num-value[i])>=0){
                sb.append(romanStore[i]);
                num = num-value[i];
            }
            else{
                i++;
            }
        }
        
        return sb.toString();
        
     }
        
}
