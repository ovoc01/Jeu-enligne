package listener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import game.*;
public class GameListener implements KeyListener {
    Game game ;
    public GameListener(Game g){
        game = g;
    }

    @Override
	public void keyTyped(KeyEvent e) {	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		int keyCode = e.getKeyCode();
		
		if(game.getGraphics().getState() == "RUNNING") {
			if(keyCode == KeyEvent.VK_Z && game.getPlayer().getMove() != "DOWN") {
				game.getPlayer().up();
			}
		
			if(keyCode == KeyEvent.VK_S && game.getPlayer().getMove() != "UP") {
				game.getPlayer().down();
			}
		
			if(keyCode == KeyEvent.VK_Q && game.getPlayer().getMove() != "RIGHT") {
				game.getPlayer().left();
			}
		
			if(keyCode == KeyEvent.VK_D && game.getPlayer().getMove() != "LEFT") {
				game.getPlayer().right();
			}
		}
		else {
			game.start();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {	}

}
