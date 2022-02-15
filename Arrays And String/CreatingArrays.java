class {
    //Creating Arrays of different column size
    public static void main(String[] args) {
// 		for(int[] ar: arrays()) System.out.println(Arrays.toString(ar));
        Arrays.stream(arrays()).forEach(ar->System.out.println(Arrays.toString(ar) ));
	}
	
	public static int[][] arrays() {
        int[][] array = new int[5][];
    
        for (int i=0; i < 5; ++i) {
            array[i] = new int[i+1];
            for (int j=0; j < array[i].length; ++j) {
                array[i][j] = j;
            }
        }
    
        return array;
    }
}