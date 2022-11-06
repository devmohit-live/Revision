import java.util.*;

public class RuntimePolymorphism {
    public static void main(String[] args) {

        List<Integer> list = new LinkedList<>();
        // fails as List is interface that doesn't have addfirst and addlast method
        // but itf it was class and linkedlist was extending this class it would have
        // passed
        // list.addLast(2);
        // list.addFirst(3);
        // System.out.println(list);

        // Overriding:
        A a = new A();
        B b = new B();

        // Runtime poly:

        A x = new B();
        // We can access the class B methods(overriden class A method present in B by
        // using object x)
        x.method();
        // But this property is not applicable to data members
        System.out.println(x.data);
        System.out.println(x.staticData); // static always depends on class(refernce)

        // Summary : data members dependes on the reference that is storing it
        // methods depende upon the memory that is created

    }
}

/**
 * A
 */
class A {
    char data = 'A';
    static String staticData = "Static Data of A";

    public void method() {
        System.out.println("Class A Method");
    }
}

/**
 * B
 */
class B extends A {

    char data = 'B';
    static String staticData = "Static Data of B";

    @Override
    public void method() {
        System.out.println("Class B Method");
    }
}
