import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Cook here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Cook extends Employee
{
    public boolean working = false;
    private int direction = 0;
    private boolean isImageFlipped = false;

    
    // Kitchen boundaries
    private int minX = 50; // Minimum X-coordinate
    private int maxX = 550; // Maximum X-coordinate
    private int minY = 50; // Minimum Y-coordinate
    private int maxY = 250; // Maximum Y-coordinate
    
    public void wander() {
        // Randomly change direction
        if (Greenfoot.getRandomNumber(100) == 1) { // 10% chance to change direction
            direction += Greenfoot.getRandomNumber(180) - 90; // Change direction randomly by -90 to +90 degrees
            direction = (direction + 360) % 360; // Ensure the direction is within 0-359 degrees
        }
        
        // Check boundaries and adjust position and direction if necessary
        if (getX() < minX || getX() > maxX || getY() < minY || getY() > maxY) {
            // Reverse direction
            direction = (direction + 180) % 360;
            //move(50); // Move back inside the boundary
        }
        
        // Set the actor's image rotation to match the direction of movement
        setRotation(direction);
        
        // Move forward in the current direction
        move(1);
        
        
        // Face either left or right (no diagonals, up/downs..)
        if ((direction > 90 && direction < 270 && !isImageFlipped) || (direction <= 90 || direction >= 270) && isImageFlipped) {
            getImage().mirrorHorizontally();
            isImageFlipped = !isImageFlipped; // Update the flipped state
        }else {
            setRotation(0); // Facing right
        }
        
        
    }

    public void act()
    {
        if (working) {
            
        } else {
            wander();
        }
    }
}
