package run;

import design.Echec;

import java.awt.*;
import java.net.Socket;

public class GameStart extends Thread{
    Echec echec;
    String p1;
    String p2;

    Socket socket;
    public GameStart(String Player1,String Player2){
        p1=Player1;
        p2 = Player2;
    }
    @Override
    public void run(){
        try{
            echec = new Echec(p1,p2);
            Point tempCur = new Point();
            Point tempNew = new Point();
            while(true){
                try{
                    if(echec.getCurrentPosition()!=null && echec.getNewPosition()!=null){
                       // if(comparePoints(tempCur,echec.getCurrentPosition())&&comparePoints(tempNew))
//                        DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
//                        PrintWriter printWriter = new PrintWriter(socket.getOutputStream());
//                        String curr = String.valueOf(echec.getCurrentPosition());
//                        System.out.println(curr);
//                        dataOutputStream.writeInt(curr.length());
//                        printWriter.println(curr);
//                        printWriter.flush();

                        System.out.print("["+echec.getCurrentPosition().x+","+echec.getCurrentPosition().y+"] to ");
                        System.out.println("["+echec.getNewPosition().x+","+echec.getNewPosition().y+"]");
                    }
                }catch (NullPointerException e){
                    System.out.println("Game not begin");
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public boolean comparePoints(Point coord1,Point coord2){
        boolean isSame = false;
        if(coord1.x==coord2.x && coord1.y==coord2.y){
            isSame = true;
        }
        return isSame;
    }
}
