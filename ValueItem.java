import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class ValueItem here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ValueItem extends Actor{
    int value;
    int minValue;
    int maxValue;
    Label valueLabel;

    public ValueItem(int initialValue, int minValue, int maxValue) {
        this.value = initialValue;
        this.minValue = minValue;
        this.maxValue = maxValue;
    }


    public void createLabel(World world, int x, int y) {
        valueLabel = new Label(String.valueOf(value), 20);
        world.addObject(valueLabel, x, y);
    }


    public void updateValue(World world,int x, int y,int delta) {
        value = Math.min(maxValue, Math.max(minValue, value + delta));
        world.removeObject(valueLabel);
        valueLabel = new Label(String.valueOf(value), 20);
        world.addObject(valueLabel, x, y);
    }
}

