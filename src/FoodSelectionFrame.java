import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

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
		ImageIcon welcomeMascot = new ImageIcon("chewpaca2.jpg"); // Only use absolute path for
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
		/**
		 * @author Xiaolu
		 * Add button functions to save user selection and move to next frame.
		 */
		btnFindOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String food = cbFood.getSelectedItem().toString();
				double w = Double.parseDouble(weight.getText());
				UserSelection u = new UserSelection(); //need to create the user and user selection object in the mainwindow 
				u.setFood(food);
				if(cbWeightUnit.getSelectedItem().toString().equals("kg")) {
					u.setWeight(w);
				}
				else {
					u.setWeight(w / 1000);
				}
				ResultsFrame rf = new ResultsFrame();
				rf.setVisible(true);
				setVisible(false);
		}});

		// FOOD CATEGORY SELECTION
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

		/**
		 * @author Xiaolu 
		 * Read food group from the back end.
		 */
		for(String s : new Calculator().generalGroupSelect(new FoodDataReader("FoodGHG.csv"))) {
			cbFoodCategories.addItem(s);
		}

		this.getContentPane().add(cbFoodCategories);

		cbFoodCategories.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (!cbFoodCategories.getSelectedItem().toString().equals("Choose one...")) {
					lblFood.setVisible(true);
					cbFood.removeAllItems();
					/**
					 * @author Xiaolu
					 * read food contributes from backend. Have deleted the Hardcodes.
					 */
					Calculator c = new Calculator();
					ArrayList<String> s = c.generalGroupSelect(new FoodDataReader("FoodGHG.csv"));
					for(int i = 0; i < s.size(); i++) {
						if (cbFoodCategories.getSelectedItem().equals(s.get(i))) {
							ArrayList<String> a = c.foodGroupSelect(s.get(i));
							for(int j = 0; j < a.size(); j++) {
								cbFood.addItem(a.get(j));
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

		// Welcome background of main window, showing Chewpaca the Alpaca's illustration
		JLabel welcomeBackground = new JLabel("");
		welcomeBackground.setIcon(new ImageIcon(resizedImage));
		welcomeBackground.setBounds(0, 0, 806, 584);
		this.getContentPane().add(welcomeBackground);

	}

}
