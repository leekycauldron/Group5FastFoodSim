import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Pickup here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Pickup extends Equipment
{
    /**
     * Act - do whatever the Pickup wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    private int lineCount;
    
    public Pickup(){
        GreenfootImage i = getImage();
        i.scale(50,50);
        setImage(i);
    }
    
    public int getLineCount() {
        return lineCount;
    }
    
    public void addLineCount() {
        lineCount++;
    }
    public void subLineCount() {
        lineCount--;
    }
    public void act()
    {
        // Add your action code here.
    }
}
