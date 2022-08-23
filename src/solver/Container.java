package solver;

import java.util.Stack;

public class Container {
    
    public static final int COLOR_EMPTY = -1;

    protected final int capacity;
    protected final Stack<Integer> content;

    public Container(int capacity){
        this.capacity = capacity;
        this.content = new Stack<Integer>();
    }

    public int getSpace(){
        return capacity - this.content.size();
    }

    public int getTopColor(){
        if(this.content.isEmpty())
            return COLOR_EMPTY;
        return this.content.peek();
    }

    public boolean isSolved(){
        // leer
        if(content.isEmpty())
            return true;

        // alle die gleiche Farbe
        int color = content.peek();
        for(int c : content)
            if(c != color)
                return false;

        // und voll
        return content.size() == capacity;
    }

    public Container copy(){
        Container result = new Container(this.capacity);
        result.content.addAll(this.content);
        return result;
    }

    public boolean canPutIn(Container target){
        if(this.content.isEmpty())
            return false;
        if(target.content.isEmpty() && target.capacity > 0)
            return true;
        if(target.getTopColor() != this.getTopColor())
            return false;
        if(target.getSpace() == 0)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return this.content.toString();
    }

    @Override
    public int hashCode(){
        return this.content.hashCode();
    }

    @Override
    public boolean equals(Object other){
        if(other instanceof Container container){
            return (container.capacity == this.capacity) && container.content.equals(this.content);
        }
        return false;
    }
}
