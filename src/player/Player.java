//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package player;

import board.Board;
import java.util.Vector;
import piece.King;
import piece.Piece;

public class Player {
    String name;
    String color;
    Player advesaire;
    Vector<Piece> pieces = new Vector();
    static Board board;

    public static void setBoard(Board var0) {
        board = var0;
    }

    public void setPieces(Vector<Piece> var1) {
        this.pieces = var1;
    }

    public void setAdvesaire(Player var1) {
        this.advesaire = var1;
    }

    public Player getAdvesaire() {
        return this.advesaire;
    }

    public static Board getBoard() {
        return board;
    }

    public Vector<Piece> getPieces() {
        return this.pieces;
    }

    public void setName(String var1) {
        this.name = var1;
    }

    public void setColor(String var1) {
        this.color = var1;
    }

    public String getName() {
        return this.name;
    }

    public String getColor() {
        return this.color;
    }

    public Player(String var1, String var2) {
        this.setName(var1);
        this.setColor(var2);
    }

    public boolean checkEchecEtMat() {
        return this.findKing().checkEchecEtMat();
    }

    public King findKing() {
        King var1 = null;

        for(int var2 = 0; var2 < this.pieces.size(); ++var2) {
            if (this.pieces.get(var2) instanceof King) {
                var1 = (King)this.pieces.get(var2);
            }
        }

        return var1;
    }

    public void removePiece(Piece var1) {
        board.getEchiquiers().remove(var1);
        this.pieces.remove(var1);
    }
}
