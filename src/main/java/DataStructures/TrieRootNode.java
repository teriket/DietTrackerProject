package DataStructures;
import java.util.ArrayList;

public class TrieRootNode extends TrieNode{
    /** A trie node that represents the root of the trie.  It is functionally identical to a TrieNode, but checking for downstream
    foods will return an empty list.  This is because the root node should not make autocomplete suggestions.
    @param character    the character that represents the node
    */
    public TrieRootNode(char character){
        super(character);
    }

    /** returns an empty list of food suggestions.
    */
    public @Override ArrayList<Food> getAllFoods(){
        return new ArrayList<Food>();
    }
}