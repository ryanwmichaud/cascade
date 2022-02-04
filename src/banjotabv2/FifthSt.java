package banjotabv2;

import banjotabv2.Generator.NoteNames;

public class FifthSt extends St {

	public FifthSt(NoteNames d, int octaveGiven, boolean fifth) {
		super(d, octaveGiven, fifth);
		stringRep= stringRep.concat("                 ");	
		buildFretBoard();
	}
	
	
	private void buildFretBoard()  {
		//build the rest of the fret board  
		int fret = 1;
		int currentNoteNum = this.open.ordinal()+1 ;   //change? to enum hashmap thing?
		int currentOctave = this.octave;
		
		while(fret<=5) {	
			fret++;
		}
		
		while(fret<22) {
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

}
