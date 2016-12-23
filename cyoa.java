//cyoa.java
//a test-based "game engine" that reads in an adventure file with specific format
//and generates a choose-your-own-adventure game

import static java.lang.System.*;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileReader;

class cyoa {
	public static void main(String[] args) throws IOException  {
		if(args.length<1){
			System.out.println("Usage: cyoa file.adventure");
			System.out.println("Please specify an adventure file to begin a new game.");
		}
		else{
			//Store filename of adventure
			String info = args[0];
			BufferedReader in = new BufferedReader(new FileReader(info));
			BufferedReader in2 = new BufferedReader(new FileReader(info));
			
			String line;
			int unp = 0;
			
			//Count the number of lines
			while((line = in.readLine()) != null){
				unp++;
			}
			
			//Array size = number of lines
         String[] unparsed = new String[unp];
			       
			//Assign each line to an array index
			int unp2 = 0;

			while((line = in2.readLine()) != null){
				if (line.isEmpty() || line.equals("") || line.equals("\n")){
					 unparsed[unp2] = "skip123 skip123";
					unp2++;
				} else {
					unparsed[unp2] = line;
					unp2++;
				}
			}

		//	arrayPrint(unparsed);
			
			//split unparsed array into two arrays: tag[] holds the letter tag and content[] holds the description
			String[] tag = new String[unp];
			String[] content = new String[unp];
	
			for(int i=0; i<unp2; i++){
				String[] temp = unparsed[i].split(" ", 2);
		//		System.out.println(temp[0] + " and " + temp[1]);
				tag[i] = temp[0];
				content[i] = temp[1];
			}
			
			
			
			buildTree(tag, content);
	
			playGame(info);		
		}
	


	}

	static void arrayPrint(String[] array){
	//prints contents of array - for debugging purposes
		for(int i=0; i<array.length; i++){
			System.out.println(array[i]);
		}
	}

	static void buildTree(String[] tag, String[] content){
	//Builds the adventure tree
		if(tag.length != content.length){
			System.out.println("Error: Mismatched number of tags and contents");
			System.exit(1);
		}	

		for(int i=0; i<tag.length; i++){
      //   advtree.found=null;
			if(tag[i].equals("r"))
				advtree.insert(content[i]);

			if(tag[i].equals("d")){
				//System.out.println(tag[i] + " " + content[i]);
		//		System.out.println(advtree.curLoc.desc);
				advtree.updateDesc(advtree.curLoc, content[i]);
           // advtree.displayRoom(advtree.curLoc);
            System.out.println("Adding desc |" + content[i] + "| to " + advtree.curLoc.name);
			}


			if(tag[i].equals("o")){
            System.out.println("Adding option: " + content[i]);
				advtree.updateChoice(advtree.curLoc, content[i]);
			}
			
			if(tag[i].equals("t")){
				advtree.updateDest(advtree.curLoc, content[i]);
            System.out.println("Adding destination to " + advtree.curLoc.name);
         }
				continue;
		}

	}

	static void playGame(String info){
	//Implement scanner to allow user input!
		System.out.println("\nWelcome! You are playing: " + info + ".");
		System.out.println("To quit the game, press q. ");
		System.out.println("To display debug info, press y. ");
		System.out.println("To restart the adventure, press r.");
		System.out.println("To undo your last choice, press z.");
		System.out.println("You will use a-l to make your choices once the adventure begins.");
		System.out.println("--------------------------------------------------------------------------");

		BufferedReader pl = new BufferedReader(new InputStreamReader(System.in));
	
	   advtree.curLoc = advtree.home;	

      input:
		while(true){
         System.out.println("\n");
			advtree.displayRoom(advtree.curLoc);			
			System.out.println("\n");

			try {
            String ui = pl.readLine();
		
				if(ui.equals("q")) System.exit(0);

				//if(ui.length()>1) System.out.println("Invalid command!");
	
				else if(ui.equals("r")){
					System.out.println("Restarting...\n");
					advtree.curLoc = advtree.home;
					continue;
				}

				else if(ui.equals("z")){
					
					if(advtree.curLoc.parent == null){
						System.out.println("Can't undo!\n");
					}
					else if(advtree.curLoc.parent != null){
						System.out.println("Undoing last choice...\n");
						advtree.curLoc = advtree.curLoc.parent;
					}
					continue;
				}

				else if(ui.equals("y")){
					advtree.printTree(advtree.home.goesto.get(0));
					continue;
				}

	         for(int i=0; i<advtree.curLoc.choiceui.size();i++){
                if(ui.equals(advtree.curLoc.choiceui.get(i))){
                    advtree.curLoc = advtree.curLoc.goesto.get(i);
                    System.out.println("[" + advtree.curLoc.choices.get(i) + "]");
						  continue input;
					}
				}

				System.out.println("Invalid command!");
			
			} catch(java.io.IOException e) { System.out.println("Error!"); }

		}
		
	}
}
