import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MainWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MainWorld extends World
{
    public static final int COOK_COUNT = 3;
    public static final int COUNTER_COUNT = 5;
    /**
     * Constructor for objects of class MainWorld.
     * 
     */
    public MainWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1); 
        
        // Spawn all equipment and employees based on the counts
        for(int i = 0; i < COUNTER_COUNT;i++) {
            Counter counter = new Counter();
            addObject(counter, (getWidth()/2)+50*i,getHeight()/2);
        }
        for(int i = 0; i < COOK_COUNT; i++) {
            Cook cook = new Cook();
            addObject(cook,100+50*i,100);
        }
    }
    
    public void act() {
        if (Greenfoot.getRandomNumber (60) == 0){
            Customer c = new Customer();
            addObject(c,getWidth()/2+Greenfoot.getRandomNumber(getWidth()/2),getHeight()/2+getHeight()/4);
        }
    }
}
