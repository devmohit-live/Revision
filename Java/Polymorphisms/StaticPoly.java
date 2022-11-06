public class StaticPoly {
    public static void main(String[] args) {
        StaticPoly ob = new StaticPoly();

        //Upcasting will be done as 2,3 are int, int ans int ,int method doesn't exisxts so it will be go to long(any one)
        //but which one to type cast as int,long and long, int both exists : ambiguious situation
        System.out.println(ob.add(2, 3));
        System.out.println(ob.add(2, 3L));
        System.out.println(ob.add(2L, 3));
        System.out.println(ob.add(2L, 3L));

    }

    // int add(int a,int b){
    // System.out.println("int a, int b");
    // return a+b;
    // }

    //

    // If we remove inta, intb add method then These 2 can't be together : as in
    // case of Upcasting of types we are not sure which to select
    long add(int a, long b) {
        System.out.println("int a, long b");
        return a + b;
    }

    long add(long a, int b) {
        System.out.println("long a, int b");
        return a + b;
    }

    //

    long add(long a, long b) {
        System.out.println("int a, long b");
        return a + b;
    }

}
