
public class Food {
	
	private String category;   // Food Category
	private double carbonEmissions;  // Greenhouse gas emissions per kilogram (kgCO2-eq/kg)
	private int calories; // Calories per kilogram or 1000ml
	
	public Food(String foodCategory, double foodCarbonEmissions, int foodCalories) {
		category = foodCategory;
		carbonEmissions = foodCarbonEmissions;
		calories = foodCalories;

	}
	
	
	/*
	 * Below is 'getters' and 'setters' for the three variables: category, carbonEmissions, calories
	 */


	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public double getCarbonEmissions() {
		return carbonEmissions;
	}

	public void setCarbonEmissions(double carbonEmissions) {
		this.carbonEmissions = carbonEmissions;
	}

	public int getCalories() {
		return calories;
	}

	public void setCalories(int calories) {
		this.calories = calories;
	}

	
	
	
}



