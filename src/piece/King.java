package piece;

import java.awt.Point;
import java.util.Vector;
import player.*;

public class King extends Piece {

    public King(Point point, Player player) throws Exception {
        super(point, player, "/home/mirindra/Documents/Jeu-enligne/src/"+player.getColor()+"/roi.png");
        moves = new int[][]{{1, 1}, {1, 0}, {-1, -1}, {-1, 0}, {0, 1}, {0, -1}, {-1, 1}, {1, -1}};
    }

    public Vector<Point> getMove() {
        Vector<Point> result=new Vector<Point>();
        for (int[] move : moves) {
            boolean check = (danger) ? (isValidatePoint(new Point(point.x+move[0], point.y+move[1])))
                    && (getDanger()[point.y+move[1]][point.x+move[0]]==0)
                    && (!checkPiece(new Point(point.x+move[0], point.y+move[1]), player))
                    : (isValidatePoint(new Point(point.x+move[0], point.y+move[1])));
            if (check)
                result.add(new Point(point.x+move[0], point.y+move[1]));
        }
        return result;
    }

    public int[][] getDanger() {
        board.getPieces()[point.y][point.x] = null;
        Vector<Point> points = new Vector<Point>();
        for (int i = 0; i < player.getAdvesaire().getPieces().size(); i++) {
            Piece piece = player.getAdvesaire().getPieces().get(i);
            piece.setDanger(false);
            points.addAll(piece.getMove());
        }
        board.getPieces()[point.y][point.x] = this;
        return board.markMove(points);
    }

    public boolean checkEchecEtMat() {
        return getDanger()[point.y][point.x]!=0;
    }
}