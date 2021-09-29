import java.util.Stack;

public class client {
    public static void main(String[] args) throws Exception {
        stack ob = new stack(5);
        System.out.println(ob.getSize());
        ob.push(1);
        ob.push(2);
        ob.push(3);
        ob.push(4);
        ob.push(5);
        System.out.println(ob);
        // ob.push(100);
        System.out.println(ob.getSize());
        System.out.println(ob.pop());
        System.out.println(ob.pop());
        System.out.println(ob.pop());
        System.out.println(ob.pop());
        System.out.println(ob.pop());
        // System.out.println(ob.pop());

    }
}
