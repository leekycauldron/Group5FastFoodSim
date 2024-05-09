import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
import java.util.List;
/**
 * Write a description of class Customer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Customer extends Actor
{
<<<<<<< Updated upstream
    GifImage customerGif = new GifImage("customerWalking.gif");
=======
    GreenfootSound walkingSound = new GreenfootSound("walking.mp3");
>>>>>>> Stashed changes
    
    protected String[] foodItems = {"burger", "hotdog"};
    protected String[] sideItems = {"fries","cola"};
    protected ArrayList<String> order = new ArrayList<String>();
    protected boolean ordered = false;
    private boolean isImageFlipped = false;
    private int direction = 0;
    
    public Customer() {
        
    }
    
    // TODO: put this in a separate "utils" class because it is likely other classes may use the function
    // Used in a previous vehicle sim
    // Find the nearest actor given the class specification in argument (Employee.class, Actor.class, Counter.class all work)
    // Modified for counterONLY which finds the closest counter that is open
    protected <T extends Counter> T findNearestCounter(Class<T> aClass) {
        // try catch needed for some reason as getting nearest actor if there is none returns error
        try{
            ArrayList<T> actors = (ArrayList<T>)getWorld().getObjects(aClass);
            T nearestActor = null;
            double nearestDistance = Double.MAX_VALUE; // Set to biggest value and then update when a value is smaller.
    
            for (T a : actors) {
                double distance = getDistance(getX(), getY(), a.getX(), a.getY());
                if (distance < nearestDistance && !a.isOrdered()) {
                    nearestDistance = distance;
                    nearestActor = a;
                }
            }
    
            return nearestActor;
        } catch (Exception e) {
            return null;
        }
        
    }
    
    // Used in a previous vehicle sim
    // Get distance between two points using simple pythagorean theorem math
    protected double getDistance(int x1, int y1, int x2, int y2) {
        return Math.hypot(x2 - x1, y2 - y1);
    }
    
    // Order if haven't ordered yet
    // Order a random amount of food items and then a random amount of side items
    protected void order(Counter counter) {
        if(!ordered){
            int foodAmt = Greenfoot.getRandomNumber(foodItems.length+1)+1; // Cannot be zero
            int sidesAmt = Greenfoot.getRandomNumber(sideItems.length+1);
            for(int i = 0; i < foodAmt; i++) {
                order.add(foodItems[Greenfoot.getRandomNumber(foodItems.length)]);
            }
            
            for(int i = 0; i < sidesAmt; i++) {
                order.add(sideItems[Greenfoot.getRandomNumber(sideItems.length)]);
            }
            counter.order(order);
            ordered = true;
        }
    }
    
    public void act() 
    {
<<<<<<< Updated upstream
        if (!ordered) {
            getImage().scale(34,46);
            Counter nearestCounter = findNearestCounter(Counter.class);
            if(nearestCounter != null) {
                setImage(customerGif.getCurrentImage());
                getImage().scale(34,46);
                if (!intersects(nearestCounter)) {
                    turnTowards(nearestCounter.getX(), nearestCounter.getY());
                    move(1); // Adjust the speed as needed
                    // Face either left or right (no diagonals, up/downs..)
                    if ((direction > 90 && direction < 270 && !isImageFlipped) || (direction <= 90 || direction >= 270) && isImageFlipped) {
                        getImage().mirrorHorizontally();
                        isImageFlipped = !isImageFlipped; // Update the flipped state
                    }else {
                        setRotation(0); // Facing right
                    }
                } else {
                    order(nearestCounter);
                }
            }
        } else {
            setLocation(getWorld().getWidth()/2-100,getY());
=======
        try {
            speechBubble.setLocation(getX(), getY() - 50);
        } catch (Exception e){
            // speech bubble does not exist
        }
        
        // True if order done, walk to exit then remove self.
        if(exit) {
            if(!intersects(getExit())) {
                turnTowards(getExit().getX(),getExit().getY());
                move(1);
                litter();
                getImage().scale(34,34);
            }  else  {
                getPickup().subLineCount();
                getWorld().removeObject(this);
            }
        } else {
            // Walk to nearest open counter if not ordered, then order
            if (!ordered) {
                getImage().scale(34,34);
                Counter nearestCounter = findNearestCounter(Counter.class);
                if(nearestCounter != null) {
                    getImage().scale(34,34);
                    if (!intersects(nearestCounter)) {
                        turnTowards(nearestCounter.getX(), nearestCounter.getY());
                        move(1); // Adjust the speed as needed
                        litter();
                    } else {
                        order(nearestCounter);
                    }
                } else {
                    // 20% Chance to leave if no open counter (decrease stars).
                    if(!fullLeaveDecided) {
                        fullLeaveDecided = true;
                        if(Greenfoot.getRandomNumber(5) == 1) {
                            exit = true;
                        }
                    }
                }
            } else { //ordered
                //walk to x coord of pickup counter
                if(getX()>getPickup().getX()) {
                    turnTowards(getPickup().getX(),getPickup().getY()+25);
                    move(1);
                    litter();
                    getImage().scale(34,34);
                } else {
                    // move down if customers in line and add to line count
                    if(!inPickupLine) {
                        setLocation(getX(),getPickup().getY()+25+5*getPickup().getLineCount());
                        getPickup().addLineCount();
                        inPickupLine = true;
                    }
                }
                
            }
>>>>>>> Stashed changes
        }
    }    
}
