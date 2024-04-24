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
    public WelcomeWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1); 
        prepare();
    }
    
        
    public void showMenu() {
        
        setBackground(selectImage);
        removeObjects(getObjects(null)); // Clears the screen
    
        // Array of value items
        ValueItem[] valueItems = {
            new ValueItem(0, 0, 10), // Cook Count
            new ValueItem(0, 0, 5),  // Counter Count
            new ValueItem(0, 0, 8),  // Grill Count
            new ValueItem(0, 0, 6)   // Fryer Count
        };
        String[] valueNames = {"Cook Count", "Counter Count", "Grill Count", "Fryer Count"};
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
            });
            
    
            // Plus Button
            int indexPlus = i; // Need a effectively final variable for lambda
            Button plusBtn = new Button(Color.GREEN, 50, 50, "+");
            addObject(plusBtn, plusBtnX, yPos);
            plusBtn.init();
            plusBtn.setOnClickAction(() -> {
                valueItems[indexPlus].updateValue(this,labelX,yPos,1);
            });
            
        }
        
        Button startBtn = new Button(Color.YELLOW,200,50,"Run Simulation");
        addObject(startBtn,getWidth()/2,getHeight()-100);
        startBtn.init();
        startBtn.setOnClickAction(() -> {
           MainWorld w = new MainWorld(valueItems[0].value,valueItems[1].value,valueItems[2].value,valueItems[3].value);
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
