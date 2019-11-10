import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ResultsFrame extends JFrame {

	/**
	 * (Iris: I'm not sure what the first line is for, but need to add this line for the code to work)
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	// Hashmap of user-input food list here
	// Hashmap of results calculated based on user-input food list here

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
	 * For the purpose of this skeleton, we are not linking the class to the result files and user-input food list yet.
	 * This will need to be done once we confirm the output format of the calculation results.
	 */
	public ResultsFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 3, 0, 0));
		
		// Read from the first item in the HashMap foodList
		
		// ResultsFrame > resultTextPanel: Leftmost panel describing the food that user has selected
		JPanel resultTextPanel = new JPanel();
		contentPane.add(resultTextPanel);
		resultTextPanel.setLayout(null);
		
		// Descriptive text of food that user has selected
		JLabel lblYouConsumed = new JLabel("You consumed:");
		lblYouConsumed.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblYouConsumed.setHorizontalAlignment(SwingConstants.CENTER);
		lblYouConsumed.setBounds(6, 44, 251, 16);
		resultTextPanel.add(lblYouConsumed);
	
		JLabel lblWeightFood = new JLabel("<double weight>g of <String food>");
		lblWeightFood.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		lblWeightFood.setHorizontalAlignment(SwingConstants.CENTER);
		lblWeightFood.setBounds(6, 72, 251, 60);
		resultTextPanel.add(lblWeightFood);
		
		JLabel lblCalories = new JLabel("<int calories> kcal/100g");
		lblCalories.setHorizontalAlignment(SwingConstants.CENTER);
		lblCalories.setBounds(6, 136, 251, 16);
		resultTextPanel.add(lblCalories);
		
		// Display of vector image of presented food
		JTextArea txtrVectorImageOfFood = new JTextArea();
		txtrVectorImageOfFood.setLineWrap(true);
		txtrVectorImageOfFood.setWrapStyleWord(true);
		txtrVectorImageOfFood.setText("Vector image of selected food item to be displayed here");
		txtrVectorImageOfFood.setBounds(37, 178, 187, 120);
		resultTextPanel.add(txtrVectorImageOfFood);
		
		// Button to go to results of previous food on the user input food list, if any
		JButton prevFood = new JButton("<");
		prevFood.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Code here to display results of the previous food item from the user input food list, if any
			}
		});
		prevFood.setBounds(161, 310, 31, 29);
		resultTextPanel.add(prevFood);
		
		// Button to go to results of next food on the user input food list, if any)
		JButton nextFood = new JButton(">");
		nextFood.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Code here to display results of the next food item from the user input food list, if any
			}
		});
		nextFood.setBounds(192, 310, 31, 29);
		resultTextPanel.add(nextFood);
		
		// ResultsFrame > equivalentEmissionsPanel: Middle panel describing the equivalent CO2 emission from the amount of food consumption
		JPanel equivalentEmissionsPanel = new JPanel();
		equivalentEmissionsPanel.setBackground(new Color(255, 255, 255));
		contentPane.add(equivalentEmissionsPanel);
		equivalentEmissionsPanel.setLayout(null);
		
		// Descriptive text of equivalent emissions of food
		JLabel lblEquivalentEmissionsText = new JLabel("By eating that, you produced:");
		lblEquivalentEmissionsText.setForeground(new Color(0, 0, 0));
		lblEquivalentEmissionsText.setHorizontalAlignment(SwingConstants.CENTER);
		lblEquivalentEmissionsText.setBounds(6, 33, 251, 16);
		equivalentEmissionsPanel.add(lblEquivalentEmissionsText);
		
		// Graphical presentation of equivalent emissions results; graphical presentation style TBC
		JTextArea txtrEmissionsResults = new JTextArea();
		txtrEmissionsResults.setBackground(UIManager.getColor("Button.disabledText"));
		txtrEmissionsResults.setLineWrap(true);
		txtrEmissionsResults.setWrapStyleWord(true);
		txtrEmissionsResults.setText("Results of emissions to be displayed here");
		txtrEmissionsResults.setBounds(24, 81, 217, 259);
		equivalentEmissionsPanel.add(txtrEmissionsResults);
		
		// ResultsFrame > alternativeFoodsPanel: Rightmost panel listing 
		JPanel alternativeFoodsPanel = new JPanel();
		contentPane.add(alternativeFoodsPanel);
		alternativeFoodsPanel.setLayout(null);
		
		// Descriptive text of equivalent emissions of food
		JTextArea txtrAlternativeFoods = new JTextArea();
		txtrAlternativeFoods.setBackground(UIManager.getColor("Button.background"));
		txtrAlternativeFoods.setForeground(Color.BLACK);
		txtrAlternativeFoods.setLineWrap(true);
		txtrAlternativeFoods.setWrapStyleWord(true);
		txtrAlternativeFoods.setText("Consider these foods which can reduce your environmental footprint!");
		txtrAlternativeFoods.setBounds(30, 31, 210, 55);
		alternativeFoodsPanel.add(txtrAlternativeFoods);
		
		// Graphical presentation of alternative foods; graphical presentation style TBC
		JTextArea txtrAlternativeFoodsResults = new JTextArea();
		txtrAlternativeFoodsResults.setWrapStyleWord(true);
		txtrAlternativeFoodsResults.setLineWrap(true);
		txtrAlternativeFoodsResults.setText("Graphical representtion of alternative foods of lower EF footprint (scrollable)");
		txtrAlternativeFoodsResults.setBounds(27, 105, 213, 170);
		alternativeFoodsPanel.add(txtrAlternativeFoodsResults);
		
		// Button to download results as PDF
		JButton btnDownloadPdf = new JButton("Download PDF");
		btnDownloadPdf.setBounds(48, 287, 176, 29);
		alternativeFoodsPanel.add(btnDownloadPdf);
		btnDownloadPdf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Code here to download results in a PDF
			}
		});
	
		// Button to email user the results
		JButton btnEmailMe = new JButton("Email me the results!");
		btnEmailMe.setBounds(48, 317, 176, 29);
		alternativeFoodsPanel.add(btnEmailMe);
		btnEmailMe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Code here to send results in an email to user
			}
		});

	}
}
