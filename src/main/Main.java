package main;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import run.GameStart;

import java.net.Socket;

public class Main {
    public static void main(String[] args) {
        try {
            GameStart gameStart = new GameStart("Mirindra","Daniel");
           // run.GameStart gameStart2 = new run.GameStart("Mirindra","Daniel");
            gameStart.start();
            //gameStart2.start();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(new JFrame(), e.getMessage());
            e.printStackTrace();
        }
    }
}