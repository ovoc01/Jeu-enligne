package piece;

import java.awt.Point;
import java.util.Vector;
import player.*;

public class Bishops extends Piece {
    public Bishops(Point point, Player player) throws Exception {
        super(point, player, "/home/mirindra/Documents/Jeu-enligne/src/"+player.getColor()+"/fou.png");
        moves = new int[][]{{1, 1}, {1, -1}, {-1, -1}, {-1, 1}};
    }

    public Vector<Point> getMove() {
        Vector<Point> result = new Vector<Point>();
        moveUntilBlock(moves, result);
        return result;
    }
}