package game;

import java.awt.Rectangle;
import java.util.ArrayList;

public class Snake{
    private ArrayList<Rectangle> body;
	private int w = 10;
	private int h = 10;
	private int d = 10;
	
	private String move; //NOTHING, UP, DOWN, LEFT, RIGHT
	
	public Snake() {
		body = new ArrayList<>();
		
		Rectangle temp = new Rectangle(10, 10);
		temp.setLocation(Game.width / 2 * 10, Game.height / 2 * 10);
		body.add(temp);
		
		temp = new Rectangle(d, d);
		temp.setLocation((w / 2 - 1) * d, (h / 2) * d);
		body.add(temp);
		
		temp = new Rectangle(d, d);
		temp.setLocation((w / 2 - 2) * d, (h / 2) * d);
		body.add(temp);
		
		move = "NOTHING";
	}
	
	public void move() {
		if(move != "NOTHING") {
			Rectangle first = body.get(0);
			
			Rectangle temp = new Rectangle(10, 10);
			
			if(move == "UP") {
				temp.setLocation(first.x, first.y - 10);
			}
			else if(move == "DOWN") {
				temp.setLocation(first.x, first.y + 10);
			}
			else if(move == "LEFT") {
				temp.setLocation(first.x - 10, first.y);
			}
			else{
				temp.setLocation(first.x + 10, first.y);
			}
			
			body.add(0, temp);
			body.remove(body.size()-1);
		}
	}
	
	public void grow() {
		Rectangle first = body.get(0);
		
		Rectangle temp = new Rectangle(10, 10);
		
		if(move == "UP") {
			temp.setLocation(first.x, first.y - 10);
		}
		else if(move == "DOWN") {
			temp.setLocation(first.x, first.y + 10);
		}
		else if(move == "LEFT") {
			temp.setLocation(first.x - 10, first.y);
		}
		else{
			temp.setLocation(first.x + 10, first.y);
		}
		
		body.add(0, temp);
	}

	public ArrayList<Rectangle> getBody() {
		return body;
	}
	

	public void setBody(ArrayList<Rectangle> body) {
		this.body = body;
	}
	
	public int getX() {
		return body.get(0).x;
	}
	
	public int getY() {
		return body.get(0).y ;
	}
	
	public String getMove() {
		return move;
	}
	
	public void up() {
		move = "UP";
	}
	public void down() {
		move = "DOWN";
	}
	public void left() {
		move = "LEFT";
	}
	public void right() {
		move = "RIGHT";
	}
}