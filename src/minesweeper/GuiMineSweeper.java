package minesweeper;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class GuiMineSweeper extends JFrame implements ActionListener{

	private JPanel contentPane;
	ArrayList<JButton> btnArr = new ArrayList<JButton>();
	private static JPanel displayPanel;
	private static Board myBoard;
	private JPanel displayPanel2;

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
		setBounds(100, 100, 550, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		myBoard = new Board(Difficulty.EASY);
		
		System.out.println(myBoard);
		myBoard.plantBombs();
		myBoard.buildBoard();
		
		JLabel lblMinesweeper = createLblTitle();
		contentPane.add(lblMinesweeper, BorderLayout.NORTH);
		
		JPanel controlPanel = createControlPanel();
		contentPane.add(controlPanel, BorderLayout.SOUTH);
		
		displayPanel = createDisplayPanel();
		contentPane.add(displayPanel, BorderLayout.CENTER);
	}
	


	private JPanel createDisplayPanel() {
		displayPanel2 = new JPanel();
		displayPanel2.setBorder(new EmptyBorder(5, 5, 5, 5));
		displayPanel2.setLayout(new GridLayout(10, 10, 0, 0)); //grid size
		setupBoardDisplay(displayPanel2);
		
		//Create 100 buttons

//		{
//			 //Create 100 buttons
//
//				for (int i = 0; i < 100; i++) {
//					btnArr.add(new JButton(""));			
//				}
//				
//				for (int j = 0; j < btnArr.size(); j++) {
//					JButton b = btnArr.get(j); 
//					b.setName("btn" + j); //names the button
//					b.addActionListener(this);
//					displayPanel.add(b);	
//						
//				}
//		}
		return displayPanel2;
	}
	
	private JPanel setupBoardDisplay(JPanel whatever) {
		for (int j = 0; j < myBoard.mineArr.size(); j++) {
			Mine mine = myBoard.mineArr.get(j);
			System.out.println(mine);
			whatever.add(mine);
			if(mine.isBomb) {
				mine.setText("X");
				mine.setBackground(Color.RED);
				mine.setOpaque(true);
				mine.setBorderPainted(false);
			} else if(mine.getBombTouchCount() > 0) {
				mine.setText("" + mine.bombTouchCount);
			} 
			mine.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					mine.setOpen(true);
					System.out.println(mine + " " + mine.getId()%10);
				}
			});
		}
		return whatever;
	}
	
	

	private JPanel createControlPanel() {
		JPanel controlPanel = new JPanel();
		
		JButton btnResetGame = new JButton("Reset Game");
		btnResetGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("The game is reset"); //CHANGE for reseting the game
			}
		});
	
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
