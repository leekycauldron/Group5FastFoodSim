import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Pickup counter where customers wait for their order and cooks serve customers.
 * 
 * @author Bryson, Bonnie, Matthew
 */
public class Pickup extends Equipment
{
    private int lineCount;
    
    public Pickup(){
        GreenfootImage i = getImage();
        i.scale(50,50);
        setImage(i);
    }
    
    /*
     * Gets the amount of people in line for the pickup counter
     * 
     * @return int The amount of people in line at pickup counter
     */
    public int getLineCount() {
        return lineCount;
    }
    
    /*
     * Adds one to the line
     */
    public void addLineCount() {
        lineCount++;
    }
    
    /*
     * Subtract one to the line
     */
    public void subLineCount() {
        lineCount--;
    }
    
    //Override equipment act code.
    public void act()
    {
        // Add your action code here.
    }
}
