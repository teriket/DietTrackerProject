package GUI;

import java.awt.*;
import javax.swing.*;

public class Window{
    JFrame frame;

    /** Instantiates a window with default settings
    */
    public Window() {
        // innit window size and behaviors
        frame = new JFrame();
        frame.setSize(800, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Hello World");
        frame.setResizable(false);
        frame.setLayout(new BorderLayout());
    }

    /** show or disable the window
    @param bool toggles the window on for true, off for false
    */
    public void toggleVisible(boolean bool){
        frame.setVisible(bool);
    }

    /** add a new component to the window
    @param component the component to be added
    @param layout where the component should be located on the borderlayout
    */
    public void add(JComponent component, String layout){
        frame.add(component, layout);
    }

    /**deletes the window
    */
    public void dispose(){
        frame.dispose();
    }

}
