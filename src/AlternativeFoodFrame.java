import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Point;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JComboBox;
import java.awt.event.ItemListener;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.HashMap;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import javax.swing.JButton;

public class AlternativeFoodFrame extends JFrame {

	private JPanel contentPane;
	private User user;

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setUser(user);
		setSize(800, 600);
		setLocation(position);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		Calculator c = new Calculator();

		String recommendedFoodOne = c.getSimilarFood(user.getFoodName()).get(0);
		double recommendedFoodOneGHGPerKG = c.getFoodGHGEmission(recommendedFoodOne, 1.0);
		double recommendedFoodOneCalPerKG = c.getFoodCalories(recommendedFoodOne);
		String recommendedFoodTwo = c.getSimilarFood(user.getFoodName()).get(1);
		double recommendedFoodTwoGHGPerKG = c.getFoodGHGEmission(recommendedFoodTwo, 1.0);
		double recommendedFoodTwoCalPerKG = c.getFoodCalories(recommendedFoodTwo);
		String recommendedFoodThree = c.getSimilarFood(user.getFoodName()).get(2);
		double recommendedFoodThreeGHGPerKG = c.getFoodGHGEmission(recommendedFoodThree, 1.0);
		double recommendedFoodThreeCalPerKG = c.getFoodCalories(recommendedFoodThree);

		ArrayList<String> foodOneDishes = new ArrayList<>();
		ArrayList<String> r1 = c.getDishesContainFood(user.getFoodName());
		for (int i = 0; i < r1.size(); i++) {
			foodOneDishes.add(r1.get(i));
		}

		ArrayList<String> foodTwoDishes = new ArrayList<>();
		ArrayList<String> r2 = c.getDishesContainFood(user.getFoodName());
		for (int i = 0; i < r2.size(); i++) {
			foodOneDishes.add(r2.get(i));
		}

		ArrayList<String> foodThreeDishes = new ArrayList<>();
		ArrayList<String> r3 = c.getDishesContainFood(user.getFoodName());
		for (int i = 0; i < r3.size(); i++) {
			foodOneDishes.add(r3.get(i));
		}

		HashMap<String, ArrayList<String>> recommendedDishes = new HashMap<>();
		recommendedDishes.put(recommendedFoodOne, foodOneDishes);
		recommendedDishes.put(recommendedFoodTwo, foodTwoDishes);
		recommendedDishes.put(recommendedFoodThree, foodThreeDishes);

		// Recommended dish #1
		JLabel imgDishOne = new JLabel("");
		imgDishOne.setIcon(new ImageIcon(
				"AlternativeFoodFrame.class.getResource(\"/javax/swing/plaf/basic/icons/image-delayed.png\")"));
		imgDishOne.setHorizontalAlignment(SwingConstants.CENTER);
		imgDishOne.setBounds(350, 150, 100, 100);
		contentPane.add(imgDishOne);
		imgDishOne.setVisible(false);

		JLabel lblDishOne = new JLabel("<html>dish1</html>");
		lblDishOne.setFont(new Font("Chalkduster", Font.PLAIN, 10));
		lblDishOne.setForeground(Color.WHITE);
		lblDishOne.setHorizontalAlignment(SwingConstants.CENTER);
		lblDishOne.setBounds(350, 260, 100, 50);
		contentPane.add(lblDishOne);
		lblDishOne.setVisible(false);

		// Recommended dish #2
		JLabel imgDishTwo = new JLabel("");
		imgDishTwo.setHorizontalAlignment(SwingConstants.CENTER);
		imgDishTwo.setIcon(new ImageIcon(
				"AlternativeFoodFrame.class.getResource(\"/javax/swing/plaf/basic/icons/image-delayed.png\")"));
		imgDishTwo.setBounds(490, 150, 100, 100);
		contentPane.add(imgDishTwo);
		imgDishTwo.setVisible(false);

		JLabel lblDishTwo = new JLabel("<html>dish2</html>");
		;
		lblDishTwo.setFont(new Font("Chalkduster", Font.PLAIN, 10));
		lblDishTwo.setForeground(Color.WHITE);
		lblDishTwo.setHorizontalAlignment(SwingConstants.CENTER);
		lblDishTwo.setBounds(490, 260, 100, 50);
		contentPane.add(lblDishTwo);
		lblDishTwo.setVisible(false);

