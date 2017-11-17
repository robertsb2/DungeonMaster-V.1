package Gameplay;

import Players.Players;
import Weapons.Weapon;
import lib.ConsoleIO;

public class Town {
    private static String[] townOptions = new String[]{"1: Visit Healer","2: Visit Shops","3: Leave Town","4: Main Menu"};
    public static void start(){
        boolean inTown = true;
        do{
            System.out.println("");
            System.out.println("---------");
            System.out.println("Durnwell");
            System.out.println("---------");
            System.out.println("");
            SetUp.delay(500);
            int choice = ConsoleIO.promptForMenuSelection(townOptions, false);
            switch (choice){
                case 1:
                    healer();
                    break;
                case 2:
                    shop();
                    break;
                case 3:
                    inTown = false;
                    break;
                case 4:
                    SetUp.mainMenu();

            }
        } while (inTown);
    }

    private static void shop() {
        String shopFile = "C:\\Users\\Bryan\\Documents\\My Games\\Dungeon Master\\Dialogue\\Shop1.txt";
        String shopFile2 = "C:\\Users\\Bryan\\Documents\\My Games\\Dungeon Master\\Dialogue\\Shop2.txt";
        boolean shopping = true;
        int amount;
        int potionCost = 10;
        int journalCost = 25;
        int bookCost = 20;
        int weaponCostMultiplier = 5;
        int choice;
        System.out.println("");
        System.out.println("Welcome to my shop.");
        SetUp.delay(500);
        while (shopping){
            System.out.println("See anything you like?");
            SetUp.delay(500);
            System.out.println("");
            SetUp.reader(shopFile);
            System.out.println("Gold: " + Players.getGold());
            System.out.println("");
            switch (ConsoleIO.promptForMenuSelection(new String[]{"1: Buy Potions","2: Buy Journal","3: Buy Guidebook","4: Buy Weapons","5: Exit Shop"},false)){
                case 1:
                    amount = ConsoleIO.promptForInt("How many would you like to buy?",1,9999);
                    if ((amount * potionCost) > Players.getGold()){
                        System.out.println("");
                        System.out.println("You don't have enough money");
                        System.out.println("");
                        SetUp.delay(1000);
                    } else {
                        Players.subtractGold(amount * potionCost);
                        Players.fillPack(amount);
                    }
                    break;
                case 2:
                    amount = ConsoleIO.promptForInt("How many would you like to buy?",1,9999);
                    if ((amount * journalCost) > Players.getGold()){
                        System.out.println("");
                        System.out.println("You don't have enough money");
                        System.out.println("");
                        SetUp.delay(1000);
                    } else {
                        Players.subtractGold(amount * journalCost);
                        Players.setStrength(amount);
                    }
                    break;
                case 3:
                    amount = ConsoleIO.promptForInt("How many would you like to buy?",1,9999);
                    if ((amount * bookCost) > Players.getGold()){
                        System.out.println("");
                        System.out.println("You don't have enough money");
                        System.out.println("");
                        SetUp.delay(1000);
                    } else {
                        Players.subtractGold(amount*bookCost);
                        Players.setDefense(amount);
                    }
                    break;
                case 4:
                    System.out.println("");
                    SetUp.reader(shopFile2);
                    System.out.println("");
                    choice = ConsoleIO.promptForInt("Which one were you looking at?",1,Weapon.Weapons.values().length);
                    amount = weaponCostMultiplier * choice + 10;
                    if (amount > Players.getGold()){
                        System.out.println("");
                        System.out.println("You don't have enough money");
                        System.out.println("");
                        SetUp.delay(1000);
                    } else {
                        Players.subtractGold(amount);
                        Weapon.setWeapon(choice);
                    }
                    break;
                case 5:
                    shopping = false;
                    break;
            }
        }
    }

    private static void healer() {
        int diff =  Players.getMaxHealth() - Players.getCurHealth();
        if(ConsoleIO.promptForBool("I can heal you for " + diff + " gold. (1:Heal 2: Cancel)","1", "2") && diff != 0){
            if(Players.getGold() >= diff){
                Players.setCurHealth(diff);
                Players.subtractGold(diff);
            } else {
                System.out.println("You don't have enough gold");
                if(ConsoleIO.promptForBool("Would you like me to heal some of your wounds?","Yes","no")){
                    Players.setCurHealth(Players.getGold());
                    Players.subtractGold(Players.getGold());
                }
            }

        } else {
            System.out.println("Oh, you don't need any healing...   Come back later");
        }
    }


}
