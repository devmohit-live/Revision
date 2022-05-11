public class FizzBuzzMultithread_1195 {
    // Using Semaphore
    class FizzBuzz {
        private int n;
        private int currentNum;
        private final int FIZZES, BUZZES, FIZZBUZZES, NUMBERS;
        private final Semaphore fSem, bSem, nSem, fbSem;

        public FizzBuzz(int n) {
            this.n = n;
            this.currentNum = 1;
            this.fSem = new Semaphore(0);
            this.bSem = new Semaphore(0);
            this.fbSem = new Semaphore(0);
            this.nSem = new Semaphore(1);
            this.FIZZBUZZES = n / 15;
            this.FIZZES = n / 3 - FIZZBUZZES;
            this.BUZZES = n / 5 - FIZZBUZZES;
            this.NUMBERS = n - (FIZZES + BUZZES + FIZZBUZZES);
        }

        private void signalNext() {
            ++currentNum;
            if (currentNum % 3 == 0 && currentNum % 5 == 0)
                fbSem.release();
            else if (currentNum % 3 == 0)
                fSem.release();
            else if (currentNum % 5 == 0)
                bSem.release();
            else
                nSem.release();
        }

        // printFizz.run() outputs "fizz".
        public void fizz(Runnable printFizz) throws InterruptedException {
            for (int i = 0; i < FIZZES; i++) {
                fSem.acquire();
                printFizz.run();
                signalNext();
            }
        }

        // printBuzz.run() outputs "buzz".
        public void buzz(Runnable printBuzz) throws InterruptedException {
            for (int i = 0; i < BUZZES; i++) {
                bSem.acquire();
                printBuzz.run();
                signalNext();
            }
        }

        // printFizzBuzz.run() outputs "fizzbuzz".
        public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
            for (int i = 0; i < FIZZBUZZES; i++) {
                fbSem.acquire();
                printFizzBuzz.run();
                signalNext();
            }
        }

        // printNumber.accept(x) outputs "x", where x is an integer.
        public void number(IntConsumer printNumber) throws InterruptedException {
            for (int i = 0; i < NUMBERS; i++) {
                nSem.acquire();
                printNumber.accept(currentNum);
                signalNext();
            }
        }
    }
}
