package design;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import listener.Reload;
import player.*;

import java.awt.GridLayout;

public class Winner extends JFrame {
    int width=200, height=200;

    public Winner(Player winner) {
        setLayout(new GridLayout(2, 1));
        JLabel label = new JLabel(winner.getName()+ " Color :"+winner.getColor());
        label.setBounds(20, 20, 100, 50);
        add(label);
        JButton button = new JButton("Rejouer");
        button.addMouseListener(new Reload(this));
        add(button);
        this.setSize(this.width, this.height);
        this.setTitle("design.Echec et Mat");
        this.setResizable(false);
        this.setLocation(100, 100);
        this.setVisible(true);
    }
}
