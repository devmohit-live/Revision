public class UsingRunnable implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println("Thread using runnable " + Thread.currentThread().getName());
        }

    }

}
