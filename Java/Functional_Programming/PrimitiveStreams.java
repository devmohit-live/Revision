import java.util.Arrays;
import java.util.List;
import java.util.OptionalDouble;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class PrimitiveStreams {
    static int[] arr = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };

    public static void main(String[] args) {
        // Usinfg Arrays : When we already have an array input
        Arrays.stream(arr); // autoxoxing is done

        // When we want to ctream number stream using some algo
        // Using Stream API
        Stream<Integer> s = Stream.of(1, 2, 3, 4, 5, 6, 7); // Autoboxing

        // Using Default Streams for Primitive
        int sum = IntStream.range(1, 10).sum(); // end is excluded
        OptionalDouble avg = IntStream.rangeClosed(1, 10).average(); // closed range

        // Creating elemets from some algo : created infi so limiting number of elements
        int sum2 = IntStream.iterate(0, x -> x + 2).limit(10).sum();

        // If you want to look through the leemtns to use peek and perform your required
        // action
        System.out.println(IntStream.iterate(1, x -> x + 2).limit(10).peek(System.out::println).sum());

        // If you want to collect it : we can;t directly collect it we have to box it to
        // refrence type
        // LongStream.iterate(0,
        // x->x+2).limit(10).peek(System.out::println).collect(Collectors.toList());
        List<Long> l = LongStream.iterate(0, x -> x + 2).limit(10).peek(System.out::println).boxed()
                .collect(Collectors.toList());
        System.out.println(l);

        System.out.println(IntStream.rangeClosed(1, 10).average().orElse(-1.0));
        System.out.println(IntStream.rangeClosed(1, 10).average());

        System.out.println(IntStream.rangeClosed(1, 10).sum());

    }
}
