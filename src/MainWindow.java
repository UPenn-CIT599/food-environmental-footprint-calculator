import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainWindow {

	private JFrame frame;
	private JTextField nameTextField;
	private JTextField emailTextField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		// Configures the welcome screen of the interface window
		frame = new JFrame();
		frame.setBounds(100, 100, 800, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new GridLayout(0, 2));

		// Configures welcome screen of the interface window

		// Welcome screen > Left panel
		JPanel welcomeLeftPanel = new JPanel();
		frame.getContentPane().add(welcomeLeftPanel);
		welcomeLeftPanel.setLayout(null); // Absolute layout for welcomeLeftPanel

		// Welcome screen > Left panel > App title
		JLabel lblAppTitle = new JLabel("Food Environmental Footprint Calculator");
		lblAppTitle.setFont(new Font("SansSerif", Font.PLAIN, 14));
		lblAppTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblAppTitle.setBounds(45, 91, 314, 43);
		welcomeLeftPanel.add(lblAppTitle);

		// Welcome screen > Left panel > Name label and input field
		JLabel lblName = new JLabel("Name");
		lblName.setFont(new Font("SansSerif", Font.PLAIN, 13));
		lblName.setBounds(89, 146, 61, 16);
		welcomeLeftPanel.add(lblName);

		JTextField nameTextField = new JTextField();
		nameTextField.setBounds(138, 141, 184, 26);
		welcomeLeftPanel.add(nameTextField);
		nameTextField.setColumns(10);

		// Welcome screen > Left panel > Email label and input field
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setFont(new Font("SansSerif", Font.PLAIN, 13));
		lblEmail.setBounds(89, 174, 61, 16);
		welcomeLeftPanel.add(lblEmail);

		JTextField emailTextField = new JTextField();
		emailTextField.setBounds(138, 169, 184, 26);
		welcomeLeftPanel.add(emailTextField);
		emailTextField.setColumns(10);

		// Welcome screen > Left panel > "Begin" button
		JButton btnBegin = new JButton("Begin");
		btnBegin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String userName = nameTextField.getText();
				String userEmail = emailTextField.getText();

				// Write function here to check for validity of user inputs
				if (userName.equals("") || userEmail.equals("")) {
					JOptionPane.showMessageDialog(frame, "Please enter both your name and email address.");
				} else {

					CalculatorFrame cf = new CalculatorFrame();
					cf.setVisible(true);
					cf.setSize(800, 400);
					cf.setTitle("Food Environmental Footprint Calculator");

					// Write line to close the welcome screen after calling the CalculatorFrame
				}

			}
		});

		btnBegin.setFont(new Font("SansSerif", Font.PLAIN, 13));
		btnBegin.setBounds(89, 218, 230, 43);
		welcomeLeftPanel.add(btnBegin);

		// Welcome screen > Right panel
		JPanel welcomeRightPanel = new JPanel();
		frame.getContentPane().add(welcomeRightPanel);

		// Welcome screen > Right panel > Background image via JLabel
		JLabel welcomeRightPanelBackground = new JLabel("");
		ImageIcon welcomeImage = new ImageIcon(
				"/Users/iris/Documents/GitHub/cit591-final-project/src/welcome-image.jpg"); // Photo by Tobias Tullius on Unsplash, https://unsplash.com/photos/PXXtq6bp6cs
		Image originalImage = welcomeImage.getImage();
		Image resizedImage = originalImage.getScaledInstance(400, 400, java.awt.Image.SCALE_SMOOTH); // Resize image to fit welcomeRightPanel
		ImageIcon resizedWelcomeImage = new ImageIcon(resizedImage);
		welcomeRightPanelBackground.setIcon(resizedWelcomeImage);
		welcomeRightPanel.add(welcomeRightPanelBackground);

	}
}
