
public class User {

	private String name;
	private String email;
	private String foodName;
	private double foodWeight;

	public User(String name, String email) {
		this.name = name;
		this.email = email;
		this.foodName = "";
		this.foodWeight = 0;
	}
	public String getFoodName() {
		return foodName;
	}

	public void setFoodName(String food) {
		this.foodName = food;
	}

	public double getFoodWeight() {
		return foodWeight;
	}

	public void setFoodWeight(double foodWeight) {
		this.foodWeight = foodWeight;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
