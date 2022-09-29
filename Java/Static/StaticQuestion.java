public class StaticQuestion {

    // Ans : bdac100

    // //Normal static blocks : line wise -> main -> calss called in main's staic
    // block linewise

    public static void main(String[] args) {
        System.out.println("a");// 3
        System.out.println(Class1.i);// 4
    }

    static { // 1
        System.out.println("b");
    }

    static {// 2
        System.out.println("d");
    }
}

class Class1 {
    static int i;

    static {
        System.out.println("c");
        i = 100;
    }

}
