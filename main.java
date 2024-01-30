import java.util.Scanner;

// Class to hold main game logic
public class main {
    /**
     * Method randomizes the room and furniture that will contain the key
     */
    public static void keyRandomizer(Furniture[] list) {
        int num = (int) (Math.random()*(double) list.length);
        list[num].setHasKey(true);
    }

    /**
     * Method randomizes room connections to mix up house layout
     */
    public static void roomRandomizer(Room[] list, House house) {
        //This is probably inefficient, I'll try to think of something more efficient
        int[] countList = {0,0,0,0,0,0};
        for(int i = 0; i < list.length; i++)
        {
            int numDoors = (int) (Math.random()*(double) 1) + 1;

            for(int j = 0; j < numDoors; j++)
            {
                int room = (int) (Math.random()* ((double) list.length));
                if(i == room)
                {
                    if(i == list.length - 1){
                        room--;
                    }
                    else
                    {
                        room++;
                    }
                }
                if(!house.checkValidPath(list[i], list[room]))
                {
                    house.addDoor(list[i], list[room]);
                    countList[i]++;
                    countList[room]++;
                }
            }
        }
    }

    /**
     * Method randomizes the player starting location in the house
     */
    public static void startPositionRandomizer() {
        // TODO: Extra feature if time permits
    }

    /**
     * Main function that controls game logic
     * @param args arguments
     */
    public static void main(String[] args) {
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
        Furniture coffeeTable = new Furniture(false, "Coffee Table");
        Furniture diningTable = new Furniture(false, "Dining Table");
        Furniture counter = new Furniture(false, "Counter");
        Furniture chair = new Furniture(false, "Chair");
        Furniture bed = new Furniture(false, "Bed");
        Furniture dresser = new Furniture(false, "Dresser");
        Furniture couch = new Furniture(false, "Couch");

        //Randomize Furniture
        Furniture[] allFurniture = {coffeeTable, diningTable, counter, chair, bed, dresser, couch};

        Scanner scan = new Scanner(System.in);
        
        //Ask user if they want to randomize
        //Could move these to main game loop, or just do away with the while loops
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
                couch.setHasKey(true);
                break;
            }
            else
            {
                System.out.println("Please enter a valid command");
            }
        }

        // Assign specific Furniture objects to a list and instantiates a Room object by passing in the list as a parameter
        Furniture[] listOfFurniture = {};
        listOfFurniture = new Furniture[]{counter, coffeeTable, chair};
        Room foyer = new Room("Foyer", listOfFurniture);
        listOfFurniture = new Furniture[]{couch, coffeeTable, chair};
        Room livingRoom = new Room("Living Room", listOfFurniture);
        listOfFurniture = new Furniture[]{counter, coffeeTable, diningTable};
        Room kitchen = new Room("Kitchen", listOfFurniture);
        listOfFurniture = new Furniture[]{counter};
        Room bathroom = new Room("Bathroom", listOfFurniture);
        listOfFurniture = new Furniture[]{bed, dresser, counter};
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

        // Add doors from one room to another (aka.edges) to House object

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

        // Print house layout
        house.printHouseLayout();

        // Game variables
        boolean keyFound = false;
        Room initialRoom = foyer;
        Room currentRoom = initialRoom;

        // While loop for game logic
        while (true) {
            // Tell player their current position
            System.out.println("Current Room: " + currentRoom.name);
            // Ask player where they want to go
            System.out.println("Choose Action: Move, Search, Look, or Quit?\n");

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
                    continue;
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
                    default:
                        break;
                }

                // Check if the furniture has the key
//                if()
//                {
//                    System.out.println("That is not in the current room.");
//                }
//                } else {
//                    // Continue to next iteration if key not found
//                    System.out.println("Key is not here!\n");
//                    continue;
//                }
            }
            else if(input.equalsIgnoreCase("look")){
                System.out.print("Furnature in " + currentRoom.name + ": ");
                for(Furniture f : currentRoom.furniture)
                {
                    System.out.print(f.getName() + ". ");
                }
                System.out.print("\n");
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


