package Character;

import Sprite.*;
import java.util.Random;

public class Character extends Sprite{

	protected int hp, hpmax;
	protected int lv;
	protected String name;
	protected boolean alive;
	protected int str, sp, con, intel;
	protected Random rand;
	
	public Character(String fileName, int h, int w, int hp, int lv, String name, int str, int sp, int con, int intel) {
		super(fileName, h, w);
		
		this.hp = hpmax = hp;
		this.lv = lv;
		this.name = name;
		alive = true;
		this.str = str;
		this.sp = sp;
		this.con = con;
		this.intel = intel;
		
		rand = new Random();
	}

	public boolean damage(int dmg)
	{
		if(hp - dmg <= 0)
		{
			hp = 0;
			alive = false;
		}
		else
			hp -= dmg;
		
		return alive;
	}
	
	public void heal(int heal)
	{
		if(hp + heal >= hpmax)
			hp = hpmax;
		else
			hp += heal;
	}
	
	public String getName()
	{
		return name;
	}
}

