import java.util.Random;

public  abstract class BasicMonster implements Monster {
  protected int health;
  protected int maxHealth;
  protected int typeDamageVulnerability;
  protected Type type;
  protected Inventory<AttackTemplate> attacks;
  protected Boolean isUnconscious;

  public int getHealth(){
    return health;
  }

  public void attack(String attackName, Monster targetMonster) throws ItemNotFoundException, MonsterIsUnconsciousException{
    if(isUnconscious){
      throw new MonsterIsUnconsciousException("This BasicMonster has fainted.");
    }
    //retrieve can throw ItemNotFoundException
    AttackTemplate attack = attacks.retrieve(attackName);
    targetMonster.defend(attack);
  }

  //returns damage sustained
  public void defend(AttackTemplate attack){
    Random random = new Random();

    int damage = attack.baseDamage;
    damage += random.nextInt(attack.randDamageRange+1);
    if (attack.typeBeats(type)){
      damage += typeDamageVulnerability;
    }
    if (random.nextInt(10)>(attack.accuracyOutOf10-1)){
      damage = 0;
    }
    takeDamage(damage);
  }

  public String[] getAttacks(){
    return attacks.currentItemNamesArray();
  }

  public Type getType(){
    return type;
  }

  protected void takeDamage(int damage){
    health -= damage;
    if (health <= 0){
      faint();
    }
  }

  public void faint(){
    health = 0;
    isUnconscious = true;
  }

  public Boolean isUnconscious(){
    return isUnconscious;
  }

  public Boolean hasAttack(String attack){
    try {
      attacks.retrieve(attack);
    } catch (ItemNotFoundException e) {
      return false;
    }
    return true;
  }

  BasicMonster(){
  }
}
