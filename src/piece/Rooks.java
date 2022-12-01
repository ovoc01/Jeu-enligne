package piece;

import java.awt.Point;
import java.util.Vector;
import player.*;

public class Rooks extends Piece {
    public Rooks(Point point, Player player) throws Exception {
        super(point, player, "/home/mirindra/Documents/Jeu-enligne/src/"+player.getColor()+"/tour.png");
        //System.out.println("./"+player.getColor()+"/tour.png");
        moves = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    }

    public Vector<Point> getMove() {
        Vector<Point> result = new Vector<Point>();
        moveUntilBlock(moves, result);
        return result;
    }
}
