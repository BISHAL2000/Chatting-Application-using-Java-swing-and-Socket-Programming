/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatting;

/**
 *
 * @author BISHAL RAJAK
 */
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Font;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class ServerEnd {

	public JFrame frame;
	public JTextField textField;
	public static   JTextArea textArea;
	public JButton button;
	static ServerSocket server ;
	static Socket con;
	private JScrollPane scrollPane;
        
	private static JLabel label2;
	private static JLabel label1;
        private static JLabel label3;
        
	
	public static void main(String[] args) throws IOException   {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ServerEnd window = new ServerEnd();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	
		 server = new ServerSocket(9748);
		
		 con = server.accept();
		 label2.setText("you are now connected...");
		 label2.setForeground(new Color(0, 0, 128));
		 while (true) {
			try {
				
				DataInputStream input = new DataInputStream(con.getInputStream());
				String string = input.readUTF();
				textArea.setText(textArea.getText() + "\n" + "Client: " + string);
			} catch (Exception ev) {
				 textArea.setText(textArea.getText()+" \n" +"Network issues ");
				 
					try {
						Thread.sleep(2000);
						System.exit(0);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			}

		}
	
	
	}

	

	ServerEnd() throws IOException {
		frame = new JFrame();
		frame.setTitle("Bishal project: Server ");
		frame.setBounds(100, 100, 605, 403);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		textField = new JTextField();
		textField.setFont(new Font("Arial", Font.PLAIN, 24));
		textField.setForeground(new Color(255, 255, 255));
		textField.setBackground(Color.DARK_GRAY);
		textField.setBounds(12, 67, 344, 38);
		frame.getContentPane().add(textField);
		
		
		button = new JButton("Send");
		
		button.addActionListener(new ActionListener() {
			 
			public void actionPerformed(ActionEvent e) {
				
				if (textField.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "Please write some text !");
			 
				}
				else{
					
					textArea.setText(textArea.getText() + "\n" + "Server : " + textField.getText());
					try {
						DataOutputStream output = new DataOutputStream(con.getOutputStream());
						output.writeUTF(textField.getText());
					} catch (IOException e1) {
						textArea.setText(textArea.getText() + "\n " + " Network issues");
						try {
							Thread.sleep(2000);
							System.exit(0);
						} catch (InterruptedException e2) {
							
							e2.printStackTrace();
						}

					}
					textField.setText("");
				}
			}
		});
		button.setFont(new Font("Arial", Font.BOLD, 24));
		button.setForeground(new Color(255, 255, 255));
		button.setBackground(Color.BLUE);
		button.setBounds(390, 66, 164, 38);
		frame.getContentPane().add(button);
		 
		 scrollPane = new JScrollPane();
		 scrollPane.setBounds(12, 134, 557, 157);
		 frame.getContentPane().add(scrollPane);
		
		 textArea = new JTextArea();
		 scrollPane.setViewportView(textArea);
		textArea.setFont(new Font("Arial", Font.BOLD, 24));
		textArea.setForeground(Color.ORANGE);
		textArea.setBackground(Color.DARK_GRAY);
		
		label1 = new JLabel();
		label1.setFont(new Font("Dialog", Font.BOLD, 16));
		
		label1.setBounds(154, 13, 242, 33);
		frame.getContentPane().add(label1);
                label1.setText("Waiting for connection");
		label1.setForeground(Color.GREEN);
		 
		label3 = new JLabel("Status");
		label3.setFont(new Font("Arial", Font.BOLD, 22));
		label3.setBounds(37, 12, 95, 30);
		frame.getContentPane().add(label3);
		
		label2 = new JLabel();
		label2.setFont(new Font("Arial", Font.BOLD, 16));
		label2.setBounds(22, 303, 200, 30);
		frame.getContentPane().add(label2);	 
	}	
}



