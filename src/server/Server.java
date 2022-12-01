package server;

import java.net.ServerSocket;
import java.net.Socket;

public class Server extends Thread{
    ServerSocket serverSocket;
    public Server() throws Exception{
        serverSocket = new ServerSocket(6969);
    }

    @Override
    public void run() {
        while(true){
            try{}
            catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
