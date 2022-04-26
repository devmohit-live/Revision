public class RunnableDemo {
    public static void main(String[] args) {
        UsingRunnable runnaableThreadObject = new UsingRunnable();
        UsingRunnable runnaableThreadObject2 = new UsingRunnable();
        Thread ob = new Thread(runnaableThreadObject);
        Thread ob2 = new Thread(runnaableThreadObject2, "Second Child Thread");
        ob2.setDaemon(true);
        ob2.start();
        ob.start();
        for (int i = 0; i < 5; i++) {
            System.out.println("Main thread " + Thread.currentThread().getName());
        }
    }
}
