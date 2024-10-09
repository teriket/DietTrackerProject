package DataStructures;
import java.util.HashMap;
import java.util.ArrayList;

public class TrieNode implements ITrieNode{
    private HashMap<Character, ITrieNode> nodes = new HashMap<Character, ITrieNode>();
    private char character;

    /**A node in the trie representing a character in the foods name.
    @param character    the character that represents the node
    */
    public TrieNode(char character){
        this.character = character;
    }

    /** Checks if the node contains a child with the given character.
    @param character     the character to check if it is childed to this node
    @return returns true if the node contains a child with the given character, false otherwise
    */
    public boolean contains(char character){
        if(nodes.containsKey(character)){
            return true;
        }
        return false;
    }

    /** returns a childed node with the given character.
    @param character the child to return
    @return returns the child with the given character
    */
    public ITrieNode get(char character){
        return nodes.get(character);
    }

    /** checks the value of this node
    @return returns the character stored in this node
    */
    public Object value(){
        return character;
    }

    /** Add a new child to this node.  If this node already contains a child with the given character, return the already created child
    without creating a new one.
    @param character    the character to add as a child
    @return returns the new node added to this one or the already existing one.  
    */
    public ITrieNode add(char character){
        //Don't create a new node if one already exists
        if(nodes.containsKey((Character)character)){
            return nodes.get(character);
        }

        //create a new node and add it to the dictionary
        ITrieNode newNode = new TrieNode(character);  
        nodes.put(character, newNode);
        return newNode;
    }

    /**Adds a terminal node to the current node
    @param food the food to be turned into a terminal node
    @return returns the new terminal node
    */
    public ITrieNode add(Food food){
        ITrieNode newNode = new TrieEndNode(food);
        nodes.put('.', new TrieEndNode(food));
        return newNode;
    }

    /** Gets a list of all foods downstream of this node.
    @return returns an ArrayList of all foods downstream of this node
    */
    public ArrayList<Food> getAllFoods(){
        ArrayList<Food> foods = new ArrayList<Food>();
        for(Character node : nodes.keySet()){
            foods.addAll(nodes.get(node).getAllFoods());
        }
        return foods;
    }

    /**prints all nodes and foods downstream of the current node
    */
    public void printSubtree(){
        System.out.println(character);
        System.out.println("Children: " + nodes.keySet());
        for(ITrieNode node : nodes.values()){
            node.printSubtree();
        }
    }

}