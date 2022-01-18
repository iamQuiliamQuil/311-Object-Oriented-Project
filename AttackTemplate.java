public abstract class AttackTemplate {
  int baseDamage;
  Type type;
  int randDamageRange;
  int accuracyOutOf10;

  Boolean typeBeats(Type monsterType){
    if(type == Type.ROCK && monsterType == Type.SCIZZORS){
      return true;
    } else if (type == Type.PAPER && monsterType == Type.ROCK){
      return true;
    } else if (type == Type.SCIZZORS && monsterType == Type.PAPER){
      return true;
    } else {
      return false;
    }
  }
}
