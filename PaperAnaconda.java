import java.util.Random;

public class PaperAnaconda extends BasicMonster{
  protected int rageCounter;

  @Override
  protected void takeDamage(int damage){
    super.takeDamage(damage);
    if(rageCounter < 5){
      rageCounter++;
    }
  }

  //PaperAnaconda gets better at dodging the more damage it takes
  @Override
  public void defend(AttackTemplate attack){
    Random random = new Random();

    int damage = attack.baseDamage;
    damage += random.nextInt(attack.randDamageRange+1);
    if (attack.typeBeats(type)){
      damage += typeDamageVulnerability;
    }
    if (random.nextInt(10)>(attack.accuracyOutOf10-rageCounter)){
      damage = 0;
    }
    takeDamage(damage);
  }

  PaperAnaconda(){
    attacks = new Inventory<AttackTemplate>();
    try {
      attacks.add("Strangle",Attacks.Strangle.getStrangle());
      attacks.add("Fall On",Attacks.FallOn.getFallOn());
    } catch (ItemNameAlreadyExistsException e){
      //this never happens
      System.out.println(e);
      e.printStackTrace();
    }

    maxHealth = 80;
    typeDamageVulnerability = 5;
    type = Type.PAPER;
    isUnconscious = false;
    health = maxHealth;
    int rageCounter = 0;
  }
}
