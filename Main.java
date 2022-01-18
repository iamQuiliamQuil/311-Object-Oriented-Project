import java.util.Scanner;

public class Main {

  public static void main(String[] args){
    Scanner scanner = new Scanner(System.in);
    SessionManager sessionManager = new SessionManager();

    System.out.println("Welcome to Terminal Monster!\n-");
    sessionManager.printMonsters();
    System.out.println("-");

    int input;
    while (true){
      System.out.println("Input 1 if you want to fight, 2 if you want to rename your monsters, 0 if you want to exit.\n-");
      input = scanner.nextInt();
      System.out.println("-");
      switch (input){
        case 0: //do nothing, fall to break statement that will exit loop
        break;
        case 1: sessionManager.conductAttack();
        continue; //repeat loop until exit command
        case 2: sessionManager.renameMonster();
        continue; //repeat loop until exit command
        default: System.out.println("Please input a valid number!");
        continue; //repeat loop until exit command
      }
      break;
    }
    System.out.println("Thank you for using TerminalMonster!");
  }
}
