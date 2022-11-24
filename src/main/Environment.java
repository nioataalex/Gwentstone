package main;

import java.util.ArrayList;

public class Environment extends Player {

    public Environment() {
    }

    /**
     * <p>
     * method where the ability of Firestorm, to decrease
     * the lifes of minions from a row, is used
     * @param table game table
     * @param affectedRow the row where all the changes happens
     */
    public void firestorm(final ArrayList<ArrayList<Card>> table,
                          final int affectedRow) {
        for (int i = 0; i < table.get(affectedRow).size(); i++) {
            table.get(affectedRow).get(i).setHealth(table.get(affectedRow).get(i).getHealth() - 1);
        }
    }

    /**
     * <p>
     * method where the ability of Winterfell, to froze all cards
     * from a row for a round, is used
     * @param table game table
     * @param affectedRow the row where all the changes happens
     */
    public void winterfell(final ArrayList<ArrayList<Card>> table,
                           final int affectedRow) {
        for (int i = 0; i < table.get(affectedRow).size(); i++) {
            table.get(affectedRow).get(i).setFrozen(true);
        }
    }

    /**
     * <p>
     * method where the ability of Heart Hound, to stole the minion of
     * the enemy with the biggest health, is used
     * @param table game table
     * @param affectedRow the row where all the changes happens
     * @param card used for swapping the cards
     */
    public void heartHound(final ArrayList<ArrayList<Card>> table,
                           final int affectedRow, final Card card) {
        int biggestHealth = 0;
        for (int i = 0; i < table.get(affectedRow).size(); i++) {
            if (table.get(affectedRow).get(i).getHealth() > biggestHealth) {
                 biggestHealth = table.get(affectedRow).get(i).getHealth();
            }

        }

        for (int i = 0; i < table.get(affectedRow).size(); i++) {
            if (table.get(affectedRow).get(i).getHealth() == biggestHealth) {
                if (affectedRow == 0) {
                    if (table.get(3).get(i) == null) {
                        card.setCard(table.get(affectedRow).get(i), table.get(3).get(i));
                    }
                } else if (affectedRow == 1) {
                    if (table.get(2).get(i) == null) {
                        card.setCard(table.get(affectedRow).get(i), table.get(2).get(i));
                    }
                } else if (affectedRow == 2) {
                    if (table.get(1).get(i) == null) {
                        card.setCard(table.get(affectedRow).get(i), table.get(1).get(i));
                    }
                } else if (affectedRow == 3) {
                    if (table.get(0).get(i) == null) {
                        card.setCard(table.get(affectedRow).get(i), table.get(0).get(i));
                    }
                }
                table.get(affectedRow).remove(i);
            }
        }
    }

    /**
     * <p>
     * method which verifies the type of Environment Card and applies its
     * special ability
     * @param card used to verfify what type of environment card is
     * @param table game table
     * @param affectedRow the row where all the changes happens
     */
    public void useEnvcard(final Card card, final ArrayList<ArrayList<Card>> table,
                           final int affectedRow) {

        if (card.getName().compareTo("Firestorm") == 0) {
            firestorm(table, affectedRow);
        }
        if (card.getName().compareTo("Winterfell") == 0) {
            winterfell(table, affectedRow);
        }
        if (card.getName().compareTo("Heart Hound") == 0) {
            heartHound(table, affectedRow, card);
        }
    }


}
