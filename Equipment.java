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
    
    
    public boolean isInUse() {
        return inUse;
    }
    public void use() {
        inUse = true;
    }
    public void act() 
    {
        // Add your action code here.
    }    
}
