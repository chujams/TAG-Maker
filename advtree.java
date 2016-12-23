//advtree.java
//Contains the adventure tree and its operations

import static java.lang.System.*;
import java.util.ArrayList;

class advtree{
	public static class Room {
		String name;
		ArrayList<String> desc = new ArrayList<String>();
		ArrayList<String> choices = new ArrayList<String>();
		ArrayList<String> choiceui = new ArrayList<String>();
		Room parent;
		ArrayList<Room> goesto = new ArrayList<Room>();
	}

	public static Room home;
	public static Room found;
	public static Room curPar;
	public static Room curLoc;
	public static String opt[] = new String[] {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l"};

	public static void updateDesc(Room here, String roomDesc){
		here.desc.add(roomDesc);
	}

	public static void updateChoice(Room here, String option){
      System.out.println("Adding option to: " + here.name);
		here.choices.add(option);
		here.choiceui.add(opt[here.choices.size()-1]);

	//	System.out.println("updateChoices is being called!");
	}

	public static void updateDest(Room here, String dname){
		//Room temp = findRoom(home, dname);
		//if(findRoom(home, dname) == true){
		findRoom(home, dname);
      Room temp = null;
		if(found != null){
         System.out.println("Found destination!");
			temp = found;
         found = null;
      }
		//}

	
		//if(!findRoom(home, dname)){
		else if(found == null){
			Room newRoom = new Room();
			newRoom.name = dname;
			newRoom.parent = here;
			//found = newRoom;
			temp = newRoom;
		}

		here.goesto.add(temp);
		found = null;
      //curLoc = home;

		
	}

	public static void displayRoom(Room here){
		//System.out.println(here.name);
		if (here == null) System.out.println("Error: Null Room!");

		for(int i=0; i<here.desc.size(); i++)
			System.out.println(here.desc.get(i));
		
		for(int i=0; i<here.choices.size(); i++) {
         System.out.println(here.choices.size());
			System.out.println(here.choiceui.get(i) + " - - - " + here.choices.get(i));	
      } 
	}

	public static void insert(String name){
		//Room temp = findRoom(home, name);

		//if(findRoom(home,name) == true){
		findRoom(home,name);
		
		if(found != null){
			curLoc = found;
			System.out.println("Found!");
         found = null;
			return;
		}

		//if(temp != null)
		//	return;

		Room newRoom = new Room();
		newRoom.name = name;
		//newRoom.desc = desc;

		if(home==null){
			home = newRoom;
			//curPar = home; 
			curLoc = home;
		}

		else{
			//newRoom.parent = curPar;
			newRoom.parent = curLoc;
			curLoc = newRoom;
		}
			

	}


	/*public static boolean findRoom(Room r, String pname){
		if(r == null)
			return false;
		
		if(pname == r.name){
			found = r;
			System.out.println("True");
			return true;
		}

		else{
			//for(Room child : r.goesto){
			for(int i=0; i<r.goesto.size(); i++){
				//return findRoom(child, pname);
				return findRoom(r.goesto.get(i), pname);
			}
		}
		System.out.println("False");
		return false;

	}	*/

	public static void findRoom(Room r, String pname){

		if(r != null){

         if(r.name.equals(pname)){
            found = r;
               return;

         }

         for(int i=0; i<r.goesto.size(); i++){
            if(r.goesto.get(i).name.equals(pname))
               found = r.goesto.get(i);
               return;
         }

			for(int i=0; i<r.goesto.size(); i++){
				findRoom(r.goesto.get(i), pname);
			}
		}

	}

	
	public static void printTree(Room r){
		if(home==null){
			System.out.println("Tree is empty!");
			return;
		}
		else if(r==null)
			return;
      
      if(r != null){
	   	System.out.println(r.name + ": ");
	   	for(int i=0; i<r.goesto.size(); i++){
		   	System.out.println(r.goesto.get(i) + " ");
            //printTree(r.goesto.get(i));
	   	}
		
      }

	}  	
}
