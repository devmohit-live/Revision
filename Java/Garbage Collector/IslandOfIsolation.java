public class IslandOfIsolation {
    public static void main(String[] args) {
        Demo a = new Demo();
        Demo b = new Demo();

        a.x = b; // a's x is pointing to b
        b.x = a; // b's x is pointing to a

        // so both a and b's instance object are pointing to objects of each other
        // ie, a's and b's objects in the heap is neing pointed/refrenced by someone yet
        // after doing a,b to null
        // garbage collection is being done

        a = null;
        b = null;
        System.gc();
    }
}

class Demo {
    Demo x; // instance var

    @Override // method of object class
    protected void finalize() throws Throwable {
        System.out.println("Finalize is Called for " + this.getClass().getCanonicalName());
    }
}
