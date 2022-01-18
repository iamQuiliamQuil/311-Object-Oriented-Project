public class Attacks{

  //singleton
  public static class Punch extends AttackTemplate {
    private static Punch punch = new Punch();

    static Punch getPunch(){
      return punch;
    }

    private Punch(){
      baseDamage = 20;
      type = Type.ROCK;
      randDamageRange = 10;
      accuracyOutOf10 = 9;
    }
  }

  //singleton
  public static class Slash extends AttackTemplate {
    private static Slash slash = new Slash();

    static Slash getSlash(){
      return slash;
    }

    private Slash(){
      baseDamage = 25;
      type = Type.SCIZZORS;
      randDamageRange = 15;
      accuracyOutOf10 = 7;
    }
  }

  public static class Kick extends AttackTemplate {
    private static Kick kick = new Kick();

    static Kick getKick(){
      return kick;
    }

    private Kick(){
      baseDamage = 25;
      type = Type.ROCK;
      randDamageRange = 12;
      accuracyOutOf10 = 8;
    }
  }

  public static class Strangle extends AttackTemplate {
    private static Strangle strangle = new Strangle();

    static Strangle getStrangle(){
      return strangle;
    }

    private Strangle(){
      baseDamage = 30;
      type = Type.PAPER;
      randDamageRange = 0;
      accuracyOutOf10 = 10;
    }
  }

  public static class FallOn extends AttackTemplate {
    private static FallOn fallOn = new FallOn();

    static FallOn getFallOn(){
      return fallOn;
    }

    private FallOn(){
      baseDamage = 50;
      type = Type.ROCK;
      randDamageRange = 10;
      accuracyOutOf10 = 5;
    }
  }

  public static class Sting extends AttackTemplate {
    private static Sting sting = new Sting();

    static Sting getSting(){
      return sting;
    }

    private Sting(){
      baseDamage = 10;
      type = Type.SCIZZORS;
      randDamageRange = 5;
      accuracyOutOf10 = 9;
    }
  }

  public static class Buzz extends AttackTemplate {
    private static Buzz buzz = new Buzz();

    static Buzz getBuzz(){
      return buzz;
    }

    private Buzz(){
      baseDamage = 0;
      type = Type.PAPER;
      randDamageRange = 1;
      accuracyOutOf10 = 10;
    }
  }
}
