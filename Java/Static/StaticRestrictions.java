public class StaticRestrictions {
    public static void main(String[] args) {
        Demo ob = new Demo();
        Demo.performOperations();
        ob.check();
    }
}

class Demo {
    int multiplier1 = 7;
    static int multiplier2 = 7;

    public static void performOperations() {
        // static methods can't call non-static methods and non staic fields
        // System.out.println(multiply(6));
        // System.out.println(multiplier1);

        System.out.println(multiply2(6));
        System.out.println(multiplier2);

        // But non static methods can call static fileds as well as static methods

    }

    public int multiply(int x) {
        return multiplier1 * x;
    }

    public static int multiply2(int x) {
        return multiplier2 * x;
    }

    public void check() {
        System.out.println("Access non satic class variable " + multiplier1);
        System.out.println("Access  satic class variable " + multiplier2);
        System.out.println("Access non satic method " + multiply(8));
        System.out.println("Access satic method " + multiply2(4));
    }
}
