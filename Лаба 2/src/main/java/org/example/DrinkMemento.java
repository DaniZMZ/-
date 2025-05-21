package org.example;

import org.springframework.stereotype.Component;


public class DrinkMemento {
    private final String drinkType;
    private final String producerName;
    private final int sugar;
    private final boolean hasMilk;

    public DrinkMemento(String drinkType, String producerName, int sugar, boolean hasMilk) {
        this.drinkType = drinkType;
        this.producerName = producerName;
        this.sugar = sugar;
        this.hasMilk = hasMilk;
    }

    public String getDrinkType() {
        return drinkType;
    }

    public String getProducerName() {
        return producerName;
    }

    public int getSugar() {
        return sugar;
    }

    public boolean hasMilk() {
        return hasMilk;
    }
}
