package ahg;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class pro {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					pro window = new pro();
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
	public pro() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 589, 496);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton("START");
		btnNewButton.setForeground(new Color(51, 0, 51));
		btnNewButton.setBackground(new Color(204, 153, 0));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				   frame.dispose();
			        pro2 sc=new pro2();
			        sc.setVisible(true);
			           
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnNewButton.setBounds(243, 374, 114, 34);
		frame.getContentPane().add(btnNewButton);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\sejal\\Downloads\\coollogo_com-60202532.png"));
		lblNewLabel_1.setBounds(78, 32, 433, 134);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBackground(new Color(255, 204, 102));
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\sejal\\Desktop\\2.jpg"));
		lblNewLabel.setBounds(0, 0, 573, 457);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_2 = new JLabel("New label");
		lblNewLabel_2.setIcon(new ImageIcon("C:\\Users\\sejal\\Downloads\\coollogo_com-280492068.png"));
		lblNewLabel_2.setBounds(68, 134, 312, 98);
		frame.getContentPane().add(lblNewLabel_2);
	}
}
