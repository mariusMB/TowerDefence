package resurse;

public class TileMap {

	public Tile[][] map;

	public TileMap() {
		map = new Tile[25][18];

		for (int i = 0; i < map.length; i++)
			for (int j = 0; j < map[i].length; j++)
				map[i][j] = new Tile(i * 40, j * 40, 40, 40, TileType.Stone);
	}

	public TileMap(int newMap[][]) {
		map = new Tile[25][18];

		for (int i = 0; i < map.length; i++)
			for (int j = 0; j < map[i].length; j++) {
				switch (newMap[j][i]) {
					case 0 :
						map[i][j] = new Tile(i * 40, j * 40, 40, 40, TileType.Grass);
						break;
					case 1 :
						map[i][j] = new Tile(i * 40, j * 40, 40, 40, TileType.Stone);
						break;
					case 2 :
						map[i][j] = new Tile(i * 40, j * 40, 40, 40, TileType.Start);
						break;
					case 3 :
						map[i][j] = new Tile(i * 40, j * 40, 40, 40, TileType.Finish);
						break;
					case 4 :
						map[i][j] = new Tile(i * 40, j * 40, 40, 40, TileType.Tree1);
						break;
					case 5 :
						map[i][j] = new Tile(i * 40, j * 40, 40, 40, TileType.Tree2);
						break;
					case 6 :
						map[i][j] = new Tile(i * 40, j * 40, 40, 40, TileType.Tree3);
						break;
					case 7 :
						map[i][j] = new Tile(i * 40, j * 40, 40, 40, TileType.Tree4);
						break;
					case 8 :
						map[i][j] = new Tile(i * 40, j * 40, 40, 40, TileType.Bush1);
						break;
					case 9 :
						map[i][j] = new Tile(i * 40, j * 40, 40, 40, TileType.Bush3);
						break;

				}
			}
	}

	public void draw() {

		for (int i = 0; i < map.length; i++)
			for (int j = 0; j < map[i].length; j++) {
				Tile t = map[i][j];
				t.draw();
			}
	}

	public void setTile(int x, int y, TileType type) {
		map[x][y] = new Tile(x * 40, y * 40, 40, 40, type);
	}

	public Tile getTile(int x, int y) {
		return map[x][y];
	}

	public Tile[][] getMap() {
		return map;
	}
}
