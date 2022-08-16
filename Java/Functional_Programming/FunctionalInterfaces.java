import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class FunctionalInterfaces {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8);

        System.out.println("OP1: ");
        numbers.stream().filter(x -> x % 2 == 0).map(x -> x * x).forEach(System.out::println);

        // filter : takes predicate(a statement which is either true or false)
        // map: takes Functions(accepts a single argument and prodices a single output)
        // forEach: takes Consumer(take one argument and consumes it: returns nothing)

        // Predicate, Functions, Consumers are part of FunctionalInterface: ir they have
        // atleast 1 abstract method
        // which takes logic

        // Actual extraction of lambda function :

        // Predicate<? super Integer> predicate = x -> x % 2 == 0;
        // Function<? super Integer, ? extends Integer> mapper = x -> x * x;
        // Consumer<?> action = System.out::println;

        // Manually doing it
        System.out.println("OP2: Extracting lambda to approriate interaface implemettaion");
        Predicate<Integer> predicate = x -> x % 2 == 0;
        Function<Integer, Integer> mapper = x -> x * x;
        Consumer<Integer> action = System.out::println;

        numbers.stream().filter(predicate).map(mapper).forEach(action);

        System.out.println("OP: Creating all interafce manually");
        Predicate<Integer> predicate2 = new Predicate<Integer>() {
            @Override
            public boolean test(Integer num) {
                return num % 2 == 0;
            }
        };
        Function<Integer, Integer> function2 = new Function<Integer, Integer>() {

            @Override
            public Integer apply(Integer x) {
                return x * x;
            }

        };

        Consumer<Integer> consumer2 = new Consumer<Integer>() {
            @Override
            public void accept(Integer x) {
                System.out.println(x);
            }
        };

        numbers.stream().filter(predicate2).map(function2).forEach(consumer2);
        System.out.println();

        // Wroking with termial functions : produces single or not outpur : ex: forEch:
        // no op, reduce : single op(for complete stream)
        int sum = numbers.stream().reduce(0, (a, b) -> a + b);
        System.out.println("OP!: " + sum);

        // BinaryOperator : takes two arguments and returns 1 : it internally extends
        // BiFunction

        BinaryOperator<Integer> binaryOperator = (a, b) -> a + b;
        int sum2 = numbers.stream().reduce(0, binaryOperator);
        System.out.println("OP2: Extraction " + sum2);

        BinaryOperator<Integer> binaryOperator2 = new BinaryOperator<Integer>() {

            @Override
            public Integer apply(Integer a, Integer b) {
                return a + b;
            }

        };

        int sum3 = numbers.stream().reduce(0, binaryOperator2);
        System.out.println("OP2: Custom Implemetation " + sum3);

     }
}
