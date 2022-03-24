import java.util.Arrays;

public class FractionalKnapSack {
    double fractionalKnapsack(int W, Item arr[], int n) {
        Arrays.sort(arr, (a, b) -> {
            int wt1 = a.weight, wt2 = b.weight;
            int val1 = a.value, val2 = b.value;

            double p1 = (val1 * 1.0) / wt1;
            double p2 = (val2 * 1.0) / wt2;
            // return reverse as default in descending
            double diff = p1 - p2;
            // Always try to return 1,-1,0 not (int)(p1-p2)

            if (diff > 0)
                return -1;
            if (diff < 0)
                return 1;
            return 0;

        });

        // for(Item x: arr) System.out.print("(" + x.weight +", "+ x.value+") ");
        // System.out.println();
        double profit = 0;
        for (int i = 0; i < n && W >= 0; i++) {
            if (W - arr[i].weight >= 0) {
                profit += arr[i].value;
                W -= arr[i].weight;
            } else {
                profit += W * ((arr[i].value * 1.0) / arr[i].weight);
                W = 0;
            }
        }

        return profit;
    }
}
