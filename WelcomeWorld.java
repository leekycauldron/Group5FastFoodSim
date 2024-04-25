import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Title Screen to allow the user to change values and start the simulation.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class WelcomeWorld extends World
{
    
    Button startBtn = new Button(Color.RED, 100, 50, "Start");
    GreenfootImage selectImage = new GreenfootImage("select.png");
    GreenfootSound bgMusic = new GreenfootSound("bg_music.wav");
    Label employeeWageTitle = new Label("Hourly Wage",40);
    Label employeeWageLabel = new Label("$0/hr",40);
    Label utilitiesTitle = new Label("Hourly Utilities",35);
    Label utilitiesLabel = new Label("$0/hr",40);
    // Min, max, current value
    ValueItem[] valueItems = {
        new ValueItem(0, 0, 10), // Cook Count
        new ValueItem(0, 0, 6),  // Counter Count
        new ValueItem(0, 0, 7),  // Grill Count
        new ValueItem(0, 0, 4),   // Fryer Count
        new ValueItem(0, 0, 3)    // Fountain Count
    };
    public WelcomeWorld()
    {    
        super(600, 400, 1); 
        bgMusic.setVolume(50);
        prepare();
    }
    
    public void started() {
        bgMusic.playLoop();
    }
    
    public void stopped() {
        bgMusic.stop();
    }
    
    public void displayWage() {
        removeObject(employeeWageLabel);
        employeeWageLabel = new Label("$"+valueItems[0].value*Constants.COOK_WAGE+"/hr",40);
        addObject(employeeWageLabel,getWidth()-100,100);
    }
    public void displayUtilities() {
        removeObject(utilitiesLabel);
        int utilities = valueItems[1].value*Constants.COUNTER_PRICE + valueItems[2].value*Constants.GRILL_PRICE + valueItems[3].value*Constants.FRYER_PRICE + valueItems[4].value*Constants.FOUNTAIN_PRICE;
        // Multiple utilities by 2 because it charges every half hour
        utilitiesLabel = new Label("$"+utilities*2+"/hr",40);
        addObject(utilitiesLabel,getWidth()-100,200);
    }
    
    
    public void showMenu() {
        
        setBackground(selectImage);
        removeObjects(getObjects(null)); // Clears the screen
        addObject(employeeWageTitle,getWidth()-100,50);
        addObject(employeeWageLabel,getWidth()-100,100);
        addObject(utilitiesTitle,getWidth()-100,150);
        addObject(utilitiesLabel,getWidth()-100,200);
        
        String[] valueNames = {"Cook Count", "Counter Count", "Grill Count", "Fryer Count", "Fountain Count"};
        int startY = 50; // Start Y position for the first item
        int spacing = 50; // Vertical spacing between items
    
        for(int i = 0; i < valueItems.length; i++) {
            // X positions for the label and buttons
            int labelX = ( getWidth() / 2 );
            int minusBtnX = labelX - 60;
            int plusBtnX = labelX + 60;
            
            // Y position for this item
            int yPos = startY + (i * spacing);
    
            // Create and display label for the value name
            Label nameLabel = new Label(valueNames[i], 30);
            addObject(nameLabel, labelX - 200, yPos);
            
            // Initialize value item and create its label
            valueItems[i].createLabel(this, labelX, yPos);
    
            // Minus Button
            int indexMinus = i; // Need a effectively final variable for lambda
            Button minusBtn = new Button(Color.RED, 50, 50, "-");
            addObject(minusBtn, minusBtnX, yPos);
            minusBtn.init();
            minusBtn.setOnClickAction(() -> {
                valueItems[indexMinus].updateValue(this,labelX,yPos,-1);
                displayWage();
                displayUtilities();
            });
            
    
            // Plus Button
            int indexPlus = i; // Need a effectively final variable for lambda
            Button plusBtn = new Button(Color.GREEN, 50, 50, "+");
            addObject(plusBtn, plusBtnX, yPos);
            plusBtn.init();
            plusBtn.setOnClickAction(() -> {
                valueItems[indexPlus].updateValue(this,labelX,yPos,1);
                displayWage();
                displayUtilities();
            });
            
        }
        
        Button startBtn = new Button(Color.YELLOW,200,50,"Run Simulation");
        addObject(startBtn,getWidth()/2,getHeight()-50);
        startBtn.init();
        startBtn.setOnClickAction(() -> {
           MainWorld w = new MainWorld(valueItems[0].value,valueItems[1].value,valueItems[2].value,valueItems[3].value,valueItems[4].value);
           Greenfoot.setWorld(w);
        });
    }



    private void prepare()
    {
        addObject(startBtn, getWidth()/2, getHeight()/2+100);
        startBtn.init();
        startBtn.setOnClickAction(() -> {
            showMenu();
        });
    }
}
