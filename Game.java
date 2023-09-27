/*
Run a short game.
Player and enemy takes turns dealing damage.
If either the player or the enemy remaining health drops below 0, they are defeated.
 */
import java.util.Scanner;

public class Game {

    private final Scanner scan = new Scanner(System.in);
    static final String PLAYER_NAME = "dummy name";
    static final int STARTING_HEALTH = 50;
    static final int STARTING_DAMAGE = 10;
    static final int STARTING_GOLD = 20;
    static final int STARTING_POTIONS = 0;
    static final int STARTING_MAGIC = 3;
    static final String STARTING_WEAPON = "stick";
    static final String SHOP_WEAPON_NAME = "sword";
    static final int SHOP_STARTING_WEAPON_DAMAGE = 5;
    static final int SHOP_STARTING_WEAPON_COST = 30;
    static final int SHOP_POTIONS = 3;
    static final int FAIRY_COST = 9;

    public void newGame(){
        Player player = new Player(PLAYER_NAME, STARTING_HEALTH, STARTING_DAMAGE, STARTING_GOLD,
        STARTING_POTIONS, STARTING_MAGIC, STARTING_WEAPON);
        Shop shop = new Shop(SHOP_WEAPON_NAME, SHOP_STARTING_WEAPON_DAMAGE, SHOP_STARTING_WEAPON_COST);

        String playerName = askForPlayerName();
        player.setName(playerName);

        int prestigeLevel = 1;

        while(true){
            GameLogic.encounter(player, player.getName() + " walks through the forest", "Goblin",
                    20 + (prestigeLevel * 4), 3 + prestigeLevel, 10 + (prestigeLevel * 2), 0);

            GameLogic.fairy(player, FAIRY_COST + prestigeLevel);

            GameLogic.encounter(player, player.getName() + " comes to a clearing", "Ogre",
                    40 + (prestigeLevel * 5), 5 + (prestigeLevel * 2), 15 + (prestigeLevel * 3), 1);

            if (GameLogic.prestige(prestigeLevel)){
                shop.enterShop(player, shop);
                prestigeLevel++;
                shop.updateShop(shop, prestigeLevel);
            } else{
                break;
            }
        }//while
    }//newGame

    private String askForPlayerName(){
        String name;
        System.out.println("Welcome!" +
                "\nWhat is your name?");
        while(true){
            name = scan.nextLine();
            if(name.isEmpty()){
                System.out.println("Please enter a name: ");
            } else {
                break;
            }
        }
        return name;
    }//askPlayerForName
}