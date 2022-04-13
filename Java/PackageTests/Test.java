
import second.B; // by specific importing
import third.*; // all import

public class Test {
    public static void main(String[] args) {
        pack.A a = new pack.A(); // using fully qualified name
        System.out.println(a.print());
        B b = new B();
        System.out.println(b.print());
        C c = new C();
        System.out.println(c.print());

        // Gives error:
        /*
         * Please remove or make sure it appears in the correct subdirectory of the
         * sourcepath. 1 error
         */

        // D d = new D();
        // System.out.println(d.print());

    }
}
