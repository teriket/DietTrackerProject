package GUI;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import DataStructures.*;

public class AddNewFoodDialogue extends JDialog implements ActionListener{
    private JTextField foodName = new JTextField(20);
    private JTextField unitName = new JTextField(20);
    private JTextField unitsPerServing = new JTextField(20);
    private JTextField caloriesPerServing = new JTextField(20);
    private JTextField proteinPerServing = new JTextField(20);
    private JPanel contents = new JPanel();
    JLabel warning = new JLabel();

    /** The dialog box to add a new food to the food database
    @param suggestedFoodName inits the food name field with the suggested food name.  This is an editable value by the user.
    */
    public AddNewFoodDialogue(String suggestedFoodName){
        //init the dialog boxes size and settings, then add the content panel to its heirarchy tree
        super();
        this.setSize(400, 250);
        this.setTitle("Add New Food");
        this.setResizable(false);
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.add(contents);

        //generate the panel for the user to enter the foods name and add it to the content pane
        JPanel foodNamePanel = new JPanel();
        this.foodName.setText(suggestedFoodName);
        foodNamePanel.setLayout(new BorderLayout());
        foodNamePanel.setPreferredSize(new Dimension(400, 20));
        foodNamePanel.add(new JLabel("Food Name: "), BorderLayout.WEST);
        foodNamePanel.add(foodName, BorderLayout.EAST);
        contents.add(foodNamePanel);

        //generate the panel for the user to enter the foods unit name and add it to the content pane
        JPanel servingSizePanel = new JPanel();
        servingSizePanel.setLayout(new BorderLayout());
        servingSizePanel.setPreferredSize(new Dimension(400, 20));
        servingSizePanel.add(new JLabel("Units of serving size: "), BorderLayout.WEST);
        servingSizePanel.add(unitName, BorderLayout.EAST);
        contents.add(servingSizePanel);

        //generate the panel for the user to enter the foods serving size and add it to the content pane
        JPanel unitsPerServingPanel = new JPanel();
        unitsPerServingPanel.setLayout(new BorderLayout());
        unitsPerServingPanel.setPreferredSize(new Dimension(400, 20));
        unitsPerServingPanel.add(new JLabel("serving size: "), BorderLayout.WEST);
        unitsPerServingPanel.add(unitsPerServing, BorderLayout.EAST);
        contents.add(unitsPerServingPanel);

        //generate the panel for the user to enter the foods calories per serving and add it to the content pane
        JPanel caloriesPerServingPanel = new JPanel();
        caloriesPerServingPanel.setLayout(new BorderLayout());
        caloriesPerServingPanel.setPreferredSize(new Dimension(400, 20));
        caloriesPerServingPanel.add(new JLabel("calories per serving: "), BorderLayout.WEST);
        caloriesPerServingPanel.add(caloriesPerServing, BorderLayout.EAST);
        contents.add(caloriesPerServingPanel);
        
        //generate the panel for the user to enter the foods protein per serving and add it to the content pane
        JPanel proteinPerServingPanel = new JPanel();
        proteinPerServingPanel.setLayout(new BorderLayout());
        proteinPerServingPanel.setPreferredSize(new Dimension(400, 20));
        proteinPerServingPanel.add(new JLabel("protein per serving:"), BorderLayout.WEST);
        proteinPerServingPanel.add(proteinPerServing, BorderLayout.EAST);
        contents.add(proteinPerServingPanel);

        //add the submit button to the content pane
        JButton submit = new JButton("Add");
        submit.addActionListener(this);
        contents.add(submit);

        //add the warning label to the content pane if the user does not enter a valid input
        warning = new JLabel();
        warning.setForeground(Color.red);
        contents.add(warning);

        //make the dialog visible
        this.setVisible(true);
    }
    
    /**Creates a new food generated by the users input
    @return returns the food object generated by the users input
    */
    private Food createFood(){
        String newFoodName = foodName.getText();
        String newUnitName = unitName.getText();
        int newUnitsPerServing = Integer.parseInt(unitsPerServing.getText());
        int newCalories = Integer.parseInt(caloriesPerServing.getText());
        int newProtein = Integer.parseInt(proteinPerServing.getText());
        
        Food newFood = new Food(newFoodName, newUnitName, newUnitsPerServing, newCalories, newProtein);
        return newFood;
    }

    /**The behavior for the submit button.  Checks whether the users input is valid,
    then adds the food generated by the users input to the foods database.
    */
	public void actionPerformed(ActionEvent e) {
        //check if the user entered a valid input
        if(!isFoodNameClean()){
            return;
        }
        if(!isUnitNameClean()){
            return;
        }
        if(!isServingSizeClean()){
            return;
        }
        if(!isCaloriesPerServingClean()){
            return;
        }
        if(!isProteinPerServingClean()){
            return;
        }

        //Add the new food to the food database
        Food newFood = createFood();
        FoodDBManager.getInstance().write(newFood);
        this.setVisible(false);
        AddFoodEatenDialogue newFoodEaten = new AddFoodEatenDialogue(newFood);
		
	}

