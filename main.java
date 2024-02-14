import java.util.Scanner;

// Class to hold main game logic
public class main {
    /***
     * Method randomizes the room and furniture that will contain the key
     * @param list list of Furniture
     */
    public static void keyRandomizer(Furniture[] list) {
        int num = (int) (Math.random()* ((double) list.length));
        //list[num].setHasKey(true);
    }

    /***
     * Method randomizes room connections to mix up house layout
     * @param list list of Rooms
     * @param house House object to mix up layout
     */
    public static void roomRandomizer(Room[] list, House house) {
        //This is probably inefficient, I'll try to think of something more efficient
        for(int i = 1; i < list.length; i++)
        {
            house.addDoor(list[i], list[i-1]);
        }

        for(int i = 0; i < list.length; i++)
        {
            int numDoors = (int) (Math.random()) + 1;

            for(int j = 0; j < numDoors; j++)
            {
                int room = (int) (Math.random()* ((double) list.length));
                while(i == room)
                {
                    room = (int) (Math.random() * ((double) list.length));
                }
                if(!house.checkValidPath(list[i], list[room]))
                {
                    house.addDoor(list[i], list[room]);
                }
            }
        }
    }

    /***
     * Method randomizes the player starting location in the house
     * @param list list of Rooms
     */
    public static Room startPositionRandomizer(Room[] list) {
        int num = (int) (Math.random()* ((double) list.length));
        return list[num];
    }

    /**
     * Main function that controls game logic
     * @param args arguments
     */
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        /* Introduction Text */

        System.out.println("  ______                            _____                         _______        _                 _                 _                  \n" +
                " |  ____|                          |  __ \\                       |__   __|      | |       /\\      | |               | |                 \n" +
                " | |__   ___  ___ __ _ _ __   ___  | |__) |___   ___  _ __ ___      | | _____  _| |_     /  \\   __| |_   _____ _ __ | |_ _   _ _ __ ___ \n" +
                " |  __| / __|/ __/ _` | '_ \\ / _ \\ |  _  // _ \\ / _ \\| '_ ` _ \\     | |/ _ \\ \\/ / __|   / /\\ \\ / _` \\ \\ / / _ \\ '_ \\| __| | | | '__/ _ \\\n" +
                " | |____\\__ \\ (_| (_| | |_) |  __/ | | \\ \\ (_) | (_) | | | | | |    | |  __/>  <| |_   / ____ \\ (_| |\\ V /  __/ | | | |_| |_| | | |  __/\n" +
                " |______|___/\\___\\__,_| .__/ \\___| |_|  \\_\\___/ \\___/|_| |_| |_|    |_|\\___/_/\\_\\\\__| /_/    \\_\\__,_| \\_/ \\___|_| |_|\\__|\\__,_|_|  \\___|\n" +
                "                      | |                                                                                                               \n" +
                "                      |_|                                                                                                               ");
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("PARAGRAPH EXPLAINING THE GAME AND RULES:");
        System.out.println("- You are in a house.");
        System.out.println("- You have forgotten where you placed your keys.");
        System.out.println("- You need to find your keys before leaving.");
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("   _____                 _   _                _    _ \n" +
                "  / ____|               | | | |              | |  | |\n" +
                " | |  __  ___   ___   __| | | |    _   _  ___| | _| |\n" +
                " | | |_ |/ _ \\ / _ \\ / _` | | |   | | | |/ __| |/ / |\n" +
                " | |__| | (_) | (_) | (_| | | |___| |_| | (__|   <|_|\n" +
                "  \\_____|\\___/ \\___/ \\__,_| |______\\__,_|\\___|_|\\_(_)\n" +
                "                                                     \n" +
                "                                                     ");
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------");

        /* Start of Game */

        // Instantiates a House object which has the data structure of an undirected graph
        House house = new House();

        // Instantiates various Furniture objects
        Furniture coffeeTable = new Furniture(false, false, false, "Coffee Table");
        Furniture diningTable = new Furniture(false, false, false, "Dining Table");
        Furniture counter = new Furniture(false, false, false, "Counter");
        Furniture chair = new Furniture(false, false, false, "Chair");
        Furniture bed = new Furniture(false, false, false, "Bed");
        Furniture dresser = new Furniture(false, false, false, "Dresser");
        Furniture couch = new Furniture(false, false, false, "Couch");
        Furniture toilet = new Furniture(false, false, false, "Toilet");
        Furniture lamp = new Furniture(false,  false, false, "Lamp");
        Furniture fridge = new Furniture(false,  false, false, "Fridge");
        Furniture[] allFurniture = {coffeeTable, diningTable, counter, chair, bed, dresser, couch, toilet, lamp, fridge};

