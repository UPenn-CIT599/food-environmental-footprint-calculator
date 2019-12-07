import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;

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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		// Resize background image to fit window
		ImageIcon welcomeMascot = new ImageIcon("/Users/iris/Desktop/chewpaca1.jpg"); // Only use absolute path for
																						// testing purpose
		Image originalImage = welcomeMascot.getImage();
		Image resizedImage = originalImage.getScaledInstance(800, 600, java.awt.Image.SCALE_SMOOTH); // Resize image to
																										// fit
																										// welcomeRightPanel

		// Welcome title, displaying the name of the project "FOOD ENVIRONMENTAL
		// FOOTPRINT CALCULATOR"
		JLabel welcomeTitle1 = new JLabel();
		welcomeTitle1.setFont(new Font("Apple LiGothic", Font.PLAIN, 56));
		welcomeTitle1.setForeground(Color.WHITE);
		welcomeTitle1.setOpaque(false);
		welcomeTitle1.setText("<html>Food Environmental");
		welcomeTitle1.setBounds(50, 37, 427, 76);

		JLabel welcomeTitle2 = new JLabel();
		welcomeTitle2.setFont(new Font("Apple LiGothic", Font.PLAIN, 56));
		welcomeTitle2.setForeground(Color.WHITE);
		welcomeTitle2.setOpaque(false);
		welcomeTitle2.setText("Footprint Calculator");
		welcomeTitle2.setBounds(50, 96, 427, 57);

		this.getContentPane().add(welcomeTitle1);
		this.getContentPane().add(welcomeTitle2);

		JPanel separator = new JPanel();
		separator.setBackground(Color.WHITE);
		separator.setOpaque(true);
		separator.setBounds(50, 165, 400, 1);
		this.getContentPane().add(separator);

		// Chewpaca's speech bubble
		JLabel introChewpaca = new JLabel(
				"<html><i>HEE-HAW!</i> I'm Chewpaca, the friendliest and greenest alpaca in the world! Tell me about yourself to get started.</html>");
		introChewpaca.setHorizontalAlignment(SwingConstants.CENTER);
		introChewpaca.setFont(new Font("Hiragino Sans GB", Font.BOLD, 20));
		introChewpaca.setBounds(65, 200, 361, 149);
		this.getContentPane().add(introChewpaca);

		// Input fields for user's name and email address
		JTextField txtUserName = new JTextField("Name");
		txtUserName.setFont(new Font("Hiragino Sans GB", Font.PLAIN, 16));
		txtUserName.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.WHITE, 1),
				BorderFactory.createEmptyBorder(5, 5, 5, 5)));
		txtUserName.setBounds(123, 383, 255, 26);
		this.getContentPane().add(txtUserName);

		JTextField txtUserEmail = new JTextField("Email");
		txtUserEmail.setFont(new Font("Hiragino Sans GB", Font.PLAIN, 16));
		txtUserEmail.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.WHITE, 1),
				BorderFactory.createEmptyBorder(5, 5, 5, 5)));
		txtUserEmail.setBounds(123, 418, 255, 26);
		this.getContentPane().add(txtUserEmail);
	

		// Button to move to next frame, if users input information correctly
		JButton letsGo = new JButton("Let's go!");
		letsGo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Start second frame
				if (!txtUserName.getText().equals("Name")&&!txtUserName.getText().equals("")&&!txtUserEmail.getText().equals("Email")&&!txtUserEmail.getText().equals("")){
					Point position = getLocation();
					FoodSelectionFrame frame2 = new FoodSelectionFrame(position);
					frame2.setVisible(true);
					frame2.setUserName(txtUserName.getText());
					frame2.setUserEmail(txtUserEmail.getText());
					
					dispose();
				} else {
					JOptionPane.showMessageDialog(null, "Don't be shy, tell me your name and email!");
				}
			}
				
		});
		letsGo.setBackground(new Color(255, 182, 193));
		letsGo.setFont(new Font("Apple LiGothic", Font.PLAIN, 30));
		letsGo.setBounds(199, 456, 117, 61);
		getContentPane().add(letsGo);

		// Small credits to ourselves
		JLabel teamName1 = new JLabel(
				"<html>(Brought to you by the really environmentally-friendly people of CIT591 Fall 2019 Team 50)</html>");
		teamName1.setFont(new Font("Hiragino Sans GB", Font.BOLD, 12));
		teamName1.setForeground(Color.WHITE);
		teamName1.setBounds(580, 62, 200, 100);
		this.getContentPane().add(teamName1);

		// Welcome background of main window, showing Chewpaca the Alpaca's illustration
		JLabel welcomeBackground = new JLabel("");
		welcomeBackground.setBackground(Color.WHITE);
		welcomeBackground.setForeground(Color.DARK_GRAY);
		welcomeBackground.setIcon(new ImageIcon(resizedImage));
		welcomeBackground.setBounds(0, 0, 800, 578);
		this.getContentPane().add(welcomeBackground);


		
	}

}
