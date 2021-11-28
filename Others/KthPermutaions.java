import java.util.ArrayList;

public class KthPermutaions {

    // IDEA: reduce the problem from finding the kth permutaion from k! to finding
    // the permutaion's correct group first according to the first character
    public String getPermutation(int n, int k) {
        StringBuilder sb = new StringBuilder();
        ArrayList<Integer> arr = new ArrayList<>();

        k = k - 1; // 0 based indexing in splitted sets
        // factorial of previos number to find the splitted set size;

        // everytime the no of groups will be n-1 ! : A
        int fact = 1;
        for (int i = 2; i < n; i++)
            fact *= i;


        // set to lookup
        for (int i = 1; i <= n; i++)
            arr.add(i);
       
        while (true) {
            // find the index
            int lookup = k / fact;
            sb.append(arr.get(lookup));
            // remove the element that is being added
            arr.remove(lookup);

            if (arr.size() == 0)
                break;
            // update the k
            k %= fact; // UPDATED SEQ NUMBER WE HAVE TO FIND IN NEW GROUP SIZE
            // size is reduced by 1 so fact=is divided by n(current size of set)
            fact /= arr.size(); // basically calulating the n-1! =>? N! / N => N-1!
        }

        return sb.toString();
    }
}
