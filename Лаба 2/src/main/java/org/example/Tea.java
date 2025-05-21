package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Tea extends Drink {
    int sugar;
    int endPrice;
    @Autowired
    TeaProducer teaProd;

    @Autowired
    public Tea(@Value("${coffee.sugar:1}") int sugar, TeaProducer teaProd) {
        this.sugar = sugar;
        this.teaProd = teaProd;
        this.endPrice = teaProd.price + sugar*5;
    }
    @Override
    public String Show(){
        return "Tea:" + "\n" +
                "Producer = " + teaProd.nameTea + "\n" +
                "Sugar = " + sugar + "\n" +
                "Price = " + endPrice + "\n";
    }
    @Override
    public int GetPrice(){
        return endPrice;
    }
    @Override
    public String Print(){
        return "Tea: " + "\n" +
                "Producer = " + teaProd.nameTea + "\n" +
                "Sugar = " + sugar + "\n";
    }
    @Override
    public void restoreFromMemento(DrinkMemento memento) {
        if(memento.getDrinkType().equals("Tea") && !memento.hasMilk()){
            this.sugar = memento.getSugar();
            this.teaProd = new TeaProducer(memento.getProducerName());
            this.endPrice = teaProd.price + sugar * 5;
        }
    }

    @Override
    public DrinkMemento createMemento() {
        return new DrinkMemento("Tea", teaProd.nameTea, sugar, false);
    }
}
