package GUI;
import javax.swing.*;
import java.awt.*;
import java.awt.MouseInfo;
import java.awt.event.*;
import DataStructures.*;

public class SuggestionPopup extends JPopupMenu implements MouseListener{
    private static SuggestionPopup instance = null;
    
    /**A singleton instance of a popup menu containing suggestions for foods to add to todays food eaten
    database, or to add a new food to the food database.  Use getInstance() to get the instance of this popup menu.
    */
    private SuggestionPopup(){
    }

    /** the instance of the popup menu
    @return the instance of the popup menu
    */
    public static SuggestionPopup getInstance(){
        if (instance == null){
            instance = new SuggestionPopup();
        }
        instance.addMouseListener(instance);
        instance.updatePosition();
        instance.buildPopup();
        return instance;
    }

    /**Build a popup menu containing all foods downstream of the users input in the database
    */
    private void buildPopup(){
        instance.removeAll();
        FoodDBManager foodDB = FoodDBManager.getInstance();
        for(Food food : foodDB.suggestFoods(WindowFooter.getUserText())){
            SuggestionMenuItem suggestion = new SuggestionMenuItem(food, this);
            this.add(suggestion);
        }
        this.add(new AddNewFoodMenuItem(this));
        //resize the popup menu to fit the elements correctly
        this.pack();
        instance.setVisible(true);
    }

    /** sets the popup menu at the mouses position
    */
    private void updatePosition(){
        this.setLocation(MouseInfo.getPointerInfo().getLocation().x - 5, MouseInfo.getPointerInfo().getLocation().y - 5);
    }

    /** disable the popup menu
    */
    public void hide(){
        this.setVisible(false);
    }

	public void mouseClicked(MouseEvent e) {
	}

	public void mousePressed(MouseEvent e) {
	}

	public void mouseReleased(MouseEvent e) {
	}

	public void mouseEntered(MouseEvent e) {
	}

    /** behavior if the mouse moves away from the popup.  Hides the popup menu.
    */
	public void mouseExited(MouseEvent e) {
		instance.hide();
		
	}
}