package com.test.calc;


public class ServerLauncher {
    
    public static void main(String[] args) {
        
        if(args.length == 0){
            new ServerImpl(Integer.parseInt("23"));
        }
        else{
            new ServerImpl(Integer.parseInt(args[0]));    
        }
        
    }
}
