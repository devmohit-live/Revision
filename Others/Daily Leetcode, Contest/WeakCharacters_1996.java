class {
     
    public int numberOfWeakCharacters(int[][] properties) {
        int count = 0;
        //sort on the basis of attack (in creasing order and defence on decreasing order : )
        // 1 thing attack(is fixed to be increasing)(so we can focus on defence property)
        //2 defence is also decreasing so we can be sure of the roder of traversal 
        // ith is weak if defence of i < max of denfence(later defences)
        
        Arrays.sort(properties, new Comparator<int[]> (){
            @Override
            public int compare(int[] a, int[] b){ 
                if(a[0]==b[0])
                    return b[1] - a[1];
                    // return (a[1]>b[1])? -1: 1;
                return a[0] - b[0]; 
                // return (a[0] < b[0])? -1: 1;  
            }
        });
        int max = Integer.MIN_VALUE;
        for(int i=properties.length -1; i>=0 ;i--){
            if(properties[i][1] < max)
                count++;
            max = Math.max(max, properties[i][1]);
        }
        return count;
    }
}