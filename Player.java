package g2;

import java.util.*;

public class Player {
	private int hp, attackValue, defenseValue, healValue, mp;
	Random rand = new Random();
	
	public Player(int hp, int mp) {
		this.hp = hp;
		this.mp = mp;
	}
	
	public Player(){
		this.hp = 300;
		this.mp = 25;
	}
	
	public void setHp(int hp) {
		this.hp -= hp;
	}
	
	public void setCurrentHp(int hp){
		this.hp = hp;
	}
	
	public int getHp() {
		return hp;
	}
	
	public void setMp(int mp) {
		this.mp = mp;
	}
	
	public int getMp() {
		return mp;
	}
	
	public int attack() {
		attackValue = rand.nextInt(50) + 5;
		return attackValue;
	}
	//Denna används inte just nu
	public int defend() {
		defenseValue = rand.nextInt(5);
		return defenseValue;
	}
	
	public int heal() {
		healValue = rand.nextInt(50) + 5;
		hp += healValue;
		mp -= 5;
		return healValue;
	}

}
