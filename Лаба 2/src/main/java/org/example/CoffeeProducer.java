package org.example;

import org.springframework.stereotype.Component;

@Component
public class CoffeeProducer extends DrinkProducer {
    int price;
    String nameCoffee;
    public CoffeeProducer() {
        this.nameCoffee = "JACOBS";
    }
    public void setName(String name) {
        this.nameCoffee = name;
        if (name.length() == 4) this.price = 120;
        if (name.length() == 6) this.price = 100;
        if (name.length() == 7) this.price = 115;
    }
    public CoffeeProducer(String name) {
        this.nameCoffee = name;
        if (name.length() == 4){
            this.price = 120;
        }
        if (name.length() == 6){
            this.price = 100;
        }
        if (name.length() == 7){
            this.price = 115;
        }
    }
    @Override
    public Drink produce(int sugar){
        return new Coffee(sugar, this);
    }

}
