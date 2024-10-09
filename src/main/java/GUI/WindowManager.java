package GUI;
import DataStructures.*;
import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;

public class WindowManager{
    Window window;
    LocalDate loadedDate;
    JPanel suggestions = new JPanel();
    private static WindowManager instance = null;
    
    /**The class responsible for handeling loading logic for the main window.
    */
    private WindowManager(){
    }

    /** gets the singleton instance of the window manager
    @return returns the singleton instance of the window manager.
    */
    public static WindowManager getInstance(){
        if (instance == null){
            instance = new WindowManager();
        }
        return instance;
    }

    /** Loads a page of foods eaten for a given date.  Page includes options to change the date, view foods eaten for that day,
    and add new foods eaten/ new foods to the food database.
    @param date the day to be loaded
    */
    public void loadPage(LocalDate date){
        loadedDate = date;
        //dispose of the windows old contents and make a new window
        if(window != null){
            window.dispose();
        }
        window = new Window();

        //generate the window contents
        DailyIntakeManager foodsEaten = DailyIntakeManager.getInstance();
        JPanel header = new WindowHeader().generate(date, this);
        JPanel footer = new WindowFooter().generate(date, this);
        JPanel center = new WindowCenter().generate(foodsEaten.getFoodsEaten(date));

        //add the generated content to the newly made window
        window.add(header, BorderLayout.NORTH);
        window.add(footer, BorderLayout.SOUTH);
        window.add(center, BorderLayout.CENTER);
        window.add(suggestions, BorderLayout.EAST);

        //display the newly made window
        window.toggleVisible(true);

    }

    /**gets the currently loaded day
    @return the LocalDate currently loaded
    */
    public LocalDate getLoadedDate(){
        return loadedDate;
    }
    
}