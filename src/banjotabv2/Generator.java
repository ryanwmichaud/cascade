package banjotabv2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;


public class Generator {
	
	int prevFret;
	int prevSt ;
	
	public enum NoteNames{
		C,Db,D,Eb,E,F,Gb,G,Ab,A,Bb,B
	}
	
	public Generator() {
		prevSt=0;
		prevFret=0;
	}
	
	
		
	public static void main(String[] args){
		
		Generator gen =new Generator();
			
		String fName=args[0];
		ArrayList<St> sts= new ArrayList<St>();
		
		St firstSt = new St(NoteNames.E,5);
		St secondSt = new St(NoteNames.B,4);
		St thirdSt = new St(NoteNames.G,4);
		St fourthSt = new St(NoteNames.D,4);
		St fifthSt = new St(NoteNames.A,3);
		St sixthSt = new St(NoteNames.E,3);
	
		
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
				ArrayList<Integer> choices = getChoices(sts,currentNote);
				int stChoice = gen.choose(choices,gen.prevSt,gen.prevFret);
				makeChoice( sts, choices, stChoice);
				
				count++;
				if(count%100==0) {
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
	}
	public static void printTab(ArrayList<St> sts) {
		for(St s:sts) {
			s.printStTab();
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
		
		for(int i=0;i<choices.size();i++) {
			
			dist= choices.get(i)-prevFret;
			if(dist<0) {
				dist*=-1;
			}
			
			System.out.println("index is "+i+" fret is "+choices.get(i) +" dist to prev ("+prevFret+") is " + dist);
	
			if(prevSt!=i && choices.get(i)==0) {
				iClosest=i;
				break;
			}
			if(prevSt!=i && choices.get(i)!=-1 && dist<smallestDist) {
				smallestDist=dist;
				iClosest = i;
			}
		}
		if(iClosest==-100) {
			iClosest=0;
		}
	
		choice = iClosest;
		System.out.println("we went with "+choices.get(choice)+" on index "+ choice);
		this.prevSt = choice;
		this.prevFret = choices.get(choice);
		return choice;
		/*
		Random rand = new Random(); 
		int i = rand.nextInt(6);  
		int count = 0;
		while(choices.get(i)==-1) {
			i = rand.nextInt(6);
			count ++;
			if(count==1000) {
				System.out.println("couldn't find it anywhere");
				break;
			}
		}
		return i;
		*/
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
