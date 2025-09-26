package Chapter5;

public class GorillaAnimal {

    public static void main(String[] args) {
        Gorilla gorilla = new Gorilla();
        
        // Feeding the gorilla
        gorilla.feed(true);
        
        // Grooming the gorilla
        gorilla.groom();
        
        // Attempting to pet the gorilla
        gorilla.pet();
    }
}
// Gorilla class implementing Animal interface
class Gorilla implements Animal {

    @Override
    public boolean feed(boolean timeToEat) {
        // put gorilla food into cage
        if (timeToEat) {
            System.out.println("Feeding the gorilla...");
            return true;
        } else {
            System.out.println("Not feeding time yet.");
            return false;
        }
    }

    @Override
    public void groom() {
        // lather, rinse, repeat
        System.out.println("Gorilla is grooming itself.");
    }

    @Override
    public void pet() {
        // pet at your own risk
        System.out.println("Warning! Petting a gorilla is dangerous.");
    }
}
