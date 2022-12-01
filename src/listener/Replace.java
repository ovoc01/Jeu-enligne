package listener;

import java.awt.event.*;

import design.Change;
import design.Winner;
import piece.*;
import player.*;

public class Replace implements MouseListener {
    Piece[] piece;
    Change change;

    public Replace(Piece[] piece, Change change) {
        this.piece=piece;
        this.change=change;
    }

    public void mouseClicked(MouseEvent e) {
        Player.getBoard().getEchiquiers().set(Player.getBoard().getEchiquiers().indexOf(piece[1]), piece[0]);
        piece[1].getPlayer().getPieces().set(piece[1].getPlayer().getPieces().indexOf(piece[1]), piece[0]);
        Player.getBoard().initPiece();
        if (Player.getBoard().winner()!=null)
            new Winner(Player.getBoard().winner());
        change.dispose();
    }

    public void mousePressed(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
}