package main;

import java.util.ArrayList;

public class Error {

    public Error() {
    }

    /**
     * <p>
     * mathod which verifies if the card is environment or not, returns 0
     * if is true and 1 if is false
     * @param name name of the card
     * @return 1 or 0 depends is the card is environment type or not
     */
    public int checkNotEnvironment(final String name) {
        if (name.compareTo("Firestorm") == 0) {
            return 0;
        }
        if (name.compareTo("Winterfell") == 0) {
            return 0;
        }
        if (name.compareTo("Heart Hound") == 0) {
            return 0;
        }
        return 1;
    }

    /**
     * <p>
     * method that checks if a card meets the conditions to be put
     * on a table
     * @param mana1 cost of the card
     * @param mana2 cost of the current player
     * @param name name of the card to verify the type of it
     * @return true or false
     */
    public boolean checkPlaceCard(final int mana1, final int mana2, final String name) {
        return mana1 <= mana2 && checkNotEnvironment(name) == 1;
    }

    /**
     * <p>
     * method that checks if you can put the minion card where it
     * need to be put (front or back row)
     * @param card card that needs to be  put on the table
     * @param  table game table
     * @param i variable for front row
     * @param j variable for back row
     * @return true or false
     */
    public boolean checkRow(final Card card,
                            final ArrayList<ArrayList<Card>> table,
                            final int i, final int j) {
        Minion minion = new Minion();
        if (minion.checkTypeMinion(card.getName()) == 1 && table.get(i).size() == 5) {
            return false;
        }
        if (minion.checkTypeMinion(card.getName()) == 0 && table.get(j).size() == 5) {
            return false;
        }
        if (table.get(j).size() == 5) {
            return false;
        }
        return table.get(i).size() != 5;
    }

    /**
     * <p>
     *  method that checks if you can apply different methods
     *  on your own cards
     * @param x1 variable for row
     * @param x2 variable for row
     * @return true or false
     */
    public boolean checkSamePlayer(final int x1, final int x2) {
        if ((x1 == 0 || x1 == 1) && (x2 == 2 || x2 == 3)) {
            return false;
        }
        return (x2 != 0 && x2 != 1) || (x1 != 2 && x1 != 3);
    }
}
