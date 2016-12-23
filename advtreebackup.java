//advtree.java
//Contains the adventure tree and its operations

import static java.lang.System.*;
import java.util.ArrayList;

class advtree{
	private static class Room {
		String name;
		String desc = "empty";
		String desc2 = "empty";
		String desc3 = "empty";
		String[] choices = new String[5];
		Room parent;
               /* Room childA;
		Room childB;
		Room childC;
		Room childD;
		Room childE;   */

		Room[] destinations = new Room[5];
	}

	public static Room home;
	public static Room curPar;
	public static Room curLoc;

	public static void updateDesc(Room here, String roomDesc){
		if(here.desc.equals("empty")){
			here.desc = roomDesc;
			return;
		}
			
		else if(here.desc2.equals("empty")){
			here.desc2 = roomDesc;
			return;		
		}

		else if(here.desc3.equals("empty")){
			here.desc3 = roomDesc;
			return;
		}
		
		if(!here.desc.equals("empty") && !here.desc2.equals("empty") &&
		   !here.desc3.equals("empty")){
			System.out.println("Error: Description slots full!");
		}	
	}

	public static void updateChoice(Room here, String option, int i){
		here.choices[i] = option;
	}

	//public static void updateDest(Room here,

	public static void displayRoom(Room here){
		System.out.println(here.name);
		System.out.println(here.desc);
		
		for(int i=0; i<here.choices.length; i++){
			System.out.println(here.choices[i]);
		}
		
	}

	public static void insert(String name){
		Room newRoom = new Room();
		newRoom.name = name;
		//newRoom.desc = desc;

		if(home==null){
			home = newRoom;
			curPar = home; 
			curLoc = home;
		}

		else{
			newRoom.parent = curPar;
			curLoc = newRoom;
		}
			
			
			/*boolean found = false;
			int checked = 42;

			while(!found){
				parent = current;
				if((pname.equals(current.name)))
					return;
				
				}
				checked = checkChild(current, pname);*/

	}

	public static int checkChild(Room r, String pname){
		int index = 42;
		for(int i=0; i<5; i++){
			if((pname.equals(r.destinations[i].name)))
				index = i;
			return index;
		}
		return index;
	}
	
	public static Room roomfinder(String pname){
		boolean found = false;
		int checked = 42;
		Room current = home;
		Room parent;

		if((pname.equals(curPar.name))) return curPar;

		while(!found){
			//parent = current;
			if((pname.equals(current.name))) 
				return current;

			checked = checkChild(current, pname);

			if(checked != 42)
				current = current.destinations[checked];
		}

		return current;
	}
}
