import java.util.*;
import java.io.*;

public class Runner {
	
	public static void main(String[] args) {
		
		//HashMap<String,Food> readData = new FoodDataReader ("FoodGHS.csv").getData();
		//System.out.println("Which food you want to check?");
		//Scanner in = new Scanner(System.in);
		//String input = in.nextLine();
		//String inputToLowerCase = input.toLowerCase();
		//System.out.println("The food " + input +" GHS gas emissions is "+ readData.get(inputToLowerCase).getCarbonEmissions()
		//	+" Kg per Kg.");
		
		
		Calculator test = new Calculator();
		ArrayList<String> testList = test.getSimilarFood("eggs");
		//HashMap<String,Double> testMap = test.getDishesContainFood(testList);
		//Set<String> testSet = testMap.keySet();
		System.out.println(testList);
		System.out.println(test.getDishesContainFood(testList.get(2)));
	
		//System.out.println(testList);
		//ArrayList<String> testList2 = test.getLowCarbonDishes(testMap);
		//for (int i = 0; i < testList2.size();i++) {
			//System.out.println(test.getDishPicPath(testList2.get(i)));
		//}
	}

}
