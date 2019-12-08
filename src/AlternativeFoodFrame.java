import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.Position;

import com.itextpdf.io.IOException;

import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JComboBox;
import java.awt.event.ItemListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import javax.swing.JButton;

public class AlternativeFoodFrame extends JFrame {

	private JPanel contentPane;
	private User user;

	/**
	 * Get the user class from FoodSelectionFrame class
	 * 
	 * @return the user class
	 */
	public User getUser() {
		return this.user;
	}

	/**
	 * Set the user class in the FoodSelectionFrame class
	 * 
	 * @param user class to be set
	 */
	public void setUser(User user) {
		this.user = user;
	}

	public HashMap<String, Rectangle> getBounds(ArrayList<String> dishes) {
		HashMap<String, Rectangle> bounds = new HashMap<>();

		if (dishes.size() == 1) {
			bounds.put("imgDishOne", new Rectangle(490, 150, 100, 100));
			bounds.put("lblDishOne", new Rectangle(490, 260, 100, 50));
			bounds.put("imgDishTwo", new Rectangle(490, 150, 100, 100));
			bounds.put("lblDishTwo", new Rectangle(490, 260, 100, 50));
			bounds.put("imgDishThree", new Rectangle(630, 150, 100, 100));
			bounds.put("lblDishThree", new Rectangle(630, 260, 100, 50));
		} else if (dishes.size() == 2) {
			bounds.put("imgDishOne", new Rectangle(395, 150, 100, 100));
			bounds.put("lblDishOne", new Rectangle(395, 260, 100, 50));
			bounds.put("imgDishTwo", new Rectangle(585, 150, 100, 100));
			bounds.put("lblDishTwo", new Rectangle(585, 260, 100, 50));
			bounds.put("imgDishThree", new Rectangle(630, 150, 100, 100));
			bounds.put("lblDishThree", new Rectangle(630, 260, 100, 50));
		} else if (dishes.size() == 3) {
			bounds.put("imgDishOne", new Rectangle(350, 150, 100, 100));
			bounds.put("lblDishOne", new Rectangle(350, 260, 100, 50));
			bounds.put("imgDishTwo", new Rectangle(490, 150, 100, 100));
			bounds.put("lblDishTwo", new Rectangle(490, 260, 100, 50));
			bounds.put("imgDishThree", new Rectangle(630, 150, 100, 100));
			bounds.put("lblDishThree", new Rectangle(630, 260, 100, 50));
		}
		return bounds;
	}

