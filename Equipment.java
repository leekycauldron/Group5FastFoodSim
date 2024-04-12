import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Equipment here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Equipment extends Actor
{
    /**
     * Act - do whatever the Equipment wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    private boolean inUse = false;
    private int useTime = 3; // In Seconds.
    SimpleTimer useTimer = new SimpleTimer();
    private Cook cook; // Current cook using equipment.
    
    public boolean isInUse() {
        return inUse;
    }
    public void use(Cook cook) {
        inUse = true;
        useTimer.mark();
        this.cook = cook;
    }
    public void act() 
    {
        
        if(inUse && useTimer.millisElapsed() >= useTime * 1000) {
            inUse = false;
            cook.doneCook();
            // make cook stop cooking
        } 
    }    
}
