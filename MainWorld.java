import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The Main simulation world where the fast food restaurant runs - spawns all the equipment 
 * and staff depending on what the user inputted before.
 * 
 * @author Bryson, Bonnie, Matthew
 * 
 */


public class MainWorld extends World
{
    // Staff + Equipment Count
    public static int COOK_COUNT;
    public static int COUNTER_COUNT;
    public static int GRILL_COUNT;
    public static int FRYER_COUNT;
    public static int FOUNTAIN_COUNT;
    
    
    // Grab stats from a separate class as they are used in the starting world too.
    // Staff Wages
    public static final int COOK_WAGE = Constants.COOK_WAGE;
    
    // Prices
    public static final int BURGER_PRICE = Constants.BURGER_PRICE;
    public static final int FRIES_PRICE = Constants.FRIES_PRICE;
    public static final int COLA_PRICE = Constants.COLA_PRICE;
    public static final int HOTDOG_PRICE = Constants.HOTDOG_PRICE;
    
    public static final int COUNTER_PRICE = Constants.COUNTER_PRICE;
    public static final int GRILL_PRICE = Constants.GRILL_PRICE;
    public static final int FRYER_PRICE = Constants.FRYER_PRICE;
    public static final int FOUNTAIN_PRICE = Constants.FOUNTAIN_PRICE;
    
    
    // Stats: Burgers, Fries, Cola, Hotdog, customersServed
    public int[] stats = {0,0,0,0,0};
    public int burgerSold;
    public int friesSold;
    public int colaSold;
    public int hotdogSold;
    public int cutomersServed;
    
    
    /**
     * Increases the stat based on the index given, the order is commented at array instantiation.
     * 
     * @param idx The index of which the stat should increase by one.
     */
    public void increaseStat(int idx) {
        stats[idx]++;
    }

    
    public int money = 100; // Starting Money
    public int time = 9; // 9AM - 5PM
    Label moneyLabel = new Label("$" + money,40);
    Label timeLabel = new Label(time+":00",40);
    SimpleTimer wageTimer = new SimpleTimer(); // Keep track of when to pay wages.
    SimpleTimer utilTimer = new SimpleTimer(); // Keep track of when to pay utilities.
    SimpleTimer timer = new SimpleTimer(); // Keep track of the day time
    
    
    
    
    /**
     * Main World Creation, spawn all the equipment and staff according to user setup
     * 
     * @param COOK_COUNT Count of cooks in game
     * @param COUNTER_COUNT Count of Counters in game
     * @param GRILL_COUNT Count of grills in game
     * @param FRYER_COUNT Count of fryers in game
     * @param FOUNTAIN_COUNT Count of fountains in game
     */
    public MainWorld(int COOK_COUNT, int COUNTER_COUNT, int GRILL_COUNT, int FRYER_COUNT, int FOUNTAIN_COUNT)
    {    
        
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1); 
        
        this.COOK_COUNT = COOK_COUNT;
        this.COUNTER_COUNT = COUNTER_COUNT;
        this.GRILL_COUNT = GRILL_COUNT;
        this.FRYER_COUNT = FRYER_COUNT;
        this.FOUNTAIN_COUNT = FOUNTAIN_COUNT;
        
        addObject(moneyLabel, 50, 50);
        // Spawn all equipment and employees based on the counts
        for(int i = 0; i < COUNTER_COUNT;i++) {
            Counter counter = new Counter();
            addObject(counter, (getWidth()/3)+30+50*i,(getHeight()/2)+50);
        }
        for(int i = 0; i < COOK_COUNT; i++) {
            Cook cook = new Cook();
            addObject(cook,100+50*i,100);
        }
        for(int i = 0; i < GRILL_COUNT; i++) {
            Grill grill = new Grill();
            addObject(grill,(getWidth()/4)+60*i,getHeight()/2-150);
        }
        for(int i = 0; i < FRYER_COUNT; i++) {
            Fryer fryer = new Fryer();
            addObject(fryer,getWidth()-50,100+50*i);
        }
        for(int i = 0; i < FOUNTAIN_COUNT; i++) {
            Fountain fountain = new Fountain();
            addObject(fountain,50,50+100*i);
        }
        Pickup pickup = new Pickup();
        addObject(pickup, (getWidth()/4),(getHeight()/2)+50);
        Exit exit = new Exit();
        addObject(exit,getWidth()/4 ,getHeight());
        
        timeLabel = new Label(time+":00 AM",40);
        addObject(timeLabel, 80, 80);
    }
    
    /**
     * Adds money to the total based on parameter amt
     * 
     * @param amt Amount of money being added to total.
     */
    public void addMoney(int amt) {
        this.money += amt;
    }
    
    
    // Handle all the timer events
    private void timerCheck() {
        // Every simulation hour is 10 real seconds,
        // Wage is costed every 10 real seconds, Utilities costed every 5 real seconds.
        if(wageTimer.millisElapsed() > 10000) {
            wageTimer.mark();
            // Employee Wages
            money -= COOK_COUNT * COOK_WAGE;
        }
        if (utilTimer.millisElapsed() > 5000) {
            utilTimer.mark();
            // Utilities (depends on equipment count)
            money -= COUNTER_PRICE*COUNTER_COUNT + GRILL_PRICE*GRILL_COUNT + FRYER_PRICE*FRYER_COUNT - FOUNTAIN_PRICE*FOUNTAIN_COUNT;
        }
        if (timer.millisElapsed() > 10000) {
            timer.mark();
            time++;
            // Get AM or PM
            if(time < 13) {
                removeObject(timeLabel);
                timeLabel = new Label(time+":00 AM",40);
                addObject(timeLabel, 80, 80);
            } else {
                removeObject(timeLabel);
                timeLabel = new Label((time-12)+":00 PM",40);
                addObject(timeLabel, 80, 80);
            }
            // At 5PM, end simulation
            if (time == 17) {
                EndWorld end = new EndWorld(money,stats);
                Greenfoot.setWorld(end);
            }
        }
    }
    
    public void act() {
        // Update Money
        removeObject(moneyLabel);
        moneyLabel = new Label("$" + money,40);
        addObject(moneyLabel, 50, 50);
        
        // Check money
        if(money <= 0) {
            EndWorld end = new EndWorld(money,stats);
            Greenfoot.setWorld(end);
        }
        
        // Spawn customers
        if (Greenfoot.getRandomNumber (300) == 0){
            Customer c = new Customer();
            addObject(c,getWidth()/2+Greenfoot.getRandomNumber(getWidth()/2),getHeight()/2+getHeight()/4);
        }
        
        
        timerCheck();
    }
}
