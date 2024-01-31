import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

// Class to represent a house which the form of an undirected graph
public class House {
    private final Map<Room, List<Room>> adjacencyList;

    /***
     * Class constructor
     */
    public House() {
        adjacencyList = new HashMap<>();
    }

    /***
     * Method to add a room (vertex) to the house (graph)
     * @param room the room to be added
     */
    public void addRoom(Room room) {
        adjacencyList.put(room, new LinkedList<>());
    }

    /***
     * Method to add a door (edge) between two rooms (vertices)
     * @param sourceRoom the first room
     * @param targetRoom the second room
     */
    public void addDoor(Room sourceRoom, Room targetRoom) {
        // Add from source to target
        adjacencyList.get(sourceRoom).add(targetRoom);

        // Add from target to source too since graph is undirected
        adjacencyList.get(targetRoom).add(sourceRoom);

        sourceRoom.connect(targetRoom);
        targetRoom.connect((sourceRoom));
    }

    /***
     * Method checks if there is a door between two rooms to provide a valid path
     * @param sourceRoom the first room
     * @param targetRoom the second room
     */
    public boolean checkValidPath (Room sourceRoom, Room targetRoom) {
        try {
            return adjacencyList.get(sourceRoom).contains(targetRoom);
        } catch (Exception e) {
            return false;
        }
    }

    /***
     * Method that returns a furniture object from a specified room of a house
     * @param room
     * @param furniture
     * @return
     */
    public Furniture getFurniture(Room room, Furniture furniture) {
        // TODO
        return null;
    }

    /***
     * Method to print layout of the house
     */
    public void printHouseLayout() {
        System.out.println("House Layout:\n");
        for (Map.Entry<Room, List<Room>> entry : adjacencyList.entrySet()) {
            System.out.print(entry.getKey().name + " is connected to: ");
            for (Room neighbor : entry.getValue()) {
                System.out.print("(" + neighbor.name + ")" + " ");
            }
            System.out.println();
        }
        System.out.println();
    }


}