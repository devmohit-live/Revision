public class AutoScaling {
    /*
     * If the average utilization < 25%, then an action is instantiated to reduce
     * the number of instances by half if the number of instances is greater than 1.
     * Take the ceiling if the number is not an integer. If the number of instances
     * is 1, take no action.
     * 
     * If 25% s average utilization s 60%, take no action.
     * 
     * If the average utilization > 60%, then an action is instantiated to double
     * the number of instances if the doubled value does not exceed 2* 108. If the
     * number of instances exceeds this limit upon doubling, take no action.
     * 
     * aFTER EVERY SCALING 10S COOLDOWN
     * 
     * // avg utilization per second
     * 
     * averageUtil = [25, 23, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 76, 80]
     * 
     * inst // instance intially
     * 
     * // remember cooldown if(cooldown > n) break rooling up *2 : < 2*08 rolling
     * down /2 : must make be >1 intially
     * 
     */
    public static int autoscaling(int[] arr, int inst) {
        int n = arr.length, i = 0;

        while (i < n) {
            if (arr[i] < 25 && inst > 1) {
                // roll down
                inst = (int) Math.ceil(inst / 2);
                i += 10;
            } else if (arr[i] > 60 && 2 * inst <= 2 * 108) {
                // roll up
                inst = inst * 2;
                i += 10;
            }
            if (i >= n)
                break;

            i++;
        }

        return inst;
    }

    public static void main(String[] args) {
        // int[] arr = { 25, 23, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 76, 80 }; // asn :2
        // int inst = 2;
        int[] arr = { 1, 3, 5, 10, 80 }; // asn :2
        int inst = 1;
        System.out.println(autoscaling(arr, inst));

    }
}
