public class CoffeeWithMilkDecorator extends DrinkDecorator {
    public CoffeeWithMilkDecorator(Drink drink) {
        super(drink);
    }

    @Override
    public String Show() {
        return decoratedDrink.Show() + "Milk: Yes\n";
    }

    @Override
    public int GetPrice() {
        return decoratedDrink.GetPrice() + 15;
    }

    @Override
    public String Print() {
        return decoratedDrink.Print() + "Milk: Yes\n";
    }

    @Override
    public DrinkMemento createMemento() {
        return new DrinkMemento(decoratedDrink instanceof Coffee ? "Coffee" : "Tea",
                getProducerName(), getSugar(), true);
    }

    @Override
    public void restoreFromMemento(DrinkMemento memento) {
        if (memento.hasMilk()) {
            Drink baseDrink;
            if (memento.getDrinkType().equals("Coffee")) {
                baseDrink = new Coffee(0, new CoffeeProducer(memento.getProducerName()));
            } else {
                baseDrink = new Tea(0, new TeaProducer(memento.getProducerName()));
            }
            baseDrink.restoreFromMemento(new DrinkMemento(memento.getDrinkType(), memento.getProducerName(), memento.getSugar(), false));
            this.decoratedDrink = baseDrink;
        }
    }

    private String getProducerName() {
        if (decoratedDrink instanceof Coffee) {
            return ((Coffee) decoratedDrink).coffeeProd.nameCoffee;
        } else if (decoratedDrink instanceof Tea) {
            return ((Tea) decoratedDrink).teaProd.nameTea;
        }
        return "";
    }

    private int getSugar() {
        if (decoratedDrink instanceof Coffee) {
            return ((Coffee) decoratedDrink).sugar;
        } else if (decoratedDrink instanceof Tea) {
            return ((Tea) decoratedDrink).sugar;
        }
        return 0;
    }
}