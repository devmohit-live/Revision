
class MyClass {

    int a; // will be intialized to 0 by default constructor

    public boolean compare(int val) {
        // int a; // local variable : will give variable not initialized error
        return a == val;
    }
}

/**
 * InnerInitialization
 */
public class Initialization {
    public static void main(String[] args) {
        MyClass ob = new MyClass();
        System.out.println(ob.compare(0));
    }

}