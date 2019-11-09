import java.util.*;
import java.io.*;

public class FoodDataReader {
	
	private HashMap<String, FoodDataStructure> foodData = new HashMap<String, FoodDataStructure>();
	
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

				
				FoodDataStructure data = new FoodDataStructure(category,carbonEmissions,calories);
				foodData.put(name, data);

			}
			
			
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
			e.printStackTrace();
		}
		
		
		
		
	}
	
	public HashMap<String, FoodDataStructure> getData() {
		return foodData;
		
	}
	
	
	
	
	

}
