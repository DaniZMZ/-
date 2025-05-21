package org.example;
public abstract class Drink implements Node{
    public abstract DrinkMemento createMemento();
    public abstract void restoreFromMemento(DrinkMemento memento);

    public int GetPrice(){
        return 0;
    };
    public String Print(){
        return null;
    };

}
