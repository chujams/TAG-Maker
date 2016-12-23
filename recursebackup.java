//advtree.java
//Contains the adventure tree and its operations

import static java.lang.System.*;
import java.util.ArrayList;

class advtree{
	private static class Room {
		String name;
		ArrayList<String> desc = new ArrayList<String>();
		ArrayList<String> choices = new ArrayList<String>();
		Room parent;
               /* Room childA;
		Room childB;
		Room childC;
		Room childD;
		Room childE;   */

		ArrayList<Room> goesto = new ArrayList<Room>();
	}

	public static Room home;
	public static Room curPar;
	public static Room curLoc;
	public static String opt[] = new String[] {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l"};
	/*public static void updateDesc(Room here, String roomDesc){
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
	}*/

	public static void updateDesc(Room here, String roomDesc){
		here.desc.add(roomDesc);
	}

	public static void updateChoice(Room here, String option){
		here.choices.add(option);
	}

	public static void updateDest(Room here, String dname){
		Room temp = findRoom(home, dname);
		
		if(temp == null){
			Room newRoom = new Room();
			newRoom.name = dname;
			newRoom.parent = here;
		}

			here.goesto.add(temp);

		
	}

	public static void displayRoom(Room here){
		System.out.println(here.name);
		for(int i=0; i<here.desc.size(); i++)
			System.out.println(here.desc.get(i));
		
		for(int i=0; i<here.choices.size(); i++)
			System.out.println(opt[i] + " - - - " + here.choices.get(i));	
	}

	public static void insert(String name){
		Room temp = findRoom(home, name);

		if(temp != null)
			return;

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
			if((pname.equals(r.goesto.get(i))))
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
				current = current.goesto.get(checked);
		}

		return current;
	}

	public static Room findRoom(Room r, String pname){
		if(r == null){
			System.out.println("Loop?");
			return home;	
		}
	
		if(r.name.equals(pname))
			return r;
	
		if(r.goesto.isEmpty() == false){	
			for(Room child : r.goesto){
				findRoom(child, pname);
			}
		}
		return null;
	}	
}
