package design;

import listener.Replace;
import piece.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.GridLayout;

public class Change extends JFrame {
    int width, height=100;

    public Change(Piece current) throws Exception {
        Piece[] newPieces = {
                new Rooks(current.getPoint(), current.getPlayer()),
                new Knight(current.getPoint(), current.getPlayer()),
                new Bishops(current.getPoint(), current.getPlayer()),
                new Queen(current.getPoint(), current.getPlayer())
        };
        this.setLayout(new GridLayout(1, newPieces.length));
        this.width = newPieces.length*150;
        for (Piece piece : newPieces) {
            JButton button = new JButton(piece.getClass().getName().split("[.]")[1]);
            button.addMouseListener(new Replace(new Piece[]{piece, current}, this));
            add(button);
        }
        this.setSize(this.width, this.height);
        this.setTitle("design.Echec et Mat");
        this.setResizable(false);
        this.setLocation(100, 100);
        this.setVisible(true);
    }
}