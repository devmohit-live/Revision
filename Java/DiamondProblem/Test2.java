public class Test2 {
    public static void main(String[] args) {
        Frog frog = new Frog();
        frog.breathes();
    }
}

/**
 * Animal
 */
interface Animal {
    public void breathes();
}

/**
 * Terrestrial
 */
interface Terrestrial extends Animal {
    @Override
    default void breathes() {
        System.out.println("Breathes throgh Lungs");
    }
}

interface Aquatic extends Animal {
    @Override
    default void breathes() {
        System.out.println("Breathes throgh Gills");
    }
}

class Frog implements Animal, Terrestrial, Aquatic {

    @Override
    public void breathes() {
        Terrestrial.super.breathes();
    }
    // Now choose any on of the default methods accordning to requiremtns using
    // qualified name or super

}