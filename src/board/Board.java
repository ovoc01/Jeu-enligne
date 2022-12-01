package board;

import java.util.Vector;
import piece.*;
import java.awt.Point;
import player.*;

public class Board {
    Player p1, p2;
    protected int m = 8, n = 8;
    Vector<Piece> echiquiers = new Vector<Piece>();
    Piece[][] pieces = new Piece[m][n];
    int[][] move = new int[m][n];
    boolean tour = true;

    public void setTour(boolean tour) {
        this.tour = tour;
    }

    public boolean getTour() {
        return tour;
    }

    public Player getP1() {
        return p1;
    }

    public Player getP2() {
        return p2;
    }

    public int[][] getMove() {
        return move;
    }

    public int getN() {
        return n;
    }

    public Piece[][] getPieces() {
        return pieces;
    }

    public Vector<Piece> getEchiquiers() {
        return echiquiers;
    }

    public int getM() {
        return m;
    }

    public void setMove(int[][] move) {
        this.move = move;
    }

    public Board(Player p1, Player p2) throws Exception {
        Piece.setBoard(this);
        this.p1 = p1;
        this.p2 = p2;
        this.p1.setAdvesaire(p2);
        this.p2.setAdvesaire(p1);
        initEchequier(p1, p2);
        initPiece();
    }

    public void initPiece() {
        pieces = new Piece[m][n];
        for (int i = 0; i < echiquiers.size(); i++)
            pieces[echiquiers.get(i).getPoint().y][echiquiers.get(i).getPoint().x] = echiquiers.get(i);
    }

    public void initEchequier(Player p1, Player p2) throws Exception {
        Player.setBoard(this);
        initEchequier(p1);
        int capacity = echiquiers.size();
        for (int i = 0; i < capacity; i++)
            p1.getPieces().add(echiquiers.get(i));
        initEchequier(p2);
        for (int i = capacity; i < echiquiers.size(); i++)
            p2.getPieces().add(echiquiers.get(i));
    }

    public void initEchequier(Player player) throws Exception {
        int y = (player.getColor() == "Black") ? 0 : 7;
        echiquiers.add(new Rooks(new Point(0, y), player));
        echiquiers.add(new Knight(new Point(1, y), player));
        echiquiers.add(new Bishops(new Point(2, y), player));
        echiquiers.add(new Queen(new Point(3, y), player));
        echiquiers.add(new King(new Point(4, y), player));
        echiquiers.add(new Bishops(new Point(5, y), player));
        echiquiers.add(new Knight(new Point(6, y), player));
        echiquiers.add(new Rooks(new Point(7, y), player));
        y = (player.getColor() == "Black") ? 1 : 6;
        for (int i = 0; i < m; i++)
            echiquiers.add(new Pawns(new Point(i, y), player));
    }

    public void initMove() {
        this.setMove(new int[m][n]);
    }

    public Player getTourPlayer(boolean tour) {
        return (tour) ? p1 : p2;
    }

    public boolean checkEchecEtMat() {
        return this.getTourPlayer(tour).checkEchecEtMat();
    }

    //Piece for indentify Player
    public int[][] markMove(Vector<Point> points) {
        int[][] moves = new int[m][n];
        for (Point point : points)
            moves[point.y][point.x] = (Piece.checkPiece(point, getTourPlayer(!tour))) ? 2
                    : (Piece.checkPiece(point, getTourPlayer(tour))) ? 3 : 1;
        return moves;
    }

    public Player winner() {
        for (Piece piece : getTourPlayer(tour).getPieces()) {
            if (!piece.getSolution().isEmpty())
                return null;
        }
        return getTourPlayer(!tour);
    }
}