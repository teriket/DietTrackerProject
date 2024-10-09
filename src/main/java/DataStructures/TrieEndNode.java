package DataStructures;
import java.util.ArrayList;

public class TrieEndNode implements ITrieNode{
    Food food;

    /**The end node of a trie containin the food object.
    @param food     the food object to be stored in the trie.
    */
    public TrieEndNode(Food food){
        this.food = food;
    }

    /**
    @return returns the food object stored in the trie as an Object
    */
    public Object value(){
        return food;
    }

    /** returns an ArrayList of all the foods stored in the trie, called by nodes upstream of the current node to generate a longer list
    @return returns the current food item as a list of foods
    */
    public ArrayList<Food> getAllFoods(){
        ArrayList<Food> foods = new ArrayList<Food>();
        foods.add(food);
        return foods;
    }

    /** prints the current node and the food object attached to it
    */
    public void printSubtree(){
        System.out.println("Terminal node: " + food.toString());
    }

    /** Searches the children of this trie for a given character.  End nodes don't have children, so should return this.
    @return returns this
    */
    public ITrieNode get(char character){
        return this;
    }
    
}