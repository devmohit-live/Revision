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


//Using Synchro and wait notify
class FooBar_UsingWaitNotigyAndSynchro{
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

//Using Retreant Lock and Contions with volatile : faster than synchro block
//Retrant : use to acquire lock
//conditions: used to send awwait and signal signals
// volatile variable : for use according to the problems statements req : ex print alternate

