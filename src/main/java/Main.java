import GUI.WindowManager;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        //load the main window with the current date.  The program will persist as long as the window is open
        WindowManager window = WindowManager.getInstance();
        window.loadPage(LocalDate.now());
    }

}