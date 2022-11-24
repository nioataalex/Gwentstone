package main;

import java.util.ArrayList;

public class Error {

    public Error () {

    }

    public int checkNotEnvironment(final String name) {
        if(name.compareTo("Firestorm") == 0)
            return 0;
        if(name.compareTo("Winterfell") == 0)
            return 0;
        if(name.compareTo("Heart Hound") == 0)
            return 0;
        return 1;
    }

    public boolean checkPlaceCard(final int mana1, final int mana2, final String name) {
        return mana1 <= mana2 && checkNotEnvironment(name) == 1;
    }

    public boolean checkRow(final Card card, final ArrayList<ArrayList<Card>> table, final int i, final int j) {
        Minion minion = new Minion();
        if(minion.checkTypeMinion(card.getName()) == 1 && table.get(i).size() == 5)
            return false;
        if(minion.checkTypeMinion(card.getName()) == 0 && table.get(j).size() == 5)
            return false;
        if(table.get(j).size() == 5)
            return false;
        return table.get(i).size() != 5;
    }

    public boolean checkSamePlayer(final int x1, final int x2) {
        if((x1 == 0 || x1 == 1) && (x2 == 2 || x2 == 3))
            return false;
        return (x2 != 0 && x2 != 1) || (x1 != 2 && x1 != 3);
    }
}
