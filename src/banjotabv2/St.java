package banjotabv2;

import java.util.HashMap;
import banjotabv2.Main.NoteNames;


public class St {
	
	private HashMap<String,Integer> map = new HashMap<String,Integer>();  
	String stringRep="";
	int octave;
	NoteNames open;

		
	public St(NoteNames openGiven, int octaveGiven) {
		map.put(openGiven+String.valueOf(octaveGiven), 0);
		stringRep= stringRep.concat(openGiven.toString()+octaveGiven+" ");
		this.octave=octaveGiven;
		this.open = openGiven;
		
		
		//build the rest of the fret board  
		int fret = 1;
		int currentNoteNum = openGiven.ordinal() + 1;   //change? to enum hashmap thing?
		int currentOctave = octaveGiven;
		
		while(fret<23) {
			if(currentNoteNum==12) {
				currentNoteNum=0;
				currentOctave++;
			}
			//Note current = new Note(NoteNames.values()[currentNoteNum],currentOctave);
			
			stringRep= stringRep.concat(NoteNames.values()[currentNoteNum]+String.valueOf(currentOctave)+" ");
			
			map.put(NoteNames.values()[currentNoteNum]+String.valueOf(currentOctave), fret);
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
	
	public int getFret(String n) throws NullPointerException {
		
		int ret = -1;
		try{
			ret = map.get(n);
		}catch(NullPointerException e) {
			//System.out.printf("%s was not found on this string\n",n);
		}
		return ret;
	}
	

}

