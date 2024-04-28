import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * A class that is used for keeping track of a value. It has a min, max and current value. Used for keeping track of 
 * the values in the title screen.
 * 
 * @author Bryson
 */
public class ValueItem extends Actor{
    int value;
    int minValue;
    int maxValue;
    Label valueLabel;
    
    
    /*
     * Create the Value Item with the proper values
     * 
     * @param initialValue The value to give the item (usually 0)
     * @param minValue The lowest the value item can go
     * @param maxValue The highest the value item can go
     */
    public ValueItem(int initialValue, int minValue, int maxValue) {
        this.value = initialValue;
        this.minValue = minValue;
        this.maxValue = maxValue;
    }

    /**
     * Creates the value item label to display the current value at a certain coordinate
     * 
     * @param world The world to display the value label in
     * @param x The x coordinate the label is at
     * @param y The y coordinate the label is at
     */
    public void createLabel(World world, int x, int y) {
        valueLabel = new Label(String.valueOf(value), 20);
        world.addObject(valueLabel, x, y);
    }
    
    
    /*
     * Updates the value item's value and the label
     * 
     * @param world The world to display the value label in
     * @param x The x coordinate the label is at
     * @param y The y coordinate the label is at
     * @param change The amount to increase the value by
     */
    public void updateValue(World world,int x, int y,int change) {
        value = Math.min(maxValue, Math.max(minValue, value + change)); // If value exceeds max, stay at max
        world.removeObject(valueLabel);
        valueLabel = new Label(String.valueOf(value), 20);
        world.addObject(valueLabel, x, y);
    }
}

