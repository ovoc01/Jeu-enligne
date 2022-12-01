package piece;

import java.awt.Point;
import java.util.Vector;
import player.*;

public class Pawns extends Piece {
    public Pawns(Point point, Player player) throws Exception {
        super(point, player, "/home/mirindra/Documents/Jeu-enligne/src/"+player.getColor()+"/pion.png");
        moves = (player.getColor() == "Black") ? new int[][]{{-1, 1}, {1, 1}} : new int[][]{{-1, -1}, {1, -1}};
    }

    public Vector<Point> getMove() {
        Vector<Point> result=new Vector<Point>();
        if(danger) {
            int increment=0;
            int sign = (player.getColor() == "Black") ? 1 : -1;
            int limit = ((point.y == 1) || (point.y == 6)) ? 2 : 1;
            int y=point.y+sign;
            while ((increment<limit) && (isValidatePoint(new Point(point.x, y))) && (board.getPieces()[y][point.x]==null)) {
                result.add(new Point(point.x, y));
                y+=sign; increment++;
            }
            makePieceToKill(result, danger);
        } else {
            makePieceToKill(result, danger);
        }
        return result;
    }

    public void makePieceToKill(Vector<Point> result, boolean kill) {
        for (int[] move : moves) {
            boolean check = (kill) ? checkPiece(new Point(point.x+move[0], point.y+move[1]), player.getAdvesaire())
                    : isValidatePoint(new Point(point.x+move[0], point.y+move[1]));
            if (check)
                result.add(new Point(point.x+move[0], point.y+move[1]));
        }
    }

    public boolean checkCanChange() {
        return point.y == 7 || point.y == 0 ? true : false;
    }
}