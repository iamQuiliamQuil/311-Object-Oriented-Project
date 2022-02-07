public class SuperRockMonster extends BasicMonster {

  private class BonusAttack extends AttackTemplate {
    BonusAttack(){
      baseDamage = 5;
      type = Type.ROCK;
      randDamageRange = 0;
      accuracyOutOf10 = 10;
    }
  }

  //SuperRockMonster gets to inflict a bonus unavoidable 5 damage when they use a rock attack
  @Override
  public void attack(String attackName, Monster targetMonster) throws ItemNotFoundException, MonsterIsUnconsciousException{
    if(isUnconscious){
      throw new MonsterIsUnconsciousException("This BasicMonster has fainted.");
    }
    //retrieve can throw ItemNotFoundException
    AttackTemplate attack = attacks.retrieve(attackName);
    targetMonster.defend(attack);
    if ((!targetMonster.isUnconscious()) && attack.type == Type.ROCK){
      targetMonster.defend(new BonusAttack());
    }
  }

  SuperRockMonster(){
    attacks = new Inventory<AttackTemplate>();
    try {
      attacks.add("Punch",Attacks.Punch.getPunch());
      attacks.add("Slash",Attacks.Slash.getSlash());
      attacks.add("Kick",Attacks.Kick.getKick());
    } catch (ItemNameAlreadyExistsException e){
      //this never happens
      System.out.println(e);
    }

    maxHealth = 120;
    typeDamageVulnerability = 12;
    type = Type.ROCK;
    isUnconscious = false;
    health = maxHealth;
  }
}
