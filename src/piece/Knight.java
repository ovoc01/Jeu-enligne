package piece;

import java.awt.Point;
import java.util.Vector;
import player.*;

public class Knight extends Piece {
    public Knight(Point point, Player player) throws Exception {
        super(point, player, "/home/mirindra/Documents/Jeu-enligne/src/"+player.getColor()+"/chevalier.png");
        moves=new int[][]{{-1, -2}, {-2, -1}, {-2, 1}, {-1, 2}, {1, -2}, {2, -1}, {2, 1}, {2, 1}, {1, 2}};
    }

    public Vector<Point> getMove() {
        Vector<Point> result=new Vector<Point>();
        for (int[] move : moves) {
            int x=point.x+move[0];
            int y=point.y+move[1];
            if ((isValidatePoint(new Point(x, y))
                    && (board.getPieces()[y][x]==null))
                    || (checkPiece(new Point(x, y), player)
                    || checkPiece(new Point(x, y), player.getAdvesaire())))
                result.add(new Point(x, y));
        }
        return result;
    }
}
