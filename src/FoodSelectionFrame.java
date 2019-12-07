import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class FoodSelectionFrame extends JFrame {

	private JPanel contentPane;
	private String userName;
	private String userEmail;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FoodSelectionFrame frame = new FoodSelectionFrame(new Point(100,100));
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void setUserName(String name) {
		this.userName = name;
	}
	
	public void setUserEmail(String email) {
		this.userEmail = email;
	}

	/**
	 * Create the frame.
	 */
	public FoodSelectionFrame(Point position) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100,100,800,600);
		setLocation(position);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		// Resize background image to fit window
		ImageIcon welcomeMascot = new ImageIcon("/Users/iris/Desktop/chewpaca2.jpg"); // Only use absolute path for
																						// testing purpose
		Image originalImage = welcomeMascot.getImage();
		Image resizedImage = originalImage.getScaledInstance(800, 600, java.awt.Image.SCALE_SMOOTH); // Resize image to
																										// fit
																										// welcomeRightPanel
		this.getContentPane().setLayout(null);

		// FOOD ITEMS SELECTION
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

		// INPUT WEIGHT OF FOOD
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

		// BUTTON TO RUN THE CALCULATIONS
		JButton btnFindOut = new JButton("Find Out!");
		btnFindOut.setBackground(new Color(255, 182, 193));
		btnFindOut.setFont(new Font("Apple LiGothic", Font.PLAIN, 25));
		btnFindOut.setBounds(506, 384, 145, 62);
		btnFindOut.setVisible(false);
		this.getContentPane().add(btnFindOut);

		// FOOD CATEGORY SELECTION
		JLabel lblCategory = new JLabel("What category does your food fall under?");
		lblCategory.setHorizontalAlignment(SwingConstants.CENTER);
		lblCategory.setFont(new Font("Apple LiGothic", Font.PLAIN, 20));
		lblCategory.setForeground(Color.WHITE);
		lblCategory.setBounds(414, 57, 330, 48);
		this.getContentPane().add(lblCategory);

		JComboBox cbFoodCategories = new JComboBox();
		cbFoodCategories.setFont(new Font("Hiragino Sans GB", Font.PLAIN, 13));
		cbFoodCategories.setBounds(460, 117, 240, 27);
		cbFoodCategories.addItem("Choose one...");

		// NOTE: Replace this portion with function that provides ArrayList of food
		// categories
		cbFoodCategories.addItem("Cereals");
		cbFoodCategories.addItem("Dairy");
		cbFoodCategories.addItem("Desserts");
		cbFoodCategories.addItem("Drinks");
		cbFoodCategories.addItem("Fish");
		cbFoodCategories.addItem("Fruits");
		cbFoodCategories.addItem("Meats");
		cbFoodCategories.addItem("Nuts");
		cbFoodCategories.addItem("Vegetables");

		this.getContentPane().add(cbFoodCategories);

		cbFoodCategories.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (!cbFoodCategories.getSelectedItem().toString().equals("Choose one...")) {
					lblFood.setVisible(true);
					cbFood.removeAllItems();

					// NOTE: Hardcoded for now. Change code to access ArrayLists from Hashmap later.
					if (cbFoodCategories.getSelectedItem().equals("Cereals")) {
						cbFood.addItem("Choose one...");
						cbFood.addItem("Barley");
						cbFood.addItem("Maize/corn");
						cbFood.addItem("Oats");
					} else if (cbFoodCategories.getSelectedItem().equals("Dairy")) {
						cbFood.addItem("Choose one...");
						cbFood.addItem("Butter");
						cbFood.addItem("Cheese");
						cbFood.addItem("Eggs");
					} else if (cbFoodCategories.getSelectedItem().equals("Desserts")) {
						cbFood.addItem("Choose one...");
						cbFood.addItem("Chocolate");
					} else if (cbFoodCategories.getSelectedItem().equals("Drinks")) {
						cbFood.addItem("Choose one...");
						cbFood.addItem("Coffee");
						cbFood.addItem("Milk");
						cbFood.addItem("Wine");
					} else if (cbFoodCategories.getSelectedItem().equals("Fish")) {
						cbFood.addItem("Choose one...");
						cbFood.addItem("Tuna");
						cbFood.addItem("Lobster");
						cbFood.addItem("Salmon");
					} else if (cbFoodCategories.getSelectedItem().equals("Dairy")) {
						cbFood.addItem("Choose one...");
						cbFood.addItem("Butter");
						cbFood.addItem("Cheese");
						cbFood.addItem("Eggs");
					} else if (cbFoodCategories.getSelectedItem().equals("Fruits")) {
						cbFood.addItem("Choose one...");
						cbFood.addItem("Apple");
						cbFood.addItem("Orange");
						cbFood.addItem("Grapes");
					} else if (cbFoodCategories.getSelectedItem().equals("Meats")) {
						cbFood.addItem("Choose one...");
						cbFood.addItem("Chicken");
						cbFood.addItem("Pork");
						cbFood.addItem("Beef");
					} else if (cbFoodCategories.getSelectedItem().equals("Nuts")) {
						cbFood.addItem("Choose one...");
						cbFood.addItem("Peanut");
						cbFood.addItem("Almond");
						cbFood.addItem("Cashew");
					} else if (cbFoodCategories.getSelectedItem().equals("Vegetables")) {
						cbFood.addItem("Choose one...");
						cbFood.addItem("Lettuce");
						cbFood.addItem("Carrot");
						cbFood.addItem("Spinach");
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

		// Welcome background of main window, showing Chewpaca the Alpaca's illustration
		JLabel welcomeBackground = new JLabel("");
		welcomeBackground.setIcon(new ImageIcon(resizedImage));
		welcomeBackground.setBounds(0, 0, 806, 584);
		this.getContentPane().add(welcomeBackground);

	}

}
