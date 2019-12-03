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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
		setupBoardDisplay(displayPanel2);
		
		return displayPanel2;
	}
	
	private JPanel setupBoardDisplay(JPanel whatever) {
		displayPanel2.setBorder(new EmptyBorder(5, 5, 5, 5));
		displayPanel2.setLayout(new GridLayout(10, 10, 0, 0)); //grid size
		
		//Create 100 buttons
		for (int j = 0; j < myBoard.mineArr.size(); j++) {
			Mine mine = myBoard.mineArr.get(j);
			System.out.println(mine);
			whatever.add(mine);
			//add pre-click styles here
			
			mine.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					if(e.getButton() == 3) {
						mine.setFlagged(!mine.isFlagged());
						flagMine(mine);
					} else {
						myBoard.onBoardChange(mine);
						checkBoardUiChanges();
					}
					evaluateMineUi(mine);
				}
			});
		}
		return whatever;
	}
	
	private void flagMine(Mine mine) {
		if(mine.isFlagged) {
			mine.setText("F");
			mine.setBackground(Color.YELLOW);
			mine.setOpaque(true);
			mine.setBorderPainted(false);
		} else {
			mine.removeAll();
		}
	}
	
	private void checkBoardUiChanges() {
		for (int j = 0; j < myBoard.mineArr.size(); j++) {
			System.out.println(myBoard.mineArr.get(j));
			evaluateMineUi(myBoard.mineArr.get(j));
		} 
	}
	
	//add post-click styles here
	private Mine evaluateMineUi(Mine mine) {
		if(mine.isFlagged()) {
			return mine;
		}
		if(mine.isOpen) {
			if(mine.isBomb) {
				mine.setText("X");
				mine.setBackground(Color.RED);
				mine.setOpaque(true);
				mine.setBorderPainted(false);
			}  else if(mine.getBombTouchCount() > 0) {
				mine.setText("" + mine.bombTouchCount);
				mine.setBackground(Color.WHITE);
				mine.setOpaque(true);
				mine.setBorderPainted(false);
			} else {
				mine.setText("");
				mine.setBackground(Color.WHITE);
				mine.setOpaque(true);
				mine.setBorderPainted(false);
			}
		}
		return mine;
	}

	private JPanel createControlPanel() {
		JPanel controlPanel = new JPanel();
		
		JButton btnResetGame = new JButton("Reset Game");
		btnResetGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("The game is reset"); //CHANGE for reseting the game
				displayPanel2.removeAll(); //Removes all components from panel including layout
				myBoard.resetGame();
				setupBoardDisplay(displayPanel2); //Adds all components back into displayPanel2 from new board array
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
