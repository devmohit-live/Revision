public class StaticInitilizers {
    public static void main(String[] args) {
        StaticInitDemo ob = null;
        System.out.println("First time not even used new keyword " + ob.owner);
        System.out.println("****************************");

        // this time static blocks will not be called as static blocks are called just 1
        // time:
        // first time intialization not even object creation

        // Constructor will be called every time new object is created
        StaticInitDemo ob1 = new StaticInitDemo();
        System.out.println("Second timewith object creation " + StaticInitDemo.owner);

        System.out.println("****************************");
        StaticInitDemo ob2 = new StaticInitDemo();
        System.out.println("Third timewith object creation " + StaticInitDemo.owner);
    }
}

class StaticInitDemo {
    public static final String owner;
    static {
        owner = "Mohit";
        System.out.println("Static Initializer 1");
    }

    StaticInitDemo() {
        System.out.println("Constructore called");
    }

    static {
        // first all static blocks are called in order then constructors
        System.out.println("Second Intializer 2");
    }
}
