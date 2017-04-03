package com.test.calc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import java.net.Socket;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.mariuszgromada.math.mxparser.Expression;


public class ClientHandler implements Runnable {
    
    private final Socket socket;
    private final Logger logger = Logger.getLogger(ClientHandler.class.getName());
    
    public ClientHandler(final Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try {
            final BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            final PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            
            out.println("You are connected to Math Telnet Server.");
            out.println("Type math expression and press enter, e. g. 2+2*5");
            
            while(true){
                final String command = reader.readLine();
                if (command.equalsIgnoreCase("exit")) {
					break;
				}
                else{
                	out.println(calculate(command));
                }
             }
            
        } catch (IOException e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                logger.log(Level.SEVERE, "Failed to close the socket", e);

            }
        }
    }
    
    public static String calculate(String expression){
        
    	Expression exp = new Expression (expression);
		if(!exp.checkSyntax()){
		     return exp.getErrorMessage();
		 }
		 else{
		     return "" + exp.calculate();
		 }
        
     }
}
