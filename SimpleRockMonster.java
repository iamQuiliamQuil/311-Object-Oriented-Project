public class SimpleRockMonster extends BasicMonster {
  SimpleRockMonster(){
    attacks = new Inventory<AttackTemplate>();
    try {
      attacks.add("Punch",Attacks.Punch.getPunch());
      attacks.add("Slash",Attacks.Slash.getSlash());
    } catch (ItemNameAlreadyExistsException e){
      //this never happens
      System.out.println(e);
    }

    maxHealth = 100;
    typeDamageVulnerability = 105;
    type = Type.ROCK;
    isUnconscious = false;
    health = maxHealth;
  }
}
