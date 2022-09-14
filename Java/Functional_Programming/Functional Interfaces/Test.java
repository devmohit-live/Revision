public class Test {
    public static void main(String[] args) {

        // No need to create class or anonymous class just use lambda expression
        MyFunctionalInterface inf = () -> System.out.println("Interface Method Implemetation using lambda");
        inf.doSomething();

        // Runnable functionalInterface : have one abstract method : run
        Runnable r = () -> {
            for (int itr = 0; itr < 10; itr++)
                System.out.println("Child Thread");
        };
        Thread t = new Thread(r);
        t.start();

        Interface2 i = (a, b) -> System.out.println("Sum is " + (a + b));
        i.add(10, 20);
        // Interface2 i2 = { (a, b) -> System.out.println("Sum is " + (a + b)) }(2,30);
        Interface3 i3 = (x) -> x.length();
        System.out.println(i3.getLength("Hello"));

        // Another Way : Using anon instance creation => complex
        int a = new Interface3() {
            @Override
            public int getLength(String s) {
                return s.length();
            }
        }.getLength("Mohit Singh");

        System.out.println(a);

    }
}

@FunctionalInterface

interface MyFunctionalInterface {
    // must have 1 abstract method
    public void doSomething();

}

@FunctionalInterface
interface Interface2 {
    public void add(int a, int b);
}

@FunctionalInterface
interface Interface3 {
    public int getLength(String s);
}

@FunctionalInterface
interface Interface4 {
    public int getLength(String s);
}

// Creatinfg threads
