import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;

import org.junit.jupiter.api.Test;

class FoodDataReaderTest {

	@Test
	void testGetData() {

			FoodDataReader testReader = new FoodDataReader("FoodGHG.csv");
			HashMap<String,Food> testMap = testReader.getData();
			assertEquals(testMap.size(),90);
		}

	}

