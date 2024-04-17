import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MainWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MainWorld extends World
{
    // THESE FINAL VARIABLES ARE HARDCODED FOR NOW (TODO: add values from title screen)
    // Staff + Equipment Count
    public static final int COOK_COUNT = 3;
    public static final int COUNTER_COUNT = 5;
    public static final int GRILL_COUNT = 3;
    public static final int FRYER_COUNT = 2;
    // Staff Wages
    public static final int COOK_WAGE = 2;
    
    // Prices
    public static final int BURGER_PRICE = 5;
    public static final int FRIES_PRICE = 2;
    public static final int COLA_PRICE = 2;
    public static final int HOTDOG_PRICE = 4;
    
    
    public int money = 50;
    public int time = 9;
    Label moneyLabel = new Label("$" + money,40);
    Label timeLabel = new Label(time+":00",40);
    SimpleTimer wageTimer = new SimpleTimer(); // Keep track of when to pay wages.
    SimpleTimer utilTimer = new SimpleTimer(); // Keep track of when to pay utilities.
    SimpleTimer timer = new SimpleTimer(); // Keep track of the day time
    
    public MainWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1); 
        addObject(moneyLabel, 50, 50);
        // Spawn all equipment and employees based on the counts
        for(int i = 0; i < COUNTER_COUNT;i++) {
            Counter counter = new Counter();
            addObject(counter, (getWidth()/2)+30+50*i,getHeight()/2);
        }
        for(int i = 0; i < COOK_COUNT; i++) {
            Cook cook = new Cook();
            addObject(cook,100+50*i,100);
        }
        for(int i = 0; i < GRILL_COUNT; i++) {
            Grill grill = new Grill();
            addObject(grill,(getWidth()/2)+60*i,getHeight()/2-150);
        }
        for(int i = 0; i < FRYER_COUNT; i++) {
            Fryer fryer = new Fryer();
            addObject(fryer,getWidth()-50,50+50*i);
        }
        Pickup pickup = new Pickup();
        addObject(pickup, (getWidth()/2)-70,getHeight()/2);
        Exit exit = new Exit();
        addObject(exit,getWidth()/4 ,getHeight());
        
        timeLabel = new Label(time+":00 AM",40);
        addObject(timeLabel, 80, 80);
    }
    
    public void addMoney(int amt) {
        this.money += amt;
    }
    
    
    // Handle all the timer events
    public void timerCheck() {
        if(wageTimer.millisElapsed() > 5000) {
            wageTimer.mark();
            // Employee Wages
            money -= COOK_COUNT * COOK_WAGE;
        }
        if (utilTimer.millisElapsed() > 10000) {
            utilTimer.mark();
            // Utilities (depends on equipment count)
            money -= 1*COUNTER_COUNT - 3*GRILL_COUNT - 2*FRYER_COUNT;
        }
        if (timer.millisElapsed() > 10000) {
            timer.mark();
            time++;
            if(time < 13) {
                removeObject(timeLabel);
                timeLabel = new Label(time+":00 AM",40);
                addObject(timeLabel, 80, 80);
            } else {
                removeObject(timeLabel);
                timeLabel = new Label((time-12)+":00 PM",40);
                addObject(timeLabel, 80, 80);
            }
            if (time == 17) {
                EndWorld end = new EndWorld();
                
            }
        }
    }
    
    public void act() {
        // Update Food
        removeObject(moneyLabel);
        moneyLabel = new Label("$" + money,40);
        addObject(moneyLabel, 50, 50);
        
        if (Greenfoot.getRandomNumber (500) == 0){
            Customer c = new Customer();
            addObject(c,getWidth()/2+Greenfoot.getRandomNumber(getWidth()/2),getHeight()/2+getHeight()/4);
        }
        
        
        timerCheck();
    }
}
