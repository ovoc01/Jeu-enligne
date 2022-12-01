package listener;

import board.Board;
import design.Jeux;
import piece.*;
import java.awt.event.*;

import java.awt.Point;

public class Mouse implements MouseListener {
    Jeux jeux;
    Piece currentPiece;

    public Piece getCurrentPiece() {
        return currentPiece;
    }

    public Mouse(Jeux jeux) {
        this.jeux=jeux;
    }

    public void mouseClicked(MouseEvent e) {
    }

    public void mousePressed(MouseEvent e) {
        Board board=jeux.getEchec().getBoard();
        int x = (int) e.getX()/75;
        int y = (int) e.getY()/75;
        if ((Piece.checkPiece(new Point(x, y), board.getTourPlayer(board.getTour())))
                && (!board.getPieces()[y][x].getSolution().isEmpty())) {
            board.setMove(board.markMove(board.getPieces()[y][x].getSolution()));
            currentPiece = board.getPieces()[y][x];
        }}
    public void mouseReleased(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
}