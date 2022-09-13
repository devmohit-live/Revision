import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Basic {
    // print all even numbers
    public static void main(String[] args) {
        int[] numbers = { 10, 9, 13, 4, -2, -8, 1, 15, 17 };
        List<Integer> nums = Arrays.asList(10, 9, 13, 4, -2, -8, 1, 15, 17);
        // functional

        // ClassName :: static fx

        // in case of List/ArrayList/Collections
        // CollectionName.stream will works : numbers.stream

        // printEven(nums, numbers);
        // printCubeOfEven(nums);
        // printNameWithLen(Arrays.asList("Mohit", "Shobhit", "Ravi", "Sudha"));

        // System.out.println(calculateSum(nums));
        System.out.println(calculateMin(nums));
        sumofSqaures(nums);
        collectingStreams();

        differentType(Arrays.asList(1, 2, 3, 4, 5, 6));

        differentType(new Integer[] { 1, 2, 3, 4, 5, 6 });
        // differentType(new int[] { 1, 2, 3, 4, 5, 6 });
    }

    private static boolean isEvene(int num) {
        return num % 2 == 0;
    }

    private static void printNameWithLen(List<String> names) {
        names.stream().map(el -> el + " -> " + el.length()).forEach(System.out::println);
    }

    private static void printEven(List<Integer> nums, int[] numbers) {

        Arrays.stream(numbers).filter(Basic::isEvene).forEach(System.out::println);
        nums.stream().filter(Basic::isEvene).forEach(System.out::println);
    }

    private static int add(int a, int b) {
        return a + b;
    }

    // Reduce
    private static int calculateSum(List<Integer> numbers) {
        // accumulator's default value , Function
        return numbers.stream().reduce(0, Basic::add);
    }

    private static int calculateMin(List<Integer> numbers) {
        return numbers.stream().reduce(Integer.MAX_VALUE, (a, b) -> Math.min(a, b));
    }

    private static void printCubeOfEven(List<Integer> nums) {
        nums.stream().filter(el -> el % 2 == 0).map(el -> el * el * el).forEach(System.out::println); // for each is use
                                                                                                      // to consume an
                                                                                                      // element
        // map,filter,reduce : perform some operations aans carry forward

    }

    private static void sumofSqaures(List<Integer> nums) {
        long ans = nums.stream().map(x -> x * x).reduce(0, Integer::sum);
        System.out.println(ans);
    }

    private static void collectingStreams() {
        // creating list/arrays from streams
        List<Integer> nums = Arrays.asList(10, 9, 13, 4, -2, -8, 1, 15, 17);
        List<Integer> doubleNums = doubleList(nums);
        System.out.println(doubleNums);

        // to Arrays
        int[] arr = tripleEl(nums);
        System.out.println(Arrays.toString(arr));

    }

    private static List<Integer> doubleList(List<Integer> inp) {
        return inp.stream().map(x -> 2 * x).collect(Collectors.toList());
    }

    private static int[] tripleEl(List<Integer> inp) {
        return inp.stream().filter(x -> x % 2 == 0).map(x -> 3 * x).mapToInt(x -> (int) x).toArray();
    }

    private static void differentType(List<Integer> inp) {
        List<String> s = inp.stream().map(x -> Integer.toBinaryString((int) x)).collect(Collectors.toList());
        System.out.println(s);
    }

    private static void differentType(Integer[] inp) {
        List<String> s = Arrays.stream(inp).map(x -> Integer.toBinaryString((int) x)).collect(Collectors.toList());
        System.out.println(s);
    }

    // private static void differentType(int[] inp) {
    // Arrays.stream(inp).map(x ->
    // Integer.toBinaryString(x)).forEach(System.out::println);
    // gives type mismathc in case of primitives : from int to String
    // }

}
