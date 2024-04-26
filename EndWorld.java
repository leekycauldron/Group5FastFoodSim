import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)


public class EndWorld extends World
{
    
    GreenfootImage selectImage = new GreenfootImage("select.png");
    int moneyDisplay;
    int money;
    Label moneyLabel = new Label("Money Earned: $"+moneyDisplay,40);
    
    
    
    public EndWorld(int money, int[] stats)
    {    
        super(600, 400, 1); 
        this.money = money;
        moneyLabel.setFillColor(Color.GREEN);
        setBackground(selectImage);
        
        // Stats: Burgers, Fries, Cola, Hotdog, customersServed
    
        addObject(new Label("Summary:",50),getWidth()/2,50);
        addObject(new Label("Burgers Sold: "+stats[0],35),getWidth()/2,100);
        addObject(new Label("Fries Sold: "+stats[1],35),getWidth()/2,150);
        addObject(new Label("Cola Sold: "+stats[2],35),getWidth()/2,200);
        addObject(new Label("Hotdog Sold: "+stats[3],35),getWidth()/2,250);
        addObject(new Label("Customers Served: "+stats[4],35),getWidth()/2,300);
        
        if(money <= 0) {
            moneyLabel = new Label("BANKRUPT",40);
            moneyLabel.setFillColor(Color.RED);
        }
        
        addObject(moneyLabel,getWidth()/2,350);
    }
    
    public void act() {
        if(money > 0 && moneyDisplay < money) {
            removeObject(moneyLabel);
            moneyDisplay++;
            moneyLabel = new Label("Money Earned: $"+moneyDisplay,40);
            moneyLabel.setFillColor(Color.GREEN);
            addObject(moneyLabel,getWidth()/2,350);
        }
    }
}
