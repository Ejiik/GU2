package g2;

import java.util.*;

public class Enemy {
	private int hp, attackValue;
	Random rand = new Random();
	
	public Enemy(int hp) {
		this.hp = hp;
	}
	
	public Enemy(){
		this.hp = 300;
	}
	
	public void setHp(int hp) {
		this.hp -= hp;
	}
	
	public int getHp() {
		return hp;
	}
	
	public int attack() {
		attackValue = rand.nextInt(75);
		return attackValue;
	}
}
