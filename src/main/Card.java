package main;


import java.util.ArrayList;

public class Card {
    private boolean frozen;
    private boolean attacker;
    private int mana;
    private int attackDamage;
    private int health;
    private String description;
    private ArrayList<String> colors;
    private String name;

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
     * getter for private variable attacker
     * @return true or false
     */
    public boolean isAttacker() {
        return attacker;
    }

    /**
     * <p>
     * setter for private variable attacker
     * @param attacker checks if a card has attacked or not
     */
    public void setAttacker(final boolean attacker) {
        this.attacker = attacker;
    }

    public Card() {
    }

    /**
     * <p>
     * getter for private variable mana
     * @return mana of a card
     */
    public int getMana() {
        return mana;
    }

    /**
     * <p>
     * setter for private variable mana
     * @param mana cost of a card
     */
    public void setMana(final int mana) {
        this.mana = mana;
    }

    /**
     * <p>
     * getter for private variable attackDamage
     * @return  attackDamage of a card
     */
    public int getAttackDamage() {
        return attackDamage;
    }

    /**
     * <p>
     * setter for private variable attackDamage
     * @param attackDamage points of a attack that can be used by the card
     */
    public void setAttackDamage(final int attackDamage) {
        this.attackDamage = attackDamage;
    }

    /**
     * <p>
     * getter for private variable health
     * @return health of the card
     */
    public int getHealth() {
        return health;
    }

    /**
     * <p>
     * setter for private variable health
     * @param health life of a card
     */
    public void setHealth(final int health) {
        this.health = health;
    }

    /**
     * <p>
     * getter of private variable description
     * @return description of a card
     */
    public String getDescription() {
        return description;
    }


    /**
     * <p>
     * setter for private variable description
     * @param description short desprcription of a cardd
     */
    public void setDescription(final String description) {
        this.description = description;
    }

    /**
     * <p>
     * getter for private variable colors
     * @return colors of the card
     */
    public ArrayList<String> getColors() {
        return colors;
    }

    /**
     * <p>
     * setter for private variable colors
     * @param colors colors that compose the design of a card
     */
    public void setColors(final ArrayList<String> colors) {
        this.colors = colors;
    }

    /**
     * <p>
     * getter for private variable name
     * @return name of a card
     */
    public String getName() {
        return name;
    }

    /**
     * <p>
     * setter for private variable name
     * @param name name of a card
     */
    public void setName(final String name) {
        this.name = name;
    }


    /**
     * <p>
     * method where a card receives atributes of another card
     * @param card1
     * @param card2
     */
    public void setCard(final Card card1, final Card card2) {
        card2.setFrozen(card1.isFrozen());
        card2.setHealth(card1.getHealth());
        card2.setAttackDamage(card1.getAttackDamage());
        card2.setColors(card1.getColors());
        card2.setName(card1.getName());
        card2.setDescription(card1.getDescription());
    }

}

