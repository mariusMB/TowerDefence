package actor;

import static build.Builder.drawQuadTex;
import static resurse.Timer.Delta;

import org.newdawn.slick.opengl.Texture;

public class Projectile {

	private final Texture tex;
	private float x;
	private float y;
	private final int speed;
	private final int damage;

	private final Mob target;
	private float xDir;
	private float yDir;
	private float xDis, yDis, totalDis;
	private boolean active;

	public Projectile(Texture tex, Mob target, float x, float y, int speed, int damage) {
		this.tex = tex;
		this.x = x;
		this.y = y;
		this.speed = speed;
		this.damage = damage;
		this.target = target;
		this.xDir = 0;
		this.yDir = 0;
		Dir();
		this.active = true;
	}

	public void draw() {

		drawQuadTex(tex, x, y, 40, 40);
	}

	public void update() {
		if (active) {
			x += xDir * speed * Delta();
			y += yDir * speed * Delta();

			if (atTarget(x, y, target.getX(), target.getY())) {
				target.setHp(target.getHp() - damage);
				active = false;
			}

			draw();
		}
	}

	private void Dir() {
		xDis = Math.abs(target.getX() - x);
		yDis = Math.abs(target.getY() - y);
		totalDis = xDis + yDis;
		xDir = xDis / totalDis;
		yDir = 1.0f - xDir;

		if (target.getX() < x)
			xDir *= -1;

		if (target.getY() < y)
			yDir *= -1;
	}

	private boolean atTarget(float x1, float y1, float x2, float y2) {
		if (x1 + 40 > x2 && x1 < x2 + 40 && y1 + 40 > y2 && y1 < y2 + 40)
			return true;
		else
			return false;
	}
}
