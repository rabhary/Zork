package com.bayviewglen.zork;
/**
 * Class Command - Part of the "Zork" game.
 * 
 * author:  Michael Kolling
 * version: 1.0
 * date:    July 1999
 *
 * This class holds information about a command that was issued by the user.
 * A command currently consists of two strings: a command word and a second
 * word (for example, if the command was "take map", then the two strings
 * obviously are "take" and "map").
 * 
 * The way this is used is: Commands are already checked for being valid
 * command words. If the user entered an invalid command (a word that is not
 * known) then the command word is <null>.
 *
 * If the command had only one word, then the second word is <null>.
 *
 * The second word is not checked at the moment. It can be anything. If this
 * game is extended to deal with items, then the second part of the command
 * should probably be changed to be an item rather than a String.
 */

// test commit

class Command
{
	private Object bag;
	private String commandWord;
    private String secondWord;
    private Room previousRoom;
    private Room currentRoom;


    /**
     * Create a command object. First and second word must be supplied, but
     * either one (or both) can be null. The command word should be null to
     * indicate that this was a command that is not recognised by this game.
     */
    public Command(String firstWord, String secondWord)
    {
        commandWord = firstWord;
        this.secondWord = secondWord;
    }

    /**
     * Return the command word (the first word) of this command. If the
     * command was not understood, the result is null.
     */
    public String getCommandWord()
    {
        return commandWord;
    }

    /**
     * Return the second word of this command. Returns null if there was no
     * second word.
     */
    public String getSecondWord()
    {
        return secondWord;
    }

    /**
     * Return true if this command was not understood.
     */
    public boolean isUnknown()
    {
        return (commandWord == null);
    }

    /**
     * Return true if the command has a second word.
     */
    public boolean hasSecondWord()
    {
        return (secondWord != null);
    }
    
    
    private void back(Command command)
    {
	Room nextRoom = previousRoom;
    
    if(nextRoom == null)
    System.out.println("You haven't gone anywhere yet!");
    else
    {
    currentRoom = nextRoom;
    
    System.out.println(currentRoom.getDescription());
    }
    
    }
   // private void printInventory(Command command){
    //	 System.out.println("You have: ");
		//if (bag.getNumItems() ==0){
    	 //System.out.println("nothing");
    	// }else{
    	 //for (int i=0; i<bag.getNumItems(); i++){
    	 //Item currentItem = bag.getInventory().get(i);
    	 //if (currentItem!=null)
    	 //System.out.println(currentItem.getDescription());
    	 //}
    	 //}
    	}
//}

