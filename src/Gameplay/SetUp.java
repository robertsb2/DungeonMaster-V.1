package Gameplay;

import Players.Players;
import lib.ConsoleIO;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class SetUp {

    public static void newGame() {
        System.out.println("");
        Players.setName("",false);
        Players.setStrength(5);
        Players.setDefense(4);
        Players.setMaxHealth(20);
        Players.setCurHealth(20);
        Players.setGold(10);
    }

    public static void savedGame() {
        try {
            BufferedReader savedGameFile = new BufferedReader(new FileReader("C:\\Users\\Bryan\\Documents\\My Games\\Dungeon Master\\Save FIles\\Player.txt"));
            String name = savedGameFile.readLine();
            Players.setName(name,true);
            System.out.println("");
            System.out.println("Welcome back " + name);
            delay(1000);
            System.out.println("");
            Players.setStrength(Integer.parseInt(savedGameFile.readLine()));
            Players.setDefense(Integer.parseInt(savedGameFile.readLine()));
            Players.setMaxHealth(Integer.parseInt(savedGameFile.readLine()));
            Players.setCurHealth(Integer.parseInt(savedGameFile.readLine()));
            Players.setGold(Integer.parseInt(savedGameFile.readLine()));
            System.out.println("");
            Players.printPlayer();
            delay(500);
            System.out.println("");

        } catch (Exception e){
            System.out.println("No save files found. Starting new game.");
            newGame();
        }
    }

    private static void save() {
        BufferedWriter newGameFile = null;
        try {
            System.out.println("Saving.....");
            delay(1500);
            newGameFile = new BufferedWriter(new FileWriter("C:\\Users\\Bryan\\Documents\\My Games\\Dungeon Master\\Save FIles\\Player.txt"));
            newGameFile.write(Players.getName());
            newGameFile.newLine();
            newGameFile.write(Integer.toString(Players.getStrength()));
            newGameFile.newLine();
            newGameFile.write(Integer.toString(Players.getDefense()));
            newGameFile.newLine();
            newGameFile.write(Integer.toString(Players.getMaxHealth()));
            newGameFile.newLine();
            newGameFile.write(Integer.toString(Players.getCurHealth()));
            newGameFile.newLine();
            newGameFile.write(Integer.toString(Players.getGold()));

            System.out.println("Game Saved");
        } catch (Exception e) {
            System.out.println("save error");
        } finally {
            try {
                if (newGameFile != null) {
                    newGameFile.flush();
                    newGameFile.close();

                }
            } catch (Exception e){
                System.out.println("save error");
            }
        }

    }

    public static void mainMenu(){
        String[] mainMenu = new String[]{"1: Save & Continue","2: Save & Quit"};
        switch (ConsoleIO.promptForMenuSelection(mainMenu,false)){
            case 1:
                save();
                delay(500);
                break;
            case 2:
                save();
                delay(500);
                System.out.println("Goodbye");
                System.exit(0);
        }

    }

    public static void reader(String filename) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (Exception e){
            System.out.println("Reader Error");
        }
    }

    public static void delay(int time){
        try{
            Thread.sleep(time);
        } catch (Exception e){
            System.out.println("delay error");
        }
    }
}
