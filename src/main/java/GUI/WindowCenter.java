package GUI;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import DataStructures.*;

public class WindowCenter{
  JPanel foodPanels = new JPanel();

  /** generates the content for the center of the window, which is a list of food eaten panels.
  @param foodsEaten a list of the foodEaten objects
  @return returns a JPanel with the foods eaten, their calories, and the cumulative calories
  */
  public JPanel generate(ArrayList<FoodEaten> foodsEaten){
      foodPanels.removeAll();
      foodPanels.setLayout(new FlowLayout(FlowLayout.LEFT));
    
    //generate a row of foodEaten panels for each food in the foodeaten list
      for(FoodEaten food : foodsEaten){
        JPanel panel = new FoodItemPanel().generate(food);
        foodPanels.add(panel);
      }
      return foodPanels;
  }
}