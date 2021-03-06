package com.bayviewglen.zork;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Class Game - the main class of the "Zork" game.
 *
 * Author: Michael Kolling Version: 1.1 Date: March 2000
 * 
 * This class is the main class of the "Zork" application. Zork is a very
 * simple, text based adventure game. Users can walk around some scenery. That's
 * all. It should really be extended to make it more interesting!
 * 
 * To play this game, create an instance of this class and call the "play"
 * routine.
 * 
 * This main class creates and initialises all the others: it creates all rooms,
 * creates the parser and starts the game. It also evaluates the commands that
 * the parser returns.
 */

class Game {
	private int gameNumber = 0;
	private Parser parser;
	private Room currentRoom;
	private Inventory inv;
	// This is a MASTER object that contains all of the rooms and is easily
	// accessible.
	// The key will be the name of the room -> no spaces (Use all caps and
	// underscore -> Great Room would have a key of GREAT_ROOM
	// In a hashmap keys are case sensitive.
	// masterRoomMap.get("GREAT_ROOM") will return the Room Object that is the
	// Great Room (assuming you have one).
	private HashMap<String, Room> masterRoomMap;

	private void initRooms(String fileName) throws Exception {
		masterRoomMap = new HashMap<String, Room>();
		Scanner roomScanner;
		try {
			HashMap<String, HashMap<String, String>> exits = new HashMap<String, HashMap<String, String>>();
			roomScanner = new Scanner(new File(fileName));
			while (roomScanner.hasNext()) {
				Room room = new Room();
				// Read the Name
				String roomName = roomScanner.nextLine();
				room.setRoomName(roomName.split(":")[1].trim());
				// Read the Description
				String roomDescription = roomScanner.nextLine();
				room.setDescription(roomDescription.split(":")[1].replaceAll("<br>", "\n").trim());
				// Read the Exits
				String roomExits = roomScanner.nextLine();
				// An array of strings in the format E-RoomName
				String[] rooms = roomExits.split(":")[1].split(",");
				HashMap<String, String> temp = new HashMap<String, String>();
				for (String s : rooms) {
					temp.put(s.split("-")[0].trim(), s.split("-")[1]);
				}

				exits.put(roomName.substring(10).trim().toUpperCase().replaceAll(" ", "_"), temp);

				// This puts the room we created (Without the exits in the
				// masterMap)
				masterRoomMap.put(roomName.toUpperCase().substring(10).trim().replaceAll(" ", "_"), room);

				// Now we better set the exits.
			}

			for (String key : masterRoomMap.keySet()) {
				Room roomTemp = masterRoomMap.get(key);
				HashMap<String, String> tempExits = exits.get(key);
				for (String s : tempExits.keySet()) {
					// s = direction
					// value is the room.

					String roomName2 = tempExits.get(s.trim());
					Room exitRoom = masterRoomMap.get(roomName2.toUpperCase().replaceAll(" ", "_"));
					roomTemp.setExit(s.trim().charAt(0), exitRoom);

				}

			}

			roomScanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the game and initialise its internal map.
	 */
	public Game() {
		try {
			inv = new Inventory();
			initRooms("data/Rooms.dat");
			currentRoom = masterRoomMap.get("OUTSIDE_OF_FENCE.");

			Room tempRoom = masterRoomMap.get("GARDENERS_SHED");
			Inventory inv = tempRoom.getInventory();
			inv.addItem(new Item("pick-axe"));

			tempRoom = masterRoomMap.get("LEAF_PILE");
			inv = tempRoom.getInventory();
			inv.addItem(new Item("crowbar"));

			tempRoom = masterRoomMap.get("SEWER_ENTRANCE");
			inv.addItem(new Item("ENEMY"));

			tempRoom = masterRoomMap.get("FRONT_ENTRANCE");
			inv.addItem(new Item("ENEMY"));

			tempRoom = masterRoomMap.get("OPEN_FIELD");
			inv = tempRoom.getInventory();
			inv.addItem(new Item("bagWithKey"));
			inv.addItem(new Item("ENEMY"));

			tempRoom = masterRoomMap.get("CLEARING");

			tempRoom = masterRoomMap.get("GUARD_SHED");
			inv = tempRoom.getInventory();
			inv.addItem(new Item("spear"));

			tempRoom = masterRoomMap.get("DEAD_END");
			inv.addItem(new Item("ENEMY"));

			tempRoom = masterRoomMap.get("ARMORY");
			inv = tempRoom.getInventory();
			inv.addItem(new Item("sword"));

			tempRoom = masterRoomMap.get("MAIN_DOOR");
			inv = tempRoom.getInventory();
			inv.addItem(new Item(""));

			tempRoom = masterRoomMap.get("WINDOW");

			tempRoom = masterRoomMap.get("RIDDLER'S_GATE");
			inv.addItem(new Item("ENEMY"));

			tempRoom = masterRoomMap.get("SEWER_EXIT");

			tempRoom = masterRoomMap.get("KITCHEN");
			inv = tempRoom.getInventory();
			inv.addItem(new Item("spork"));

			tempRoom = masterRoomMap.get("MAIN_HALLWAY");
			inv = tempRoom.getInventory();
			inv.addItem(new Item("painting"));
			inv.addItem(new Item("ENEMY"));

			tempRoom = masterRoomMap.get("LIVING_ROOM");
			inv = tempRoom.getInventory();
			inv.addItem(new Item("candlestick"));
			inv.addItem(new Item("ENEMY"));

			tempRoom = masterRoomMap.get("STAIRS");

			tempRoom = masterRoomMap.get("UPSTAIRS HALLWAY");

			tempRoom = masterRoomMap.get("END_OF_HALLWAY");

			tempRoom = masterRoomMap.get("MASTER_BEDROOM");

			tempRoom = masterRoomMap.get("GUEST_BEDROOM");

			tempRoom = masterRoomMap.get("BATHROOM");
			inv = tempRoom.getInventory();
			inv.addItem(new Item("hairdryer"));

			tempRoom = masterRoomMap.get("DIDDY_KONG");
			inv.addItem(new Item("DIDDYKONG"));

			tempRoom = masterRoomMap.get("GHOST_HALL");
			inv.addItem(new Item("ENEMY"));

			tempRoom = masterRoomMap.get("MUSIC_HALL");
			inv = tempRoom.getInventory();
			inv.addItem(new Item("bagpipes"));
			inv.addItem(new Item("ENEMY"));

			tempRoom = masterRoomMap.get("GRAND_HALL");

			tempRoom = masterRoomMap.get("DINING_ROOM");

			tempRoom = masterRoomMap.get("FUN_HALL");
			inv = tempRoom.getInventory();
			inv.addItem(new Item("baseballBat"));

			tempRoom = masterRoomMap.get("MAIN_ARMORY");
			inv = tempRoom.getInventory();
			inv.addItem(new Item("rifle"));

			tempRoom = masterRoomMap.get("LUIGI'S_CELL");

			// =======
			// >>>>>>> refs/remotes/origin/master
		} catch (Exception e) {
			e.printStackTrace();
		}
		parser = new Parser();
	}

	/**
	 * Main play routine. Loops until end of play.
	 */
	public void play() {
		printWelcome();

		// Enter the main command loop. Here we repeatedly read commands and
		// execute them until the game is over.

		boolean finished = false;
		while (!finished) {
			Command command = parser.getCommand();
			finished = processCommand(command);
		}
		System.out.println("You couldn't save Luigi. You're a horrible brother.");
		System.out.println("Thanks for playing!");

	}

	/**
	 * Print out the opening message for the player.
	 */
	private void printWelcome() {
		System.out.println();
		System.out.println("Hey Mario!");
		System.out.println("Ya boi Luigi's been captured!");
		System.out.println("You gotta kill Diddy Kong to get him back.");
		System.out.println("Go now!");
		System.out.println("Type 'help' if you need help.");
		System.out.println();
		System.out.println(currentRoom.longDescription());
	}

	/**
	 * Given a command, process (that is: execute) the command. If this command
	 * ends the game, true is returned, otherwise false is returned.
	 */
	private boolean processCommand(Command command) {
		if (command.isUnknown()) {
			System.out.println("I don't know what you mean...");
			return false;
		}

		String commandWord = command.getCommandWord();
		if (commandWord.equals("help"))
			printHelp();
		else if (commandWord.equals("go"))
			return goRoom(command);
		else if (commandWord.equals("quit")) {
			if (command.hasSecondWord())
				System.out.println("Quit what?");
			else
				return true; // signal that we want to quit
		} else if (commandWord.equals("eat")) {
			System.out.println("Do you really think you should be eating at a time like this?");
		} else if (commandWord.equals("inventory")) {
			printInventory(command);

		} else if (commandWord.equals("n")) {
			command = new Command("go", "north");
			goRoom(command);
		} else if (commandWord.equals("s")) {
			command = new Command("go", "south");
			goRoom(command);
		} else if (commandWord.equals("e")) {
			command = new Command("go", "east");
			goRoom(command);
		} else if (commandWord.equals("w")) {
			command = new Command("go", "west");
			goRoom(command);
		} else if (commandWord.equals("u")) {
			command = new Command("go", "up");
			goRoom(command);
		} else if (commandWord.equals("d")) {
			command = new Command("go", "down");
			goRoom(command);
		} else if (commandWord.equals("grab")) {
			grab(command);

		} else if (commandWord.equals("look")) {
			look();
		} else if (commandWord.equals("search")) {
			search(command);
		}
		return false;
	}

	private void printInventory(Command command) {
		inv.print();
	}

	private void search(Command command) {
		String secondWord = command.getSecondWord();
		if (currentRoom.getRoomName().equals("Leaf Pile")) {
			System.out.println("you searched the leaf pile and found a crowbar");
		}

	}

	private void look() {

		System.out.println(currentRoom.longDescription());
	}

	private void grab(Command command) {
		String takeWhat = command.getSecondWord();
		Item i = currentRoom.getInventory().contains(takeWhat);
		if (i == null)
			System.out.println("There is no" + takeWhat + "in this room");
		else {
			currentRoom.getInventory().removeItem(i);
			inv.addItem(i);
		}
	}


	/**
	 * Print out some help information. Here we print some stupid, cryptic
	 * message and a list of the command words.
	 */
	private void printHelp() {
		System.out.println("Your commands are:");
		parser.showCommands();
	}

	/**
	 * Try to go to one direction. If there is an exit, enter the new room,
	 * otherwise print an error message.
	 */
	private boolean goRoom(Command command) {
		if (!command.hasSecondWord()) {
			// if there is no second word, we don't know where to go...
			System.out.println("Go where?");
			return false;
		}

		String direction = command.getSecondWord();

		// Try to leave current room.
		Room nextRoom = currentRoom.nextRoom(direction);

		if (nextRoom == null)
			System.out.println("There is no door!");
		else {
			currentRoom = nextRoom;
			System.out.println(currentRoom.longDescription());

			// you just entered a new room
			// check room for enemy
			// if enemy present, attack
			Item enemy = currentRoom.getInventory().contains("ENEMY");
			if (enemy != null) {
				System.out.println(
						"Press 'Enter' to begin combat with the enemy - keep in mind if you loose the game is over! Be cautious.");
				if (Combat.combat()) {
					// attack, if you lose game over, if you win game continues
					System.out.println("You rolled a " + Combat.playerAttack + " and the enemy rolled a "
							+ Combat.enemyAttack + "!");
					System.out.println("You killed the enemy. Advance to the next room.");
					return false;
				} else {
					System.out.println("You rolled a " + Combat.playerAttack + " and the enemy rolled a "
							+ Combat.enemyAttack + "!");
					System.out.println("You just died, hold this L.");
					gameNumber = 1;
					return true;
				}

			}
		}
		return false;
	}

}
