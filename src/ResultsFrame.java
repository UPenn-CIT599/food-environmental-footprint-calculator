import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class ResultsFrame extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ResultsFrame frame = new ResultsFrame();
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
	public ResultsFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		// Hardcoded now, but to instantiate the variables with calculator function later
		/**
		 * @author Xiaolu
		 * see the functions below
		 */
		UserSelection us = new UserSelection();//this should be the same userSelection object which has created last frame
		                                         //Else it will throw null pointer exception.
		Calculator c = new Calculator();
		double weightKG = us.getWeight();
		String selectedFood = us.getFood();
		double equivalentGHG = c.getFoodGHGEmission(selectedFood, weightKG);

		// Overall description of user's GHG(eq) emissions
		double annualGHG = c.getAnnualGHG(equivalentGHG);
		String resultsString = "<html>If you eat "+ weightKG +" kg of " + selectedFood.toLowerCase() + " everyday for one year,<br> you will contribute to <b>" + equivalentGHG + "</b> kg of CO2(eq), <br>which is equivalent to...</html>";
		JLabel results = new JLabel(resultsString);
		results.setBackground(Color.WHITE);
		results.setFont(new Font("Apple LiGothic", Font.PLAIN, 25));
		results.setBounds(35, 37, 456, 140);
		results.setForeground(new Color(102, 205, 170));
		this.getContentPane().add(results);

		// Displays the GHG(eq) in terms of flight kilometres		
		String flightString = "<html>... flying on a plane over <b>"+String.format("%.2f", c.getEquivalentFlightKM(annualGHG))+"</b> kilometre(s)";
		JLabel resultsFlightKM = new JLabel(flightString);
		resultsFlightKM.setFont(new Font("Tahoma", Font.PLAIN, 16));
		resultsFlightKM.setBounds(182, 206, 220, 70);
		resultsFlightKM.setForeground(Color.WHITE);
		this.getContentPane().add(resultsFlightKM);

		// Displays the GHG(eq) in terms of lightbulb days
		String lightbulbString = "<html>... powering a typical LED lightbulb for <b>"+String.format("%.2f", c.getEquivalentBulbLightDays(annualGHG))+"</b> day(s)";
		JLabel resultsLightbulbDays = new JLabel(lightbulbString);
		resultsLightbulbDays.setFont(new Font("Tahoma", Font.PLAIN, 16));
		resultsLightbulbDays.setBounds(271, 340, 220, 70);
		resultsLightbulbDays.setForeground(Color.WHITE);
		this.getContentPane().add(resultsLightbulbDays);

		// Displays the GHG(eq) in terms of car kilometres		
		String carString = "<html>... driving a passenger car for <b>"+String.format("%.2f", c.getCarKMEquivalent(annualGHG))+"</b> kilometre(s)";
		JLabel resultsCarKM = new JLabel(carString);
		resultsCarKM.setFont(new Font("Tahoma", Font.PLAIN, 16));
		resultsCarKM.setBounds(105, 452, 200, 70);
		resultsCarKM.setForeground(Color.WHITE);
		this.getContentPane().add(resultsCarKM);

		JButton btnExploreAltFood = new JButton("Tell me more!");
		btnExploreAltFood.setFont(new Font("Apple LiGothic", Font.BOLD, 25));
		btnExploreAltFood.setBackground(new Color(238, 232, 170));
		btnExploreAltFood.setForeground(Color.BLACK);
		btnExploreAltFood.setBounds(597, 301, 150, 70);
		this.getContentPane().add(btnExploreAltFood);
		/**
		 * @author Xiaolu
		 * Add listener for this button
		 */
		btnExploreAltFood.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AlternativeFoodFrame af = new AlternativeFoodFrame();
				af.setVisible(true);
				setVisible(false);
			}
		});

		// Resize background image to fit window
		ImageIcon welcomeMascot = new ImageIcon("chewpaca3.jpg"); // Only use absolute path for testing purpose
		Image originalImage = welcomeMascot.getImage();
		Image resizedImage = originalImage.getScaledInstance(800, 600, java.awt.Image.SCALE_SMOOTH); // Resize image to fit welcomeRightPanel
		this.getContentPane().setLayout(null);

		JLabel backgroundImage = new JLabel("");
		backgroundImage.setBackground(Color.WHITE);
		backgroundImage.setForeground(Color.DARK_GRAY);
		backgroundImage.setIcon(new ImageIcon(resizedImage));
		backgroundImage.setBounds(0, 0, 800, 600);
		this.getContentPane().add(backgroundImage);

	}
}
