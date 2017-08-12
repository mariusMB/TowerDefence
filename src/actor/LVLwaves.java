package actor;

import static build.Builder.loadTex;

import java.util.ArrayList;

import resurse.TileMap;

public class LVLwaves {

	//private float timeSinceLastSpawn , spawnTime;
	private ArrayList <Wave>waveList = new ArrayList<Wave>();
	
	private TileMap map;
	
	private Mob m ;
	
	public LVLwaves (TileMap map){
		this.map = map;
		m = new Mob(loadTex("spiderDown"),map.getTile(1, 2),map,40,40,20,5);
		waveList.add(new Wave(5,m));
	}
	
	public void update(){
		
	}
}
