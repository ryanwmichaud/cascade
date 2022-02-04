package banjotabv2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


public class Generator {
	
	int prevFret;
	int prevSt ;
	
	public enum NoteNames{
		C,Db,D,Eb,E,F,Gb,G,Ab,A,Bb,B
	}
	
	public Generator() {
		prevSt=-100;
		prevFret=0;
	}
	
	
		
	public static void main(String[] args){
		
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

		
		printSts(sts);
		
		try {
			Scanner scan = new Scanner(new File(fName));
			int count=0;
			while (scan.hasNext()) {
				String currentNote=scan.next();	//next note
				//System.out.println("got "+currentNote+"\n");
				ArrayList<Integer> choices = getChoices(sts,currentNote);
				int stChoice = gen.choose(choices,gen.prevSt,gen.prevFret);
				makeChoice( sts, choices, stChoice);
				
				count++;
				if(count%(8)==0) {
					addBarline(sts);
				}
				if(count%30==0) {
					printTab(sts);
					System.out.println("\n");
				}
			}
			printTab(sts);
			System.out.println("\n");
			
			
			
		}
			catch(FileNotFoundException e){
				System.out.println("File Not Found");
			}
		
		
	}
		
	
	/*
		for(Integer fret: getChoices(sts,"E5")) {
			System.out.println(String.valueOf(fret));
		}
	*/
		
	
	
	public static void printSts(ArrayList<St> sts) {
		for(St s:sts) {
			s.printSt();
		}
		System.out.println("\n");
	}
	public static void printTab(ArrayList<St> sts) {
		for(St s:sts) {
			s.printStTab();
		}
	}
	public static void addBarline(ArrayList<St> sts) {
		for(St s:sts) {
			s.barline();
		}
	}
	
	public static ArrayList<Integer> getChoices(ArrayList<St> sts,String note) {
		ArrayList<Integer> arr = new ArrayList<Integer>();
		for(St s:sts) {
			arr.add(s.getFret(note));
		}
		return arr;
	}
	
	public int choose(ArrayList<Integer> choices,int prevSt,int prevFret) {
		int choice = -100;
		int iClosest = -100;
		int smallestDist = 100;
		int dist;
		
		for(int i=0;i<choices.size();i++) { //look at every string
			
			dist= choices.get(i)-prevFret;  //get the dist
			if(dist<0) {
				dist*=-1;
			}
			
			//System.out.println("index is "+i+" fret is "+choices.get(i) +" dist to prev ("+prevFret+") is " + dist);
	
			if(prevSt!=i && choices.get(i)==0) {  //if its new string and open, done
				iClosest=i;
				break;
			}
			if(prevSt!=i && choices.get(i)!=-1 && dist<smallestDist) { //get closest on new string
				smallestDist=dist;
				iClosest = i;
			}
		}
		if(iClosest==-100) {  //if not found anywhere, choose prev string
			iClosest=prevSt;  //it'll be a double up or a rest "X" if it truly is not anywhere
		}
	
		choice = iClosest;
		//System.out.println("we went with "+choices.get(choice)+" on index "+ choice+ " dist of "+smallestDist);
		
		this.prevSt = choice;
		if(choices.get(choice)!=0) {  //playing open strings doesn't require a shift
			this.prevFret = choices.get(choice);
		}
		return choice;

	}
	
	public static void makeChoice(ArrayList<St> sts, ArrayList<Integer> choices, int strChoice) {
		int i = 0;
		for(St s:sts) {
			//*****************************
			
			if(i==strChoice) {
				s.choose(choices.get(i));
			}else {
				s.notChoose();
			}
			
			//*****************************
			/*
			if(choices.get(i)!=-1) {
				s.choose(choices.get(i));
			}else {
				s.notChoose();
			}
			*/
			//******************************
			i++;
		}
	}

}
