public class SingleTon {
    public static void main(String[] args) {
        SingletonClass a = SingletonClass.getInstance();
        System.out.println(a.val + " " + a.s + " " + a.hashCode());

        // should be same

        SingletonClass b = SingletonClass.getInstance();
        System.out.println(b.val + " " + b.s + " " + b.hashCode());
    }
}

class SingletonClass {
    private static SingletonClass obj = null;
    // can't be private
    public int val;
    public String s;

    private SingletonClass() {
        this.val = 0;
        this.s = "First";
    }

    public static SingletonClass getInstance() {
        if (obj == null) {
            obj = new SingletonClass();
        }
        return obj;
    }

}
