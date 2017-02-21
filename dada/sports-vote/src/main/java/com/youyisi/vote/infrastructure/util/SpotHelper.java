package com.youyisi.vote.infrastructure.util;

import java.util.Random;


public class SpotHelper {
	
	
	public static Boolean spot(int probability) {
		 Random random = new Random();
		 int r = random.nextInt(100);
		 
		 if(r<probability){
			return true; 
		 }
		return false;
		
	}
	
	public static void main(String[] args) {
		int d = 0;
		 for(int index=0;index<100;index++){
			 if(spot(20)){
				d++;
			 }
			
		 }
		 System.out.println(d);
	}
	
	
	public static Integer random(int max) {
		 Random random = new Random();
		 return random.nextInt(max);
	}
	

}
