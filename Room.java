import java.util.List;

public class Room{
	public String name;
	public boolean visited;
	public Furniture[] furniture;
    public List<Room> connections;

	public Room(String name, Furniture[] obj){
		this.name = name;
		this.visited = false;
		this.furniture = obj;
	}
	public void connect(Room r){
		this.connections.add(r);
		r.connections.add(this);
	}
}
