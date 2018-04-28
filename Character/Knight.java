package Character;

public class Knight extends PlayerCharacter{
	
	private boolean defenseUp;
	
	public Knight(int h, int w) {
		super("res2/knight.png", 21, 30, 45, "Steven the Knight", 1, 10, 2, 1);
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
	public void act(int action, Character[] targetArr, int target)
	{
		switch(action)
		{
		case -1:
			stunned = false;
			break;
		case 1:
			this.attack(targetArr, target);
			break;
		case 2:
			this.groupAttack(targetArr);
			break;
		case 3:
			this.buff();
			break;
		//Use health potion
		case 4:
			targetArr[target].heal(25);
			break;
		//Use revive scroll
		case 5:
			if(!targetArr[target].isAlive())
			{
				targetArr[target].alive = true;
				targetArr[target].heal(25);
			}
			break;
		}
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

	private void attack(Character[] enemies, int target)
	{
		int dmg = (rand.nextInt(3) + 1) + str;
		
		enemies[target].damage(dmg);
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
}


