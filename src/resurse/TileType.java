package resurse;

public enum TileType {

	Grass("grass", true), Stone("stone", false), Tower("tower", false), Tower1("tower1", false), Tree1("tree1", false), Tree2("tree2", false), Tree3(
			"tree3", false), Tree4("tree4", false), Bush1("bush1", false), Bush2("bush2", false), Bush3("bush3", false), Bush4("bush4", false), Start(
			"start", false), Finish("finish", false);

	String texName;
	boolean buildable;

	TileType(String texName, boolean buildable) {
		this.texName = texName;
		this.buildable = buildable;
	}
}
