import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
import java.util.List;
/**
 * The Customer that orders food, then waits at the pickup counter
 * 
 * @author Bryson, Bonnie, Matthew
 */
public class Customer extends Actor
{
    GifImage customerGif = new GifImage("customerWalking.gif");
    GreenfootSound walkingSound = new GreenfootSound("walking.mp3");
    
    protected String[] foodItems = {"burger", "hotdog"};
    protected String[] sideItems = {"fries","cola"};
    protected ArrayList<String> order = new ArrayList<String>();
    private SpeechBubble speechBubble;
    protected boolean ordered = false;
 
    private int direction = 0;
    private boolean inPickupLine = false;
    private boolean exit = false;
    private boolean fullLeaveDecided = false; // if counters full, only decide to leave ONC    
    
    
    

    // Protected as the goal was to make multiple customer subclasses with different behaviour
    protected Counter findNearestCounter(Class<Counter> counter) {
        // try catch needed for some reason as getting nearest actor if there is none returns error
        try{
            ArrayList<Counter> actors = (ArrayList<Counter>)getWorld().getObjects(counter);
            Counter nearestActor = null;
            double nearestDistance = Double.MAX_VALUE; // Set to biggest value and then update when a value is smaller.
    
            for (Counter a : actors) {
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
    
    // Get instance of pickup counter so customers can use it.
    private Pickup getPickup() {
        return getWorld().getObjects(Pickup.class).get(0);
    }
    
    // Get instance of exit so customers can use it.
    private Exit getExit() {
        return getWorld().getObjects(Exit.class).get(0);
    }
    
    // Get distance between two points using simple pythagorean theorem math
    protected double getDistance(int x1, int y1, int x2, int y2) {
        return Math.hypot(x2 - x1, y2 - y1);
    }
    
    // Order a random amount of food items and then a random amount of side items
    protected void order(Counter counter) {
        if(!ordered){
            int foodAmt = Greenfoot.getRandomNumber(foodItems.length+1)+1; // Cannot be zero
            int sidesAmt = Greenfoot.getRandomNumber(sideItems.length+1);
            String speechDisplay = "";
            for(int i = 0; i < foodAmt; i++) {
                String str = foodItems[Greenfoot.getRandomNumber(foodItems.length)];
                order.add(str);
                speechDisplay += str + ", ";
            }
            
            for(int i = 0; i < sidesAmt; i++) {
                String str = sideItems[Greenfoot.getRandomNumber(sideItems.length)];
                order.add(str);
                if(i+1 == sidesAmt) {
                    speechDisplay += str;
                } else {
                    speechDisplay += str + ", ";
                }
                
            }
            speechBubble = new SpeechBubble(speechDisplay);
            getWorld().addObject(speechBubble, getX(), getY() - 50);
            counter.order(order,this);
            ordered = true;
        }
    }
    
    // Customers may litter
    protected void litter() {
        if(Greenfoot.getRandomNumber(200) == 1) {
            MainWorld w = (MainWorld) getWorld();
            w.addTrash(this);
        }
    }
    
    
    
    /*
     * Called by cook when the order is done, tells customer to exit restaurant.
     */
    public void getOrder() {
        exit = true;
    }
    
    public void act() 
    {
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
                setImage(customerGif.getCurrentImage());
                getImage().scale(34,46);
            }  else  {
                getPickup().subLineCount();
                getWorld().removeObject(this);
            }
        } else {
            // Walk to nearest open counter if not ordered, then order
            if (!ordered) {
                getImage().scale(34,46);
                Counter nearestCounter = findNearestCounter(Counter.class);
                if(nearestCounter != null) {
                    setImage(customerGif.getCurrentImage());
                    getImage().scale(34,46);
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
                    setImage(customerGif.getCurrentImage());
                    getImage().scale(34,46);
                } else {
                    // move down if customers in line and add to line count
                    if(!inPickupLine) {
                        setLocation(getX(),getPickup().getY()+25+5*getPickup().getLineCount());
                        getPickup().addLineCount();
                        inPickupLine = true;
                    }
                }
                
            }
        }
        
    }    
}
