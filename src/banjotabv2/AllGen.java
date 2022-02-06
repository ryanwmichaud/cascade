package banjotabv2;


import java.util.ArrayList;


public class AllGen extends Generator{
	
	public AllGen() {
		super();
	}
	
		
	
	public void makeChoice(ArrayList<St> sts, ArrayList<Integer> choices, int strChoice) {
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
	
	
	
