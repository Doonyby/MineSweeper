package minesweeper;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

//implements ActionListener
public class GameUi extends JFrame implements ActionListener{

	private JPanel contentPane;
	ArrayList<JButton> btnArr = new ArrayList<JButton>();
	JButton btn;
	
	//Launch the application
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GameUi frame = new GameUi();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	//Create the frame
	public GameUi() {
		setTitle("Minesweeper");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(10, 10, 0, 0)); //grid size
		
		
	//Create spacing at the top for additional game functions
		JLabel lblTimerReset = new JLabel("Timer/Reset");
		contentPane.add(lblTimerReset);
	
		
	 //Create 100 buttons

		for (int i = 0; i < 100; i++) {
			btnArr.add(new JButton(""));			
		}
		
		for (int j = 0; j < btnArr.size(); j++) {
			JButton b = btnArr.get(j); 
			b.setName("btn" + j); //names the button
			b.addActionListener(this);
			contentPane.add(b);	
				
		}
			
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("The button has been clicked.");
	}

}
