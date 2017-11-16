package Weapons;


public class Weapon {
    public enum Weapons{
        SHORT_SWORD("name",1),
        LONG_SWORD("NAME",2),
        BROADSWORD("NAME",3),
        MACE("NAME",4),
        AXE("NAME",5);

        private final String weaponName;
        private final int power;

        Weapons(String name, int power){
            this.weaponName = name;
            this.power = power;
        }
    }

    private static int atkPower;
    private static String name;

    public static void setWeapon(int weaponId){
        Weapons weapon;
        weapon = Weapons.values()[weaponId];
        atkPower = weapon.power;
        name = weapon.weaponName;
    }

    public static int getAtkPower(){
        return atkPower;
    }

    public static String getWeaponName(){
        return name;
    }
}
