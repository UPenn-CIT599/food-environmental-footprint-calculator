import java.util.*;
import java.io.*;

/*
 * Below code is to read data from file and save it to Hashmap
 * Hashmap: key is food name, value is Food class
 */



public class FoodDataReader {
	
	private HashMap<String, Food> foodData = new HashMap<String, Food>();
	
	public FoodDataReader(String filename) {
		File file = new File(filename);
		try {
			Scanner scanner = new Scanner(file);
			scanner.nextLine();
			while(scanner.hasNextLine()) {
				String row = scanner.nextLine();
				String[] columnData = row.split(",");
				String name = columnData[0].toLowerCase();
				String category = columnData[1].toLowerCase();
				double carbonEmissions = Double.parseDouble(columnData[2]);
				int calories = Integer.parseInt(columnData[3]);

				
				Food data = new Food(category,carbonEmissions,calories);
				foodData.put(name, data);

			}
			
			
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
			e.printStackTrace();
		}
		
		
		
		
	}
	
	public HashMap<String, Food> getData() {
		return foodData;
		
	}
	
	
	
	
	

}
