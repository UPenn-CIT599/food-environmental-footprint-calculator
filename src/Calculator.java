import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Random;

public class Calculator {
	/**
	 * 
	 * @param the user input food name 
	 * @return GHG Emission of selected food
	 */
	public double getFoodGHGEmission(String name, Double weight){
		HashMap<String,Food> readData = new FoodDataReader ("FoodGHG.csv").getData();
		double emission = readData.get(name).getCarbonEmissions() * weight;
		return emission;
	}
	
	/**
	 * 
	 * @param the user input food name and kg
	 * @return Calories of selected food
	 */
	public double getFoodCalories(String name){
		HashMap<String,Food> readData = new FoodDataReader ("FoodGHG.csv").getData();
		double unitCalories = readData.get(name).getCalories();
		return unitCalories;
	}
	/**
	 * 
	 * @param FoodGHG
	 * @return AnnualGHG of selected food
	 */
	public double getAnnualGHG(double foodGHG){
		double annualGHG = foodGHG * 365;
		return annualGHG;
	}
	
	/**
	 * 
	 * @param AnnualGHG
	 * @return Car KM Equivalent of selected food
	 */
	public double getCarKMEquivalent(double AnnualGHG){
		double carKM = AnnualGHG/0.25;
		return carKM;
	}
	
	/**
	 * 
	 * @param AnnualGHG
	 * @return bulb light days
	 */
	public double getEquivalentBulbLightDays(double AnnualGHG){
		double bulbLightDays = AnnualGHG/0.093;
		return bulbLightDays;
	}
	
	/**
	 * 
	 * @param AnnualGHG
	 * @return Flight KM
	 */
	public double getEquivalentFlightKM(double AnnualGHG){
		double flightKM = AnnualGHG/0.186;
		return flightKM;
	}

	/**
	 * 
	 * @param foodDataReader
	 * @return the general food group (fish, meat, etc.,)
	 */
	public ArrayList<String> generalGroupSelect(FoodDataReader foodDataReader){
		ArrayList<String> foodgroup = new ArrayList<String>();
		HashMap<String,Food> readData = new FoodDataReader ("FoodGHG.csv").getData();
		for(String key: readData.keySet()) {
			String group0 = readData.get(key).getCategory();
			if(!foodgroup.contains(group0)) {
				foodgroup.add(group0);
			}	
		}
		return foodgroup;	
	}
	
	/**
	 * 
	 * @param name
	 * @return dish category
	 */
	public String getDishCategory(String name) {
		HashMap<String,Dish> readDish = new DishDataReader("Dishes.csv").getData();
		String category = readDish.get(name).getGroup();
		return category;
	}
	
	/**
	 * 
	 * @param foodgroup
	 * @return food under each group(e.g. milk, chocolate...under dairy.)
	 */
	public ArrayList<String> foodGroupSelect(String foodgroup){
		ArrayList<String> foodlist = new ArrayList<String>();
		HashMap<String,Food> readData = new FoodDataReader ("FoodGHG.csv").getData();
		for(String key: readData.keySet()) {
			if(readData.get(key).getCategory().equals(foodgroup)) {
				foodlist.add(key);		
			}	
		}
		return foodlist;
	}


	
	/**
	 * 
	 * @param the user input food name
	 * @return an Arraylist contains three other food in the same category, sorted by GHG. If database doesn't
	 * have enough food in same category with lower GHG, randomly select some food in same category and add them
	 * in the Arraylist
	 */
	public ArrayList<String> getSimilarFood(String name){
		ArrayList<String> similarfood = new ArrayList<String>();
		HashMap<String,Food> readData = new FoodDataReader ("FoodGHG.csv").getData();
		String selectedFoodCategory = readData.get(name).getCategory();
		HashMap<String, Double> sameCategory = new HashMap<String, Double>();
		for (String key: readData.keySet()) {
			if (readData.get(key).getCategory().equals(selectedFoodCategory)) {
				sameCategory.put(key, readData.get(key).getCarbonEmissions());
			}
		}
		ArrayList<Double> carbonEmissionList = new ArrayList<>();
		for (String key: sameCategory.keySet()) {
			carbonEmissionList.add(sameCategory.get(key));	
		}
		Collections.sort(carbonEmissionList);
		ArrayList<String> topThreeFood = new ArrayList<String>();
		for (int i = 0; i < 3; i++) {
			for (String key: sameCategory.keySet()) {
				if (sameCategory.get(key) == carbonEmissionList.get(i)) {
					topThreeFood.add(key);
				}
			}
		}
		for (int i = 0; i < topThreeFood.size();i++) {
			if (topThreeFood.get(i).equals(name)) {
				i = 4;
			}
		else {
				similarfood.add(topThreeFood.get(i));
			}
		}
		
		ArrayList<String> sameCategoryWithoutSelectedFood = new ArrayList<String>();
		for (String key: readData.keySet()) {
			if (readData.get(key).getCategory().equals(selectedFoodCategory)) {
				if (!key.equals(name)) {
					sameCategoryWithoutSelectedFood.add(key);
					
				}
			}
		}
		for (int i = 0; i<similarfood.size();i++) {
			if (sameCategoryWithoutSelectedFood.contains(similarfood.get(i))) {
				sameCategoryWithoutSelectedFood.remove(similarfood.get(i));
			}

		}
		Random rand = new Random();
		int randFoodWeNeed = 3 - similarfood.size();
		for (int i = 0; i < randFoodWeNeed;i++) {
		int randNumber = rand.nextInt(sameCategoryWithoutSelectedFood.size());
		similarfood.add(sameCategoryWithoutSelectedFood.get(randNumber));
		sameCategoryWithoutSelectedFood.remove(randNumber);
	}		

		return similarfood;

	}	
	
	
	
	
	/**
	 * 
	 * @param food name
     * @return an Arraylist contains most three random Dishes related to the food
	 */
	public ArrayList<String> getDishesContainFood(String food){
		ArrayList <String> relatedDishes = new ArrayList<String>();
 		HashMap<String,Dish> readData = new DishDataReader ("Dishes.csv").getData();
		for (String key:readData.keySet()) {
			if (food.equals(readData.get(key).getIngredents().get(0)) || 
					food.equals(readData.get(key).getIngredents().get(1)) || 
					food.equals(readData.get(key).getIngredents().get(2))) {
				relatedDishes.add(key);
				}
		}
		int moreDishes = 0;
		if (relatedDishes.size()>3)	{
			moreDishes = relatedDishes.size()-3;
		}
		for (int i = 0; i<moreDishes;i++) {
			relatedDishes.remove(relatedDishes.size()-1);
			
		}
		
		
		
		return relatedDishes;
		}

	
	
	
	/**
	 * 
	 * @param Dish name
	 * @return GHG Emission of selected Dish
	 */
	public double getDishGHSEmission(String name){
		HashMap<String,Dish> readData = new DishDataReader ("Dishes.csv").getData();
		double totalEmission = readData.get(name).getCarbonEmissions();
		return totalEmission;
	}
	
	
	/**
	 * 
	 * @param Dish name
	 * @return picture path of selected Dish
	 */
	public String getDishPicPath(String name){
		HashMap<String,Dish> readData = new DishDataReader ("Dishes.csv").getData();
		String picPath = readData.get(name).getImagepath();
		return picPath;
	}
	

}
