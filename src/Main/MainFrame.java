package Main;

import static build.Builder.InitDisplay;
import static build.Builder.loadTex;

import org.lwjgl.opengl.Display;

import resurse.Maps;
import resurse.TileMap;
import resurse.Timer;
import actor.Mob;
import actor.Player;
import actor.Wave;

public class MainFrame {

	public MainFrame() {

		InitDisplay();

		TileMap map = new TileMap(Maps.getMap(1));

		Mob mob = new Mob(loadTex("spiderDown"), map.getTile(1, 1), map, 40, 40, 50, 25);

		Wave wave = new Wave(2, mob);
		Player player = new Player(map);

		while (!Display.isCloseRequested()) {

			Timer.update();

			map.draw();
			if (Timer.pause == false) {
				wave.update();
			}
			player.update();
			Display.update();
			Display.sync(30);
		}
		Display.destroy();
	}

	public static void main(String[] args) {

		new MainFrame();
	}

}
