public class TeaProducer extends DrinkProducer {
    int price;
    String nameTea;

    public TeaProducer(String name) {
        this.nameTea = name;
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
        return new Tea(sugar, this);
    }

}
