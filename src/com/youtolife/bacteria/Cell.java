package com.youtolife.bacteria;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class Cell {

	public Color color;
	
	Sprite sprite;
	
	public Cell(Texture texture, Color color){
		this.color = color;
		sprite = new Sprite(texture);
		sprite.setColor(color);
		sprite.setSize(1, 1);
	}
	
	public abstract void update(Cell[][] map, int x, int y, Texture texture);
	
	public void draw(SpriteBatch batch,int x, int y){
		
		sprite.setPosition(x-Main.FIELD_SIZE/2-sprite.getWidth()/2, y-Main.FIELD_SIZE/2-sprite.getHeight()/2);
		sprite.draw(batch);
	}
}