    /**Checks if the name of the food is clean.  food names should not include a period or semicolon, be nonempty, and not already
    be in the food database.  Displays warning dialog to the user if it is not clean.
    @return returns true if the food name is clean, false otherwise.
    */
    private boolean isFoodNameClean(){
        //check if the foods name is blank.
        if(foodName.getText().isBlank()){
            warning.setText("Please enter a food name.");
            return false;
        }
        //checks if the foods name contains a period or semicolon
        else if(foodName.getText().contains(".") || foodName.getText().contains(";")){
            warning.setText("The food Name may not contain a period or semicolon");
            return false;
        }
        //Check the input is too long
        else if(foodName.getText().length() >= 25){
            warning.setText("the food name is too long");
            return false;
        }
        //checks if the food name is already in the food database
        else if((FoodDBManager.getInstance().contains(foodName.getText()))){
            warning.setText(foodName.getText() + " is already in the database.");
            return false;
        }
        //the input is clean
        return true;
    }

    /** Checks whether an appropriate unitname is entered i.e. cup, oz, etc.  Displays appropriate warnings
    if it is not clean.
    @return returns true if a valid input is entered, false otherwise.
    */
    private boolean isUnitNameClean(){
        //check if the unit name is blank
        if(unitName.getText().isBlank()){
            warning.setText("You must choose a serving size, e.g. cup, ounce, etc.");
            return false;
        }
        //Check the input is too long
        else if(unitName.getText().length() >= 20){
            warning.setText("the serving size is too long");
            return false;
        }
        //checks if the unit name contains a semicolon
        else if(unitName.getText().contains(";")){
            warning.setText("the serving size cannot contain a semicolon"); 
            return false;
        }
        // a valid input was entered
        return true;
    }

    /**check if the serving size is clean.  Serving size should be a positive integer.  Displays warning dialog to the user if it is not         clean.
    @return returns true if the user input a valid input, false otherwise.
    */
    private boolean isServingSizeClean(){
        //check if the input is nonempty
        if(unitsPerServing.getText().isBlank()){
            warning.setText("You must choose a serving size");
            return false;
        }
        //check if the input contains a semicolon
        if(unitsPerServing.getText().contains(";")){
            warning.setText("the serving size must not contain a semicolon");
            return false;
        }
        //Check the input is too long
        else if(unitsPerServing.getText().length() >= 5){
            warning.setText("the units per serving is too long");
            return false;
        }
        //check if the input is a number
        for(char character : unitsPerServing.getText().toCharArray()){
            if(!Character.isDigit(character)){
                warning.setText("The serving size must be a number");
                return false;
            }
        }
        //the input is clean
        return true;
    }

    /** Check if the calories per serving is clean.  Calories should be an integer, nonempty, and not contain a semicolon.
    @return returns true if the user input a valid input, false otherwise.
    */
    private boolean isCaloriesPerServingClean(){
        //check if the input is nonempty
        if(caloriesPerServing.getText().isBlank()){
            warning.setText("You must choose the calories per serving");
            return false;
        }
        //check if the input contains a semicolon
        else if(caloriesPerServing.getText().contains(";")){
            warning.setText("the calories must not contain a semicolon");
            return false;
        }
        //Check the input is too long
        else if(caloriesPerServing.getText().length() >= 5){
            warning.setText("the calories per serving is too long");
            return false;
        }
        //check if the input is a number
        for(char character : caloriesPerServing.getText().toCharArray()){
            if(!Character.isDigit(character)){
                warning.setText("The calories per serving must be a number");
                return false;
            }
        }
        //the input is clean
        return true;
    }

    /**Checks if the protein per serving is a valid input.  protein should be a nonempty integer without a semicolon.  A warning dialog is displayed if the input is not valid.
    @return returns true if the user input a valid input, false otherwise.
    */
    private boolean isProteinPerServingClean(){
        //check if the input is nonempty
        if(proteinPerServing.getText().isBlank()){
            warning.setText("You must choose the protein per serving");
            return false;
        }
        //check if the input contains a semicolon
        else if(proteinPerServing.getText().contains(";")){
            warning.setText("the protein must not contain a semicolon");
            return false;
        }
        //Check the input is too long
        else if(proteinPerServing.getText().length() >= 3){
            warning.setText("the protein per serving is too long");
            return false;
        }
        //check if the input is an integer
        for(char character : proteinPerServing.getText().toCharArray()){
            if(!Character.isDigit(character)){
                warning.setText("The protein per serving must be a number");
                return false;
            }
        }
        //the input is clean
        return true;
    }
    }