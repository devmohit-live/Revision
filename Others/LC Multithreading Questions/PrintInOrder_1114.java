public class PrintInOrder_1114 {
    // Using CountDownLatch : easisest : but not recommended(as countdown latches' usecase is different)
    class Foo_UsingCountDownLatch {
        // https://stackoverflow.com/questions/8579657/whats-the-difference-between-thread-start-and-runnable-run

        CountDownLatch lathfor2; // wait for 1 thread to complete
        CountDownLatch lathfor3; // wait for 1 thread to complete

        public Foo_UsingCountDownLatch() {
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

// Why not jutst private var why volatiile: all sync variables here should be
// volatile
// (except in the synchronized), otherwise it might go into deadlock. No
// guarantee three threads will be run in the same CPU, thus diff CPU cache
// might maintain different values.


//can be used but not optimal way

// Using Volatile keyword
class Foo_UsingVolatile {
    private volatile int counter; // counter will be different for differnt object of class
    // so if you want to have some kind of synchro on the object basis this is
    // preferred

    // private static volatile int counter;
    // if you want synchro on class bases this is preferred : very rare case :
    // singlewton classex

    public Foo_UsingVolatile() {
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


//  Recommended use 
// Using Java's Semaphore type
class Foo_Semaphore {
    private Semaphore s1, s2;

    public Foo_Semaphore() {
        // All of them have 0 resources to go (q is full) so basically s2,s2 can't run
        // untill atleast 1
        // of the thread releases them
        // so if anyone/method/object want't to acquire locak on s2 or s3 the folowing
        // code will be blocked
        s1 = new Semaphore(0);
        s2 = new Semaphore(0);
    }

    public void first(Runnable printFirst) throws InterruptedException {
        // doesn't need any lock to be acquired so will execute sucessgfully;

        printFirst.run();
        s1.release(); // relasing the s2 lock
    }

    public void second(Runnable printSecond) throws InterruptedException {

        s1.acquire(); /// acquiring of s1 will only be succesfull if s1 was released and it can only
                      /// be when first method have executed; else the below code will be blocked

        printSecond.run();

        s2.release(); // releasing the s2 sema
    }

    public void third(Runnable printThird) throws InterruptedException {

        s2.acquire(); // acquiring of s2 will only be succesfull if s2 was released and it can only be
                      // when second method have executed; else the below code will be blocked

        printThird.run();
    }
}


