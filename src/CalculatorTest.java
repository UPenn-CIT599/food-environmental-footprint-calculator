import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CalculatorTest {

	@Test
	void testGetFoodGHGEmission() {
		Calculator c = new Calculator();
		String name = "apples";
		assertEquals(c.getFoodGHGEmission(name), 0.29);
	}

	@Test
	void testGetFoodCalories() {
		Calculator c = new Calculator();
		String name = "beef";
		assertEquals(c.getFoodCalories(name), 1220);
		
	}

	@Test
	void testGetAnnualGHG() {
		Calculator c = new Calculator();
		assertEquals(c.getAnnualGHG(10), 3650);
	}

	@Test
	void testGetCarKMEquivalent() {
		Calculator c = new Calculator();
		assertEquals(c.getCarKMEquivalent(10), 40);
	}

	@Test
	void testGetEquivalentBulbLightDays() {
		Calculator c = new Calculator();
		assertEquals(c.getEquivalentBulbLightDays(93), 1000);

	}

	@Test
	void testGetEquivalentFlightKM() {
		Calculator c = new Calculator();
		assertEquals(c.getEquivalentFlightKM(186), 1000);

	}

	@Test
	void testGeneralGroupSelect() {
		Calculator c = new Calculator();
		FoodDataReader dataReader = new FoodDataReader("FoodGHG.csv");
		assertEquals(c.generalGroupSelect(dataReader).size(), 7);
		

	}

	@Test
	void testFoodGroupSelect() {
		Calculator c = new Calculator();
		assertEquals(c.foodGroupSelect("meat").size(), 6);
	}

	@Test
	void testGetSimilarFoodTestOne() {
		Calculator c = new Calculator();
		assertEquals(c.getSimilarFood("eggs").size(), 3);
	}
	
	
	@Test
	void testGetSimilarFoodTestTwo() {
		Calculator c = new Calculator();
		assertEquals(c.getSimilarFood("beef").get(0), "eggs");
	}


	@Test
	void testGetDishesContainFoodTestOne() {
		Calculator c = new Calculator();
		assertEquals(c.getDishesContainFood("eggs").size(),3);
	}

	
	@Test
	void testGetDishesContainFoodTestTwo() {
		Calculator c = new Calculator();
		assertEquals(c.getDishesContainFood("lamb").size(),1);
	}
	
	
	@Test
	void testGetDishGHSEmission() {
		Calculator c = new Calculator();
		assertEquals(c.getDishGHSEmission("fried egg"),336);

	}

	@Test
	void testGetDishPicPath() {
		Calculator c = new Calculator();
		assertEquals(c.getDishPicPath("caesar salad"), "\"images/23.jpg\"");		

	}

}
