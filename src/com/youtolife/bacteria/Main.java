package com.youtolife.bacteria;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Main extends ApplicationAdapter {

	public static final int FIELD_SIZE = 15;// only odd
	public static final float UPDATE_TIME = 0.0001f;

	SpriteBatch batch;
	OrthographicCamera camera;
	Texture texture;

	Cell[][] map;

	@Override
	public void create() {
		batch = new SpriteBatch();
		camera = new OrthographicCamera(FIELD_SIZE, FIELD_SIZE);

		map = new Cell[FIELD_SIZE][FIELD_SIZE];

		texture = new Texture(Gdx.files.internal("cell.png"));

		char[][] bmap = (new MazeGenerator()).getMaze(FIELD_SIZE - 1);
		for (int i = 0; i < FIELD_SIZE; i++)
			for (int j = 0; j < FIELD_SIZE; j++) {
				if (bmap[i][j] == 0)
					map[i][j] = new Empty(texture);
				if (bmap[i][j] == 1)
					map[i][j] = new Wall(texture);
			}

	}

	@Override
	public void render() {
		this.update();

		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batch.setProjectionMatrix(camera.combined);

		batch.begin();
		for (int i = 0; i < FIELD_SIZE; i++)
			for (int j = 0; j < FIELD_SIZE; j++)
				map[i][j].draw(batch, i, j);
		batch.end();
	}

	public void update() {

		Input input = Gdx.input;

		for (int i = 0; i < FIELD_SIZE; i++)
			for (int j = 0; j < FIELD_SIZE; j++)
				map[i][j].update(map, i, j, texture);

		if (input.isTouched()) {
			float stepX = Gdx.graphics.getWidth() / FIELD_SIZE;
			float stepY = Gdx.graphics.getHeight() / FIELD_SIZE;
			float x = input.getX();
			float y = input.getY();
			for (int i = 0; i < FIELD_SIZE; i++)
				for (int j = 0; j < FIELD_SIZE; j++) {
					if (x >= stepX * i && x <= stepX * (i + 1)
							&& y >= stepY * j && y <= stepY * (j + 1))
						if (map[i][FIELD_SIZE - j - 1] instanceof Empty)
							map[i][FIELD_SIZE - j - 1] = new Unit(texture, map,
									i, j);
				}
		}

	}
}
