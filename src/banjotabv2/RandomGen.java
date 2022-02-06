package banjotabv2;

import java.util.ArrayList;
import java.util.Random;


public class RandomGen extends Generator {
	
	public RandomGen() {
		super();
	}
		
	
	public int choose(ArrayList<Integer> choices,int prevSt,int prevFret) {
		
		Random rand = new Random(); 
		int i = rand.nextInt(choices.size());  
		int count = 0;
		while(choices.get(i)==-1) {
			i = rand.nextInt(choices.size());
			count ++;
			if(count==1000) {
				//System.out.println("couldn't find it anywhere");
				break;
			}
		}
		return i;
		
	}
}
	
	