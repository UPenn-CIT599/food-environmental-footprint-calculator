
public class Dish {
	/**
	 * Create a dish classes which contains different dishes
	 */
	private String group;   // Dish Group
	private double carbonEmissions;  // Greenhouse gas emissions per dish per kilogram (kgCO2-eq/kg)
	private String ingredents;
	
	//getters and setters
	
	public String getGroup() {
		return group;
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
	public String getIngredents() {
		return ingredents;
	}
	public void setIngredents(String ingredents) {
		this.ingredents = ingredents;
	}

}
