package Monsters;

import Gameplay.Dungeon;
import Gameplay.SetUp;
import Players.Players;
import Weapons.Weapon;
import lib.ConsoleIO;
import java.util.Scanner;

public class Monster {
    private static int strength;
    private static int defense;
    private static int health;
    private static int escapeRoll;
    private static Types type;
    private static String name;
    private static boolean escape;

    public enum Types {
        //TYPE("name", Health, Strength, Defense, Escape Requirement)
        GOBLIN("Goblin", 5, 4, 3, 2),
        ZOMBIE("Zombie", 8, 5, 5, 3),
        BANSHEE("Banshee", 10, 5, 5, 4),
        VAMPIRE("Vampire", 13, 5, 5, 5),
        GHOUL("Ghoul", 15, 5, 5, 6),
        WEREWOLF("Werewolf", 18, 5, 5, 7),
        MINOTAUR("Minotaur", 20, 5, 5, 8),
        CYCLOPS("Cyclops", 23, 5, 5, 9),
        DRAGON("Dragon", 25, 5, 5, 11),
        DEMON("Demon", 30, 5, 5, 11);

        private final String eName;
        private final int eHealth;
        private final int eStrength;
        private final int eDefense;
        private final int eEscape;

        Types(String type, int health, int strength, int defense, int escapeRoll){
            this.eName = type;
            this.eHealth = health;
            this.eStrength = strength;
            this.eDefense = defense;
            this.eEscape = escapeRoll;
        }

    }



    public static void foundMonster(int i){
        type = Types.values()[SetUp.getRandom(i)];
        strength = type.eStrength + i ;
        defense = type.eDefense + i ;
        health = type.eHealth;
        name = type.eName;
        escapeRoll = type.eEscape;

        fight();

    }

    private static void fight() {
        escape = false;
        Scanner scanner = new Scanner(System.in);
        System.out.println("A " + Monster.name + " appeared!");
        while (!escape){
            System.out.println("");
            printBattleStats();
            scanner.nextLine();
            playerTurn();
            if(Monster.health > 0 && !escape) {
                monsterAttack();
            }
            if (Monster.health <=0){
                System.out.println("You defeated the " + Monster.name);
                Dungeon.getReward();
                SetUp.delay(500);
                escape = true;
            }
        }
        if(Players.getCurHealth() <= 0){
            System.out.println("Game Over");
            System.exit(0);
        }
    }

    private static void playerTurn() {
        int attack;
        int damage;
        switch (ConsoleIO.promptForMenuSelection(new String[]{"1: Fight","2: Open pack", "3: Escape"},false)){
            case 1:
                SetUp.delay(500);
                attack = SetUp.getRandom(Players.getStrength())+1;
                if(attack >= Monster.defense){
                   damage = (Players.getStrength()/2) + Weapon.getAtkPower();
                   Monster.health -= damage;
                    System.out.println("You hit and did " + damage + " damage.");
                } else {
                    System.out.println("Your attack missed...");
                }
                SetUp.delay(500);
                break;
            case 2:
                Players.openPack();
                break;
            case 3:
                if(SetUp.getRandom(10)> Monster.escapeRoll) {
                    System.out.println("Coward....");
                    escape = true;
            } else {
                System.out.println("Can't Escape!");
            }



        }
    }

    private static void monsterAttack() {
        System.out.println("The " + Monster.type + " attacks");
        SetUp.delay(500);
        int attack = SetUp.getRandom(Monster.strength)+1;
        if(attack >= Players.getDefense()){
            int damage = SetUp.getRandom(Monster.strength/2)+1;
            System.out.println("It deals " + damage + " damage.");
            Players.setCurHealth(-damage);
        } else {
            System.out.println("It's attack misses");
        }
        SetUp.delay(500);
    }


    private static void printBattleStats(){
        System.out.println(Players.getName() + "                            " + type);
        System.out.println("--------                         --------");
        System.out.println("Strength: " + Players.getStrength() + "                      Strength: " + strength);
        System.out.println("Defense: " + Players.getDefense() + "                       Defense: " + defense);
        System.out.println("Current Health: " + Players.getCurHealth() + "               Current Health: " + health);
    }

}
