package com.test.calc;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Logger;

public class ServerLauncher {
	
	private final Logger logger = Logger.getLogger(ServerLauncher.class.getName());
    private final ExecutorService executorService = Executors.newFixedThreadPool(1);
    private ServerSocket serverSocket;
    
    public ServerLauncher(int port){
    	
        try {
            serverSocket = new ServerSocket(port);
            logger.info("Server running and listening port : " + serverSocket.getLocalPort());
            
            while (true) {
                Socket socket = serverSocket.accept();
                executorService.execute(new ClientHandler(socket));
            }
        } catch (IOException e) {
        }
    }
    
    public static void main(String[] args) {
        
        if(args.length == 0){
            new ServerLauncher(Integer.parseInt("23"));
        }
        else{
            new ServerLauncher(Integer.parseInt(args[0]));    
        }
        
    }
}
