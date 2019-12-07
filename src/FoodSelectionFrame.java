import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class FoodSelectionFrame extends JFrame {

	private JPanel contentPane;
	private User user;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FoodSelectionFrame frame = new FoodSelectionFrame(new Point(100, 100), new User("", ""));
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

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
	
	/**
	 * Capitalises the first letter of the string
	 * @param str is the input string to be capitalised
	 * @return A string with the first letter capitalised
	 */
	public static String capitalise(String str) {
	    if(str == null || str.isEmpty()) {
	        return str;
	    }

	    return str.substring(0, 1).toUpperCase() + str.substring(1);
	}

	/**
	 * Create the frame
	 */
	public FoodSelectionFrame(Point position, User user) {
		setLocation(position);
		setSize(800, 600);
		setUser(user);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		this.getContentPane().setLayout(null);

		// User to select food item, after selecting food category
		JLabel lblFood = new JLabel("What is your food item?");
		lblFood.setForeground(Color.WHITE);
		lblFood.setHorizontalAlignment(SwingConstants.CENTER);
		lblFood.setFont(new Font("Apple LiGothic", Font.PLAIN, 20));
		lblFood.setBounds(414, 171, 330, 27);
		lblFood.setVisible(false);
		this.getContentPane().add(lblFood);

		JComboBox cbFood = new JComboBox();
		cbFood.setFont(new Font("Hiragino Sans GB", Font.PLAIN, 13));
		cbFood.setBounds(460, 210, 240, 27);
		cbFood.setVisible(false);
		this.getContentPane().add(cbFood);

		// User to input weight of food, after selecting food category
		JLabel lblWeight = new JLabel("How much of it do you eat?");
		lblWeight.setForeground(Color.WHITE);
		lblWeight.setHorizontalAlignment(SwingConstants.CENTER);
		lblWeight.setFont(new Font("Apple LiGothic", Font.PLAIN, 20));
		lblWeight.setBounds(414, 260, 330, 27);
		lblWeight.setVisible(false);
		this.getContentPane().add(lblWeight);

		JTextField weight = new JTextField("Enter weight of food");
		weight.setFont(new Font("Hiragino Sans GB", Font.PLAIN, 12));
		weight.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.WHITE, 1),
				BorderFactory.createEmptyBorder(5, 5, 5, 5)));
		weight.setBounds(471, 303, 155, 25);
		weight.setVisible(false);
		this.getContentPane().add(weight);

		JComboBox cbWeightUnit = new JComboBox();
		cbWeightUnit.setBounds(628, 303, 72, 27);
		cbWeightUnit.addItem("g");
		cbWeightUnit.addItem("kg");
		cbWeightUnit.setVisible(false);
		this.getContentPane().add(cbWeightUnit);

		// Button to run calculations based on user inputs, and move to next frame
		// (ResultsFrame)
		JButton btnFindOut = new JButton("Find Out!");
		btnFindOut.setBackground(new Color(255, 182, 193));
		btnFindOut.setFont(new Font("Apple LiGothic", Font.PLAIN, 25));
		btnFindOut.setBounds(506, 384, 145, 62);
		btnFindOut.setVisible(false);
		this.getContentPane().add(btnFindOut);
		btnFindOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Check if the input weight is numeric
				try {
					double w = Double.parseDouble(weight.getText());
					// If a food item has been selected
					if (!cbFood.getSelectedItem().equals("Choose one...")) {
						String food = cbFood.getSelectedItem().toString();
						user.setFoodName(food);
						if (cbWeightUnit.getSelectedItem().toString().equals("kg")) {
							user.setFoodWeight(w);
						} else {
							user.setFoodWeight(w / 1000);
						}
						Point position = getLocation();
						ResultsFrame frame3 = new ResultsFrame(position, user);
						frame3.setVisible(true);
						setVisible(false);
						dispose();
					}
					// If no food item has been selected, prompt user to choose food
					else {
						JOptionPane.showMessageDialog(null, "Please choose a food item!");
					}
				} catch (NumberFormatException e2) {
					// If user has not selected a food item and has not input weight
					if (cbFood.getSelectedItem().equals("Choose one...")) {
						JOptionPane.showMessageDialog(null, "Please choose a food item and key in a valid weight!");
					}
					// If user has selected a food item but has not input weight
					else {
						JOptionPane.showMessageDialog(null, "Please key in a valid weight!");
					}
				}
			}
		});

		// User to select food category
		JLabel lblCategory = new JLabel("What category does your food fall under?");
		lblCategory.setHorizontalAlignment(SwingConstants.CENTER);
		lblCategory.setFont(new Font("Apple LiGothic", Font.PLAIN, 16));
		lblCategory.setForeground(Color.WHITE);
		lblCategory.setBounds(414, 57, 330, 48);
		this.getContentPane().add(lblCategory);

		JComboBox cbFoodCategories = new JComboBox();
		cbFoodCategories.setFont(new Font("Hiragino Sans GB", Font.PLAIN, 13));
		cbFoodCategories.setBounds(460, 117, 240, 27);
		cbFoodCategories.addItem("Choose one...");

		// Add category types to Food Categories ComboBox from input csv
		Calculator c = new Calculator();
		ArrayList<String> categories = c.generalGroupSelect(new FoodDataReader("FoodGHG.csv"));
		Collections.sort(categories);
		for (String category : categories) {
			cbFoodCategories.addItem(capitalise(category));
		}
		this.getContentPane().add(cbFoodCategories);

		// Check user's selection of food category
		cbFoodCategories.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {

				// User has selected a food category
				if (!cbFoodCategories.getSelectedItem().toString().equals("Choose one...")) {
					lblFood.setVisible(true);
					cbFood.removeAllItems();

					// Add food items to Food ComboBox from input csv, based on the selected food
					// category
					
					cbFood.addItem("Choose one...");
					for (int i = 0; i < categories.size(); i++) {
						if (cbFoodCategories.getSelectedItem().equals(capitalise(categories.get(i)))) {
							ArrayList<String> foodItems = c.foodGroupSelect(categories.get(i));
							Collections.sort(foodItems);
							for (int j = 0; j < foodItems.size(); j++) {
								cbFood.addItem(capitalise(foodItems.get(j)));
							}
						}
					}
					cbFood.setVisible(true);
					weight.setVisible(true);
					lblWeight.setVisible(true);
					cbWeightUnit.setVisible(true);
					btnFindOut.setVisible(true);

				} else {
					lblFood.setVisible(false);
					cbFood.setVisible(false);
					weight.setVisible(false);
					lblWeight.setVisible(false);
					cbWeightUnit.setVisible(false);
					btnFindOut.setVisible(false);
				}
			}
		});

		// Import image from src folder
		String fileName = "chewpaca2.jpg";

		// Resize image to fit window's resolution
		ImageIcon icon = new ImageIcon(getClass().getResource(fileName));
		Image originalImage = icon.getImage();
		Image resizedImage = originalImage.getScaledInstance(800, 600, java.awt.Image.SCALE_SMOOTH);

		JLabel background = new JLabel("");
		background.setBackground(Color.WHITE);
		background.setForeground(Color.DARK_GRAY);
		background.setIcon(new ImageIcon(resizedImage));
		background.setBounds(0, 0, 800, 600);
		this.getContentPane().add(background);

	}

}
