package banjotabv2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import banjotabv2.Generator.NoteNames;

public class Main {

	public static void main(String args[]) {
		Generator gen =new Generator();
		
		String fName=args[0];
		ArrayList<St> sts= new ArrayList<St>();
		
		St firstSt = new St(NoteNames.D,5);
		St secondSt = new St(NoteNames.B,4);
		St thirdSt = new St(NoteNames.G,4);
		St fourthSt = new St(NoteNames.D,4);
		FifthSt fifthSt = new FifthSt(NoteNames.G,5,true);
	
		
		sts.add(firstSt);
		sts.add(secondSt);
		sts.add(thirdSt);
		sts.add(fourthSt);	
		sts.add(fifthSt);

		
		gen.printSts(sts);
		
		try {
			Scanner scan = new Scanner(new File(fName));
			int count=0;
			while (scan.hasNext()) {
				String currentNote=scan.next();	//next note
				//System.out.println("got "+currentNote+"\n");
				ArrayList<Integer> choices = gen.getChoices(sts,currentNote);
				int stChoice = gen.choose(choices,gen.prevSt,gen.prevFret);
				gen.makeChoice( sts, choices, stChoice);
				
				count++;
				if(count%(8)==0) {
					gen.addBarline(sts);
				}
				if(count%30==0) {
					gen.printTab(sts);
					System.out.println("\n");
				}
			}
			gen.printTab(sts);
			System.out.println("\n");
			
			
			
		}
			catch(FileNotFoundException e){
				System.out.println("File Not Found");
			}
		
	
	}
}
