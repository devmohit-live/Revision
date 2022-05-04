class FooBar {
    private int n;
    Semaphore foo, bar;

    public FooBar(int n) {
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