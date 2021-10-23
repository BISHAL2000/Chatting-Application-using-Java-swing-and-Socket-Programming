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
import java.awt.Color;
import java.awt.Font;
import java.awt.EventQueue;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import javax.swing.JScrollPane;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;

public class ClientEnd {

    private JFrame frame;
    private JTextField textField;
    static JTextArea textArea;
    static Socket con;
    DataInputStream input;
    DataOutputStream output;
    static JScrollPane scrollPane;
    public JButton Button;
  
	public static void main(String[] args) throws UnknownHostException, IOException {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClientEnd window = new ClientEnd();
					window.frame.setVisible(true);
				
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		con = new Socket("127.0.0.1", 9748);
		 while (true) {
			try {
				
				DataInputStream input = new DataInputStream(con.getInputStream());
				String string = input.readUTF();
				textArea.setText(textArea.getText() + "\n" + "Server: " + string);
			} catch (Exception ev) {
				 textArea.setText(textArea.getText()+" \n" +"Network issues ");

				 try {
						Thread.sleep(2000);
						System.exit(0);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
			}
			
				

		}
		
	}

	
	ClientEnd() throws IOException{
		frame = new JFrame();
		frame.setBounds(100, 100, 605, 378);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setTitle("Client");
		
		textField = new JTextField();
		textField.setFont(new Font("Arial", Font.PLAIN, 22));
		textField.setForeground(Color.ORANGE);
		textField.setBackground(Color.DARK_GRAY);
		textField.setBounds(12, 45, 344, 38);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		Button = new JButton("Send");
		Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (textField.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Write something to send.");
				}
                                else{
					textArea.setText(textArea.getText() + "\n" + "Client : " + textField.getText());
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
		Button.setFont(new Font("Arial", Font.BOLD, 22));
		Button.setForeground(Color.WHITE);
		Button.setBackground(Color.RED);
		Button.setBounds(398, 45, 164, 38);
		frame.getContentPane().add(Button);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 149, 557, 157);
		frame.getContentPane().add(scrollPane);
		
		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		textArea.setFont(new Font("Arial", Font.PLAIN, 22));
		textArea.setForeground(new Color(255, 255, 255));
		textArea.setBackground(new Color(0, 128, 128));
	}

	
}

