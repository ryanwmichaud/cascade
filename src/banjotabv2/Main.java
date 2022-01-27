package banjotabv2;

public class Main {
	
	public enum NoteNames{
		C,Db,D,Eb,E,F,Gb,G,Ab,A,Bb,B
	}
	
	//public String[] noteList = {"C","Db","D","Eb","E","F","Gb","G","Ab","A","Bb","B"};
	
	public static void main(String[] args){ 
		St firstSt = new St(new Note(NoteNames.D,5));
		St secondSt = new St(new Note(NoteNames.B,4));
		St thirdSt = new St(new Note(NoteNames.G,4));
		St fourthSt = new St(new Note(NoteNames.D,4));
		firstSt.printSt();
		secondSt.printSt();
		thirdSt.printSt();
		fourthSt.printSt();
		
		//System.out.printf("%d",firstSt.getFret(new Note(NoteNames.B,5)));

	}
}
