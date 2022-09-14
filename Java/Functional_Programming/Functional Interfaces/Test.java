public class Test {
    public static void main(String[] args) {
        // No need to create class or anonymous class just use lambda expression
        MyFunctionalInterface inf = () -> System.out.println("Interface Method Implemetation using lambda");
        inf.doSomething();
    }
}

@FunctionalInterface

interface MyFunctionalInterface {
    // must have 1 abstract method
    public void doSomething();

}
