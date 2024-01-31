Escape Room Text Adventure

Game Synopsis: 
- Escape room-esque idea where you must find the keys somewhere in a house before you are able to leave and therefore "win" the game. 
- Rooms in a house is represented by an undirected graph (kitchen, living room, foyer, bathroom, dining room, bedrooms, basement).
- Each room has an array with furnitures to inspect such as couch, chair, etc.
- Player spawns in a room to start and will have the choice between actions of Move, Search, Look, or Quit.
   - Move: Select a valid room to enter.
   - Search: Check a piece of furniture for the key.
   - Look: List of all furniture in a room will be printed to the player.
   - Quit: Game will end.
 - Win Condition: Game ends when a player successfully finds the key or if they choose to quit the game. 

House Class: Graph of rooms in the house.
- adjacencyList: Map<Room, List<Room>>
- House(): constructor of HashMap called adjacencyList
- addRoom(): puts new room in HashMap
- addDoor(): adds an edge between two vertices(rooms)
- checkValidPath(): returns true/false if there is an edge between two vertices
- getFurniture(): *** should this be in room, instead of house? ***
- printHouseLayout(): prints each room in the house and the rooms it connects to

Room class: Each room in the house is an instance of this
- name: name of the room
- visited: bool if the room has been visited
- Furniture[]: list of furniture objects that exist in that Room
- Connections[]: list of references to the Room objects (vertices that have an edge to this one)
- Room(): Constructor of Room object 
- connect(Room r): adds r to this Connections list and adds this Room to r Connections List

Furniture class: Each object in a room is an instance of this
- hasKey: boolean if key is with this object
- hasBeenLookedAt: boolean if player has inspected the object
- name: name of furniture
- Furniture(): constructor of Furniture object
- getName(): returns name
- setLook(): sets hasBeenLookedAt
- getLook(): returns hasBeenLookedAt
- hasKey(): returns hasKey variable

Main class:
- utilizes other classes along with its own utility functions for main game logic
  
Room Class by Jillian Desmond

Objects Class, Randomization, and Search features by Kristopher Bruno

House Class, Main Class, and Randomization features by Fabio Tran

Saerch for Key feature by Nilay Patel
