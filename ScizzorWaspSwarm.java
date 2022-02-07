import java.util.ArrayList;

public class ScizzorWaspSwarm extends CompositeMonster {

  private static class ScizzorWasp extends BasicMonster{
    ScizzorWasp(){
      attacks = new Inventory<AttackTemplate>();
      try {
        attacks.add("Sting",Attacks.Sting.getSting());
        attacks.add("Buzz",Attacks.Buzz.getBuzz());
      } catch (ItemNameAlreadyExistsException e){
        //this never happens
        System.out.println(e);
      }

      maxHealth = 40;
      typeDamageVulnerability = 10;
      type = Type.SCIZZORS;
      isUnconscious = false;
      health = maxHealth;
    }
  }

  ScizzorWaspSwarm(){
    monsters = new ArrayList<Monster>();
    for(int i = 0; i < 3; i++){
      monsters.add(new ScizzorWasp());
    }
    type = Type.SCIZZORS;
    attacks = new Inventory<AttackTemplate>();
    try {
      attacks.add("Sting",Attacks.Sting.getSting());
      attacks.add("Buzz",Attacks.Buzz.getBuzz());
    } catch (ItemNameAlreadyExistsException e){
      //this never happens
      System.out.println(e);
    }
  }
}
