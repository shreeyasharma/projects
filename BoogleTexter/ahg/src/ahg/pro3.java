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

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;

public class pro3 extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					pro3 frame = new pro3();
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
	public pro3() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 631, 592);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		JTextArea textArea = new JTextArea();
		textArea.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 15));
		textArea.setForeground(Color.WHITE);
		textArea.setBackground(Color.BLACK);
		JScrollPane scroll=new JScrollPane(textArea);
		scroll.setEnabled(false);
		
		
		scroll.setBounds(30,150, 570, 258);
		contentPane.add(scroll);

		
		JButton btnNewButton_2 = new JButton("REPLACE");
		btnNewButton_2.setBounds(459, 456, 146, 35);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_1 = new JButton("SUMMARY");
		btnNewButton_1.addActionListener(new ActionListener() {
			String s2;
			public void actionPerformed(ActionEvent arg0) {
				
				s2=textArea.getText();
				try
				 {
					 FileWriter frw = new FileWriter("D:\\m.txt");
					 BufferedWriter brw = new BufferedWriter(frw);
					 
						 
						 brw.write(s2); //It will write original sentence and cleaned sentence with a seperator 
						// brw.newLine();
					 
					 brw.close();
				 }
				 catch(Exception e)
				 {
					 e.printStackTrace();
				 }
				BTexter DC12;
				DC12= new BTexter();
				DC12.summarizer();
				
				
				
				//contentPane.setVisible(false);
		        pro4 sc=new pro4();
		        sc.setVisible(true);
		        pro5 sc1=new pro5();
		        sc1.setVisible(true);
		        
			
			}
		});
		btnNewButton_1.setBounds(251, 456, 157, 35);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton = new JButton("SEARCH");
		
		btnNewButton.addActionListener(new ActionListener() {
			String s1;
			int j;
			
			public void actionPerformed(ActionEvent arg0) {
				
				
				
				s1=textArea.getText();
				try
				 {
					 FileWriter frw = new FileWriter("D:\\m.txt");
					 BufferedWriter brw = new BufferedWriter(frw);
					 
						 
						 brw.write(s1); //It will write original sentence and cleaned sentence with a seperator 
						// brw.newLine();
					 
					 brw.close();
				 }
				 catch(Exception e)
				 {
					 e.printStackTrace();
				 }
			/*	Search DC12;
				DC12= new Search();
				int i;
				i=DC12.Search1();
				*/
				
				
				//contentPane.setVisible(false);
		        pro6 sc=new pro6();
		        sc.setVisible(true);
		       
		        
			}
			
		});
		
		btnNewButton.setBounds(47, 456, 144, 35);
		contentPane.add(btnNewButton);
		
		
		
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
