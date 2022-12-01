package piece;

import java.awt.Point;

import board.Board;
import player.*;

import java.util.Vector;
import javax.imageio.ImageIO;
import java.awt.*;
import java.io.*;

public abstract class Piece {
    Point point;
    Player player;
    Image icon;
    boolean danger = true;
    int[][] moves;
    static Board board;

    public void setPoint(Point point) throws NullPointerException {
        if (!isValidatePoint(point))
            throw new NullPointerException("Limite de board.Board atteint");
        this.point = point;
    }

    public void setDanger(boolean danger) {
        this.danger = danger;
    }

    public Image getIcon() {
        return icon;
    }

    public static boolean isValidatePoint(Point point) {
        return (point.x >= 0)
                && (point.y >= 0)
                && (point.x < Player.getBoard().getM())
                && (point.y < Player.getBoard().getN());
    }

    public static void setBoard(Board board) {
        Piece.board = board;
    }

    public Point getPoint() {
        return point;
    }

    public Player getPlayer() {
        return player;
    }
    public void setPlayer(Player player) {
        this.player = player;
    }

    public Piece(Point point, Player player, String path) throws Exception {
        setPoint(point);
        setPlayer(player);
        icon = upload(path);
    }

    public Image upload(String pathname) throws Exception {
        File file = new File(pathname);
        return ImageIO.read(file);
    }

    public abstract Vector<Point> getMove();

    //this player for friend and adversaire for killing
    public static boolean checkPiece(Point point, Player player) {
        return isValidatePoint(point)
                && (Player.getBoard().getPieces()[point.y][point.x] != null)
                && (Player.getBoard().getPieces()[point.y][point.x].getPlayer() == player);
    }

    public void moveUntilBlock(int[][] moves, Vector<Point> result) {
        for (int[] move : moves) {
            int x = point.x + move[0];
            int y = point.y + move[1];
            while (isValidatePoint(new Point(x, y)) && (board.getPieces()[y][x]==null)) {
                result.add(new Point(x, y));
                x += move[0]; y += move[1];
            }
            if (checkPiece(new Point(x, y), player) || checkPiece(new Point(x, y), player.getAdvesaire()))
                result.add(new Point(x, y));
        }
    }

    public Vector<Point> getSolution() {
        setDanger(true);
        Vector<Point> points = new Vector<Point>();
        Vector<Point> moves = getMove();
        if ((this instanceof King) && (!moves.isEmpty()))
            return moves;
        for (int i = 0; i < moves.size(); i++) {
            if (!checkPiece(moves.get(i), player))
                deplacementTest(moves.get(i), points);
        }
        return points;
    }

    public void deplacementTest(Point move, Vector<Point> points) {
        Piece tmpPiece = board.getPieces()[move.y][move.x];
        int index = player.getAdvesaire().getPieces().indexOf(tmpPiece);
        board.getPieces()[point.y][point.x]=null;
        if (checkPiece(move, player.getAdvesaire()))
            //Using for checkEchecEtMat() because this function use for getDanger() getPlayer()
            player.getAdvesaire().getPieces().remove(tmpPiece);
        board.getPieces()[move.y][move.x] = this;
        if (!board.checkEchecEtMat())
            points.add(move);
        if (index > 0)
            player.getAdvesaire().getPieces().add(index, tmpPiece);
        board.initPiece();
    }
}