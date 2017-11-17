package Weapons;


public class Weapon {
    public enum Weapons{
        EMPTY("None",0),
        SHORT_SWORD("Short Sword",1),
        LONG_SWORD("Long Sword",2),
        BROADSWORD("Broadsword",3),
        MACE("Mace",4),
        AXE("Axe",5);

        private final String weaponName;
        private final int power;

        Weapons(String name, int power){
            this.weaponName = name;
            this.power = power;
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
}
