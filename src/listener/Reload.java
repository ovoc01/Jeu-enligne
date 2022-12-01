package listener;

import design.Winner;
import player.Player;

import java.awt.event.*;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Reload implements MouseListener {
    Winner winner;

    public Reload(Winner winner) {
        this.winner=winner;
    }
    public void mouseClicked(MouseEvent e) {
        try {
            Player.getBoard().initEchequier(new Player(Player.getBoard().getP1().getName(),
                    Player.getBoard().getP1().getColor()), new Player(Player.getBoard().getP2().getName(),
                    Player.getBoard().getP2().getColor()));
            winner.dispose();
        } catch (Exception exception) {
            JOptionPane.showMessageDialog(new JFrame(), exception.getMessage());
        }
    }
    public void mousePressed(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
}