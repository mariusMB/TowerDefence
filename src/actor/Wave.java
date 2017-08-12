package actor;

import java.util.ArrayList;

import resurse.Timer;

public class Wave {

	private float timeSinceLastSpawn = 0;
	private final float spawnTime;
	private final Mob mobType;
	private static ArrayList<Mob> mobList;
	private int i = 0;
	private ArrayList<Mob> killList;

	private boolean first = true;
	int waveNr = 0;

	public Wave(float spawnTime, Mob mobType) {
		this.spawnTime = spawnTime;
		this.mobType = mobType;
		mobList = new ArrayList<Mob>();
		timeSinceLastSpawn = 0;
	}

	public void update() {
		killList = new ArrayList<Mob>();

		if (first) {
			Timer.Delta();
			first = false;
			timeSinceLastSpawn = 0;
		} else
			timeSinceLastSpawn = timeSinceLastSpawn + Timer.Delta();

		if (timeSinceLastSpawn > spawnTime) {

			if (i < 10) {
				Spawn();
				i++;
			}

			timeSinceLastSpawn = 0;
		}
		if (mobList.size() == 0) {
			i = 0;
			// System.out.println(waveNr);
			waveNr++;
		}
		for (Mob m : mobList) {

			m.update();
			m.draw();
			if (m.getHp() <= 0) {
				if (m.getHp() > -100)
					Player.addGold(5);
				killList.add(m);
			}
		}
		for (Mob m : killList) {
			mobList.remove(m);
			try {
				m.finalize();
			} catch (Throwable e) {

				e.printStackTrace();
			}
		}
	}

	private void Spawn() {
		mobList.add(new Mob(mobType.getTex(), mobType.getStart(), mobType.getMap(), 40, 40, mobType.getHp() + waveNr, mobType.getSpeed()));

	}

	public static ArrayList<Mob> getMobList() {
		return mobList;
	}

}
