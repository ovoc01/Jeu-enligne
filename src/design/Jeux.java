package design;//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import listener.Mouse;
import listener.Move;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseListener;
import java.awt.image.ImageObserver;
import javax.swing.JPanel;

public class Jeux extends JPanel {
    Echec echec;
    MouseListener[] listeners;

    public MouseListener[] getMouseListener() {
        return this.listeners;
    }

    public Echec getEchec() {
        return this.echec;
    }

    public Jeux(Echec var1) {
        this.echec = var1;
        this.setFocusable(true);
        this.listeners = new MouseListener[]{new Mouse(this), new Move(this)};
        MouseListener[] var2 = this.listeners;
        int var3 = var2.length;

        for(int var4 = 0; var4 < var3; ++var4) {
            MouseListener var5 = var2[var4];
            this.addMouseListener(var5);
        }

        this.setBackground(Color.gray);
    }

    public void paint(Graphics var1) {
        super.paint(var1);
        this.paintMove(var1);
        this.paintBoard(var1);
        this.echec.getBoard().initPiece();
        this.repaint();
    }

    public void paintBoard(Graphics var1) {
        var1.setColor(Color.BLACK);

        int var2;
        for(var2 = 0; var2 * 75 <= this.echec.height; ++var2) {
            var1.drawLine(0, var2 * 75, this.echec.width, var2 * 75);
        }

        for(var2 = 0; var2 * 75 <= this.echec.width; ++var2) {
            var1.drawLine(var2 * 75, 0, var2 * 75, this.echec.height);
        }

    }

    public void paintMove(Graphics var1) {
        for(int var2 = 0; var2 < this.echec.getBoard().getM(); ++var2) {
            for(int var3 = 0; var3 < this.echec.getBoard().getN(); ++var3) {
                if (this.echec.board.getMove()[var2][var3] == 1) {
                    var1.setColor(new Color(87, 121, 147));
                    var1.fillRect(var3 * 75, var2 * 75, 75, 75);
                } else if (this.echec.board.getMove()[var2][var3] == 2) {
                    var1.setColor(Color.PINK);
                    var1.fillRect(var3 * 75, var2 * 75, 75, 75);
                }

                if (this.echec.board.getPieces()[var2][var3] != null) {
                    var1.drawImage(this.echec.getBoard().getPieces()[var2][var3].getIcon(), var3 * 75, var2 * 75, 75, 75, (Color)null, (ImageObserver)null);
                }
            }
        }

    }
}
