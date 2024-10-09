package DataStructures;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;

/** The database responsible for storing all the foods, their calories, their protein, serving size, and serving size units into
a searchable trie.  Also writes foods to the food database.  This manager is stored as a singleton and must be accessed using
the getInstance() method.
@author Tanner Hunt
*/
public class FoodDBManager extends Trie{
    private File database;
    private static FoodDBManager instance = null;

    /** private constructor for the database manager.
    */
    private FoodDBManager(){
        database = new File("src/main/java/Data/foodDB.txt");
        this.buildTrie(database);
        
    }

    /** Accesses the singleton instance of the food database manager.
    @return returns the instance of the food database manager.
    */
    public static FoodDBManager getInstance(){
        if (instance == null){
            instance = new FoodDBManager();
        }
        return instance;
    }

    /** Write a food to the database of foods and their nutrients.
    @param food     the food to be added to the database.
    @return         returns true if the food was added successfully, false otherwise
    */
    public boolean write(Food food){
        //Don't create duplicates of food
        if(this.contains(food)){
            return false;
        }

        //The database representation of the food to be written.
        String writeLine = food.toString();

        //write the food to the database
        try{
            Files.writeString(database.toPath(), writeLine, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
            this.addFood(food);
            return true;
        }
        catch(IOException exception){
            System.out.println(exception);
        }
        return false;
    }

}