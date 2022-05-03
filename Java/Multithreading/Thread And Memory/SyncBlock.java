public class SyncBlock {
    // in previos problem we sync the complete method what we can do is syn a block
    // of code base of some variable
    // remember local variables will have seprate memlocation so can't be used for
    // concurrency
    // String local vars can be used as they shared same space(user defined : heap)
    // but not a recommended way:

    // Solution : 1 use volatile vaer , 2 use this(curretn object)

    public static void main(String[] args) {

        // // Stack example
        // CountDown resource = new CountDown();
        // Mythread t1 = new Mythread(resource);
        // Mythread t2 = new Mythread(resource);
        // t1.setName("Thread 1");
        // t2.setName("Thread 2");

        // t1.start();
        // t2.start();

        // Heap exapmle
        CountDown2 resource2 = new CountDown2();
        Mythread2 t3 = new Mythread2(resource2);
        Mythread2 t4 = new Mythread2(resource2);
        t3.setName("Thread 1");
        t4.setName("Thread 2");

        t3.start();
        t4.start();
    }

}

// resource class
class CountDown {
    CountDown() {
    }

    public void doCountDown() { // don't make complete method sync
        String color;
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

        synchronized (this) {
            for (int i = 10; i >= 1; i--)
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

    CountDown2() {
        this.p = 10;
    }

    public synchronized void doCountDown() {
        String color;
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