        // Assign specific Furniture objects to a list and instantiates a Room object by passing in the list as a parameter
        Furniture[] listOfFurniture = {};
        listOfFurniture = new Furniture[]{counter, coffeeTable, chair};
        Room foyer = new Room("Foyer", listOfFurniture);
        listOfFurniture = new Furniture[]{couch, coffeeTable, chair, lamp};
        Room livingRoom = new Room("Living Room", listOfFurniture);
        listOfFurniture = new Furniture[]{counter, coffeeTable, diningTable, fridge};
        Room kitchen = new Room("Kitchen", listOfFurniture);
        listOfFurniture = new Furniture[]{counter, toilet};
        Room bathroom = new Room("Bathroom", listOfFurniture);
        listOfFurniture = new Furniture[]{bed, dresser, counter, lamp};
        Room bedroomOne = new Room("Bedroom One", listOfFurniture);
        listOfFurniture = new Furniture[]{bed, dresser};
        Room bedroomTwo = new Room("Bedroom Two", listOfFurniture);

        // Add Room objects to House object
        house.addRoom(foyer);
        house.addRoom(livingRoom);
        house.addRoom(kitchen);
        house.addRoom(bathroom);
        house.addRoom(bedroomOne);
        house.addRoom(bedroomTwo);
        Room[] allRooms = {foyer, livingRoom, kitchen, bathroom, bedroomOne, bedroomTwo};

        // Ask user if they want to randomize the player's start position
        Room currentRoom;
        while(true)
        {
            System.out.println("Would you like to randomize the player starting position? (Yes/No)");
            String input = scan.nextLine();

            // Check the action the player selected
            if(input.equalsIgnoreCase("yes"))
            {
                currentRoom = startPositionRandomizer(allRooms);
                break;
            }
            else if(input.equalsIgnoreCase("no"))
            {
                currentRoom = foyer;
                break;
            }
            else
            {
                System.out.println("Please enter a valid command");
            }
        }

        // Ask user if they want to randomize the room locations
        while(true)
        {
            System.out.println("Would you like to randomize the room locations? (Yes/No)");
            String input = scan.nextLine();

            // Check the action the player selected
            if(input.equalsIgnoreCase("yes"))
            {
                roomRandomizer(allRooms, house);
                break;
            }
            else if(input.equalsIgnoreCase("no"))
            {
                // Add doors from one room to another (aka.edges) to House object
                house.addDoor(foyer, kitchen);
                house.addDoor(foyer, livingRoom);
                house.addDoor(livingRoom, bathroom);
                house.addDoor(livingRoom, kitchen);
                house.addDoor(kitchen, bedroomOne);
                house.addDoor(kitchen, bedroomTwo);
                break;
            }
            else
            {
                System.out.println("Please enter a valid command");
            }
        }

        // Ask user if they want to randomize the key location
        while(true)
        {
            System.out.println("Would you like to randomize the key location? (Yes/No)");
            String input = scan.nextLine();

            // Check the action the player selected
            if(input.equalsIgnoreCase("yes"))
            {
                keyRandomizer(allFurniture);
                break;
            }
            else if(input.equalsIgnoreCase("no"))
            {
                toilet.setHasKey(2);
                break;
            }
            else
            {
                System.out.println("Please enter a valid command");
            }
        }

        // Print house layout
        house.printHouseLayout();

