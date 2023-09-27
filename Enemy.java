public class Enemy extends Player{
    /**
     * Generate a new enemy
     * @param n enemy name
     * @param h enemy health points
     * @param d enemy damage
     * @param g gold enemy drops
     * @param p potions enemy drops
     */
    public Enemy(String n, int h, int d, int g, int p){
        super(n, h, d, g, p);
    }//Enemy

    /**
     * Deal damage to player
     * @param p current player
     */
    public void dealDamage(Player p){
        System.out.println(getName() + " deals " + getDamage() + " damage!");
        p.setHealth(p.getHealth() - getDamage());
        if (p.getHealth() <= 0){
            loss(p);
        }
    }//dealDamage

    /**
     * loss screen, when player's health has dropped to 0 or below
     * @param p current player
     */
    private void loss(Player p){
        System.out.println(p.getName() + " has died");
    }//loss
}