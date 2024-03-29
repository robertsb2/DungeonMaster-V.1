package Gameplay;
import Monsters.Monster;
import Players.Players;
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

    // Entry point for dungeon methods.
    public static void start(){
        System.out.println("");
        System.out.println("---------");
        System.out.println("Dungeon");
        System.out.println("Floor " + floor);
        System.out.println("---------");
        System.out.println("");
        SetUp.delay(500);



            String[] dungeonOptions;
            dungeonOptions = new String[]{"1: Explore", "2: Go Upstairs", "3: Open Pack", "4: Exit Dungeon", "5: Main Menu"};
            boolean inDungeon = true;
            do {
                if(floor == 10){
                    finalFloor();
                } else {
                    System.out.println("");
                    int choice = ConsoleIO.promptForMenuSelection(dungeonOptions, false);
                    switch (choice) {
                        case 1:
                            explore();
                            break;
                        case 2:
                            upStairs();
                            break;
                        case 3:
                            Players.openPack();
                            break;
                        case 4:
                            exitDungeon();
                            inDungeon = false;
                            break;
                        case 5:
                            SetUp.mainMenu();
                            break;
                    }
                }
            } while (inDungeon);

    }

    // Randomly selects tower rooms type.
    private static void explore() {
        int i = SetUp.getRandom(100)+1;
        if (i <= 22){
            i = 1; //Hallway 22% chance
        } else if (i <= 44){
            i = 2; //Cavern 22% chance
        } else if (i <= 66){
            i = 3; //Library 22% chance
        } else if (i <= 80){
            i = 4; //Stairs 14% chance
        } else if (i <= 90){
            i = 5; //Treasury 10% chance
        } else if ( i <= 100){
            i = 6; //Shrine 10% chance
        }
        System.out.println("");
        Rooms room = roomType(i);
        System.out.println(room);
        System.out.println("-------");
        if (room != null) {
            System.out.println(room.roomDescription);
        }
        System.out.println("-------");
        System.out.println("");
        SetUp.delay(1000);
        encounter(room);
    }

    // Takes input from explore() to generate selected room.
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

    // Handles player encounters based on room type.
    private static void encounter(Rooms room) {
        switch (room){
            case CAVERN:
                Monster.foundMonster(SetUp.getRandom(floor));
                break;
            case SHRINE:
                if(ConsoleIO.promptForBool("Pray at the alter? (Yes/No)","Yes","No")) {
                    int i = SetUp.getRandom(3)+1;
                    if (i == 1) {
                        SetUp.delay(2000);
                        System.out.println("You max health has increased by 1");
                        Players.setMaxHealth(1);
                        SetUp.delay(1000);
                    } else if (i == 2) {
                        SetUp.delay(2000);
                        Players.heal(3);
                        SetUp.delay(1000);
                    } else {
                        SetUp.delay(2000);
                        System.out.println("Nothing happened...");
                        SetUp.delay(1000);
                    }
                }
                break;
            case HALLWAY:
                if(SetUp.getRandom(2)+1 == 2){
                    System.out.println("You found some gold!");
                    SetUp.delay(1000);
                    getReward();
                } else {
                    Monster.foundMonster(SetUp.getRandom(floor));
                }
                break;
            case LIBRARY:
                if (SetUp.getRandom(6) + 1 > 5){
                    System.out.println("You found a hidden passageway! Choose a location");
                    int i = ConsoleIO.promptForMenuSelection(new String[]{"1: Stairs","2: Treasury","3: Shrine"},false) + 3;
                    System.out.println("");
                    Rooms passage = roomType(i);
                    System.out.println(passage);
                    System.out.println("-------");
                    if (passage != null) {
                        System.out.println(passage.roomDescription);
                    }
                    System.out.println("-------");
                    System.out.println("");
                    SetUp.delay(1000);
                    encounter(passage);
                } else {
                    Players.findPiece();
                }
                break;
            case TREASURY:
                getReward();
                break;

        }
    }

    // Checks if stairs have been found on current floor.
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

    // Initiates Boss Battle when final floor is reached.
    private static void finalFloor() {
        SetUp.delay(2000);
        System.out.println("You've done well to make it this far mortal...");
        SetUp.delay(2000);
        System.out.println("but you will go no further.");
        SetUp.delay(2000);
        System.out.println();
        System.out.println();
        Monster.foundMonster(floor -1);
    }

    // Handles player request to exit dungeon.
    private static void exitDungeon() {
        floor = 1;

    }

    // Gives random reward upon battle end or when treasure is found
    public static void getReward(){
            Players.addGold(SetUp.getRandom(5) + floor +1);
    }
}
