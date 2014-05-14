package com.youtolife.bacteria;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;

public class Empty extends Cell {

	public Empty(Texture texture) {
		super(texture, new Color(1, 1, 1, 1));
	}

	@Override
	public void update(Cell[][] map, int x, int y, Texture texture) {

	}

}
