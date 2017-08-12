package actor;

import static build.Builder.loadTex;

import java.util.ArrayList;

import resurse.Timer;

public class Tower {

	private final float x, y;
	private float spawnTime;
	private float timeSinceLastSpawn = 0;
	private final int type;
	private int range;
	private boolean first = true;
	private final ArrayList<Projectile> projectileList = new ArrayList<Projectile>();

	private ArrayList<Mob> mobs = new ArrayList<Mob>();
	private Mob target;

	private float xDis, yDis;

	public Tower(float x, float y, int type) {
		this.x = x;
		this.y = y;
		this.type = type;

		if (type == 0) {
			this.spawnTime = 1;
			this.range = 200;
		} else if (type == 1) {
			this.spawnTime = 2;
			this.range = 100;
		}
		this.timeSinceLastSpawn = 0;
		this.mobs = Wave.getMobList();

	}

	public void update() {
		if (first) {
			Timer.Delta();
			first = false;
		} else
			timeSinceLastSpawn = timeSinceLastSpawn + Timer.Delta();

		mobs = Wave.getMobList();

		if (mobs.size() > 0) {
			target = getTarget();

			if (timeSinceLastSpawn > spawnTime) {

				if (target != null)
					spawn();

			}

			for (Projectile p : projectileList)
				p.update();
		}
	}

	private void spawn() {
		timeSinceLastSpawn = 0;
		if (type == 0) {
			projectileList.add(new Projectile(loadTex("projectiles2"), target, x, y, 160, 5));

		} else if (type == 1) {
			projectileList.add(new Projectile(loadTex("projectiles1"), target, x, y, 60, 35));
		}

	}

	private Mob getTarget() {
		Mob t = null;
		float min = 10000;

		for (Mob m : mobs)
			if (inRange(m) && Dis(m) < min) {
				min = Dis(m);
				t = m;
			}

		return t;
	}

	private boolean inRange(Mob m) {
		xDis = Math.abs(m.getX() - x);
		yDis = Math.abs(m.getY() - y);

		if (xDis < range && yDis < range)
			return true;
		else
			return false;
	}

	private float Dis(Mob m) {
		xDis = Math.abs(m.getX() - x);
		yDis = Math.abs(m.getY() - y);

		return xDis + yDis;
	}
}
