import java.util.Scanner;

public class Shop {
    Scanner shopScan = new Scanner(System.in);
    private int potions;
    private String weapon;
    private int weaponDamage;
    private int weaponCost;

    /**
     * Generate a new shop with one weapon and three potions in stock
     * @param weapon name of weapon
     * @param weaponDamage weapon's damage
     * @param weaponCost cost of weapon
     */
    public Shop(String weapon, int weaponDamage, int weaponCost){
        this.potions = 3;
        this.weapon = weapon;
        this.weaponDamage =  weaponDamage;
        this.weaponCost = weaponCost;
    }//Shop

    public int getWeaponDamage() {
        return weaponDamage;
    }

    public void setWeaponDamage(int weaponDamage) { this.weaponDamage = weaponDamage; }

    public int getWeaponCost() {
        return weaponCost;
    }

    public void setWeaponCost(int weaponCost) {
        this.weaponCost = weaponCost;
    }

    public int getPotions() {
        return potions;
    }

    public void setPotions(int potions) { this.potions = potions; }

    public String getWeapon() { return weapon; }

    public void setWeapon(String weapon) { this.weapon = weapon; }

    /**
     * Enter a shop and buy items, if player has enough gold
     * @param p current player
     * @param s current shop
     */
    public void enterShop(Player p, Shop s){
        String weaponAvailable = s.getWeapon() + " (" + s.getWeaponCost() + " gold)";
        boolean weaponBought = false;
        int choice = 0;
        int potionCost = 10;
        String shopString = "We currently have " + getPotions() + " potions in stock for " + potionCost + " gold each.\n" +
                "We also have a " + s.getWeapon() + "(+" + s.getWeaponDamage() + " damage) for " + s.getWeaponCost() + " gold.";

        System.out.println("Welcome to the shop, " + p.getName() +"!\n" +
                "______________________________");

        while(true){
            System.out.println(shopString);
            System.out.println("You currently have [" + p.getGold() + "] gold on you. Would you like to buy anything?\n" +
                    "1: potion (10 gold) | 2: " + weaponAvailable + " | 3: Exit shop");
            String shopChoice = shopScan.nextLine();
            try{
                choice = Integer.parseInt(shopChoice);
            }catch(NumberFormatException e){
                System.out.println("**Please enter a valid choice between 1-3**");
            }
            if(choice == 1){
                if(p.getGold() >= potionCost && s.getPotions() > 0) {
                    System.out.println("**You bought a potion!**");
                    p.setPotions(p.getPotions() + 1);
                    p.setGold(p.getGold() - potionCost);
                }
                else if(s.getPotions() == 0){
                    System.out.println("**We are all out of potions**");
                }
                else{
                    System.out.println("**You do not have enough money to buy a potion**");
                }
            }
            else if(choice == 2){
                if(p.getGold() > getWeaponCost() && !weaponBought){
                    System.out.println("_____________________________________" +
                            "\n**You bought a " + s.getWeapon() + "! (+" + getWeaponDamage() + " damage!)**" +
                            "\n_____________________________________");
                    p.setDamage(Game.STARTING_DAMAGE + s.getWeaponDamage());
                    p.setWeapon(s.getWeapon());
                    p.setGold(p.getGold() - s.getWeaponCost());
                    weaponAvailable = "SOLD OUT";
                    weaponBought = true;
                    shopString = "We currently have " + getPotions() + " potions in stock for 10 gold each.\n";
                }
                else if(weaponBought){
                    System.out.println("You have already bought the " + s.getWeapon());
                }
                else{
                    System.out.println("You do not have enough money to buy the " + s.getWeapon());
                }
            }
            else if(choice == 3){
                System.out.println("Good bye and good luck!");
                break;
            }
        }
    }//enterShop

    /**
     * Updates the shop
     * @param shop shop in current game
     * @param prestige current prestige level of game
     */
    public void updateShop(Shop shop, int prestige){
        shop.setWeapon(getWeapon() + "+");
        shop.setWeaponCost(getWeaponCost() + (prestige * 10));
        shop.setWeaponDamage(getWeaponDamage() + (prestige * 3));
        shop.setPotions(Game.SHOP_POTIONS);
    }//updateShop
}