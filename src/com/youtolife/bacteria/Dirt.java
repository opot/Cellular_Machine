package com.youtolife.bacteria;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;

public class Dirt extends Cell {

	public Dirt(Texture texture) {
		super(texture, new Color(0.49f,0.2f,0,1));
	}

	@Override
	public void update(Cell[][] map, int x, int y, Texture texture) {
		// TODO Auto-generated method stub

	}

}
