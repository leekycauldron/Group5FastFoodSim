import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
/**
 * Counter class where customers place order and cooks get the order.
 * 
 * @author Bryson, Bonnie, Matthew
 */
public class Counter extends Equipment
{
    private ArrayList<String> order = new ArrayList<>();
    private Customer currentCustomer;
    private boolean ordered = false;
    
    public Counter(){
        GreenfootImage i = getImage();
        i.scale(50,50);
        setImage(i);
    }
    
    /**
     * Takes order from customer
     * 
     * @param order The arraylist of order items to be saved in the counter
     * @param customer The customer that placed the order.
     */
    public void order(ArrayList<String> order, Customer customer) {
        // Take order and save it to this counter
        this.order = order;
        this.currentCustomer = customer;
        ordered = true;
    }
    
    /*
     * Gets the customer that placed the order, used so cook knows who to serve
     * 
     * @return Customer Returns the Cutomer object that placed the order.
     */
    public Customer getCustomer() {
        return currentCustomer;
    }
    
    /*
     * Sets the counter to be clear of any orders and lets customers go to it again
     * 
     * @return ArrayList<String> This is called by cook, so the order is returned so the cook knows what to cook.
     */
    public ArrayList<String> doneOrder() {
        ordered = false;
        return order;
    }
    
    /*
     * Returns if the counter has an order or not, used for both cooks and customers.
     * 
     * @return boolean returns if the counter has an order stored or not.
     */
    public boolean isOrdered() {
        return ordered;
    }
    
    
    // Empty Act method to override the equipment superclass.
    public void act()
    {
        // Add your action code here.
    }
}
