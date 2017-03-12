package ahg;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Document;
import javax.swing.text.Highlighter;
import javax.swing.text.JTextComponent;
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
import java.io.File;
import java.util.Arrays;
import java.util.Scanner;
import java.io.FileInputStream;
import java.io.IOException;

public class pro6 extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					pro6 frame = new pro6();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	class MyHighlightPainter extends DefaultHighlighter.DefaultHighlightPainter
	{
		public MyHighlightPainter(Color color)
		{
			super(color); 
		}
			}
	Highlighter.HighlightPainter myHighlightPaniter=new MyHighlightPainter(Color.red);

	public void highligh(JTextComponent textComp,String pattern) throws BadLocationException
	{
		//String text =new String();
		 
	try
	{
		Highlighter hilite=textComp.getHighlighter();
		Document doc=textComp.getDocument();
	String	text=doc.getText(0,doc.getLength());
	
		
	int pos=0;
	while((pos=text.toUpperCase().indexOf(pattern.toUpperCase(),pos))>=0)
	{
	hilite.addHighlight(pos,pos+pattern.length(),myHighlightPaniter);
	pos+=pattern.length();
	
	
	}}
	catch(Exception e){}
	
	}
	


	/**
	 * Create the frame.
	 */
	public pro6() {
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
		
		
		scroll.setBounds(32, 282, 565, 260);
		contentPane.add(scroll);

		
		
		
		JButton btnNewButton = new JButton("");
		
		btnNewButton.addActionListener(new ActionListener() {
			String s4;
			public void actionPerformed(ActionEvent e) {
				
				//s4=textField.getText();
				try {
					highligh(textArea,textField.getText());
				} catch (BadLocationException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
				
				
					Search DC12;
				DC12= new Search();
				int [] pp=new int[10];
			DC12.Search1(s4);
			//int i;
			/*try
			 {
				 FileReader frw = new FileReader("D:\\ab.txt");
				 BufferedReader brw = new BufferedReader(frw);
				textArea.read(brw,null);
				 
				 
				 brw.close();
				 textArea.requestFocus();
			 }
			 catch(Exception e1)
			 {
				 e1.printStackTrace();
				// JOptionStack.
			 }*/
		//	int[]data=readFiles("D:\\ab.txt");
			
			File file1 = new File("D:\\ab.txt");
		     Scanner inputFile = null;
		     try {
		        inputFile = new Scanner(file1);
		     } 
		     //If file not found-error message
		        catch (IOException e3) {
		           System.out.println("File not found!");
		        }
		     
		    int i=0;
		    int[]array=new int[100];
		     
		     if (inputFile != null) {
		    	  //System.out.print("number of integers in file \""
		    	   //   + filename + "\" = \n");
		    	  // loop through file for integers and store in array
		    	  try {
		    	    while (inputFile.hasNext()) {
		    	      if (inputFile.hasNextInt()) {
		    	        array[i] = inputFile.nextInt();
		    	        i++;
		    	      } else {
		    	        inputFile.next();
		    	      }
		    	    }
		    	  } finally {
		    	    inputFile.close();
		    	  }
		    	  for (int v = 0; v < i; v++) {
		    		    System.out.printf("array[%d] = %d\n", v, array[v]);
		    		  }
		     }
		     
			
			
			int k=0;
			int l=0;
			try {
				 File file = new File("D:\\m.txt");
			      FileInputStream fis = new FileInputStream(file);
			      int current;
			      while (fis.available() > 0) {
			    	 
			        current = (char) fis.read();
			      //  String s2=String.valueOf(current);
			        if(k!=array[l])
			        {
			        	textArea.setText(Integer.toString(current));
			        }
			      }
			    } catch (IOException e2) {
			      e2.printStackTrace();
			    }
				
			
				
			}
		});
		btnNewButton.setIcon(new ImageIcon("C:\\Users\\sejal\\Desktop\\arrow.jpg"));
		btnNewButton.setBounds(542, 182, 63, 69);
		contentPane.add(btnNewButton);
		
		
		
		
				
				
				
				//s1=textArea.getText();
				try
				 {
					 FileReader frw = new FileReader("D:\\cc.txt");
					 BufferedReader brw = new BufferedReader(frw);
					// textArea.read(brw,null);
					 
						 
						// brw.write(s1); //It will write original sentence and cleaned sentence with a seperator 
						// brw.newLine();
					 
					 brw.close();
					 //textArea.requestFocus();
				 }
				 catch(Exception e)
				 {
					 e.printStackTrace();
					// JOptionStack.
				 }
		
		/*textField_1 = new JTextField();
		textField_1.setForeground(Color.WHITE);
		textField_1.setBackground(Color.BLACK);
		textField_1.setBounds(32, 282, 565, 260);
		contentPane.add(textField_1);
		textField_1.setColumns(10);*/
		
		textField = new JTextField();
		textField.setForeground(Color.WHITE);
		textField.setBackground(new Color(0, 0, 0));
		textField.setBounds(78, 182, 454, 66);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblEnterWord = new JLabel("ENTER:-");
		lblEnterWord.setForeground(new Color(218, 165, 32));
		lblEnterWord.setFont(new Font("DejaVu Sans Condensed", Font.BOLD | Font.ITALIC, 17));
		lblEnterWord.setBounds(0, 193, 199, 30);
		contentPane.add(lblEnterWord);
		
		JLabel lblNewLabel_3 = new JLabel("New label");
		lblNewLabel_3.setIcon(new ImageIcon("C:\\Users\\sejal\\Desktop\\pp5.png"));
		lblNewLabel_3.setBounds(-12, 168, 565, 85);
		contentPane.add(lblNewLabel_3);
				
			
			
		
		
		
		
		JLabel lblNewLabel_2 = new JLabel("New label");
		lblNewLabel_2.setBackground(Color.BLACK);
		lblNewLabel_2.setFont(new Font("Arial Rounded MT Bold", Font.BOLD | Font.ITALIC, 11));
		lblNewLabel_2.setIcon(new ImageIcon("C:\\Users\\sejal\\Desktop\\pp3.png"));
		lblNewLabel_2.setBounds(0, 260, 631, 293);
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
