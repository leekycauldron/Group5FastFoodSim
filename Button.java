import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * A modular button class that can change color, size, and shape(?).
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Button extends Actor
{
    private Color bgColor;
    private int width;
    private int height;
    private String content;
    private Label contentLabel;
    
    public Button(Color bgColor, int width, int height, String content) {
        this.bgColor = bgColor;
        this.width = width;
        this.height = height;
        this.content = content;
    }
    
    
    // Must call in order to draw button
    // Cannot run at instantiation as constructor runs before added to world
    public void init() {
        GreenfootImage image = new GreenfootImage(width, height);
        image.setColor(bgColor);
        image.fillRect(0,0, width, height);
        setImage(image);
        contentLabel = new Label(content,height/2);
        getWorld().addObject(contentLabel, getX(),getY());
    }
    public void act()
    {
        if(Greenfoot.mouseClicked(this) || Greenfoot.mouseClicked(contentLabel)) {
            // Remove title and button and show sim menu
            
            WelcomeWorld w = (WelcomeWorld) getWorld();
            w.showMenu();
        }
    }
}
