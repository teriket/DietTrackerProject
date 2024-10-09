package GUI;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;

public class WindowHeader{
    JPanel headerPanel = new JPanel();
    JLabel title;
    JButton yesterday = new JButton("previous day");
    JButton tomorrow = new JButton("next day");
    JButton skipToToday = new JButton("Go to today");
    WindowManager parent;

    /** Decides which buttons to add to the header panel and adds them.  Does not add tomorrow/ today buttons
    if the pages date is today.
    */
    private void addButtons(LocalDate date){
        //generate the tomorrow and skip to today buttons if they are appropriate
        if(!date.isEqual(LocalDate.now())){
            JPanel rightSideButtons = new JPanel();
            rightSideButtons.setLayout(new FlowLayout(FlowLayout.RIGHT));
            rightSideButtons.add(tomorrow);
            rightSideButtons.add(skipToToday);
            headerPanel.add(rightSideButtons, BorderLayout.EAST);
        }

        //generate the yesterday button
        JPanel leftSideButtons = new JPanel();
        leftSideButtons.add(yesterday);
        addButtonBehaviors();
        headerPanel.add(leftSideButtons, BorderLayout.WEST);
    }

    /** creates the header panel, which displays the date of the loaded page, and buttons to move between days.
    @param date the date of the loaded page
    @param parent the window containing the header panel
    @return returns a JPanel with the loaded date, and buttons to move between days
    */
    public JPanel generate(LocalDate date, WindowManager parent){
        this.parent = parent;
        headerPanel.removeAll();
        title = new JLabel(date.toString());
        title.setHorizontalAlignment(JLabel.CENTER);
        headerPanel.setLayout(new BorderLayout());
        headerPanel.add(title, BorderLayout.CENTER);
        addButtons(date);
        return headerPanel;
    }

    /**Adds the behaviors to the yesterday, tomorrow, and skip to today buttons.
    */
    private void addButtonBehaviors(){
        //yesterday buttons behavior.  Loads the day before the currently loaded one.
        yesterday.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
             parent.loadPage(parent.getLoadedDate().minusDays(1));  
            }
        });

        //tomorrow buttons behavior.  Loads the day after the currently loaded one.
        tomorrow.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
             parent.loadPage(parent.getLoadedDate().plusDays(1));  
            }
        });

        //skip to today buttons behavior.  Loads the foods eaten today.
        skipToToday.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
             parent.loadPage(LocalDate.now());  
            }
        });
    }
}