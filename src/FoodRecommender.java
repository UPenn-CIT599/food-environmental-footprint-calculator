import java.util.*;
import java.io.*;

public class FoodRecommender {
	
	public static void main(String[] args) {
		
		HashMap<String,Food> readData = new FoodDataReader("FoodGHS.csv").getData();
		System.out.println("Which food you want to check?");
		Scanner in = new Scanner(System.in);
		String input = in.nextLine();
		String inputToLowerCase = input.toLowerCase();
		System.out.println("The food " + input +" GHS gas emissions is "+ readData.get(inputToLowerCase).getCarbonEmissions()
			+" Kg per Kg. And its calories is " + readData.get(inputToLowerCase).getCalories()+ " Cal per Kg");

	}

}
