public class Outer {
    public int var1 = 0;
    private int var2 = 1;

    public void accessInnerData() {
        Inner inner = new Inner();
        System.out.println("Public data of inner " + inner.innervar1);
        System.out.println("Private data of inner " + inner.innervar2);
    }

    public class Inner {
        int innervar1 = 3;
        private int innervar2 = 4;

        public void accessOuterData() {
            System.out.println("Public data of outer " + var1);
            System.out.println("Private data of outer " + var2);
        }

    }
}
