import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
import java.util.ArrayList;
/**
 * Write a description of class Cook here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Cook extends Employee
{
    GifImage cookGif = new GifImage("cook.gif");
    
    private boolean working = false; // when received order but may necessarily be at equipment
    private boolean cooking = false; // when using cooking equipment
    private int direction = 0;
    private boolean isImageFlipped = false;
    private ArrayList<String> order = new ArrayList<String>();
    private boolean addedMoney = false; // Only add money once per item!
    
<<<<<<< Updated upstream
    // Kitchen boundaries
    private int minX = 50; // Minimum X-coordinate
    private int maxX = 550; // Maximum X-coordinate
    private int minY = 50; // Minimum Y-coordinate
    private int maxY = 250; // Maximum Y-coordinate
=======
    public Cook(){
        setImage(cookGif.getCurrentImage());
        getImage().scale(33, 53);
    }
>>>>>>> Stashed changes
    
    private Grill findGrill() {
        List<Grill> grills = getWorld().getObjects(Grill.class);
        for (Grill grill : grills) {
            if (!grill.isInUse()) {
                return grill;
            }
        }
        return null; // No free grill found
    }
    
    private void goToGrill() {
        System.out.println("finding grill");
        Grill grill = findGrill();
        if (grill != null) {
                if (!intersects(grill)) {
                    System.out.println("moving to grill");
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
                    grill.use();
                    cooking = true;
                    System.out.println("grilling");
                }
        } else {
            System.out.println("no grill");
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

    public void act()
    {
        MainWorld world = (MainWorld) getWorld();
        if (working) {
            for(String item : order) {
                if(!addedMoney) {
                    addedMoney = true;
                    switch(item) {
                        case "burger":
                            world.addMoney(world.BURGER_PRICE);
                            goToGrill();
                            break;
                        case "hotdog":
                            world.addMoney(world.HOTDOG_PRICE);
                            goToGrill();
                            break;
                        case "fries":
                            world.addMoney(world.FRIES_PRICE);
                            goToGrill(); // temporary
                            break;
                        case "cola":
                            world.addMoney(world.COLA_PRICE);
                            goToGrill(); // temporary
                            break;
                        default:
                            System.out.println("err");
                    }
                }
            }
            
            if(!cooking) {
                for(String item : order) {
                    switch(item) {
                        case "burger":
                            goToGrill();
                            break;
                        case "hotdog":
                            goToGrill();
                            break;
                        case "fries":
                            goToGrill(); // temporary
                            break;
                        case "cola":
                            goToGrill(); // temporary
                            break;
                        default:
                            System.out.println("err");
                    }
                }
            }
        } else {
            Counter orderCounter = findOrderCounter();
            if (orderCounter != null) {
                if (!intersects(orderCounter)) {
                    // Move towards the counter with an order
                    turnTowards(orderCounter.getX(), orderCounter.getY());
                    move(1); // Adjust the speed as needed
                    setImage(cookGif.getCurrentImage());
                    getImage().scale(33, 53);
                    if ((direction > 90 && direction < 270 && !isImageFlipped) || (direction <= 90 || direction >= 270) && isImageFlipped) {
                        getImage().mirrorHorizontally();
                        isImageFlipped = !isImageFlipped; // Update the flipped state
                    }else {
                        setRotation(0); // Facing right
                    }
                } else {
                    // Take the order
                    order = orderCounter.doneOrder();
                    working = true;
                }
            }
        }
    }
}
