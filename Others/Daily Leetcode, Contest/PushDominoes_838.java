class PushDominoes_838 {
    
    {
//         distRight[i] = distance from the closest R to its left
// distLeft[i] = distance from the closest L to its right

// Then, we know that for a given '.', it is equals to whichever is closer, i.e. if distLeft[2] = 2 and distRight[2] = 3, then dominoes.charAt(2) == 'L'. One exception to this is when either distRight[i] or distLeft[i] equals to 0. That means that there is no closest R or L, in that case, we let '.' to be whichever that is not 0.
    }
    
    
    public String pushDominoes(String dominoes) {
        char[] arr = dominoes.toCharArray();
        int[] distRight = new int[arr.length];
        int[] distLeft  = new int[arr.length];

        for (int i = 0; i < arr.length - 1; i++){
            if (arr[i + 1] != '.') continue;
            if (distRight[i] != 0 || arr[i] == 'R') distRight[i + 1] = distRight[i] + 1;
        }

        for (int i = arr.length - 1; i > 0; i--){
            if (arr[i - 1] != '.') continue;
            if (distLeft[i] != 0 || arr[i] == 'L') distLeft[i - 1] = distLeft[i] + 1;
        }

        for (int i = 0; i < arr.length; i++){
            if (arr[i] != '.' || distRight[i] == distLeft[i]) continue;
            if (distRight[i] == 0) {arr[i] = 'L'; continue;}
            if (distLeft[i]  == 0) {arr[i] = 'R'; continue;}
            arr[i] = distRight[i] < distLeft[i]? 'R' : 'L';
        }

        return new String(arr);
    }
}