package minesweeper;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class GuiMineSweeper extends JFrame implements ActionListener{

	private JPanel contentPane;
	ArrayList<JButton> btnArr = new ArrayList<JButton>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GuiMineSweeper frame = new GuiMineSweeper();
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
	public GuiMineSweeper() {
		setTitle("Minesweeper");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JLabel lblMinesweeper = createLblTitle();
		contentPane.add(lblMinesweeper, BorderLayout.NORTH);
		
		JPanel controlPanel = createControlPanel();
		contentPane.add(controlPanel, BorderLayout.SOUTH);
		
		JPanel displayPanel = createDisplayPanel();
		contentPane.add(displayPanel, BorderLayout.CENTER);
	}

	private JPanel createDisplayPanel() {
		JPanel displayPanel = new JPanel();
		displayPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		displayPanel.setLayout(new GridLayout(10, 10, 0, 0)); //grid size
		{
			JButton btn = new JButton("");
			displayPanel.add(btn);
			
			 //Create 100 buttons

				for (int i = 0; i < 100; i++) {
					btnArr.add(new JButton(""));			
				}
				
				for (int j = 0; j < btnArr.size(); j++) {
					JButton b = btnArr.get(j); 
					b.setName("btn" + j); //names the button
					b.addActionListener(this);
					displayPanel.add(b);	
						
				}
		}
		return displayPanel;
	}

	private JPanel createControlPanel() {
		JPanel controlPanel = new JPanel();
		
		JButton btnResetGame = new JButton("Reset Game");
		controlPanel.add(btnResetGame);
		return controlPanel;
	}

	private JLabel createLblTitle() {
		JLabel lblMinesweeper = new JLabel("Minesweeper");
		lblMinesweeper.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblMinesweeper.setHorizontalAlignment(SwingConstants.CENTER);
		return lblMinesweeper;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("The button has been clicked.");
		
	}

}
