import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class EndWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class EndWorld extends World
{
    private Label endTitle;
    
    public EndWorld(int money)
    {    
        super(600, 400, 1); 
        
        
        
        
        if (money <= 0) {
            endTitle = new Label("BANKRUPT",40);
        } else if (money <= 100) {
            endTitle = new Label("Barely...",40);
        } else {
            endTitle = new Label("RICH!",40);
        }
        
        addObject(endTitle,getWidth()/2,getHeight()/2);
        
    }
}
