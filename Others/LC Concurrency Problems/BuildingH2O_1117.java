public class BuildingH2O_1117 {
    // Using syncchro wait and notify
    class H2O {
        // private boolean o;
        private int h;

        public H2O() {
            this.h = 0;
            // this.o = false;
        }

        public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {
            synchronized (this) {
                while (this.h >= 2)
                    wait();

                ++this.h;
                releaseHydrogen.run();
                notifyAll();
            }

        }

        public void oxygen(Runnable releaseOxygen) throws InterruptedException {
            synchronized (this) {
                while (this.h < 2) {
                    wait();
                }
                // this.o = true;
                this.h = 0;
                releaseOxygen.run();
                notifyAll();
            }
        }

    }

    class H2O {
        private Semaphore hydrogen, oxygen;

        public H2O() {
            this.hydrogen = new Semaphore(2);
            this.oxygen = new Semaphore(1);
        }

        public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {

            hydrogen.acquire();
            releaseHydrogen.run();
            if (hydrogen.availablePermits() == 0) // if both hydrogens are acquired(HH) release oxugne
                oxygen.release();
        }

        public void oxygen(Runnable releaseOxygen) throws InterruptedException {

            oxygen.acquire();
            releaseOxygen.run();
            hydrogen.release(2); // ogygen is acquired relase HH for next molecule
        }
    }

    // TODO: Fails in Leetcode
    // Lintcode 2558: Using ReentrantLocks
    class H2O {
        private Lock lock;
        private Condition hydrogenC, oxygenC;
        private volatile int h;

        public H2O() {
            this.h = 0;
            this.lock = new ReentrantLock();
            this.hydrogenC = lock.newCondition();
            this.oxygenC = lock.newCondition();

        }

        public void hydrogen(Consumer releseHydrogen) {
            lock.lock();
            try {
                if (this.h >= 2)
                    hydrogenC.await();

                ++this.h;
                releseHydrogen.accept(0);
                oxygenC.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }

        }

        public void oxygen(Consumer releseOxygen) {
            lock.lock();
            try {
                if (this.h < 2) {
                    hydrogenC.signal();
                    oxygenC.await();
                }
                this.h = 0;
                releseOxygen.accept(0);
                hydrogenC.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }

        }
    }

}
