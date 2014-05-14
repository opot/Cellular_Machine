package com.youtolife.bacteria;

import java.util.Vector;

import com.youtolife.bacteria.Unit.Action;

public class WavePath {

	public static Vector<Action> getPath(Cell[][] my_map, int x, int y, int nx,
			int ny) {

		int[][] map = new int[my_map.length + 2][my_map[0].length + 2];

		for (int j = 0; j < map[0].length; j++) {
			map[0][j] = 1001;
			map[map.length - 1][j] = 1001;
		}
		for (int i = 0; i < map.length; i++) {
			map[i][0] = 1001;
			map[i][map[0].length - 1] = 1001;
		}

		for (int j = 1; j <= my_map[0].length; j++)
			for (int i = 1; i <= my_map.length; i++) {
				int n = 1001;

				if (my_map[i - 1][j - 1] != null) {
					if (my_map[i - 1][j - 1] instanceof Empty)
						n = 1000;
					if (my_map[i - 1][j - 1] instanceof Dirt)
						n = 1000;
					if (my_map[i - 1][j - 1] instanceof Unit)
						n = 1000;
				}
				map[i][j] = n;
			}

		map[x + 1][y + 1] = 0;

		int n = 1;
		boolean isEnd = false;
		while (!isEnd) {
			isEnd = true;
			for (int j = 1; j < map[0].length - 1; j++)
				for (int i = 1; i < map.length - 1; i++)
					if (map[i][j] == n - 1) {
						for (int k = 0; k <= 1; k++) {
							if (map[i + (1 + k * (-2))][j] == 1000) {
								map[i + (1 + k * (-2))][j] = n;
								isEnd = false;
							}
							if (map[i][j + (1 + k * (-2))] == 1000) {
								map[i][j + (1 + k * (-2))] = n;
								isEnd = false;
							}
						}
					}
			n++;
		}

		/*for (int j = 1; j < map[0].length - 1; j++) {
			for (int i = 1; i < map.length - 1; i++) {
				if (map[i][j] == 1001)
					System.out.print('x');
				else if (map[i][j] == 1000)
					System.out.print('*');
				else
					System.out.print(map[i][j]);
			}
			System.out.println();
		}

		System.out.println("Steps " + map[nx + 1][ny + 1]);
		System.out.println("new coords = " + nx + " " + ny + " old coords " + x+ " " + y);*/

		Vector<Action> path = new Vector<Action>();
		int cx = nx+1, cy = ny+1;
		int step = map[cx][cy];
		while (map[cx][cy]!=0) {
			boolean finded = false;
			for (int k = 0; k <= 1 && !finded; k++) {
				if (map[cx + 1 - 2 * k][cy] == step - 1) {
					finded = true;
					if (k == 0) {
						path.add(Action.left);
						cx++;
					}
					if (k == 1) {
						path.add(Action.right);
						cx--;
					}
					//System.out.println(path.get(path.size() - 1));
				} else {
					if (map[cx][cy + 1 - 2 * k] == step - 1) {
						finded = true;
						if (k == 0) {
							path.add(Action.up);
							cy++;
						}
						if (k == 1) {
							path.add(Action.down);
							cy--;
						}
						//System.out.println(path.get(path.size() - 1));
					}
				}
			}
			step--;
		}

		// for (Action a : path)
		// System.out.println(a);
		// System.out.println();
		return path;

	}

}
