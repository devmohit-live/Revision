public class PrintInOrder_1114 {
    //Using CountDownLatch : easisest
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

