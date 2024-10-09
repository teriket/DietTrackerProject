package GUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import DataStructures.*;
import java.time.LocalDate;

import javax.swing.*;

public class AddFoodEatenDialogue extends JDialog implements ActionListener{
    private JTextField amountEaten = new JTextField(20);
    private JPanel contents = new JPanel();
    private JLabel warning;
    private Food food;

    /**The dialog box for adding a food eaten to today's food log.
    @param food     the food to be added to the database.
    */
    public AddFoodEatenDialogue(Food food){
        //set the dialog box's size and style
        super();
        this.setSize(400, 250);
        this.setTitle("Add food eaten");
        this.setResizable(false);
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        //add all the components to the dialog box, declared below
        this.add(contents);

        //cache the food passed through the constructor
        this.food = food;

        //build the header component, which includes directions to the user
        JPanel headerPanel = new JPanel();
        String prompt = "How much/ many " + food.getUnitName() + " of " + food.getName() + " did you eat?";
        headerPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        headerPanel.add(new JLabel(prompt));
        contents.add(headerPanel, BorderLayout.NORTH);

        //build the body component, where the user will add the amount of food eaten in a form
        JPanel bodyPanel = new JPanel();
        bodyPanel.setLayout(new BorderLayout());
        bodyPanel.setPreferredSize(new Dimension(400, 20));
        bodyPanel.add(new JLabel("amount eaten:"), BorderLayout.WEST);
        bodyPanel.add(amountEaten, BorderLayout.EAST);
        contents.add(bodyPanel);

        //add the submit button
        JButton submit = new JButton("Submit");
        submit.addActionListener(this);
        contents.add(submit);

        //The field to display warnings to the user if they add an invalid input.
        warning = new JLabel();
        warning.setForeground(Color.RED);
        contents.add(warning);

        //display the dialog box
        this.setVisible(true);
    }

    /** generates a FoodEaten object given the users input.
    @return returns a FoodEaten object with the users amount eaten.
    */
    public FoodEaten createFoodEaten(){
        return new FoodEaten(food, Integer.parseInt(amountEaten.getText()));
    }

    /**The behavior for when the submit button is pressed.  Should check if the
    user input a valid input, then submit a new foodeaten object to todays database,
    then refresh the window.
    */
	public void actionPerformed(ActionEvent e) {
        //Do nothing if an invalid input is entered.  A warning dialog is displayed from isInputClean()
        if(!isInputClean()){
            return;
        }
        
        //add the food eaten to the database and refresh the window
		DailyIntakeManager.getInstance().write(createFoodEaten());
        WindowManager.getInstance().loadPage(LocalDate.now());
        this.setVisible(false);
		
	}

    /** checks if the users input is clean.  Users input should be nonempty and an integer
    @return returns true if the users input is valid, false otherwise.
    */
    private boolean isInputClean(){
        //Check if the input is empty
        if(amountEaten.getText().isEmpty()){
            warning.setText("Please enter an amount");
            return false;
        }
        //Check the input is too long
        else if(amountEaten.getText().length() >= 4){
            warning.setText("the input length is too long");
            return false;
        }
        //check every character input and make sure it is an integer
        for(char character : amountEaten.getText().toCharArray()){
            if(!Character.isDigit(character)){
                warning.setText("The serving must be strictly an integer");
                return false;
            }
        }
        return true;
    }
    
}