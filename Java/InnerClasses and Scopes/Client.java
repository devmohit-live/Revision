public class Client {
    public static void main(String[] args) {
        Outer o = new Outer();
        o.accessInnerData();
        // outerobject.new can be used to creater inner class object
        Outer.Inner innerclass = o.new Inner();
        innerclass.accessOuterData();

        // can't do that : an enclosing instance that contains Outer.Inner is required
        // Outer.Inner inner2 = new Outer.Inner();
        // inner2.accessOuterData();

        // cant' access private var directly by inner objeect though
        System.out.println(innerclass.innervar1);
        // System.out.println(innerclass.innervar2); will give error as it is a private var

    }
}
