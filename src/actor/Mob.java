package actor;

import static build.Builder.drawQuadTex;
import static build.Builder.loadTex;
import static resurse.Timer.Delta;

import org.newdawn.slick.opengl.Texture;

import resurse.Tile;
import resurse.TileMap;
import resurse.TileType;

public class Mob {

	private int hp, speed;
	private final int maxHP;
	private float x, y, w, h;
	private Texture tex;
	private final Texture texUp;
	private final Texture texDown;
	private final Texture texLeft;
	private final Texture texRight;
	private final Texture HPtex;
	private final Texture HPbackTex;
	private final Texture HPborTex;
	private Tile start;
	private boolean first = true;
	private TileMap map;
	private int to = 1, from = 0;
	private int mx, my;

	private Tile t, next;

	private boolean atEnd = false;

	public Mob(Texture tex, Tile start, TileMap map, float w, float h, int hp, int s) {
		this.tex = tex;
		this.HPbackTex = loadTex("HPback");
		this.HPborTex = loadTex("HPborder");
		this.HPtex = loadTex("HP");
		this.start = start;
		this.map = map;
		this.x = start.getX();
		this.y = start.getY() + 1;
		this.w = w;
		this.h = h;
		this.hp = hp;
		this.maxHP = hp;
		this.speed = s;
		this.mx = (int) x / 40;
		this.my = (int) y / 40;

		this.texUp = loadTex("spiderUp");
		this.texDown = loadTex("spiderDown");
		this.texLeft = loadTex("spiderLeft");
		this.texRight = loadTex("spiderRight");

	}

	public void draw() {

		float t = (float) hp / maxHP;
		drawQuadTex(tex, x, y, w, h);

		drawQuadTex(HPbackTex, x, y - 10, w, 8);
		drawQuadTex(HPtex, x, y - 10, 40 * t, 8);
		drawQuadTex(HPborTex, x, y - 10, w, 8);

	}

	public void update() {
		if (atEnd) {
			this.hp = -100;
			Player.updateLives(-1);
		} else if (first)
			first = false;
		else {
			dir();

			if (to == 0) {
				tex = texUp;
				y += Delta() * speed * -1;
			} else if (to == 1) {
				tex = texDown;
				y += Delta() * speed;
			} else if (to == 2) {
				tex = texLeft;
				x += Delta() * speed * -1;
			} else if (to == 3) {
				tex = texRight;
				x += Delta() * speed;
			}

		}
	}

	// 0 up ,1 down ,2 left ,3 right
	private void dir() {
		switch (from) {
			case 0 :
				mx = (int) x / 40;
				my = (int) y / 40;
				if (isPossibleDown()) {
					from = 0;
					to = 1;
				} else if (isPossibleRight()) {
					from = 2;
					to = 3;
				} else if (isPossibleLeft()) {
					from = 3;
					to = 2;
				}
				break;
			case 1 :
				mx = (int) x / 40;
				my = (int) (y / 40 + 0.9);
				if (isPossibleUp()) {
					from = 1;
					to = 0;
				} else if (isPossibleRight()) {
					from = 2;
					to = 3;
				} else if (isPossibleLeft()) {
					from = 3;
					to = 2;
				}
				break;
			case 2 :
				mx = (int) x / 40;
				my = (int) y / 40;
				if (isPossibleRight()) {
					from = 2;
					to = 3;
					break;
				} else if (isPossibleUp()) {
					from = 1;
					to = 0;
					break;
				} else if (isPossibleDown()) {
					from = 0;
					to = 1;
					break;
				}
			case 3 :
				mx = (int) (x / 40 + 0.9);// (int) Math.ceil(x/40);
				my = (int) y / 40;
				if (isPossibleLeft()) {
					from = 3;
					to = 2;
				} else if (isPossibleUp()) {
					from = 1;
					to = 0;
				} else {
					if (isPossibleDown()) {
						from = 0;
						to = 1;
					}
				}
				break;
		}

	}

	private boolean isPossibleRight() {
		boolean state = true;
		t = map.getTile(mx, my);
		next = map.getTile(mx + 1, my);
		if (t.getType() != next.getType())
			state = false;

		if (next.getType() == TileType.Finish)
			atEnd = true;

		return state;
	}

	private boolean isPossibleLeft() {
		boolean state = true;
		t = map.getTile(mx, my);
		next = map.getTile(mx - 1, my);
		if (t.getType() != next.getType())
			state = false;

		if (next.getType() == TileType.Finish)
			atEnd = true;

		return state;
	}

	private boolean isPossibleUp() {
		boolean state = true;
		t = map.getTile(mx, my);
		next = map.getTile(mx, my - 1);
		if (t.getType() != next.getType())
			state = false;

		if (next.getType() == TileType.Finish)
			atEnd = true;

		return state;
	}

	private boolean isPossibleDown() {
		boolean state = true;
		t = map.getTile(mx, my);
		next = map.getTile(mx, my + 1);
		if (t.getType() != next.getType())
			state = false;

		if (next.getType() == TileType.Finish)
			atEnd = true;

		return state;
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public float getW() {
		return w;
	}

	public void setW(float w) {
		this.w = w;
	}

	public float getH() {
		return h;
	}

	public void setH(float h) {
		this.h = h;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public Texture getTex() {
		return tex;
	}

	public void setTex(Texture tex) {
		this.tex = tex;
	}

	public Tile getStart() {
		return start;
	}

	public void setStart(Tile start) {
		this.start = start;
	}

	public boolean isFirst() {
		return first;
	}

	public void setFirst(boolean first) {
		this.first = first;
	}

	public TileMap getMap() {
		return map;
	}

	public void setMap(TileMap map) {
		this.map = map;
	}

	@Override
	protected void finalize() throws Throwable {
		// TODO Auto-generated method stub
		super.finalize();
	}

}
