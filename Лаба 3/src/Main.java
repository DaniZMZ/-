import java.util.Vector;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    private static final ExecutorService executor = Executors.newFixedThreadPool(2);

    public static void main(String[] args) {
        try {
            Vector<Node> standardMenu = new Vector<>();
            DrinkComposite menu = new DrinkComposite(standardMenu);
            DrinkMemento lastDrinkMemento = null;

            int drinkCount = InputHandler.INSTANCE.readInt("Input a number of standard drinks: ");

            lastDrinkMemento = addDrinksInteractively(drinkCount, standardMenu, lastDrinkMemento);

            if (!standardMenu.isEmpty()) {
                System.out.println("\n--- Final Menu ---");

                CompletableFuture<String> menuFuture = CompletableFuture.supplyAsync(() -> menu.Show(), executor);
                CompletableFuture<Integer> priceFuture = calculateTotalPriceAsync(standardMenu);

                menuFuture.thenAcceptBoth(priceFuture, (menuText, totalPrice) -> {
                    System.out.println(menuText);
                    System.out.println("\nTotal price (calculated in background): " + totalPrice + " руб.");
                }).join(); // Ожидаем завершения
            } else {
                System.out.println("\nNo drinks added to the menu.");
            }
        } finally {
            shutdown();
        }
    }

    private static DrinkMemento addDrinksInteractively(int count, Vector<Node> menu, DrinkMemento lastMemento) {
        for (int i = 0; i < count; i++) {
            System.out.println("\n--- Adding drink " + (i + 1) + " ---");

            if (lastMemento != null) {
                System.out.println("Last drink: " + getMementoDescription(lastMemento));
                String duplicateChoice = InputHandler.INSTANCE.readString("Order the same drink? (yes/no): ");
                if (duplicateChoice.equalsIgnoreCase("yes")) {
                    Drink duplicatedDrink = createDrinkFromMemento(lastMemento);
                    menu.add(duplicatedDrink);
                    System.out.println("Duplicated drink added:");
                    System.out.println(duplicatedDrink.Show());
                    lastMemento = duplicatedDrink.createMemento();
                    continue;
                }
            }

            String drinkType = getValidDrinkType();
            Drink newDrink = createDrinkByType(drinkType);

            menu.add(newDrink);
            System.out.println(drinkType + " added:");
            System.out.println(newDrink.Show());
            lastMemento = newDrink.createMemento();

            if (drinkType.equalsIgnoreCase("Coffee")) {
                String milkChoice = InputHandler.INSTANCE.readString("Add milk to coffee? (yes/no): ");
                if (milkChoice.equalsIgnoreCase("yes")) {
                    Drink milkCoffee = new CoffeeWithMilkDecorator(newDrink);
                    menu.add(milkCoffee);
                    System.out.println("Coffee with milk added:");
                    System.out.println(milkCoffee.Show());
                    lastMemento = milkCoffee.createMemento();
                }
            }
        }
        return lastMemento;
    }

    private static String getValidDrinkType() {
        String drinkType;
        while (true) {
            drinkType = InputHandler.INSTANCE.readString("Input a type of drink (Tea or Coffee): ");
            if (drinkType.equalsIgnoreCase("Tea") || drinkType.equalsIgnoreCase("Coffee")) {
                return drinkType;
            }
            System.out.println("Error: Please enter 'Tea' or 'Coffee'.");
        }
    }

    private static Drink createDrinkByType(String drinkType) {
        String name = InputHandler.INSTANCE.readString("Input a name of " + drinkType.toLowerCase() + " producer: ");
        int sugar = InputHandler.INSTANCE.readInt("Input the amount of sugar: ");

        DrinkProducer producer;
        if (drinkType.equalsIgnoreCase("Tea")) {
            producer = new TeaProducer(name);
        } else {
            producer = new CoffeeProducer(name);
        }
        return producer.produce(sugar);
    }

    private static CompletableFuture<Integer> calculateTotalPriceAsync(Vector<Node> drinks) {
        return CompletableFuture.supplyAsync(() -> {
            int total = 0;
            for (Node node : drinks) {
                if (node instanceof Drink) {
                    total += ((Drink) node).GetPrice();
                }
            }
            return total;
        }, executor);
    }

    private static String getMementoDescription(DrinkMemento memento) {
        String desc = memento.getDrinkType() +
                ", Producer: " + memento.getProducerName() +
                ", Sugar: " + memento.getSugar();
        if (memento.hasMilk()) {
            desc += ", Milk: Yes";
        }
        return desc;
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

    private static void shutdown() {
        executor.shutdown();
        InputHandler.INSTANCE.close();
    }
}