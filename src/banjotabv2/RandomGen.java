package banjotabv2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;


public class RandomGen extends Generator {
	
	public RandomGen() {
		super();
	}
	
	public static void main(String[] args){
		
		RandomGen gen =new RandomGen();
			
		String fName=args[0];
		ArrayList<St> sts= new ArrayList<St>();
		
		St firstSt  = new St(Generator.NoteNames.E,5);
		St secondSt = new St(Generator.NoteNames.B,4);
		St thirdSt  = new St(Generator.NoteNames.G,4);
		St fourthSt = new St(Generator.NoteNames.D,4);
		St fifthSt  = new St(Generator.NoteNames.A,3);
		St sixthSt = new St(Generator.NoteNames.E,3);
	
		
		sts.add(firstSt);
		sts.add(secondSt);
		sts.add(thirdSt);
		sts.add(fourthSt);	
		sts.add(fifthSt);
		sts.add(sixthSt);

		
		printSts(sts);
		
		try {
			Scanner scan = new Scanner(new File(fName));
			int count=0;
			while (scan.hasNext()) {
				String currentNote=scan.next();	//next note
				//System.out.println("got "+currentNote+"\n");
				if(currentNote.equals("break")) {
					printTab(sts);
					System.out.println("\n");
					continue;
				}
				ArrayList<Integer> choices = getChoices(sts,currentNote);
				int stChoice = gen.choose(choices,gen.prevSt,gen.prevFret);
				makeChoice( sts, choices, stChoice);
				
				count++;
				if(count%(8)==0) {
					addBarline(sts);
				}
			/*	if(count%100==0 ) {
					printTab(sts);
					System.out.println("\n");
				}
				*/
			}
			printTab(sts);
			
			
			
		}
			catch(FileNotFoundException e){
				System.out.println("File Not Found");
			}
		
		
	}
		
	
	public int choose(ArrayList<Integer> choices,int prevSt,int prevFret) {
		
		Random rand = new Random(); 
		int i = rand.nextInt(6);  
		int count = 0;
		while(choices.get(i)==-1) {
			i = rand.nextInt(6);
			count ++;
			if(count==1000) {
				//System.out.println("couldn't find it anywhere");
				break;
			}
		}
		return i;
		
	}
}
	
	