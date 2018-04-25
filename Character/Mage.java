package Character;

public class Mage extends PlayerCharacter{

	public Mage(String fileName, int h, int w, int hp, int xp) {
		super(/*Mage's fileName*/fileName, h, w, 20, "Mage name", 1, 15, 1, 2);
	}
	
	@Override
	public int lvUp()
	{
		super.lvUp();
		str++;
		con++;
		intel+=2;
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
			this.stun(targetArr, target);
			break;
		case 3:
			this.heal(targetArr, target);
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
	
	public void die()
	{
		//I actually have no idea what these numbers will be 
		this.grabFrame(30, 45);
	}
	
	
	private void stun(Character[] enemies, int target)
	{
		enemies[target].stunned = true;
	}
	
	private void attack(Character[] enemies, int target)
	{
		int dmg = (rand.nextInt(3) + 1) + intel;
		
		enemies[target].damage(dmg);
	}
	
	private void heal(Character[] allies, int target)
	{
		int heal = (rand.nextInt(6) + 3) + intel;
		
		allies[target].heal(heal);
	}
}


