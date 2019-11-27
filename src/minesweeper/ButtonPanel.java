package minesweeper;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import java.awt.Color;

@SuppressWarnings("serial")
public class ButtonPanel extends JPanel {

	/**
	 * Create the panel that displays a bomb image
	 */
	public ButtonPanel() {
		setLayout(new BorderLayout(0, 0));
		
		JLabel lblButton = new JLabel("");
		lblButton.setOpaque(true);
		lblButton.setBackground(Color.LIGHT_GRAY);
		lblButton.setIcon(new ImageIcon(ButtonPanel.class.getResource("/minesweeper/Images/flag.jpg")));
		lblButton.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblButton, BorderLayout.CENTER);

	}

	/*
	 * private Icon getButtonIcon() { String imagePath;
	 * 
	 * switch(button()) { case clickedBomb: imagePath =
	 * "/minesweeper/Images/bombHit.jpg"; break; case clickedEmpty: imagePath =
	 * "/minesweeper/Images/flag.png"; //CHANGE ME break; case clickedNumber:
	 * imagePath = "/minesweeper/Images/flag.png"; //CHANGE ME break; default:
	 * imagePath = "/minesweeper/Images/flag.png"; break; }
	 * 
	 * return new ImageIcon(ButtonPanel.class.getResource()); }
	 */

}
