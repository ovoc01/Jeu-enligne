package tcp;
import java.net.*;
import java.io.*;
import java.util.Scanner;
public class TCPClient {
  Socket socket;
  static int nbr_user;
  int clientNo;
  BufferedReader bufferedReader;
  BufferedWriter bufferedWriter;
  
  public static void resetUser(){
    nbr_user = 0;
  } 

  public TCPClient(int port){
      try {
        socket = new Socket("localhost",port);
        nbr_user++;
        clientNo = nbr_user;
        bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
      } catch (Exception e) {
        e.printStackTrace();
      }
  }


  public void sendMessage(){
    try {
      bufferedWriter.write("test");
      bufferedWriter.newLine();
      bufferedWriter.flush();
      while(socket.isConnected()){
        Scanner scanner = new Scanner(System.in);
        String message = scanner.nextLine();
        bufferedWriter.write("client n-"+clientNo+":"+message);
        bufferedWriter.newLine();
        bufferedWriter.flush(); 
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void listenMessage(){
      new Thread(new Runnable(){
        @Override
        public void run(){
            String msgFromOhterUser;
            while(socket.isConnected()){
              try {
                msgFromOhterUser = bufferedReader.readLine();
                System.out.println(msgFromOhterUser);
              } catch (Exception e) {
                e.printStackTrace();
              }
            }
        }
      }).start();
  }

  public static void main(String[] args) {
    TCPClient tcpClient = new TCPClient(8888);
    tcpClient.listenMessage();
    tcpClient.sendMessage();
  }
}
