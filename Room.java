import java.util.ArrayList;
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
		if(connections == null)
		{
			connections = new ArrayList<Room>();
		}
		if(r.connections == null)
		{
			r.connections = new ArrayList<Room>();
		}

		if(!this.connections.contains(r.name) || !r.connections.contains((name)))
		{
			this.connections.add(r);
		}

	}

	public boolean hasTargetFurniture(String target)
	{
		boolean hasTarget = false;
		for (Furniture f : furniture)
		{
			if(f.getName() == target)
				hasTarget = true;
		}
		return hasTarget;
	}
	public String getName() {
        return name;
    }
    public boolean getVisit(){
        return visited;
    }
    public void setVisit(boolean update){
        visited = update;
    }
}
