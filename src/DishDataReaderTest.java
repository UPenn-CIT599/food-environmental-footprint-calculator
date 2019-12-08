import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;

import org.junit.jupiter.api.Test;

class DishDataReaderTest {

	@Test
	void testGetData() {
		DishDataReader testReader = new DishDataReader("Dishes.csv");
		HashMap<String,Dish> testMap = testReader.getData();
		assertEquals(testMap.size(),74);
	}
}
