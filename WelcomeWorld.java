import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Title Screen to allow the user to change values and start the simulation.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class WelcomeWorld extends World
{

    /**
     * Constructor for objects of class WelcomeWorld.
     * 
     */
    Label titleLabel = new Label("ChubMcFlub's", 40);
    public WelcomeWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1); 
        prepare();
    }
    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
    private void prepare()
    {
        addObject(titleLabel,getWidth()/2,getHeight()/2);
    }
}
