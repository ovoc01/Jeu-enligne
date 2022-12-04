package server;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

import client.ServerClientThread;
import exception.ServerBusy;

public class Server {
    ServerSocket serverSocket;
    private int user_connected;
    
    public void setServerSocket(ServerSocket ss){
        serverSocket =ss;
    }

    public ServerSocket getServerSocket(){
        return serverSocket;
    }

    public void updateUserConnected(int count) throws Exception{
        if(count>3){
        }
        user_connected = count;
    }

    public int getUserConnected(){
        return user_connected;
    }

    public Server(int port) throws Exception{
        setServerSocket(new ServerSocket(port));
        
    }    

    /** start the server waiting for a client thread 
     * infinite loop */  
    public void startServer() throws Exception{
        System.out.println("Server Started ....");
        while(true){
            try {
                updateUserConnected(getUserConnected()+1);  //iterate 1 user connected
                Socket new_client = serverSocket.accept();
                System.out.println(" >> " + "Client No:" + getUserConnected() + " started!");
                ServerClientThread new_tThread = new ServerClientThread(new_client, user_connected) ;
                new_tThread.start();     
            }catch(Exception e){
                e.printStackTrace();
                
            }
        }
    }
}