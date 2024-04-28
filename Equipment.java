import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Equipment super class with use time.
 * 
 * @author Bryson, Bonnie, Matthew
 */
public class Equipment extends Actor
{
    private boolean inUse = false;
    private int useTime = 1; // How long it takes to use equipment (default one second).
    SimpleTimer useTimer = new SimpleTimer();
    private Cook cook; // Current cook using equipment.
    
    
    /*
     * Checks if the equipment is in use or not.
     * 
     * @return boolean True if a employee is using equipment, False if a employee is not using equipment.
     */
    public boolean isInUse() {
        return inUse;
    }
    
    /*
     * Used by cook to start using the equipment and start use timer.
     * 
     * @param cook The cook object that is using the equpiment.
     */
    public void use(Cook cook) {
        inUse = true;
        useTimer.mark();
        this.cook = cook;
    }
    public void act() 
    {
        // Check if time is up, then end cooking process.
        if(inUse && useTimer.millisElapsed() >= useTime * 1000) {
            inUse = false;
            cook.doneCook();
        } 
    }    
}
