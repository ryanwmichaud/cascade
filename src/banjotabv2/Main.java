package banjotabv2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.TreeMap;

public class Main {
	
	
	public enum NoteNames{
		C,Db,D,Eb,E,F,Gb,G,Ab,A,Bb,B
	}
	
	//public String[] noteList = {"C","Db","D","Eb","E","F","Gb","G","Ab","A","Bb","B"};
	
	public static void main(String[] args){
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
		
		
		
		try {
			Scanner scan = new Scanner(new File(fName));
			int count=0;
			while (scan.hasNext()) {
				String currentNote=scan.next();	//next note
				//System.out.println("got "+currentNote+"\n");
				ArrayList<Integer> choices = getChoices(sts,currentNote);
				int stChoice = choose(choices);
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
	
	public static int choose(ArrayList<Integer> choices) {
		Random rand = new Random();   
		return rand.nextInt(4);
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
