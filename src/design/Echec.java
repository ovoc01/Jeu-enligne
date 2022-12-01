package design;

import javax.swing.JFrame;

import board.Board;
import player.Player;

import java.awt.*;

public class Echec extends JFrame {
    int width = 600, height = 628;
    Board board;
    Jeux jeux;
    Point currentPosition;
    Point newPosition;


    public void setWidth(int width) {
        this.width = width;
    }


    public void setHeight(int height) {
        this.height = height;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public void setJeux(Jeux jeux) {
        this.jeux = jeux;
    }

    public Point getCurrentPosition() {
        return currentPosition;
    }

    public void setCurrentPosition(Point currentPosition) {
        this.currentPosition = currentPosition;
    }

    public Point getNewPosition() {
        return newPosition;
    }

    public void setNewPosition(Point newPosition) {
        this.newPosition = newPosition;
    }

    public Jeux getJeux() {
        return jeux;
    }
    public Board getBoard() {
        return board;
    }
    
    public Echec(String Player1,String Player2) throws Exception {
        board = new Board(new Player(Player1, "Black"), new Player(Player2, "White"));
        this.jeux = new Jeux(this);
        this.add(jeux);
        initFrame();
    }

    public void initFrame() {
        this.setSize(this.width, this.height);
        this.setTitle("design.Echec (Black)");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLocation(100, 100);
        this.setVisible(true);
    }
}