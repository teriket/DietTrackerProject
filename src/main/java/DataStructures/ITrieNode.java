package DataStructures;
import java.util.ArrayList;

interface ITrieNode{
    public Object value();
    public ArrayList<Food> getAllFoods();
    public void printSubtree();
    public ITrieNode get(char character);
}