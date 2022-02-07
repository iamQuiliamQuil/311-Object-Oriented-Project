import java.util.Scanner;

public class SessionManager {
  private Inventory<Monster> monsters;
  Scanner scanner;

  void renameMonster(){
    printMonsters();
    System.out.println("Select the monster you would like to rename..\n-");
    String oldName = scanner.nextLine();
    System.out.println("What would you like to name it?\n-");
    String newName = scanner.nextLine();

    try {
      monsters.rename(oldName, newName);
    } catch(ItemNotFoundException e) {
      System.out.println(oldName+" does not exist!\n-");
    } catch(ItemNameAlreadyExistsException e) {
      System.out.println(newName+" is already taken! Monsters names must be unique!\n-");
    }
  }

  public void printMonsters(){
    System.out.println("Your Monsters: ");
    for(String name: monsters.currentItemNamesArray()){
      try {
        System.out.println(name+" HP: "+monsters.retrieve(name).getHealth());
      } catch(ItemNotFoundException e) {
        //can't happen here
      }
    }
  }

  private void printMonsterAttacks(String monsterName) throws ItemNotFoundException{
    Monster monster = monsters.retrieve(monsterName);
    System.out.println(monsterName+"'s Attacks:");
    for(String attack: monster.getAttacks()){
      System.out.println(attack);
    }
  }

  void conductAttack(){
    printMonsters();
    String attackingMonsterName;
    Monster attackingMonster;
    //repeat until valid input is recieved
    while (true) {
      System.out.println("...Please select the monster you would like to attack with...\n-");
      attackingMonsterName = scanner.nextLine();
      try {
        printMonsterAttacks(attackingMonsterName);
      } catch(ItemNotFoundException e) {
        System.out.println(attackingMonsterName+" does not exist!\n-");
        continue;
      }
      break;
    }

    try {
      attackingMonster = monsters.retrieve(attackingMonsterName);
      String attack;
      while (true){
        System.out.println("...Please select what attack you would like to use...\n-");
        attack = scanner.nextLine();
        if (attackingMonster.hasAttack(attack)){
          break;
        } else {
          System.out.println(attack+" is not valid!\n-");
        }
      }

      printMonsters();

      String targetMonster;
      while(true){
        System.out.println("...Please select which monster you would like to attack...\n-");
        targetMonster = scanner.nextLine();

        try {
          int damageDone = monsters.retrieve(targetMonster).getHealth();
          attackingMonster.attack(attack, monsters.retrieve(targetMonster));
          damageDone -= monsters.retrieve(targetMonster).getHealth();
          System.out.println(attackingMonsterName+" dealt "+damageDone+" damage to "+targetMonster+"\n-");
          if (monsters.retrieve(targetMonster).isUnconscious()){
            System.out.println(targetMonster+" has fainted!\n-");
          }
        } catch (ItemNotFoundException e) {
          System.out.println(targetMonster+" does not exist!\n-");
          continue;
        } catch (MonsterIsUnconsciousException e){
          System.out.println(attackingMonsterName+" is unconscious, they cannot attack\n-");
          break;
        }
        break;
      }
    } catch(ItemNotFoundException e) {
      //input is already verified
    }
  }

  SessionManager(){
    scanner = new Scanner(System.in);
    monsters = new Inventory<Monster>();

    try {
      monsters.add("Scizzor Wasp Swarm", new ScizzorWaspSwarm());
      monsters.add("Super Rock Monster", new SuperRockMonster());
      monsters.add("Paper Anaconda", new PaperAnaconda());
    } catch(ItemNameAlreadyExistsException e) {
      //this never happens here
    }
  }
}
