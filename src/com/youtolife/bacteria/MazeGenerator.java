package com.youtolife.bacteria;

import java.util.Random;

public class MazeGenerator {

	static final int fullfill = 30;
	static final int wallshort = 100;

	int size;

	Random rand = new Random();
	char[][] m = new char[size + 1][size + 1];
	int[][] r = new int[2][size / 2 * size / 2];
	int h = 0;

	int startx, starty;

	public char[][] getMaze(int size) {

		this.size = size;

		m = new char[size + 1][size + 1];
		r = new int[2][size / 2 * size / 2];

		for (int i = 0; i < size; i++)
			for (int j = 0; j < size; j++)
				m[i][j] = 0;

		for (int i = 0; i <= size; i++) {
			m[0][i] = 1;
			m[size][i] = 1;

			m[i][0] = 1;
			m[i][size] = 1;
		}

		initrandom();

		while (getrandom()) {

			if (m[starty][startx] == 1)
				continue;
			if (rand.nextInt(100) > fullfill)
				continue;
			int sx = 0, sy = 0;
			do {
				sx = rand.nextInt(3) - 1;
				sy = rand.nextInt(3) - 1;
			} while (sx == 0 && sy == 0 || sx != 0 && sy != 0);
			while (m[starty][startx] == 0) {
				if (rand.nextInt(100) > wallshort) {
					m[starty][startx] = 1;
					break;
				}
				m[starty][startx] = 1;
				startx += sx;
				starty += sy;
				m[starty][startx] = 1;
				startx += sx;
				starty += sy;
			}
		}
		return m;
	}

	private void initrandom() {
		int j = 0;

		for (int y = 2; y < size; y += 2)
			for (int x = 2; x < size; x += 2) {
				r[0][j] = x;
				r[1][j] = y;
				j++;
			}
		h = j - 1;
	}

	private boolean getrandom() {

		int i = (new Random()).nextInt(h);

		startx = r[0][i];
		starty = r[1][i];

		r[0][i] = r[0][h];
		r[1][i] = r[1][h];

		return (--h) != 0;

	}

}
