package combat;

import java.util.ArrayList;


public class stats{

// ARMOR STATS

public static int HP = 0;
public static int BLOCK = 0;
public static int DODGE = 0;
public static int REFLECT = 0;
public static int ARMOR = 0;
public static int HPREGEN = 0;
public static int HEALCHANCE = 0;
public static int PUREDMGBONUS = 0;
public static int FIREDMGBONUS = 0;
public static int ICEDMGBONUS = 0;
public static int POISONDMGBONUS = 0;
public static int HPPERC = 0;
public static int ENE = 0;

// WEAPON STATS

public static int DMG = 0;
public static int CRITCHANCE = 0;
public static int CRITDMG = 0;
public static int FIREDMG = 0;
public static int ICEDMG = 0;
public static int PUREDMG = 0;
public static int POISONDMG = 0;
public static int LIFESTEAL = 0;
public static int PVEDMG = 0;

	
public static  String[] armorStatNames =
{
		"HP",
		"BLOCK",
		"DODGE",
		"REFLECT",
		"ARMOR",
		"HPREGEN",
		"HEALCHANCE",
		"PUREDMGBONUS",
		"ICEDMGBONUS",
		"FIREDMGBONUS",
		"POISONDMGBONUS",
		"HPPERC",
		"ENE"
		
		
};

public static  String[] weaponStatNames =
{
	"DMG",
	"FIREDMG",
	"ICEDMG",
	"POISONDMG",
	"PUREDMG",
	"LIFESTEAL",
	"CRITCHANCE",
	"CRITDMG",
	"PVEDMG"
};


 static ArrayList<Integer> armorStatNumbers = new ArrayList<Integer>();
 static ArrayList<Integer> weaponStatNumbers = new ArrayList<Integer>();


public static  void resetStats(){	
armorStatNumbers = new ArrayList<Integer>();	
for (String x : armorStatNames){armorStatNumbers.add(0);}	
 HP = 0;
 BLOCK = 0;
 DODGE = 0;
 REFLECT = 0;
 ARMOR = 0;
 HPREGEN = 0;
 HEALCHANCE = 0;
 PUREDMGBONUS = 0;
 FIREDMGBONUS = 0;
 ICEDMGBONUS = 0;
 POISONDMGBONUS = 0;
 HPPERC = 0;
 ENE = 0;
}

public static  void resetWeaponStats(){	
weaponStatNumbers = new ArrayList<Integer>();	
for (String x : weaponStatNames){weaponStatNumbers.add(0);}
 DMG = 0;
 CRITCHANCE = 0;
 CRITDMG = 0;
 FIREDMG = 0;
 ICEDMG = 0;
 PUREDMG = 0;
 POISONDMG = 0;
 LIFESTEAL = 0;
 PVEDMG = 0;		
}


public static  void setArmorStats(){
 HP = armorStatNumbers.get(0);
 BLOCK = armorStatNumbers.get(1);
 DODGE = armorStatNumbers.get(2);
 REFLECT = armorStatNumbers.get(3);
 ARMOR = armorStatNumbers.get(4);
 HPREGEN = armorStatNumbers.get(5);
 HEALCHANCE = armorStatNumbers.get(6);
 PUREDMGBONUS = armorStatNumbers.get(7);
 ICEDMGBONUS = armorStatNumbers.get(8);
 FIREDMGBONUS = armorStatNumbers.get(9);
 POISONDMGBONUS = armorStatNumbers.get(10);
 HPPERC = armorStatNumbers.get(11);
 ENE = armorStatNumbers.get(12);
}

public static  void setWeaponStats(){
 DMG = weaponStatNumbers.get(0);
 FIREDMG = weaponStatNumbers.get(1);
 ICEDMG = weaponStatNumbers.get(2);
 POISONDMG = weaponStatNumbers.get(3);
 PUREDMG = weaponStatNumbers.get(4);
 LIFESTEAL = weaponStatNumbers.get(5);
 CRITCHANCE = weaponStatNumbers.get(6);
 CRITDMG = weaponStatNumbers.get(7);
 PVEDMG = weaponStatNumbers.get(8);

}



}
