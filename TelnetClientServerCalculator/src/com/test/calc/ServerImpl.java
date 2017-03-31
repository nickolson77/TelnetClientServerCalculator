package com.test.calc;

import java.io.IOException;

import java.net.ServerSocket;
import java.net.Socket;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Logger;


public class ServerImpl {
    
    private final Logger logger = Logger.getLogger(ServerImpl.class.getName());
    private final ExecutorService executorService = Executors.newFixedThreadPool(10);
    private ServerSocket serverSocket;
    
    public ServerImpl(int port){
        try {
            serverSocket = new ServerSocket(port);
            logger.info("Server running and listening on port : " + serverSocket.getLocalPort());
            
            while (true) {
                Socket socket = serverSocket.accept();
                executorService.execute(new ClientHandler(socket));
                System.out.println(ServerImpl.class.getName());
            }
        } catch (IOException e) {
        }
    }
    
}
