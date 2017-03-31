package com.test.calc;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.swing.JTextField;

public class TextFieldInStream extends InputStream {

	private JTextField textField;
	
	public TextFieldInStream (JTextField control){
		textField = control;
	}
	
	public int read() throws IOException {
		
		//super.read();
		
		return 0;
	}
	

}
