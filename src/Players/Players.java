package Players;

import lib.ConsoleIO;

import java.util.ArrayList;
import java.util.Random;

public class Players {
    private static String name = "";
    private static int strength = 0;
    private static int defense = 0;
    private static int maxHealth = 0;
    private static int curHealth = 0;
    private static int gold = 0;

    private static ArrayList pack = new ArrayList();

    // Name methods:
    public static String setName(String entry, boolean savedGame){

        if(savedGame){
            name = entry;
        } else {
            name = ConsoleIO.promptForInput("Please enter your name: ",false);
        }
        return name;
    }
    public static String getName(){
        return name;
    }

    // Strength Stat Methods:
    public static void setStrength(int value){
        strength += value;
    }
    public static int getStrength(){
        return strength;
    }

    //Defense Stat Methods:
    public static void setDefense(int value){
        defense += value;
    }
    public static int getDefense() {
        return defense;
    }

    // Max Health Methods:
    public static void setMaxHealth(int value){
        maxHealth += value;
    }
    public static int getMaxHealth() {
        return maxHealth;
    }


    //Current Health Methods:
    public static void setCurHealth(int value){
        curHealth += value;
    }
    public static int getCurHealth() {
        return curHealth;
    }
    public static void heal(int amount){
        if  (maxHealth-curHealth >= amount) {
            System.out.println("You regained " + amount + " health");
            curHealth += amount;
         } else {
            curHealth = maxHealth;
        }
        Players.printPlayer();
    }

    // Gold Methods:
    public static void setGold(int amount){
        gold += amount;
    }
    public static void addGold(int amount){
        System.out.println(amount + " gold added to pouch.");
        gold += amount;

    }
    public static int getGold() {
        return gold;
    }
    public static void subtractGold(int amount){
        gold -=amount;
    }


    // Pack Methods:
    public static void fillPack(int amount){
        for (int i = 0; i < amount ; i++) {
            pack.add("potion");
        }
    }
    public static void openPack() {
        String choice = null;
        try {
            for (Object item : pack) {
                System.out.println(item.toString());

            }
            choice = ConsoleIO.promptForInput("What do you want to use? ", false);
        } catch (Exception e) {
            System.out.println("Your Pack is empty");
        }
           try {
               if ( pack.contains(choice)){
                   useItem(choice);
               } else {
                   System.out.println("you don't have any of those.");
               }
           }catch (Exception e){
               System.out.println("Your Pack is empty.");
           }
        }
    private static void useItem(String item){
        try {
            switch (item){
                case "potion":
                    Players.pack.remove(item);
                    heal(5);
                    break;
                default:
                    System.out.println("Use Item Error");

            }
        } catch (NullPointerException e){
            System.out.println("Your pack is empty");
        }
    }

    public static void printPlayer(){
        System.out.println(name);
        System.out.println("--------");
        System.out.println("Strength: " + strength);
        System.out.println("Defense: " + defense);
        System.out.println("Max Health: " + maxHealth);
        System.out.println("Current Health: " + curHealth);
        System.out.println("Gold: " + gold);
    }

    public static void statUp(){
        Random random = new Random();
        int i = random.nextInt(3)+1;
        switch (i){
            case 1:
                System.out.println("You found a Hero's Journal. Your strength has increased by 1");
                setStrength(1);
                printPlayer();
                break;
            case 2:
                System.out.println("You found a Strategist's Guidebook. Your defense has increased by 1");
                setDefense(1);
                printPlayer();
                break;
            default:
                System.out.println("There is nothing of interest here.");
        }
    }

}
