package DataStructures;
import java.io.BufferedReader;
import java.io.File;
import java.util.ArrayList;
import java.io.IOException;
import java.io.FileReader;

/** A Trie that looks up foods based on their name.
*/
public class Trie{
    TrieNode root = new TrieRootNode(' ');
    ITrieNode curNode;

    /** Builds a trie from a file of foods.
    * @param database    the file containing the foods to be added to the trie.
    */
    public void buildTrie(File database){
        try{
        BufferedReader reader = new BufferedReader(new FileReader(database.getPath()));
        
        //Build the trie from the food name of each food.  
        String line;
        while((line = reader.readLine()) != null){
            curNode = root;
            //Get the food name from the string representation which is foodName;etc;etc;
            String foodName[] = line.split(";");
            for(int i = 0; i <= foodName[0].length(); i++){
                //Add the food to the trie if the food name is complete
                if(i == foodName[0].length()){
                    Food food = parseFood(foodName);
                    ((TrieNode)curNode).add(food);
                }
                //Add the character to the trie and move down
                else{                    
                    curNode = ((TrieNode)curNode).add(foodName[0].charAt(i));
                }
            }
            
            }
        reader.close();
        }

        //Throw an exception if the file is not found.
        catch(IOException exception){
            exception.printStackTrace();
        }
    }

    /** Converts a Food.toString() back into a Food object.
    @param food the string array representation of the food, which should pass through String.split(";") first
    */
    private Food parseFood(String[] food){
        String foodName = food[0];
        String unitName = food[1];
        int servingSize = Integer.parseInt(food[2]);
        int calories = Integer.parseInt(food[3]);
        int protein = Integer.parseInt(food[4]);
        return new Food(foodName, unitName, servingSize, calories, protein);
    }

    /**Searches the trie for a food based on its name
    @param query the name of the food to be searched for.
    @return returns all the foods that contains the query as a substring in its name
    */
    public ArrayList<Food> suggestFoods(String query){
        curNode = root;
        for(int i = 0; i < query.length(); i++){
            //Move down the trie if the character is found
            if( ((TrieNode)curNode).contains(query.charAt(i)) ){
                curNode = curNode.get(query.charAt(i));
            }
            //If the character is not in the trie, return an empty list
            else{
                return new ArrayList<Food>();
            }
        }
        //return all foods downstream of the current node in the trie.
        return curNode.getAllFoods();
    }

    /** Adds a new food to the trie if it doesn't already exist.
    @param food the food to be added.
    */
    public void addFood(Food food){
        curNode = root;
        for(int i = 0; i < food.getName().length(); i++){
            if( ((TrieNode)curNode).contains(food.getName().charAt(i)) ){
                curNode = curNode.get(food.getName().charAt(i));
            }
            else{
                curNode = ((TrieNode)curNode).add(food.getName().charAt(i));
            }
        }
        ((TrieNode)curNode).add(food);
    }

    /**Checks whether a Food exists in the trie
    @param food checks if the food exists in the trie
    @return returns true if the food exists in the trie, false otherwise
    */
    public boolean contains(Food food){
        curNode = root;
        //Traverse the foods name in the trie
        for(int i = 0; i < food.getName().length(); i++){
            if( ((TrieNode)curNode).contains(food.getName().charAt(i)) ){
                curNode = curNode.get(food.getName().charAt(i));
            }
            else{
                return false;
            }
        }
        //If the food contains a terminal node, return true
        if(((TrieNode)curNode).contains('.')){
            return true;
        }
        return false;
    }

    /**Checks if a Food exists in the trie based on its name
    @param food the name of the food to check if it exists in the trie
    @return returns true if the food exists in the trie, false otherwise
    */
    public boolean contains(String food){
        curNode = root;
        //Traverse the foods name in the trie
        for(int i = 0; i < food.length(); i++){
            if( ((TrieNode)curNode).contains(food.charAt(i)) ){
                curNode = curNode.get(food.charAt(i));
            }
            else{
                return false;
            }
        }
        //If the food contains a terminal node, return true
        if(((TrieNode)curNode).contains('.')){
            return true;
        }
        return false;
    }

    /**Prints the current node and all downstream nodes
    */
    public void printSubtree(){
        root.printSubtree();
    }

    /** Gets a food object given a food name
    @param foodName the name of the food to be retrieved
    @return returns a food object if the food exists in the trie, null otherwise
    */
    public Food getFood(String foodName){
        curNode = root;
        for(int i = 0; i < foodName.length(); i++){
            if( ((TrieNode)curNode).contains(foodName.charAt(i)) ){
                curNode = curNode.get(foodName.charAt(i));
            }
            else{
                return null;
            }
        }
        if(((TrieNode)curNode).contains('.')){
            curNode = curNode.get('.');
            return (Food)curNode.value();
        }
        return null;
    }
}