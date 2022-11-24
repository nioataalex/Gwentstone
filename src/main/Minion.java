package main;

import java.util.ArrayList;

public class Minion extends Card{
    public Minion() {

    }
    public int checkTypeMinion(final String name) {
        if(name.compareTo("The Ripper") == 0 || name.compareTo("Miraj") == 0 || name.compareTo("Goliath") == 0 || name.compareTo("Warden") == 0)
            return 1;
        return 0;
    }

    public void useAbility(final int x1, final int y1, final int x2, final int y2, ArrayList<ArrayList<Card>> table) {
        Card card1 = table.get(x1).get(y1);
        Card card2 = table.get(x2).get(y2);

        if(card1.getName().compareTo("The Ripper") == 0) {
            if(card2.getAttackDamage() >= 2)
                card2.setAttackDamage(card2.getAttackDamage() - 2);
            else card2.setAttackDamage(0);
        } else if(card1.getName().compareTo("Miraj") == 0) {
            int health1 = card1.getHealth();
            int health2 = card2.getHealth();
            card1.setHealth(health2);
            card2.setHealth(health1);
        } else if(card1.getName().compareTo("The Cursed One") == 0) {
            int health2 = card2.getHealth();
            int attack2 = card2.getAttackDamage();
            card2.setHealth(attack2);
            card2.setAttackDamage(health2);
        } else if(card2.getName().compareTo("Disciple") == 0) {
            card2.setHealth(card2.getHealth() + 2);

        }
    }

}
