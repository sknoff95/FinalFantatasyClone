package Character;

import SimpleFramework.Matrix3x3f;
import SimpleFramework.Vector2f;

public class Knight extends PlayerCharacter{
	
	private boolean defenseUp;
	
	public Knight() {
		super("src/Character/knight.png", 21, 30, "Steven the Knight", 1, 2, 1);
		setHp(45);
		setSp(10);
		grabFrame(4, 65);
		defenseUp = false;
	}

	@Override
	public int lvUp()
	{
		super.lvUp();
		str++; 
		con += 2;
		intel++;
		//Tentative hp upgrade equation
		hp = hp + con*2;
		
		return lv;		
	}
	
	@Override
	public void act(int action, Character target)
	{
		switch(action)
		{
		case -1:
			stunned = false;
			break;
		case 1:
			attack(target);
			break;
		case 3:
			buff();
			break;
		//Use health potion
		case 4:
			heal(25);
			break;
		//Use revive scroll
		case 5:
			if(!target.isAlive())
			{
				target.alive = true;
				target.heal(25);
			}
			break;
		}
	}
	
	@Override
	public void act(Character[] enemies)
	{
		groupAttack(enemies);
	}
	
	@Override
	public boolean damage(int dmg)
	{
		if(defenseUp)
			dmg /= 2;
		
		if(hp - dmg <= 0)
		{
			hp = 0;
			alive = false;
		}
		else
			hp -= dmg;
		
		return alive;
	}

	private void attack(Character target)
	{
		int dmg = (rand.nextInt(3) + 1) + str;
		
		target.damage(dmg);
	}
	
	private void groupAttack(Character[] enemies)
	{
		int dmg = 2 + str;
		
		for(int i = 0; i < enemies.length; i++)
			enemies[i].damage(dmg);
	}
	
	private void buff()
	{
		defenseUp = true;
	}
	
	@Override
	public void stepUp(Matrix3x3f viewport)
	{
		//Last two will change with knight scale
		transform(new Vector2f(2.0f, 0.0f), 0.0f, viewport, 2.0f, 2.0f);
	}
	
	@Override
	public void stepBack(Matrix3x3f viewport)
	{
		//Last two will change with knight scale
		transform(new Vector2f(-2.0f, 0.0f), 0.0f, viewport, 2.0f, 2.0f);
	}
}


