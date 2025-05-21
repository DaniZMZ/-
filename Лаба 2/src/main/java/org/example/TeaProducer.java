package org.example;

import org.springframework.stereotype.Component;

@Component
public class TeaProducer extends DrinkProducer {
    int price;
    String nameTea;

    public TeaProducer() {
        this.nameTea = "LIPTON";
    }
    public void setName(String name) {
        this.nameTea = name;
        if (name.length() == 4) this.price = 120; /*TESS, NURI*/
        if (name.length() == 6) this.price = 100; /*LIPTON, CURTIS*/
        if (name.length() == 7) this.price = 115; /*RICHARD*/
    }
    public TeaProducer(String name) {
        this.nameTea = name;
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
        return new Tea(sugar, this);
    }
}
