import java.util.Scanner;

public class GameLogic {
    static Scanner gameLogicScan = new Scanner(System.in);

    /**
     * Generate an enemy to encounter
     * @param player current player
     * @param print print phrase before enemy appears
     * @param name enemy name
     * @param health enemy health
     * @param damage enemy damage
     * @param gold dropped gold from enemy
     * @param potions dropped potions from enemy
     */
    public static void encounter(Player player, String print, String name, int health, int damage, int gold, int potions){
        Enemy enemy = new Enemy(name, health, damage, gold, potions);
        System.out.println(print + " and a " + enemy.getName() + " attacks!");
        battle(player, enemy);
    }//encounter

    /**
     * Flow of battle. Player and monster takes turns dealing damage
     * Casting a spell or drinking a potion does not cost a turn
     * @param player the current player
     * @param enemy the current enemy
     */
    public static void battle(Player player, Enemy enemy) {
        while(true) {
            int option = 0;
            String optionString;
            System.out.println("_____________________________\n| "
                    + player.getName() + " " + player.getHealth() + "hp | " + enemy.getName() + " " + enemy.getHealth() + " hp |\n"
                    + player.getName() + " damage: " + player.getDamage() + " | potions: " + player.getPotions() +
                    "\nWhat would you like to do? (write number below and press enter)\n" +
                    "1: Physical attack | 2: Magical attack | 3: Drink a potion | 4: Help | Q: Quit");

            optionString = gameLogicScan.nextLine();
            if(optionString.equalsIgnoreCase("q")){
                System.out.println("Thanks for playing!");
                System.exit(0);
            }

            try{
                option = Integer.parseInt(optionString);
            } catch(NumberFormatException e){
                System.out.println("**Please provide a number between 1 and 4**");
            }
            if(option == 1) {
                player.dealDamage(enemy, player);
                if (enemy.getHealth() <= 0) {
                    break;
                }
                enemy.dealDamage(player);
                if (player.getHealth() <= 0) {
                    break;
                }
            } else if(option == 2){
                if(player.getMagic() >= 0) {
                    player.dealMagicDamage(enemy, player);
                }
                else{
                    System.out.println("**You are out of magic spells!**");
                }
                if (enemy.getHealth() <= 0) {
                    break;
                }
            } else if(option == 3){
                if(player.getPotions() > 0){
                    player.setHealth(player.getHealth() + 15);
                    if(player.getHealth() > Game.STARTING_HEALTH){
                        player.setHealth(Game.STARTING_HEALTH);
                    }
                    player.setPotions(player.getPotions() - 1);
                }
                else{
                    System.out.println("You have no potions");
                }
            } else if(option == 4){
                System.out.println("""
                        ** Physical attack strikes with your currently equipped weapon
                        ** Magical attack does not take a turn and hurls a fireball for 10 damage at the enemy
                        ** Potions do not take a turn and restore 15 health each
                        ** Entering Q exits the game""");
            }
        }
    }//battle

    /**
     * Asks the player if they want to pay a sum of gold to fully heal
     * @param p current player
     * @param cost current cost of fairy services
     */
    public static void fairy(Player p, int cost){
        System.out.println("You see a pond in the distance. It glistens with a pink sheen");
        String fairyChoice;
        boolean stay = true;
        while(stay) {
            System.out.println("Would you like to approach the pond?\n" +
                    "1: Yes | 2: No");
            fairyChoice = gameLogicScan.nextLine();
            if (fairyChoice.equalsIgnoreCase("yes") || fairyChoice.equals("1")) {
                System.out.println("A fairy materialises out of the pond and floats above it\n" +
                        "~**I can fully heal you for " + cost + " gold pieces**~\n" +
                        "~**Would you like me to heal you?**~\n" +
                        "1: Yes | 2: No | Current gold: " + p.getGold());
                while (true) {
                    fairyChoice = gameLogicScan.nextLine();
                    if (fairyChoice.equalsIgnoreCase("yes") || fairyChoice.equals("1")) {
                        p.setGold(p.getGold() - cost);
                        p.setHealth(Game.STARTING_HEALTH);
                        System.out.println("~**Your wounds have been healed!**~\n" +
                                cost + " gold pieces disappear from your pockets");
                        stay = false;
                        break;
                    } else if (fairyChoice.equalsIgnoreCase("no") || fairyChoice.equals("2")) {
                        System.out.println("Safe travels!");
                        stay = false;
                        break;
                    } else {
                        System.out.println("Please answer with 'Yes', 'No' '1' or '2'");
                    }
                }
            } else if (fairyChoice.equalsIgnoreCase("no") || fairyChoice.equals("2")) {
                System.out.println("You leave the pond behind you and keep going");
                stay = false;
            } else {
                System.out.println("Please answer with 'Yes', 'No' '1' or '2'");
            }
        }
    }//fairy

    /**
     * Ask the player if they would like to keep playing the game, but harder
     * @param prestige the current difficulty level the player is at
     * @return if the player wants to continue or not
     */
    public static boolean prestige(int prestige){
        while(true) {
            System.out.println("Would you like to enter the shop and then repeat the encounters (the enemies will be tougher)" +
                    "\n1: Yes | 2: No");
            Scanner prestigeScanner = new Scanner(System.in);
            String prestigeChoice = prestigeScanner.nextLine();
            if (prestigeChoice.equalsIgnoreCase("yes") || prestigeChoice.equals("1")) {
                System.out.println("You are currently on prestige level " + prestige);
                return true;
            } else if (prestigeChoice.equalsIgnoreCase("no") || prestigeChoice.equals("2")) {
                System.out.println("Thanks for playing!");
                return false;
            }
            else{
                System.out.println("Please answer with 'Yes', 'No' '1' or '2'");
            }
        }
    }//prestige


}