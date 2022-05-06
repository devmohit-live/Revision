import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class FooBar_UsingSemaphore {
    private int n;
    Semaphore foo, bar;

    public FooBar_UsingSemaphore(int n) {
        this.n = n;
        this.foo = new Semaphore(1); // starting point
        this.bar = new Semaphore(0);
    }

    public void foo(Runnable printFoo) throws InterruptedException {

        for (int i = 0; i < n; i++) {
            foo.acquire(); // if not acquired foo will be printed many times
            printFoo.run();
            bar.release();
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {

        for (int i = 0; i < n; i++) {

            bar.acquire();
            printBar.run();
            foo.release();
        }
    }
}

// Using Synchro and wait notify
class FooBar_UsingWaitNotigyAndSynchro {
    private int n;
    private boolean runFoo;

    public FooBar_UsingWaitNotigyAndSynchro(int n) {
        this.n = n;
        this.runFoo = true;
    }

    public void foo(Runnable printFoo) throws InterruptedException {

        for (int i = 0; i < n; i++) {

            synchronized (this) {
                while (!runFoo) {
                    wait();
                }

                printFoo.run();
                runFoo = false;
                notifyAll();

            }

        }
    }

    public void bar(Runnable printBar) throws InterruptedException {

        for (int i = 0; i < n; i++) {

            synchronized (this) {
                while (runFoo) {
                    wait();
                }
                printBar.run();
                runFoo = true;
                notifyAll();

            }

        }

    }
}

// Using Retreant Lock and Contions with volatile : faster than synchro block
// Retrant : use to acquire lock
// conditions: used to send awwait and signal signals
// volatile variable : for use according to the problems statements req : ex
// print alternate
class FooBar_UsingReentrantLock_Conditions_volatile {
    private int n;
    private final Lock lock;
    private Condition fooCondition, barCondition;
    private volatile boolean fooTurn;

    public FooBar_UsingReentrantLock_Conditions_volatile(int n) {
        this.n = n;
        this.fooTurn = true;
        this.lock = new ReentrantLock();
        this.fooCondition = lock.newCondition();
        this.barCondition = lock.newCondition();
    }

    public void foo(Runnable printFoo) throws InterruptedException {

        for (int i = 0; i < n; i++) {

            try {
                lock.lock();
                if (!this.fooTurn) { // just like wait and notify: just notify uses while
                    fooCondition.await();
                }
                printFoo.run();
                this.fooTurn = false;
                barCondition.signal();
            } finally {
                lock.unlock();
            }

        }
    }

    public void bar(Runnable printBar) throws InterruptedException {

        for (int i = 0; i < n; i++) {

            try {
                lock.lock();
                if (this.fooTurn) { // just like wait and notify
                    barCondition.await();
                }
                printBar.run();
                this.fooTurn = true;
                fooCondition.signal();

            } finally {
                lock.unlock();
            }

        }
    }
}
