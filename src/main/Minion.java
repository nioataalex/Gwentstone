package main;

import java.util.ArrayList;

public class Minion extends Card {
    public Minion() {
    }

    /**
     * <p>
     * mathod which verifies if the card what type of minion it is, returns 0
     * if the minion has special ability and 1 if is false
     * @param name name of the card
     * @return 1 or 0 depends if it has ability or not
     */
    public int checkTypeMinion(final String name) {
        if (name.compareTo("The Ripper") == 0
                || name.compareTo("Miraj") == 0
                || name.compareTo("Goliath") == 0
                || name.compareTo("Warden") == 0) {
            return 1;
        }
        return 0;
    }

    /**
     * <p>
     * method where a minion card with the given coordinates uses
     * its special ability on a card with the given coordinates
     * @param x1 coordinate of attacker card
     * @param y1 coordinate of attacker card
     * @param x2 coordinate of attacked card
     * @param y2 coordinate of attacked card
     * @param table game table
     */
    public void useAbility(final int x1, final int y1,
                           final int x2, final int y2,
                           final ArrayList<ArrayList<Card>> table) {
        Card card1 = table.get(x1).get(y1);
        Card card2 = table.get(x2).get(y2);

        if (card1.getName().compareTo("The Ripper") == 0) {
            if (card2.getAttackDamage() >= 2) {
                card2.setAttackDamage(card2.getAttackDamage() - 2);
            } else {
                card2.setAttackDamage(0);
            }
        } else if (card1.getName().compareTo("Miraj") == 0) {
            int health1 = card1.getHealth();
            int health2 = card2.getHealth();
            card1.setHealth(health2);
            card2.setHealth(health1);
        } else if (card1.getName().compareTo("The Cursed One") == 0) {
            int health2 = card2.getHealth();
            int attack2 = card2.getAttackDamage();
            card2.setHealth(attack2);
            card2.setAttackDamage(health2);
        } else if (card2.getName().compareTo("Disciple") == 0) {
            card2.setHealth(card2.getHealth() + 2);
        }
    }

}
