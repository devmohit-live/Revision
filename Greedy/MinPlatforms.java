public class MinPlatforms {
    static int findPlatform(int arr[], int dep[], int n) {
        // sorting them will changes the arrival and departure order for the specific
        // train then why?
        // bcz this time we are not thinking according to the train , we are thinking
        // from the perspectives on railway station
        // station is not concernd about the train it is concerned about the time.
        // for a particular platform station is checking at time t wheter a train is
        // arriving on it or not and if there is still 1 train at the platform and
        // another(some train any train doesn't matter it was train 1 or train2 whose
        // departure time was less or more whatever) is trying to stand at same platform
        // we cab't do that we have to create new platofrm
        // if the train leaves the platforms then we need to stop considering those
        // vacant platform so platform--

        Arrays.sort(arr);
        Arrays.sort(dep);

        // initally there should a a platform, and we are standing the fisr train there
        int i = 1, j = 0, platforms = 1, max = 1;

        while (i < n && j < n) {
            if (arr[i] <= dep[j]) {
                platforms++;// on new platofrm
                max = Math.max(platforms, max);
                i++; // look for other arrivals as we have new platform

            } else {
                // can be arrived on same platforms we have to don;t consider th vacant ones
                // 1 platforms is now vacant so currentplatform-=1
                platforms--;
                j++;
            }
        }

        return max;
    }
}
