package minesweeper;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.Timer;

import java.awt.Font;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JToggleButton;

@SuppressWarnings("serial")
public class GuiMineSweeper extends JFrame implements ActionListener{

	private JPanel contentPane;
	ArrayList<JButton> btnArr = new ArrayList<JButton>();
	private static JPanel displayPanel;
	private static Board myBoard;
	private JPanel displayPanel2;
	private Timer time;
	private JTextField Clock;
	private JTextField Bombsleft;
	private String mine; //??For Bomb count down

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
		setResizable(false);

		
	/**
	 * Created the menu bar and add menu items with Windowbuilder
	 */
		{
			JMenuBar menuBar = new JMenuBar();
			setJMenuBar(menuBar);
			{
				JMenu mnGame = new JMenu("Game");
				menuBar.add(mnGame);
				{
					JMenuItem mntmResetGame = new JMenuItem("Reset Game");
					mnGame.add(mntmResetGame);
				}
			}
			{
				JMenu mnDifficulty = new JMenu("Difficulty");
				menuBar.add(mnDifficulty);
				{
					JMenuItem mntmEasy = new JMenuItem("Easy");
					mnDifficulty.add(mntmEasy);
				}
				{
					JMenuItem mntmMedium = new JMenuItem("Medium");
					mnDifficulty.add(mntmMedium);
				}
				{
					JMenuItem mntmHard = new JMenuItem("Hard");
					mnDifficulty.add(mntmHard);
				}
			}
		}
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		myBoard = new Board(Difficulty.EASY);
		
		System.out.println(myBoard);
		myBoard.buildBoard();
		
		JLabel lblMinesweeper = createTitle();
		contentPane.add(lblMinesweeper, BorderLayout.NORTH);
		
		JPanel controlPanel = createControlPanel();
		contentPane.add(controlPanel, BorderLayout.SOUTH);
		
		displayPanel = createDisplayPanel();
		contentPane.add(displayPanel, BorderLayout.CENTER);
	}
	
	/**
	 * Create grid of buttons/bombs etc
	 * @param whatever
	 * @return
	 */
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
					} else {
						myBoard.onBoardChange(mine);
						checkBoardUiChanges();
					}
				}
			});
		}
		return whatever;
	}
	
	/**
	 * Evaluate the board/setup the game play
	 * @param mine
	 * @return
	 */
	//add post-click styles here
	private Mine evaluateMineUi(Mine mine) {
		if(mine.isOpen) {
			if (mine.isFlagged()) {
				//mine.setText("F");
				//mine.setBackground(Color.YELLOW);
				mine.setIcon(new ImageIcon(this.getClass().getResource("/Images/redflag.png")));
				mine.setOpaque(true);
				mine.setBorderPainted(false);
			} else if(mine.isBomb) {
				//mine.setText("X");
				mine.setBackground(Color.RED);
				mine.setIcon(new ImageIcon(this.getClass().getResource("/Images/bomb.png")));
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
	
	private void checkBoardUiChanges() {
		for (int j=0; j < myBoard.mineArr.size(); j++) {
			evaluateMineUi(myBoard.mineArr.get(j));
		} 
	}

	private JLabel createTitle() {
		JLabel gameTitle = new JLabel("Minesweeper");
		gameTitle.setFont(new Font("Tahoma", Font.PLAIN, 20));
		gameTitle.setHorizontalAlignment(SwingConstants.CENTER);
		return gameTitle;
	}
	
	private JPanel createDisplayPanel() {
		displayPanel2 = new JPanel();
		setupBoardDisplay(displayPanel2);
		
		return displayPanel2;
	}

	private JPanel createControlPanel() {
		JPanel controlPanel = new JPanel();
		
		/**
		 * Create Reset Button
		 */
		JButton btnResetGame = new JButton("Reset Game");
		btnResetGame.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnResetGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("The game is reset"); //CHANGE for reseting the game
				displayPanel2.removeAll(); //Removes all components from panel including layout
				myBoard.resetGame();
				setupBoardDisplay(displayPanel2); //Adds all components back into displayPanel2 from new board array
			}
		});
		{
			/**
			 * Create a Timer
			 * @return
			 */
			time = new Timer(1000, new TimerListener());
			
			Clock = new JTextField("" + 0);
			Clock.setHorizontalAlignment(SwingConstants.CENTER);
			Clock.setEditable(false);
			Clock.setFont(new Font("Tahoma", Font.BOLD, 19));
			Clock.setForeground(Color.RED);
			Clock.setBackground(Color.DARK_GRAY);
			controlPanel.add(Clock);
			Clock.setColumns(3);
			
			time.start();
		}

		controlPanel.add(btnResetGame);
		{
			/**
			 * Create a bomb count
			 */
			Bombsleft = new JTextField("" + mine);
			Bombsleft.setHorizontalAlignment(SwingConstants.CENTER);
			Bombsleft.setFont(new Font("Tahoma", Font.BOLD, 19));
			Bombsleft.setEditable(false);
			Bombsleft.setBackground(Color.DARK_GRAY);
			Bombsleft.setForeground(Color.RED);
			Bombsleft.setText("bombs");
			controlPanel.add(Bombsleft);
			Bombsleft.setColumns(3);
		}
		return controlPanel;
	}
	
	

	private class TimerListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			if (Clock.getText().compareTo("999") < 0)
				Clock.setText((Integer.parseInt(Clock.getText()) + 1) + "");
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("The button has been clicked.");
		
	}

}
