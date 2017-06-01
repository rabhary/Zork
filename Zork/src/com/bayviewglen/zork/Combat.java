package com.bayviewglen.zork;

public class Combat {

	public static int playerAttack = 0;
	public static int enemyAttack = 0;
	
	public static boolean combat(){
		
		
		
		while (playerAttack == enemyAttack){		
			playerAttack = (int) (Math.random()*10);
			enemyAttack = (int) (Math.random()*10);
			if (playerAttack > enemyAttack){
				return true;
			}else if (playerAttack < enemyAttack){
				return false;
			}
		}
		
		return false;
		
	}
	
}
