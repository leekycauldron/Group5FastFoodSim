import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Fryer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Fryer extends Equipment
{
    /**
     * Act - do whatever the Fryer wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    private int useTime = 2;
    public Fryer(){
        GreenfootImage f = getImage();
        f.scale(60, 60);
    }
}
