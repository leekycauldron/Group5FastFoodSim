import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Fryer class for the fries.
 * 
 * @author Bryson, Bonnie, Matthew
 */
public class Fryer extends Equipment
{
    
    private int useTime = 2;
    public Fryer(){
        GreenfootImage f = getImage();
        f.scale(60, 60);
    }
}
