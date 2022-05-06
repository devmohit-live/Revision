import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.IntConsumer;

class ZeroEvenOdd_UsingWait_NotifyAll_synchro_volatile {
    private int n;
    private volatile int currState;
    private final int ZERO, EVEN, ODD;

    public ZeroEvenOdd_UsingWait_NotifyAll_synchro_volatile(int n) {
        this.n = n;
        this.ZERO = 0;
        this.EVEN = 2;
        this.ODD = 1;
        this.currState = ZERO;
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void zero(IntConsumer printNumber) throws InterruptedException {
        for (int i = 1; i <= n; i++) {
            synchronized (this) {
                while (currState != ZERO)
                    wait();

                printNumber.accept(0);
                if ((i & 1) == 1) {
                    currState = ODD;
                } else {
                    currState = EVEN;
                }

                notifyAll();
            }
        }
    }

    public void even(IntConsumer printNumber) throws InterruptedException {
        for (int i = 2; i <= n; i += 2) {
            synchronized (this) {
                while (currState != EVEN)
                    wait();

                printNumber.accept(i);
                currState = ZERO;
                notifyAll();
            }
        }
    }

    public void odd(IntConsumer printNumber) throws InterruptedException {
        for (int i = 1; i <= n; i += 2) {
            synchronized (this) {
                while (currState != ODD)
                    wait();

                printNumber.accept(i);
                currState = ZERO;
                notifyAll();
            }
        }
    }
}

public class PrintZeroEvenOdd_1116 {

    class ZeroEvenOdd_UsingReentrantLock_volatile {
        private int n;
        private Lock lock;
        private Condition zero, odd, even;
        private volatile int currState;
        private final int ZERO, EVEN, ODD;

        public ZeroEvenOdd_UsingReentrantLock_volatile(int n) {
            this.n = n;
            this.lock = new ReentrantLock();
            this.zero = lock.newCondition();
            this.odd = lock.newCondition();
            this.even = lock.newCondition();
            this.ZERO = 0;
            this.EVEN = 2;
            this.ODD = 1;
            this.currState = ZERO;
        }

        // printNumber.accept(x) outputs "x", where x is an integer.
        public void zero(IntConsumer printNumber) throws InterruptedException {
            for (int i = 1; i <= n; i++) {
                lock.lock();
                try {
                    if (currState != ZERO)
                        zero.await();

                    printNumber.accept(0);
                    if (i % 2 == 0) {
                        currState = EVEN;
                        even.signal();
                    } else {
                        currState = ODD;
                        odd.signal();
                    }
                } finally {
                    lock.unlock();
                }

            } // for
        }

        public void even(IntConsumer printNumber) throws InterruptedException {
            for (int i = 2; i <= n; i += 2) {
                lock.lock();
                try {
                    if (currState != EVEN)
                        even.await();

                    printNumber.accept(i);
                    currState = ZERO;
                    zero.signal();
                } finally {
                    lock.unlock();
                }
            }
        }

        public void odd(IntConsumer printNumber) throws InterruptedException {
            for (int i = 1; i <= n; i += 2) {
                lock.lock();
                try {
                    if (currState != ODD)
                        odd.await();

                    printNumber.accept(i);
                    currState = ZERO;
                    zero.signal();
                } finally {
                    lock.unlock();
                }
            }
        }
    }

}
