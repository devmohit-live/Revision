import java.util.Stack;

public class client {
    public static void main(String[] args) throws Exception {
        // stack ob = new stack(5);
        // System.out.println(ob.getSize());
        // ob.push(1);
        // ob.push(2);
        // ob.push(3);
        // ob.push(4);
        // ob.push(5);
        // System.out.println(ob);
        // // ob.push(100);
        // System.out.println(ob.getSize());
        // System.out.println(ob.pop());
        // System.out.println(ob.pop());
        // System.out.println(ob.pop());
        // System.out.println(ob.pop());
        // System.out.println(ob.pop());
        // // System.out.println(ob.pop());
        // Stack<Integer> st = new Stack<>();
        // st.push(1);
        // st.push(2);
        // st.push(3);
        // st.push(4);
        // st.push(5);
        // System.out.println("original stack of java " + st);

        dynamicStack ds = new dynamicStack(5);
        ds.push(1);
        ds.push(2);
        ds.push(3);
        ds.push(4);
        ds.push(5);
        System.out.println(ds);
        System.out.printf("size is %d and max size is %d \n", ds.getSize(), ds.getMaxSize());
        ds.push(100);
        System.out.println(ds);
        System.out.printf("size is %d and max size is %d \n", ds.getSize(), ds.getMaxSize());

    }
}
