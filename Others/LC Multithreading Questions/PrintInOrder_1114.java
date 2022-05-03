public class PrintInOrder_1114 {
    // Using CountDownLatch : easisest
    class Foo_UsingCountDownLatch {
        // https://stackoverflow.com/questions/8579657/whats-the-difference-between-thread-start-and-runnable-run

        CountDownLatch lathfor2; // wait for 1 thread to complete
        CountDownLatch lathfor3; // wait for 1 thread to complete

        public Foo() {
            lathfor2 = new CountDownLatch(1);
            lathfor3 = new CountDownLatch(1);
        }

        public void first(Runnable printFirst) throws InterruptedException {

            // printFirst.run() outputs "first". Do not change or remove this line.
            printFirst.run();
            lathfor2.countDown();
        }

        public void second(Runnable printSecond) throws InterruptedException {
            lathfor2.await();
            // printSecond.run() outputs "second". Do not change or remove this line.
            printSecond.run();
            lathfor3.countDown();
        }

        public void third(Runnable printThird) throws InterruptedException {
            lathfor3.await();
            // printThird.run() outputs "third". Do not change or remove this line.
            printThird.run();
        }
    }
}
// Different ways:
// https://leetcode.com/problems/print-in-order/discuss/893827/Java-SynchronizedLockSemaphoreCondition-Variable

// Using Volatile keyword
class Foo_UsingVolatile {
    private volatile int counter; // counter will be different for differnt object of class
    // so if you want to have some kind of synchro on the object basis this is
    // preferred

    // private static volatile int counter;
    // if you want synchro on class bases this is preferred : very rare case :
    // singlewton classex

    public Foo() {
        this.counter = 0;
    }

    public void first(Runnable printFirst) throws InterruptedException {

        // printFirst.run() outputs "first". Do not change or remove this line.
        printFirst.run();
        counter = 1;
    }

    public void second(Runnable printSecond) throws InterruptedException {
        while (counter < 1)
            ;

        // printSecond.run() outputs "second". Do not change or remove this line.
        printSecond.run();
        counter = 2;
    }

    public void third(Runnable printThird) throws InterruptedException {

        while (counter < 2)
            ;
        // printThird.run() outputs "third". Do not change or remove this line.
        printThird.run();
    }
}
