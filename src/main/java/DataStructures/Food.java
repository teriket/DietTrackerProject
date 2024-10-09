package DataStructures;


public class Food {
    private String foodName;
    private String unitName;
    private int unitsPerServing;
    private int caloriesPerServing;
    private int proteinPerServing;

      /**The data representation of a food, including its name, calories, and protein per serving.
      *@param name             the name of the food
      *@param unitName         the unit of measurement of the food i.e. cup, oz, gallon, etc.
      *@param unitsPerServing  the integer number of units of the food per serving. i.e. 1 cup, 8 oz, etc.
      *@param calories         the integer number of calories per serving.
      *@param protein          the integer number of grams of protein per serving.
      *@author Tanner Hunt
      */
    public Food(String name, String unitName, int unitsPerServing, int calories, int protein) {
        this.foodName = name;
        this.unitName = unitName;
        this.unitsPerServing = unitsPerServing;
        this.caloriesPerServing = calories;
        this.proteinPerServing = protein;
    }
    
    /** calculates the number of calories and protein from a given number of units of the food.  
    *@param amountEaten         the integer number of units of the food eaten. For example 8 oz of chicken.
    *@return                    Returns a tuple of <Integer calories, Integer protein>.
    */
    public Tuple<Integer, Integer> calculateNutrients(int amountEaten) {
        //Don't calculate negative nutrients
        if(amountEaten <= 0){
            return new Tuple<Integer, Integer>(0, 0);
        }

        //Calculate the calories and protein from the given amount eaten
        Integer calories = (this.caloriesPerServing * amountEaten / this.unitsPerServing);
        Integer protein = (Integer) (this.proteinPerServing * amountEaten / this.unitsPerServing);
        
        return new Tuple<Integer, Integer>(calories, protein);
    }

    /**
    * @return                 the name of the food as a String.  i.e. "salad" or "chicken"
    */
    public String getName() {
        return this.foodName;
    }

    /**
    * @return                 the unit of measurement of the food as a String.  i.e. "cup", "gallon"
    */
    public String getUnitName() {
        return unitName;
    }

    /**
    * @return                 returns a string in the format name;units;units-per-serving;calories;protein\n
    */
    public @Override String toString(){
        String foodDetails;
        foodDetails = String.join(";", this.foodName, this.unitName, Integer.toString(this.unitsPerServing), Integer.toString(this.caloriesPerServing), Integer.toString(this.proteinPerServing));
        
        return foodDetails + "\n";
    }
}