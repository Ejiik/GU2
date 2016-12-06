package g2;

public class SpelLogik {
	private Player playerOne = new Player();
	private Enemy enemyOne = new Enemy();
	static Spel prog = new Spel();
	
	public static void main(String[] args) {
		prog.run();
	}
	
	public void playerAttack() {
		int attackV = playerOne.attack();
		enemyOne.setHp(attackV);
		prog.setEnemyHp(attackV);
		prog.playerAttackText(attackV);
		checkHP();
		
	}
	
	public void playerDefend() {
		int attackV = enemyOne.attack();
		playerOne.setHp(attackV / 2);
		prog.playerDefendText(attackV / 2);
	}
	
	public void playerHeal() {
		if(playerOne.getMp() < 5) {
			System.out.println("Du har för lite magi!");
		} else {
		int healValue = playerOne.heal();
		prog.heal(healValue);
		prog.playerHpUpdate();
		prog.playerHealText(healValue);
		checkHP();
		}
	}
	
	public void enemyAttack() {
		int attackV = enemyOne.attack();
		playerOne.setHp(attackV);
		prog.setPlayerHp(attackV);
		prog.enemyAttackText(attackV);
		checkHP();
	}
	
	/*
	 * utgråad då den inte funkar utan att kladda text över text.
	 */
//	public void displayHP() {
//		int playerHP = playerOne.getHp();
//		prog.playerHPdisplay(playerHP);
//		int enemyHP = enemyOne.getHp();
//		prog.enemyHPdisplay(enemyHP);
//	}
	
	public void checkHP() {
		
		if(playerOne.getHp() > 300) {
			System.out.println(playerOne.getHp());
			playerOne.setCurrentHp(300);
			prog.setCurrentPlayerHp(300);
			System.out.println(playerOne.getHp());
		} else if(playerOne.getHp() < 0) {
			playerOne.setCurrentHp(0);
			prog.setCurrentPlayerHp(0);
		}
		
		if(playerOne.getHp() <= 0) {
			prog.gameOver();
		} else if (enemyOne.getHp() <= 0) {
			prog.gameWon();
		}
		
	}

}
