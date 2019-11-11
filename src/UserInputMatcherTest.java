import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class UserInputMatcherTest {

	@Test
	void testGetSimilarityMetric() {
		UserInputMatcher u = new UserInputMatcher();
		String word1 = "aple";
		String word2 = "apple";
		assertEquals(u.getSimilarityMetric(word1, word2), 2.5);
	}

	@Test
	void testGetMatchedWord() {
		UserInputMatcher u = new UserInputMatcher();
		ArrayList<String> foodlist = new ArrayList<String>();
		foodlist.add("apple");
		foodlist.add("orange");
		foodlist.add("banana");
		assertEquals(u.getMatchedWord("aple", foodlist), "apple");
	}

}
