package banjotabv2;

import banjotabv2.Main.NoteNames;

public class Note {
	private int octave;
	private NoteNames noteName;
	private String name;

	public Note(NoteNames noteGiven, int octaveGiven) {
        this.noteName = noteGiven;
        this.octave = octaveGiven;
        
        this.name = String.format("%s%d",noteName,octave);
    }
	
	public String getName() {
		return name;
	}
	public NoteNames getNoteName() {
		return noteName;
	}
	public int getOctave() {
		return octave;
	}
}

