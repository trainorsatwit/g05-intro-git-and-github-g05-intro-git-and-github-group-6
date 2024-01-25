public class Room{
	public String name;
	public boolean visited;
	public Object[] objects;
	public Room[] connections;
	
	public Room(Sting name, Object[] obj){
		this.name = name;
		this.visited = False;
		this.objects = obj;
		this.connections = [];
	}

	public static void connect(Room r){
		this.connections.add(r);
		r.connections.add(this);
	}
}
