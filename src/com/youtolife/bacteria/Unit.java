package com.youtolife.bacteria;

import java.util.Random;
import java.util.Vector;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;

public class Unit extends Cell {

	Cell[][] my_map = new Cell[3][3];// 0 - empty 1 - wall, 2 - interest,
										// -1 - unit
	float update_time = Main.UPDATE_TIME;
	int mapX = 1, mapY = 1;
	Vector<Action> queue = new Vector<Action>();

	enum Action {
		left, right, up, down
	}

	public Unit(Texture texture, Cell[][] map, int x, int y) {
		super(texture, new Color(1f, 0, 0, 1));
		for (int i = x - 1; i <= x + 1; i++)
			for (int j = y - 1; j <= y + 1; j++)
				my_map[i - x + 1][j - y + 1] = map[i][Main.FIELD_SIZE - j - 1];

		my_map[1][1] = this;
/*
		for (int j = 0; j < my_map[0].length; j++) {
			for (int i = 0; i < my_map.length; i++) {
				char ch = '?';

				if (my_map[i][j] != null) {
					if (my_map[i][j] instanceof Wall)
						ch = 'x';
					if (my_map[i][j] instanceof Empty)
						ch = 'a';
					if (my_map[i][j] instanceof Dirt)
						ch = '*';
					if (my_map[i][j] instanceof Unit)
						ch = '@';
				}

				System.out.print(ch);
			}
			System.out.println();
		}
		System.out.println();
		
		queue.add(Action.left);
		queue.add(Action.left);
		queue.add(Action.left);
		queue.add(Action.left);
		queue.add(Action.up);
		queue.add(Action.up);
		queue.add(Action.up);
		queue.add(Action.up);*/
		
	}

	@Override
	public void update(Cell[][] map, int x, int y, Texture texture) {

		update_time -= Gdx.graphics.getDeltaTime();
		if (update_time <= 0) {
			update_time += Main.UPDATE_TIME;

			if (queue.size() != 0) {
				Action action = queue.get(queue.size()-1);
				queue.remove(queue.size()-1);

				int nx = x, ny = y;

				if (action == Action.left) {
					map[x - 1][y] = this;
					map[x][y] = new Dirt(texture);
					mapX -= 1;
					nx--;

					if (mapX == 0) {
						Cell[][] mini_map = new Cell[my_map.length + 1][my_map[0].length];
						for (int i = 0; i < my_map.length; i++)
							for (int j = 0; j < my_map[0].length; j++)
								mini_map[i + 1][j] = my_map[i][j];
						my_map = mini_map;
						mapX = 1;
					}
				}

				if (action == Action.right) {
					map[x + 1][y] = this;
					map[x][y] = new Dirt(texture);
					mapX += 1;
					nx++;

					if (mapX == my_map.length - 1) {
						Cell[][] mini_map = new Cell[my_map.length + 1][my_map[0].length];
						for (int i = 0; i < my_map.length; i++)
							for (int j = 0; j < my_map[0].length; j++)
								mini_map[i][j] = my_map[i][j];
						my_map = mini_map;
					}

				}
				if (action == Action.up) {
					map[x][y + 1] = this;
					map[x][y] = new Dirt(texture);
					mapY--;
					ny++;

					if (mapY == 0) {
						Cell[][] mini_map = new Cell[my_map.length][my_map[0].length + 1];
						for (int i = 0; i < my_map.length; i++)
							for (int j = 0; j < my_map[0].length; j++)
								mini_map[i][j + 1] = my_map[i][j];
						my_map = mini_map;
						mapY = 1;
					}

				}
				if (action == Action.down) {
					map[x][y - 1] = this;
					map[x][y] = new Dirt(texture);
					mapY++;
					ny--;

					if (mapY == my_map[0].length - 1) {
						Cell[][] mini_map = new Cell[my_map.length][my_map[0].length + 1];
						for (int i = 0; i < my_map.length; i++)
							for (int j = 0; j < my_map[0].length; j++)
								mini_map[i][j] = my_map[i][j];
						my_map = mini_map;
					}

				}

				for (int i = -1; i <= 1; i++)
					for (int j = -1; j <= 1; j++)
						my_map[i + mapX][j + mapY] = map[i + nx][ny - j];

				/*for (int j = 0; j < my_map[0].length; j++) {
					for (int i = 0; i < my_map.length; i++) {
						char ch = '?';

						if (my_map[i][j] != null) {
							if (my_map[i][j] instanceof Wall)
								ch = 'x';
							if (my_map[i][j] instanceof Empty)
								ch = 'a';
							if (my_map[i][j] instanceof Dirt)
								ch = '*';
							if (my_map[i][j] instanceof Unit)
								ch = '@';
						}

						System.out.print(ch);
					}
					System.out.println();
				}
				System.out.println();*/
			} else {
				Random r = new Random();
				for (int j = 0; j < my_map[0].length; j++)
					for (int i = 0; i < my_map.length; i++)
						if (my_map[i][j] instanceof Empty)
							if (r.nextBoolean()){
								queue.addAll(WavePath.getPath(my_map, mapX,
										mapY, i, j));
								return;
							}
			}
		}
	}

}
