package banjotabv2;

import java.util.ArrayList;

public class Main {
	
	public enum NoteNames{
		C,Db,D,Eb,E,F,Gb,G,Ab,A,Bb,B
	}
	
	//public String[] noteList = {"C","Db","D","Eb","E","F","Gb","G","Ab","A","Bb","B"};
	
	public static void main(String[] args){
		ArrayList<St> sts= new ArrayList<St>();
	
		St firstSt = new St(NoteNames.D,5);
		sts.add(firstSt);
		St secondSt = new St(NoteNames.B,4);
		sts.add(secondSt);
		St thirdSt = new St(NoteNames.G,4);
		sts.add(thirdSt);
		St fourthSt = new St(NoteNames.D,4);
		sts.add(fourthSt);		
		
		for(Integer fret: getChoices(sts,"E5")) {
			System.out.println(String.valueOf(fret));
		}
		

	}
	
	public static void printSts(ArrayList<St> sts) {
		for(St s:sts) {
			s.printSt();
		}
	}
	public static ArrayList<Integer> getChoices(ArrayList<St> sts,String note) {
		ArrayList<Integer> arr = new ArrayList<Integer>();
		for(St s:sts) {
			arr.add(s.getFret(note));
		}
		return arr;
	}
}
