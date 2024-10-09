package GUI;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.time.LocalDate;

public class WindowFooter implements KeyListener, MouseListener{
    JPanel container = new JPanel();
    static JTextField userInput = new JTextField();
    String defaultPrompt = "Start typing the name of a food here...";

    /** generates a JPanel containing the contents of the windows footer
    @param date the LocalDate.  Only loads the footer if the date is today.
    @param parent a reference to the window containing the footer.
    */
    public JPanel generate(LocalDate date, WindowManager parent){
        container.removeAll();
        //build the footer panel if the date is today
        if(date.equals(LocalDate.now())){
            userInput = new JTextField(defaultPrompt);
            userInput.setEditable(true);
            userInput.addKeyListener(this);
            userInput.addMouseListener(this);
            container.setLayout(new BorderLayout());
            container.add(userInput, BorderLayout.CENTER);
        }
        return container;
    }

    /**
    @return returns the text the user has inputed into the text field
    */
    public static String getUserText(){
        return userInput.getText();
    }

	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

    /** behavior if the user types into the text field.  Generates a popup menu of foods in the database and the option
    to add new foods to the database.
    */
	public void keyPressed(KeyEvent e) {
        if(e.getID() == KeyEvent.KEY_PRESSED){
            SuggestionPopup popup = SuggestionPopup.getInstance();
        }
		
	}

	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

    /** Clears the text field if the user clicks on the text field
    */
	public void mouseClicked(MouseEvent e) {
        userInput.setText("");
		
	}

	public void mousePressed(MouseEvent e) {
		
	}

	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}