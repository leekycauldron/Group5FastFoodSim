import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Speech Bubble Class that attaches to a customer and contains their order.
 * 
 * @author Bryson
 */

public class SpeechBubble extends Actor {
    private GreenfootImage image;
    private String text;

    public SpeechBubble(String text) {
        this.text = text;
        updateImage();
    }

    public void setText(String text) {
        this.text = text;
        updateImage();
    }
    
    private void updateImage() {
        // Create Speech Bubble Graphic; Chat GPT was used to refine the looks.
        GreenfootImage bubble = new GreenfootImage(text, 20, Color.BLACK, new Color(255, 255, 255, 200));
        GreenfootImage pointer = new GreenfootImage(10, 10);
        pointer.setColor(new Color(255, 255, 255, 200));
        pointer.fillPolygon(new int[] {0, 10, 5}, new int[] {0, 0, 10}, 3);
        
        image = new GreenfootImage(bubble.getWidth(), bubble.getHeight() + pointer.getHeight());
        image.drawImage(bubble, 0, 0);
        image.drawImage(pointer, (bubble.getWidth() - pointer.getWidth()) / 2, bubble.getHeight());
        setImage(image);
    }
}

