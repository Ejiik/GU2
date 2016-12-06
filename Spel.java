package g2;

import java.awt.*;
import java.util.*;
import javax.swing.*;

import gu2.PaintWindow_GU2;
import paintpanel.Text;

public class Spel {
	private PaintWindow_GU2 window = new PaintWindow_GU2(800,480,"RPG",Color.DARK_GRAY);
	private ImageIcon attackIcon = new ImageIcon("imagesGU2/atkBtn.png");
	private ImageIcon defendIcon = new ImageIcon("imagesGU2/defBtn.png");
	private ImageIcon healIcon = new ImageIcon("imagesGU2/healBtn.png");
	private ImageIcon playerIcon = new ImageIcon("imagesGU2/playerIcon.png");
	private ImageIcon enemyIcon = new ImageIcon("imagesGU2/enemyIcon.png");
	private ImageIcon blackSquare = new ImageIcon("imagesGU2/svartruta.png");
	private ImageIcon playerAtkIcon = new ImageIcon("imagesGU2/atkIcon.png");
	private ImageIcon enemyAtkIcon = new ImageIcon("imagesGU2/atkEnemyIcon.png");
	private ImageIcon fightIcon = new ImageIcon("imagesGU2/fight.png");
	private Font font1 = new Font("Arial", Font.ITALIC, 25);
	private SpelLogik logik = new SpelLogik();
	
	
	private int playerHpX = 300;
	private int enemyHpX = 300;
	
	
	public void run() {
		window.fillRect(0, 100, 800, 202, Color.GRAY);
		window.addIcon(attackIcon, 50, 380, true);
		window.addIcon(defendIcon, 350, 380, true);
		window.addIcon(healIcon, 555, 380, true);
		window.addIcon(playerIcon, 50, 90, true);
		window.addIcon(enemyIcon, 600, 15, true);
		window.fillRect(450, 350, playerHpX, 20, Color.GREEN);
		window.fillRect(100, 50, enemyHpX, 20, Color.GREEN);
		window.setIconAction(attackIcon, logik, "playerAttack");
		window.setIconAction(defendIcon, logik, "playerDefend");
		window.setIconAction(healIcon, logik, "playerHeal");
		
		window.addIcon(fightIcon, 800, 150, true);
		while(window.getIconX(fightIcon) > -200){
			window.setIconXY(fightIcon, window.getIconX(fightIcon) - 5, window.getIconY(fightIcon));
			window.pause(10);
		}
		
	}
	
	public void playerAttackText(int attackValue) {
		Text attackscore = new Text(String.valueOf(attackValue), font1, Color.GREEN);
		Text critical = new Text("Critical hit!", font1, Color.GREEN);
		Text glancing = new Text("Glancing blow!", font1, Color.GREEN);
		window.addIcon(playerAtkIcon, 145, 200, true);
		while(window.getIconX(playerAtkIcon) < 630){
			window.setIconXY(playerAtkIcon, window.getIconX(playerAtkIcon) + 5, window.getIconY(playerAtkIcon) - 1);
			window.pause(10);
		}
		window.removeIcon(playerAtkIcon);
		enemyHpUpdate();
		if(attackValue > 45){
			window.addText(critical, 300, 170);
		} else if( attackValue < 15){
			window.addText(glancing, 300, 170);
		}
		window.addText(attackscore, 300, 200);
		window.pause(1000);
		window.removeIcon(attackscore);
		window.removeIcon(critical);
		window.removeIcon(glancing);
		window.pause(1000);
		logik.checkHP();
		logik.enemyAttack();
//		logik.displayHP();
		
	}
	
	
	/*
	 * tagit bort logik.enemyAttack, då denna fick enemy att attackera två ggr i rad.
	 */
	public void playerDefendText(int attackValue) {
		Text defendscore = new Text(String.valueOf(attackValue), font1, Color.RED);
		window.addText(defendscore, 300, 200);
		window.pause(1000);
		window.removeIcon(defendscore);
		window.pause(1000);
//		logik.enemyAttack();  
//		logik.displayHP();
		logik.checkHP();
	}
	
	public void playerHealText(int healValue) {
		Text healpoints = new Text(String.valueOf(healValue), font1, Color.BLUE);
		window.addText(healpoints, 200, 200);
		window.pause(1000);
		window.removeIcon(healpoints);
		window.pause(1000);
		logik.checkHP();
		logik.enemyAttack();
//		logik.displayHP();
	}
	
	public void enemyAttackText(int attackValue) {
		Text attackscore = new Text(String.valueOf(attackValue), font1, Color.RED);
		Text critical = new Text("Critical hit!", font1, Color.RED);
		Text glancing = new Text("Glancing blow!", font1, Color.RED);
		window.addIcon(enemyAtkIcon, 590, 70, true);
		while(window.getIconX(enemyAtkIcon) > 90){
			window.setIconXY(enemyAtkIcon, window.getIconX(enemyAtkIcon) - 5, window.getIconY(enemyAtkIcon) + 1);
			window.pause(10);
		}
		window.removeIcon(enemyAtkIcon);
		playerHpUpdate();
		if(attackValue > 45){
			window.addText(critical, 300, 170);
		} else if( attackValue < 15){
			window.addText(glancing, 300, 170);
		}
		window.addText(attackscore, 300, 200);
		window.pause(1000);
		window.removeIcon(glancing);
		window.removeIcon(critical);
		window.removeIcon(attackscore);
//		logik.displayHP();
		logik.checkHP();
	}
	
//	public void playerHPdisplay(int playerHP) {
//		Text playerhits = new Text(String.valueOf(playerHP), font1, Color.WHITE);
//		window.addText(playerhits, 450, 327);
//		
//	}
//	
//	public void enemyHPdisplay(int enemyHP) {
//		Text enemyhits = new Text(String.valueOf(enemyHP), font1, Color.WHITE);
//		window.addText(enemyhits, 100, 27);
//		
//	}
	
	public void gameOver() {
		Text gameover = new Text("Du dog!", font1, Color.WHITE);
		window.addText(gameover, 250, 200);
		window.removeIcon(playerIcon);
		window.pause(2000);
		window.clearAll();
	}
	
	public void gameWon() {
		Text gameWon = new Text("Du besegrade!", font1, Color.WHITE);
		window.addText(gameWon, 250, 200);
		window.removeIcon(enemyIcon);
		window.pause(2000);
		window.clearAll();
	}
	
	public void setPlayerHp(int playerHp){
		this.playerHpX -=  playerHp;
	}
	
	public void heal(int healAmount){
		this.playerHpX += healAmount;
	}
	
	public void setCurrentPlayerHp(int hp){
		this.playerHpX = hp;
	}
	
	public void setEnemyHp(int enemyHp){
		this.enemyHpX -= enemyHp;
	}
	
	public int getPlayerHp(){
		return this.playerHpX;
	}
	
	public int getEnemyHp(){
		return this.enemyHpX;
	}
	
	public void playerHpUpdate(){
		window.fillRect((450 + playerHpX), 350, (300 - playerHpX), 20, Color.RED);
		window.fillRect(450, 350, playerHpX, 20, Color.GREEN);
		window.fillRect(750, 350, 100, 100, Color.DARK_GRAY);
	}
	
	public void enemyHpUpdate(){
		window.fillRect((100 + enemyHpX), 50, (300 - enemyHpX), 20, Color.RED);
		window.fillRect(100, 50, enemyHpX, 20, Color.GREEN);
	}
	
	
	
	

	
}
