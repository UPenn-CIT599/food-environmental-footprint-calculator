import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import java.net.URL;

public class MainFrame extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
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
	public MainFrame() {

		// Create fonts
		Font appleLG25 = new ImportFont().createFont("fonts/appleLG.ttf", 25);
		Font appleLG36 = new ImportFont().createFont("fonts/appleLG.ttf", 36);
		
		Font hiragino12 = new ImportFont().createFont("fonts/Hiragino Sans GB W3.ttf", 12);
		Font hiragino16 = new ImportFont().createFont("fonts/Hiragino Sans GB W3.ttf", 16);
		Font hiragino20 = new ImportFont().createFont("fonts/Hiragino Sans GB W3.ttf", 20);
		
	

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		// Welcome title displaying name of the application (first line)
		JLabel welcomeTitle1 = new JLabel();
		welcomeTitle1.setFont(appleLG36);
		welcomeTitle1.setForeground(Color.WHITE);
		welcomeTitle1.setOpaque(false);
		welcomeTitle1.setText("<html>Food Environmental");
		welcomeTitle1.setBounds(50, 37, 427, 76);

		// Welcome title displaying name of the application (second line)
		JLabel welcomeTitle2 = new JLabel();
		welcomeTitle2.setFont(appleLG36);
		welcomeTitle2.setForeground(Color.WHITE);
		welcomeTitle2.setOpaque(false);
		welcomeTitle2.setText("Footprint Calculator");
		welcomeTitle2.setBounds(50, 96, 427, 57);

		this.getContentPane().add(welcomeTitle1);
		this.getContentPane().add(welcomeTitle2);

		// Separator line below the welcome titl
		JPanel separator = new JPanel();
		separator.setBackground(Color.WHITE);
		separator.setOpaque(true);
		separator.setBounds(50, 165, 400, 1);
		this.getContentPane().add(separator);

		// Chewpaca's speech bubble
		JLabel introChewpaca = new JLabel(
				"<html><i>HEE-HAW!</i> I'm Chewpaca, the friendliest and greenest alpaca in the world! Tell me about yourself to get started.</html>");
		introChewpaca.setHorizontalAlignment(SwingConstants.CENTER);
		introChewpaca.setFont(hiragino20);
		introChewpaca.setBounds(65, 200, 361, 149);
		this.getContentPane().add(introChewpaca);

		// Input fields for user's name
		JTextField txtUserName = new JTextField("Name");
		txtUserName.setFont(hiragino16);
		txtUserName.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.WHITE, 1),
				BorderFactory.createEmptyBorder(5, 5, 5, 5)));
		txtUserName.setBounds(123, 383, 255, 26);
		this.getContentPane().add(txtUserName);

		// Input field for user's email address
		JTextField txtUserEmail = new JTextField("Email");
		txtUserEmail.setFont(hiragino16);
		txtUserEmail.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.WHITE, 1),
				BorderFactory.createEmptyBorder(5, 5, 5, 5)));
		txtUserEmail.setBounds(123, 418, 255, 26);
		this.getContentPane().add(txtUserEmail);

		// Button to move to the next frame (FoodSelectionFrame)
		JButton letsGo = new JButton("Let's go!");
		letsGo.setBackground(new Color(255, 182, 193));
		letsGo.setFont(appleLG25);
		letsGo.setBounds(199, 456, 117, 61);
		getContentPane().add(letsGo);
		letsGo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// Check if user has input the name and email
				if (!txtUserName.getText().equals("Name") && !txtUserName.getText().equals("")
						&& !txtUserEmail.getText().equals("Email") && !txtUserEmail.getText().equals("")) {
					Point position = getLocation();
					User user = new User(txtUserName.getText(), txtUserEmail.getText());
					FoodSelectionFrame frame2 = new FoodSelectionFrame(position, user);
					frame2.setVisible(true);
					setVisible(false);
					dispose();
				}
				// Pop-up dialog if user did not input name and email
				else {
					JOptionPane.showMessageDialog(null, "Don't be shy, tell me your name and email!");
				}
			}

		});

		// Small credits to ourselves
		JLabel teamName1 = new JLabel(
				"<html>(Brought to you by the really environmentally-friendly people of CIT591 Fall 2019 Team 50)</html>");
		teamName1.setFont(hiragino12);
		teamName1.setForeground(Color.WHITE);
		teamName1.setBounds(580, 62, 200, 100);
		this.getContentPane().add(teamName1);

		// Import and resize background image
		BufferedImage backgroundImage = null;
		try {
			URL url = getClass().getResource("images/chewpaca1.jpg");
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
