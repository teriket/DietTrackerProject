package GUI;
import java.awt.event.*;
import javax.swing.*;
import java.awt.Color;

public class AddNewFoodMenuItem extends JMenuItem implements MouseListener, ActionListener{
    private SuggestionPopup parent;

	/**the option within the suggestion popup menu to generate a new food for the food database
	@param parent the popup menu this belongs to.  Hides that popup menu when the user clicks on this option.
	*/
    public AddNewFoodMenuItem(SuggestionPopup parent){
        this.init("Add New Food", null);
        this.addMouseListener(this);
        this.parent = parent;
    }

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

	/** behavior when the button is pressed.  Hides the popup menu and creates the "add new food" dialog.
	*/
	public void mouseClicked(MouseEvent e) {
        parent.hide();
        AddNewFoodDialogue newFoodDialogue = new AddNewFoodDialogue(WindowFooter.getUserText());
		
	}

	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	/** behavior when the mouse hovers over this button.  Makes the button slightly darker
	*/
	public void mouseEntered(MouseEvent e) {
        this.setBackground(new Color(118, 198, 245));
		
	}

	/** behavior when the mouse moves off of this button.  Returns it to the original color.
	*/
	public void mouseExited(MouseEvent e) {
        this.setBackground(new Color(238,238,238));
		
	}
    
}