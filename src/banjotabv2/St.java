package banjotabv2;

import java.util.HashMap;
import banjotabv2.Main.NoteNames;


public class St {
	
	private HashMap<Note,Integer> map = new HashMap<Note,Integer>();  
	Note open;
	String stringRep="";
		
	public St(Note openGiven) {
		this.open = openGiven;
		map.put(openGiven, 0);
		stringRep= stringRep.concat(this.open.getName()+" ");

		
		//build the rest of the fretboard  
		int fret = 1;
		int currentNoteNum = this.open.getNoteName().ordinal() + 1;   //change? to enum hashmap thing?
		int currentOctave = this.open.getOctave();
		
		while(fret<23) {
			if(currentNoteNum==12) {
				currentNoteNum=0;
				currentOctave++;
			}
			Note current = new Note(NoteNames.values()[currentNoteNum],currentOctave);
			stringRep= stringRep.concat(current.getName()+" ");
			
			map.put(current, fret);
			fret++;
			currentNoteNum++;
		}
	}
	
	
	public void printSt(){
		/*for(Note current:map.keySet()) {
			System.out.printf("%d for %s\n",map.get(current), current.getName());
		}*/
		System.out.println(stringRep);
	}
	
	public int getFret(Note n) {
		return map.get(n);
	}
	

}

