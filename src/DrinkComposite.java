import java.util.Vector;

public class DrinkComposite implements Node{
    Vector<Node> children;

    public DrinkComposite(Vector<Node> children) {
        this.children = children;
    }

    public void add(Node node) {
        children.add(node);
    }
    @Override
    public String Show(){
        String listOfChildren = "";
        Iterator iterator = createIterator();
        while (iterator.hasNext()){
            Node one = iterator.next();
            listOfChildren = listOfChildren + one.Show()+"\n";
        }
        return listOfChildren;
    }
    public Iterator createIterator(){
        return new DrinkCompositeIterator(children);
    }
}
