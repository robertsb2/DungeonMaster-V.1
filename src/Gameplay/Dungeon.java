package Gameplay;
import Monsters.Monster;
import Players.Players;
import Weapons.Weapon;
import lib.ConsoleIO;

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
                    upStairs();
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
        int i = SetUp.getRandom(100)+1;
        if (i <= 25){
            i = 1; //Hallway 25% chance
        } else if (i <= 50){
            i = 2; //Cavern 25% chance
        } else if (i <= 65){
            i = 3; //Library 15% chance
        } else if (i <= 80){
            i = 4; //Stairs 15% chance
        } else if (i <= 90){
            i = 5; //Treasury 10% chance
        } else if ( i <= 100){
            i = 6; //Shrine 10% chance
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
                if(SetUp.getRandom(2)+1 == 2){
                    System.out.println("You found some gold!");
                    SetUp.delay(1000);
                    Players.addGold(SetUp.getRandom(4)+1);
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

    private static void upStairs() {
        if(foundStairs){
            foundStairs = false;
            floor++;
            System.out.println("");
            System.out.println("---------");
            System.out.println("Dungeon");
            System.out.println("Floor " + floor);
            System.out.println("---------");
            System.out.println("");

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
        int i = SetUp.getRandom(4)+1;
        if (i == 1 && (floor % 2) == 0){
            System.out.println("You got a weapon");
            System.out.println(Weapon.getWeaponName());
            Weapon.setWeapon(SetUp.getRandom(floor/2)+1);
        } else {
            Players.addGold(SetUp.getRandom(5)+1);
        }
    }
}
