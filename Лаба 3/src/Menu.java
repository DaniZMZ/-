import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
public class Menu {
    CopyOnWriteArrayList<Drink> DrinkCont;
    CopyOnWriteArrayList<Menu> MenuCont;
    private final ExecutorService executor = Executors.newSingleThreadExecutor();
    public Menu(){
        this.DrinkCont = new CopyOnWriteArrayList<Drink>();
        this.MenuCont = new CopyOnWriteArrayList<Menu>();
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
    public int calculateTotalPrice() {
        int total = 0;
        for (Drink drink : DrinkCont) {
            total += drink.GetPrice();
        }
        return total;
    }
    public CompletableFuture<Integer> calculateTotalPriceAsync() {
        return CompletableFuture.supplyAsync(() -> calculateTotalPrice(), executor);
    }

    public void shutdown() {
        executor.shutdown();
    }
}
