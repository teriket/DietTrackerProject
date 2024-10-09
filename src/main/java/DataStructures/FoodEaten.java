package DataStructures;

public class FoodEaten{
    private Food food;
    private int amountEaten;
    private int cumulativeCalories;
    private int cumulativeProtein;
    
    /** How much of a food was eaten, and the cumulative calories/ protein consumed in a day.
    @param food         the food that was eaten.
    @param amountEaten  the integer number of units of the food eaten.
    */
    public FoodEaten(Food food, int amountEaten){
        this.food = food;
        this.amountEaten = amountEaten;
    }

    /**Calculates the total amount of calories and protein eaten for a given day
    @param cumulativeNutrients    tuple<Integer calories, Integer protein>
    @return returns a tuple of <Integer calories, Integer protein>
    */
    public Tuple<Integer, Integer> setCumulativeNutrients(Tuple<Integer, Integer> cumulativeNutrients){
        Tuple<Integer, Integer> nutrients = food.calculateNutrients(amountEaten);
        cumulativeCalories = (Integer)cumulativeNutrients.get(0) + (Integer)nutrients.get(0);
        cumulativeProtein = (Integer)cumulativeNutrients.get(1) + (Integer)nutrients.get(1);
        return new Tuple<Integer, Integer>(cumulativeCalories, cumulativeProtein);
    }

    /**
    @return returns the Food object of the food eaten.
    */
    public Food getFood(){
        return food;
    }

    /**
    @return returns the integer number of units of the food eaten i.e. 8 oz, 1 carton, etc.
    */
    public int getAmountEaten(){
        return amountEaten;
    }

    /**
    @return returns an integer tuple of cumulative calories eaten in a day and cumulative protein eaten in a day.
    */
    public Tuple<Integer, Integer> getCumulativeNutrients(){
        return new Tuple<Integer, Integer>(cumulativeCalories, cumulativeProtein);
    }
    
    /**
    @return returns an integer tuple of the calories and protein eaten in this meal
    */
    public Tuple<Integer, Integer>getNutrients(){
        return food.calculateNutrients(amountEaten);
    }

    /** String representation of the FoodEaten object.
    @return returns a string in the format amountEaten;foodName\n
    */
    public @Override String toString(){
        String details = "%d;%s\n";
        details = String.format(details, amountEaten, food.getName());
        return details;
    }
    
}