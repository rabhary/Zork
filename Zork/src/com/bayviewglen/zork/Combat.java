package com.bayviewglen.zork;

public class Combat {

	public boolean combat(){
		
		int playerAttack = 0;
		int enemyAttack = 0;
		
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
