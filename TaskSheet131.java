package Task13;

class Dish {
    String name;
    String[] ingredients;

    Dish(String name, String[] ingredients) {
        this.name = name;
        this.ingredients = ingredients;
    }

    void showIngredients() {
        System.out.println("Dish: " + name);
        System.out.print("Ingredients: ");
        for (int i = 0; i < ingredients.length; i++) {
            System.out.print(ingredients[i]);
            if (i < ingredients.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("\n");
    }
}

class Afritada extends Dish {
    Afritada() {
        super("Afritada", new String[]{"Tomato Sauce", "Meat", "Potatoes & Carrots"});
    }
}

class Mechado extends Dish {
    Mechado() {
        super("Mechado", new String[]{"Tomato Sauce", "Meat", "Tomato Paste", "Liver Spread"});
    }
}


class Menudo extends Dish {
    Menudo() {
        super("Menudo", new String[]{"Tomato Sauce", "Meat", "Potatoes & Carrots", "Raisins", "Hotdog"});
    }
}


class Caldereta extends Dish {
    Caldereta() {
        super("Caldereta", new String[]{"Tomato Sauce", "Meat", "Potatoes & Carrots", "Tomato Paste", "Liver Spread", "Cheese"});
    }
}


public class Task13 {
    public static void main(String[] args) {
        Dish meal1 = new Afritada();
        Dish meal2 = new Mechado();
        Dish meal3 = new Menudo();
        Dish meal4 = new Caldereta();

        meal1.showIngredients();
        meal2.showIngredients();
        meal3.showIngredients();
        meal4.showIngredients();
    }
}
