package Weapons;

public class Weapon {
    public enum Weapons{
        //NAME("Name",Attack Power, Cost)
        EMPTY("None",0,0),
        SHORT_SWORD("Short Sword",1,30),
        LONG_SWORD("Long Sword",2,60),
        BROADSWORD("Broadsword",3,90),
        MACE("Mace",4,120),
        AXE("Axe",5,150);

        private final String weaponName;
        private final int power;
        private final int cost;

        Weapons(String name, int power, int cost){
            this.weaponName = name;
            this.power = power;
            this.cost = cost;
        }
    }

    private static int atkPower;
    private static String name = "none";
    private static int weaponId = 0;

    public static void setWeapon(int idNumber){
        Weapons weapon;
        weapon = Weapons.values()[idNumber];
        atkPower = weapon.power;
        name = weapon.weaponName;
        weaponId = idNumber;
    }

    public static int getAtkPower(){
        return atkPower;
    }

    public static String getWeaponName(){
        return name;
    }

    public static int getWeaponId(){
        return weaponId;
    }

    public static int getCost(int idNumber){
        Weapons weapon = Weapons.values()[idNumber];

        return weapon.cost;
    }
}
