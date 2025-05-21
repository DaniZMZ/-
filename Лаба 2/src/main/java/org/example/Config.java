package org.example;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.Vector;

@Configuration
@ComponentScan
public class Config {

    /*@Bean
    public Tea tea(String string, int sugar){
        return new Tea(sugar, new TeaProducer(string));
    }
    @Bean
    public Coffee coffee(String string, int sugar){
        return new Coffee(sugar, new CoffeeProducer(string));
    }
    @Bean
    public Menu menu(){
        return new Menu();
    }
    @Bean
    public DrinkComposite drinkComposite(Vector<Node>children){
        return new DrinkComposite(children);
    }
    @Bean
    public DrinkMemento drinkMemento(String drinkType, String producerName, int sugar, boolean hasMilk){
        return new DrinkMemento(drinkType, producerName, sugar, hasMilk);
    }
    @Bean
    public CoffeeWithMilkDecorator coffeeWithMilkDecorator(Drink drink){
        return new CoffeeWithMilkDecorator(drink);
    }
    @Bean
    public DrinkCompositeIterator drinkCompositeIterator(Vector<Node> children){
        return new DrinkCompositeIterator(children);
    }*/
}
