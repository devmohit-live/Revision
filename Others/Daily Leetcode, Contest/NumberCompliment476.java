class NumberCompliment476{
     if(num == 0) return 1;
        // no of bits in which this number can be represented
        int nBits = (int) Math.floor((Math.log(num) / Math.log(2)) + 1);
        // or Integer.highestOneBit(n) 
        
        // actal compliment
        int comp = (~num);
        // mask : to set the intitial 1's of the complement(which causes problem)(32 - nbiits)
        // ex: num = 5, 101=>3 bits (1<<3) => 1000 -1 =>0111 => 00000....0111
        int mask = (1 << nBits) - 1;
        return (comp & mask);
}