package piece;

import player.*;
import java.awt.Point;
import java.util.Vector;

public class Queen extends Piece {
    public Queen(Point point, Player player) throws Exception {
        super(point, player, "/home/mirindra/Documents/Jeu-enligne/src/"+player.getColor()+"/reine.png");
    }

    public Vector<Point> getMove() {
        //listener.Move of Knight and Bishops
        int[][][] moves={{{0, 1}, {0, -1}, {1, 0}, {-1, 0}}, {{1, 1}, {1, -1}, {-1, -1}, {-1, 1}}};
        Vector<Point> result=new Vector<Point>();
        for (int[][] move : moves)
            moveUntilBlock(move, result);
        return result;
    }
}
