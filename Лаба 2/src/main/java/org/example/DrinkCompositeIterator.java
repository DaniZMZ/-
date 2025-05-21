package org.example;



import java.util.Vector;

public class DrinkCompositeIterator implements Iterator{
    private Vector<Node> children;
    private int currentIndex;

    public DrinkCompositeIterator(Vector<Node> children){
        this.children = children;
        this.currentIndex = 0;
    }

    @Override
    public boolean hasNext() {
        return currentIndex<children.size();
    }

    @Override
    public Node next() {
        if (hasNext()){
            return children.get(currentIndex++);
        }
        return null;
    }

}
