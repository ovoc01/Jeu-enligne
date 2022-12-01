import server.Server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Main2 {
    public static void main(String[] args) throws Exception{
       ServerSocket serverSocket = new ServerSocket(6969);
        System.out.println("connexion en cours");
        while (true){
            Socket socket = serverSocket.accept();
            InputStreamReader inputStreamReader = new InputStreamReader(socket.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String response = bufferedReader.readLine();
            System.out.println(response);
            System.out.println("Utilisateur connecter");
        }
    }
}
