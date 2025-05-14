public abstract class DrinkDecorator extends Drink{
    protected Drink decoratedDrink;

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
