package client;
import java.io.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Vector;

public class ServerClientThread extends Thread {

    static Vector<ServerClientThread> listClient = new Vector<>();
    Socket serverClient;
    int clientNo;
    int squre;

    BufferedReader bufferedReader;
    BufferedWriter bufferedWriter;

    public BufferedReader getBufferedReader(){
      return bufferedReader;
    }


    public BufferedWriter getBufferedWriter(){
      return bufferedWriter;
    }
    public int getClienNo(){
      return clientNo;
    }
    public static Vector<ServerClientThread> getListClient(){
      return listClient;
    }
    public void setCounter(int nbr)throws Exception{
        clientNo = nbr;
    }



    public ServerClientThread(Socket inSocket,int counter) throws Exception{
      serverClient = inSocket;
      setCounter(counter);
      bufferedWriter = new BufferedWriter(new OutputStreamWriter(serverClient.getOutputStream()));
      bufferedReader = new BufferedReader(new InputStreamReader(serverClient.getInputStream()));
      listClient.add(this);
    }




  public void run(){
      String clientMessage;
      while(serverClient.isConnected()){
          try {
              clientMessage = bufferedReader.readLine();
              broadcastMessage(clientMessage);          
          } catch (Exception e) {
            closeEverything();
            break;
          }
      }
  }

  /**
   * send message to other client connected to the server **/
  public void broadcastMessage(String message){
      for (ServerClientThread client : listClient) {
        try {
          if(client.getClienNo()!=clientNo){
            client.getBufferedWriter().write(message);
            client.getBufferedWriter().newLine();
            client.getBufferedWriter().flush(); 
          }
        } catch (Exception e) {
          closeEverything();
        }
      }
  }

  /**
  * remove the client because the connection is no longer there
  * remove this Object to the static list of client **/
  public void removeClient(){
      listClient.remove(this);
      broadcastMessage("Server client n-"+clientNo+" leave the room");
  }


  /** 
   * close  socket ,and the buffered object (Writer,Reader)**/
  public void closeEverything(){
    removeClient();
    try {
        if(bufferedReader!=null){
          bufferedReader.close();
        }

        if(bufferedWriter!=null){
            bufferedWriter.close();
        }
        if(serverClient!=null){
          serverClient.close();
        }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}