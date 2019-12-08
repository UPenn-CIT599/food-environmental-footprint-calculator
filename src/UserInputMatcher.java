
import java.util.ArrayList;

public class UserInputMatcher {
/**
 * This class provides an automatic match if the user misspells food name or mistake the single and plural
 */
	
	/**
	 * 
	 * @param word1
	 * @param word2 
	 * @return the similarity score of the two words
	 */
	 public double getSimilarityMetric(String word1, String word2){
	        int wordLength1 = word1.length();
	        int wordLength2 = word2.length();
	        int minLength = wordLength1;
	        if ( wordLength2 < minLength ){
	            minLength = wordLength2;
	        }
	        int leftSimilarity = 0;
	        int rightSimilarity = 0;
	        for(int i = 0; i < minLength; i++){
	            char leftCh1 = word1.charAt(i);
	            char leftCh2 = word2.charAt(i);
	            char rightCh1 = word1.charAt(word1.length() - 1 - i);
	            char rightCh2 = word2.charAt(word2.length() - 1 - i);
	            if ( leftCh1 == leftCh2 ){
	                leftSimilarity ++;
	            }
	            if ( rightCh1 == rightCh2 ){
	                rightSimilarity ++;
	            }
	        }
	        return (leftSimilarity + rightSimilarity) / 2.0f;
	    }
	 /**
	  * 
	  * @param the user input word
	  * @param a list with all food names
	  * @return the most similar food name to the user input word
	  */
	 public String getMatchedWord(String word1, ArrayList<String> foodlist) {
		 double max = 0;
		 String mostSimilarName = "";
		 for(String foodname: foodlist) {
			if(getSimilarityMetric(word1, foodname) > max) {
				max = getSimilarityMetric(word1, foodname);
				mostSimilarName = foodname;
			}
		}
		return mostSimilarName;
		 
	 }
}