	/**
	 * Generates a resized image given the dish image paths
	 * 
	 * @param dishImagePath is the image path of the selected dish
	 * @return an Image class of the dish image of dimension 100 by 100
	 */
	public Image getDishImage(String dishImagePath) {
		BufferedImage dishImage = null;
		try {
			URL url = getClass().getResource(dishImagePath);
			dishImage = ImageIO.read(url);
		} catch (Exception e) {
			// null
		}
		ImageIcon dishIcon = new ImageIcon(dishImage);
		Image originalDishImage = dishIcon.getImage();
		Image resizedDishImage = originalDishImage.getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH);
		return resizedDishImage;

	}

	/**
	 * Capitalises the first letter of the string
	 * 
	 * @param str is the input string to be capitalised
	 * @return A string with the first letter capitalised
	 */
	public static String capitalise(String str) {
		if (str == null || str.isEmpty()) {
			return str;
		}

		return str.substring(0, 1).toUpperCase() + str.substring(1);
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AlternativeFoodFrame frame = new AlternativeFoodFrame(new Point(100, 100), new User("", ""));
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AlternativeFoodFrame(Point position, User user) {
		
		// Create fonts
		
		Font appleLG25 = new ImportFont().createFont("fonts/appleLG.ttf", 25);
		Font appleLG35 = new ImportFont().createFont("fonts/appleLG.ttf", 35);
		
		Font hiragino13 = new ImportFont().createFont("fonts/Hiragino Sans GB W3.ttf", 13);
		
		Font chalkduster12 = new ImportFont().createFont("fonts/Chalkduster.ttf", 12);
		
		Font chalkboard13 = new ImportFont().createFont("fonts/chalkboard.ttf", 13);
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setUser(user);
		setSize(800, 600);
		setLocation(position);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// Generate 3 recommended food items based on user's input
		Calculator c = new Calculator();
		ArrayList<String> similarFoods = c.getSimilarFood(user.getFoodName().toLowerCase());
		Collections.sort(similarFoods);

		String recommendedFoodOne = similarFoods.get(0);
		double recommendedFoodOneGHGPerKG = c.getFoodGHGEmission(recommendedFoodOne, 1.0);
		double recommendedFoodOneCalPerKG = c.getFoodCalories(recommendedFoodOne);
		String recommendedFoodTwo = similarFoods.get(1);
		double recommendedFoodTwoGHGPerKG = c.getFoodGHGEmission(recommendedFoodTwo, 1.0);
		double recommendedFoodTwoCalPerKG = c.getFoodCalories(recommendedFoodTwo);
		String recommendedFoodThree = similarFoods.get(2);
		double recommendedFoodThreeGHGPerKG = c.getFoodGHGEmission(recommendedFoodThree, 1.0);
		double recommendedFoodThreeCalPerKG = c.getFoodCalories(recommendedFoodThree);

		// Display the first recommended dish
		JLabel imgDishOne = new JLabel("");
		imgDishOne.setIcon(new ImageIcon(
				"AlternativeFoodFrame.class.getResource(\"/javax/swing/plaf/basic/icons/image-delayed.png\")"));
		imgDishOne.setHorizontalAlignment(SwingConstants.CENTER);
		imgDishOne.setBounds(350, 155, 100, 100);
		contentPane.add(imgDishOne);
		imgDishOne.setVisible(false);

		JLabel lblDishOne = new JLabel("<html>dish1</html>");
		lblDishOne.setFont(chalkduster12);
		lblDishOne.setForeground(Color.WHITE);
		lblDishOne.setHorizontalAlignment(SwingConstants.CENTER);
		lblDishOne.setBounds(350, 260, 100, 50);
		contentPane.add(lblDishOne);
		lblDishOne.setVisible(false);

		// // Display the second recommended dish
		JLabel imgDishTwo = new JLabel("");
		imgDishTwo.setHorizontalAlignment(SwingConstants.CENTER);
		imgDishTwo.setIcon(new ImageIcon(
				"AlternativeFoodFrame.class.getResource(\"/javax/swing/plaf/basic/icons/image-delayed.png\")"));
		imgDishTwo.setBounds(490, 155, 100, 100);
		contentPane.add(imgDishTwo);
		imgDishTwo.setVisible(false);

		JLabel lblDishTwo = new JLabel("<html>dish2</html>");
		lblDishTwo.setFont(chalkduster12);
		lblDishTwo.setForeground(Color.WHITE);
		lblDishTwo.setHorizontalAlignment(SwingConstants.CENTER);
		lblDishTwo.setBounds(490, 260, 100, 50);
		contentPane.add(lblDishTwo);
		lblDishTwo.setVisible(false);

		// Display the third recommended dish
		JLabel imgDishThree = new JLabel("");
		imgDishThree.setHorizontalAlignment(SwingConstants.CENTER);
		imgDishThree.setIcon(new ImageIcon(
				"AlternativeFoodFrame.class.getResource(\"/javax/swing/plaf/basic/icons/image-delayed.png\")"));
		imgDishThree.setBounds(630, 155, 100, 100);
		contentPane.add(imgDishThree);
		imgDishThree.setVisible(false);

		JLabel lblDishThree = new JLabel("<html>dish3</html>");
		lblDishThree.setFont(chalkduster12);
		lblDishThree.setForeground(Color.WHITE);
		lblDishThree.setHorizontalAlignment(SwingConstants.CENTER);
		lblDishThree.setBounds(630, 260, 100, 50);
		contentPane.add(lblDishThree);
		lblDishThree.setVisible(false);

		// Combobox to select recommended food, in order to display dishes for the food
		JComboBox cbRecipes = new JComboBox();
		cbRecipes.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (!cbRecipes.getSelectedItem().toString().equals("Choose one...")) {
					// Generate list of dishes that can be made with the recommended food item
					ArrayList<String> foodDishes = new ArrayList<>();
					foodDishes = c.getDishesContainFood(cbRecipes.getSelectedItem().toString().toLowerCase());

					// Set bounds for the dish placeholders
					HashMap<String, Rectangle> boundsHM = getBounds(foodDishes);
					imgDishOne.setBounds(boundsHM.get("imgDishOne"));
					lblDishOne.setBounds(boundsHM.get("lblDishOne"));
					imgDishTwo.setBounds(boundsHM.get("imgDishTwo"));
					lblDishTwo.setBounds(boundsHM.get("lblDishTwo"));
					imgDishThree.setBounds(boundsHM.get("imgDishThree"));
					lblDishThree.setBounds(boundsHM.get("lblDishThree"));

					// Set visibility of placeholders based on number of dishes recommended

					// One dish is recommended
					if (foodDishes.size() == 1) {
						imgDishOne.setVisible(true);
						lblDishOne.setVisible(true);
						imgDishTwo.setVisible(false);
						lblDishTwo.setVisible(false);
						imgDishThree.setVisible(false);
						lblDishThree.setVisible(false);

						// Set image icons of placeholders

						// Image 1
						Image imageOne = getDishImage(c.getDishPicPath(foodDishes.get(0)));
						imgDishOne.setIcon(new ImageIcon(imageOne));
						lblDishOne.setText("<html>" + capitalise(foodDishes.get(0)) + "</html>");
					}

					// Two dishes are recommended
					else if (foodDishes.size() == 2) {
						imgDishOne.setVisible(true);
						lblDishOne.setVisible(true);
						imgDishTwo.setVisible(true);
						lblDishTwo.setVisible(true);
						imgDishThree.setVisible(false);
						lblDishThree.setVisible(false);

						// Set image icons of placeholders

						// Image 1
						Image imageOne = getDishImage(c.getDishPicPath(foodDishes.get(0)));
						imgDishOne.setIcon(new ImageIcon(imageOne));
						lblDishOne.setText("<html>" + capitalise(foodDishes.get(0)) + "</html>");

						// Image 2
						Image imageTwo = getDishImage(c.getDishPicPath(foodDishes.get(1)));
						imgDishTwo.setIcon(new ImageIcon(imageTwo));
						lblDishTwo.setText("<html>" + capitalise(foodDishes.get(1)) + "</html>");

					}
					// Three dishes are recommended
					else if (foodDishes.size() == 3) {
						imgDishOne.setVisible(true);
						lblDishOne.setVisible(true);
						imgDishTwo.setVisible(true);
						lblDishTwo.setVisible(true);
						imgDishThree.setVisible(true);
						lblDishThree.setVisible(true);

						// Set image icons of placeholders

						// Image 1
						Image imageOne = getDishImage(c.getDishPicPath(foodDishes.get(0)));
						imgDishOne.setIcon(new ImageIcon(imageOne));
						lblDishOne.setText("<html>" + capitalise(foodDishes.get(0)) + "</html>");

						// Image 2
						Image imageTwo = getDishImage(c.getDishPicPath(foodDishes.get(1)));
						imgDishTwo.setIcon(new ImageIcon(imageTwo));
						lblDishTwo.setText("<html>" + capitalise(foodDishes.get(1)) + "</html>");

						// Image 3
						Image imageThree = getDishImage(c.getDishPicPath(foodDishes.get(2)));
						imgDishThree.setIcon(new ImageIcon(imageThree));
						lblDishThree.setText("<html>" + capitalise(foodDishes.get(2)) + "</html>");

					}
				}
			}
		});
		cbRecipes.setFont(hiragino13);
		cbRecipes.setBounds(463, 111, 156, 27);
		cbRecipes.addItem("Choose one...");
		cbRecipes.addItem(capitalise(recommendedFoodOne));
		cbRecipes.addItem(capitalise(recommendedFoodTwo));
		cbRecipes.addItem(capitalise(recommendedFoodThree));

		contentPane.add(cbRecipes);

		JLabel lblAltFood = new JLabel(
				"<html>Instead of " + user.getFoodName().toLowerCase() + ",<br>you can try:</html>");
		lblAltFood.setVerticalAlignment(SwingConstants.TOP);
		lblAltFood.setForeground(new Color(102, 205, 170));
		lblAltFood.setFont(appleLG35);
		lblAltFood.setBounds(42, 35, 248, 124);
		contentPane.add(lblAltFood);

		// Recommended food #1
		String recommendationOneName = "<html>#1 " + recommendedFoodOne.toUpperCase() + "</html>";
		JLabel lblFoodOneName = new JLabel(recommendationOneName);
		lblFoodOneName.setFont(appleLG25);
		lblFoodOneName.setForeground(Color.WHITE);
		lblFoodOneName.setHorizontalAlignment(SwingConstants.LEFT);
		lblFoodOneName.setBounds(42, 150, 237, 44);
		contentPane.add(lblFoodOneName);

		String recommendationOneMetrices = "<html>Each kg of " + recommendedFoodOne.toLowerCase() + " produces <b>"
				+ recommendedFoodOneGHGPerKG + " kgCO2(eq)</b> and provides <b>" + recommendedFoodOneCalPerKG
				+ " kcal</b>.</html>";
		JLabel lblFoodOneMetrices = new JLabel(recommendationOneMetrices);
		lblFoodOneMetrices.setVerticalAlignment(SwingConstants.TOP);
		lblFoodOneMetrices.setFont(chalkboard13);
		lblFoodOneMetrices.setForeground(new Color(245, 245, 245));
		lblFoodOneMetrices.setHorizontalAlignment(SwingConstants.LEFT);
		lblFoodOneMetrices.setBounds(42, 185, 248, 51);
		contentPane.add(lblFoodOneMetrices);

		// Recommended food #2
		String recommendationTwoName = "<html>#2 " + recommendedFoodTwo.toUpperCase() + "</html>";
		JLabel lblFoodTwoName = new JLabel(recommendationTwoName);
		lblFoodTwoName.setFont(appleLG25);
		lblFoodTwoName.setForeground(Color.WHITE);
		lblFoodTwoName.setHorizontalAlignment(SwingConstants.LEFT);
		lblFoodTwoName.setBounds(42, 240, 237, 44);
		contentPane.add(lblFoodTwoName);

		String recommendationTwoMetrices = "<html>Each kg of " + recommendedFoodTwo.toLowerCase() + " produces <b>"
				+ recommendedFoodTwoGHGPerKG + " kgCO2(eq)</b> and provides <b>" + recommendedFoodTwoCalPerKG
				+ " kcal</b>.</html>";
		JLabel lblFoodTwoMetrices = new JLabel(recommendationTwoMetrices);
		lblFoodTwoMetrices.setForeground(new Color(245, 245, 245));
		lblFoodTwoMetrices.setFont(chalkboard13);
		lblFoodTwoMetrices.setVerticalAlignment(SwingConstants.TOP);
		lblFoodTwoMetrices.setHorizontalAlignment(SwingConstants.LEFT);
		lblFoodTwoMetrices.setBounds(42, 275, 248, 51);
		contentPane.add(lblFoodTwoMetrices);

		// Recommended food #3
		String recommendationThreeName = "<html>#3 " + recommendedFoodThree.toUpperCase() + "</html>";
		JLabel lblFoodThreeName = new JLabel(recommendationThreeName);
		lblFoodThreeName.setFont(appleLG25);
		lblFoodThreeName.setForeground(Color.WHITE);
		lblFoodThreeName.setHorizontalAlignment(SwingConstants.LEFT);
		lblFoodThreeName.setBounds(42, 330, 237, 44);
		contentPane.add(lblFoodThreeName);

		String recommendationThreeMetrices = "<html>Each kg of " + recommendedFoodThree.toLowerCase() + " produces <b>"
				+ recommendedFoodThreeGHGPerKG + " kgCO2(eq)</b> and provides <b>" + recommendedFoodThreeCalPerKG
				+ " kcal</b>.</html>";
		JLabel lblFoodThreeMetrices = new JLabel(recommendationThreeMetrices);
		lblFoodThreeMetrices.setVerticalAlignment(SwingConstants.TOP);
		lblFoodThreeMetrices.setFont(chalkboard13);
		lblFoodThreeMetrices.setForeground(new Color(245, 245, 245));
		lblFoodOneMetrices.setForeground(new Color(245, 245, 245));
		lblFoodThreeMetrices.setHorizontalAlignment(SwingConstants.LEFT);
		lblFoodThreeMetrices.setBounds(42, 365, 248, 51);
		contentPane.add(lblFoodThreeMetrices);

		JButton btnEmailResults = new JButton("Email Results");
		btnEmailResults.setBackground(new Color(72, 209, 204));
		btnEmailResults.setOpaque(true);
		btnEmailResults.setBounds(90, 450, 120, 30);
		contentPane.add(btnEmailResults);

		btnEmailResults.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					new PDFGenerator().createPdf("results.pdf", user);
				} catch (java.io.IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				try {
					SendEmail mail = new SendEmail(user.getEmail());
					// Send the user a notice of email
					JOptionPane.showMessageDialog(null, "The email has been sent to " + user.getEmail() + ".");

				} catch (GeneralSecurityException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});

		JButton btnTryAgain = new JButton("Try Again");
		btnTryAgain.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				Point position = getLocation();
				FoodSelectionFrame fsf = new FoodSelectionFrame(position, user);
				fsf.setVisible(true);
				setVisible(false);
				dispose();
			}
		});

		btnTryAgain.setBackground(new Color(102, 205, 170));
		btnTryAgain.setOpaque(true);
		btnTryAgain.setBounds(90, 488, 120, 30);
		contentPane.add(btnTryAgain);

		// Import and resize background image
		BufferedImage backgroundImage = null;
		try {
			URL url = getClass().getResource("images/chewpaca4.jpg");
			backgroundImage = ImageIO.read(url);
		} catch (Exception e) {
			// null
		}
		ImageIcon backgroundIcon = new ImageIcon(backgroundImage);
		Image originalImage = backgroundIcon.getImage();
		Image resizedImage = originalImage.getScaledInstance(800, 600, java.awt.Image.SCALE_SMOOTH);

		JLabel background = new JLabel("");
		background.setBackground(Color.WHITE);
		background.setForeground(Color.DARK_GRAY);
		background.setIcon(new ImageIcon(resizedImage));
		background.setBounds(0, 0, 800, 600);
		this.getContentPane().add(background);

	}
}