		// Recommended dish #3
		JLabel imgDishThree = new JLabel("");
		imgDishThree.setHorizontalAlignment(SwingConstants.CENTER);
		imgDishThree.setIcon(new ImageIcon(
				"AlternativeFoodFrame.class.getResource(\"/javax/swing/plaf/basic/icons/image-delayed.png\")"));
		imgDishThree.setBounds(630, 150, 100, 100);
		contentPane.add(imgDishThree);
		imgDishThree.setVisible(false);

		JLabel lblDishThree = new JLabel("<html>dish3</html>");
		;
		lblDishThree.setFont(new Font("Chalkduster", Font.PLAIN, 10));
		lblDishThree.setForeground(Color.WHITE);
		lblDishThree.setHorizontalAlignment(SwingConstants.CENTER);
		lblDishThree.setBounds(630, 260, 100, 50);
		contentPane.add(lblDishThree);
		lblDishThree.setVisible(false);

		// Combobox to select recommended food
		JComboBox cbRecipes = new JComboBox();
		cbRecipes.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (!cbRecipes.getSelectedItem().toString().equals("Choose one...")) {

					// If there is only one dish recommedation
					if (recommendedDishes.get(cbRecipes.getSelectedItem().toString()).size() == 1) {

						// Set positions of placeholders for one dish
						imgDishOne.setBounds(490, 150, 100, 100);
						lblDishOne.setBounds(490, 260, 100, 50);
						imgDishTwo.setBounds(490, 150, 100, 100);
						lblDishTwo.setBounds(490, 260, 100, 50);
						imgDishThree.setBounds(630, 150, 100, 100);
						lblDishThree.setBounds(630, 260, 100, 50);
						/**
						 * @author Xiaolu replaced hard codes with method below
						 * 
						 */
						if (cbRecipes.getSelectedItem().toString().equals(recommendedFoodOne)) {
							// Use method to select corresponding dish images
							String imagepath = c.getDishPicPath(foodOneDishes.get(0));
							imgDishOne.setIcon(new ImageIcon(imagepath));
							// Use method to select corresponding dish names
							lblDishOne.setText(foodOneDishes.get(0));

						} else if (cbRecipes.getSelectedItem().toString().equals(recommendedFoodTwo)) {
							// Use method to select corresponding dish images
							String imagepath = c.getDishPicPath(foodTwoDishes.get(0));
							imgDishOne.setIcon(new ImageIcon(imagepath));
							// Use method to select corresponding dish names
							lblDishOne.setText(foodTwoDishes.get(0));

						} else if (cbRecipes.getSelectedItem().toString().equals(recommendedFoodThree)) {
							// Use method to select corresponding dish images
							String imagepath = c.getDishPicPath(foodThreeDishes.get(0));
							imgDishOne.setIcon(new ImageIcon(imagepath));
							// Use method to select corresponding dish names
							lblDishOne.setText(foodThreeDishes.get(0));
						}

						imgDishOne.setVisible(true);
						lblDishOne.setVisible(true);
						imgDishTwo.setVisible(false);
						lblDishTwo.setVisible(false);
						imgDishThree.setVisible(false);
						lblDishThree.setVisible(false);

						// If there are two dish recommendations
					} else if (recommendedDishes.get(cbRecipes.getSelectedItem().toString()).size() == 2) {

						// Set positions of placeholders for two dishes
						imgDishOne.setBounds(395, 150, 100, 100);
						lblDishOne.setBounds(395, 260, 100, 50);
						imgDishTwo.setBounds(585, 150, 100, 100);
						lblDishTwo.setBounds(585, 260, 100, 50);
						imgDishThree.setBounds(630, 150, 100, 100);
						lblDishThree.setBounds(630, 260, 100, 50);

						if (cbRecipes.getSelectedItem().toString().equals(recommendedFoodOne)) {
							String imagepath1 = c.getDishPicPath(foodOneDishes.get(0));
							String imagepath2 = c.getDishPicPath(foodOneDishes.get(1));
							imgDishOne.setIcon(new ImageIcon(imagepath1));
							imgDishTwo.setIcon(new ImageIcon(imagepath2));
							lblDishOne.setText(foodOneDishes.get(0));
							lblDishTwo.setText(foodOneDishes.get(1));

						} else if (cbRecipes.getSelectedItem().toString().equals(recommendedFoodTwo)) {
							String imagepath1 = c.getDishPicPath(foodTwoDishes.get(0));
							String imagepath2 = c.getDishPicPath(foodTwoDishes.get(1));
							imgDishOne.setIcon(new ImageIcon(imagepath1));
							imgDishTwo.setIcon(new ImageIcon(imagepath2));
							lblDishOne.setText(foodTwoDishes.get(0));
							lblDishTwo.setText(foodTwoDishes.get(1));

						} else if (cbRecipes.getSelectedItem().toString().equals(recommendedFoodThree)) {
							String imagepath1 = c.getDishPicPath(foodThreeDishes.get(0));
							String imagepath2 = c.getDishPicPath(foodThreeDishes.get(1));
							imgDishOne.setIcon(new ImageIcon(imagepath1));
							imgDishTwo.setIcon(new ImageIcon(imagepath2));
							lblDishOne.setText(foodThreeDishes.get(0));
							lblDishTwo.setText(foodThreeDishes.get(1));
						}

						imgDishOne.setVisible(true);
						lblDishOne.setVisible(true);
						imgDishTwo.setVisible(true);
						lblDishTwo.setVisible(true);
						imgDishThree.setVisible(false);
						lblDishThree.setVisible(false);

						// If there are three dish recommendations
					} else if (recommendedDishes.get(cbRecipes.getSelectedItem().toString()).size() == 3) {

						// Set positions of placeholders for three dishes
						imgDishOne.setBounds(350, 150, 100, 100);
						lblDishOne.setBounds(350, 260, 100, 50);
						imgDishTwo.setBounds(490, 150, 100, 100);
						lblDishTwo.setBounds(490, 260, 100, 50);
						imgDishThree.setBounds(630, 150, 100, 100);
						lblDishThree.setBounds(630, 260, 100, 50);

						if (cbRecipes.getSelectedItem().toString().equals(recommendedFoodOne)) {
							String imagepath1 = c.getDishPicPath(foodOneDishes.get(0));
							String imagepath2 = c.getDishPicPath(foodOneDishes.get(1));
							String imagepath3 = c.getDishPicPath(foodOneDishes.get(2));
							imgDishOne.setIcon(new ImageIcon(imagepath1));
							imgDishTwo.setIcon(new ImageIcon(imagepath2));
							imgDishOne.setIcon(new ImageIcon(imagepath3));
							lblDishOne.setText(foodOneDishes.get(0));
							lblDishTwo.setText(foodOneDishes.get(1));
							lblDishTwo.setText(foodOneDishes.get(2));

						} else if (cbRecipes.getSelectedItem().toString().equals(recommendedFoodTwo)) {
							String imagepath1 = c.getDishPicPath(foodTwoDishes.get(0));
							String imagepath2 = c.getDishPicPath(foodTwoDishes.get(1));
							String imagepath3 = c.getDishPicPath(foodTwoDishes.get(2));
							imgDishOne.setIcon(new ImageIcon(imagepath1));
							imgDishTwo.setIcon(new ImageIcon(imagepath2));
							imgDishOne.setIcon(new ImageIcon(imagepath3));
							lblDishOne.setText(foodTwoDishes.get(0));
							lblDishTwo.setText(foodTwoDishes.get(1));
							lblDishTwo.setText(foodTwoDishes.get(2));

						} else if (cbRecipes.getSelectedItem().toString().equals(recommendedFoodThree)) {
							String imagepath1 = c.getDishPicPath(foodThreeDishes.get(0));
							String imagepath2 = c.getDishPicPath(foodThreeDishes.get(1));
							String imagepath3 = c.getDishPicPath(foodThreeDishes.get(2));
							imgDishOne.setIcon(new ImageIcon(imagepath1));
							imgDishTwo.setIcon(new ImageIcon(imagepath2));
							imgDishOne.setIcon(new ImageIcon(imagepath3));
							lblDishOne.setText(foodThreeDishes.get(0));
							lblDishTwo.setText(foodThreeDishes.get(1));
							lblDishTwo.setText(foodThreeDishes.get(2));
						}

						imgDishOne.setVisible(true);
						lblDishOne.setVisible(true);
						imgDishTwo.setVisible(true);
						lblDishTwo.setVisible(true);
						imgDishThree.setVisible(true);
						lblDishThree.setVisible(true);

					}

				}

			}
		});
		cbRecipes.setFont(new Font("Hiragino Sans GB", Font.PLAIN, 13));
		cbRecipes.setBounds(461, 97, 156, 27);
		cbRecipes.addItem("Choose one...");
		cbRecipes.addItem(recommendedFoodOne);
		cbRecipes.addItem(recommendedFoodTwo);
		cbRecipes.addItem(recommendedFoodThree);

		contentPane.add(cbRecipes);

		JLabel lblAltFood = new JLabel(
				"<html>Instead of " + user.getFoodName().toLowerCase() + ",<br>you can try:</html>");
		lblAltFood.setVerticalAlignment(SwingConstants.TOP);
		lblAltFood.setForeground(new Color(102, 205, 170));
		lblAltFood.setFont(new Font("Apple LiGothic", Font.PLAIN, 35));
		lblAltFood.setBounds(42, 35, 248, 124);
		contentPane.add(lblAltFood);

		// Recommended food #1
		String recommendationOneName = "<html>#1 " + recommendedFoodOne.toUpperCase() + "</html>";
		JLabel lblFoodOneName = new JLabel(recommendationOneName);
		lblFoodOneName.setFont(new Font("Apple LiGothic", Font.PLAIN, 25));
		lblFoodOneName.setForeground(Color.WHITE);
		lblFoodOneName.setHorizontalAlignment(SwingConstants.LEFT);
		lblFoodOneName.setBounds(42, 150, 237, 44);
		contentPane.add(lblFoodOneName);

		String recommendationOneMetrices = "<html>Each kg of " + recommendedFoodOne.toLowerCase() + " produces <b>"
				+ recommendedFoodOneGHGPerKG + " kgCO2(eq)</b> and provides <b>" + recommendedFoodOneCalPerKG
				+ " kcal</b>.</html>";
		JLabel lblFoodOneMetrices = new JLabel(recommendationOneMetrices);
		lblFoodOneMetrices.setVerticalAlignment(SwingConstants.TOP);
		lblFoodOneMetrices.setFont(new Font("Chalkboard", Font.PLAIN, 13));
		lblFoodOneMetrices.setForeground(new Color(245, 245, 245));
		lblFoodOneMetrices.setHorizontalAlignment(SwingConstants.LEFT);
		lblFoodOneMetrices.setBounds(42, 185, 248, 51);
		contentPane.add(lblFoodOneMetrices);

		// Recommended food #2
		String recommendationTwoName = "<html>#2 " + recommendedFoodTwo.toUpperCase() + "</html>";
		JLabel lblFoodTwoName = new JLabel(recommendationTwoName);
		lblFoodTwoName.setFont(new Font("Apple LiGothic", Font.PLAIN, 25));
		lblFoodTwoName.setForeground(Color.WHITE);
		lblFoodTwoName.setHorizontalAlignment(SwingConstants.LEFT);
		lblFoodTwoName.setBounds(42, 240, 237, 44);
		contentPane.add(lblFoodTwoName);

		String recommendationTwoMetrices = "<html>Each kg of " + recommendedFoodTwo.toLowerCase() + " produces <b>"
				+ recommendedFoodTwoGHGPerKG + " kgCO2(eq)</b> and provides <b>" + recommendedFoodTwoCalPerKG
				+ " kcal</b>.</html>";
		JLabel lblFoodTwoMetrices = new JLabel(recommendationTwoMetrices);
		lblFoodTwoMetrices.setForeground(new Color(245, 245, 245));
		lblFoodTwoMetrices.setFont(new Font("Chalkboard", Font.PLAIN, 13));
		lblFoodTwoMetrices.setVerticalAlignment(SwingConstants.TOP);
		lblFoodTwoMetrices.setHorizontalAlignment(SwingConstants.LEFT);
		lblFoodTwoMetrices.setBounds(42, 275, 248, 51);
		contentPane.add(lblFoodTwoMetrices);

		// Recommended food #3
		String recommendationThreeName = "<html>#3 " + recommendedFoodThree.toUpperCase() + "</html>";
		JLabel lblFoodThreeName = new JLabel(recommendationThreeName);
		lblFoodThreeName.setFont(new Font("Apple LiGothic", Font.PLAIN, 25));
		lblFoodThreeName.setForeground(Color.WHITE);
		lblFoodThreeName.setHorizontalAlignment(SwingConstants.LEFT);
		lblFoodThreeName.setBounds(42, 330, 237, 44);
		contentPane.add(lblFoodThreeName);

		String recommendationThreeMetrices = "<html>Each kg of " + recommendedFoodThree.toLowerCase() + " produces <b>"
				+ recommendedFoodThreeGHGPerKG + " kgCO2(eq)</b> and provides <b>" + recommendedFoodThreeCalPerKG
				+ " kcal</b>.</html>";
		JLabel lblFoodThreeMetrices = new JLabel(recommendationThreeMetrices);
		lblFoodThreeMetrices.setVerticalAlignment(SwingConstants.TOP);
		lblFoodThreeMetrices.setFont(new Font("Chalkboard", Font.PLAIN, 13));
		lblFoodThreeMetrices.setForeground(new Color(245, 245, 245));
		lblFoodOneMetrices.setForeground(new Color(245, 245, 245));
		lblFoodThreeMetrices.setHorizontalAlignment(SwingConstants.LEFT);
		lblFoodThreeMetrices.setBounds(42, 365, 248, 51);
		contentPane.add(lblFoodThreeMetrices);

		JButton btnEmailResults = new JButton("Email Results");
		btnEmailResults.setBackground(new Color(30, 144, 255));
		btnEmailResults.setOpaque(true);
		btnEmailResults.setBounds(90, 450, 120, 30);
		contentPane.add(btnEmailResults);

		btnEmailResults.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					SendEmail mail = new SendEmail(user.getEmail());
				} catch (GeneralSecurityException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				// to send the user a notice of email
				JOptionPane.showMessageDialog(null, "The email has been sent to " + user.getEmail() + ".");
			}
		});

		JButton btnDownloadPDF = new JButton("Download PDF");
		btnDownloadPDF.setBackground(new Color(0, 191, 255));
		btnDownloadPDF.setOpaque(true);
		btnDownloadPDF.setBounds(90, 480, 120, 30);
		contentPane.add(btnDownloadPDF);

		JButton btnTryAgain = new JButton("Try Again");
		btnTryAgain.setBackground(new Color(102, 205, 170));
		btnTryAgain.setOpaque(true);
		btnTryAgain.setBounds(90, 510, 120, 30);
		contentPane.add(btnTryAgain);

		// Import background image
		BufferedImage backgroundImage = null;
		String fileName = "altFood.jpg";
		try {
			URL url = getClass().getResource(fileName);
			backgroundImage = ImageIO.read(url);
		} catch (Exception e2) {
			// null
		}
		ImageIcon icon = new ImageIcon(backgroundImage);
		Image originalImage = icon.getImage();
		Image resizedImage = originalImage.getScaledInstance(800, 600, java.awt.Image.SCALE_SMOOTH);

		JLabel welcomeBackground = new JLabel("");
		welcomeBackground.setBackground(Color.WHITE);
		welcomeBackground.setForeground(Color.DARK_GRAY);
		welcomeBackground.setIcon(new ImageIcon(resizedImage));
		welcomeBackground.setBounds(0, 0, 800, 600);
		this.getContentPane().add(welcomeBackground);

	}
}
