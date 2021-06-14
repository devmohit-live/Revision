public class client {
    public static void main(String[] args) throws Exception {
        stack s1 = new stack();
        stack s2 = new stack(3);
        System.out.println("S1---------------");
        System.out.println(s1.size());
        // s1.pop();
        s1.push(1);
        s1.push(2);
        System.out.println(s1.top());
        s1.push(3);
        System.out.println(s1);
        s1.push(4);
        System.out.println(s1.size());
        s1.push(5);
        // s1.push(6); Overflow
        s1.pop();
        s1.pop();
        s1.pop();
        s1.pop();
        System.out.println(s1.top());
        // s1.pop();

        System.out.println(s1.top());
        // s1.pop(); // Underflow
    }
}
