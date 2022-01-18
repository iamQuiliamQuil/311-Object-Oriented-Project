import java.util.ArrayList;

public abstract class CompositeMonster implements Monster {
  protected ArrayList<Monster> monsters;
  protected Type type;
  protected Inventory<AttackTemplate> attacks;

  public int getHealth(){
    int health = 0;
    for(Monster monster: monsters){
      health+=monster.getHealth();
    }
    return health;
  }

  public void attack(String attackName, Monster targetMonster) throws ItemNotFoundException, MonsterIsUnconsciousException{
    if(isUnconscious()){
      throw new MonsterIsUnconsciousException("This BasicMonster has fainted.");
    }
    //retrieve can throw ItemNotFoundException
    AttackTemplate attack = attacks.retrieve(attackName);
    for(Monster monster: monsters){
      try {
        monster.attack(attackName,targetMonster);
      } catch (ItemNotFoundException e){
        //if not all of the monsters have that attack,
        //those monsters simply won't use it
      }
    }
  }

  public void defend(AttackTemplate attack){
    if(!monsters.isEmpty()){
      Monster firstMonster = monsters.get(0);
      firstMonster.defend(attack);
      if (firstMonster.isUnconscious()){
        monsters.remove(0);
      }
    }
  }

  public Type getType(){
    return type;
  }

  public String[] getAttacks(){
    return attacks.currentItemNamesArray();
  }

  public Boolean isUnconscious(){
    if (monsters.isEmpty()){
      return true;
    } else {
      return false;
    }
  }

  public Boolean hasAttack(String attack){
    try {
      attacks.retrieve(attack);
    } catch (ItemNotFoundException e) {
      return false;
    }
    return true;
  }
}
