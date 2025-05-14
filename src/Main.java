import java.util.Vector;

public class Main {
    public static void main(String[] args) {
        int n, sugar;
        Vector<Node> StandartMenu = new Vector<>();
        DrinkComposite menu = new DrinkComposite(StandartMenu);
        DrinkMemento lastDrinkMemento = null;

        n = InputHandler.INSTANCE.readInt("Input a number of standard drinks: ");

        for (int i = 0; i < n; i++) {
            System.out.println("\n--- Adding drink " + (i + 1) + " ---");

            if (lastDrinkMemento != null) {
                System.out.println("Last drink: " + getMementoDescription(lastDrinkMemento));
                String duplicateChoice = InputHandler.INSTANCE.readString("Order the same drink? (yes/no): ");
                if (duplicateChoice.equalsIgnoreCase("yes")) {
                    Drink duplicatedDrink = createDrinkFromMemento(lastDrinkMemento);
                    StandartMenu.add(duplicatedDrink);
                    System.out.println("Duplicated drink added:");
                    System.out.println(duplicatedDrink.Show());
                    lastDrinkMemento = duplicatedDrink.createMemento();
                    continue;
                }
            }

            String drinkType;
            while (true) {
                drinkType = InputHandler.INSTANCE.readString("Input a type of drink (Tea or Coffee): ");
                if (drinkType.equalsIgnoreCase("Tea") || drinkType.equalsIgnoreCase("Coffee")) {
                    break;
                }
                System.out.println("Error: Please enter 'Tea' or 'Coffee'.");
            }

            Drink newDrink;
            if (drinkType.equalsIgnoreCase("Tea")) {
                String name = InputHandler.INSTANCE.readString("Input a name of tea producer: ");
                DrinkProducer teaProd = new TeaProducer(name);
                sugar = InputHandler.INSTANCE.readInt("Input the amount of sugar: ");
                newDrink = teaProd.produce(sugar);
                StandartMenu.add(newDrink);
                System.out.println("Tea added:");
                System.out.println(newDrink.Show());
                lastDrinkMemento = newDrink.createMemento();
            } else {
                String name = InputHandler.INSTANCE.readString("Input a name of coffee producer: ");
                DrinkProducer coffeeProd = new CoffeeProducer(name);
                sugar = InputHandler.INSTANCE.readInt("Input the amount of sugar: ");
                newDrink = coffeeProd.produce(sugar);
                StandartMenu.add(newDrink);
                System.out.println("Coffee added:");
                System.out.println(newDrink.Show());
                lastDrinkMemento = newDrink.createMemento();

                String milkChoice = InputHandler.INSTANCE.readString("Add milk to coffee? (yes/no): ");
                if (milkChoice.equalsIgnoreCase("yes")) {
                    newDrink = new CoffeeWithMilkDecorator(newDrink);
                    StandartMenu.add(newDrink);
                    System.out.println("Coffee with milk added:");
                    System.out.println(newDrink.Show());
                    lastDrinkMemento = newDrink.createMemento();
                }
            }
        }

        if (!StandartMenu.isEmpty()) {
            System.out.println("\n--- Final Menu ---");
            System.out.println(menu.Show());
        } else {
            System.out.println("\nNo drinks added to the menu.");
        }

        InputHandler.INSTANCE.close();
    }

    private static String getMementoDescription(DrinkMemento memento) {
        StringBuilder desc = new StringBuilder();
        desc.append(memento.getDrinkType())
                .append(", Producer: ").append(memento.getProducerName())
                .append(", Sugar: ").append(memento.getSugar());
        if (memento.hasMilk()) {
            desc.append(", Milk: Yes");
        }
        return desc.toString();
    }

    private static Drink createDrinkFromMemento(DrinkMemento memento) {
        Drink drink;
        if (memento.getDrinkType().equals("Coffee")) {
            drink = new Coffee(0, new CoffeeProducer(memento.getProducerName()));
        } else {
            drink = new Tea(0, new TeaProducer(memento.getProducerName()));
        }
        drink.restoreFromMemento(memento);
        if (memento.hasMilk()) {
            drink = new CoffeeWithMilkDecorator(drink);
        }
        return drink;
    }
}