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
    // Prices
    public static final int BURGER_PRICE = 5;
    public static final int FRIES_PRICE = 2;
    public static final int COLA_PRICE = 2;
    public static final int HOTDOG_PRICE = 4;
    
    
    public int money = 50;
    Label moneyLabel = new Label("$" + money,40);
    
    /**
     * Constructor for objects of class MainWorld.
     * 
     */
    public MainWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1); 
        addObject(moneyLabel, 50, 50);
        // Spawn all equipment and employees based on the counts
        for(int i = 0; i < COUNTER_COUNT;i++) {
            Counter counter = new Counter();
            addObject(counter, (getWidth()/2)+45*i,getHeight()/2);
        }
        for(int i = 0; i < COOK_COUNT; i++) {
            Cook cook = new Cook();
            addObject(cook,100+50*i,100);
        }
<<<<<<< Updated upstream
        for(int i = 0; i < GRILL_COUNT; i++) {
            Grill grill = new Grill();
            addObject(grill,(getWidth()/2)+70*i,getHeight()/2-150);
=======
        for(int i = 0; i < JANITOR_COUNT; i++) {
            Janitor janitor = new Janitor();
            addObject(janitor,100+50*i,getHeight()/2-50);
        }
        
        
        // Spawn other equipment and furniture that have a fixed amount.
        addObject(new Pickup(), (getWidth()/4),(getHeight()/2)+50);
        addObject(new Exit(),getWidth()/4 ,getHeight());
        addObject(new Supplies(),getWidth()/6,getHeight() - 25 );    
            
        timeLabel = new Label(time+":00 AM",40);
        addObject(timeLabel, 80, 80);
        
        // Show stars
        for (int i = 0; i < 5; i++) {
            Star s = new Star();
            addObject(s,getWidth()/3+i*50,50);
>>>>>>> Stashed changes
        }
    }
    
    public void addMoney(int amt) {
        this.money += amt;
    }
    
    public void act() {
        // Update Food
        removeObject(moneyLabel);
        moneyLabel = new Label("$" + money,40);
        addObject(moneyLabel, 50, 50);
        
<<<<<<< Updated upstream
        if (Greenfoot.getRandomNumber (60) == 0){
=======
        // Check money
        if(money <= 0) {
            EndWorld end = new EndWorld(money,stats);
            Greenfoot.setWorld(end);
        }
        
        
        int customerChance = 0;
        // Increase customer spawns during lunch & dinner times
        if((time > 10 && time < 14) || (time > 17 && time < 20)){
            customerChance = 100;
            // Display rush
            if(!addedRush){
                addObject(lunchLabel,getWidth()/2,getHeight()/2);
                addedRush = true;
            } 
        } else {
            addedRush = false;
            removeObject(lunchLabel);
        }
        // Spawn customers
        if (Greenfoot.getRandomNumber (Constants.customerChance-customerChance-(stars*20)) == 0){
>>>>>>> Stashed changes
            Customer c = new Customer();
            addObject(c,getWidth()/2+Greenfoot.getRandomNumber(getWidth()/2),getHeight()/2+getHeight()/4);
        }
    }
}
