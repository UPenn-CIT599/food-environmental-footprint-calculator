import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class ResultsFrame extends JFrame {

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

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ResultsFrame frame = new ResultsFrame(new Point(100, 100), new User("", ""));
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame
	 */
	public ResultsFrame(Point position, User user) {


		setUser(user);
		setLocation(position);
		setSize(800, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		// Create fonts
		Font appleLG25 = new ImportFont().createFont("fonts/appleLG.ttf", 25);


		// Calculate the GHG(eq) emissions based on user's inputs
		Calculator c = new Calculator();
		double weightKG = user.getFoodWeight();
		String selectedFood = user.getFoodName();
		double equivalentGHG = c.getFoodGHGEmission(selectedFood, weightKG);

		// Overall description of user's GHG(eq) emissions
		double annualGHG = c.getAnnualGHG(equivalentGHG);
		String resultsString = "<html>If you eat " + String.format("%.2f", weightKG) + " kg of "
				+ selectedFood.toLowerCase() + " everyday for one year,<br> you will contribute to <b>"
				+ String.format("%.2f", equivalentGHG) + "</b> kg of CO2(eq), <br>which is equivalent to...</html>";
		JLabel results = new JLabel(resultsString);
		results.setBackground(Color.WHITE);
		results.setFont(appleLG25);
		results.setBounds(35, 37, 700, 140);
		results.setForeground(new Color(102, 205, 170));
		this.getContentPane().add(results);

		// Displays the GHG(eq) in terms of flight kilometres
		String flightString = "<html>... flying on a plane over <b>"
				+ String.format("%.0f", c.getEquivalentFlightKM(annualGHG)) + "</b> kilometre(s)";
		JLabel resultsFlightKM = new JLabel(flightString);
		resultsFlightKM.setFont(new Font("Tahoma", Font.PLAIN, 16));
		resultsFlightKM.setBounds(182, 206, 220, 70);
		resultsFlightKM.setForeground(Color.WHITE);
		this.getContentPane().add(resultsFlightKM);

		// Displays the GHG(eq) in terms of lightbulb days
		String lightbulbString = "<html>... powering a typical LED lightbulb for <b>"
				+ String.format("%.0f", c.getEquivalentBulbLightDays(annualGHG)) + "</b> day(s)";
		JLabel resultsLightbulbDays = new JLabel(lightbulbString);
		resultsLightbulbDays.setFont(new Font("Tahoma", Font.PLAIN, 16));
		resultsLightbulbDays.setBounds(271, 340, 220, 70);
		resultsLightbulbDays.setForeground(Color.WHITE);
		this.getContentPane().add(resultsLightbulbDays);

		// Displays the GHG(eq) in terms of car kilometres
		String carString = "<html>... driving a passenger car for <b>"
				+ String.format("%.0f", c.getCarKMEquivalent(annualGHG)) + "</b> kilometre(s)";
		JLabel resultsCarKM = new JLabel(carString);
		resultsCarKM.setFont(new Font("Tahoma", Font.PLAIN, 16));
		resultsCarKM.setBounds(105, 452, 200, 70);
		resultsCarKM.setForeground(Color.WHITE);
		this.getContentPane().add(resultsCarKM);

		// Button to click to display the next frame (AlternativeFoodFrame)
		JButton btnExploreAltFood = new JButton("Tell me more!");
		btnExploreAltFood.setFont(appleLG25);
		btnExploreAltFood.setBackground(new Color(238, 232, 170));
		btnExploreAltFood.setForeground(Color.BLACK);
		btnExploreAltFood.setBounds(597, 301, 150, 70);
		this.getContentPane().add(btnExploreAltFood);

		btnExploreAltFood.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Point position = getLocation();
				AlternativeFoodFrame frame4 = new AlternativeFoodFrame(position, user);
				frame4.setVisible(true);
				setVisible(false);
			}
		});

		// Import and resize background image
		BufferedImage backgroundImage = null;
		try {
			URL url = getClass().getResource("images/chewpaca3.jpg");
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
