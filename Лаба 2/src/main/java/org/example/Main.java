package org.example;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Vector;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context =
                new AnnotationConfigApplicationContext("org.example");

        InputHandler inputHandler = context.getBean(InputHandler.class);
        DrinkComposite menu = context.getBean(DrinkComposite.class);

        int n, sugar;
        Vector<Node> StandartMenu = new Vector<>();
        DrinkMemento lastDrinkMemento = null;
        n = inputHandler.readInt("Input a number of standard drinks: ");

        for (int i = 0; i < n; i++) {
            System.out.println("\n--- Adding drink " + (i + 1) + " ---");

            if (lastDrinkMemento != null) {
                System.out.println("Last drink: " + getMementoDescription(lastDrinkMemento));
                String duplicateChoice = inputHandler.readString("Order the same drink? (yes/no): ");
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
                drinkType = inputHandler.readString("Input a type of drink (Tea or Coffee): ");
                if (drinkType.equalsIgnoreCase("Tea") || drinkType.equalsIgnoreCase("Coffee")) {
                    break;
                }
                System.out.println("Error: Please enter 'Tea' or 'Coffee'.");
            }

            Drink newDrink;
            if (drinkType.equalsIgnoreCase("Tea")) {
                String name = inputHandler.readString("Input a name of tea producer: ");
                TeaProducer teaProd = context.getBean(TeaProducer.class);
                teaProd.setName(name);
                //DrinkProducer teaProd = new TeaProducer(name);
                sugar = inputHandler.readInt("Input the amount of sugar: ");
                newDrink = context.getBean(Tea.class, sugar, teaProd);
                StandartMenu.add(newDrink);
                System.out.println("Tea added:");
                System.out.println(newDrink.Show());
                lastDrinkMemento = newDrink.createMemento();
            } else {
                String name = inputHandler.readString("Input a name of coffee producer: ");
                CoffeeProducer coffeeProd = context.getBean(CoffeeProducer.class);
                coffeeProd.setName(name);
                //DrinkProducer coffeeProd = new CoffeeProducer(name);
                sugar = inputHandler.readInt("Input the amount of sugar: ");
                newDrink = context.getBean(Coffee.class, sugar, coffeeProd);
                StandartMenu.add(newDrink);
                System.out.println("Coffee added:");
                System.out.println(newDrink.Show());
                lastDrinkMemento = newDrink.createMemento();

                String milkChoice = inputHandler.readString("Add milk to coffee? (yes/no): ");
                if (milkChoice.equalsIgnoreCase("yes")) {
                    newDrink = new CoffeeWithMilkDecorator(newDrink);
                    StandartMenu.add(newDrink);
                    System.out.println("Coffee with milk added:");
                    System.out.println(newDrink.Show());
                    lastDrinkMemento = newDrink.createMemento();
                }
            }
        }
        menu.setChildren(StandartMenu);
        if (!StandartMenu.isEmpty()) {
            System.out.println("\n--- Final Menu ---");
            System.out.println(menu.Show());
        } else {
            System.out.println("\nNo drinks added to the menu.");
        }
        inputHandler.close();

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