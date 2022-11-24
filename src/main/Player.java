package main;

import fileio.CardInput;

import java.util.ArrayList;

public class Player {
    private int mana;

    private ArrayList<CardInput> deck;

    private ArrayList<CardInput> hands;

    /**
     * <p>
     * getter for private variable decl
     * @return deck of cards
     */
    public ArrayList<CardInput> getDeck() {
        return deck;
    }

    private Hero hero;

    private boolean frozen;

    /**
     * <p>
     * getter for private variable frozen
     * @return true or false
     */
    public boolean isFrozen() {
        return frozen;
    }

    /**
     * <p>
     * setter  for private variable frozen
     * @param  frozen  checks if a card is frozen or not
     */
    public void setFrozen(final boolean frozen) {
        this.frozen = frozen;
    }

    /**
     * <p>
     * getter for private object Hero
     * @return hero of the player
     */
    public Hero getHero() {
        return hero;
    }

    /**
     * <p>
     * setter for private object hero
     * @param hero hero of the player
     */
    public void setHero(final Hero hero) {
        this.hero = hero;
    }

    /**
     * <p>
     * setter for private variable deck
     * @param deck deck of cards
     */
    public void setDeck(final ArrayList<CardInput> deck) {
        this.deck = deck;
    }

    /**
     * <p>
     * getter for private variable hands
     * @return hands cards that are in player's hands
     */
    public ArrayList<CardInput> getHands() {
        return hands;
    }

    /**
     * <p>
     * setter for private variable hands
     * @param hands cards in the player's hand
     */
    public void setHands(final ArrayList<CardInput> hands) {
        this.hands = hands;
    }

    /**
     * <p>
     * getter for private variable mana
     * @return cost of the player's mana
     */
    public int getMana() {
        return mana;
    }

    /**
     * <p>
     * setter for private variable mana
     * @param mana cost of the player's mana
     */
    public void setMana(final int mana) {
        this.mana = mana;
    }

    public Player() {
    }

    /**
     * <p>
     * method that takes a card from the deck and put it in hands
     */
    public void drawCard() {
            getHands().add(getDeck().get(0));
            getDeck().remove(0);
            setHands(getHands());
            setDeck(getDeck());

    }

    /**
     * <p>
     * method that places a minion card from the hand on a table
     * @param card card that is placed on the table
     * @param table game table
     * @param i front row
     * @param j back row
     */
    public void placeCard(final Card card,
                          final ArrayList<ArrayList<Card>> table,
                          final int i, final int j) {
        Minion minion = new Minion();
        if (minion.checkTypeMinion(card.getName()) == 1) {
            table.get(i).add(card);
        } else if (minion.checkTypeMinion(card.getName()) == 0) {
            table.get(j).add(card);
        } else {
            if (table.get(j).size() < 5) {
                table.get(j).add(card);
            } else {
                table.get(i).add(card);
            }
        }
        getHands().remove(card);
        setHands(getHands());
    }

    /**
     * <p>
     * method that uses the hero of a given player's ability
     * @param player the current player
     * @param table game table
     * @param affectedRow row where the abilty is used
     */
    public void heroUsesAbility(final Player player,
                                final ArrayList<ArrayList<Card>> table,
                                final int affectedRow) {
        String name = player.getHero().getName();
        player.setMana(player.getMana() - player.getHero().getMana());
        hero.useHeroAbility(name, table.get(affectedRow));
    }

    /**
     * <p>
     * method where an environment card from the hand of a given player
     * with the givn index, uses it's ability on a given row from the game table
     * @param index given index of the card
     * @param player given player
     * @param effects object that contains the ability of the environment card
     * @param table game table
     * @param affectedRow row where the ability of the environment card
     *                    is used
     */
    public void useEnvCard(final int index, final Player player,
                           final Environment effects,
                           final ArrayList<ArrayList<Card>> table,
                           final int affectedRow) {
        Card card = player.getHands().get(index);
        player.setMana(player.getMana() - player.getHands().get(index).getMana());
        effects.useEnvcard(card, table, affectedRow);
        player.getHands().remove(index);
        player.setHands(player.getHands());
    }
}

