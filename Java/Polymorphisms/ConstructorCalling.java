public class ConstructorCalling {
    public static void main(String[] args) {

        // Summary : All upper constructors will be executed for till we reach the point
        // for which memory is assigned(new object is created)
        // irrespective of refrence

        System.out.print(" A a = new A(); ");
        A a = new A();
        System.out.println();
        System.out.print(" B b = new B(); ");
        B b = new B();
        System.out.println();
        System.out.print(" C c = new C(); ");
        C c = new C();
        System.out.println();
        System.out.print(" A x = new B(); ");
        A x = new B();
        System.out.println();
        System.out.print(" A y = new C(); ");
        A y = new C();
        System.out.println();
        System.out.print(" B z = new C(); ");
        B z = new C();
        System.out.println();
        System.out.print(" D z = new D(); with super() in constr");
        D z = new D();
        System.out.println();

        System.out.print(" A k = new D(); with super() in constr");
        A k = new D();
        System.out.println();

    }
}

class A {
    A() {
        System.out.print("This is A's Constructor ");
    }
}

class B extends A {
    B() {
        System.out.print("This is B's Constructor ");
    }
}

class C extends B {
    C() {
        System.out.println("This is C's Constructor");
    }
}

class D extends C {
    D() {
        super();
        System.out.println("This is D's Constructor");
    }
}
