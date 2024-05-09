import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
import java.util.ArrayList;
/**
 * Cooks find an order at the counter, cooks the order, serves customer, repeats.
 * 
 * KNOWN ERROR (sort of): The find grill, fountain, fryer methods are very similar and just vary by their subclass, 
 * could refactor to make it one method with a parameter equipment-type.
 * 
 * @author Bryson, Bonnie, Matthew
 */
public class Cook extends Employee
{
    GreenfootSound cookingSound = new GreenfootSound("cooking.mp3");
    
    private boolean working = false; // Order received
    private boolean cooking = false; // Order received AND cooking
    private int direction = 0;
    private boolean isImageFlipped = false;
    private ArrayList<String> order = new ArrayList<String>();
    private Customer currentCustomer;
    private boolean addedMoney = false; // Keep track if added money from order.
    private boolean gotOrder = false; // If cook is done order and they need to give to customer.
    
    public Cook(){
        getImage().scale(38,38);
    }
    
    private Grill findGrill() {
        List<Grill> grills = getWorld().getObjects(Grill.class);
        for (Grill grill : grills) {
            if (!grill.isInUse()) {
                return grill;
            }
        }
        return null; // No free grill found
    }
    
    private Fryer findFryer() {
        List<Fryer> fryers = getWorld().getObjects(Fryer.class);
        for (Fryer fryer : fryers) {
            if (!fryer.isInUse()) {
                return fryer;
            }
        }
        return null; // No free fryer found
    }
    
    private Fountain findFountain() {
        List<Fountain> fountains = getWorld().getObjects(Fountain.class);
        for (Fountain fountain : fountains) {
            if (!fountain.isInUse()) {
                return fountain;
            }
        }
        return null; // No free fountain  found
    }
    
    
    
    
    
    private void goToGrill() {
        Grill grill = findGrill();
        if (grill != null) {
                if (!intersects(grill)) {
                    // Move towards the counter with an order
                    turnTowards(grill.getX(), grill.getY());
                    move(1); // Adjust the speed as needed
                    if ((direction > 90 && direction < 270 && !isImageFlipped) || (direction <= 90 || direction >= 270) && isImageFlipped) {
                        getImage().mirrorHorizontally();
                        isImageFlipped = !isImageFlipped; // Update the flipped state
                    }else {
                        setRotation(0); // Facing right
                    }
                } else {
                    // Grill!
                    grill.use(this);
                    cooking = true;
                }
        } 
    }
    
    
    private void goToFryer() {
        Fryer fryer = findFryer();
        if (fryer != null) {
                if (!intersects(fryer)) {
                    // Move towards the counter with an order
                    turnTowards(fryer.getX(), fryer.getY());
                    move(1); // Adjust the speed as needed
                    if ((direction > 90 && direction < 270 && !isImageFlipped) || (direction <= 90 || direction >= 270) && isImageFlipped) {
                        getImage().mirrorHorizontally();
                        isImageFlipped = !isImageFlipped; // Update the flipped state
                    }else {
                        setRotation(0); // Facing right
                    }
                } else {
                    // Fry!
                    fryer.use(this);
                    cooking = true;
                }
        } 
    }
    
    private void goToFountain() {
        Fountain fountain = findFountain();
        if (fountain != null) {
                if (!intersects(fountain)) {
                    // Move towards the counter with an order
                    turnTowards(fountain.getX(), fountain.getY());
                    move(1); // Adjust the speed as needed
                    if ((direction > 90 && direction < 270 && !isImageFlipped) || (direction <= 90 || direction >= 270) && isImageFlipped) {
                        getImage().mirrorHorizontally();
                        isImageFlipped = !isImageFlipped; // Update the flipped state
                    }else {
                        setRotation(0); // Facing right
                    }
                } else {
                    // Get drink!
                    fountain.use(this);
                    cooking = true;
                }
        } 
    }
    
    private Counter findOrderCounter() {
        List<Counter> counters = getWorld().getObjects(Counter.class);
        for (Counter counter : counters) {
            if (counter.isOrdered()) {
                return counter;
            }
        }
        return null; // No counter with an order found
    }

    /*
     * Used by equipment to let cook now that the order item is done cooking and can move on.
     */
    public void doneCook() {
        cooking = false;
        order.remove(0);
    }

    public void act()
    {
        MainWorld world = (MainWorld) getWorld();
        if (working) {
            // Add money based on order item and increase stats.
            if(!addedMoney) {
                for(String item : order) {
                    addedMoney = true;
                    switch(item) {
                        case "burger":
                            world.addMoney(world.BURGER_PRICE);
                            world.increaseStat(0);
                            goToGrill();
                            break;
                        case "hotdog":
                            world.addMoney(world.HOTDOG_PRICE);
                            world.increaseStat(3);
                            goToGrill();
                            break;
                        case "fries":
                            world.addMoney(world.FRIES_PRICE);
                            world.increaseStat(1);
                            goToFryer(); 
                            break;
                        case "cola":
                            world.addMoney(world.COLA_PRICE);
                            world.increaseStat(2);
                            goToFountain();
                            break;
                        default:
                            break;
                    }
                }
            }
            // Once order is done, go to pickup and serve customer.
            if(gotOrder) {
                Pickup p = getWorld().getObjects(Pickup.class).get(0);
                if(!intersects(p)){
                    turnTowards(p.getX(),p.getY());
                    move(1);
                } else {
                    gotOrder = false;
                    working = false;
                    addedMoney = false;
                    currentCustomer.getOrder(); // give customer their order
                    world.increaseStat(4);
                    return;
                }
            }
            else {
                if(!cooking) {
                    cookingSound.stop();
                    // If all items done, complete order.
                    if (order.size() <= 0) {
                        // done order, set working to false
                        gotOrder = true;
                        return;
                    }
                    // Get the next item in the order and walk to appropriate equipment.
                    switch(order.get(0)) {
                        case "burger":
                            goToGrill();
                            break;
                        case "hotdog":
                            goToGrill();
                            break;
                        case "fries":
                            goToFryer();
                            break;
                        case "cola":
                            goToFountain();
                            break;
                        default:
                            break;
                    }
                    
                } else {
                    // Cooking sound here
                    if(!cookingSound.isPlaying()) {
                        cookingSound.play();
                    }
                }
            }
            
        // If no order in hands, go to counter with an order and take it
        } else {
            Counter orderCounter = findOrderCounter();
            if (orderCounter != null) {
                if (!intersects(orderCounter)) {
                    // Move towards the counter with an order
                    turnTowards(orderCounter.getX(), orderCounter.getY());
                    move(1); // Adjust the speed as needed
                    getImage().scale(38,38);
                    if ((direction > 90 && direction < 270 && !isImageFlipped) || (direction <= 90 || direction >= 270) && isImageFlipped) {
                        getImage().mirrorHorizontally();
                        isImageFlipped = !isImageFlipped; // Update the flipped state
                    }else {
                        setRotation(0); // Facing right
                    }
                } else {
                    // Take the order
                    currentCustomer = orderCounter.getCustomer();
                    order = orderCounter.doneOrder();
                    working = true;
                }
            }
        }
    }
}
