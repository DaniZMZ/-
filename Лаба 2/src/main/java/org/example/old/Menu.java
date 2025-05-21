package org.example.old;

import org.example.Drink;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
@Component
public class Menu {
    ArrayList<Drink> DrinkCont;
    ArrayList<Menu> MenuCont;

    public Menu(){
        this.DrinkCont = new ArrayList<Drink>();
        this.MenuCont = new ArrayList<Menu>();
    }
    public void MenuAdd(Menu menu){
        MenuCont.add(menu);
    }
    public void DrinkAdd(Drink drink){
        DrinkCont.add(drink);
    }
    public String printCurrentLevel(){
        String s = "";
        for (int i = 0; i<DrinkCont.size(); i++) {
            s = s + DrinkCont.get(i).toString();
        }
        return s;
    }
}
