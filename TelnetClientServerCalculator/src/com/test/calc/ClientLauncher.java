package com.test.calc;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import org.apache.commons.net.telnet.TelnetClient;

public class ClientLauncher extends JFrame implements ActionListener {

	
	JTextField textField;
	JTextArea textArea;
	JFrame preFrame;
	JPanel mainPanel;
	JPanel southPanel;
	PrintWriter inWriter;

	public ClientLauncher() {
		
		super("Telnet Client");
		mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());

		southPanel = new JPanel();
		southPanel.setLayout(new GridBagLayout());

		textField = new JTextField(30);
		textField.requestFocusInWindow();
		textField.addActionListener(this);
			
		textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setFont(new Font("Serif", Font.PLAIN, 15));
		textArea.setLineWrap(true);
			
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

		this.add(mainPanel);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(470, 300);
		this.setVisible(true);
		
		redirectIO(textArea); 
		connectTelnet();
	}

	public void connectTelnet() {
		
		TelnetClient telnet = new TelnetClient();

		try {
			telnet.connect("localhost", 23);
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}

		IOUtil.readWrite(telnet.getInputStream(), telnet.getOutputStream(), System.in, System.out);

		try {
			telnet.disconnect();
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}

		System.exit(0);
	}

	public void redirectIO(JTextArea text) {
		
		PrintStream textAreaOut = new PrintStream(new TextAreaOutStream(text));
		PipedInputStream inPipe = new PipedInputStream();
		
		System.setOut(textAreaOut);
		System.setIn(inPipe);
		try {
			inWriter = new PrintWriter(new PipedOutputStream(inPipe), true);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		String msq = textField.getText();
		textArea.append(msq + "\n");
		textField.setText("");
		inWriter.println(msq); 
		
	}
	
	public static void main(String[] args) {			
		
		ClientLauncher clientView = new ClientLauncher();		
		
		/*java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ClientLauncher();
            }
        });  */
   }
}



