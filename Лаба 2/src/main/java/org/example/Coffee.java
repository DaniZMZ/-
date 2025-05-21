package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Coffee extends Drink{
    int sugar;
    int endPrice;
    @Autowired
    CoffeeProducer coffeeProd;

    @Autowired
    public Coffee(@Value("${coffee.sugar:1}") int sugar, CoffeeProducer coffeeProd) {
        this.sugar = sugar;
        this.coffeeProd = coffeeProd;
        this.endPrice = coffeeProd.price + sugar*5;
    }
    @Override
    public String Show(){
        return "Coffee:" + "\n" +
                "Producer = " + coffeeProd.nameCoffee + "\n" +
                "Sugar = " + sugar + "\n" +
                "Price = " + endPrice + "\n";
    }
    @Override
    public int GetPrice(){
        return endPrice;
    }
    @Override
    public String Print(){
        return "Coffee:" + "\n" +
                "Producer = " + coffeeProd.nameCoffee + "\n" +
                "Sugar = " + sugar + "\n";
    }
    @Override
    public DrinkMemento createMemento() {
        return new DrinkMemento("Coffee", coffeeProd.nameCoffee, sugar, false);
    }
    @Override
    public void restoreFromMemento(DrinkMemento memento) {
        if(memento.getDrinkType().equals("Coffee") && !memento.hasMilk()){
            this.sugar = memento.getSugar();
            this.coffeeProd = new CoffeeProducer(memento.getProducerName());
            this.endPrice = coffeeProd.price + sugar*5;
        }
    }
}
