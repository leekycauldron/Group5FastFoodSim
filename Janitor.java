import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
/**
 * Janitor class cleans up trash made by customers.
 * 
 * @author Bryson, Bonnie, Matthew
 */
public class Janitor extends Employee
{
    GifImage janitorGif = new GifImage("janitor.gif");
    private boolean gotBroom = false;
    public Janitor() {
        setImage(janitorGif.getCurrentImage());
        getImage().scale(30, 30);
    }
    
    
    // Get distance between two points using simple pythagorean theorem math
    private double getDistance(int x1, int y1, int x2, int y2) {
        return Math.hypot(x2 - x1, y2 - y1);
    }
    
    private Trash findNearestTrash(Class<Trash> trash) {
        // try catch needed for some reason as getting nearest actor if there is none returns error
        try{
            ArrayList<Trash> actors = (ArrayList<Trash>)getWorld().getObjects(trash);
            Trash nearestActor = null;
            double nearestDistance = Double.MAX_VALUE; // Set to biggest value and then update when a value is smaller.
    
            for (Trash a : actors) {
                double distance = getDistance(getX(), getY(), a.getX(), a.getY());
                if (distance < nearestDistance) {
                    nearestDistance = distance;
                    nearestActor = a;
                }
            }
    
            return nearestActor;
        } catch (Exception e) {
            return null;
        }
        
    }
    
    public void act()
    {
        Trash trash = findNearestTrash(Trash.class);
        Supplies s = getWorld().getObjects(Supplies.class).get(0);
        // Must have broom to clean one mess
        if(gotBroom) {
            if (trash != null) {
            if(!intersects(trash)) {
                turnTowards(trash.getX(),trash.getY());
                move(1);
            } else {
                trash.pickup();
                gotBroom = false;
            }
        }
        // Go to supply closet to get a broom if no broom.
        } else {
            if(!intersects(s)) {
                turnTowards(s.getX(),s.getY());
                move(1);
            } else {
                gotBroom = true;
            }
        }
        
    }
}
