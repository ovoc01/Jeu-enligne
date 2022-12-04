package client;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class ServerClientThread extends Thread {
    Socket serverClient;
    int clientNo;
    int squre;

    public void setCounter(int nbr)throws Exception{
        clientNo = nbr;
    }
    public ServerClientThread(Socket inSocket,int counter) throws Exception{
      serverClient = inSocket;
      setCounter(counter);
    }
    public void run(){
      try{
        DataInputStream inStream = new DataInputStream(serverClient.getInputStream());
        DataOutputStream outStream = new DataOutputStream(serverClient.getOutputStream());
        String clientMessage="", serverMessage="";
        while(!clientMessage.equals("bye")){
          clientMessage=inStream.readUTF();
          System.out.println("From Client-" +clientNo+ ": Number is :"+clientMessage);
          //squre = Integer.parseInt(clientMessage) * Integer.parseInt(clientMessage);
          serverMessage="From Server to Client-" + clientNo + " Hello";
          
          outStream.writeUTF(serverMessage);
          outStream.flush();
        }
        inStream.close();
        outStream.close();
        serverClient.close();
      }catch(Exception ex){
        System.out.println(ex);
      }finally{
        System.out.println("Client -" + clientNo + " exit!! ");
      }
    }
  }