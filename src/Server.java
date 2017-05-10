import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Server extends JFrame{
	Socket client;
	PrintWriter out;
	JLabel jLabel;
	JTextField textField;
	JButton jButton;
	 public Server() {
		// TODO Auto-generated constructor stub
		
		 
		 
	    this.setTitle("服务端");
	    this.setSize(200, 200);
	    this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		FlowLayout flowLayout=new FlowLayout();
		this.setLayout(flowLayout);
		
		jLabel=new JLabel("接收到的信息");
		
		jLabel.setSize(200,100);
		jLabel.setVisible(true);
		textField=new JTextField("输入文字");
		textField.setSize(400,100);
		textField.setPreferredSize(new Dimension(200, 50));
		jButton=new JButton("发送");
		jButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String text=textField.getText();
				textField.setText("");
				
					out.println(text);
				
		            out.flush();  
				System.out.println(text);
				
			}
		});
		
		
		
		this.add(jLabel);
		this.add(textField);
		this.add(jButton);
		this.setVisible(true);
		
		 
		 try {
			   ServerSocket serverSocket=new ServerSocket(9898);
				
				client = serverSocket.accept();  
		        final BufferedReader in = new BufferedReader(new InputStreamReader(  
		                client.getInputStream()));  
		        out = new PrintWriter(client.getOutputStream()); 
		        
		        new Thread(new Runnable() {
					
					@Override
					public void run() {
						while (true) {
							try {
								jLabel.setText(in.readLine());
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						
					}
				}).start();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		 
	}
	
	public static void main(String[] args) {
		new Server();
		
	}

}
