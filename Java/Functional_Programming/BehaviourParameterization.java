import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class BehaviourParameterization {
    // kind of high order function : passing function logic(predicate) as an
    // argument
    static List<Integer> nums = Arrays.asList(12, 10, 9, 8, 3, 5, 1, 6, 7, 8);

    public static void main(String[] args) {
        // Normal way
        // even
        nums.stream().filter(x -> x % 2 == 0).forEach(System.out::println);
        System.out.println();
        // odd
        nums.stream().filter(x -> x % 2 != 0).forEach(System.out::println);
        System.out.println();
        // multiple of 3
        nums.stream().filter(x -> x % 3 == 0).forEach(System.out::println);
        System.out.println();

        System.out.println("Better");
        // Better :
        function(nums, x -> x % 2 == 0);
        System.out.println();
        function(nums, x -> x % 2 != 0);
        System.out.println();
        function(nums, x -> x % 3 == 0);
        System.out.println();

    }

    private static void function(List<Integer> nums, Predicate<? super Integer> predicate) {
        nums.stream().filter(predicate).forEach(System.out::println);
    }
}
