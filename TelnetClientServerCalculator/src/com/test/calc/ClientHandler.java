package com.test.calc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import java.net.Socket;

import java.util.logging.Level;
import java.util.logging.Logger;


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
            
            // display welcome screen
            out.println("You are connected to Server");
            out.flush();
            
            boolean cancel = false;
            while(!cancel){
                final String command = reader.readLine();
                out.println(CalculatorImpl.calculate(command));
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
}
