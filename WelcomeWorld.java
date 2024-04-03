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
    Label titleLabel = new Label("ChubMcFlub's", 60);
    Button startBtn = new Button(Color.RED, 100, 50, "Start");
    public WelcomeWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1); 
        prepare();
    }
    
    public void showMenu() {
        removeObjects(getObjects(null));
        Label val1 = new Label("Value 1", 40);
        Label val2 = new Label("Value 2", 40);
        Label val3 = new Label("Value 3", 40);
        Button playBtn = new Button(Color.RED, 100, 50, "Start Sim");
        
        addObject(val1,getWidth()/2, 0);
        addObject(val2,getWidth()/2, 100);
        addObject(val3,getWidth()/2, 200);
        addObject(playBtn,getWidth()/2,300);
        playBtn.init();
    }
    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
    private void prepare()
    {
        addObject(titleLabel,getWidth()/2,getHeight()/2);
        addObject(startBtn, getWidth()/2, getHeight()/2+100);
        startBtn.init();
    }
}