        // While loop for game logic
        boolean keyFound = false;
        while (true) {
            // Tell player their current position
            System.out.println("Current Room: " + currentRoom.name);
            // Ask player where they want to go
            System.out.println("Choose Action: Move, Search, Look, Leave, or Quit?\n");

            // Grab players input
            String input = scan.nextLine();

            // Check the action the player selected
            if (input.equalsIgnoreCase("move")) {
                System.out.println("Move to ...\n");
                // Convert player input to Room object
                input = scan.nextLine();
                Room targetRoom = null;
                switch (input) {
                    case "Foyer":
                    case "foyer":
                        targetRoom = foyer;
                        break;
                    case "Living Room":
                    case "living room":
                        targetRoom = livingRoom;
                        break;
                    case "Kitchen":
                    case "kitchen":
                        targetRoom = kitchen;
                        break;
                    case "Bathroom":
                    case "bathroom":
                        targetRoom = bathroom;
                        break;
                    case "Bedroom One":
                    case "bedroom one":
                        targetRoom = bedroomOne;
                        break;
                    case "Bedroom Two":
                    case "bedroom two":
                        targetRoom = bedroomTwo;
                        break;
                    default:
                        break;
                }

                // Check if the room player wants to move to is a valid path
                if(house.checkValidPath(currentRoom, targetRoom)) {
                    // Update current room if valid
                    currentRoom = targetRoom;
                } else {
                    // Continue to next iteration if not valid
                    System.out.println("Please enter a valid room!\n");
                }
            }
            else if (input.equalsIgnoreCase("search")) {
                System.out.println("Searching ...\n");
                // Convert player input to Furniture object
                input = scan.nextLine();
                Furniture targetFurniture = null;
                switch (input) {
                    case "Coffee Table":
                    case "coffee table":
                        targetFurniture = coffeeTable;
                        break;
                    case "Dining Table":
                    case "dining table":
                        targetFurniture = diningTable;
                        break;
                    case "Counter":
                    case "counter":
                        targetFurniture = counter;
                        break;
                    case "Chair":
                    case "chair":
                        targetFurniture = chair;
                        break;
                    case "Bed":
                    case "bed":
                        targetFurniture = bed;
                        break;
                    case "Dresser":
                    case "dresser":
                        targetFurniture = dresser;
                        break;
                    case "Couch":
                    case "couch":
                        targetFurniture = couch;
                        break;
                    case "Toilet":
                    case "toilet":
                    	targetFurniture = toilet;
                    	break;
                    case "Lamp":
                    case "lamp":
                    	targetFurniture = lamp;
                    	break;
                    case "Fridge":
                    case "fridge":
                    	targetFurniture = fridge;
                    	break;
                    default:
                        break;
                }

                // Check if the furniture has the key
                if(targetFurniture == null || !currentRoom.hasTargetFurniture(targetFurniture.getName()))
                {
                    System.out.println("That is not in the current room.");
                }
                else
                {
                	int search = -1;
                	System.out.println("Where on the object do you want search?\n	[T] On Top of It\n	[I] Inside It \n	[B] Beneath It");
                	input = scan.nextLine();
                	switch (input) {
                    case "T":
                    case "t":
                        search = 1;
                        break;
                    case "I":
                    case "i":
                        search = 2;
                        break;
                    case "B":
                    case "b":
                        search = 3;
                        break;
                	}
                	if(targetFurniture.hasKey(search))
                    {
                        System.out.println("Key is here!");
                        System.out.println("You Win!\n");
                        break;
                    }
                    else {
                        // Continue to next iteration if key not found
                        System.out.println("Key is not here!\n");
                    }
                }
                
            }
            else if(input.equalsIgnoreCase("look")){
                System.out.print("Furniture in " + currentRoom.name + ": ");
                for(Furniture f : currentRoom.furniture)
                {
                    System.out.print(f.getName() + ". ");
                }
                System.out.print("\n");
                System.out.print("Connections in " + currentRoom.name + ": ");
                for(Room r : currentRoom.connections)
                {
                    System.out.print(r.name + ". ");
                }
                System.out.print("\n");
            }
            else if(input.equalsIgnoreCase("leave")){
                System.out.println("Are you sure you want to leave the house? You will be late to work! Y/N");
                String confirm = scan.nextLine();
                if(confirm.equalsIgnoreCase("Y")){
                    System.out.println("You left the house without your car keys, and ended up late to work. You were written up. Don't be late again!");
                    break;
                }
                else if(confirm.equalsIgnoreCase("N")){
                    System.out.println("You decide to stay in the house to look for your car keys some more.\n");
                }
                else{
                    System.out.println("Please enter a valid action!\n");
                }
            }
            else if (input.equalsIgnoreCase("quit")) {
                System.out.println("You gave up! Thanks for playing!\n");
                break;
            } else {
                System.out.println("Please enter a valid action!\n");
            }
        }
    }
}


