package GUI;
import javax.swing.*;
import DataStructures.*;
import java.awt.event.*;
import java.awt.Color;

public class SuggestionMenuItem extends JMenuItem implements MouseListener, ActionListener{
    private SuggestionPopup parent;
	private Food food;

	/**A button for the suggestion popup menu that displays a food item.
	@param food the food item to be displayed
	@param parent the popup menu this belongs to.  Hides that popup menu when this button is clicked.
	*/
    public SuggestionMenuItem(Food food, SuggestionPopup parent){
		//cache the food and the parent passed through the constructor
		this.food = food;
        this.parent = parent;

		//init this button with the food name and add its behavior
		this.init(food.getName(), null);
        this.addMouseListener(this);
    }

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

	/** This buttons behavior when the button is clicked.  Hides the popup menu and makes an "add food eaten" dialog box
	*/
	public void mouseClicked(MouseEvent e) {
        AddFoodEatenDialogue foodEaten = new AddFoodEatenDialogue(food);
		parent.hide();
		
	}

	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	/** behavior when the user hovers over this button.  Makes the button slightly darker
	*/
	public void mouseEntered(MouseEvent e) {
		this.setBackground(new Color(118, 198, 245));
		
	}

	/** behavior when the user unhovers from this button.  Returns it to the original color.
	*/
	public void mouseExited(MouseEvent e) {
		this.setBackground(new Color(238,238,238));
		
	}
}
