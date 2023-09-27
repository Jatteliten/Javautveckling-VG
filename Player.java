public class Player {
    private String name;
    private int health;
    private int damage;
    private int gold;
    private int potions;
    private int magic;
    private String weapon;

    /**
     * Generate a player
     * @param name player name
     * @param health player starting health
     * @param damage player starting damage
     * @param gold player starting gold
     * @param potions player starting potions
     * @param magic number of magic spells player starts with
     * @param weapon player starting weapon
     */
    public Player(String name, int health, int damage, int gold, int potions, int magic, String weapon) {
        this.name = name;
        this.health = health;
        this.damage = damage;
        this.gold = gold;
        this.potions = potions;
        this.magic = magic;
        this.weapon = weapon;
    }//Player

    public Player(String name, int health, int damage, int gold, int potions) {
        this.name = name;
        this.health = health;
        this.damage = damage;
        this.gold = gold;
        this.potions = potions;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) { this.name = name; }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public int getPotions() {
        return potions;
    }

    public void setPotions(int potions) {
        this.potions = potions;
    }

    public int getMagic() {
        return magic;
    }

    public void setMagic(int magic) {
        this.magic = magic;
    }

    public String getWeapon() {
        return weapon;
    }

    public void setWeapon(String weapon) {
        this.weapon = weapon;
    }

    /**
     * Player damage phase
     * @param e enemy the player is currently fighting
     * @param p current player
     */
    public void dealDamage(Enemy e, Player p){
        System.out.println(p.getName() + " deals " + p.getDamage() + " damage with your " + p.getWeapon() + "!");
        e.setHealth(e.getHealth() - p.getDamage());
        if(e.getHealth() <= 0){
            win(e, p);
        }
    }//dealDamage

    /**
     * Deal magic damage, which does not take a turn
     * @param e enemy the player is currently fighting
     * @param p current player
     */
    public void dealMagicDamage(Enemy e, Player p){
        int magicDamage = 10;
        System.out.println("You deal 10 damage with your fireball spell!");
        e.setHealth(e.getHealth() - magicDamage);
        p.setMagic(p.getMagic() - 1);
        if(e.getHealth() <= 0){
            win(e, p);
        }
    }//dealMagicDamage

    /**
     * Win screen when defeating an enemy
     * @param p player
     * @param e enemy the player has defeated
     */
    public void win(Enemy e, Player p){
        System.out.println("You have slain " + e.getName() + "!");
        String victorySpoils = "You receive " + e.getGold() + " gold";
        String potionsGrammar;
        if(e.getPotions() > 0) {
            if(e.getPotions() == 1){
                potionsGrammar = " potion!";
            }
            else{
                potionsGrammar = " potions!";
            }
            victorySpoils = victorySpoils + " and " + e.getPotions() + potionsGrammar;
            p.setPotions(p.getPotions() + e.getPotions());
        }
        System.out.println(victorySpoils + "\n______________________________");
        p.setGold(p.getGold() + e.getGold());
    }//win

}