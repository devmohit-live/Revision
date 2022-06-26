class QRescontructionByHeight_406 {
    
    /*
    Pick out tallest group of people and sort them in a subarray (S). Since there's no other groups of people taller than them, therefore each guy's index will be just as same as his k value.
For 2nd tallest group (and the rest), insert each one of them into (S) by k value. So on and so forth.

//pick up the tallest guy first
        //when insert the next tall guy, just need to insert him into kth position
        //repeat until all people are inserted into list
    
    
    */
    /*
    k is only determined by people with equal or larger height, so makes sense to insert in non-increasing order of height. Because when we insert some person with height h and count k, we know that we have found its correct position relative to people with equal and larger height. And when we later insert other people with equal or smaller height, we know that it will not affect this relative position. So the answer is right after we insert all people.
    */
    public int[][] reconstructQueue(int[][] people) {
        Arrays.sort(people, (a, b) -> a[0] == b[0] ? a[1] - b[1] : b[0] - a[0]);
        List<int[]> list = new LinkedList<>();
        for (int[] p : people) {
            int correct_pos = p[1];
            list.add(correct_pos, p);
        }
        return list.toArray(new int[list.size()][]);
    }
}