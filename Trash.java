import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Trash extends Actor
{
    public Trash() {
        GreenfootImage img = getImage();
        img.scale(25, 25);
    }
    
    /*
     * Used by janitors to remove this trash object.
     */
    public void pickup() {
        getWorld().removeObject(this);
    }
}
