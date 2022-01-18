public interface Monster {
  public int getHealth();
  public void attack(String attackName, Monster targetMonster) throws ItemNotFoundException, MonsterIsUnconsciousException;
  public void defend(AttackTemplate attack);
  public Type getType();
  public String[] getAttacks();
  public Boolean isUnconscious();
  public Boolean hasAttack(String attack);
}
