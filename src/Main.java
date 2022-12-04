package main;
import game.*;
import server.*;
import tcp.TCPClient;
import exception.*;
import java.awt.*;
public class Main{
    public static void main(String[] args) throws Exception{
        TCPClient.resetUser();
        Server server = new Server(8888);
        server.startServer();
        // Game game = new Game();
    }
}
