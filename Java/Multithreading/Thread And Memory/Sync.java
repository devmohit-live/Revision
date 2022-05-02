public class Sync {
    public static void main(String[] args) {

        // Stack example
        CountDown resource = new CountDown();
        Mythread t1 = new Mythread(resource);
        Mythread t2 = new Mythread(resource);
        t1.setName("Thread 1");
        t2.setName("Thread 2");

        t1.start();
        t2.start();

        // Heap exapmle
        // CountDown2 resource2 = new CountDown2();
        // Mythread2 t3 = new Mythread2(resource2);
        // Mythread2 t4 = new Mythread2(resource2);
        // t3.setName("Thread 1");
        // t4.setName("Thread 2");

        // t3.start();
        // t4.start();
    }

}

// resource class
class CountDown {
    String color;

    CountDown() {
    }

    public synchronized void doCountDown() {
        switch (Thread.currentThread().getName()) {
        case "Thread 1":
            color = "\u001B[31m"; // purple
            break;
        case "Thread 2":
            color = "\u001B[36m"; // cyna
            break;
        default:
            color = "\u001B[32m"; // green
        }
        for (int i = 10; i >= 1; i--) {
            System.out.println(color + Thread.currentThread().getName() + " i: " + i);
        }
        System.out.println("\u001B[0m"); // Resret Color
    }
}

// Thread class
class Mythread extends Thread {
    private CountDown countDownResource;

    /**
     * @param target
     * @param countDownResource
     */
    public Mythread(CountDown countDownResource) {
        this.countDownResource = countDownResource;
    }

    @Override
    public void run() {
        countDownResource.doCountDown();
    }

}

class Mythread2 extends Thread {
    private CountDown2 countDownResource;

    /**
     * @param target
     * @param countDownResource
     */
    public Mythread2(CountDown2 countDownResource) {
        this.countDownResource = countDownResource;
    }

    @Override
    public void run() {
        countDownResource.doCountDown();
    }
}

class CountDown2 {
    private int p;
    String color;

    CountDown2() {
        this.p = 10;
    }

    public synchronized void doCountDown() {
        switch (Thread.currentThread().getName()) {
        case "Thread 1":
            color = "\u001B[31m"; // purple
            break;
        case "Thread 2":
            color = "\u001B[36m"; // cyna
            break;
        default:
            color = "\u001B[32m"; // green
        }

        for (; p >= 1; p--) {
            System.out.println(color + Thread.currentThread().getName() + " i: " + p);
        }
        System.out.println("\u001B[0m"); // Resret Color
    }
}