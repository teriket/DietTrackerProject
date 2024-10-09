package DataStructures;
import java.time.LocalDate;
import java.nio.file.Files;
import java.io.File;
import java.nio.file.StandardOpenOption;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;


/**Singleton that keeps track of reading and writing what was eaten in any given day.
* An object is declared using the getInstance() method i.e. DailyIntakeManager intake = DailyIntakeManager.getInstance()
@author Tanner Hunt
*/
public class DailyIntakeManager{
  private static DailyIntakeManager instance = null;
  private ArrayList<FoodEaten> foodsEaten = new ArrayList<FoodEaten>();
  private File file;

  /**returns the static instance of the intake manager.  Creates a new instance if one does not exist.
  */
  public static DailyIntakeManager getInstance(){
    if (instance == null){
      instance = new DailyIntakeManager();
    }
    return instance;
  }

  /** A private constructor prevents new objects being created.  New instances of this class should be 
  generated using DailyIntakeManager.getInstance() instead of the new keyword.
  */
  private DailyIntakeManager(){
  }

  /** Writes a new foodEaten to todays file.  File writes are in the format amountEaten;foodName.
  * @param foodEaten: The foodEaten to be written to the file.
  */
  public void write(FoodEaten foodEaten){
    //init a .txt file path which is in the src/main/java/Data file and its file name is the current date
    LocalDate date = LocalDate.now();
    String filePath = "src/main/java/Data/" + date.toString() + ".txt";
    file = new File(filePath);

    //Write to the end of the file the food eaten and quantity
    try{
    Files.writeString(file.toPath(), foodEaten.toString(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
    }
    catch(IOException exception){
      exception.printStackTrace();
    }
  }

  /** Converts a file of foods eaten to a list of foods eaten and the quantity eaten
  * @param day: the LocalDate of the file to be read.
  * @return An ArrayList of FoodEaten objects.
  */
  public ArrayList<FoodEaten> getFoodsEaten(LocalDate day){
    foodsEaten.clear();
    String filePath = "src/main/java/Data/" + day.toString() + ".txt";
    //This queue looks back at the previous food eaten to calculate the cumulative nutrients.
    Queue<FoodEaten> foodsEatenHolder = new LinkedList<FoodEaten>();

    //return an empty list if no record of a date exists
    if(!(new File(filePath).exists())){
      return foodsEaten;
    }

    //Read through each line of the foods eaten for that day, calculate their cumulative nutrients, and add them to the foodsEaten array.
    try{
      //make a file reader and init a line to hold each line read
      BufferedReader reader = new BufferedReader(new FileReader(filePath));
      String line;

      //read through every element in the file
      while((line = reader.readLine()) != null){
        FoodEaten foodEaten = getFood(line);
        //if this is the first element, set the previous cumulative nutrients of the first item in the file to 0,0 
        if(foodsEatenHolder.size() == 0){
          Tuple<Integer, Integer> cumulativeNutrients = new Tuple<Integer, Integer>(0, 0);
          foodEaten.setCumulativeNutrients(cumulativeNutrients);
          foodsEatenHolder.add(foodEaten);
          foodsEaten.add(foodEaten);
        }
        //calculate the cumulative nutrients from the previous one.
        else{
          foodsEatenHolder.add(foodEaten);
          foodEaten.setCumulativeNutrients(foodsEatenHolder.poll().getCumulativeNutrients());
          foodsEaten.add(foodEaten);
        }
      }
      reader.close();
      return foodsEaten;
    }
    //print an exception if the file is not found
    catch(IOException e){
      e.printStackTrace();
    }
    return foodsEaten;
  }

  /** Converts a record in the foodEaten database into a FoodEaten object.
  */
  private FoodEaten getFood(String line){
    //seperate the line into its two tokens: the food name and quantity eaten
    String foodName = line.split(";")[1];
    String quantity = line.split(";")[0];

    //Get access to the database of foods and their respective nutrients, then lookup the food
    FoodDBManager foodDBManager = FoodDBManager.getInstance();
    FoodEaten foodEaten = new FoodEaten(foodDBManager.getFood(foodName), Integer.parseInt(quantity));
    return foodEaten;
  }
}