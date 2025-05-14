public class CoffeeProducer extends DrinkProducer {
    int price;
    String nameCoffee;
    public CoffeeProducer(String name) {
        this.nameCoffee = name;
        if (name.length() == 4){ /*TESS, NURI*/
            this.price = 120;
        }
        if (name.length() == 6){ /*LIPTON, CURTIS*/
            this.price = 100;
        }
        if (name.length() == 7){ /*RICHARD*/
            this.price = 115;
        }
    }
    @Override
    public Drink produce(int sugar){
        return new Coffee(sugar, this);
    }

}
