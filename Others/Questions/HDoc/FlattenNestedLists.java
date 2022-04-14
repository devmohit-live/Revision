import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FlattenNestedLists {
    static List<Integer> ans = new ArrayList<>();

    public static void main(String[] args) {
        List listOfLists = new ArrayList<>();
        listOfLists.add(1);
        listOfLists.add(new ArrayList(Arrays.asList(2, 3)));
        List mid = new ArrayList();
        mid.add(4);
        List smid = new ArrayList();
        smid.add(5);
        smid.add(new ArrayList<>(Arrays.asList(6, 7, 8, 9)));
        smid.add(10);
        mid.add(smid);
        mid.add(new ArrayList(Arrays.asList(11, 12)));
        mid.add(13);
        listOfLists.add(mid);
        listOfLists.add(14);
        // List<Integer> ans = new ArrayList<>();
        // List ans =
        // listOfLists.stream().flatMap(List::stream).collect(Collectors.toList());
        // List flatList = new ArrayList<>();
        // listOfLists.forEach(flatList::addAll);

        // List flatList = listOfLists.stream().collect(ArrayList::new,
        // ArrayList::addAll, ArrayList::addAll);
        // System.out.println(listOfLists);
        // System.out.println(listOfLists.get(0) instanceof Integer);
        flatten(listOfLists, 0);
        System.out.println("Answer is " + ans);
    }

    private static void flatten(List arr, int idx) {
        if (idx == arr.size())
            return;

        // System.out.println(idx + " " + arr);
        try {
            if (arr.get(idx) instanceof Integer) {
                // System.out.println("Integer");
                ans.add((Integer) arr.get(idx));
            } else {
                flatten((List) arr.get(idx), 0);
            }
        } finally {
            flatten(arr, idx + 1);
        }

    }
}
