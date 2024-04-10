import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
/**
 * Write a description of class Counter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Counter extends Equipment
{
    private ArrayList<ArrayList<String>> orders = new ArrayList<>();
    private boolean ordered = false;
    public Counter(){
        GreenfootImage i = getImage();
        i.scale(50,50);
        setImage(i);
    }
    public void order(ArrayList<String> order) {
        // Take order and save it to this counter
        orders.add(order);
        ordered = true;
    }
    public void doneOrder() {
        ordered = false;
    }
    public boolean isOrdered() {
        return ordered;
    }
    public void act()
    {
        // Add your action code here.
    }
}
