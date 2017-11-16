package Gameplay;
import Monsters.Monster;
import Players.Players;
import Weapons.Weapon;
import lib.ConsoleIO;

import java.util.Random;

public class Dungeon {
    public enum Rooms {
        HALLWAY("Dark and dusty, full of cobwebs."),
        TREASURY("A small room with a single golden chest."),
        CAVERN("A massive opening with damp walls."),
        LIBRARY("A room filled with old books."),
        STAIRS("Cobblestone stairs leading further into the darkness..."),
        SHRINE("A large room with polished granite walls and a candle adorned altar.");



        private final String roomDescription;
        Rooms(String roomDescription){
            this.roomDescription = roomDescription;
        }
    }

    private static int floor = 1;
    private static boolean foundStairs = false;
    public static void start(){
        System.out.println("");
        System.out.println("---------");
        System.out.println("Dungeon");
        System.out.println("Floor " + floor);
        System.out.println("---------");
        System.out.println("");
        SetUp.delay(500);
        String[] dungeonOptions;
        dungeonOptions = new String[]{"1: Explore","2: Go Upstairs","3: Exit Dungeon","4: Main Menu"};
        boolean inDungeon = true;
        do{
            System.out.println("");
            int choice = ConsoleIO.promptForMenuSelection(dungeonOptions, false);
            switch (choice){
                case 1:
                    explore();
                    break;
                case 2:
                    downStairs();
                    break;
                case 3:
                    exitDungeon();
                    inDungeon = false;
                    break;
                case 4:
                    SetUp.mainMenu();
                    break;

            }
        } while (inDungeon);
    }

    private static void explore() {
        Random random = new Random();
        int i = random.nextInt(100)+1;
        if (i <= 25){
            i = 1;
        } else if (i <= 50){
            i = 2;
        } else if (i <= 60){
            i = 3;
        } else if (i <= 80){
            i = 4;
        } else if (i <= 85){
            i = 5;
        } else if (i <= 100){
            i = 6;
        }
        System.out.println("");
        Rooms room = roomType(i);
        System.out.println(room);
        System.out.println("-------");
        System.out.println(room.roomDescription);
        System.out.println("-------");
        System.out.println("");
        SetUp.delay(1000);
        encounter(room);
    }

    private static void encounter(Rooms room) {
        Random random = new Random();
        switch (room){
            case CAVERN:
                Monster.foundMonster(floor);
                break;
            case SHRINE:
                if(ConsoleIO.promptForBool("Pray at the alter? (Yes/No)","Yes","No")) {
                    if (Players.getMaxHealth() != Players.getCurHealth()) {
                        Players.heal(((Players.getMaxHealth() - Players.getCurHealth()) / 2) + 1);
                    } else {
                        System.out.println("You have full health");
                        SetUp.delay(1000);
                    }
                }
                break;
            case HALLWAY:
                if(random.nextInt(2)+1 == 2){
                    System.out.println("You found some gold!");
                    SetUp.delay(1000);
                    Players.addGold(random.nextInt(4)+1);
                } else {
                    Monster.foundMonster(floor);
                }
                break;
            case LIBRARY:
                Players.statUp();
                break;
            case TREASURY:
                getReward();
                break;

        }
    }

    private static void downStairs() {
        if(foundStairs){
            foundStairs = false;
            floor++;
            System.out.println("");
            System.out.println("Floor " + floor);
        } else {
            System.out.println("");
            System.out.println("You haven't found the stairs for this floor yet...");
            SetUp.delay(1000);
        }
    }

    private static void exitDungeon() {
        floor = 1;

    }

    private static Gameplay.Dungeon.Rooms roomType(int i) {

        switch (i){
            case 1:
                return Rooms.HALLWAY;
            case 2:
                return Rooms.CAVERN;
            case 3:
                return Rooms.LIBRARY;
            case 4:
                foundStairs = true;
                return Rooms.STAIRS;
            case 5:
                return Rooms.TREASURY;
            case 6:
                return Rooms.SHRINE;
            default:
                return null;
        }
    }

    public static void getReward(){
        Random random = new Random();
        int i = random.nextInt(4)+1;
        if (i == 1 && (floor % 2) == 0){
            System.out.println("You got a weapon");
            System.out.println(Weapon.getWeaponName());
            Weapon.setWeapon(random.nextInt(floor/2)+1);
        } else {
            Players.addGold(random.nextInt(10)+1);
        }
    }
}
