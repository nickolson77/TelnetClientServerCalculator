package com.test.calc;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class ClientLauncher {

	String appName = "Simple Telnet Client";
	JFrame frame = new JFrame(appName);
	JTextField textField;
	JTextArea textArea;
	JFrame preFrame;

	public void display() {
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());

		JPanel southPanel = new JPanel();
		southPanel.setLayout(new GridBagLayout());

		textField = new JTextField(30);
		textField.requestFocusInWindow();
		textField.addActionListener(new TextFieldListener());
		
		//System.setIn(in);
		

		textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setFont(new Font("Serif", Font.PLAIN, 15));
		textArea.setLineWrap(true);
		
		
		
		//redirect System.out to textArea
		PrintStream out = new PrintStream(new TextAreaOutStream(textArea));
		System.setOut( out );
		//System.setErr( out );

	
		mainPanel.add(new JScrollPane(textArea), BorderLayout.CENTER);

		GridBagConstraints left = new GridBagConstraints();
		left.anchor = GridBagConstraints.LINE_START;
		left.fill = GridBagConstraints.HORIZONTAL;
		left.weightx = 512.0D;
		left.weighty = 1.0D;

		GridBagConstraints right = new GridBagConstraints();
		right.insets = new Insets(0, 10, 0, 0);
		right.anchor = GridBagConstraints.LINE_END;
		right.fill = GridBagConstraints.NONE;
		right.weightx = 1.0D;
		right.weighty = 1.0D;

		southPanel.add(textField, left);

		mainPanel.add(BorderLayout.SOUTH, southPanel);

		frame.add(mainPanel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(470, 300);
		frame.setVisible(true);
	}

	class TextFieldListener implements ActionListener {
		
		public void actionPerformed(ActionEvent event) {
			
			if (textField.getText().length() > 1) {
				
				textArea.append(textField.getText() + "\n");
				textField.setText("");
				textField.requestFocusInWindow();
				/*try {
					InputStream textFieldInStream = new ByteArrayInputStream(textField.getText().getBytes("UTF-8"));
					System.setIn(textFieldInStream);
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}*/
			}
		}
	}
	
	public static void main(String[] args) {
			
		ClientLauncher clientView = new ClientLauncher();
		clientView.display();
		
		new TelnetClientImpl("localhost", 23);
			
		}

}
