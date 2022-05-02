public class SameHeapMem {
    public static void main(String[] args) {
        CountDown resource = new CountDown();
        Mythread t1 = new Mythread(resource);
        Mythread t2 = new Mythread(resource);
        t1.setName("Thread 1");
        t2.setName("Thread 2");

        t1.start();
        t2.start();
    }
}

// resource class
class CountDown {
    private int p;

    public void doCountDown() {
        String color;
        this.p = 10;

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

        for (p = 10; p >= 1; p--) {
            System.out.println(color + Thread.currentThread().getName() + " i: " + p);
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

// output
/**
 * 
 * Thread 1 i: 10
 * 
 * Thread 2 i: 10
 * 
 * Thread 1 i: 9
 * 
 * Thread 2 i: 8
 * 
 * Thread 1 i: 7
 * 
 * Thread 2 i: 6
 * 
 * Thread 1 i: 5
 * 
 * Thread 2 i: 4
 * 
 * Thread 2 i: 2
 * 
 * Thread 2 i: 1
 * 
 * Thread 1 i: 3
 * 
 * 
 */
