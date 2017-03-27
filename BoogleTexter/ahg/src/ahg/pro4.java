package ahg;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.FileReader;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;

public class pro4 extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					pro4 frame = new pro4();
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
	public pro4() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(600, 0, 631, 592);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		JTextArea textArea = new JTextArea();
		textArea.setFont(new Font("Arial Black", Font.BOLD | Font.ITALIC, 15));
		textArea.setForeground(Color.WHITE);
		textArea.setBackground(Color.BLACK);
		JScrollPane scroll=new JScrollPane(textArea);
		scroll.setEnabled(false);
		
		
		scroll.setBounds(30,150, 570, 258);
		contentPane.add(scroll);

		
		
		
				
				
				
				//s1=textArea.getText();
				try
				 {
					 FileReader frw = new FileReader("D:\\cc.txt");
					 BufferedReader brw = new BufferedReader(frw);
					 textArea.read(brw,null);
					 
						 
						// brw.write(s1); //It will write original sentence and cleaned sentence with a seperator 
						// brw.newLine();
					 
					 brw.close();
					 textArea.requestFocus();
				 }
				 catch(Exception e)
				 {
					 e.printStackTrace();
					// JOptionStack.
				 }
				
			
			
		
		
		
		
		JLabel lblNewLabel_2 = new JLabel("New label");
		lblNewLabel_2.setIcon(new ImageIcon("C:\\Users\\sejal\\Desktop\\pp3.png"));
		lblNewLabel_2.setBounds(0, 99, 631, 346);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setForeground(Color.PINK);
		lblNewLabel_1.setBackground(Color.BLACK);
		lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\sejal\\Desktop\\2.jpg"));
		lblNewLabel_1.setBounds(0, -21, 631, 674);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\sejal\\Desktop\\coollogo_com-219924176.png"));
		lblNewLabel.setBounds(0, 0, 631, 85);
		contentPane.add(lblNewLabel);
	}
}
