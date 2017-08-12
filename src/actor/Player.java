package actor;

import java.awt.Font;
import java.util.ArrayList;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.TrueTypeFont;

import resurse.Tile;
import resurse.TileMap;
import resurse.TileType;
import resurse.Timer;

public class Player {

	private static TrueTypeFont font;
	private static Font awtFont = new Font(null, Font.BOLD, 20);
	private static TrueTypeFont font2;
	private static Font awtFont2 = new Font(null, Font.BOLD, 40);

	private final TileMap map;
	private static int gold = 200;
	private static int lives = 5;

	private boolean prim = true;
	private boolean gameover = true;
	private String text = "";
	private Tile t;
	private int x, y;

	private final ArrayList<Tower> towerList = new ArrayList<Tower>();

	public Player(TileMap map) {
		this.map = map;
	}

	public void setTile(int type) {
		x = (int) Math.floor(Mouse.getX() / 40);
		y = (int) Math.floor((719 - Mouse.getY()) / 40);
		t = map.getTile(x, y);
		if (t.getType() == TileType.Grass) {
			if (type == 0 && gold > 49) {
				map.setTile(x, y, TileType.Tower);
				towerList.add(new Tower(x * 40, y * 40, 0));
				gold -= 50;

			} else if (type == 1 && gold > 99) {
				map.setTile(x, y, TileType.Tower1);
				towerList.add(new Tower(x * 40, y * 40, 1));
				gold -= 100;

			}
		}
	}

	public static void addGold(int g) {
		gold += g;
		// System.out.println(gold);
	}

	public static String getLives() {
		return "" + lives;
	}

	public static void updateLives(int l) {
		lives += l;
	}

	public void update() {
		for (Tower t : towerList)
			t.update();

		if (prim) {
			init();
			prim = false;
		}
		if (gold > 0) {
			while (Mouse.next()) {
				if (Mouse.getEventButtonState()) {
					setTile(Mouse.getEventButton());
				}
			}

			// if(Mouse.isButtonDown(0)){
			// setTile(0);

			// }
			// if(Mouse.isButtonDown(1)){
			// setTile(1);

			// }
		}
		text = "Gold : ";
		text += gold;

		font.drawString(845, 50, text);

		text = "Lives : " + lives;
		font.drawString(725, 50, text);

		if (lives == 0) {
			if (gameover) {
				Timer.Pause();
				gameover = false;
			}
			font2.drawString(400, 350, "Game over !");

		}

	}

	private static void init() {
		font = new TrueTypeFont(awtFont, false);
		font2 = new TrueTypeFont(awtFont2, false);
	}
}
