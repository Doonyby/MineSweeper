package minesweeper;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import java.awt.Color;

@SuppressWarnings("serial")
public class ButtonPanel extends JPanel {

	/**
	 * Create the panel that displays a bomb image
	 */
	public ButtonPanel() {
		setLayout(new BorderLayout(0, 0));
		
		JLabel lblBomb = new JLabel("Bomb");
		lblBomb.setOpaque(true);
		lblBomb.setBackground(Color.WHITE);
		lblBomb.setIcon(new ImageIcon(ButtonPanel.class.getResource("/minesweeper/Images/bomb.png")));
		lblBomb.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblBomb, BorderLayout.CENTER);

	}

}
