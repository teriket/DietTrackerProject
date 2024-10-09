package GUI;

import java.awt.Color;
import javax.swing.*;
import DataStructures.*;
import java.util.ArrayList;

public class FoodItemPanel {

    /**Creates a panel that displays the food eaten, its calories/ protein, and the cumulative calories/protein eaten that day.
    @param foodEaten the food eaten
    @return a panel that contains the food eaten, its nutrients, and the cumulative nutrients for the day
    */
    public JPanel generate(FoodEaten foodEaten) {
        JPanel panel = new JPanel();
        ArrayList<JComponent> components = new ArrayList<JComponent>();

        panel.setBackground(Color.LIGHT_GRAY);

        //build all the components to go into the panel
        components.add(new JLabel(Integer.toString(foodEaten.getAmountEaten()))); // How many units of the food were eaten
        components.add(new JLabel(foodEaten.getFood().getUnitName())); // The name of the units eaten
        components.add(new JLabel(foodEaten.getFood().getName())); // The foods name
        components.add(new JLabel(foodEaten.getNutrients().get(0).toString() + " kcal")); // How many calories were in this serving
        components.add(new JLabel(foodEaten.getNutrients().get(1).toString() + "g protein")); // How many grams of protein were in this serving
        components.add(new JLabel(foodEaten.getCumulativeNutrients().get(0).toString() + " total calories")); // The running total of calories consumed today
        components.add(new JLabel(foodEaten.getCumulativeNutrients().get(1).toString() + "g total protein")); // The running total of protein consumed today

        //add every element into the panamountel
        for (JComponent component : components) {
            panel.add(component);
            //dont add a divider between after the last element or between the timer and the first element
            if(component != components.get(components.size() - 1) && component != components.get(0)){        
            panel.add(new JLabel(" || "));
            }
        }

        return panel;
    }
}