public class ThreadClass extends Thread {
    ThreadClass() {

    }

    ThreadClass(String name) {
        super(name);
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println("User thread " + Thread.currentThread().getName());
        }
    }

    public static void main(String[] args) {
        ThreadClass ob = new ThreadClass();
        ThreadClass ob2 = new ThreadClass("Second child thread");// named thead
        // making any thraed deamon : low priority : if maina nd other non deamon
        // threads are being exceuted
        // then program will stop without worrying about wheater this theread is
        // finished or not
        ob.start();
        ob2.setDaemon(true);
        ob2.start();
        for (int i = 0; i < 5; i++) {
            System.out.println("Main thread"); // main is also an user defined and non deamon thread
        }
    }
}
