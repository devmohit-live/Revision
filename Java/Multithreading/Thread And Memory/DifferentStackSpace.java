public class DifferentStackSpace {
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
    public void doCountDown() {
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

// Output
/*
 * 
 * Thread 1 i: 10
 * 
 * 
 * Thread 2 i: 10
 * 
 * 
 * Thread 1 i: 9
 * 
 * 
 * Thread 2 i: 9
 * 
 * 
 * Thread 1 i: 8
 * 
 * 
 * Thread 2 i: 8
 * 
 * 
 * Thread 1 i: 7
 * 
 * 
 * Thread 2 i: 7
 * 
 * 
 * Thread 1 i: 6
 * 
 * 
 * Thread 2 i: 6
 * 
 * 
 * Thread 1 i: 5
 * 
 * 
 * Thread 2 i: 5
 * 
 * 
 * Thread 1 i: 4
 * 
 * 
 * Thread 2 i: 4
 * 
 * 
 * Thread 1 i: 3
 * 
 * 
 * Thread 2 i: 3
 * 
 * 
 * Thread 2 i: 2
 * 
 * 
 * Thread 1 i: 2
 * 
 * 
 * Thread 1 i: 1
 * 
 * 
 * Thread 2 i: 1
 * 
 */
