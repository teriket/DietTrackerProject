package DataStructures;


public class Tuple<W, X> {
    private W w;
    private X x;

    /**
     * An immutable data storage container for two data types
     */
    public Tuple(W w, X x) {
        this.w = w;
        this.x = x;
    }

    /** gets the object stored at index 0 or index 1
    @param index the index of the object to be returned, either 0 or 1.
    @return returns the object stored at the given index
    */
    public Object get(int index) {
        if (index < 0 || index > 1) {
            throw new IllegalArgumentException("Index out of bounds");
        }
        switch (index) {
            case 0:
                return w;
            case 1:
                return x;
        }
        return -1;

    }
}