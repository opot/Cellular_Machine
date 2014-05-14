package com.youtolife.bacteria;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;

public class Wall extends Cell {

	public Wall(Texture texture) {
		super(texture, new Color(0.1f, 0.1f, 0.1f, 1));
	}

	@Override
	public void update(Cell[][] map, int x, int y, Texture texture) {

	}

}
