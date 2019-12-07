import java.util.ArrayList;

public class Dish {
	/**
	 * Create a dish classes which contains different dishes
	 */
	private String group;   // Dish Group
	private double carbonEmissions;  // Greenhouse gas emissions per dish per kilogram (kgCO2-eq/kg)
	private ArrayList<String> ingredients = new ArrayList<String>();//save all 3 main ingredients to this arraylist
	private String imagepath;//save the image path of the dish
	//write the constructor
	public Dish(String g, double e, ArrayList<String> a, String p) {
		this.group = g;
		this.carbonEmissions = e;
		this.ingredients = a;
		this.imagepath = p;
	}
	//getters and setters
	public String getGroup() {
		return group;
	}
	public String getImagepath() {
		return imagepath;
	}
	public void setImagepath(String imagepath) {
		this.imagepath = imagepath;
	}
	public void setGroup(String group) {
		this.group = group;
	}
	public double getCarbonEmissions() {
		return carbonEmissions;
	}
	public void setCarbonEmissions(double carbonEmissions) {
		this.carbonEmissions = carbonEmissions;
	}
	public ArrayList<String> getIngredents() {
		return ingredients;
	}
	public void setIngredents(String ingredents) {
		this.ingredients = ingredients;
	}

}
