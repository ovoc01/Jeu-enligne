package listener;

import board.Board;
import design.Change;
import design.Jeux;
import design.Winner;
import piece.Pawns;
import piece.Piece;

import java.awt.event.*;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import java.awt.Point;
import java.io.DataOutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class Move implements MouseListener {
    Jeux jeux;
    Socket socket;
    public Move(Jeux jeux) {
        this.jeux=jeux;
    }
    public void mouseClicked(MouseEvent e) {
        int x = (int) e.getX()/75;
        int y = (int) e.getY()/75;
        Board board = jeux.getEchec().getBoard();
        try {
            if (board.getMove()[y][x] == 1) {
                move(new Point(x, y));

            } else if (board.getMove()[y][x] == 2) {
                board.getTourPlayer(!board.getTour()).removePiece(board.getPieces()[y][x]);
                move(new Point(x, y));
            }
        } catch (Exception pointer) {
            JOptionPane.showMessageDialog(new JFrame(), pointer.getMessage());
        }
    }

    public void move(Point point) throws Exception {
        Board board = jeux.getEchec().getBoard();
        Piece currentPiece = ((Mouse) jeux.getMouseListener()[0]).currentPiece;

        //Sending position through Socket to the server
        socket = new Socket("localhost",6969);
        DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());

        PrintWriter printWriter = new PrintWriter(socket.getOutputStream());
        String curr = String.valueOf(jeux.getEchec().getCurrentPosition());
        System.out.println(curr);
        dataOutputStream.writeInt(curr.length());
        printWriter.println(curr);
        printWriter.flush();

        jeux.getEchec().setCurrentPosition(currentPiece.getPoint());
        currentPiece.setPoint(point);
        jeux.getEchec().setNewPosition(currentPiece.getPoint());

        board.initMove();
        board.setTour(!board.getTour());
        jeux.getEchec().setTitle(" design.Echec "+"("+board.getTourPlayer(board.getTour()).getColor()+")");
        if ((currentPiece instanceof Pawns) && (((Pawns) currentPiece).checkCanChange()))
            new Change(currentPiece);
        if (board.checkEchecEtMat()) {
            jeux.getEchec().setTitle(" design.Echec "+"("+board.getTourPlayer(board.getTour()).getColor()+")"+" design.Echec Et Mat");
            if (board.winner() != null) new Winner(board.winner());
        }
    }

    public void mousePressed(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
}