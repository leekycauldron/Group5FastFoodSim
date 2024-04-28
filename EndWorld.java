import greenfoot.*; 

/**
 * End World for the Simulation
 * - Will show user stats like burgers sold, fries sold, money made etc...
 * - If user failed ($0): Will show BANKRUPT Message
 * 
 * @author Bryson, Bonnie, Matthew
 */ 


public class EndWorld extends World
{
    
    GreenfootImage selectImage = new GreenfootImage("select.png");
    int moneyDisplay;
    int money;
    Label moneyLabel = new Label("Money Earned: $"+moneyDisplay,40);
    
    
    /**
     * End World Constructor - set up all the labels and display the user's stats in a pleasing way.
     * 
     * @param money The amount of money that the simulation has accumulated
     * @param stats An array that contains all the stats like food sold and customers served
     */
    public EndWorld(int money, int[] stats)
    {    
        super(600, 400, 1); 
        this.money = money;
        moneyLabel.setFillColor(Color.GREEN);
        setBackground(selectImage);
        
        // Stats: Burgers, Fries, Cola, Hotdog, customersServed (in this order)
    
        addObject(new Label("Summary:",50),getWidth()/2,50);
        addObject(new Label("Burgers Sold: "+stats[0],35),getWidth()/2,100);
        addObject(new Label("Fries Sold: "+stats[1],35),getWidth()/2,150);
        addObject(new Label("Cola Sold: "+stats[2],35),getWidth()/2,200);
        addObject(new Label("Hotdog Sold: "+stats[3],35),getWidth()/2,250);
        addObject(new Label("Customers Served: "+stats[4],35),getWidth()/2,300);
        // Change money label to red BANKRUPT if no money
        if(money <= 0) {
            moneyLabel = new Label("BANKRUPT",40);
            moneyLabel.setFillColor(Color.RED);
        }
        
        addObject(moneyLabel,getWidth()/2,350);
    }
    
    public void act() {
        // Increase the money label by one each frame (simply for visual purposes)
        if(money > 0 && moneyDisplay < money) {
            removeObject(moneyLabel);
            moneyDisplay++;
            moneyLabel = new Label("Money Earned: $"+moneyDisplay,40);
            moneyLabel.setFillColor(Color.GREEN);
            addObject(moneyLabel,getWidth()/2,350);
        }
    }
}
