package org.example;

import org.springframework.beans.factory.annotation.Autowired;

public abstract class DrinkDecorator extends Drink{
    @Autowired
    protected Drink decoratedDrink;
    @Autowired
    public DrinkDecorator(Drink drink) {
        this.decoratedDrink = drink;
    }
    @Override
    public int GetPrice(){
        return decoratedDrink.GetPrice();
    }
    @Override
    public String Print(){
        return decoratedDrink.Print();
    }
}
