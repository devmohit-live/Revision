import static java.lang.System.*;
import static java.lang.Math.*;
import java.util.Scanner;

public class StaticImports {
    /*
     * Usage : Less coding is required if you have access any static member of a
     * class oftenly.
     * 
     * Disadvantages:If you overuse the static import feature, it makes the program
     * unreadable and unmaintainable
     */

    public static void main(String[] args) {
        // System.out.println("Hello");
        out.println("Hello: No need to write System");
        Scanner sc = new Scanner(in); // no need to write SYstem.in
        sc.close();
        out.println(sqrt(25)); // no need to wrte Math in Math.sqrt
        out.println(abs(-12));
        out.println(floor(25.89));
        out.println(ceil(25.22));
        out.println(pow(2, 3));
    }

}
