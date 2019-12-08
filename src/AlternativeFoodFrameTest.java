import static org.junit.jupiter.api.Assertions.*;

import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class AlternativeFoodFrameTest {



	@Test
	void testGetBoundsArrayListOfStringTestOne() {
		Calculator c = new Calculator();
		User testUser = new User("123","123");
		testUser.setFoodName("eggs");
		testUser.setFoodWeight(1.0);
		ArrayList<String> dishList = c.getDishesContainFood("eggs");
		AlternativeFoodFrame testFrame = new AlternativeFoodFrame(new Point(100, 100), testUser);
		assertEquals(testFrame.getBounds(dishList).size(),6);
		

	}
	
	@Test
	void testGetBoundsArrayListOfStringTestTwo() {
		Calculator c = new Calculator();
		User testUser = new User("123","123");
		testUser.setFoodName("eggs");
		testUser.setFoodWeight(1.0);
		ArrayList<String> dishList = c.getDishesContainFood("eggs");
		AlternativeFoodFrame testFrame = new AlternativeFoodFrame(new Point(100, 100), testUser);
		Rectangle testRectangle = new Rectangle(350,150,100,100);
		assertEquals(testFrame.getBounds(dishList).get("imgDishOne"),testRectangle);
		

	}

	@Test
	void testGetDishImage() {
		Calculator c = new Calculator();
		User testUser = new User("123","123");
		testUser.setFoodName("eggs");
		testUser.setFoodWeight(1.0);
		ArrayList<String> dishList = c.getDishesContainFood("eggs");
		AlternativeFoodFrame testFrame = new AlternativeFoodFrame(new Point(100, 100), testUser);
		Image testImage = testFrame.getDishImage(c.getDishPicPath("fried egg"));
		assertEquals(testImage.getHeight(null),100);
	}

	@Test
	void testCapitalise() {
		Calculator c = new Calculator();
		User testUser = new User("123","123");
		testUser.setFoodName("eggs");
		testUser.setFoodWeight(1.0);
		ArrayList<String> dishList = c.getDishesContainFood("eggs");
		AlternativeFoodFrame testFrame = new AlternativeFoodFrame(new Point(100, 100), testUser);
		assertEquals(testFrame.capitalise("egg"),"Egg");
	}

}
