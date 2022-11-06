public class Test1 {
    public static void main(String[] args) {
        Frog frog = new Frog();
        frog.habitat();
    }
}

/**
 * Animal
 */
interface Animal {
    public void habitat();
}

/**
 * Terrestrial
 */
interface Terrestrial extends Animal {

}

interface Aquatic extends Animal {

}

class Frog implements Animal, Terrestrial, Aquatic {

    // Custom concrete implemetation : causing no problems
    @Override
    public void habitat() {
        System.out.println("Lives in water as well as on land");
    }

}