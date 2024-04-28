import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Grill class for burgers and hotdogs.
 * 
 * @author Bryson, Bonnie, Matthew
 */
public class Grill extends Equipment
{
    
    private int useTime = 3;
    public Grill(){
        GreenfootImage g = getImage();
        g.scale(50, 50);
    }
}
