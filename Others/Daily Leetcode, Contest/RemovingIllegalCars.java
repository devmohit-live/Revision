public class RemovingIllegalCars {
    // LC:2167. Minimum Time to Remove All Cars Containing Illegal Goods
    // Weekly contest : 279
    public int minimumTime(String s) {
        int n = s.length(), left = 0, res = n;

        // "left" is the minimum cost to move all the illegal cars from s[0] to s[i] (i
        // + 1 cars in total), this can be done by
        // either removing all of the cars in this range consecutively starting from the
        // left OR
        // removing some of them from the left and some of them in the middle.
        // "res" stands for the minimum cost of moving all the illegal cars, and it is
        // important to understand that it has an upper bound of n,
        // which equals to the total cost if we remove all the cars consecutively from
        // one end.
        // The reason we initialize it with n is that we seek to minimize it with other
        // possible ways of removing cars.
        for (int i = 0; i < n; ++i) {
            // As explained in the original post, each time when s[i] is illegal, we have
            // the option to either
            // remove it in a consecutive fashion starting from the left OR to remove it as
            // if it is picked from the middle.
            left = Math.min(left + (s[i] - '0') * 2, i + 1);
            // Here is the key part. "left + n - 1 - i" means the total cost with the cars
            // from s[i + 1] to s[n - 1] to be removed
            // starting from the right consecutively, and we compare it with the current
            // minimum res.
            // An alternative way to look at it is: imagine if we have maintained a dp
            // array,
            // where dp[i] := cost of removing illegal cars from s[0] to s[i] in the optimal
            // fasion +
            // cost of removing illegal cars from s[i + 1] to s[n - 1] consecutively from
            // the right.
            // As the dp array is filled from 0 to n - 1 using the rules defined above, it
            // covers all possible min cost at each index,
            // while avoids optimizing the costs associated with the right portion of the
            // input array.
            // And then we find the minimum cost in the dp array, which is the answer we are
            // looking for.
            res = Math.min(res, left + n - 1 - i);
        }
        return res;
    }
}
