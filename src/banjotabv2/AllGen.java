package banjotabv2;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


public class AllGen extends Generator{
	
	public AllGen() {
		super();
	}
public static void main(String[] args){
		
		AllGen gen =new AllGen();
			
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
		
	
	public static void makeChoice(ArrayList<St> sts, ArrayList<Integer> choices, int strChoice) {
		int i = 0;
		for(St s:sts) {

			if(choices.get(i)!=-1) {
				s.choose(choices.get(i));
			}else {
				s.notChoose();
			}
		i++;
		
		}

	}
}
	
	
	
