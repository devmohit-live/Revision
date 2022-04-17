public class CallinfGC {
    public static void main(String[] args) {
        Runtime.getRuntime().gc();
        // or
        System.gc();
    }
}
