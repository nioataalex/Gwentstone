package main;

import fileio.CardInput;

import java.util.ArrayList;

public class Player {
    private int mana;

    private ArrayList<CardInput> deck;

    private ArrayList<CardInput> hands;

    public ArrayList<CardInput> getDeck() {
        return deck;
    }

    private Hero hero;

    private boolean frozen;

    public boolean isFrozen() {
        return frozen;
    }

    public void setFrozen(boolean frozen) {
        this.frozen = frozen;
    }

    public Hero getHero() {
        return hero;
    }

    public void setHero(Hero hero) {
        this.hero = hero;
    }

    public void setDeck(ArrayList<CardInput> deck) {
        this.deck = deck;
    }

    public ArrayList<CardInput> getHands() {
        return hands;
    }

    public void setHands(ArrayList<CardInput> hands) {
        this.hands = hands;
    }

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    public Player() {
    }

    public void drawCard() {
            getHands().add(getDeck().get(0));
            getDeck().remove(0);
            setHands(getHands());
            setDeck(getDeck());

    }

    public void placeCard(final Card card, final ArrayList<ArrayList<Card>> table, final int i, final int j) {
        Minion minion = new Minion();
        if(minion.checkTypeMinion(card.getName()) == 1)
            table.get(i).add(card);
        else if (minion.checkTypeMinion(card.getName()) == 0)
            table.get(j).add(card);
        else {
            if(table.get(j).size() < 5)
                table.get(j).add(card);
            else table.get(i).add(card);
        }
        getHands().remove(card);
        setHands(getHands());
    }

    public void heroUsesAbility(final Player player, final ArrayList<ArrayList<Card>> table, final int affectedRow) {
        String name = player.getHero().getName();
        player.setMana(player.getMana() - player.getHero().getMana());
        hero.useHeroAbility(name, table.get(affectedRow));
    }

    public void useEnvCard(final int index, final Player player, final Environment effects, final ArrayList<ArrayList<Card>> table, final int affected_row) {
        Card card = player.getHands().get(index);
        player.setMana(player.getMana() - player.getHands().get(index).getMana());
        effects.useEnvcard(card, table, affected_row);
        player.getHands().remove(index);
        player.setHands(player.getHands());
    }
}

