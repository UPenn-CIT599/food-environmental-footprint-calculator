import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
/**
 * 
 * @author Xiaolu
 * Create a HushMap to save all the information of the Dishes from dish.csv.
 */
public class DishDataReader {
	
private HashMap<String, Dish> dishData = new HashMap<String, Dish>();
	
	public DishDataReader(String filename) {
		File file = new File(filename);
		try {
			Scanner scanner = new Scanner(file);
			scanner.nextLine();
			while(scanner.hasNextLine()) {
				String row = scanner.nextLine();
				String[] columnData = row.split(",");
				//save key
				String name = columnData[0].toLowerCase();
				//save contents
				String group = columnData[1].toLowerCase();
				double carbonEmissions = Double.parseDouble(columnData[2]);
				ArrayList<String> ingredients = new ArrayList<String>();
				ingredients.add(columnData[3].toLowerCase());
				ingredients.add(columnData[4].toLowerCase());
				ingredients.add(columnData[5].toLowerCase());
				String imagepath = columnData[6].toLowerCase();
				
				Dish data = new Dish(group,carbonEmissions,ingredients,imagepath);
				dishData.put(name, data);
			}		
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
			e.printStackTrace();
		}			
	}
	
	public HashMap<String, Dish> getData() {
		return dishData;
		
	}
}
